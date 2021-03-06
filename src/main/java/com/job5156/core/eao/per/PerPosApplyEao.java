package com.job5156.core.eao.per;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.per.job.PerPosApply;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PerPosApplyEao extends BaseHibernateEao<PerPosApply, Integer> {

	@Resource(name="sessionFactoryAction")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    public int countQuery(PerPosApply command) {
    	String hql = "select count(*) from PerPosApply where perUserId = ? AND delStatus!=-1";
        return this.<Number>aggregate(hql, getQueryParam(command)).intValue();
    }
    
    private Object[] getQueryParam(PerPosApply command) {
        Integer perUserId = command.getPerUserId();
        return new Object[]{
        		perUserId
        };
    }
    
	public List<PerPosApply> query(int pn, int pageSize, PerPosApply command){
		String hql = "FROM PerPosApply where perUserId = ? AND delStatus!=-1 order by creTime desc";
		return list(hql, pn, pageSize, getQueryParam(command));
	}

    public List<PerPosApply> findByUserId(Integer userId) {
        String hql = "FROM PerPosApply WHERE perUserId = ? and delStatus!=-1";
        return list(hql, new Object[] {userId});
    }
    
    public int findPosNumById(Integer userId) {
        String hql = "select posId FROM PerPosApply where perUserId = ? AND delStatus!=-1 group by posId";
        return list(hql, new Object[] {userId}).size();
    }
    
    /**
	 * 通过个人用户id查询某页个人应聘过的职位记录列表
	 */
	public List<PerPosApply> findPerPosApplyList(Integer perUserId, Integer pn, Integer pageSize){
		String hql = "FROM PerPosApply WHERE perUserId = ? and delStatus !=-1 order by creTime desc";
 		return list(hql, pn, pageSize, new Object[] {perUserId});
	}
	
	/**
	 * 通过个人用户id查询记录数目
	 */
	public int countByUserId(Integer perUserId) {
        String hql = "select count(*) FROM PerPosApply WHERE perUserId = ? and delStatus!=-1";
        return this.<Number>aggregate(hql, perUserId).intValue();
    }
	
	public PerPosApply findByPosIdAndPerUserId(Integer posId, Integer perUserId){
	    String hql = "FROM PerPosApply WHERE posId = ?  AND perUserId = ? and delStatus !=-1";
	    return unique(hql, new Object[] {posId ,perUserId});
    }

    public PerPosApply findLastApplyLog(Integer perUserId){
        String hql = "FROM PerPosApply WHERE perUserId = ? and delStatus != -1 ORDER BY id desc ";
        return unique(hql, new Object[] {perUserId});
    }
}
