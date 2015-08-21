package com.job5156.core.eao.per;

import com.google.common.collect.Maps;
import com.job5156.core.common.Constants;
import com.job5156.core.common.helper.CookieHelper;
import com.job5156.core.common.helper.PageViewHelper;
import com.job5156.core.common.util.IPUtil;
import com.job5156.core.bo.CountRegisterBo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * Date:2015/5/8 15:08
 *
 * @author pzm
 * @version 1.0
 */
@Repository
public class PerRegisterEao {
    private static Logger logger = Logger.getLogger(PerRegisterEao.class);
    @Resource
    private JdbcTemplate jdbcTemplateCount;

    @Resource
    private CookieHelper cookieHelper;

    @Resource
    private PageViewHelper pageViewHelper;

    /**
     * <p>
     * 统计用户注册数据
     * </p>
     */
    public void countResumeRegister(CountRegisterBo vo) {
        try {

            String sql = "INSERT INTO PV_Person_Register(register,semRegister, fromDomain, fromUrl, targetUrl, siteId, userId, userIp, credate, account_from)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplateCount.update(sql, vo.getSpreadParam(), vo.getSemSpreadParam(), vo.getFdomain(), StringUtils.abbreviate(vo.getFromUrl(), 800),
                    StringUtils.abbreviate(vo.getTargetUrl(), 500), vo.getSiteId(), vo.getUserId(), vo.getUserIp(), new Date(),
                    vo.getComeFrom() == null ? null : vo.getComeFrom().getValue());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("简历注册写入统计数据到DB异常：" + e.getMessage(), e);
        }
    }

    public CountRegisterBo getCountRegisterVo(HttpServletRequest request, Integer userId) {
        CountRegisterBo vo = new CountRegisterBo();
        Map<String, String> cookieMap = null;
        try {
            cookieMap = cookieHelper.readMapFromGroupCookie(Constants.COOKIE_GROUP_KEY_PVCOUNT, request);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            cookieMap = Maps.newHashMap();
        }
        try {
            vo.setSpreadParam(cookieMap.get("spreadParam"));
            vo.setSemSpreadParam(cookieMap.get("semSpreadParam"));
            vo.setFromUrl(cookieMap.get("fromUrl"));
            vo.setUserIp(IPUtil.getIp(request));
            vo.setTargetUrl(cookieMap.get("targetUrl"));
            vo.setSiteId(pageViewHelper.getSiteId(request));
            vo.setFdomain(pageViewHelper.getUrlDomain(vo.getFromUrl()));
            vo.setUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取简历注册统计信息出错：" + e.getMessage(), e);
        }
        return vo;
    }

    public CountRegisterBo getCountRegisterVo(HttpServletRequest request) {
        return getCountRegisterVo(request, null);
    }

    /**
     * 查询某个用户的注册记录
     * @param perId
     * @return
     */
    public Map<String,Object> findResumeRegisterByPerId(Integer perId){
        try {
            List<Map<String, Object>> list = jdbcTemplateCount.queryForList("select * from PV_Person_Register where userId = ?", perId);
            return (list == null || list.isEmpty()) ? null : list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询用户注册记录异常：" + e.getMessage(), e);
            return null;
        }
    }
}
