package com.job5156.core.eao.per;

import com.job5156.core.TestBase;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p></p>
 * Date:2015/6/5 16:49
 *
 * @author pzm
 * @version 1.0
 */
public class PerUserEaoTest extends TestBase {
    @Resource
    private PerUserEao perUserEao;
    @Test
    public void testFind(){
        //测试点：校验账号密码成功
        Assert.assertEquals("cyc_2010",4220268,perUserEao.find("cyc_2010", "123456").getId().longValue());
        //测试点：校验账号密码成功
        Assert.assertEquals("961580081@qq.com", 4231485, perUserEao.find("961580081@qq.com", "123456").getId().longValue());
        //测试点：密码错误
        Assert.assertNull("cyc_2010:passwordfail",perUserEao.find("961580081@qq.com", "xxx"));
        //测试点：无此用户
        Assert.assertNull("foo:notexist", perUserEao.find("foo", "bar"));
    }

    @Test
    public void testFindEmailUsage(){
        //测试点：email没有被别人占用
        Assert.assertTrue("emailNoOtherUse",perUserEao.findEmailUsage("961580081@qq.com",4231485)==0);
        //测试点：email已被用
        Assert.assertTrue("emailUse",perUserEao.findEmailUsage("961580081@qq.com",0)>0);
        //测试点：email未使用
        Assert.assertTrue("emailNotUse",perUserEao.findEmailUsage("foo@qq.com",0)==0);
    }

    @Test
    public void testFindMobileUsage(){
        //测试点：手机号没有被别人占用
        Assert.assertTrue("mobileNoOtherUse",perUserEao.findMobileUsage("18028921558",4231485)==0);
        //测试点：手机号已被用
        Assert.assertTrue("mobileUse",perUserEao.findMobileUsage("18028921558",0)>0);
        //测试点：手机号未使用
        Assert.assertTrue("mobileNotUse",perUserEao.findMobileUsage("11111111111",0)==0);
    }

    @Test
    public void testFindByAccount(){
        //测试点：根据账号找实体
        Assert.assertEquals("account",4220268,perUserEao.findByAccount("cyc_2010").get(0).getId().longValue());
        //测试点：无此账号
        Assert.assertTrue("notexist",perUserEao.findByAccount("foo").isEmpty());
    }
}
