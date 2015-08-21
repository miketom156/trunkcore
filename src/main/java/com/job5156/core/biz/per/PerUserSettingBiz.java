/**
 * 
 */
package com.job5156.core.biz.per;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.google.common.collect.Maps;
import com.job5156.core.bo.BizRetBo;
import com.job5156.core.bo.BizRetCode;
import com.job5156.core.bo.PerUserInfoFormBoTemplate.FilterComIdBo;
import com.job5156.core.bo.form.per.PerSubscribeEmailFormBo;
import com.job5156.core.bo.form.per.PerSubscribeMobileFormBo;
import com.job5156.core.common.Constants;
import com.job5156.core.common.helper.CookieExtractHelper;
import com.job5156.core.common.helper.ValidationHelper;
import com.job5156.core.common.helper.per.PassAccountHelper;
import com.job5156.core.common.util.MD5Builder;
import com.job5156.core.common.util.XssFilterStringUtils;
import com.job5156.core.eao.per.PerSettingSrv;
import com.job5156.core.srv.com.ComInfoSrv;
import com.job5156.core.srv.per.PerUserConnectSrv;
import com.job5156.core.srv.per.PerUserSrv;
import com.job5156.webapp.model.com.ComInfo;
import com.job5156.webapp.model.per.PerSubscription;
import com.job5156.webapp.model.per.PerUser;
import com.job5156.webapp.model.per.PerUserConnect;
/**
 * @author lyh
 * @Description 个人中心设置
 * @date 2015年8月4日
 */
@Service
public class PerUserSettingBiz {
	@Resource
	private PerUserConnectSrv perUserConnectSrv;
	@Resource
	private CookieExtractHelper cookieExtractHelper;
	@Resource
	private  PerUserSrv perUserSrv;
	@Resource
	private   PerSettingSrv perSettingSrv;
	@Resource
	private   ComInfoSrv comInfoSrv;
    @Resource
    private ValidationHelper validationHelper;
    @Resource
    private PassAccountHelper passAccountHelper;
	/** 
	* @Description: 获取所有 的第三方连接信息
	* @param perUserId 用户id
	* @throws 
	*/
	public BizRetBo<Map<String, PerUserConnect>> connect(Integer perUserId) {
		BizRetBo<Map<String, PerUserConnect>> bizRetBo = new BizRetBo<Map<String, PerUserConnect>>(BizRetCode.SUCCESS);
		List<PerUserConnect> perUserConnectList = perUserConnectSrv.listConnect(perUserId);
		bizRetBo.setRef("per/connect");
		Map<String, PerUserConnect> perMap = Maps.newHashMap();
		for (PerUserConnect perUserConnect : perUserConnectList) {
			perMap.put(perUserConnect.getType(), perUserConnect);

		}
		bizRetBo.setItem(perMap);
		return bizRetBo;
	}

	
	/** 
	* @Description: 删除 第三方的绑定信息
	* @param perUserId 用户id
	* @param @param id  第三方id
	* @throws 
	*/
	public BizRetBo<String> unbind(Integer perUserId, Integer id) {
		BizRetBo<String> bizRetBo = new BizRetBo<>(BizRetCode.SUCCESS);
		bizRetBo.setRef("redirect:/per/connect");
		perUserConnectSrv.removeConnect(perUserId, id);
		return bizRetBo;
	}

	/**
	 * 账号管理下的隐私设置页面获取数据
	 * 从per_user表中获取public_settings简历保密/filter_com_id屏蔽企业/res_recommend简历推荐
	 * @param model
	 * @param request
	 * @return
	 */
	public BizRetBo<Map<String,Object>> secret(Integer perUserId){
		PerUser perUser = perUserSrv.get(perUserId);
		String filterComId = perUser.getFilterComId();
		if(StringUtils.isBlank(filterComId)){
			filterComId = "[]";
		}
		
		BizRetBo<Map<String, Object>> bizRetBo = new BizRetBo<Map<String, Object>>(BizRetCode.SUCCESS);
		Map  perSettingMap=Maps.newHashMap();
		perSettingMap.put("publicSettings", perUser.getPublicSettings());
		perSettingMap.put("filterComId", XssFilterStringUtils.cancelXSSFilter(filterComId));
		perSettingMap.put("resRecommend", perUser.getResRecommend());
		bizRetBo.setItem(perSettingMap);
		bizRetBo.setRef("/per/setting/secret");
		return bizRetBo;
	}
	
	/**
	 * 简历推荐【0不同意，1同意】
	 * @param request
	 * @param reRecommandStr 简历推荐0或1
	 * @return
	 */
	public BizRetBo<String> recommend(Integer perId,String reRecommandStr){
		PerUser perUser = perUserSrv.get(perId);
		perUser.setResRecommend(NumberUtils.toInt(reRecommandStr,perUser.getResRecommend()));
		perUserSrv.update(perUser);
		BizRetBo<String> bizRetBo=new BizRetBo<String>(BizRetCode.SUCCESS);
	    bizRetBo.setRef("blank");
	    return bizRetBo;
	}
	
	/**
	 * 取消屏蔽企业[单个取消]
	 * @param perId 用户id
	 *  @param comIdStr 企业id字符串
	 * @return
	 */

	public BizRetBo<String> filterDel(Integer perId,String comIdStr){
		perSettingSrv.cancelFilterCom(perId, NumberUtils.toInt(comIdStr));
		BizRetBo<String> bizRetBo=new BizRetBo<String>(BizRetCode.SUCCESS);
		bizRetBo.setRef("blank");
		return bizRetBo;
	}
	
	/**
	 * 新增屏蔽企业
	 * @param perId 用户id
	 * @param filterComId 屏蔽企业id
	 * @return
	 */
	public  BizRetBo<String>  filterAdd(Integer perId,String filterComId){
		perSettingSrv.addFilterCom(perId, filterComId);
		BizRetBo<String> bizRetBo=new BizRetBo<String>(BizRetCode.SUCCESS);
		bizRetBo.setRef("blank");
		return bizRetBo;
	}
	
	/**
	 * 通过关键字查询企业
	 * @param model
	 * @param request
	 * @return
	 */
	public BizRetBo<Map<String,Object>> filterSearch(String comKey){
		//通过关键字来查询企业
		List<ComInfo> comBaseList = comInfoSrv.findComInfoByComKey(comKey);
		Integer sum = 0;
		if(comBaseList != null){
			sum = comBaseList.size();
		}else{
			comBaseList = new ArrayList<ComInfo>();
		}
		List<FilterComIdBo> comList = perSettingSrv.toFilterComIdVo(comBaseList);
		BizRetBo<Map<String,Object>> bizRetBo=new BizRetBo<Map<String,Object>>(BizRetCode.SUCCESS);
		Map<String,Object> filtComs=Maps.newHashMap();
		filtComs.put("sum", sum);
		filtComs.put("comList", comList);
		bizRetBo.setItem(filtComs);
		bizRetBo.setRef("blank");
		return bizRetBo;
	}
	
	/**
	 * 修改密码
	 * @param old_password 旧密码
	 * @param new_password 新密码
	 * @param newg_password 再次输入新密码
	 * @param perId 用户id
	 * @param captcha 验证码
	 * @return
	 */
	public BizRetBo<String> new_passordUpdate(Integer perId,String old_password,String new_passord,String newg_passord,String captcha){
		PerUser perUser = perUserSrv.get(perId);
		BizRetBo<String> bizRetBo=new BizRetBo<>();
		if(old_password != null){
			if(new_passord == null || !new_passord.equals(newg_passord)){
				bizRetBo.setRef("/per/setting/pwd");
			   bizRetBo.setRetCode(BizRetCode.ERROR_ENTERED_PASSWORD_DIFFER);
				return bizRetBo;
			}
			String pwd = perUser.getPassword();
			if(!MD5Builder.md5(perUser.getAccount(),old_password).equals(pwd)){
			    bizRetBo.setRetCode(BizRetCode.ERROR_CURRENT_PASSWORD);
			    bizRetBo.setRef("/per/setting/pwd");
				return bizRetBo;
			};
		}
		if (!validationHelper.checkCaptchaCode(captcha)) {
			  bizRetBo.setRetCode(BizRetCode.CAPTCHACODE_NOT_RIGHT);
			  bizRetBo.setRef("/per/setting/pwd");
			  return bizRetBo;
		}
		perUser.setPassword(MD5Builder.md5(perUser.getAccount(),new_passord));
		perUserSrv.update(perUser);
		bizRetBo.setRef("/per/setting/pwd");
		bizRetBo.setRetCode(BizRetCode.SUCCESS_MODIFY_PASSOWRD);
		//同步修改智通通行证的密码
		if(null != perUser.getAccountId() && perUser.getAccountId() > 0) {
			passAccountHelper.updateChitonePass(perUser,new_passord);
		}
		return bizRetBo;
	}
	
	/**
	 * 访问我的订阅页面
	 * @param model
	 * @param perId 用户id
	 * @return
	 */
	public BizRetBo<Map<String,Integer>> subscribe(Integer perId){
		List<PerSubscription> list = perSettingSrv.findByUserId(perId);
		BizRetBo<Map<String,Integer>> bizRetBo=new BizRetBo<Map<String,Integer>>(BizRetCode.SUCCESS);
		Map<String,Integer> map=Maps.newHashMap();
		for (PerSubscription p : list) {
			map.put(p.getSubType(),  p.getSubscribeStatus());
		}
		bizRetBo.setRef("/per/setting/subscribe");
		return bizRetBo;
	}
	
	/**
	 * 更新我的邮件订阅
	 * @param PerSubscribeEmailFormBo
	 * @param perId 
	 * @return
	 */
	public BizRetBo<String> subscribeEmail(PerSubscribeEmailFormBo vo,  Integer perId){
		perSettingSrv.updateEmailSubsciption(vo, perId);
		BizRetBo<String> bizRetBo=new BizRetBo<>();
		bizRetBo.setRef("blank");
		bizRetBo.setRetCode(BizRetCode.SUCCESS);
		return bizRetBo;
	}
	/**
	 * 更新我的短信订阅
	 * @param request
	 * @return
	 */
	public BizRetBo<String> subscribeMobile(PerSubscribeMobileFormBo vo, Integer perId){
		perSettingSrv.updateMobileSubsciption(vo, perId);
		BizRetBo<String> bizRetBo=new BizRetBo<>();
		bizRetBo.setRef("blank");
		bizRetBo.setRetCode(BizRetCode.SUCCESS);
		return bizRetBo;
	}
}
