package com.job5156.core.common;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p></p>
 * Date:2015/5/14 17:08
 *
 * @author pzm
 * @version 1.0
 */
public class EyasHibernateTransactionManager extends HibernateTransactionManager {
    @Resource
    private SessionFactory sessionFactory;
    @Resource
    private SessionFactory sessionFactoryAction;
    @Resource
    private SessionFactory sessionFactoryIrc;
    @Resource
    private SessionFactory sessionFactorySystemQuery;
    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        for(SessionFactory sf:new SessionFactory[]{sessionFactory,sessionFactoryAction,sessionFactoryIrc,sessionFactorySystemQuery}){
            Object o = TransactionSynchronizationManager.getResource(sf);
            if(o instanceof SessionHolder){
                SessionFactoryUtils.closeSession(((SessionHolder) o).getSession());
                TransactionSynchronizationManager.unbindResource(sf);
            }
        }
    }
}
