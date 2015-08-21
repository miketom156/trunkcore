/**
 * 
 */
package com.job5156.core.biz.per;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.WebUtils;

import com.google.common.collect.Maps;
import com.job5156.core.bo.BizRetBo;
import com.job5156.core.common.Constants;
import com.job5156.core.common.helper.CookieExtractHelper;
import com.job5156.core.srv.per.PerUserConnectSrv;
import com.job5156.webapp.model.per.PerUserConnect;

/**
 * @author lyh
 * @Description
 * @date 2015年8月4日
 */
public class PerUserConnectBiz {

	@Resource
	private PerUserConnectSrv perUserConnectSrv;

	@Resource
	private CookieExtractHelper cookieExtractHelper;

	/**
	 * @Description: 个人设置中心首页面
	 * @param request
	 * @throws
	 */
	public BizRetBo<Map<String, PerUserConnect>> index(HttpServletRequest request) {
		Integer perId = cookieExtractHelper.getPerUserIdFromCookie(WebUtils.getCookie(request, Constants.COOKIE_KEY_PER));
		BizRetBo<Map<String, PerUserConnect>> bizRetBo = new BizRetBo<Map<String, PerUserConnect>>();
		List<PerUserConnect> perUserConnectList = perUserConnectSrv.listConnect(perId);
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
	* @param @param request
	* @param @param id  账号id
	* @throws 
	*/
	public BizRetBo<String> unbind(HttpServletRequest request, Integer id) {
		Integer perId = cookieExtractHelper.getPerUserIdFromCookie(WebUtils.getCookie(request, Constants.COOKIE_KEY_PER));
		BizRetBo<String> bizRetBo = new BizRetBo<>();
		bizRetBo.setRef("redirect:/per/connect");
		perUserConnectSrv.removeConnect(perId, id);
		return bizRetBo;
	}

}
