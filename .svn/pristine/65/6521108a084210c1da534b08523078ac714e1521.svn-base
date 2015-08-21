package com.job5156.core.eao.com;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.core.eao.base.PropertyFilter;
import com.job5156.webapp.model.com.ComBlacklist;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ComBlacklistEao extends BaseHibernateEao<ComBlacklist, Integer> {

	@Resource(name="sessionFactoryAction")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	 public int countQuery(Object[] params, String hqlParam) {
    	 String hql = "select count(*) from ComBlacklist where comId = ? " + hqlParam;
    	 return this.<Number>aggregate(hql, params).intValue();
    }
    
	public List<ComBlacklist> query(int pn, int pageSize, Object[] params, String hqlParam){
		String hql = "from ComBlacklist where comId = ? " + hqlParam;
		return list(hql, pn, pageSize, params);
	}

    public boolean checkExistBlacklist(Integer comId, Integer perUserId) {
        String hql = "select count(*) from ComBlacklist where comId = ? and perUserId = ?";
        Integer count = this.<Number>aggregate(hql,comId, perUserId).intValue();
        return count > 0;
    }
    
    public List<ComBlacklist> getComBlacklistByPosId(Integer posId) {
		return findBy("posId", new Object[] { posId }, PropertyFilter.MatchType.EQ);
	}
		
    public Boolean checkResumeIsBuy(Integer comId, Integer resumeId) {  
        String hql = "SELECT buyFlag FROM ComBlacklist WHERE resumeId = ? AND comId = ?  AND buyFlag = 1";
        return unique(hql, new Object[]{resumeId, comId}) == null? false :true;
     }	
    public ComBlacklist findBySourceId(Integer sourceId){
        String hql = "FROM ComBlacklist WHERE sourceId = ? ";
        return unique(hql, new Object[]{sourceId});
    }

    public List<Integer> tempFillUpMixInfoComList(){
        String hql = "SELECT DISTINCT comId FROM ComBlacklist where delStatus!=-1 AND NULLIF(mixInfo, '') IS NULL ";
        return list(hql);
    }

    public List<ComBlacklist> findNoMixInfoListByComId(Integer comId){
        String hql = "from ComBlacklist where comId = ? AND delStatus!=-1 AND NULLIF(mixInfo, '') IS NULL ";
        return list(hql, new Object[]{comId});
    }
}
