package com.job5156.core.eao.per;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.per.PerLetter;

/**
 * <p></p>
 * Date:Administrator 2015-8-4
 *
 * @author hjs
 * @version 1.0
 */
@Repository
public class PerLetterEao  extends BaseHibernateEao<PerLetter,Integer> {

    @Resource(name="sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<PerLetter> findByUserId(Integer userId) {
        String hql = "FROM PerLetter WHERE userId = ?";
        return list(hql, new Object[] {userId});
    }
}
