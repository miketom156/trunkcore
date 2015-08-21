package uml.per.login;


/**
 * <p></p>
 * Date:2015/6/9 8:25
 *
 * @author hjs
 * @version 1.0
 */
public interface PerUserBiz {
    public PerUserSrv perUserSrv = null;
    public RedisSrv redisSrv = null;
    public Producer captchaProducer = null;
    /**
     * 单点登录
     * @param pid 登录用户id
     * @param ip 登录ip
     * @return
     */
    public BizRetBo<LoginInfoBo> login(Integer pid, String ip);
    /**
     * 账号密码登录
     * @param account 账号
     * @param password 密码
     * @param ip 登录ip
     * @return
     */
    public BizRetBo<LoginInfoBo> login(String account, String password, String ip);

    /**
     * 生成登录校验码
     *
     * @return
     */
    public String gentCaptchaText();
}
