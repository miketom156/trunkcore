/**
 * 
 */
package com.job5156.core.biz;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.WebUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.job5156.core.bo.BizRetBo;
import com.job5156.core.bo.BizRetCode;
import com.job5156.core.common.Constants;
import com.job5156.core.common.helper.CheckLoginHelper;
import com.job5156.core.common.helper.CookieExtractHelper;
import com.job5156.core.common.util.KeyMaskUtil;
import com.job5156.core.srv.com.ComPositionSrv;
import com.job5156.core.srv.per.PerApplySrv;
import com.job5156.core.srv.per.PerEnum;
import com.job5156.core.srv.per.PerResumeSrv;
import com.job5156.core.srv.per.iper.IPer;
import com.job5156.webapp.model.com.ComPosition;

/**
 * @author lyh
 * @Description
 * @date 2015年8月14日
 */
@Component
public class ApplyJobBiz {

	@Resource
	private CheckLoginHelper checkLoginHelper;
	@Resource
	private ComPositionSrv comPositionSrv;
	@Resource
	private IPer per;
	@Resource
	private CookieExtractHelper cookieExtractHelper;
	@Resource
	private PerResumeSrv perResumeSrv;
	@Resource
	private PerApplySrv perApplySrv;

	public BizRetBo<Map<String, Object>> apply(Integer perId, Integer posId, Integer resumeId, boolean confirm, HttpServletRequest request) {
		BizRetBo<Map<String, Object>> bizRetBo = new BizRetBo<>();
		Map<String, Object> map = Maps.newHashMap();
		if (checkLoginHelper.isPerUserLogin(request) < 0) {
			map.put("state", false);
			bizRetBo.setRef("blank");
			bizRetBo.setItem(map);
			return bizRetBo;
		} else {
			map.put("state", true);
			Integer posNo = KeyMaskUtil.posIdToNo(posId, (short) 1);
			PerEnum.ApplyErrType applyErrType = per.apply(posNo, perId, resumeId, confirm);
			if (applyErrType == PerEnum.ApplyErrType.APPLY_SUCCESS) {
				map.put("msg", "应聘成功");
			} else if (applyErrType == PerEnum.ApplyErrType.SECOND_APPLY) {
				// 提示第二次应聘
				map.put("secondApply", true);
			} else {
				map.put("errmsg", applyErrType.getErrMsg());
				map.put("errType", applyErrType.getErrType());
			}
		}
		bizRetBo.setRef("blank");
		bizRetBo.setItem(map);
		return bizRetBo;
	}

	public BizRetBo<Map<String, Object>> batchApply(Integer[] posIdList, Integer resumeId, HttpServletRequest request, Model model) {
		BizRetBo<Map<String, Object>> bizRetBo = new BizRetBo<>();
		Map<String, Object> map = Maps.newHashMap();

		if (checkLoginHelper.isPerUserLogin(request) < 0) {
			map.put("state", false);
			bizRetBo.setItem(map);
			return bizRetBo;
		} else {
			map.put("state", true);
			List<ApplyResultBo> resultVoList = Lists.newArrayList();
			Integer perId = cookieExtractHelper.getPerUserIdFromCookie(WebUtils.getCookie(request, Constants.COOKIE_KEY_PER));

			for (Integer posId : posIdList) {
				ComPosition comPosition = comPositionSrv.findById(posId);
				// 备注：批量应聘是默认不提示二次应聘提醒
				// PerApplyService.ApplyErrType applyErrType = perApplyService.checkAndApply(perId, resumeId, comPosition, true);
				Integer posNo = KeyMaskUtil.posIdToNo(posId, (short) 1);
				PerEnum.ApplyErrType applyErrType = per.apply(posNo, perId, resumeId, true);
				ApplyResultBo resultVo = new ApplyResultBo();
				resultVo.setComId(comPosition == null ? 0 : comPosition.getComId());
				resultVo.setPosId(comPosition == null ? 0 : comPosition.getId());
				resultVo.setComName(comPosition == null ? "" : comPosition.getComInfo().getComName());
				resultVo.setPosName(comPosition == null ? "" : comPosition.getPosName());

				if (applyErrType == PerEnum.ApplyErrType.APPLY_SUCCESS) {
					resultVo.setResult(true);
					resultVo.setResultMsg("应聘成功");
					resultVo.setErrType("apply_success");
				} else {
					resultVo.setResult(false);
					resultVo.setResultMsg(applyErrType.getErrMsg());
					resultVo.setErrType(applyErrType.getErrType());
				}

				resultVoList.add(resultVo);
			}

			map.put("resultVoList", resultVoList);
		}
		bizRetBo.setItem(map);
		bizRetBo.setRef("blank");
		bizRetBo.setRetCode(BizRetCode.SUCCESS);
		return bizRetBo;
	}

	public BizRetBo<Map<String, Object>> selectResume(@PathVariable Integer posId, HttpServletRequest request, Model model) {
		BizRetBo<Map<String, Object>> bizRetBo = new BizRetBo<>();
		Map<String, Object> map = Maps.newHashMap();
		if (checkLoginHelper.isPerUserLogin(request) < 0) {
			map.put("state", false);
			bizRetBo.setItem(map);
			bizRetBo.setRef("blank");
			bizRetBo.setRetCode(BizRetCode.FAIL);
			return bizRetBo;
		} else {
			map.put("state", true);
			Integer perId = cookieExtractHelper.getPerUserIdFromCookie(WebUtils.getCookie(request, Constants.COOKIE_KEY_PER));

			// 常规的id合理性校验-----start-----
			if (perId == null || perId <= 0) {
				map.put("errmsg", "请先登录再进行应聘操作");
				bizRetBo.setItem(map);
				bizRetBo.setRef("blank");
				bizRetBo.setRetCode(BizRetCode.FAIL);
				return bizRetBo;
			}
			if (posId > 0) {
				ComPosition comPosition = comPositionSrv.findById(posId);
				if (comPosition == null) {
					map.put("errmsg", "该职位信息不存在");
					bizRetBo.setRef("blank");
					bizRetBo.setItem(map);
					bizRetBo.setRetCode(BizRetCode.FAIL);
					return bizRetBo;
				}

				if (comPosition.getComInfo() == null) {
					map.put("errmsg", "该企业不存在");
					bizRetBo.setRef("blank");
					bizRetBo.setItem(map);
					bizRetBo.setRetCode(BizRetCode.FAIL);
					return bizRetBo;
				} else {
					// 常规的id合理性校验-----end-----
					Integer per30DaysApply = perApplySrv.count30DaysApplyTimes(posId, perId);
					if (per30DaysApply == 1) {
						map.put("tips", "您已在近30天内应聘过此职位，同一职位最多应聘2次");
					} else if (per30DaysApply == 2) {
						map.put("errmsg", "同一职位30天内不能应聘超过2次");
						bizRetBo.setRef("blank");
						bizRetBo.setItem(map);
						bizRetBo.setRetCode(BizRetCode.FAIL);
						return bizRetBo;
					}
				
				}
			
			}
			map.put("resumeList", perResumeSrv.getResumeCnNameList(perId));
			bizRetBo.setRef("blank");
			bizRetBo.setItem(map);
			bizRetBo.setRetCode(BizRetCode.SUCCESS);
		}
		return bizRetBo;
	}

	/**
	 * 批量应聘结果Vo
	 * 
	 * @author xh
	 * @date 2014-5-7 下午2:20:02
	 */
	class ApplyResultBo {
		private Integer posId;
		private Integer comId;
		private String posName;
		private String comName;
		private String resultMsg; // 应聘失败原因
		private boolean result; // 应聘结果true=成功，false=失败
		private String errType; // 错误类型

		public Integer getPosId() {
			return posId;
		}

		public void setPosId(Integer posId) {
			this.posId = posId;
		}

		public Integer getComId() {
			return comId;
		}

		public void setComId(Integer comId) {
			this.comId = comId;
		}

		public String getPosName() {
			return posName;
		}

		public void setPosName(String posName) {
			this.posName = posName;
		}

		public String getComName() {
			return comName;
		}

		public void setComName(String comName) {
			this.comName = comName;
		}

		public String getResultMsg() {
			return resultMsg;
		}

		public void setResultMsg(String resultMsg) {
			this.resultMsg = resultMsg;
		}

		public boolean isResult() {
			return result;
		}

		public void setResult(boolean result) {
			this.result = result;
		}

		public String getErrType() {
			return errType;
		}

		public void setErrType(String errType) {
			this.errType = errType;
		}
	}

}
