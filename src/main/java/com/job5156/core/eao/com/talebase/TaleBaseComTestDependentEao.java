package com.job5156.core.eao.com.talebase;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.com.talebase.TalebaseComTestDependent;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单与产品的关联表Dao
 * 
 * @author 夏安定
 * 
 */
@Repository
public class TaleBaseComTestDependentEao extends BaseHibernateEao<TalebaseComTestDependent, Integer> {

	@Override
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 获取此产品订单号
	 * 
	 * @param productId
	 * @return
	 */
	public List<Integer> getOrderList(Integer productId) {
		String sql = "SELECT order_id FROM talebase_com_test_dependent AS ctd WHERE ctd.product_id=? ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setInteger(0, productId);
		return query.list();
	}
	
	/**
	 * 企业服务申请数据
	 * 
	 * @param ids
	 * @return
	 */
	public TalebaseComTestDependent getComTestDependent(Integer orderId,Integer productId){
		String hql = "FROM TalebaseComTestDependent ctd WHERE ctd.orderId=:orderId AND ctd.productId=:productId";
		Query query = getSession().createQuery(hql);
		query.setInteger("orderId", orderId);
		query.setInteger("productId", productId);
		List<Object> list = query.list();
		TalebaseComTestDependent ctd = null;
		if(CollectionUtils.isNotEmpty(list)){
			ctd = (TalebaseComTestDependent)list.get(0);
		}
		return ctd;
	}
}