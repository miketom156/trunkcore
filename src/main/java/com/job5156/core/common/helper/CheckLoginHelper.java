package com.job5156.core.common.helper;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.job5156.core.bo.redis.ComUserLoginRedisBo;
import com.job5156.core.bo.redis.PerUserLoginRedisBo;
import com.job5156.core.bo.redis.SysUserLoginRedisBo;
import com.job5156.core.common.Constants;
import com.job5156.core.common.util.ValidateUtil;
import com.job5156.core.eao.RedisEao;

@Component
public class CheckLoginHelper {

    @Resource
    private RedisEao redisEao;

    /**
     * 判断个人用户是否已登录
     * @param request
     * @return 1-已登录，-1-未登录
     */
    public int isPerUserLogin(HttpServletRequest request) {
        Cookie perCookie = WebUtils.getCookie(request, Constants.COOKIE_KEY_PER);

        if(perCookie != null && StringUtils.isNotBlank(perCookie.getValue())) {
            PerUserLoginRedisBo vo = redisEao.readRedisVoFromHash(Constants.REDIS_KEY_PER_LOGIN_INFO_PREFIX + perCookie.getValue(), new PerUserLoginRedisBo());

            if(vo != null && ValidateUtil.validate(vo)
//                    && StringUtils.equals(vo.getIp(), IPUtil.getIp(request))
            		) {

//                String checkCookie = redisEao.readFromString(Constants.REDIS_KEY_PER_LOGIN_CHECK_PREFIX  + vo.getId());
//                if(StringUtils.equalsIgnoreCase(perCookie.getValue(), checkCookie)) {
                //每次正常操作延长生存时间
                redisEao.setKeyExpire(Constants.REDIS_KEY_PER_LOGIN_INFO_PREFIX + perCookie.getValue(), Constants.REDIS_EXPIRE_TIME_PER_LOGIN);
//                    redisEao.setKeyExpire(Constants.REDIS_KEY_PER_LOGIN_CHECK_PREFIX + perCookie, Constants.REDIS_EXPIRE_TIME_PER_LOGIN);
                return 1;
//                } else {
//                    response.sendRedirect(request.getContextPath() + "/login/per?ref="+request.getRequestURI()+"&errmsg="+java.net.URLEncoder.encode("你的账号已在其他地方登录，请重新登录", "UTF-8"));
//                    return false;
//                }
            }
        }
        return -1;
    }

    /**
     * 判断企业用户是否已登录
     * @return 1-已登录，-1-未登录，-2-由于设定了唯一登录限制被挤出
     */
    public int isComUserLogin(HttpServletRequest request) {
        Cookie comCookie = WebUtils.getCookie(request, Constants.COOKIE_KEY_COM);

        if(comCookie != null && StringUtils.isNotBlank(comCookie.getValue())) {
            ComUserLoginRedisBo vo = redisEao.readRedisVoFromHash(Constants.REDIS_KEY_COM_LOGIN_INFO_PREFIX + comCookie.getValue(), new ComUserLoginRedisBo());

            if(vo != null && ValidateUtil.validate(vo)
//                    && StringUtils.equals(vo.getIp(), IPUtil.getIp(request))
                    ) {

                if(vo.getCanLoginDual() == 0) { //只有当用户设置为限制唯一登录的时候才判断
                    String checkCookie = redisEao.readFromString(Constants.REDIS_KEY_COM_LOGIN_CHECK_PREFIX  + vo.getComUserId());
                    if(StringUtils.equalsIgnoreCase(comCookie.getValue(), checkCookie)) {
                        //每次正常操作延长生存时间
                        redisEao.setKeyExpire(Constants.REDIS_KEY_COM_LOGIN_INFO_PREFIX + comCookie.getValue(), Constants.REDIS_EXPIRE_TIME_COM_LOGIN);
                        redisEao.setKeyExpire(Constants.REDIS_KEY_COM_LOGIN_CHECK_PREFIX + comCookie.getValue(), Constants.REDIS_EXPIRE_TIME_COM_LOGIN);
                        return 1;
                    } else {
                        return -2;
                    }
                } else {  //否则不判断唯一性登录，直接延长生存时间
                    redisEao.setKeyExpire(Constants.REDIS_KEY_COM_LOGIN_INFO_PREFIX + comCookie.getValue(), Constants.REDIS_EXPIRE_TIME_COM_LOGIN);
                    return 1;
                }
            }
        }

        return -1;
    }
    
    /**
     * 判断后台用户是否已登录
     * @return true已登录，false-未登录
     */
    public boolean isSysUserLogin(HttpServletRequest request) {
    	Cookie comCookie = WebUtils.getCookie(request, Constants.COOKIE_KEY_SYS);
    	
    	if(comCookie != null && StringUtils.isNotBlank(comCookie.getValue())) {
    		SysUserLoginRedisBo sysUserLoginRedisVo = redisEao.readRedisVoFromHash(Constants.REDIS_KEY_SYS_LOGIN_INFO_PREFIX + comCookie.getValue(), new SysUserLoginRedisBo());
    		if(sysUserLoginRedisVo != null) {
    			return true;
    		}
    	}
    	return false;
    }
}
