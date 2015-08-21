package uml.per.reg;


/**
 * <p></p>
 * Date:2015/6/5 10:56
 *
 * @author pzm
 * @version 1.0
 */
public interface RedisSrv {
    public RedisEao redisEao = null;
    /**
     * 登陆验证码校验
     * @param captchaCode
     * @return
     */
    public boolean checkCaptchaCode(String captchaCode);
    /**
     * 生成邮件验证码，此验证码自带一定的时效性
     * 对应id如果在一定时间内生成过验证码的，不重复生成第二次，而是直接返回空字符串
     * @param mail
     * @param id
     * @return
     */
    public String buildMailValidationCode(String mail, Integer id);
}
