/**
 * 
 */
package com.job5156.core.eao.per;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.job5156.core.TestBase;
import com.job5156.webapp.model.per.PerSearcher;

/**
 * @author lyh
 * @Description
 * @date 2015年7月1日
 */
public class PerSearcherEaoTest extends TestBase {

	@Resource
	private PerSearcherEao perSearcherEao;

	@Test
	public void findByPerUser() {
		//测试点：用户统计用户搜索器
		List<PerSearcher> list = perSearcherEao.findByPerUser(this.DEFAULT_PID);

		Assert.assertEquals("搜索器统计", 10, list.size());
		//测试点：用户统计用户搜索器出错
	     list = perSearcherEao.findByPerUser(54);
		Assert.assertNotEquals("搜索器统计", 5, list.size());

        System.out.println(list.size());
		Assert.assertNotNull(list.size());
	}

}
