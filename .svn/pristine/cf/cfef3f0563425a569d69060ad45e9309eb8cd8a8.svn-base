package com.job5156.core.biz;

import com.job5156.core.TestBase;
import com.job5156.core.bo.BizRetBo;
import com.job5156.core.bo.BizRetCode;
import com.job5156.core.bo.LoginInfoBo;
import com.job5156.core.bo.form.per.*;
import com.job5156.core.common.CommonEnum;
import com.job5156.core.common.util.KeyMaskUtil;
import com.job5156.core.common.util.MD5Builder;
import com.job5156.core.eao.com.ComReceiveEao;
import com.job5156.core.eao.per.PerLoginHistoryEao;
import com.job5156.core.eao.per.PerPosApplyEao;
import com.job5156.core.eao.per.PerUserEao;
import com.job5156.webapp.model.com.ComReceive;
import com.job5156.webapp.model.count.PvPersonRegister;
import com.job5156.webapp.model.per.PerUser;
import com.job5156.core.srv.com.ComPositionSrv;
import com.job5156.core.srv.count.PVPersonRegisterSrv;
import com.job5156.core.srv.per.PerApplySrv;
import com.job5156.core.srv.per.PerUserSrv;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p></p>
 * Date:2015/5/22 16:25
 *
 * @author pzm
 * @version 1.0
 */
public class PerUserBizTest extends TestBase {
    @Resource
    private PerPosApplyEao perPosApplyEao;
    @Resource
    private PerApplySrv perApplySrv;
    @Resource
    private ComReceiveEao comReceiveEao;
    @Resource
    private ComPositionSrv comPositionSrv;
    @Resource
    private PerUserBiz perUserBiz;
    @Resource
    private PerUserEao perUserEao;
    @Resource
    private PerLoginHistoryEao perLoginHistoryEao;
    @Resource
    private PerUserSrv perUserSrv;
    @Resource
    private PVPersonRegisterSrv pvPersonRegisterSrv;

    @Test
    public void testRegister() {
        PerRegisterFormBo vo = getPerRegisterFormVo();
        BizRetBo<PerUser> ret = perUserBiz.registerWeb(vo, false);
        if (ret.getRetCode() == BizRetCode.SUCCESS) {
            PerUser perUserCreate = ret.getItem();
            //测试点：返回的用户实体是否能成功登录
            BizRetBo<LoginInfoBo> loginRet = perUserBiz.login(perUserCreate.getId(), vo.getIp());
            if (loginRet.getRetCode() == BizRetCode.SUCCESS) {
                Assert.assertNotNull("login:cookie", loginRet.getItem().getCookie());
                Assert.assertEquals("login:email", vo.getUsername(), loginRet.getItem().getEmail());
            } else {
                Assert.fail("login fail:" + ret.getRetCodeVal());
            }

            //测试点：用户是否插入成功
            sessionFactory.getCurrentSession().clear();
            checkRegPerUser(perUserSrv.get(perUserCreate.getId()), vo);

            //测试点：PV_Person_Register注册记录是否正否正确插入
            PvPersonRegister personRegister = pvPersonRegisterSrv.getByUserId(perUserCreate.getId());
            checkPVPersonRegister(personRegister, vo, perUserCreate.getId());
        } else {
            //测试点：注册返回结果是否为成功
            Assert.fail("register fail:" + ret.getRetCodeVal());
        }

        //测试点：用户已存在
        ret = perUserBiz.registerWeb(vo, false);
        Assert.assertEquals(BizRetCode.EMAIL_EXIST, ret.getRetCode());
    }

    @Test
    public void testCheckRegister() {
        //测试点：两次密码不一样
        PerRegisterFormBo vo = getPerRegisterFormVo();
        vo.setPassword1(vo.getPassword() + "xxx");
        BizRetBo ret = perUserBiz.registerWeb(vo, false);
        Assert.assertEquals(BizRetCode.PASSWORD_NOT_SAME, ret.getRetCode());

        //测试点：两次密码不一样
        vo = getPerRegisterFormVo();
        vo.setCaptcha("xxx");
        ret = perUserBiz.registerWeb(vo, false);
        Assert.assertEquals(BizRetCode.CAPTCHACODE_NOT_RIGHT, ret.getRetCode());
    }

    @Test
    public void testAppRegister() {
        AppRegisterFormBo vo = getAppRegisterFormVo();
        BizRetBo<PerUser> ret = perUserBiz.registerApp(vo, false);
        if (ret.getRetCode() == BizRetCode.SUCCESS) {
            PerUser perUserCreate = ret.getItem();
            //测试点：返回的用户实体是否能成功登录
            BizRetBo<LoginInfoBo> loginRet = perUserBiz.login(perUserCreate.getId(), vo.getIp());
            if (loginRet.getRetCode() == BizRetCode.SUCCESS) {
                Assert.assertNotNull("login:cookie", loginRet.getItem().getCookie());
                Assert.assertEquals("login:email", vo.getUsername(), loginRet.getItem().getEmail());
            } else {
                Assert.fail("login fail:" + ret.getRetCodeVal());
            }

            //测试点：用户是否插入成功
            clearSessionCache();
            checkRegPerUser(perUserSrv.get(perUserCreate.getId()), vo);
        } else {
            //测试点：注册返回结果是否为成功
            Assert.fail("register fail:" + ret.getRetCodeVal());
        }

        //测试点：用户已存在
        ret = perUserBiz.registerApp(vo, false);
        Assert.assertEquals(BizRetCode.EMAIL_EXIST, ret.getRetCode());
    }

    @Test
    public void testCheckAppRegister() {
        //测试点：用户名空
        AppRegisterFormBo vo = getAppRegisterFormVo();
        vo.setUsername("");
        BizRetBo<PerUser> ret = perUserBiz.registerApp(vo, false);
        Assert.assertEquals(BizRetCode.USER_NAME_EMPTY, ret.getRetCode());

        //测试点：密码空
        vo = getAppRegisterFormVo();
        vo.setPassword("");
        ret = perUserBiz.registerApp(vo, false);
        Assert.assertEquals(BizRetCode.PASSWORD_EMPTY, ret.getRetCode());

        //测试点：手机空
        vo = getAppRegisterFormVo();
        vo.setMobile("");
        ret = perUserBiz.registerApp(vo, false);
        Assert.assertEquals(BizRetCode.MOBILE_EMPTY, ret.getRetCode());

        //测试点：密码长度太短
        vo = getAppRegisterFormVo();
        vo.setPassword("123");
        ret = perUserBiz.registerApp(vo, false);
        Assert.assertEquals(BizRetCode.PASSWORD_LENGTH_NOT_VALID, ret.getRetCode());

        //测试点：密码长度太长
        vo = getAppRegisterFormVo();
        vo.setPassword("123456789012345678901");
        ret = perUserBiz.registerApp(vo, false);
        Assert.assertEquals(BizRetCode.PASSWORD_LENGTH_NOT_VALID, ret.getRetCode());

        //测试点：EMAIL地址格式不正确
        vo = getAppRegisterFormVo();
        vo.setUsername("pengzeming");
        ret = perUserBiz.registerApp(vo, false);
        Assert.assertEquals(BizRetCode.EMAIL_NOT_VALID, ret.getRetCode());

        //测试点：手机号码不正确
        vo = getAppRegisterFormVo();
        vo.setMobile("1234567891234");
        ret = perUserBiz.registerApp(vo, false);
        Assert.assertEquals(BizRetCode.MOBILE_NOT_VALID, ret.getRetCode());

        vo = getAppRegisterFormVo();
        BizRetBo<PerUser> retSuc = perUserBiz.registerApp(vo, false);
        PerUser perUserSuc = retSuc.getItem();
        perUserSuc.setMobileActivation(1);
        perUserSuc.setMobile(vo.getMobile());
        perUserSrv.save(perUserSuc);
        AppRegisterFormBo sameMobileVo = getAppRegisterFormVo();
        sameMobileVo.setUsername("pengzeming@111.com");
        sameMobileVo.setMobile(vo.getMobile());
        ret = perUserBiz.registerApp(sameMobileVo, false);
        Assert.assertEquals(BizRetCode.MOBILE_EXIST, ret.getRetCode());
    }

    @Test
    public void testTouchRegister() {
        TouchRegisterFormBo vo = getTouchRegisterFormVo();
        BizRetBo<PerUser> ret = perUserBiz.registerTouch(vo, false);
        if (ret.getRetCode() == BizRetCode.SUCCESS) {
            PerUser perUserCreate = ret.getItem();
            //测试点：返回的用户实体是否能成功登录
            BizRetBo<LoginInfoBo> loginRet = perUserBiz.login(perUserCreate.getId(), vo.getIp());
            if (loginRet.getRetCode() == BizRetCode.SUCCESS) {
                Assert.assertNotNull("login:cookie", loginRet.getItem().getCookie());
                Assert.assertEquals("login:email", vo.getEmail(), loginRet.getItem().getEmail());
            } else {
                Assert.fail("login fail:" + ret.getRetCodeVal());
            }

            //测试点：用户是否插入成功
            clearSessionCache();
            checkRegPerUser(perUserSrv.get(perUserCreate.getId()), vo);

            //测试点：PV_Person_Register注册记录是否正否正确插入
            PvPersonRegister personRegister = pvPersonRegisterSrv.getByUserId(perUserCreate.getId());
            checkPVPersonRegister(personRegister, vo, perUserCreate.getId());
        } else {
            //测试点：注册返回结果是否为成功
            Assert.fail("register fail:" + ret.getRetCodeVal());
        }

        //测试点：用户已存在
        ret = perUserBiz.registerTouch(vo, false);
        Assert.assertEquals(BizRetCode.EMAIL_EXIST, ret.getRetCode());
    }

    @Test
    public void testCheckTouchRegister() {
        //todo
    }

    @Test
    public void testWechatRegister() {
        WechatRegisterFormBo vo = getWechatRegisterFormVo();
        BizRetBo<PerUser> ret = perUserBiz.registerWechat(vo, false);
        if (ret.getRetCode() == BizRetCode.SUCCESS) {
            PerUser perUserCreate = ret.getItem();
            //测试点：返回的用户实体是否能成功登录
            BizRetBo<LoginInfoBo> loginRet = perUserBiz.login(perUserCreate.getId(), vo.getIp());
            if (loginRet.getRetCode() == BizRetCode.SUCCESS) {
                Assert.assertNotNull("login:cookie", loginRet.getItem().getCookie());
                Assert.assertEquals("login:email", vo.getAccount(), loginRet.getItem().getEmail());
            } else {
                Assert.fail("login fail:" + ret.getRetCodeVal());
            }

            //测试点：用户是否插入成功
            clearSessionCache();
            checkRegPerUser(perUserSrv.get(perUserCreate.getId()), vo);
        } else {
            //测试点：注册返回结果是否为成功
            Assert.fail("register fail:" + ret.getRetCodeVal());
        }

        //测试点：用户已存在
        ret = perUserBiz.registerWechat(vo, false);
        Assert.assertEquals(BizRetCode.ACCOUNT_EXIST_WECHAT, ret.getRetCode());
    }

    @Test
    public void testCheckWechatRegister() {
        //todo
    }

    private PerRegisterFormBo getPerRegisterFormVo() {
        PerRegisterFormBo vo = new PerRegisterFormBo();
        vo.setTargetUrl("http://dg.job5156.com/?et=9d021d12");
        vo.setCaptcha(perUserBiz.gentCaptchaText());
        vo.setPassword("123456");
        vo.setPassword1("123456");
        vo.setUsername("pengzeming@196.com");
        vo.setComeFrom(CommonEnum.ComeFrom.WEB);
        vo.setFdomain("www.haosou.com");
        vo.setFromUrl("http://www.haosou.com/s?ie=utf-8&src=hao_search&shb=1&q=%E4%B8%9C%E8%8E%9E%E6%8B%9B%E8%81%98%E7%BD%91");
        String ip = "120.198.232.22";
        vo.setIp(ip);
        vo.setSemSpreadParam(null);
        vo.setServerName("m.job5156.com");
        vo.setSiteId(33);
        vo.setSpreadParam("9d021d12");
        return vo;
    }

    private AppRegisterFormBo getAppRegisterFormVo() {
        AppRegisterFormBo vo = new AppRegisterFormBo();
        vo.setUsername("pengzeming@126.com");
        vo.setPassword("123456");
        vo.setIp("120.198.232.22");
        vo.setComeFrom(CommonEnum.ComeFrom.MOBILE_REG);
        vo.setMobile("13312188888");
        vo.setType(1);
        return vo;
    }

    private TouchRegisterFormBo getTouchRegisterFormVo() {
        TouchRegisterFormBo vo = new TouchRegisterFormBo();
        vo.setMobile("13312188888");
        vo.setCaptcha(perUserBiz.gentCaptchaText());
        vo.setPassword("123456");
        vo.setPassword1("123456");
        vo.setEmail("pengzeming@126.com");
        vo.setTargetUrl("http://m.job5156.com/");
        vo.setComeFrom(CommonEnum.ComeFrom.TOUCH_REG);
        vo.setFdomain("m.sogou.com");
        vo.setFromUrl("http://m.sogou.com/web/searchList.jsp?pid=sogou-clse-2996962656838a97&e=1427&g_f=123&keyword=%E6%99%BA%E9%80%9A");
        vo.setIp("14.17.37.161");
        vo.setSemSpreadParam(null);
        vo.setServerName("m.job5156.com");
        vo.setSiteId(33);
        vo.setSpreadParam("a4028e12");
        return vo;
    }

    private WechatRegisterFormBo getWechatRegisterFormVo() {
        WechatRegisterFormBo vo = new WechatRegisterFormBo();
        vo.setMobile("13312188888");
        vo.setPassword("123456");
        vo.setAccount("pengzeming@126.com");
        vo.setUsername("pengzeming");
        vo.setCardnum("441900198501010017");
        vo.setTargetUrl("http://m.job5156.com/");
        vo.setComeFrom(CommonEnum.ComeFrom.WECHAT_REG);
        vo.setIp("14.17.37.161");
        return vo;
    }

    private void checkPVPersonRegister(PvPersonRegister personRegister, RegisterBo vo, Integer userId) {
        Assert.assertNotNull("id", personRegister.getId());
        Assert.assertEquals("userId", userId, personRegister.getUserId());
        Assert.assertEquals("userIp", vo.getIp(), personRegister.getUserIp());
        Assert.assertEquals("fromDomain", vo.getFdomain(), personRegister.getFromDomain());
        Assert.assertEquals("siteId", vo.getSiteId(), personRegister.getSiteId());
        Assert.assertEquals("fromUrl", vo.getFromUrl(), personRegister.getFromUrl());
        Assert.assertEquals("targetUrl", vo.getTargetUrl(), personRegister.getTargetUrl());
        Assert.assertEquals("register", vo.getSpreadParam(), personRegister.getRegister());
        Assert.assertEquals("semRegister", vo.getSemSpreadParam(), personRegister.getSemRegister());
        Assert.assertEquals("accountFrom", vo.getComeFrom().getValue(), (int) personRegister.getAccountFrom());
    }

    private void checkRegPerUser(PerUser user, PerRegisterFormBo vo) {
        Assert.assertEquals("account", vo.getUsername(), user.getAccount());
        Assert.assertEquals("email", vo.getUsername(), user.getEmail());
        Assert.assertEquals("accountType", new Integer(0), user.getAccountType());
        Assert.assertEquals("password", MD5Builder.md5(vo.getUsername(), vo.getPassword()), user.getPassword());
        Assert.assertEquals("loginCount", new Integer(1), user.getLoginCount());
        Assert.assertEquals("ip", vo.getIp(), user.getIp());
        Assert.assertEquals("accountFrom", new Integer(vo.getComeFrom().getValue()), user.getAccountFrom());
    }

    private void checkRegPerUser(PerUser user, TouchRegisterFormBo vo) {
        Assert.assertEquals("account", vo.getEmail(), user.getAccount());
        Assert.assertEquals("email", vo.getEmail(), user.getEmail());
        Assert.assertEquals("mobile", vo.getMobile(), user.getMobile());
        Assert.assertEquals("accountType", new Integer(0), user.getAccountType());
        Assert.assertEquals("password", MD5Builder.md5(vo.getEmail(), vo.getPassword()), user.getPassword());
        Assert.assertEquals("loginCount", new Integer(1), user.getLoginCount());
        Assert.assertEquals("ip", vo.getIp(), user.getIp());
        Assert.assertEquals("accountFrom", new Integer(vo.getComeFrom().getValue()), user.getAccountFrom());
    }

    private void checkRegPerUser(PerUser user, AppRegisterFormBo vo) {
        Assert.assertEquals("account", vo.getUsername(), user.getAccount());
        Assert.assertEquals("email", vo.getUsername(), user.getEmail());
        Assert.assertEquals("accountType", new Integer(0), user.getAccountType());
        Assert.assertEquals("password", MD5Builder.md5(vo.getUsername(), vo.getPassword()), user.getPassword());
        Assert.assertEquals("loginCount", new Integer(1), user.getLoginCount());
        Assert.assertEquals("ip", vo.getIp(), user.getIp());
        Assert.assertEquals("accountFrom", new Integer(vo.getComeFrom().getValue()), user.getAccountFrom());
    }

    private void checkRegPerUser(PerUser user, WechatRegisterFormBo vo) {
        Assert.assertEquals("account", vo.getAccount(), user.getAccount());
        Assert.assertEquals("email", vo.getAccount(), user.getEmail());
        Assert.assertEquals("userName", vo.getUsername(), user.getUserName());
        Assert.assertEquals("accountType", new Integer(0), user.getAccountType());
        Assert.assertEquals("password", MD5Builder.md5(vo.getAccount(), vo.getPassword()), user.getPassword());
        Assert.assertEquals("loginCount", new Integer(1), user.getLoginCount());
        Assert.assertEquals("ip", vo.getIp(), user.getIp());
        Assert.assertEquals("accountFrom", new Integer(vo.getComeFrom().getValue()), user.getAccountFrom());
        Assert.assertEquals("mobile", vo.getMobile(), user.getMobile());
        Assert.assertEquals("cardNum", vo.getCardnum(), user.getCardNum());
    }

    @Test
    public void testLogin() {
        Integer perId = DEFAULT_PID;
        String ip="181.1.22.15";
        PerUser perUser = perUserEao.get(perId);
        BizRetBo<LoginInfoBo> ret = perUserBiz.login(perUser.getId(),ip);
        if (ret.getRetCode() == BizRetCode.SUCCESS) {
            //测试点：登录信息是否正确
            LoginInfoBo loginInfoBo = ret.getItem();
            Assert.assertNotNull("cookie", loginInfoBo.getCookie());
            Assert.assertEquals("email", perUser.getEmail(), loginInfoBo.getEmail());
            Assert.assertEquals("userName", perUser.getUserName(), loginInfoBo.getName());
            Assert.assertEquals("id",perUser.getId(), loginInfoBo.getId());

            //测试点：登录历史记录
            Assert.assertEquals("perLoginHistory:count", 1, perLoginHistoryEao.<Number>aggregate("select count(*) FROM PerLoginHistory WHERE perUserId=?", perId).intValue());
        } else {
            //测试点：应聘是否成功
            Assert.fail("login fail:" + ret.getRetCodeVal());
        }
    }

    @Test
    public void testLoginPassword() {
        String ip="181.1.22.15",account="cyc_2010",password="123456";
        PerUser perUser = perUserEao.findByAccount(account).get(0);
        BizRetBo<LoginInfoBo> ret = perUserBiz.login(account,password,ip);
        if (ret.getRetCode() == BizRetCode.SUCCESS) {
            //测试点：登录信息是否正确
            LoginInfoBo loginInfoBo = ret.getItem();
            Assert.assertNotNull("cookie", loginInfoBo.getCookie());
            Assert.assertEquals("email", perUser.getEmail(), loginInfoBo.getEmail());
            Assert.assertEquals("userName", perUser.getUserName(), loginInfoBo.getName());
            Assert.assertEquals("id",perUser.getId(), loginInfoBo.getId());

            //测试点：登录历史记录
            Assert.assertEquals("perLoginHistory:count", 1, perLoginHistoryEao.<Number>aggregate("select count(*) FROM PerLoginHistory WHERE perUserId=?", perUser.getId()).intValue());
        } else {
            //测试点：应聘是否成功
            Assert.fail("login fail:" + ret.getRetCodeVal());
        }

        //测试点：密码错误
        password +="x";
        ret = perUserBiz.login(account,password,ip);
        Assert.assertEquals("password", BizRetCode.LOGIN_USER_OR_PASSWORD_FAIL,ret.getRetCode());
    }

    @Test
    public void testApply() {
        Integer posId = 7798797, perId = DEFAULT_PID, resumeId = 4220268;
        Integer comId = comPositionSrv.get(posId).getComId();
        BizRetBo ret = perUserBiz.apply(KeyMaskUtil.posIdToNo(posId, (short) 0), perId, resumeId, false);
        if (ret.getRetCode() == BizRetCode.SUCCESS) {
            //测试点：是否有应聘记录
            Assert.assertNotNull("perPosApply", perApplySrv.findByPosIdAndPerUserId(posId, perId));
            //测试点：是否有企业收到简历记录
            ComReceive comReceive = comReceiveEao.findByComIdAndResumeId(comId, resumeId);
            Assert.assertNotNull("comReceive", comReceive);
            //测试点：应聘次数为1
            Assert.assertEquals("comReceive:applyNumer", new Integer(1), comReceive.getApplyNumer());
        } else {
            //测试点：应聘是否成功
            Assert.fail("apply fail:" + ret.getRetCodeVal());
        }

        //测试点：二次应聘（需要客户确认）
        BizRetBo ret2 = perUserBiz.apply(KeyMaskUtil.posIdToNo(posId, (short) 0), perId, resumeId, false);
        Assert.assertEquals("hasComfirmSecondApply", BizRetCode.SECOND_APPLY, ret2.getRetCode());

        //测试点：二次应聘（客户已确认）
        perUserBiz.apply(KeyMaskUtil.posIdToNo(posId, (short) 0), perId, resumeId, true);
        Assert.assertEquals("secondApply:perPosApply:count", 2, perPosApplyEao.<Number>aggregate("select count(*) FROM PerPosApply WHERE posId=? and perUserId = ?", posId, perId).intValue());
        clearSessionCache();
        ComReceive comReceive = comReceiveEao.findByComIdAndResumeId(comId, resumeId);
        Assert.assertEquals("secondApply:comReceive:applyNumer", 2, comReceive.getApplyNumer().intValue());
    }

    @Test
    public void testLoginInfoTouch(){
        Integer perId = DEFAULT_PID;
        BizRetBo<Map> bizRetBo = perUserBiz.loginInfoTouch(perId);
        //测试点：登录信息是否正确
        Assert.assertEquals("score",80,bizRetBo.getItem().get("score"));
        Assert.assertEquals("userName","陈义春",bizRetBo.getItem().get("userName"));
        Assert.assertEquals("account","cyc_2010",bizRetBo.getItem().get("account"));
        Assert.assertEquals("email","59281665@qq.com",bizRetBo.getItem().get("email"));
        Assert.assertEquals("photo","/content/per_photo/2015-4/bb9e320bef7c1342f3e3725c9f07b9e6.jpg",bizRetBo.getItem().get("photo"));
    }

}
