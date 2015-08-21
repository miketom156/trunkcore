/**
 * 
 */
package com.job5156.core.biz.per;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.google.common.collect.Maps;
import com.job5156.core.bo.BizRetBo;
import com.job5156.core.bo.BizRetCode;
import com.job5156.core.bo.form.per.PerSendoutFormBo;
import com.job5156.core.bo.mail.PerSendoutMailBo;
import com.job5156.core.bo.mapping.ResumePreviewBo;
import com.job5156.core.common.MailConstants;
import com.job5156.core.common.helper.PerResumeCheckHelper;
import com.job5156.core.common.helper.ValidationHelper;
import com.job5156.core.common.helper.per.PerResumePreviewHelper;
import com.job5156.core.common.util.ChineseCodeChangeUtil;
import com.job5156.core.srv.MailSrv;
import com.job5156.core.srv.com.ComBlacklistSrv;
import com.job5156.core.srv.com.ComContactSrv;
import com.job5156.core.srv.per.PerLetterSrv;
import com.job5156.core.srv.per.PerResumeSrv;
import com.job5156.core.srv.per.PerSendoutSrv;
import com.job5156.core.srv.per.PerUserSrv;
import com.job5156.webapp.model.per.PerLetter;
import com.job5156.webapp.model.per.PerResume;
import com.job5156.webapp.model.per.PerSendout;
import com.job5156.webapp.model.per.PerUser;

/**
 * @author lyh
 * @Description
 * @date 2015年8月12日
 */
@Component
public class PerSendoutBiz {
	@Resource
	private PerResumeSrv perResumeSrv;
	@Resource
	private PerSendoutSrv perSendoutSrv;
	@Resource
	private ValidationHelper validationHelper;
	@Resource
	private PerUserSrv perUserSrv;
	@Resource
	private PerResumeCheckHelper perResumeCheckHelper;
	@Resource
	private ComContactSrv comContactSrv;
	@Resource
	private ComBlacklistSrv comBlacklistSrv;
	@Resource
	private PerResumePreviewHelper perResumePreviewHelper;
	@Resource
	private PerLetterSrv perLetterSrv;
	@Resource
	private MailSrv mailSrv;

	/**
	 * 外发简历第一步【个人管理中心点击外发简历】
	 * 
	 * @param model
	 * @param perId
	 *            用户id
	 */
	public BizRetBo<Map<String, Object>> list(Integer perId, PerSendoutFormBo perSendoutFormBo) {
		BizRetBo<Map<String, Object>> bizRetBo = new BizRetBo<Map<String, Object>>();
		if (perSendoutFormBo != null && perSendoutFormBo.getEmailFormat() == null) {
			perSendoutFormBo.setEmailFormat(0); // 默认简历中文
		}
		Map<String, Object> map = Maps.newHashMap();
		List<PerSendout> outgoResumeList = perSendoutSrv.getPerSendoutList(perId);
		map.put("outgoResumeList", outgoResumeList);
		map.put("resumeList", perResumeSrv.getResumeCnNameList(perId));
		bizRetBo.setItem(map);
		bizRetBo.setRef("/per/outResume");
		bizRetBo.setRetCode(BizRetCode.SUCCESS);
		return bizRetBo;
	}

	/**
	 * 更多外发简历历史记录
	 * 
	 * @param perId
	 */
	public BizRetBo<PerSendout> more(Integer perId) {
		List<PerSendout> outgoResumeList = perSendoutSrv.getPerSendoutList(perId);
		BizRetBo<PerSendout> bizRetBo = new BizRetBo<>();
		bizRetBo.setItems(outgoResumeList);
		bizRetBo.setRef("blank");
		bizRetBo.setRetCode(BizRetCode.SUCCESS);
		return bizRetBo;
	}

	/**
	 * 外发简历第二步[保存外发信息]
	 * 
	 * @param request
	 * @param model
	 * @param formVo
	 * @param result
	 * @return
	 */
	public BizRetBo<Map<String, Object>> save(PerSendoutFormBo formBo, BindingResult result, Integer perId) {
		BizRetBo<Map<String, Object>> bizRetBo = new BizRetBo<>();
		Map<String, Object> map = Maps.newHashMap();
		if (!validationHelper.checkCaptchaCode(formBo.getValidCode())) {
			map.put("error", "验证码错误");
			map.put("status", false);
			bizRetBo.setRef("blank");
			bizRetBo.setRetCode(BizRetCode.FAIL);
			bizRetBo.setItem(map);
			return bizRetBo;
		} else {

			if (!result.hasErrors()) {
				PerUser perUser = perUserSrv.get(perId);

				// 查询当天的外发简历次数
				if (10 <= perSendoutSrv.findSendoutCountByToday(perId)) {
					map.put("error", "一天最多能发送10份简历");
					map.put("status", false);
					bizRetBo.setRef("blank");
					bizRetBo.setRetCode(BizRetCode.FAIL);
					bizRetBo.setItem(map);
					return bizRetBo;
				}

				if (perUser.getPublicSettings() == null || perUser.getPublicSettings() != 0) {
					map.put("error", "简历外发失败，您的简历已设置为保密状态，请公开您的简历再尝试外发");
					map.put("status", false);
					bizRetBo.setRef("blank");
					bizRetBo.setRetCode(BizRetCode.FAIL);
					bizRetBo.setItem(map);
					return bizRetBo;
				}

				if (formBo.getResumeId() == null || formBo.getResumeId() <= 0) {
					map.put("error", "无法获取到您的默认简历信息，请进入简历中心进行完善");
					map.put("status", false);
					bizRetBo.setRef("blank");
					bizRetBo.setRetCode(BizRetCode.FAIL);
					bizRetBo.setItem(map);
					return bizRetBo;
				}
				PerResume perResume = perResumeSrv.get(formBo.getResumeId());
				if (perResume == null) {
					map.put("error", "无法获取到您的默认简历信息，请进入简历中心进行完善");
					map.put("status", false);
					bizRetBo.setRef("blank");
					bizRetBo.setRetCode(BizRetCode.FAIL);
					bizRetBo.setItem(map);
					return bizRetBo;
				}

				// 校验简历的完整度
				String msg = perResumeCheckHelper.resumeCompleteMsg(perResume);
				if (StringUtils.isNotBlank(msg)) {
					map.put("error", "您的简历不完整，请先补充必填信息再执行操作[" + msg + "]");
					map.put("status", false);
					bizRetBo.setRef("blank");
					bizRetBo.setRetCode(BizRetCode.FAIL);
					bizRetBo.setItem(map);
					return bizRetBo;
				}

				// 校验个人是否在企业黑名单中
				Integer comId = comContactSrv.getComIdByEmail(formBo.getEmail());
				if (comId > 0 && StringUtils.isNotBlank(formBo.getComName())) {
					if (comBlacklistSrv.checkExistBlacklist(comId, perId)) {
						map.put("error", "您的选中的企业，婉拒了您的邮件！");
						map.put("status", false);
						bizRetBo.setRef("blank");
						bizRetBo.setRetCode(BizRetCode.FAIL);
						bizRetBo.setItem(map);
						return bizRetBo;
					}
				}

				ResumePreviewBo vo = perResumeSrv.getPreviewCnVo(formBo.getResumeId());

				if (vo != null && vo.getPerUserVo() != null && perId.equals(vo.getPerUserVo().getId())) {
					// 预览的时候同时将中英文的简历列出来，有中文没有英文的只列中文
					String resumeContentCn = perResumePreviewHelper.getCnMailContent(vo);
					ResumePreviewBo enVo = perResumeSrv.getPreviewEnVo(formBo.getResumeId());
					if (enVo == null || enVo.getDisplayEnResume() == null || enVo.getDisplayEnResume() == 0) {
						enVo = null;
					}
					String resumeContentEn = perResumePreviewHelper.getEnMailContent(enVo);

					// 有附加求职信的在头部加上求职信内容
					String jobLetter = "";
					if (formBo.isUseLetter()) {
						PerLetter letter = perLetterSrv.getDefaultLetter(perId);
						if (letter != null) {
							jobLetter = letter.getJobLetter();
						}
					}
					// 保存记录
					perSendoutSrv.save(perId, vo.getResumeName(), formBo);
					String subject = "智通人才网-" + perUser.getUserName() + "应聘【" + formBo.getPosName() + "】(外发简历)";
					// 发送邮件
					mailSrv.insertEmailQueue(
							new PerSendoutMailBo(new PerSendoutMailBo.PlaceHolderVo(jobLetter, formBo, perUser.getUserName(), resumeContentCn, resumeContentEn), new String[] { formBo.getEmail() },
									formBo.getEmailFormat(), subject), MailConstants.MAIL_TASK_PER_SEND_OUT);
					map.put("status", true);
					bizRetBo.setRef("blank");
					bizRetBo.setRetCode(BizRetCode.SUCCESS);
					bizRetBo.setItem(map);
					return bizRetBo;
				} else {
					// 此简历ID不存在或不是该用户的，不允许预览，至于返回什么页面，进行什么提示，实际到时待定
					map.put("status", "noexist");
					bizRetBo.setRetCode(BizRetCode.FAIL);
				}
			} else {
				map.put("status", false);
				bizRetBo.setRetCode(BizRetCode.FAIL);
			}
		}

		bizRetBo.setRef("blank");
		bizRetBo.setItem(map);
		return bizRetBo;
	}


    /**
     * 删除外发简历历史记录
     * @param sendReIndex 外发简历序号ids
     * 
     * @return
     */
	public BizRetBo<String> del(String sendReIndex) {
		BizRetBo<String> bizRetBo=new BizRetBo<>(BizRetCode.SUCCESS);
		if (StringUtils.isNotEmpty(sendReIndex)) {
			String[] ids = sendReIndex.split(",");
			for (String id : ids) {
				perSendoutSrv.delSendout(perSendoutSrv.get(NumberUtils.toInt(id)));
			}
		}
		bizRetBo.setRef("blank");
		return bizRetBo;
	}

	/**
	 * 预览个人简历
	 * @param request
	 * @return
	 */
	public BizRetBo<Map<String,Object>> preview(  PerSendoutFormBo formBo, BindingResult result,Integer perId) {
       BizRetBo<Map<String,Object>> bizRetBo=new BizRetBo<>();
       Map<String,Object> map=Maps.newHashMap();
        if(!result.hasErrors()) {
            PerUser perUser = perUserSrv.get(perId);
            ResumePreviewBo vo = perResumeSrv.getPreviewCnVo(formBo.getResumeId());

            if(vo != null && vo.getPerUserVo() != null && perId.equals(vo.getPerUserVo().getId())) {
                //预览的时候同时将中英文的简历列出来，有中文没有英文的只列中文
                String resumeContentCn = perResumePreviewHelper.getCnMailContent(vo);
                ResumePreviewBo enVo = perResumeSrv.getPreviewEnVo(formBo.getResumeId());
                if(enVo == null || enVo.getDisplayEnResume() == null || enVo.getDisplayEnResume() == 0){
                    enVo = null;
                }
                String resumeContentEn = perResumePreviewHelper.getEnMailContent(enVo);
                //有附加求职信的在预览头部加上求职信内容
                String jobLetter = "";
                if(formBo.isUseLetter()) {
                    PerLetter letter = perLetterSrv.getDefaultLetter(perId);
                    if(letter != null) {
                    	jobLetter = letter.getJobLetter();
                    }
                }
                if(formBo.getEmailFormat().equals(1)){
                	resumeContentCn = ChineseCodeChangeUtil.toTraditional(resumeContentCn);
                	formBo.setPosName(ChineseCodeChangeUtil.toTraditional(formBo.getPosName()));
                    formBo.setComName(ChineseCodeChangeUtil.toTraditional(formBo.getComName()));
                    jobLetter = ChineseCodeChangeUtil.toTraditional(jobLetter);
                    perUser.setUserName(ChineseCodeChangeUtil.toTraditional(perUser.getUserName()));
                }
               map.put("userName", perUser.getUserName());
               map.put("resumeContentCn", resumeContentCn);
               map.put("resumeContentEn", resumeContentEn);
               map.put("vo", formBo);
               map.put("jobLetter", jobLetter);
               bizRetBo.setItem(map);
               bizRetBo.setRef("/per/sendoutPreview");
               bizRetBo.setRetCode(BizRetCode.SUCCESS);
               
            } else {
                //此简历ID不存在或不是该用户的，不允许预览，至于返回什么页面，进行什么提示，实际到时待定
            }
        }

      return  bizRetBo;
	}

	
	
	
	
	
}
