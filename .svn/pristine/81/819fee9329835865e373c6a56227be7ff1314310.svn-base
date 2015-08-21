package com.job5156.core.srv.com;

import java.util.List;

import com.job5156.core.eao.com.ComInfoEao;
import com.job5156.webapp.model.com.ComInfo;
import com.job5156.core.srv.base.BaseSrv;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * </p>
 * Date:2015/5/8 17:35
 * 
 * @author pzm
 * @version 1.0
 */

@Service
@Transactional(value = "transactionManager")
public class ComInfoSrv extends BaseSrv<ComInfo, Integer> {
	@Resource
	private ComInfoEao comInfoEao;

	protected void initBaseHibernateEao() {
		super.baseHibernateEao = comInfoEao;
	}

	public List<ComInfo> findComInfoByComKey(String comKey) {
		return this.comInfoEao.findComInfoByComKey(comKey);
	}
}
