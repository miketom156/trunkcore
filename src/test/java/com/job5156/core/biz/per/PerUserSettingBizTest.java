/**
 * 
 */
package com.job5156.core.biz.per;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Test;

import com.job5156.core.TestBase;
import com.job5156.core.bo.BizRetBo;
import com.job5156.core.bo.BizRetCode;
import com.job5156.webapp.model.per.PerUserConnect;
import com.job5156.webapp.model.sys.SysOperateLog;

/**
 * @author lyh
 * @Description
 * @date 2015年8月4日
 */
public class PerUserSettingBizTest extends TestBase {
	@Resource
	private PerUserSettingBiz perUserSettingBiz;

	@Test
	public void TestConnect() {
		BizRetBo<Map<String, PerUserConnect>> bizRetBo = perUserSettingBiz.connect(this.DEFAULT_PID);
		// 测试点：个人用户的绑定的第三方的信息
		for(String s:bizRetBo.getItem().keySet()){
			PerUserConnect perUserConnect=bizRetBo.getItem().get(s);
			System.out.println(perUserConnect.getId());
		}
		Assert.assertEquals("绑定的信息数", 3, bizRetBo.getItem().size());

	}

	@Test
	public void testUnbind() {
		// 测试不绑定
		BizRetBo<String> bizRetBo = perUserSettingBiz.unbind(this.DEFAULT_PID, 1);
		Assert.assertEquals("删除绑定账号成功！", BizRetCode.SUCCESS, bizRetBo.getRetCode());
	}

	@Test
	public void TestSerct() {
		BizRetBo<Map<String, Object>> bizRetBo = perUserSettingBiz.secret(this.DEFAULT_PID);
		Map<String,Object> map=bizRetBo.getItem();
		Assert.assertEquals("测试用户公开状态", 0,map.get("publicSettings"));
	}

	@Test
	public void TestRecommand() {
		BizRetBo<String> bizRetBo = perUserSettingBiz.recommend(this.DEFAULT_PID, "0");
		Assert.assertEquals("测试用户修改公开状态", BizRetCode.SUCCESS,bizRetBo.getRetCode());
	}
	
}
