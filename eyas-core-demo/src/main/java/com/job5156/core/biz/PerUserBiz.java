package com.job5156.core.biz;

import com.google.code.kaptcha.Producer;
import com.job5156.core.bo.BizRetBo;
import com.job5156.core.bo.BizRetCode;
import com.job5156.core.bo.LoginInfoBo;
import com.job5156.core.bo.form.per.*;
import com.job5156.core.bo.mail.PerRegisterMailBo;
import com.job5156.core.common.CommonEnum;
import com.job5156.core.common.Constants;
import com.job5156.core.common.TypeException;
import com.job5156.core.common.option.OptionMap;
import com.job5156.core.common.util.CheckUtil;
import com.job5156.core.common.util.MD5Builder;
import com.job5156.core.entity.count.PvPersonRegister;
import com.job5156.core.entity.per.PerUser;
import com.job5156.core.srv.MailSrv;
import com.job5156.core.srv.RedisSrv;
import com.job5156.core.srv.count.PVPersonRegisterSrv;
import com.job5156.core.srv.per.PerUserSrv;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>用户业务逻辑类</p>
 * Date:2015/5/15 16:20
 *
 * @author pzm
 * @version 1.0
 */
@Component
public class PerUserBiz {
    @Resource
    protected MailSrv mailSrv;
    @Resource
    protected PerUserSrv perUserSrv;
    @Resource
    protected RedisSrv redisSrv;
    @Resource
    protected PVPersonRegisterSrv pvPersonRegisterSrv;
    @Resource
    private Producer captchaProducer;

    /**
     * 个人用户网站注册
     *
     * @param registerBo 注册信息
     * @param sendMail   是否发送邮件
     * @return
     */
    public BizRetBo<PerUser> registerWeb(RegisterBo registerBo, boolean sendMail) {
        if (registerBo instanceof PerRegisterFormBo) {
            PerRegisterFormBo perRegisterFormBo = (PerRegisterFormBo) registerBo;
            if (!StringUtils.equals(perRegisterFormBo.getPassword(), perRegisterFormBo.getPassword1())) {
                return new BizRetBo<>(BizRetCode.PASSWORD_NOT_SAME);
            }
            if (perUserSrv.checkPerEmailExist(perRegisterFormBo.getUsername())) {
                return new BizRetBo<>(BizRetCode.EMAIL_EXIST);
            }
            if (!redisSrv.checkCaptchaCode(perRegisterFormBo.getCaptcha())) {
                return new BizRetBo<>(BizRetCode.CAPTCHACODE_NOT_RIGHT);
            }
            PerUser perUser = new PerUser();
            perUser.setAccount(perRegisterFormBo.getUsername());
            perUser.setEmail(perRegisterFormBo.getUsername());
            perUser.setAccountType(0);
            perUser.setPassword(MD5Builder.md5(perRegisterFormBo.getUsername(), perRegisterFormBo.getPassword()));
            perUser.setCreateDate(DateTime.now().toDate());
            perUser.setLoginCount(0);
            perUser.setLoginDate(DateTime.now().toDate());
            perUser.setFreDate(DateTime.now().toDate());
            perUser.setIp(perRegisterFormBo.getIp());
            perUser.setAccountFrom(perRegisterFormBo.getComeFrom().getValue());
            perUserSrv.save(perUser);

            //写入用户注册统计数据到数据库
            addCountRegisterer(registerBo, perUser);

            //账号创建成功，发送注册成功的激活邮件
            if (sendMail) {
                sendRegSuccessMail(perUser, perRegisterFormBo.getServerName());
            }

            return new BizRetBo<>(BizRetCode.SUCCESS, perUser);
        } else {
            throw new TypeException("registerVo", PerRegisterFormBo.class);
        }
    }

    /**
     * 个人用户app注册
     *
     * @param appRegisterFormBo 注册信息
     * @param sendMail          是否发送邮件
     * @return
     */
    public BizRetBo<PerUser> registerApp(AppRegisterFormBo appRegisterFormBo, boolean sendMail) {
        if (StringUtils.isBlank(appRegisterFormBo.getUsername())) {
            return new BizRetBo<>(BizRetCode.USER_NAME_EMPTY);
        }
        if (StringUtils.isBlank(appRegisterFormBo.getPassword())) {
            return new BizRetBo<>(BizRetCode.PASSWORD_EMPTY);
        }
        if (StringUtils.isBlank(appRegisterFormBo.getMobile())) {
            return new BizRetBo<>(BizRetCode.MOBILE_EMPTY);
        }
        if (StringUtils.length(appRegisterFormBo.getPassword()) < 6 || StringUtils.length(appRegisterFormBo.getPassword()) > 20) {
            return new BizRetBo<>(BizRetCode.PASSWORD_LENGTH_NOT_VALID);
        }
        if (!CheckUtil.checkEmailValid(appRegisterFormBo.getUsername())) {
            return new BizRetBo<>(BizRetCode.EMAIL_NOT_VALID);
        }
        if (!CheckUtil.checkMobileValid(appRegisterFormBo.getMobile())) {
            return new BizRetBo<>(BizRetCode.MOBILE_NOT_VALID);
        }
        if (perUserSrv.checkPerEmailExist(appRegisterFormBo.getUsername())) {
            return new BizRetBo<>(BizRetCode.EMAIL_EXIST);
        }
        if (perUserSrv.checkPerMobileExist(appRegisterFormBo.getMobile(), 0)) {
            return new BizRetBo<>(BizRetCode.MOBILE_EXIST);
        }
        PerUser perUser = new PerUser();
        perUser.setAccount(appRegisterFormBo.getUsername());
        perUser.setEmail(appRegisterFormBo.getUsername());
        perUser.setAccountType(0);
        perUser.setPassword(MD5Builder.md5(appRegisterFormBo.getUsername(), appRegisterFormBo.getPassword()));
        perUser.setCreateDate(DateTime.now().toDate());
        perUser.setLoginCount(0);
        perUser.setLoginDate(DateTime.now().toDate());
        perUser.setFreDate(DateTime.now().toDate());
        perUser.setIp(appRegisterFormBo.getIp());
        perUser.setAccountFrom(appRegisterFormBo.getComeFrom().getValue());
        perUserSrv.save(perUser);

        //账号创建成功，发送注册成功的激活邮件
        if (sendMail) {
            sendRegSuccessMail(perUser, appRegisterFormBo.getServerName());
        }

        return new BizRetBo<>(BizRetCode.SUCCESS, perUser);
    }

    /**
     * 个人用户触屏版注册
     *
     * @param touchRegisterFormBo 注册信息
     * @param sendMail            是否发送邮件
     * @return
     */
    public BizRetBo<PerUser> registerTouch(TouchRegisterFormBo touchRegisterFormBo, boolean sendMail) {
        BizRetBo<PerUser> apiRetVo = new BizRetBo<>();
        if (!StringUtils.equals(touchRegisterFormBo.getPassword(), touchRegisterFormBo.getPassword1())) {
            apiRetVo.setRetCode(BizRetCode.PASSWORD_NOT_SAME);
            return apiRetVo;
        }
        if (perUserSrv.checkPerEmailExist(touchRegisterFormBo.getEmail())) {
            apiRetVo.setRetCode(BizRetCode.EMAIL_EXIST);
            return apiRetVo;
        }
        if (!redisSrv.checkCaptchaCode(touchRegisterFormBo.getCaptcha())) {
            apiRetVo.setRetCode(BizRetCode.CAPTCHACODE_NOT_RIGHT);
            return apiRetVo;
        }
        PerUser perUser = new PerUser();
        perUser.setAccount(touchRegisterFormBo.getEmail());
        perUser.setEmail(touchRegisterFormBo.getEmail());
        perUser.setMobile(touchRegisterFormBo.getMobile());
        perUser.setAccountType(0);
        perUser.setPassword(MD5Builder.md5(touchRegisterFormBo.getEmail(), touchRegisterFormBo.getPassword()));
        perUser.setLoginCount(0);
        perUser.setCreateDate(DateTime.now().toDate());
        perUser.setLoginDate(DateTime.now().toDate());
        perUser.setFreDate(DateTime.now().toDate());
        perUser.setIp(touchRegisterFormBo.getIp());
        perUser.setAccountFrom(touchRegisterFormBo.getComeFrom() == null ? CommonEnum.ComeFrom.TOUCH_REG.getValue() : touchRegisterFormBo.getComeFrom().getValue());//注册的来源
        perUserSrv.save(perUser);

        //写入用户注册统计数据到数据库
        addCountRegisterer(touchRegisterFormBo, perUser);

        //账号创建成功，发送注册成功的激活邮件
        if (sendMail) {
            sendRegSuccessMail(perUser, touchRegisterFormBo.getServerName());
        }
        apiRetVo.setRetCode(BizRetCode.SUCCESS);
        apiRetVo.setItem(perUser);
        return apiRetVo;
    }

    /**
     * 个人用户微信注册
     *
     * @param wechatRegisterFormBo 注册信息
     * @param sendMail             是否发送邮件
     * @return
     */
    public BizRetBo<PerUser> registerWechat(WechatRegisterFormBo wechatRegisterFormBo, boolean sendMail) {
        if (StringUtils.isBlank(wechatRegisterFormBo.getUsername()) || StringUtils.isBlank(wechatRegisterFormBo.getCardnum())
                || !CheckUtil.checkEmailValid(wechatRegisterFormBo.getAccount())
                || !CheckUtil.checkMobileValid(wechatRegisterFormBo.getMobile())
                || StringUtils.length(wechatRegisterFormBo.getPassword()) < 6 || StringUtils.length(wechatRegisterFormBo.getPassword()) > 20
                ) {
            return new BizRetBo<>(BizRetCode.INPUT_INFO_NOT_RIGHT);
        }
        if (perUserSrv.checkPerEmailExist(wechatRegisterFormBo.getAccount())) {
            return new BizRetBo<>(BizRetCode.ACCOUNT_EXIST_WECHAT);
        }
        PerUser perUser = new PerUser();
        perUser.setAccount(wechatRegisterFormBo.getAccount());
        perUser.setEmail(wechatRegisterFormBo.getAccount());
        perUser.setUserName(wechatRegisterFormBo.getUsername());
        perUser.setMobile(wechatRegisterFormBo.getMobile());
        perUser.setCardType(OptionMap.getKeyByValue(OptionMap.OptionType.OPT_PER_CARDTYPE, "身份证"));
        perUser.setCardNum(wechatRegisterFormBo.getCardnum());
        perUser.setAccountType(0);
        perUser.setPassword(MD5Builder.md5(wechatRegisterFormBo.getAccount(), wechatRegisterFormBo.getPassword()));
        perUser.setCreateDate(DateTime.now().toDate());
        perUser.setLoginCount(0);
        perUser.setLoginDate(DateTime.now().toDate());
        perUser.setFreDate(DateTime.now().toDate());
        perUser.setIp(wechatRegisterFormBo.getIp());
        perUser.setAccountFrom(CommonEnum.ComeFrom.WECHAT_REG.getValue());
        perUserSrv.save(perUser);

        //账号创建成功，发送注册成功的激活邮件
        if (sendMail) {
            sendRegSuccessMail(perUser, wechatRegisterFormBo.getServerName());
        }

        return new BizRetBo<>(BizRetCode.SUCCESS, perUser);
    }

    /**
     * 个人用户账号注册，用于第三方账号注册，密码随机生成
     *
     * @param bo 注册信息
     * @return
     */
    public BizRetBo<PerUser> registerThirdBinding(ThirdBindingRegisterFormBo bo) {
        PerUser perUser = new PerUser();
        perUser.setAccount(bo.getEmail());
        perUser.setEmail(bo.getEmail());
        perUser.setAccountType(0);
        perUser.setPassword(MD5Builder.md5(bo.getEmail(), "Job5156" + (int) (Math.random() * 1000)));
        perUser.setCreateDate(DateTime.now().toDate());
        perUser.setLoginCount(0);
        perUser.setLoginDate(DateTime.now().toDate());
        perUser.setFreDate(DateTime.now().toDate());
        perUser.setIp(bo.getIp());
        perUser.setAccountFrom(CommonEnum.ComeFrom.THIRD_BINDING_REG.getValue());
        perUserSrv.save(perUser);

        //写入用户注册统计数据到数据库
        addCountRegisterer(bo, perUser);

        return new BizRetBo<>(BizRetCode.SUCCESS, perUser);
    }

    /**
     * 发送注册成功邮件
     *
     * @param perUser    用户实体
     * @param serverName 注册服务器域名
     */
    private void sendRegSuccessMail(PerUser perUser, String serverName) {
        String validCode = redisSrv.buildMailValidationCode(perUser.getEmail(), perUser.getId());
        mailSrv.send(new PerRegisterMailBo(
                new PerRegisterMailBo.PlaceHolderVo(
                        perUser.getAccount(),
                        perUser.getUserName(),
                        "http://" + serverName + "/per/valid/mail/" + validCode
                ),
                new String[]{perUser.getEmail()}
        ));
    }

    /**
     * 添加注册统计记录
     *
     * @param registerBo 注册信息
     * @param perUser    用户实体
     */
    private void addCountRegisterer(RegisterBo registerBo, PerUser perUser) {
        PvPersonRegister pvPersonRegister = new PvPersonRegister();
        pvPersonRegister.setAccountFrom(registerBo.getComeFrom().getValue());
        pvPersonRegister.setCredate(perUser.getCreateDate());
        pvPersonRegister.setFromDomain(registerBo.getFdomain());
        pvPersonRegister.setFromUrl(registerBo.getFromUrl());
        pvPersonRegister.setRegister(registerBo.getSpreadParam());
        pvPersonRegister.setSemRegister(registerBo.getSemSpreadParam());
        pvPersonRegister.setSiteId(registerBo.getSiteId());
        pvPersonRegister.setTargetUrl(registerBo.getTargetUrl());
        pvPersonRegister.setUserId(perUser.getId());
        pvPersonRegister.setUserIp(registerBo.getIp());
        pvPersonRegisterSrv.save(pvPersonRegister);
    }

    /**
     * 单点登录
     * @param pid 登录用户id
     * @param ip 登录ip
     * @return
     */
    public BizRetBo<LoginInfoBo> login(Integer pid, String ip) {
        String cookie = perUserSrv.login(pid, ip);
        if(cookie==null){
            return new BizRetBo<>(BizRetCode.LOGIN_FAIL);
        }
        PerUser perUser = perUserSrv.get(pid);
        LoginInfoBo loginInfoBo = new LoginInfoBo();
        loginInfoBo.setCookie(cookie);
        loginInfoBo.setEmail(perUser.getEmail());
        loginInfoBo.setId(perUser.getId());
        loginInfoBo.setName(perUser.getUserName());
        return new BizRetBo<>(BizRetCode.SUCCESS, loginInfoBo);
    }

    /**
     * 账号密码登录
     * @param account 账号
     * @param password 密码
     * @param ip 登录ip
     * @return
     */
    public BizRetBo<LoginInfoBo> login(String account, String password, String ip) {
        PerUser perUser = perUserSrv.find(account,password);
        if(perUser==null){
            return new BizRetBo<>(BizRetCode.LOGIN_USER_OR_PASSWORD_FAIL);
        }
        return login(perUser.getId(),ip);
    }

    /**
     * 生成登录校验码
     *
     * @return
     */
    public String gentCaptchaText() {
        String capText = StringUtils.lowerCase(captchaProducer.createText());
        String captchaKey = Constants.REDIS_KEY_CAPTCHA_PREFIX + capText;
        redisSrv.writeToString(captchaKey, capText);
        redisSrv.setKeyExpire(captchaKey, Constants.REDIS_EXPIRE_TIME_CAPTCHA);
        return capText;
    }
}
