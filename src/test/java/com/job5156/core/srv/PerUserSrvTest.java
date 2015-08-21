package com.job5156.core.srv;

import com.job5156.core.TestBase;
import com.job5156.core.bo.redis.PerUserLoginRedisBo;
import com.job5156.core.common.Constants;
import com.job5156.core.eao.per.PerLoginHistoryEao;
import com.job5156.core.srv.per.PerUserSrv;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p></p>
 * Date:2015/6/5 17:23
 *
 * @author pzm
 * @version 1.0
 */
public class PerUserSrvTest extends TestBase {
    @Resource
    private PerUserSrv perUserSrv;
    @Resource
    private RedisSrv redisSrv;
    @Resource
    private PerLoginHistoryEao perLoginHistoryEao;

    @Test
    public void testCheckPerEmailExist() {
        //邮件已被用
        Assert.assertTrue("emailUse", perUserSrv.checkPerEmailExist("cyc_2010"));
        //邮件未使用
        Assert.assertFalse("emailNotUse", perUserSrv.checkPerEmailExist("foo@qq.com"));
    }

    @Test
    public void testCheckPerMobileExist() {
        //手机号已被用
        Assert.assertTrue("mobileUse", perUserSrv.checkPerMobileExist("13790373210"));
        //手机号未使用
        Assert.assertFalse("mobileNotUse", perUserSrv.checkPerMobileExist("1111111111111"));
    }

    @Test
    public void testLogin() {
        Integer pid = DEFAULT_PID;
        String userName = "陈义春", ip = "192.168.1.1";

        String cookie = perUserSrv.login(pid, ip);
        //测试点：登录成功
        Assert.assertNotNull("loginSuc", cookie);
        //测试点：redis缓存中信息是否正确
        PerUserLoginRedisBo perUserLoginRedisBo = redisSrv.readRedisVoFromHash(Constants.REDIS_KEY_PER_LOGIN_INFO_PREFIX + cookie, new PerUserLoginRedisBo());
        Assert.assertEquals("userName", userName, perUserLoginRedisBo.getLoginName());
        Assert.assertEquals("ip", ip, perUserLoginRedisBo.getIp());
        Assert.assertEquals("id", pid, new Integer(perUserLoginRedisBo.getId()));
        //测试点：登录历史记录是否插入成功
        Assert.assertEquals("loginHistory", pid, perLoginHistoryEao.listAll().get(0).getPerUserId());

        //测试点：登录失败，账号不存在
        String cookieN = perUserSrv.login(0, ip);
        Assert.assertNull("loginFail", cookieN);
    }
}
