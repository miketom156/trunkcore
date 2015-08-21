package com.job5156.core.biz.redis;

import com.job5156.core.TestBase;
import com.job5156.core.biz.PerUserBiz;
import com.job5156.core.bo.BizRetBo;
import com.job5156.core.bo.LoginInfoBo;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p></p>
 * Date:2015/6/19 10:36
 *
 * @author pzm
 * @version 1.0
 */
public class CookieExtractRedisBizTest extends TestBase{
    @Resource
    private CookieExtractRedisBiz cookieExtractRedisBiz;
    @Resource
    private PerUserBiz perUserBiz;

    @Test
    public void testGetPerUserIdFromCookie(){
        Integer perId = DEFAULT_PID;
        String ip="111.111.111.111";
        BizRetBo<LoginInfoBo> bizRetBo = perUserBiz.login(perId, ip);
        Assert.assertEquals("perId", perId, cookieExtractRedisBiz.getPerUserIdFromCookie(bizRetBo.getItem().getCookie()));
    }

    @Test
    public void testGetPerUserLoginNameFromCookie(){
        Integer perId = DEFAULT_PID;
        String ip="111.111.111.111", userName="陈义春";
        BizRetBo<LoginInfoBo> bizRetBo = perUserBiz.login(perId, ip);
        Assert.assertEquals("userName", userName, cookieExtractRedisBiz.getPerUserLoginNameFromCookie(bizRetBo.getItem().getCookie()));
    }
}
