package com.job5156.core.srv.per;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.job5156.core.bo.mapping.ResumePreviewBo;
import com.job5156.core.common.CommonEnum;
import com.job5156.core.common.Constants;
import com.job5156.core.common.MailConstants;
import com.job5156.core.common.helper.PerResumeCheckHelper;
import com.job5156.core.common.helper.per.PerResumePreviewHelper;
import com.job5156.core.common.util.HttpAsyncUtils;
import com.job5156.core.common.util.KeyMaskUtil;
import com.job5156.core.common.util.MD5Builder;
import com.job5156.core.srv.MailSrv;
import com.job5156.core.srv.TalebaseComTestSrv;
import com.job5156.core.srv.com.ComInfoSrv;
import com.job5156.core.srv.com.ComPositionSrv;
import com.job5156.core.srv.com.ComReceiveSrv;
import com.job5156.webapp.model.com.ComInfo;
import com.job5156.webapp.model.com.ComPosition;
import com.job5156.webapp.model.com.ComReceive;
import com.job5156.webapp.model.com.talebase.TalebaseComTestAuto;
import com.job5156.webapp.model.per.PerResume;
import com.job5156.webapp.model.per.PerUser;

/**
 * 个人应聘线上网络职位
 * 注意：包内类，只在business层使用
 * 不要定义成public class,以保证方法是从接口或抽象类进入
 *
 * @autor:leeton
 * Date: 2014/11/7
 * Time: 20:40
 */
@Component
class PerApplyNetPositionSrv extends PerImpl {

    @Resource
    private PerApplySrv perApplySrv ;
    @Resource
    private PerResumeSrv perResumeSrv ;
    @Resource
    private PerResumeCheckHelper perResumeCheckHelper ;
   /* @Resource
    private PosFilterHelper posFilterHelper ;*/
    @Resource
    private PerUserSrv perUserSrv ;
    @Resource
    private ComInfoSrv comInfoSrv ;
    @Resource
    private PerResumePreviewHelper perResumePreviewHelper ;
    @Resource
    private MailSrv mailSrv ;
    @Resource
    private TalebaseComTestSrv talebaseComTestSrv ;
    @Resource
    private ComPositionSrv comPositionSrv ;
    @Resource
    private PerUserConnectSrv perUserConnectSrv;
    @Resource
    private ResumeImpl resumeImpl ;
    @Resource
    private ComReceiveSrv comReceiveSrv ;

    @Override
    public PerEnum.ApplyErrType apply(Integer posNo, Integer perUserId,Integer resumeId,boolean userSecondApply) {

        ComPosition comPosition = comPositionSrv.findByPosNo(posNo);
        PerEnum.ApplyErrType applyErrType = checkAndApply(perUserId, resumeId, comPosition, true);

        return applyErrType;
    }


    private PerEnum.ApplyErrType checkAndApply(Integer perId, Integer resumeId, ComPosition comPosition, boolean confirm) {

        //常规的id合理性校验-----start-----
        if(perId ==null || perId <= 0) {
            return PerEnum.ApplyErrType.APPLY_FAILURE_UNLOGIN;
        }

        if(resumeId == null || resumeId <= 0) {
            return PerEnum.ApplyErrType.APPLY_FAILURE_RESUME_NO_SELECT;
        }

        PerResume perResume = perResumeSrv.get(resumeId);
        if(perResume == null) {
            return PerEnum.ApplyErrType.APPLY_FAILURE_RESUME_NOT_EXIST;
        }

        if(!perId.equals(perResume.getAccountId())) {
            return PerEnum.ApplyErrType.APPLY_FAILURE_RESUME_NOT_OWNER;
        }

        if(perResume.getPerUser() == null) {
            return PerEnum.ApplyErrType.APPLY_FAILURE_USER_NOT_EXIST;
        }

        if(comPosition == null) {
            return PerEnum.ApplyErrType.APPLY_FAILURE_POSITION_NOT_EXIST;
        }

        if(comPosition.getComInfo() == null) {
            return PerEnum.ApplyErrType.APPLY_FAILURE_COM_NOT_EXIST;
        }
        //常规的id合理性校验-----end-----

        //当简历不通过审核，也是不允许进行投递的
        if(perResume.getPass() <= 0) {
            return PerEnum.ApplyErrType.APPLY_FAILURE_RESUME_UNAUDIT;
        }

        //职位状态
        if(comPosition.getPosStatus() <= CommonEnum.PositionStatus.WAIT_FOR_AUDIT.getValue()) {
            return PerEnum.ApplyErrType.APPLY_FAILURE_POSITION_STOP;
        }

        /**
         * 2015-07-10 暂时去掉职位过滤条件
         */
        //职位投递过滤要求
       /* String filterResultStr = posFilterHelper.checkPosFilter(comPosition, perResume);
        if(StringUtils.isNotBlank(filterResultStr)) {
            return PerEnum.ApplyErrType.APPLY_FAILURE_RESUME_UNMATCH;
        }*/

        //企业黑名单
        if(perApplySrv.checkExistBlacklist(comPosition.getComInfo().getId(), perId)) {
            return PerEnum.ApplyErrType.APPLY_FAILURE_RESUME_BLACK;
        }

        //应聘已超过500次
        if(perApplySrv.getApplyCount(perId) >= 500) {
            return PerEnum.ApplyErrType.APPLY_LIMIT;
        }

        //校验正式简历的完整度
        String msg = "";
        if (perResume.getResumeType() == CommonEnum.ResumeType.COMMON.getValue() || perResume.getResumeType() == CommonEnum.ResumeType.CHANGSHA.getValue() ) {
            msg = perResumeCheckHelper.resumeCompleteMsg(perResume); //正式简历应聘
            if(StringUtils.isNotBlank(msg)) {
                PerEnum.ApplyErrType applyErr = PerEnum.ApplyErrType.APPLY_FAILURE_RESUME_INCOMPLATE;
                applyErr.setErrMsg("您的简历不完整，请先补充必填信息再执行操作["+msg+"]");
                return applyErr;
            }
        }

        //名片应聘检查
        if (perResume.getResumeType() == CommonEnum.ResumeType.CARD.getValue()) {
            //职位是否接受名片应聘
            //TODO:暂时取消 默认接受名片的职位
           // if(!PosNameFilterUtil.isMatchCardJob(comPosition.getPosName())){ //默认接受名片的职位,即使企业没有手动设置接受名片应聘
                if(CommonEnum.ResumeType.CARD.getValue() != comPosition.getAcceptResumeType()
                        && CommonEnum.ResumeType.CARD.getValue() == perResume.getResumeType()) {
                    return PerEnum.ApplyErrType.APPLY_NOT_ACCPET_CARD;
                }
           // }

            //校验名片的完整度
            msg = perResumeCheckHelper.cardCompleteMsg(perResume);
            if(StringUtils.isNotBlank(msg)) {
               return PerEnum.ApplyErrType.APPLY_CARD_NOT_PERFECT;
            }

            //手机是否激活
            PerUser perUser = perUserSrv.get(perId);
            if (StringUtils.isBlank(perUser.getMobile())
                    || perUser.getMobileActivation() == null
                    || perUser.getMobileActivation() == 0 ) {
                return PerEnum.ApplyErrType.APPLY_NOT_VERIFY_MOBILE;
            }
        }

/*        //3个月内应聘同一家公司最多只能应聘该公司的3个职位
        if(perApplySrv.getCandidatePosCount(comPosition.getComId(),comPosition.getId()) >= 3){
            return ApplyErrType.APPLY_LIMIT_BY_POSTION;
        }

        */

        Integer per30DaysApply = perApplySrv.count30DaysApplyTimes(comPosition.getId(), perId);
        if(per30DaysApply == 1 && !confirm) {
            return PerEnum.ApplyErrType.SECOND_APPLY;
        } else if(per30DaysApply >= 2) {
            return PerEnum.ApplyErrType.APPLY_FAILURE_WARN_TWICE;
        }

        //所有校验都通过后，写入2张表，分别是个人应聘记录表和企业收到简历表
        Integer comReceiveId;
        if(perApplySrv.countTodayApplyTimes(comPosition.getComId(), resumeId, comPosition.getId()) < 2){ //防止重复提交
            comReceiveId = perApplySrv.applySave(perResume, comPosition);
        }else{
            return PerEnum.ApplyErrType.APPLY_FAILURE_WARN_DUPLICATE;
        }
        //发送微信通知
        sendWeiXinNotice(comPosition, perResume);
        
        if(comPosition.getComContactBase().getRejectEmail() != null && comPosition.getComContactBase().getRejectEmail() == 1) {
            //不发送邮件
        } else {
            //给企业发送应聘邮件
            PerUser perUser = perUserSrv.get(perId);
            ComInfo comInfo = comInfoSrv.get(comPosition.getComId());
            String posName = comPosition.getPosName();
            String comName = comInfo.getComName();
            ResumePreviewBo vo = perResumeSrv.getPreviewCnVo(perResume.getId());
            vo.setPosName(posName);
            String resumeContentCn = perResumePreviewHelper.getCnMailContent(vo);
            String resumeContentEn = "";

            if(perResume.getResumeId() != null && perResume.getResumeId() > 0) {
                PerResume resumeEn = perResumeSrv.get(perResume.getResumeId());
                //个人的英文简历是否公开
                if(resumeEn != null && resumeEn.getDisplayEnResume().equals(1)){
                    resumeContentEn = perResumePreviewHelper.getEnMailContent(perResumeSrv.getPreviewEnVo(perResume.getId()));
                }
            }

            //String subject = posName + " - 收到" +perUser.getUserName()+"的简历,请惠阅";//个人应聘职位的邮件主题
            String subject = "智通人才网-" + perUser.getUserName() + "应聘【" + posName + "】";//个人应聘职位的邮件主题

            List<String> emailList = new Gson().fromJson(comPosition.getComContactBase().getEmail(), new TypeToken<List<String>>() {}.getType());
            List<String> fixEmailList = Lists.newArrayList();
            if(CollectionUtils.isNotEmpty(emailList)) {
                for(String email : emailList) {
                    if(email.contains("；")) {
                        Collections.addAll(fixEmailList, email.split("；"));
                    } else {
                        fixEmailList.add(email);
                    }
                }
            }
            String imgUrlPara= "imgKey="+ KeyMaskUtil.enNumberMask(comReceiveId, Constants.EMAIL_IMG_APPLY_JOB)+"&maskCode="+ MD5Builder.md5(comReceiveId + "", perResume.getId().toString());
            ApplyJobMailVo applyJobMailVo = new ApplyJobMailVo(
                    new ApplyJobMailVo.PlaceHolderVo(
                            comName, posName, perUser.getUserName(), resumeContentCn, resumeContentEn, imgUrlPara
                    ),
                    fixEmailList.toArray(new String[CollectionUtils.size(fixEmailList)]),
                    subject
            );
            applyJobMailVo.setEmailType(comPosition.getComContactBase().getEmailFormat());
            if(StringUtils.isNotEmpty(perUser.getEmail())) {
                //设置个人邮箱
                applyJobMailVo.setReplyAddr(perUser.getEmail());
            }
            //发送邮件
            //TODO:警告，线上的发邮件，记得要用队列方式方式，以避免卡死
              //mailSrv.send(applyJobMailVo);
            if (CommonEnum.ResumeType.COMMON.getValue() == perResume.getResumeType()) { //TODO:暂时:正式简历才发邮件，名片不发邮件
            	if(!resumeImpl.isFiltered(resumeId, comPosition.getId())){
            		mailSrv.insertEmailQueue(applyJobMailVo, MailConstants.MAIL_TASK_PER_APPLY_JOB);
            	}
                //发送测评邀请邮件
                List<TalebaseComTestAuto> list = talebaseComTestSrv.findByComTestAutoList(comPosition.getComId(), comPosition.getId());
                if(CollectionUtils.isNotEmpty(list)){
                    TalebaseComTestAuto cta = list.get(0);//2015-07-24 Andy 将(TalebaseComTestAuto)list.get(0),修改为list.get(0)
                    ComReceive comReceive = new ComReceive();
                    comReceive.setComId(comPosition.getComId());
                    comReceive.setPosId(comPosition.getId());
                    comReceive.setPerUserId(perId);
                    comReceive.setPosName(comPosition.getPosName());
                    comReceive.setUserName(perUser.getUserName());
                    comReceive.setEmail(perUser.getEmail());
                    comReceive.setResumeId(resumeId);
                    if(null != cta.getProductId() && cta.getProductId() > 0){
                        talebaseComTestSrv.comTestInvite(comReceive, comPosition,cta.getProductId(), cta.getCompleteCycle());
                    }
                }
            }

        }

        return PerEnum.ApplyErrType.APPLY_SUCCESS;
    }

	/**
	 * 发送微信通知（智聘微信）
	 * @param comPsotion 企业发布职位
	 * @param perResume 用户简历
	 */
	public void sendWeiXinNotice(ComPosition comPosition, PerResume perResume) {
		WxZpReceiveResumeVo wxZpReceiveResume = new WxZpReceiveResumeVo();
		List<String> openIdList = perUserConnectSrv.findOpenIdByComUserId(comPosition.getComUserId(), Constants.CONNECT_TYPE_WEIXIN);
		if(CollectionUtils.isEmpty(openIdList)) {
			return;
		}
		Integer resourceId = comReceiveSrv.getId(comPosition.getComId(), comPosition.getId(), perResume.getId());
		Map<String, Object> map = perResume.getMap();
		wxZpReceiveResume.setResumeId(String.valueOf(perResume.getId()));
		wxZpReceiveResume.setSourceId(String.valueOf(resourceId));
		wxZpReceiveResume.setOpenid(openIdList);
		wxZpReceiveResume.setType("candidate");
		wxZpReceiveResume.setPosition(comPosition.getPosName());
		wxZpReceiveResume.setResume(perResume.getResumeName());
		
		wxZpReceiveResume.setUserName(perResume.getPerUser().getUserName());
		wxZpReceiveResume.setWorkYears(perResume.getPerUser().getJobyearType()+"年");
		wxZpReceiveResume.setLastComName(String.valueOf(map.get("lastComName")));
		wxZpReceiveResume.setLastJobName(String.valueOf(map.get("lastJobName")));
		
		HttpAsyncUtils.postObject(Constants.ZHIPIN_WEIXIN_URL_NOTICE, wxZpReceiveResume, "", "");
		
	}
	
}
