package com.job5156.core.eao.per;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.per.PerFollowCom;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015-6-16.
 */
@Repository
public class PerFollowComEao extends BaseHibernateEao<PerFollowCom, Integer> {
    @Resource(name="sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<PerFollowCom> findByPerUserId(Integer perUserId) {
        String hql = "FROM PerFollowCom WHERE perUserId = ?";
        return list(hql, new Object[] {perUserId});
    }
}
