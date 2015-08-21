package com.job5156.core.eao.count;

import com.job5156.webapp.model.count.PvPersonRegister;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * <p>用户注册统计持久类</p>
 * Date:2015/5/20 10:50
 *
 * @author pzm
 * @version 1.0
 */
@Repository
public class PVPersonRegisterEao {
    @Resource
    private JdbcTemplate jdbcTemplateCount;

    /**
     * 新增用户注册统计
     * @param personRegister
     */
    public void save(PvPersonRegister personRegister) {
        String sql = "INSERT INTO PV_Person_Register(register,semRegister, fromDomain, fromUrl, targetUrl, siteId, userId, userIp, credate, account_from)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplateCount.update(sql, personRegister.getRegister(), personRegister.getSemRegister(), personRegister.getFromDomain(), StringUtils.abbreviate(personRegister.getFromUrl(), 800),
                StringUtils.abbreviate(personRegister.getTargetUrl(), 500), personRegister.getSiteId(), personRegister.getUserId(), personRegister.getUserIp(), new Date(),
                personRegister.getAccountFrom());
    }

    /**
     * 根据用户id查找注册统计
     * @param userId 用户id
     * @return
     */
    public PvPersonRegister getByUserId(Integer userId){
        String sql = "select * from PV_Person_Register where userId=?";
        List<Map<String,Object>> list = jdbcTemplateCount.queryForList(sql,userId);
        if(list!=null && !list.isEmpty()){
            Map map = list.get(0);
            PvPersonRegister pvPersonRegister = new PvPersonRegister();
            pvPersonRegister.setSiteId((Integer)map.get("siteId"));
            pvPersonRegister.setFromUrl((String)map.get("fromUrl"));
            pvPersonRegister.setAccountFrom((Integer)map.get("account_from"));
            pvPersonRegister.setCredate((Date)map.get("credate"));
            pvPersonRegister.setFromDomain((String)map.get("fromDomain"));
            pvPersonRegister.setId((Integer)map.get("id"));
            pvPersonRegister.setKeyWord((String)map.get("keyWord"));
            pvPersonRegister.setRegister((String)map.get("register"));
            pvPersonRegister.setSemRegister((String)map.get("semRegister"));
            pvPersonRegister.setTargetUrl((String)map.get("targetUrl"));
            pvPersonRegister.setUserId((Integer)map.get("userId"));
            pvPersonRegister.setUserIp((String)map.get("userIp"));
            return pvPersonRegister;
        } else {
            return null;
        }
    }
}
