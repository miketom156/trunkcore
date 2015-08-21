package com.job5156.core.eao.per;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.per.job.PerViewLog;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * Date:2015/6/12 9:22
 *
 * @author hjs
 * @version 1.0
 */
@Repository
public class PerViewLogEao extends BaseHibernateEao<PerViewLog, Integer> {
    @Resource(name="sessionFactoryAction")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<PerViewLog> findPerViewLogList(Integer perUserId){
        String hql = "FROM PerViewLog WHERE perUserId = ? and delStatus !=-1 order by creTime desc";
        return list(hql, 1, 100, new Object[] {perUserId});
    }

    public int countQuery(PerViewLog command) {
        String hql = "SELECT COUNT(*) FROM PerViewLog WHERE perUserId = ? AND delStatus!=-1 AND comId > 0";
        return this.<Number>aggregate(hql, getQueryParam(command)).intValue();
    }

    private Object[] getQueryParam(PerViewLog command) {
        Integer perUserId = command.getPerUserId();
        return new Object[]{
                perUserId
        };
    }

    public List<PerViewLog> query(int pn, int pageSize, PerViewLog command){
        String hql = "FROM PerViewLog WHERE perUserId = ? AND delStatus!=-1 AND comId > 0 ORDER BY creTime DESC";
        return list(hql, pn, pageSize, getQueryParam(command));
    }

    public List<PerViewLog> findComNumById(Integer userId) {
        String hql = "FROM PerViewLog WHERE perUserId = ? AND delStatus!=-1 AND comId > 0  GROUP BY comId";
        return list(hql, new Object[] {userId});
    }
}
