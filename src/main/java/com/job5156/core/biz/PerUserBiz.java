package com.job5156.core.biz;

import com.google.code.kaptcha.Producer;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.job5156.core.bo.BizRetBo;
import com.job5156.core.common.*;
import com.job5156.core.common.helper.PerResumeCheckHelper;
import com.job5156.core.common.helper.PerResumeScoreHelper;
import com.job5156.core.common.helper.PosFilterHelper;
import com.job5156.core.common.helper.UploadHelper;
import com.job5156.core.common.option.OptionMap;
import com.job5156.core.common.util.CheckUtil;
import com.job5156.core.common.util.MD5Builder;
import com.job5156.webapp.model.com.ComPosition;
import com.job5156.webapp.model.com.ComReceive;
import com.job5156.webapp.model.com.talebase.TalebaseComTestAuto;
import com.job5156.webapp.model.count.PvPersonRegister;
import com.job5156.webapp.model.per.PerResume;
import com.job5156.webapp.model.per.PerUser;
import com.job5156.core.srv.MailSrv;
import com.job5156.core.srv.RedisSrv;
import com.job5156.core.srv.TalebaseComTestSrv;
import com.job5156.core.srv.com.ComBlacklistSrv;
import com.job5156.core.srv.com.ComMessageSrv;
import com.job5156.core.srv.com.ComPositionSrv;
import com.job5156.core.srv.count.PVPersonRegisterSrv;
import com.job5156.core.srv.per.PerApplySrv;
import com.job5156.core.srv.per.PerResumeSrv;
import com.job5156.core.srv.per.PerUserSrv;
import com.job5156.core.bo.BizRetCode;
import com.job5156.core.bo.LoginInfoBo;
import com.job5156.core.bo.form.per.*;
import com.job5156.core.bo.mail.ApplyJobMailBo;
import com.job5156.core.bo.mail.PerRegisterMailBo;
import com.job5156.core.bo.mapping.ResumePreviewBo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * Date:2015/5/15 16:20
 *
 * @author pzm
 * @version 1.0
 */
@Component
public class PerUserBiz {
    @Resource
    protected PerApplySrv perApplySrv;
    @Resource
    protected ComBlacklistSrv comBlacklistSrv;
    @Resource
    protected MailSrv mailSrv;
    @Resource
    protected TalebaseComTestSrv talebaseComTestSrv;
    @Resource
    protected ComPositionSrv comPositionSrv;
    @Resource
    protected ComMessageSrv comMessageSrv;
    @Resource
    protected PerResumeSrv perResumeSrv;
    @Resource
    protected PerUserSrv perUserSrv;
    @Resource
    protected RedisSrv redisSrv;
    @Resource
    protected PVPersonRegisterSrv pvPersonRegisterSrv;

    @Resource
    protected PerResumeCheckHelper perResumeCheckHelper;
    @Resource
    protected PosFilterHelper posFilterHelper;
    @Resource
    private Producer captchaProducer;
    @Resource
    private PerResumeScoreHelper perResumeScoreHelper;
    @Resource
    private UploadHelper uploadHelper;

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

    /**
     * 个人应聘职位
     *
     * @param posNo       职位编号（搜索库id)
     * @param perId       个人ID
     * @param resumeId    简历ID
     * @param hasComfirmSecondApply 用户确认是否进行第2次应聘
     * @return
     */
    public BizRetBo apply(Integer posNo, Integer perId, Integer resumeId, boolean hasComfirmSecondApply) {

        //常规的id合理性校验-----start-----
        if (perId == null || perId <= 0) {
            return new BizRetBo(BizRetCode.UNLOGIN_APPLY);
        }

        if (resumeId == null || resumeId <= 0) {
            return new BizRetBo(BizRetCode.RESUME_NO_SELECT);
        }

        PerResume perResume = perResumeSrv.get(resumeId);
        if (perResume == null) {
            return new BizRetBo(BizRetCode.RESUME_NOT_EXIST);
        }

        if (!perId.equals(perResume.getAccountId())) {
            return new BizRetBo(BizRetCode.RESUME_NOT_OWNER);
        }

        if (perResume.getPerUser() == null) {
            return new BizRetBo(BizRetCode.USER_NOT_EXIST);
        }
        PerUser perUser = perResume.getPerUser();

        ComPosition comPosition = comPositionSrv.findByPosNo(posNo);
        if (comPosition == null) {
            return new BizRetBo(BizRetCode.POSITION_NOT_EXIST);
        }

        if (comPosition.getComInfo() == null) {
            return new BizRetBo(BizRetCode.COM_NOT_EXIST);
        }
        //常规的id合理性校验-----end-----

        //当简历不通过审核，也是不允许进行投递的
        //todo 0要改为使用常量
        if (perResume.getPass() <= 0) {
            return new BizRetBo(BizRetCode.RESUME_UNAUDIT);
        }

        //职位状态
        if (comPosition.getPosStatus() <= CommonEnum.PositionStatus.WAIT_FOR_AUDIT.getValue()) {
            return new BizRetBo(BizRetCode.POSITION_STOP);
        }

        //职位投递过滤要求
        String filterResultStr = posFilterHelper.checkPosFilter(comPosition, perResume);
        //todo 增加提示具体哪里不符合要求
        if (StringUtils.isNotBlank(filterResultStr)) {
            return new BizRetBo(BizRetCode.RESUME_UNMATCH);
        }

        //企业黑名单
        if (comBlacklistSrv.checkExistBlacklist(comPosition.getComInfo().getId(), perId)) {
            return new BizRetBo(BizRetCode.RESUME_BLACK);
        }

        //应聘已超过500次
        if (perApplySrv.getApplyCount(perId) >= 500) {
            return new BizRetBo(BizRetCode.APPLY_LIMIT_500_ONEDAY);
        }

        //校验正式简历的完整度
        String msg = "";
        if (perResume.getResumeType() == CommonEnum.ResumeType.COMMON.getValue() || perResume.getResumeType() == CommonEnum.ResumeType.CHANGSHA.getValue()) {
            msg = perResumeCheckHelper.resumeCompleteMsg(perResume); //正式简历应聘
            if (StringUtils.isNotBlank(msg)) {
                return new BizRetBo(BizRetCode.RESUME_INCOMPLETE, "msg", msg);
            }
        }

        //名片应聘检查
        if (perResume.getResumeType() == CommonEnum.ResumeType.CARD.getValue()) {
            //职位是否接受名片应聘
            //TODO:暂时取消 默认接受名片的职位
            // if(!PosNameFilterUtil.isMatchCardJob(comPosition.getPosName())){ //默认接受名片的职位,即使企业没有手动设置接受名片应聘
            if (CommonEnum.ResumeType.CARD.getValue() != comPosition.getAcceptResumeType()
                    && CommonEnum.ResumeType.CARD.getValue() == perResume.getResumeType()) {
                return new BizRetBo(BizRetCode.NOT_ACCPET_CARD);
            }
            // }

            //校验名片的完整度
            msg = perResumeCheckHelper.cardCompleteMsg(perResume);
            if (StringUtils.isNotBlank(msg)) {
                return new BizRetBo(BizRetCode.CARD_NOT_PERFECT);
            }

            //手机是否激活
            if (StringUtils.isBlank(perUser.getMobile())
                    || perUser.getMobileActivation() == null
                    || perUser.getMobileActivation() == 0) {
                return new BizRetBo(BizRetCode.NOT_VERIFY_MOBILE);
            }
        }

        //3个月内应聘同一家公司最多只能应聘该公司的3个职位
        if (perApplySrv.getCandidatePosCount(comPosition.getComId(), comPosition.getId()) >= 3) {
            return new BizRetBo(BizRetCode.LIMIT_BY_POSTION);

        }

        Integer per30DaysApply = perApplySrv.count30DaysApplyTimes(comPosition.getId(), perId);
        if (per30DaysApply == 1 && !hasComfirmSecondApply) {//确认同一职位是否应聘第2次
            return new BizRetBo(BizRetCode.SECOND_APPLY);
        } else if (per30DaysApply >= 2) {//同一职位30天内不能应聘超过2次
            return new BizRetBo(BizRetCode.WARN_TWICE);
        }

        //所有校验都通过后，写入2张表，分别是个人应聘记录表和企业收到简历表
        if (perApplySrv.countTodayApplyTimes(comPosition.getComId(), resumeId, comPosition.getId()) < 2) { //防止重复提交
            perApplySrv.applySave(perResume, comPosition);
            if (CommonEnum.ResumeType.COMMON.getValue() == perResume.getResumeType()) { //TODO:暂时正式简历才做如下操作，名片取消写入?why
                //写入应聘总数到redis
                perApplySrv.writeApplyCountToRedis(perResume.getAccountId());

                //写入站内消息/交互记录
                String content = "应聘【" + comPosition.getPosName() + "】";
                String title = perUser.getUserName() + content;
                comMessageSrv.perSendMessageToCom(perResumeSrv.get(resumeId), comPosition, title, content,
                        MsgConstants.PER_DYNAMIC_CATEGORY_INTERACTION, MsgConstants.PER_DYNAMIC_TYPE_PER_APPLY_COM_POS);
            }
        } else {
            return new BizRetBo(BizRetCode.WARN_DUPLICATE);
        }

        if (comPosition.getComContactBase().getRejectEmail() != null && comPosition.getComContactBase().getRejectEmail() == 1) {
            //不发送邮件
        } else {
            //给企业发送应聘邮件
            String posName = comPosition.getPosName();
            String comName = comPosition.getComInfo().getComName();
            ResumePreviewBo vo = perResumeSrv.getPreviewCnVo(perResume.getId());
            vo.setPosName(posName);
            String resumeContentCn = perResumeSrv.getCnMailContent(vo);
            String resumeContentEn = "";

            if (perResume.getResumeId() != null && perResume.getResumeId() > 0) {
                PerResume resumeEn = perResumeSrv.get(perResume.getResumeId());
                //个人的英文简历是否公开
                if (resumeEn != null && resumeEn.getDisplayEnResume().equals(1)) {
                    resumeContentEn = perResumeSrv.getEnMailContent(perResumeSrv.getPreviewEnVo(perResume.getId()));
                }
            }

            //String subject = posName + " - 收到" +perUser.getUserName()+"的简历,请惠阅";//个人应聘职位的邮件主题
            String subject = perUser.getUserName() + "应聘【" + posName + "】";//个人应聘职位的邮件主题

            List<String> emailList = new Gson().fromJson(comPosition.getComContactBase().getEmail(), new TypeToken<List<String>>() {
            }.getType());
            List<String> fixEmailList = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(emailList)) {
                for (String email : emailList) {
                    if (email.contains("；")) {
                        Collections.addAll(fixEmailList, email.split("；"));
                    } else {
                        fixEmailList.add(email);
                    }
                }
            }
            ApplyJobMailBo applyJobMailBo = new ApplyJobMailBo(
                    new ApplyJobMailBo.PlaceHolderVo(
                            comName, posName, perUser.getUserName(), resumeContentCn, resumeContentEn
                    ),
                    fixEmailList.toArray(new String[CollectionUtils.size(fixEmailList)]),
                    subject
            );
            applyJobMailBo.setEmailType(comPosition.getComContactBase().getEmailFormat());
            //发送邮件
            //TODO:警告，线上的发邮件，记得要用队列方式方式，以避免卡死
            //  mailService.send(applyJobMailVo);
            if (CommonEnum.ResumeType.COMMON.getValue() == perResume.getResumeType()) { //TODO:暂时:正式简历才发邮件，名片不发邮件
                mailSrv.insertEmailQueue(applyJobMailBo, MailConstants.MAIL_TASK_PER_APPLY_JOB);
                //发送测评邀请邮件
                List<TalebaseComTestAuto> list = talebaseComTestSrv.findByComTestAutoList(comPosition.getComId(), comPosition.getId());
                if (CollectionUtils.isNotEmpty(list)) {
                    TalebaseComTestAuto cta = (TalebaseComTestAuto) list.get(0);
                    ComReceive comReceive = new ComReceive();
                    comReceive.setComId(comPosition.getComId());
                    comReceive.setPosId(comPosition.getId());
                    comReceive.setPerUserId(perId);
                    comReceive.setPosName(comPosition.getPosName());
                    comReceive.setUserName(perUser.getUserName());
                    comReceive.setEmail(perUser.getEmail());
                    comReceive.setResumeId(resumeId);
                    if (null != cta.getProductId() && cta.getProductId() > 0) {
                        talebaseComTestSrv.comTestInvite(comReceive, comPosition, cta.getProductId(), cta.getCompleteCycle());
                    }
                }
            }

        }
        return new BizRetBo(BizRetCode.SUCCESS);
    }

    /**
     * 个人登录信息触屏版
     * @param perId
     * @return
     */
    public BizRetBo<Map> loginInfoTouch(Integer perId){
        BizRetBo<Map> retVo = new BizRetBo<>();
        PerUser user = perUserSrv.get(perId);
        Map<String, Object> item = Maps.newHashMap();
        if (user != null) {
            PerResume resume = user.getDefaultPerResume();
            if(resume == null){
                item.put("score", 0);
            }else{
                item.put("score", perResumeScoreHelper.getScore(resume).intValue());
            }
        }
        if (user != null) {
            item.put("publicSettings", user.getPublicSettings());
            item.put("freDate", user.getFreDate());
            item.put("userName", user.getUserName());
            item.put("account", user.getAccount());
            item.put("email", user.getEmail());
            item.put("photo", uploadHelper.getUrl(Constants.UPLOAD_TYPE_PER_PHOTO, user.getFilePath(), user.getFileName()));
        }
        if (item.get("photo") == null || StringUtils.isBlank(item.get("photo").toString())) {
            item.put("photo", "/static/img/photoBlank.gif");
        }
        retVo.setItem(item);
        retVo.setRetCode(BizRetCode.SUCCESS);
        return retVo;
    }
}
