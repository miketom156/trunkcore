package com.job5156.core.common.helper;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.job5156.core.bo.redis.ComUserLoginRedisBo;
import com.job5156.core.bo.redis.PerUserLoginRedisBo;
import com.job5156.core.bo.redis.SysUserLoginRedisBo;
import com.job5156.core.common.CommonEnum;
import com.job5156.core.common.Constants;
import com.job5156.core.eao.RedisEao;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.List;

/**
 * <p></p>
 * Date:2015/6/16 10:20
 *
 * @author hjs
 * @version 1.0
 */
@Component
public class CookieExtractHelper {
    @Resource
    private RedisEao redisEao;

    public ComUserLoginRedisBo extractComCookie(String cookie) {
        return redisEao.readRedisVoFromHash(Constants.REDIS_KEY_COM_LOGIN_INFO_PREFIX  + cookie, new ComUserLoginRedisBo());
    }

    public PerUserLoginRedisBo extractPerCookie(String cookie) {
        return redisEao.readRedisVoFromHash(Constants.REDIS_KEY_PER_LOGIN_INFO_PREFIX  + cookie, new PerUserLoginRedisBo());
    }

    public SysUserLoginRedisBo extractSysCookie(String cookie){
        return redisEao.readRedisVoFromHash(Constants.REDIS_KEY_SYS_LOGIN_INFO_PREFIX + cookie, new SysUserLoginRedisBo());
    }

    public Integer getComUserIdFromCookie(Cookie comCookie) {
        if(comCookie == null || StringUtils.isBlank(comCookie.getValue())) {
            return 0;
        } else {
            ComUserLoginRedisBo vo = extractComCookie(comCookie.getValue());
            if(vo == null || StringUtils.isBlank(vo.getComUserId())) {
                return 0;
            } else {
                return NumberUtils.toInt(vo.getComUserId(), 0);
            }
        }
    }

    public Integer getComIdFromCookie(Cookie comCookie) {
        if(comCookie == null || StringUtils.isBlank(comCookie.getValue())) {
            return 0;
        } else {
            ComUserLoginRedisBo vo = extractComCookie(comCookie.getValue());
            if(vo == null || StringUtils.isBlank(vo.getComId())) {
                return 0;
            } else {
                return NumberUtils.toInt(vo.getComId(), 0);
            }
        }
    }

    public String getComNameFromCookie(Cookie comCookie) {
        if(comCookie == null || StringUtils.isBlank(comCookie.getValue())) {
            return "";
        } else {
            ComUserLoginRedisBo vo = extractComCookie(comCookie.getValue());
            if(vo == null || StringUtils.isBlank(vo.getComName())) {
                return "";
            } else {
                return StringUtils.trimToEmpty(vo.getComName());
            }
        }
    }

    public String getComUserNameFromCookie(Cookie comCookie) {
        if(comCookie == null || StringUtils.isBlank(comCookie.getValue())) {
            return "";
        } else {
            ComUserLoginRedisBo vo = extractComCookie(comCookie.getValue());
            if(vo == null || StringUtils.isBlank(vo.getComUserName())) {
                return "";
            } else {
                return StringUtils.trimToEmpty(vo.getComUserName());
            }
        }
    }

    public Integer getComUserRoleType(Cookie comCookie) {
        if(comCookie == null || StringUtils.isBlank(comCookie.getValue())) {
            return -1;
        } else {
            ComUserLoginRedisBo vo = extractComCookie(comCookie.getValue());
            if(vo == null
                    || vo.getComUserRoleType() == null
                    && ( vo.getComUserRoleType() != CommonEnum.ComUserType.MAJOR.getValue()
                    || vo.getComUserRoleType() != CommonEnum.ComUserType.CHILD.getValue()
                    || vo.getComUserRoleType() != CommonEnum.ComUserType.BRANCH.getValue())) {
                return -1;
            } else {
                return vo.getComUserRoleType();
            }
        }
    }

    public List<Integer> getComUserDeptRights(Cookie comCookie) {
        if(comCookie == null || StringUtils.isBlank(comCookie.getValue())) {
            return Lists.newArrayList();
        } else {
            ComUserLoginRedisBo vo = extractComCookie(comCookie.getValue());
            if(vo == null || StringUtils.isBlank(vo.getComUserDeptRights())) {
                return Lists.newArrayList();
            } else {
                return new Gson().fromJson(StringUtils.trimToEmpty(vo.getComUserDeptRights()), new TypeToken<List<Integer>>() {}.getType());
            }
        }
    }

//    public boolean getComIsMemberFromCookie(Cookie comCookie) {
//        if(comCookie == null || StringUtils.isBlank(comCookie.getValue())) {
//            return false;
//        } else {
//            ComUserLoginRedisBo vo = extractComCookie(comCookie.getValue());
//            if(vo == null) {
//                return false;
//            } else {
//                return vo.isMember();
//            }
//        }
//    }
//
//    public String getComMemberRestrictAreaFromCookie(Cookie comCookie) {
//        if(comCookie == null || StringUtils.isBlank(comCookie.getValue())) {
//            return "";
//        } else {
//            ComUserLoginRedisBo vo = extractComCookie(comCookie.getValue());
//            if(vo == null || StringUtils.isBlank(vo.getMemberRestrictArea())) {
//                return "";
//            } else {
//                return StringUtils.trimToEmpty(vo.getMemberRestrictArea());
//            }
//        }
//    }
//
//    public Integer getComMemberTypeFromCookie(Cookie comCookie) {
//        if(comCookie == null || StringUtils.isBlank(comCookie.getValue())) {
//            return 0;
//        } else {
//            ComUserLoginRedisBo vo = extractComCookie(comCookie.getValue());
//            if(vo == null || StringUtils.isBlank(vo.getMemberType())) {
//                return 0;
//            } else {
//                return NumberUtils.toInt(vo.getMemberType(), 0);
//            }
//        }
//    }

    /**
     * 从Cookie中获取个人用户Id。
     * 如果Cookie值为空、Cookie值对应的用户登录信息不存在，返回0。
     * @param perCookie
     * @return
     */
    public Integer getPerUserIdFromCookie(Cookie perCookie) {
        if(perCookie == null || StringUtils.isBlank(perCookie.getValue())) {
            return 0;
        } else {
            PerUserLoginRedisBo vo = extractPerCookie(perCookie.getValue());
            if(vo == null || StringUtils.isBlank(vo.getId())) {
                return 0;
            } else {
                return NumberUtils.toInt(vo.getId(), 0);
            }
        }
    }

    public String getPerUserLoginNameFromCookie(Cookie perCookie) {
        if(perCookie == null || StringUtils.isBlank(perCookie.getValue())) {
            return "";
        } else {
            PerUserLoginRedisBo vo = extractPerCookie(perCookie.getValue());
            if(vo == null || StringUtils.isBlank(vo.getLoginName())) {
                return "";
            } else {
                return StringUtils.trimToEmpty(vo.getLoginName());
            }
        }
    }

    public Integer getSysUserIdFromCookie(Cookie sysCookie){
        if(sysCookie == null || StringUtils.isBlank(sysCookie.getValue())) {
            return 0;
        } else {
            SysUserLoginRedisBo vo = extractSysCookie(sysCookie.getValue());
            if(vo == null || StringUtils.isBlank(String.valueOf(vo.getUserId()))) {
                return 0;
            } else {
                return vo.getUserId();
            }
        }
    }

    public String getSysUserNameFromCookie(Cookie sysCookie){
        if(sysCookie == null || StringUtils.isBlank(sysCookie.getValue())) {
            return "";
        } else {
            SysUserLoginRedisBo vo = extractSysCookie(sysCookie.getValue());
            if(vo == null || StringUtils.isBlank(String.valueOf(vo.getUserName()))) {
                return "";
            } else {
                return vo.getUserName();
            }
        }
    }

}
