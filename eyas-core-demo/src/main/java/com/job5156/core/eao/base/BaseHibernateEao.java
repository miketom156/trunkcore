package com.job5156.core.eao.base;

import com.google.common.collect.Lists;
import com.job5156.core.common.pagination.Page;
import com.job5156.core.common.pagination.PageUtil;
import com.job5156.core.common.pagination.PageV3;
import com.job5156.core.common.util.Reflections;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.Map.Entry;
import com.job5156.core.eao.base.PropertyFilter.*;
import com.job5156.core.eao.base.AliasCacheUtils.*;
/**
 * <p>
 * 数据处理类的基础父类，包含基本的增删改方法，所有dao的实现类都要继承此类
 * </p>
 *
 * Date:2015-4-16 下午2:50:26
 *
 * @param <M>
 *            持久实体类泛型，用来指定此dao负责的实体
 * @param <PK>
 *            主键泛型，用于指定主键的类型
 * @author leo
 * @version 1.0
 */
public abstract class BaseHibernateEao<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBaseEao<M, PK> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseHibernateEao.class);

    private final Class<M> entityClass;
    private final String HQL_LIST_ALL;
    private final String HQL_COUNT_ALL;
    private final String HQL_OPTIMIZE_PRE_LIST_ALL;
    private final String HQL_OPTIMIZE_NEXT_LIST_ALL;
    private String pkName = null;
    //允许自动封装的属性集合
    protected Map<String, MatchType> allowPropertyMap;

    public BaseHibernateEao() {
    	setAllowPropertyMap();
        this.entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Field[] fields = this.entityClass.getDeclaredFields();
        for(Field f : fields) {
            if(f.isAnnotationPresent(Id.class)) {
                this.pkName = f.getName();
            }
        }
        
        //TODO @Entity name not null
        HQL_LIST_ALL = "from " + this.entityClass.getSimpleName() + " order by " + pkName + " desc";
        HQL_OPTIMIZE_PRE_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where " + pkName + " > ? order by " + pkName + " asc";
        HQL_OPTIMIZE_NEXT_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where " + pkName + " < ? order by " + pkName + " desc";
        HQL_COUNT_ALL = " select count(*) from " + this.entityClass.getSimpleName();
    }
        
    protected SessionFactory sessionFactory;
    protected Session session;

//    @Required
//    public void setSessionFactory(SessionFactory sessionFactory) {
//    	this.sessionFactory = sessionFactory;
//    }
    public abstract void setSessionFactory(SessionFactory sessionFactory);
    public void setSession(Session session){
        this.session = session;
    }

    public Session getSession() {
        //事务必须是开启的(Required)，否则获取不到
        if(sessionFactory != null && sessionFactory.getCurrentSession() != null) {
            return sessionFactory.getCurrentSession();
        } else {
            return session;
        }
    }
    
    /**
     * 
    * @Title: setAllowPropertyList 
    * @Description: 子类重写该方法可以添加允许自动封装的字段集合
    * @return void    返回类型
    * @throws
     */
    public void setAllowPropertyMap() {
    	
    }

    @Override
    public PK save(M model) {
        return (PK) getSession().save(model);
    }

    @Override
    public void saveOrUpdate(M model) {
        getSession().saveOrUpdate(model);
    }

    @Override
    public void update(M model) {
        getSession().update(model);

    }

    @Override
    public void merge(M model) {
        getSession().merge(model);
    }

    @Override
    public void delete(PK id) {
        getSession().delete(this.get(id));

    }

    @Override
    public void deleteObject(M model) {
        getSession().delete(model);

    }

    @Override
    public boolean exists(PK id) {
        return get(id) != null;
    }

	@Override
    public M get(PK id) {
        if(id == null) {
            return null;
        } else {
            return (M) getSession().get(this.entityClass, id);
        }
    }
	
    @SuppressWarnings("unchecked")
    public M load(PK id) {
        Assert.notNull(id, "id is required");
        return (M) getSession().load(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<M> get(PK[] ids) {
        Assert.notEmpty(ids, "ids must not be empty");
        String hql = "from " + entityClass.getName() + " as model where model.id in(:ids)";
        return getSession().createQuery(hql).setParameterList("ids", ids).list();
    }


    @Override
    public int countAll() {
        Long total = aggregate(HQL_COUNT_ALL);
        return total.intValue();
    }


    @Override
    public List<M> listAll() {
        return list(HQL_LIST_ALL);
    }

    @Override
    public List<M> listAll(int pn, int pageSize) {
        return list(HQL_LIST_ALL, pn, pageSize, null);
    }
    
    @Override
    public List<M> pre(PK pk, int pn, int pageSize) {
        if(pk == null) {
            return list(HQL_LIST_ALL, pn, pageSize, null);
        }
        //倒序，重排
        List<M> result = list(HQL_OPTIMIZE_PRE_LIST_ALL, 1, pageSize, new Object[]{pk});
        Collections.reverse(result);
        return result;
    }
    @Override
    public List<M> next(PK pk, int pn, int pageSize) {
        if(pk == null) {
            return list(HQL_LIST_ALL, pn, pageSize ,null);
        }
        return list(HQL_OPTIMIZE_NEXT_LIST_ALL, 1, pageSize, new Object[]{pk});
    }

    @Override
    public void flush() {
        getSession().flush();
    }

    @Override
    public void clear() {
        getSession().clear();
    }

    protected long getIdResult(String hql, Object... paramlist) {
        long result = -1;
        List<?> list = list(hql, paramlist);
        if (list != null && list.size() > 0) {
            return ((Number) list.get(0)).longValue();
        }
        return result;
    }

    protected List<M> listSelf(final String hql, final int pn, final int pageSize, final Object... paramlist) {
        return this.<M> list(hql, pn, pageSize, paramlist);
    }


    /**
     * for in
     */
    protected <T> List<T> listWithIn(final String hql,final int start, final int length, final Map<String, Collection<?>> map, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);
        for (Entry<String, Collection<?>> e : map.entrySet()) {
            query.setParameterList(e.getKey(), e.getValue());
        }
        if (start > -1 && length > -1) {
            query.setMaxResults(length);
            if (start != 0) {
                query.setFirstResult(start);
            }
        }
        List<T> results = query.list();
        return results;
    }

    protected <T> List<T> list(final String hql, final int pn, final int pageSize, final Object[] paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);
        if (pn > -1 && pageSize > -1) {
            query.setMaxResults(pageSize);
            int start = PageUtil.getPageStart(pn, pageSize);
            if (start != 0) {
                query.setFirstResult(start);
            }
        }
        if (pn < 0) {
            query.setFirstResult(0);
        }
        List<T> results = query.list();
        return results;
    }

    /**
     * 返回LIST通过NAME传值
     * @param hql
     * @param pn
     * @param pageSize
     * @param paraMap
     * @param <T>
     * @return
     */
    public <T> List<T> listByParaMap(final String hql, final int pn, final int pageSize,  final Map<String,Object> paraMap) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paraMap);
        if (pn > -1 && pageSize > -1) {
            query.setMaxResults(pageSize);
            int start = PageUtil.getPageStart(pn, pageSize);
            if (start != 0) {
                query.setFirstResult(start);
            }
        }
        if (pn < 0) {
            query.setFirstResult(0);
        }
        List<T> results = query.list();
        return results;
    }

    /**
     * 根据查询条件返回唯一一条记录
     */
    protected <T> T unique(final String hql, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);
        return (T) query.setMaxResults(1).uniqueResult();
    }

    public <T> T unique(final String hql, final Map<String,Object> paraMap) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paraMap);
        return (T) query.setMaxResults(1).uniqueResult();
    }

       /**
        * 
        * for in
        */
    protected <T> T aggregate(final String hql, final Map<String, Collection<?>> map, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        if (paramlist != null) {
            setParameters(query, paramlist);
            for (Entry<String, Collection<?>> e : map.entrySet()) {
                query.setParameterList(e.getKey(), e.getValue());
            }
        }

        return (T) query.uniqueResult();
    }

    public <T> T aggregate(final String hql, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);

        //由于这个方法主要用于获取统计数量，泛型一般为Number，但是有可能会造成NullPointerException，所以这里直接当null的时候返回0，注释掉的是原来的代码
        Object result = query.uniqueResult();
        if(result != null) {
            return (T) result;
        } else {
            return (T) new Integer(0);
        }
//        return (T) query.uniqueResult();

    }

    public <T> T aggregate(final String hql,  final Map<String,Object> paraMap ) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paraMap);
        Object result = query.uniqueResult();
        if(result != null) {
            return (T) result;
        } else {
            return (T) new Integer(0);
        }

    }


    /**
     * 执行批处理语句.如 之间insert, update, delete 等.
     */
    protected int execteBulk(final String hql, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);
        Object result = query.executeUpdate();
        return result == null ? 0 : ((Integer) result).intValue();
    }
    
    protected int execteNativeBulk(final String natvieSQL, final Object... paramlist) {
        Query query = getSession().createSQLQuery(natvieSQL);
        setParameters(query, paramlist);
        Object result = query.executeUpdate();
        return result == null ? 0 : ((Integer) result).intValue();
    }

    protected <T> List<T> list(final String sql) {
        return list(sql, null);
    }

    protected <T> List<T> list(final String sql, final Object[] paramlist) {
        return list(sql, -1, -1, paramlist);
    }

    public <T> T aggregateBySql(final String sql, final Object... paramlist) {
        String countSql = prepareCountHql(sql);
        Query query = getSession().createSQLQuery(countSql);
        setParameters(query, paramlist);

        Object result = query.uniqueResult();
        if (result != null) {
            return (T) result;
        } else {
            return (T) new Integer(0);
        }
    }

    public <T> T aggregateBySql(final String sql, final Map<String,Object> paraMap) {
        String countSql = prepareCountHql(sql);
        Query query = getSession().createSQLQuery(countSql);
        setParameters(query, paraMap);

        Object result = query.uniqueResult();
        if (result != null) {
            return (T) result;
        } else {
            return (T) new Integer(0);
        }
    }


    public <T> PageV3<T> findPageByNative(final String nativeSQL, PageV3 page,
                                    final List<Entry<String, Class<?>>> entityList,
                                    final List<Entry<String, Type>> scalarList, final Object... paramlist) {

        SQLQuery query = getSession().createSQLQuery(nativeSQL); 
        if (entityList != null) {
            for (Entry<String, Class<?>> entity : entityList) {
                query.addEntity(entity.getKey(), entity.getValue());
            }
        }
        if (scalarList != null) {
            for (Entry<String, Type> entity : scalarList) {
                query.addScalar(entity.getKey(), entity.getValue());
            }
        }

        setParameters(query, paramlist);
        int tatalCount = (this.<Number>aggregateBySql(nativeSQL,paramlist)).intValue();
        page.setTotalCount(tatalCount);

        if (page.getPageNo() > -1 && page.getPageSize() > -1) {
            query.setMaxResults(page.getPageSize());
            int start = PageUtil.getPageStart(page.getPageNo(), page.getPageSize());
            if (start != 0) {
                query.setFirstResult(start);
            }
        }

        List<T> result = query.list();
        page.setResult(result);

        return page;
    }

    /**
     * 通过包含:name的原生SQL查询并返回PAGE
     * @param nativeSQL 原生SQL
     * @param page
     * @param entityList 实体LIST
     * @param scalarList 标量LIST（指定返回具体的字段）
     * @param paraMap   按name传值的map
     * @param <T>
     * @return
     */
    public <T> PageV3<T> findPageByNative(final String nativeSQL, PageV3 page,
                                          final List<Entry<String, Class<?>>> entityList,
                                          final List<Entry<String, Type>> scalarList, final Map<String,Object> paraMap) {

        SQLQuery query = getSession().createSQLQuery(nativeSQL);
        if (entityList != null) {
            for (Entry<String, Class<?>> entity : entityList) {
                query.addEntity(entity.getKey(), entity.getValue());
            }
        }
        if (scalarList != null) {
            for (Entry<String, Type> entity : scalarList) {
                query.addScalar(entity.getKey(), entity.getValue());
            }
        }

        setParameters(query, paraMap);
        int tatalCount = (this.<Number>aggregateBySql(nativeSQL,paraMap)).intValue();
        page.setTotalCount(tatalCount);

        if (page.getPageNo() > -1 && page.getPageSize() > -1) {
            query.setMaxResults(page.getPageSize());
            int start = PageUtil.getPageStart(page.getPageNo(), page.getPageSize());
            if (start != 0) {
                query.setFirstResult(start);
            }
        }

        List<T> result = query.list();
        page.setResult(result);

        return page;
    }

    public <T> List<T> listByNative(final String nativeSQL, final int pn, final int pageSize,
            final List<Entry<String, Class<?>>> entityList, 
            final List<Entry<String, Type>> scalarList, final Object... paramlist) {

        SQLQuery query = getSession().createSQLQuery(nativeSQL);
        if (entityList != null) {
            for (Entry<String, Class<?>> entity : entityList) {
                query.addEntity(entity.getKey(), entity.getValue());
            }
        }
        if (scalarList != null) {
            for (Entry<String, Type> entity : scalarList) {
                query.addScalar(entity.getKey(), entity.getValue());
            }
        }

        setParameters(query, paramlist);

        if (pn > -1 && pageSize > -1) {
            query.setMaxResults(pageSize);
            int start = PageUtil.getPageStart(pn, pageSize);
            if (start != 0) {
                query.setFirstResult(start);
            }
        }
        List<T> result = query.list();
        return result;
    }

    public <T> T aggregateByNative(final String natvieSQL, final List<Entry<String, Type>> scalarList, final Object... paramlist) {
        SQLQuery query = getSession().createSQLQuery(natvieSQL);
        if (scalarList != null) {
            for (Entry<String, Type> entity : scalarList) {
                query.addScalar(entity.getKey(), entity.getValue());
            }
        }

        setParameters(query, paramlist);

        Object result = query.uniqueResult();
        if(result != null) {
            return (T) result;
        } else {
            return (T) new Integer(0);
        }
    }
    
    
    public <T> T aggregateByCriteria(ConditionQuery query){
        Criteria criteria = getSession().createCriteria(this.entityClass);
        query.build(criteria);
        Integer totalCount = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        criteria.setProjection(null);
        return (T) totalCount;
    }

    public <T> List<T> list(ConditionQuery query, OrderBy orderBy, final int pn, final int pageSize) {
        Criteria criteria = getSession().createCriteria(this.entityClass);
        query.build(criteria);
        orderBy.build(criteria);
        int start = PageUtil.getPageStart(pn, pageSize);
        if(start != 0) {
            criteria.setFirstResult(start);
        }
        criteria.setMaxResults(pageSize);
        return criteria.list();
    }

    public <T> List<T> list(ConditionQuery query, OrderBy orderBy) {
        Criteria criteria = getSession().createCriteria(this.entityClass);
        query.build(criteria);
        if (orderBy != null) {
            orderBy.build(criteria);
        }
        return criteria.list();
    }
    /**
     * 通过 Criteria 对象查询并直接返回分页对象page
     * @param query
     * @param orderBy
     * @param pn
     * @param pageSize
     * @return Page
     */
    public <T> Page<T> findPage(ConditionQuery query, OrderBy orderBy, final int pn, final int pageSize) {
        Criteria criteria = getSession().createCriteria(this.entityClass);
        query.build(criteria);
        
        int totalCount = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        criteria.setProjection(null);
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        orderBy.build(criteria);
        
        int start = PageUtil.getPageStart(pn, pageSize);
        if(start != 0) {
            criteria.setFirstResult(start);
        }
        criteria.setMaxResults(pageSize);
        return  PageUtil.getPage(totalCount ,pn,criteria.list(), pageSize);
    }

    public <T> List<T> list(Criteria criteria) {
        return criteria.list();
    }

    public <T> T unique(Criteria criteria) {
        return (T) criteria.uniqueResult();
    }

    public <T> List<T> list(DetachedCriteria criteria) {
        return list(criteria.getExecutableCriteria(getSession()));
    }

    public <T> T unique(DetachedCriteria criteria) {
        return (T) unique(criteria.getExecutableCriteria(getSession()));
    }

    @SuppressWarnings("rawtypes")
	protected void setParameters(Query query, Object[] paramlist) {
        if (paramlist != null) {
            for (int i = 0; i < paramlist.length; i++) {
                if(paramlist[i] instanceof Date) {
                    //TODO 难道这是bug 使用setParameter不行？？
                    query.setTimestamp(i, (Date)paramlist[i]);
                } else if(paramlist[i] instanceof Collection) {
                    query.setParameterList("params", (Collection)paramlist[i]);
                } else {
                    query.setParameter(i, paramlist[i]);
                }
            }
        }
    }

    /**
     * 通过name传入参数
     * @param query
     * @param paraMap
     */
    @SuppressWarnings("rawtypes")
    protected void setParameters(Query query, Map<String,Object> paraMap) {
        if (paraMap != null) {
            String[] names = query.getNamedParameters();
            for (String name : names) {
                if (paraMap.get(name) instanceof Date) {
                    query.setTimestamp(name,(Date) paraMap.get(name));
                }else if(paraMap.get(name) instanceof Collection) {
                    query.setParameterList(name,(Collection)paraMap.get(name));
                } else {
                    query.setParameter(name, paraMap.get(name));
                }
            }
        }
    }
    
    /**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public <X> List<X> find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <X> List<X> find(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).list();
	}
	
	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public List<M> find(AliasCacheUtils aliasUtils, final Criterion... criterions) {
		Criteria criteria = createCriteria(criterions);
    	criteria = builderAliasCache(criteria, aliasUtils);
		return criteria.list();
	}
	
	// -- 属性过滤条件(FieldFilter)查询函数 --//  
	  
    /** 
     * 按属性查找对象列表,支持多种匹配方式. 
     *  
     * @param matchType 
     *            匹配方式,目前支持的取值见FieldFilter的MatcheType enum. 
     */  
    public List<M> findBy(final String fieldName, final Object[] value, final MatchType matchType) {
    	AliasCacheUtils aliasUtils = new AliasCacheUtils();
        Criterion criterion = buildFieldFilterCriterion(new PropertyFilter(fieldName, matchType, value), aliasUtils);  
        return find(aliasUtils, criterion);
    }
    
    /**
     * 
    * @Title: findBy 
    * @Description: 按属性查找对象列表,支持多种匹配方式（支持排序） 
    * @param @param fieldName
    * @param @param value
    * @param @param order  排序对象
    * @param @param matchType
    * @param @return    设定文件 
    * @return List<T>    返回类型 
    * @throws
     */
    public List<M> findBy(final String fieldName, final Object[] value, final MatchType matchType, final OrderCondition... orders) {
        return findBy(fieldName, value, matchType, 0, orders);
    }
    
    public List<M> findBy(final String fieldName, final Object[] value, final MatchType matchType, int maxResults, final OrderCondition... orders) {
    	AliasCacheUtils aliasUtils = new AliasCacheUtils();
    	Criterion criterion = buildFieldFilterCriterion(new PropertyFilter(fieldName, matchType, value), aliasUtils);  
        return find(aliasUtils, orders, 0, criterion);
    }

	@SuppressWarnings("unchecked")
	public List<M> find(AliasCacheUtils aliasUtils, final OrderCondition[] orders, int maxResults, final Criterion... criterions) {
    	Criteria criteria = createCriteria(criterions);
    	if(orders != null)
    		criteria = builderOrder(criteria, aliasUtils, orders);
    	criteria = builderAliasCache(criteria, aliasUtils);
    	if(maxResults > 0) criteria.setMaxResults(maxResults);
		return criteria.list();
	}
  
    /** 
     * 按属性过滤条件列表查找对象列表. 
     */  
    public List<M> find(List<PropertyFilter> filters) {
    	AliasCacheUtils aliasUtils = new AliasCacheUtils();
        Criterion[] criterions = buildFieldFilterCriterions(filters, aliasUtils);  
        return find(aliasUtils, criterions);  
    }
    
    /** 
     * 按属性过滤条件列表查找对象列表（支持排序）.
     */  
    public List<M> find(List<PropertyFilter> filters, final OrderCondition... orders) {
        return find(filters, 0, orders);
    }
    
    public List<M> find(List<PropertyFilter> filters, int maxResults, final OrderCondition... orders) {
    	AliasCacheUtils aliasUtils = new AliasCacheUtils();
        Criterion[] criterions = buildFieldFilterCriterions(filters, aliasUtils);  
        return find(aliasUtils, orders, maxResults, criterions);
    }
    
    /**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public <X> X findUnique(final String hql, final Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <X> X findUnique(final String hql, final Map<String, ?> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}
    
    /**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		
		return criteria;
	}
	
	/**
	 * 
	* @Title: createDetachedCriteria 
	* @Description: 根据Criterion条件创建离线Criteria.
	* @param @param criterions
	* @param @return    设定文件 
	* @return DetachedCriteria    返回类型 
	* @throws
	 */
	public DetachedCriteria createDetachedCriteria(final Criterion... criterions) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		for (Criterion c : criterions) {
			dc.add(c);
		}
		return dc;
	}
    
    /**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public Query createQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

    /**
	 * 根据查询HQL与参数列表创建Query对象.
	 *
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 *
	 * @param values
	 *            List参数,按顺序绑定.
	 */
	public Query createQuery(final String queryString, List values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.size(); i++) {
				query.setParameter(i, values.get(i));
			}
		}
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public Query createQuery(final String queryString, final Map<String, ?> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}
  
    /** 
     * 按HQL分页查询. 
     *  
     * @param page 
     *            分页参数.不支持其中的orderBy参数. 
     * @param hql 
     *            hql语句. 
     * @param values 
     *            数量可变的查询参数,按顺序绑定. 
     *  
     * @return 分页查询结果, 附带结果列表及所有查询时的参数. 
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public PageV3<M> findPage(final PageV3<M> page, final String hql, final Object... values) {  
        Assert.notNull(page, "page不能为空");  
  
        Query q = createQuery(hql, values);  
        
        if (page.isAutoCount()) {  
            long totalCount = countHqlResult(hql, values);
            page.setTotalCount(totalCount);  
        } else {
        	q.setMaxResults((int)page.getTotalCount());
        	List result = q.list();
        	page.setTotalCount(result.size());
        }
  
        setPageParameter(q, page);  
        List result = q.list();  
        page.setResult(result);  
        return page;  
    }  
  
    /** 
     * 按HQL分页查询. 
     *  
     * @param page 
     *            分页参数. 
     * @param hql 
     *            hql语句. 
     * @param values 
     *            命名参数,按名称绑定. 
     *  
     * @return 分页查询结果, 附带结果列表及所有查询时的参数. 
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public PageV3<M> findPage(final PageV3<M> page, final String hql,  
            final Map<String, ?> values) {  
        Assert.notNull(page, "page不能为空");  
  
        Query q = createQuery(hql, values);  
  
        if (page.isAutoCount()) {  
            long totalCount = countHqlResult(hql, values);  
            page.setTotalCount(totalCount);  
        }  
  
        setPageParameter(q, page);  
  
        List result = q.list();  
        page.setResult(result);  
        return page;  
    }  
  
    /** 
     * 按Criteria分页查询. 
     *  
     * @param page 
     *            分页参数. 
     * @param criterions 
     *            数量可变的Criterion. 
     *  
     * @return 分页查询结果.附带结果列表及所有查询时的参数. 
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public PageV3<M> findPage(final PageV3<M> page, AliasCacheUtils aliasUtils, final Criterion... criterions) {  
        Assert.notNull(page, "page不能为空");  
  
        Criteria c = createCriteria(criterions);
        c = builderPageOrder(c, page, aliasUtils);
        c = builderAliasCache(c, aliasUtils);
  
        if (page.isAutoCount()) {  
            int totalCount = countCriteriaResult(c);  
            page.setTotalCount(totalCount);  
        } else {
        	c.setMaxResults((int)page.getTotalCount());
        	List result = c.list();
        	page.setTotalCount(result.size());
        }
  
        setPageParameter(c, page);
        
        List result = c.list();
        page.setResult(result);
        
        return page;
    }
    
    /**
     * 按Criteria查询记录总数
    * @Title: findByCountAll 
    * @Description: 按Criteria查询记录总数
    * @param @param aliasUtils
    * @param @param criterions
    * @param @return    设定文件 
    * @return Integer    返回类型 
    * @throws
     */
    public Integer findByCountAll(AliasCacheUtils aliasUtils, final Criterion... criterions) {
    	Criteria c = createCriteria(criterions);
        c = builderAliasCache(c, aliasUtils);
        
        return ((Number)c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
    
    /** 
     * 按属性过滤条件列表分页查找对象. 
     */  
    public PageV3<M> findPage(final PageV3<M> page,  
            final List<PropertyFilter> filters) {
    	AliasCacheUtils aliasUtils = new AliasCacheUtils();
        Criterion[] criterions = buildFieldFilterCriterions(filters, aliasUtils);
        return findPage(page, aliasUtils, criterions);
    }
    
    /** 
     * 按属性过滤条件统计数量. 
     */ 
    public Integer findByCountAll(final List<PropertyFilter> filters) {
    	AliasCacheUtils aliasUtils = new AliasCacheUtils();
        Criterion[] criterions = buildFieldFilterCriterions(filters, aliasUtils);
        return findByCountAll(aliasUtils, criterions);
    }
    
    /**
     * 
    * @Title: builderAliasCache 
    * @Description: 将别名缓存中的别名构造到 Criteria去
    * @param @param criteria
    * @param @param aliasUtils
    * @param @return    设定文件 
    * @return Criteria    返回类型 
    * @throws
     */
    protected Criteria builderAliasCache(Criteria criteria, AliasCacheUtils aliasUtils) {
    	Collection<PropertyAlias> aliasList = aliasUtils.getAliasCache().values();
        for(PropertyAlias alias : aliasList) {
        	criteria.createAlias(alias.getPath(), alias.getName());
    	}
        return criteria;
    }
    
    /**
     * 
    * @Title: builderPageOfder 
    * @Description: 生成分页中的排序条件 
    * @param @param c
    * @param @param page
    * @param @param aliasUtils
    * @param @return    设定文件 
    * @return Criteria    返回类型 
    * @throws
     */
    protected Criteria builderPageOrder(final Criteria c, final PageV3<M> page, AliasCacheUtils aliasUtils) {
    	if (page.isOrderBySetted()) {
            String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
            String[] orderArray = StringUtils.split(page.getOrder(), ',');
  
            Assert.isTrue(orderByArray.length == orderArray.length,  
                    "分页多重排序参数中,排序字段与排序方向的个数不相等");  
            
            List<OrderCondition> orders = Lists.newArrayList();
            for (int i = 0; i < orderByArray.length; i++) {
            	OrderCondition.Order order = PageV3.ASC.equals(orderArray[i]) == true ? OrderCondition.Order.ASC : OrderCondition.Order.DESC;
            	orders.add(new OrderCondition(order, orderByArray[i]));
            }
            builderOrder(c, aliasUtils, orders.toArray(new OrderCondition[0]));
        }
    	return c;
    }
    
    /**
     * 
    * @Title: builderOrder 
    * @Description: 根据排序列表生成排序别名，并产生别名缓存
    * @param @param c
    * @param @param aliasUtils
    * @param @param orders
    * @param @return    设定文件 
    * @return Criteria    返回类型 
    * @throws
     */
    protected Criteria builderOrder(final Criteria c, AliasCacheUtils aliasUtils, OrderCondition... orders) {
    	for(OrderCondition order : orders) {
    		String propertyName = aliasUtils.filterPropertyByAlias(order.getProperty());
        	
            if (order.getOrder() == OrderCondition.Order.ASC) {
                c.addOrder(Order.asc(propertyName));
            } else {  
                c.addOrder(Order.desc(propertyName));
            }
    	}
    	return c;
    }
    
    /** 
     * 设置分页参数到Query对象,辅助函数. 
     */  
    protected Query setPageParameter(final Query q, final PageV3<M> page) {  
        // hibernate的firstResult的序号从0开始  
        q.setFirstResult(page.getFirst() - 1);  
        q.setMaxResults(page.getPageSize());  
        return q;  
    }  
  
    /** 
     * 设置分页参数到Criteria对象,辅助函数. 
     */  
    protected Criteria setPageParameter(final Criteria c, final PageV3<M> page) {  
        // hibernate的firstResult的序号从0开始  
        c.setFirstResult(page.getFirst() - 1);
        c.setMaxResults(page.getPageSize());
        return c;
    }
    
    /** 
     * 执行count查询获得本次Hql查询所能获得的对象总数. 
     *  
     * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询. 
     */  
    protected long countHqlResult(final String hql, final Object... values) {
		String countHql = prepareCountHql(hql);
		
		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}
  
    /** 
     * 执行count查询获得本次Hql查询所能获得的对象总数. 
     *  
     * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询. 
     */  
    protected long countHqlResult(final String hql, final Map<String, ?> values) {
		String countHql = prepareCountHql(hql);

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}
    
    private String prepareCountHql(String orgHql) {
		String fromHql = orgHql;
		//将大写替换成小写匹配
		fromHql = fromHql.replace("FROM", "from");
		fromHql = fromHql.replaceAll("ORDER BY", "order by");
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;
		return countHql;
	}
    
    /** 
     * 执行count查询获得本次Criteria查询所能获得的对象总数. 
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    protected int countCriteriaResult(final Criteria c) {  
        CriteriaImpl impl = (CriteriaImpl) c;  
  
        // 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作  
        Projection projection = impl.getProjection();  
        ResultTransformer transformer = impl.getResultTransformer();  
  
        List<CriteriaImpl.OrderEntry> orderEntries = null;  
        try {  
            orderEntries = (List) Reflections.getFieldValue(impl, "orderEntries");
            Reflections  .setFieldValue(impl, "orderEntries", new ArrayList());  
        } catch (Exception e) {  
        	LOGGER.error("不可能抛出的异常:{}", e.getMessage());  
        } 
  
        // 执行Count查询  
        int totalCount = ((Long) c.setProjection(Projections.rowCount())  
                .uniqueResult()).intValue();  
  
        // 将之前的Projection,ResultTransformer和OrderBy条件重新设回去  
        c.setProjection(projection);  
  
        if (projection == null) {  
            c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);  
        }  
        if (transformer != null) {  
            c.setResultTransformer(transformer);  
        }  
        try {  
            Reflections.setFieldValue(impl, "orderEntries", orderEntries);  
        } catch (Exception e) {  
        	LOGGER.error("不可能抛出的异常:{}", e.getMessage());  
        }  
  
        return totalCount;  
    }  
  
    /** 
     * 按属性条件列表创建Criterion数组,辅助函数. 
     */  
    protected Criterion[] buildFieldFilterCriterions(  
            final List<PropertyFilter> filters, AliasCacheUtils aliasUtils) {  
        List<Criterion> criterionList = new ArrayList<Criterion>();
        for (PropertyFilter filter : filters) {
        	
            if(allowPropertyMap != null) {
            	MatchType matchType = allowPropertyMap.get(filter.getFieldName());
            	
            	if(matchType == null || matchType != filter.getMatchType())
            		continue;
            }
        	
            if (!filter.isMulti()) {  
                criterionList.add(buildFieldFilterCriterion(  
                        filter.getFieldName(), filter.getValues(),  
                        filter.getOtherField(), filter.getMatchType(), aliasUtils));  
            } else {  
                criterionList.add(buildMultiFieldFilter(filter, aliasUtils));
            }  
        }  
        return criterionList.toArray(new Criterion[criterionList.size()]);  
    }  
  
    protected Criterion buildMultiFieldFilter(PropertyFilter filter, AliasCacheUtils aliasUtils) {
        Criterion last = null;
        
        for (Iterator<PropertyFilter> it = filter.iterator(); it.hasNext();) {  
            PropertyFilter ff = it.next();  
            Criterion c = buildFieldFilterCriterion(ff, aliasUtils);  
            if (ff.isAnd()) {  
                last = new SimpleLogicalExpression(last, c, "and");  
            } else if (ff.isOr()) {  
                last = new SimpleLogicalExpression(last, c, "or");  
            } else if (ff.isRoundAnd()) {  
                last = Restrictions.and(last, c);  
            } else if (ff.isRoundOr()) {  
                last = Restrictions.or(last, c);  
            } else {  
                last = c;  
            }  
        }  
        return last;  
    }  
  
    /** 
     * 按属性条件参数创建Criterion,辅助函数. 
     */  
    protected Criterion buildFieldFilterCriterion(final String fieldName,  
            final Object[] fieldValues, String otherField,  
            final MatchType matchType, AliasCacheUtils aliasUtils) {  
        Assert.hasText(fieldName, "fieldName不能为空");  
        Criterion criterion = null;
        
        String propertyName = aliasUtils.filterPropertyByAlias(fieldName);
        String otherPropertyName = aliasUtils.filterPropertyByAlias(otherField);
        String checkFieldName = aliasUtils.filterFieldName(fieldName);
        
        try {  
            // 根据MatchType构造criterion  
            if (MatchType.EQ.equals(matchType)) {  
                criterion = Restrictions.eq(propertyName, fieldValues[0]);  
            } else if (MatchType.LE.equals(matchType)) {
                criterion = Restrictions.le(propertyName, fieldValues[0]);  
            } else if (MatchType.LT.equals(matchType)) {  
                criterion = Restrictions.lt(propertyName, fieldValues[0]);  
            } else if (MatchType.GE.equals(matchType)) {  
                criterion = Restrictions.ge(propertyName, fieldValues[0]);  
            } else if (MatchType.GT.equals(matchType)) {  
                criterion = Restrictions.gt(propertyName, fieldValues[0]);  
            } else if (MatchType.NE.equals(matchType)) {  
                criterion = Restrictions.ne(propertyName, fieldValues[0]);  
            } else if (MatchType.EQF.equals(matchType)) {  
                criterion = Restrictions.eqProperty(propertyName, otherPropertyName);  
            } else if (MatchType.LEF.equals(matchType)) {  
                criterion = Restrictions.leProperty(propertyName, otherPropertyName);  
            } else if (MatchType.LTF.equals(matchType)) {  
                criterion = Restrictions.ltProperty(propertyName, otherPropertyName);  
            } else if (MatchType.GEF.equals(matchType)) {  
                criterion = Restrictions.geProperty(propertyName, otherPropertyName);  
            } else if (MatchType.GTF.equals(matchType)) {  
                criterion = Restrictions.gtProperty(propertyName, otherPropertyName);  
            } else if (MatchType.NEF.equals(matchType)) {  
                criterion = Restrictions.neProperty(propertyName, otherPropertyName);  
            } else if (MatchType.LIKE.equals(matchType)) {  
            	criterion = Restrictions.like(propertyName, ObjectUtils.toString(fieldValues[0]), MatchMode.EXACT);
            } else if (MatchType.LIKESTART.equals(matchType)) {  
            	criterion = Restrictions.like(propertyName, ObjectUtils.toString(fieldValues[0]), MatchMode.START); 
            } else if (MatchType.LIKEEND.equals(matchType)) {  
            	criterion = Restrictions.like(propertyName, ObjectUtils.toString(fieldValues[0]), MatchMode.END); 
            } else if (MatchType.LIKEANYWHERE.equals(matchType)) {  
            	criterion = Restrictions.like(propertyName, ObjectUtils.toString(fieldValues[0]), MatchMode.ANYWHERE);
            } else if (MatchType.LIKEISTART.equals(matchType)){
            	criterion = Restrictions.sqlRestriction(checkFieldName + " LIKE '%" + fieldValues[0] + "'");
            } else if (MatchType.LIKEIEND.equals(matchType)){
            	criterion = Restrictions.sqlRestriction(checkFieldName + " LIKE '" + fieldValues[0] + "%'");
            } else if (MatchType.LIKEIANYWHERE.equals(matchType)){
            	criterion = Restrictions.sqlRestriction(checkFieldName + " LIKE '%" + fieldValues[0] + "%'");
            } else if (MatchType.BETWEEN.equals(matchType)) {  
                criterion = Restrictions.between(propertyName, fieldValues[0], fieldValues[1]);  
            } else if (MatchType.IN.equals(matchType)) {
                criterion = Restrictions.in(propertyName, fieldValues);
            } else if (MatchType.ISNULL.equals(matchType)) {
                criterion = Restrictions.isNull(propertyName);
            }
        } catch (Exception e) {  
            throw Reflections.convertReflectionExceptionToUnchecked(e);  
        }  
        return criterion;  
    }  
  
    /** 
     * 按属性条件参数创建Criterion,辅助函数. 
     */  
    protected Criterion buildFieldFilterCriterion(PropertyFilter filter, AliasCacheUtils aliasUtils) {  
        return buildFieldFilterCriterion(filter.getFieldName(),  
                filter.getValues(), filter.getOtherField(),  
                filter.getMatchType(), aliasUtils);  
    }
}
