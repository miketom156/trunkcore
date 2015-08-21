package uml.per.login;

import java.util.List;

/**
 * <p></p>
 * Date:2015/6/9 08:40
 *
 * @author pzm
 * @version 1.0
 */
public interface RedisEao {
    public Boolean isKeyExist(final String key);
    public Long del(final String key);
    public void writeToString(final String key, final String value);
    public Boolean setKeyExpire(final String key, final long seconds);
    public <T> Boolean writeRedisVoToHash(final String key, final T vo);
    public void zAdd(final String key, final List<String> list);

}
