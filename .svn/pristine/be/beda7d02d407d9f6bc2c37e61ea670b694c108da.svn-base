package com.job5156.core.eao.com;

import com.google.common.collect.Lists;
import com.job5156.core.common.Constants;
import com.job5156.core.eao.RedisEao;
import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.com.ComInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class ComInfoEao extends BaseHibernateEao<ComInfo, Integer> {

    @Resource
    private RedisEao redisEao;

	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<ComInfo> findComInfoByComKey(String comKey) {
		
        String hql = "FROM ComInfo WHERE comName LIKE ? ORDER BY updateDate DESC";
        return list(hql, new Object[] {"%"+comKey+"%"});
    }

    public int findComNameUsage(String comName, Integer comId) {
    	
        String hql = "SELECT COUNT(*) FROM ComInfo WHERE comName = ? AND id != ? AND status != -4";
        return this.<Number>aggregate(hql, new Object[] {comName, comId}).intValue();
    }
    
    public List<ComInfo> findComInfoByCrm(String hql, int start, int length, Map<String, Collection<?>> inMap, List<Object> paramList) {
    	return this.listWithIn(hql, start, length, inMap, paramList.toArray());
    }

    public List<ComInfo> findComInfoListByIds(List<Integer> ids){
        if(CollectionUtils.isNotEmpty(ids)) {
            String hql = "FROM ComInfo WHERE id in (:params)";
            return list(hql, new Object[] {ids});
        } else {
            return Lists.newArrayList();
        }
    }
    
    @SuppressWarnings("unchecked")
	public List<Integer> findComIdListBySalerId(Integer salerId){
    	String hql = "SELECT id FROM ComInfo WHERE salerId = ?";
    	return getSession().createQuery(hql).setInteger(0, salerId).list();
    }

    @Override
    public Integer save(ComInfo model) {
        Integer id = super.save(model);
        addToSync(id);
        return id;
    }

    @Override
    public void saveOrUpdate(ComInfo model) {
        super.saveOrUpdate(model);
        addToSync(model.getId());
    }

    @Override
    public void update(ComInfo model) {
        super.update(model);
        addToSync(model.getId());
    }
    
    public int countComInfoBySalerId(Integer salerId){
    	String hql = "SELECT COUNT(id) FROM ComInfo WHERE salerId = ?";
    	return this.<Number>aggregate(hql, new Object[] {salerId}).intValue();
    }

    public void addToSync(Integer id){
        if(id != null && id > 0) {
            redisEao.zAdd(Constants.REDIS_KEY_SYNC_QUEUE_COMPANY, Lists.newArrayList(ObjectUtils.toString(id)));
        }
    }
}
