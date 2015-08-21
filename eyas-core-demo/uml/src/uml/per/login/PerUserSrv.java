package uml.per.login;

/**
 * <p></p>
 * Date:2015/6/9 8:25
 *
 * @author hjs
 * @version 1.0
 */
public interface PerUserSrv {
    public PerUserEao perUserEao = null;
    public RedisEao redisEao = null;
    public TempHelper tempHelper = null;
    public PerResumeEao perResumeEao = null;
    public PerLoginHistoryEao perLoginHistoryEao = null;

    /**
     * 单点登录
     *
     * @param pid 登录用户id
     * @param ip  登录ip
     * @return
     */
    public String login(Integer pid, String ip);

    /**
     * 根据账号密码找到用户
     *
     * @param account  用户账号
     * @param password 用户密码
     * @return
     */
    public PerUser find(String account, String password);

    /**
     * 根据账号找到用户
     *
     * @param account 用户账号
     * @return
     */
    public PerUser find(String account);

    /**
     * 根据id获取个人信息
     * @param pid
     * @return
     */
    public PerUser get(Integer pid);
}
