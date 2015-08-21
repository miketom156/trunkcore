/**
 * 
 */
package com.job5156.core.biz.per;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.job5156.core.TestBase;
import com.job5156.core.bo.BizRetBo;
import com.job5156.core.bo.BizRetCode;
import com.job5156.core.bo.form.per.PerSearcherBo;
import com.job5156.core.bo.form.searcher.SearchPosFormBo;
import com.job5156.webapp.model.per.PerSearcher;
import com.job5156.core.srv.PerSearcherSrvTest;

/**
 * @author lyh
 * @Description
 * @date 2015年7月1日
 */
public class PerSeracherBizTest extends TestBase {
	@Resource
	private PerSearcherBiz perSearcherBiz;
	Integer Error_SearchId = 68;
	Integer Error_UserId = 255;
	@Test
	public void testGetAllSearchersByPerUserId() {
		BizRetBo<Map<String, String>> bizRetBo = perSearcherBiz.getAllSearchersByPerUserId(this.DEFAULT_PID);
		// 用户名搜索内容
		Assert.assertNotEquals("搜索器内容", "操作失败", bizRetBo.getItem().get("searcherListJson"));
		// Assert.assertEquals("匹配用户邮件信息", PerSearcherSrvTest.FAIT_EMAILE, bizRetBo.getItem().get("userEmail"));
	}

	@Test
	public void testModifyPerSearcher() {
		PerSearcherBo perSearcherBo = new PerSearcherBo();
		perSearcherBo.setId(69);
		perSearcherBo.setName("东莞－中国");
		perSearcherBo.setSubscribe(1);
		perSearcherBo.setEmail("miketom137@163.com");
		perSearcherBo.setUrl("http://www.baidu.com");
		SearchPosFormBo searchPosFormBo = new SearchPosFormBo();
		searchPosFormBo.setDegreeFrom(6);
		perSearcherBo.setSearchPosFormBo(searchPosFormBo);
		BizRetBo<Map<String, Object>> bizRetBo = null;
		// BizRetBo<Map<String, Object>> bizRetBo = perSearcherBiz.deletePerSearcher(69, this.DEFAULT_PID);
		// 测试点：当ID不为0的时候，首先判断这个搜索器ID是否属于当前用户的，否则报错
		if (perSearcherBo.getId() > 0) {
			// 测试点：用户id与搜索器用户不匹配
			bizRetBo = perSearcherBiz.modifyPerSearcher(perSearcherBo, Error_UserId);
			Assert.assertEquals("搜索器出错", BizRetCode.PER_SEARCHER_UPDATE_ERROR, bizRetBo.getRetCode());
			// 更新操作
			bizRetBo = perSearcherBiz.modifyPerSearcher(perSearcherBo, this.DEFAULT_PID);
			Assert.assertEquals("更新成功", BizRetCode.SUCCESS, bizRetBo.getRetCode());
		} else {
			// 增加，不能大于10个
			perSearcherBo.setId(0);
			bizRetBo = perSearcherBiz.modifyPerSearcher(perSearcherBo, this.DEFAULT_PID);
			Assert.assertEquals("增加失败", "操作失败", bizRetBo.getRetCode());
			Assert.assertEquals("错误信息", "最多只能设置10个搜索器，请<a href='http://s.job5156.com/per/searcher'>设置搜索器</a>", bizRetBo.getMsg());
           //进行删除再增加
			bizRetBo = perSearcherBiz.deletePerSearcher(69, this.DEFAULT_PID);
			bizRetBo = perSearcherBiz.modifyPerSearcher(perSearcherBo, this.DEFAULT_PID);
			Assert.assertEquals("增加成功", "操作成功", bizRetBo.getRetCode());
			
		}

	}

	@Test
	public void testUpdateSubscribe() {
		PerSearcherBo perSearcherBo = new PerSearcherBo();
		perSearcherBo.setId(69);
		perSearcherBo.setName("东莞－中国");
		perSearcherBo.setSubscribe(1);
		perSearcherBo.setEmail("miketom137@163.com");
		perSearcherBo.setUrl("http://www.baidu.com");
		SearchPosFormBo searchPosFormBo = new SearchPosFormBo();
		searchPosFormBo.setDegreeFrom(3);
		perSearcherBo.setSearchPosFormBo(searchPosFormBo);
		// 用户不匹配时
		BizRetBo<PerSearcher> bizRetBo = perSearcherBiz.updateSubscribe(perSearcherBo, 2255);
		Assert.assertEquals("订阅失败", BizRetCode.PER_SEARCHER_UPDATE_ERROR, bizRetBo.getRetCode());
		// 用户匹配时更新成功
		bizRetBo = perSearcherBiz.updateSubscribe(perSearcherBo, this.DEFAULT_PID);
		Assert.assertEquals("订阅成功", BizRetCode.SUCCESS, bizRetBo.getRetCode());
		// Assert.assertEquals("订阅成功", "操作成功", bizRetBo.getRetCode().getMsg());
	}

	@Test
	public void testDeletePerSearcher() {
		// 搜索器id出错，或者用户不匹配
		BizRetBo<Map<String, Object>> bizRetBo = perSearcherBiz.deletePerSearcher(Error_SearchId, this.DEFAULT_PID);
		Assert.assertEquals("搜索器更新出错！", BizRetCode.PER_SEARCHER_UPDATE_ERROR, bizRetBo.getRetCode());
//		 测试点用户不匹配
		bizRetBo = perSearcherBiz.deletePerSearcher(69, Error_UserId);
		Assert.assertEquals("搜索器更新出错！", BizRetCode.PER_SEARCHER_UPDATE_ERROR, bizRetBo.getRetCode());
		// 正确的匹配之后进行删除操作
		bizRetBo = perSearcherBiz.deletePerSearcher(69, this.DEFAULT_PID);
		Assert.assertEquals("用户删除之后：",BizRetCode.SUCCESS,bizRetBo.getRetCode());
		Assert.assertEquals("用户删除之后的搜索", 9, bizRetBo.getItem().get("countSearcherNum"));
	}

}
