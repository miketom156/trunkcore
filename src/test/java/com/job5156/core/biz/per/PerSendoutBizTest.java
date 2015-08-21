/**
 * 
 */
package com.job5156.core.biz.per;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.job5156.core.TestBase;
import com.job5156.core.bo.BizRetBo;
import com.job5156.core.bo.form.per.PerSendoutFormBo;

/**
 * @author lyh
 * @Description
 * @date 2015年8月12日
 */
public class PerSendoutBizTest extends TestBase {

	@Resource
	private PerSendoutBiz perSendoutBiz;

	@Test
	public void testList() {
	      BizRetBo<Map<String,Object>> bizRetBo=perSendoutBiz.list(this.DEFAULT_PID, null);
	      //返回正确的外发记录的条数
	      Assert.assertEquals("个人外发记录", 10,((List) bizRetBo.getItem().get("outgoResumeList")).size());
	}
	@Test
	public void testSave()
	{
		PerSendoutFormBo perSendoutFormBo=new PerSendoutFormBo();
		perSendoutFormBo.setComName("test");
		perSendoutFormBo.setEmail("790260645@qq.com");
		perSendoutFormBo.setEmailFormat(0);
		perSendoutFormBo.setPosName("test");
		perSendoutFormBo.setResumeId(0);
		perSendoutFormBo.setUseLetter(false);
		perSendoutFormBo.setValidCode("2535");
		BizRetBo<Map<String,Object>> bizRetBo=null;
		//测试点：查询当天的外发简历次数
		bizRetBo=perSendoutBiz.save(perSendoutFormBo, null, this.DEFAULT_PID);
		//Assert.assertEquals("测试外发 简历保存成功！",BizRetCode.SUCCESS, bizRetBo.getRetCode());
		Assert.assertEquals("测试外发 简历失败！","天最多能发送10份简历", bizRetBo.getItem().get("error"));
		//简历已设置为保密状态
		bizRetBo=perSendoutBiz.save(perSendoutFormBo, null, this.DEFAULT_PID);
		//默认简历信息
		bizRetBo=perSendoutBiz.save(perSendoutFormBo, null, this.DEFAULT_PID);
		// 校验简历的完整度
		bizRetBo=perSendoutBiz.save(perSendoutFormBo, null, this.DEFAULT_PID);
		//个人是否在企业黑名单中
	}

}
