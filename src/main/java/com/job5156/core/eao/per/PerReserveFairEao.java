package com.job5156.core.eao.per;

import com.job5156.core.eao.base.BaseHibernateEao;
import com.job5156.webapp.model.irc.IrcReserve;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * Date:2015/6/16 10:20
 *
 * @author hjs
 * @version 1.0
 */
@Repository
public class PerReserveFairEao extends BaseHibernateEao<IrcReserve, Integer> {

    @Resource(name = "sessionFactoryIrc")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<IrcReserve> findComInviteByUserId(Integer perId) {
        String hql = "FROM IrcReserve WHERE userId=? AND resStatus = 2 AND inviteDelete = 0 order by resTime desc ";
        return list(hql,1, 100, new Object[] {perId});
    }
}
