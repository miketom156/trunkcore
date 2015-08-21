package com.job5156.core.eao.per;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.per.job.PerInviteLog;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class PerInviteLogEao extends BaseHibernateEao<PerInviteLog, Integer> {

	@Resource(name="sessionFactoryAction")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    public List<PerInviteLog> findByUserId(Integer userId) {
        String hql = "FROM PerInviteLog WHERE perUserId = ? and delStatus!=-1";
        return list(hql, new Object[] {userId});
    }
    
    public int findComNumByUserId(Integer userId) {
        String hql = "select comId FROM PerInviteLog WHERE perUserId = ? and delStatus!=-1 group by comId";
        return list(hql, new Object[] {userId}).size();
    }
    
    public int countQuery(PerInviteLog command) {
    	String hql = "select count(*) from PerInviteLog WHERE perUserId = ? and delStatus!=-1 order by creTime desc";
        return this.<Number>aggregate(hql, getQueryParam(command)).intValue();
    }
    
    private Object[] getQueryParam(PerInviteLog command) {
        Integer perUserId = command.getPerUserId();
        return new Object[]{
        		perUserId
        };
    }
    
	public List<PerInviteLog> query(int pn, int pageSize, PerInviteLog command){
		String hql = "FROM PerInviteLog WHERE perUserId = ?  and delStatus!=-1 order by creTime desc";
		return list(hql, pn, pageSize, getQueryParam(command));
	}
    
	public List<PerInviteLog> findPerInviteLogList(Integer perUserId, Integer pn, Integer pageSize){
		String hql = "FROM PerInviteLog WHERE perUserId = ? and delStatus !=-1 order by readStatus asc, creTime desc";
 		return list(hql, pn, pageSize, new Object[] {perUserId});
	}

    public int countByUserId(Integer perUserId) {
        String hql = "select count(*) FROM PerInviteLog WHERE perUserId = ? and delStatus !=-1";
        return this.<Number>aggregate(hql, perUserId).intValue();
    }
    public int countByUserIdAfter2015(Integer perUserId) {
        String hql = "select count(*) FROM PerInviteLog WHERE perUserId = ? and delStatus !=-1 and creTime>='2015-1-1' ";
        return this.<Number>aggregate(hql, perUserId).intValue();
    }

    public int countUnreadByUserId(Integer perUserId) {
        String hql = "select count(*) FROM PerInviteLog WHERE perUserId = ? and delStatus !=-1 and readStatus = 0";
        return this.<Number>aggregate(hql, perUserId).intValue();
    }
    public int countUnreadByUserIdAfter2015(Integer perUserId) {
        String hql = "select count(*) FROM PerInviteLog WHERE perUserId = ? and delStatus !=-1 and readStatus = 0  and creTime>='2015-1-1' ";
        return this.<Number>aggregate(hql, perUserId).intValue();
    }

    public List<PerInviteLog> findPerInviteLogList(Integer perUserId){
        String hql = "FROM PerInviteLog WHERE perUserId = ? and delStatus !=-1 order by creTime desc";
        return list(hql, 1, 100, new Object[] {perUserId});
    }

    public List<PerInviteLog> findPerInviteLogListByPage(Integer perUserId,Integer pageSize,Integer pageNum){
        String hql = "FROM PerInviteLog WHERE perUserId = ? and delStatus !=-1 order by creTime desc";
        return list(hql, pageNum, pageSize, new Object[] {perUserId});
    }

    public int countAfterDate(Integer perUserId, Date date) {
        String hql = "select count(*) from PerInviteLog where perUserId = ? AND delStatus!=-1 AND creTime > ?";
        return this.<Number>aggregate(hql, perUserId, date).intValue();
    }
}
