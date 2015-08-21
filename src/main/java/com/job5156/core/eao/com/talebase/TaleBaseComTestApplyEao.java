package com.job5156.core.eao.com.talebase;

import com.google.common.collect.Maps;
import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.com.talebase.TalebaseComTestApply;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 企业测评服务申请Dao
 * 
 * @author 夏安定
 * 
 */
@Repository
public class TaleBaseComTestApplyEao extends BaseHibernateEao<TalebaseComTestApply, Integer> {

	@Override
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 企业服务申请
	 * 
	 * @param obj
	 * @return
	 */
	public List<TalebaseComTestApply> getComTestApplyList(Object obj) {
		final String hql = "FROM TalebaseComTestApply WHERE 1=1 AND comId=:comId AND endDate>=:endDate ";
		Map<String, Object> param = Maps.newHashMap();
		if (obj instanceof TalebaseComTestApply) {
			TalebaseComTestApply ctas = (TalebaseComTestApply) obj;
			param.put("comId", ctas.getComId());
		} else {
			param.put("comId", obj);
		}
		param.put("endDate", new Date());
		return find(hql, param);
	}
	
	/**
	 * 企业服务申请数据
	 * 
	 * @param ids
	 * @return
	 */
	public TalebaseComTestApply getComTestApply(String ids){
		ids = StringUtils.substring(ids, 0,StringUtils.lastIndexOf(ids, ","));
		String hql = "FROM TalebaseComTestApply cta WHERE cta.orderId IN ("+ids+") AND cta.overplus > 0 AND cta.endDate >= :nowDate ORDER BY cta.endDate";
		Query query = getSession().createQuery(hql);
		query.setDate("nowDate", new Date());
		List<Object> list = query.list();
		TalebaseComTestApply cta = null;
		if(CollectionUtils.isNotEmpty(list)){
			cta = (TalebaseComTestApply)list.get(0);
		}
		return cta;
	}
	
	public List<Object> getTalebaseComTestApplyList(Integer comId,Integer productId){
		String sql = "SELECT tc.* FROM talebase_com_test_apply tc JOIN talebase_com_test_dependent tb ON tb.order_id=tc.order_id WHERE tc.com_id=? AND tb.product_id=? ORDER BY tc.end_date";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter(0, comId);
		query.setParameter(1, productId);
		return query.list();
	}
}