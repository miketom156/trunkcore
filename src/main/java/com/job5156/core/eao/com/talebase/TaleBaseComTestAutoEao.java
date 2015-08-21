package com.job5156.core.eao.com.talebase;

import com.google.common.collect.Maps;
import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.com.talebase.TalebaseComTestAuto;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 自动邀请测评Dao
 * 
 * @author 夏安定
 * 
 */
@Repository
public class TaleBaseComTestAutoEao extends BaseHibernateEao<TalebaseComTestAuto, Integer> {

	@Override
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 根据ComTestAuto对象获取所对应的产品
	 * 
	 * @param comTestAuto
	 * @return
	 */
	public List<TalebaseComTestAuto> getComTestAuto(TalebaseComTestAuto comTestAuto) {
		final String hql = "FROM TalebaseComTestAuto WHERE 1=1 AND comId=:comId AND posId=:posId";
		Map<String, Object> param = Maps.newHashMap();
		param.put("comId", comTestAuto.getComId());
		param.put("posId", comTestAuto.getPosId());
		return find(hql, param);
	}
}