/**
 * 
 */
package com.job5156.core.eao.per;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.per.PerSendout;

/**
 * @author lyh
 * @Description 
 * @date 2015年8月11日
 * 
 */
@Repository
public class PerSendoutEao extends BaseHibernateEao<PerSendout, Integer> {

	@Resource(name = "sessionFactoryAction")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	  /**
     * 个人用户的所有外发记录
     * @param accountId
     * @return
     */
    public List<PerSendout> findPerSendoutList(int accountId) {
        String hql = "FROM PerSendout WHERE perUserId = ? AND delStatus > -1 ORDER BY creDate DESC";
        return list(hql, new Object[] { accountId });
    }

	/**
	 * 查询当天外发简历的次数
	 * @param perUserId
	 * @return
	 */
	public int findSendoutCountByToday(int perUserId) {
		Date beginDate = new DateTime().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toDate();
		Date endDate = new DateTime().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toDate();
		String hql = "SELECT count(*) From PerSendout where perUserId=? AND cre_date between ? and ?";
		return this.<Number>aggregate(hql, perUserId, beginDate, endDate).intValue();
	}
	
	

}
