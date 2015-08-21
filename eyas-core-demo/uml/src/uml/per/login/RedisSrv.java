package uml.per.login;


import uml.per.reg.RedisEao;

/**
 * <p></p>
 * Date:2015/6/9 08:57
 *
 * @author hjs
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
     * 将内容写入Redis，保存类型为String
     * @param key Key
     * @param value 内容
     */
    public void writeToString(String key, String value);

    /**
     * 设置Redis key的生存期
     * @param key Key
     * @param seconds 存活期（秒）
     * @return 成功设置返回true，否则返回false
     */
    public Boolean setKeyExpire(String key, long seconds);
}
