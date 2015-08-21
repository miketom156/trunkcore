package uml.per.reg;

/**
 * <p>用户业务逻辑类</p>
 * Date:2015/6/4 15:37
 *
 * @author pzm
 * @version 1.0
 */
public interface PerUserBiz {
    public PerUserSrv perUserSrv = null;
    public PVPersonRegisterSrv pvPersonRegister = null;
    public RedisSrv redisSrv = null;
    public MailSrv mailSrv = null;

    /**
     * web注册
     *
     * @param bo
     * @param sendMail
     * @return
     */
    public BizRetBo<PerUser> registerWeb(RegisterBo bo, boolean sendMail);

    /**
     * 触屏版注册
     *
     * @param bo
     * @param sendMail
     * @return
     */
    public BizRetBo<PerUser> registerTouch(TouchRegisterFormBo bo, boolean sendMail);

    /**
     * 微信注册
     *
     * @param bo
     * @param sendMail
     * @return
     */
    public BizRetBo<PerUser> registerWechat(WechatRegisterFormBo bo, boolean sendMail);

    /**
     * 手机app注册
     * @param bo
     * @param sendMail
     * @return
     */
    public BizRetBo<PerUser> registerApp(AppRegisterFormBo bo, boolean sendMail);

    /**
     * 插入注册统计
     * @param registerBo
     * @param perUser
     */
    public void addCountRegisterer(RegisterBo registerBo, PerUser perUser);

    public void sendRegSuccessMail(PerUser perUser, String serverName);
}
