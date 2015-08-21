package com.job5156.core.biz.redis;

import com.job5156.core.bo.redis.PerUserLoginRedisBo;
import com.job5156.core.common.Constants;
import com.job5156.core.eao.RedisEao;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

@Component
public class CookieExtractRedisBiz {
    @Resource
    private RedisEao redisEao;
    /**
     * 从Cookie中获取个人用户Id。
     * 如果Cookie值为空、Cookie值对应的用户登录信息不存在，返回0。
     * @param perCookie
     * @return
     */
    public Integer getPerUserIdFromCookie(String perCookie) {
        if(StringUtils.isBlank(perCookie)) {
            return 0;
        } else {
            PerUserLoginRedisBo bo = getPerUserLoginBo(perCookie);
            if(bo == null || StringUtils.isBlank(bo.getId())) {
                return 0;
            } else {
                return NumberUtils.toInt(bo.getId(), 0);
            }
        }
    }

    /**
     * 从cookie中获取用户登录对象
     * @param cookie
     * @return
     */
    public PerUserLoginRedisBo getPerUserLoginBo(String cookie) {
        return redisEao.readRedisVoFromHash(Constants.REDIS_KEY_PER_LOGIN_INFO_PREFIX  + cookie, new PerUserLoginRedisBo());
    }

    /**
     * 从cookie中获取用户名
     * @param perCookie
     * @return
     */
    public String getPerUserLoginNameFromCookie(String perCookie) {
        if(StringUtils.isBlank(perCookie)) {
            return "";
        } else {
            PerUserLoginRedisBo bo = getPerUserLoginBo(perCookie);
            if(bo == null || StringUtils.isBlank(bo.getLoginName())) {
                return "";
            } else {
                return StringUtils.trimToEmpty(bo.getLoginName());
            }
        }
    }


}
