/**
 * 
 */
package com.job5156.core.common.helper.per;

import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.job5156.core.common.Constants;
import com.job5156.core.common.util.HttpAsyncUtils;
import com.job5156.webapp.model.ChitonePassRegister;
import com.job5156.webapp.model.per.PerUser;

/**
 * @author lyh
 * @Description 
 * @date 2015年8月4日
 * 
 */
@Component
public class PassAccountHelper {
	/**
	 * 修改智通通行证密码
	 * 
	 * @param perUser
	 * @param password 原密码
	 */
	public String updateChitonePass(PerUser perUser,String password) {
		String url = Constants.CHITONE_PASS_MODIFY_PASS_UPDATE_POST;
		String resultStr = HttpAsyncUtils.postObject(url, changePerUserToCPR(perUser,password),null,"utf-8");
		ChitonePassRegister.Result resultObj = new Gson().fromJson(resultStr,
				ChitonePassRegister.Result.class);
		return resultObj != null ? resultObj.getMsg() : "保存通行证密码失败";
	}
	
	/**
	 * 将PerUser转为ChitonePassRegister
	 * 
	 * @param perUser
	 * @param password 原密码
	 * @return ChitonePassRegister 智通通行证注册用户
	 * 
	 */
	public ChitonePassRegister changePerUserToCPR(PerUser perUser,String password) {
		ChitonePassRegister obj = new ChitonePassRegister();
		if (null != perUser.getAccountId()) {
			obj.setAccount_id(perUser.getAccountId());
		}
		obj.setUser_account(URLEncoder.encode(perUser.getAccount()));
		//使用原始密码
		obj.setUser_password(URLEncoder.encode(password));
		return obj;
	}
}
