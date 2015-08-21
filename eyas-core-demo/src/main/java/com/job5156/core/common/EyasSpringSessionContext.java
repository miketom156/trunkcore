package com.job5156.core.common;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.orm.hibernate4.SpringSessionContext;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * <p></p>
 * Date:2015/5/14 17:16
 *
 * @author pzm
 * @version 1.0
 */
public class EyasSpringSessionContext extends SpringSessionContext{
    /**
     * Create a new SpringSessionContext for the given Hibernate SessionFactory.
     *
     * @param sessionFactory the SessionFactory to provide current Sessions for
     */

    private SessionFactory sessionFactoryThis;
    public EyasSpringSessionContext(SessionFactoryImplementor sessionFactory) {
        super(sessionFactory);
        sessionFactoryThis = sessionFactory;
    }

    @Override
    public Session currentSession() throws HibernateException {
        try{
            return super.currentSession();
        } catch (HibernateException he){
            if(he.getMessage().equals("No Session found for current thread")){
                return openNewSession();
            } else {
                throw he;
            }
        }
    }

    private Session openNewSession() {
        Session session = sessionFactoryThis.openSession();
        SessionHolder sessionHolder = new SessionHolder(session);
        TransactionSynchronizationManager.bindResource(sessionFactoryThis, sessionHolder);
        return session;
    }
}
