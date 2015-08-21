package com.job5156.core.eao.com;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.com.ComPosition;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015-6-16.
 */
@Repository
public class ComPositionSlaveEao extends BaseHibernateEao<ComPosition, Integer>{

    @Resource(name = "sessionFactorySystemQuery")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 根据企业id列表查询职位信息，按照发布时间排序
     * @param comIds
     * @return
     */
    public List<ComPosition> getPosListByComId(List<Integer> comIds) {
        String hql = "FROM ComPosition WHERE comId in (:params) AND posStatus > 0 order by createDate DESC";
        return list(hql, 1, 100, new Object[] {comIds});
    }
}
