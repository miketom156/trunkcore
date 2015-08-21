package com.job5156.core.srv.base;

import com.job5156.core.eao.base.BaseHibernateEao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p></p>
 * Date:2015/5/8 16:49
 *
 * @author pzm
 * @version 1.0
 */
@Transactional()
public abstract class BaseSrv<M extends java.io.Serializable, PK extends java.io.Serializable> {
    protected BaseHibernateEao<M, PK> baseHibernateEao;

    protected BaseSrv() {
        initBaseHibernateEao();
    }

    public M get(PK id) {
        return getBaseHibernateEao().get(id);
    }

    public PK save(M model) {
        return getBaseHibernateEao().save(model);
    }

    public void saveOrUpdate(M model) {
        getBaseHibernateEao().saveOrUpdate(model);
    }

    public void update(M model) {
        getBaseHibernateEao().update(model);
    }

    public void merge(M model) {
        getBaseHibernateEao().merge(model);
    }

    public void delete(PK id) {
        getBaseHibernateEao().delete(id);
    }

    public void deleteObject(M model) {
        getBaseHibernateEao().deleteObject(model);
    }

    public int countAll() {
        return getBaseHibernateEao().countAll();
    }

    public List<M> listAll() {
        return getBaseHibernateEao().listAll();
    }

    public List<M> listAll(int pn, int pageSize) {
        return getBaseHibernateEao().listAll(pn,pageSize);
    }

    public List<M> pre(PK pk, int pn, int pageSize) {
        return getBaseHibernateEao().pre(pk,pn,pageSize);
    }

    public List<M> next(PK pk, int pn, int pageSize) {
        return getBaseHibernateEao().next(pk,pn,pageSize);
    }

    public boolean exists(PK id) {
        return getBaseHibernateEao().exists(id);
    }

    protected abstract void initBaseHibernateEao();

    protected BaseHibernateEao<M, PK> getBaseHibernateEao(){
        if(baseHibernateEao ==null){
            initBaseHibernateEao();
        }
        return baseHibernateEao;
    }
}
