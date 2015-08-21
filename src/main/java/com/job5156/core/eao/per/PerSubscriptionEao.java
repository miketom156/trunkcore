/**
 * 
 */
package com.job5156.core.eao.per;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.per.PerSubscription;

/**
 * @author lyh
 * @Description 
 * @date 2015年8月4日
 * 
 */
@Repository
public class PerSubscriptionEao extends BaseHibernateEao<PerSubscription, Integer> {

	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<PerSubscription> findByUserId(Integer userId) {
		String hql = "FROM PerSubscription where userId=? ";
		return list(hql, new Object[] {userId});
	}
	 
	public List<PerSubscription> findByUserIdAndMainType(Integer userId, String mainType) {
    	String hql = "FROM PerSubscription where userId=? and mainType='"+mainType+"'";
        return list(hql, new Object[] {userId});
	}
	 
	public List<PerSubscription> findByUserIdAndType(Integer userId, String mainType, String subType){
		String hql = "FROM PerSubscription where userId=? and mainType=? and subType=?";
		return list(hql, new Object[] {userId, mainType, subType});
	}

}
