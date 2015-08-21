package com.job5156.core.eao.per;

import com.job5156.core.common.helper.IpSeekerHelper;
import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.core.entity.per.PerLoginHistory;
import com.job5156.core.entity.per.PerUser;
import com.job5156.core.entity.sys.system.SysUser;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
/**
 * <p>用户登录历史持久类</p>
 * Date:2015/5/20 10:50
 *
 * @author pzm
 * @version 1.0
 */
@Repository
public class PerLoginHistoryEao extends BaseHibernateEao<PerLoginHistory, Integer> {
	@Resource
    private IpSeekerHelper ipSeekerHelper;

    @Resource(name="sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 新增用户登录历史记录
     * @param perUser 用户实体
     * @param sysUser 管理员（用于管理员后台登陆个人用户）
     * @param ip 登录ip
     * @param loginType 登录类型
     */
    public void addLoginHistory(PerUser perUser, SysUser sysUser, String ip, String loginType) {
    	PerLoginHistory perLoginHistory = new PerLoginHistory();
    	
    	perLoginHistory.setPerUserId(perUser.getId());
    	perLoginHistory.setPerUserAccount(perUser.getAccount());

    	perLoginHistory.setCreTime(DateTime.now().toDate());
    	perLoginHistory.setIp(ip);
    	perLoginHistory.setArea(ipSeekerHelper.getCountryAndArea(ip));
    	//perLoginHistory.setArea(ipSeekerHelper.getCountryAndAreaByWeb(ip));
    	

        if(sysUser != null) {
        	perLoginHistory.setSysUserId(sysUser.getId());
        	perLoginHistory.setSysUserAccount(sysUser.getAccount());
        	perLoginHistory.setSysUserName(sysUser.getName());
        }

        perLoginHistory.setLoginType(loginType);

        this.save(perLoginHistory);
    }
}
