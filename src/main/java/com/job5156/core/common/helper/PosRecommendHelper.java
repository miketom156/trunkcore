package com.job5156.core.common.helper;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.job5156.core.bo.PosBriefSimpleBo;
import com.job5156.core.bo.form.searcher.SearchKeyMap;
import com.job5156.core.bo.form.searcher.SearchPosFormBo;
import com.job5156.core.bo.form.searcher.SearchPosResultBo;
import com.job5156.core.bo.mapping.PerResumeBo;
import com.job5156.core.common.Constants;
import com.job5156.core.common.pagination.Page;
import com.job5156.core.common.pagination.PageUtil;

import com.job5156.core.common.util.PosNameFilterUtil;
import com.job5156.core.common.util.RegExUtil;
import com.job5156.core.eao.com.ComPositionEao;
import com.job5156.core.eao.per.PerPosFavoriteEao;
import com.job5156.webapp.model.com.ComPosition;
import com.job5156.webapp.model.per.PerResume;
import com.job5156.webapp.model.per.PerSearcher;
import com.job5156.webapp.model.per.PerUser;
import com.job5156.webapp.model.per.job.PerPosApply;
import com.job5156.webapp.model.per.job.PerPosFavorite;
import com.job5156.searcher.entity.PosBrief;
import com.job5156.core.srv.RedisSrv;
import com.job5156.core.srv.com.ComPositionSrv;
import com.job5156.core.srv.per.*;
import com.job5156.core.srv.search.SearchPosSrv;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <p></p>
 * Date:2015/6/13 10:20
 *
 * @author hjs
 * @version 1.0
 */
@Component
public class PosRecommendHelper {
    @Resource
    private CookieHelper cookieHelper;

    /**
     * 把REDIES KEY 存入COOKIES或读取
     *
     * @param request
     * @param response
     * @param site
     * @return
     */
    public String getRedisKeyByCookie(HttpServletRequest request, HttpServletResponse response, String site) {
        Cookie storeCookies = WebUtils.getCookie(request, "guest_you_like");
        String redisKey = storeCookies != null ? storeCookies.getValue() : "";
        if (storeCookies == null) {//storeCookies为空时生成新的cookies key
            redisKey = generateRedisKey(site);
            storeCookies = cookieHelper.createCookie("guest_you_like", redisKey);
            response.addCookie(storeCookies);
        }
        return redisKey;
    }

    /**
     * 生成缓存猜你喜欢的rediskey
     *
     * @param site 分站点
     * @return
     */
    public String generateRedisKey(String site) {
        String redisKeyPre = Constants.REDIS_KEY_SITE_GUEST_LIKE_POSITION_LIST + site + ":";
        String randomKey = "g_" + new Date().getTime();
        String redisKey = redisKeyPre + randomKey;
        return redisKey;
    }

}
