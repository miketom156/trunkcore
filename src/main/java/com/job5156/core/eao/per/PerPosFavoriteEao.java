package com.job5156.core.eao.per;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.per.job.PerPosFavorite;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * Date:2015/6/15 10:20
 *
 * @author hjs
 * @version 1.0
 */
@Repository
public class PerPosFavoriteEao extends BaseHibernateEao<PerPosFavorite,Integer>{

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 查询个人职位收藏记录列表
     */
    public List<PerPosFavorite> findByUserId(Integer userId) {
        String hql = "FROM PerPosFavorite WHERE perUserId = ? and delStatus!=-1 order by creTime desc";
        return list(hql, new Object[]{userId});
    }

    public int countQuery(PerPosFavorite command) {
        String hql = "select count(*) from PerPosFavorite WHERE perUserId = ? AND delStatus!=-1";
        return this.<Number>aggregate(hql, getQueryParam(command)).intValue();
    }

    private Object[] getQueryParam(PerPosFavorite command) {
        Integer perUserId = command.getPerUserId();
        return new Object[]{
                perUserId
        };
    }

    public List<PerPosFavorite> query(int pn, int pageSize, PerPosFavorite command){
        String hql = "FROM PerPosFavorite WHERE perUserId = ? AND delStatus!=-1 order by creTime desc";
        return list(hql, pn, pageSize, getQueryParam(command));
    }

}

