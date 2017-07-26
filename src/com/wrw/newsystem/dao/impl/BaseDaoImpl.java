package com.wrw.newsystem.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.wrw.newsystem.dao.BaseDao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author wrw
 *
 */

public class BaseDaoImpl<T> implements Serializable, BaseDao<T> {
	protected transient Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> persistentClass;

    protected Class<T> getCurClass() {
        if (persistentClass == null) {
            this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return persistentClass;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected StatelessSession getStatelessSession() {
        return sessionFactory.openStatelessSession();
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#flush()
	 */
    @Override
	public void flush() {
        getSession().flush();
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#evict(T)
	 */
    @Override
	public void evict(T modele) {
        getSession().evict(modele);
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#get(long)
	 */
    @Override
	public T get(long id) throws DataAccessException {
        log.debug("DAO:Get modele " + getCurClass().getSimpleName() + ":Id=" + id);
        return (T) getSession().get(getCurClass(), id);
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#save(T)
	 */
    @Override
	public void save(T modele) throws DataAccessException {
        log.debug("DAO:Save modele " + modele.getClass().getSimpleName());
        getSession().save(modele);
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#update(T)
	 */
    @Override
	public void update(T modele) throws DataAccessException {
        log.debug("DAO:Update modele " + modele.getClass().getSimpleName());
        getSession().clear();
        getSession().update(modele);
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#saveOrUpdate(T)
	 */
    @Override
	public void saveOrUpdate(T modele) throws DataAccessException {
        log.debug("DAO:Sava or Update modele " + modele.getClass().getSimpleName());
        getSession().clear();
        getSession().saveOrUpdate(modele);
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#delete(T)
	 */
    @Override
	public void delete(T modele) throws DataAccessException {
        log.debug("DAO:delete modele " + getCurClass().getSimpleName());
        getSession().delete(modele);
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#deleteById(long)
	 */
    @Override
	public void deleteById(long id) throws DataAccessException {
        log.debug("DAO:delete modele " + getCurClass().getSimpleName() + ":Id=" + id);
        String queryString = "delete from " + getCurClass().getSimpleName() + " where id=" + id;
        this.excute(queryString);
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#excute(java.lang.String)
	 */
    @Override
	public int excute(String queryString) throws DataAccessException {
        log.debug("DAO:Excute HQL update :" + queryString);
        Query query = getSession().createQuery(queryString);
        return query.executeUpdate();
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#find(java.lang.String)
	 */
    @Override
	public List<T> find(String queryString) throws DataAccessException {
        log.debug("DAO:Running HQL query :" + queryString);
        Query query = getSession().createQuery(queryString);
        query.setCacheable(true);
        return query.list();
    }

    private Query createQuery(String queryString, Map<String, Object> params, int startRow, int pageSize) {
        Query query = getSession().createQuery(queryString);
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String paramName = entry.getKey();
                Object obj = entry.getValue();
                log.info("DAO:set param:" + paramName + " with value:" + obj);
                if (obj instanceof List) {
                    query.setParameterList(paramName, (Collection) obj);
                } else if (obj instanceof Object[]) {
                    query.setParameterList(paramName, (Object[]) obj);
                } else {
                    query.setParameter(paramName, obj);
                }
            }
        }
        query.setCacheable(true);
        if (pageSize != -1) {
            query.setFirstResult(startRow).setMaxResults(pageSize);
        }
        return query;
    }

    private Query createQuery(String queryString) {
        return createQuery(queryString, null, 0, -1);
    }

    private Query createQuery(String queryString, Map<String, Object> params) {
        return createQuery(queryString, params, 0, -1);
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#find(java.lang.String, int, int)
	 */
    @Override
	public List<T> find(String queryString, int startRow, int pageSize) throws DataAccessException {
        log.debug("DAO:Running HQL query by page:" + queryString);
        Query query = createQuery(queryString, null, startRow, pageSize);
        return query.list();
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#getTotalCount(java.lang.String)
	 */
    @Override
	public int getTotalCount(String queryString) throws DataAccessException {
        return getTotalCount(queryString, null);
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#getTotalCount(java.lang.String, java.util.Map)
	 */
    @Override
	public int getTotalCount(String queryString, Map<String, Object> params) throws DataAccessException {
        log.debug("DAO:Running HQL query for total count of records :" + queryString);
        queryString = "select count(t.id) " + queryString;
        Query query;
        if (params != null) {
            query = createQuery(queryString, params);
        } else {
            query = createQuery(queryString);
        }
        return ((Long) query.uniqueResult()).intValue();
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#findWithSelect(java.lang.String)
	 */
    @Override
	public List findWithSelect(String queryString) throws DataAccessException {
        log.debug("DAO:Running HQL query with selections :" + queryString);
        Query query = createQuery(queryString);
        return query.list();
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#findWithSelect(java.lang.String, java.util.Map)
	 */
    @Override
	public List findWithSelect(String queryString, Map<String, Object> params) throws DataAccessException {
        log.debug("DAO:Running HQL query with parameters:" + queryString);
        Query query = createQuery(queryString, params);
        return query.list();
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#findWithSelect(java.lang.String, java.util.Map, int, int)
	 */
    @Override
	public List findWithSelect(String queryString, Map<String, Object> params, int startRow, int pageSize) throws DataAccessException {
        log.debug("DAO:Running HQL query by page :" + queryString);
        Query query = createQuery(queryString, params, startRow, pageSize);
        return query.list();
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#find(java.lang.String, java.util.Map)
	 */
    @Override
	public List<T> find(String queryString, Map<String, Object> params) throws DataAccessException {
        log.debug("DAO:Running HQL query with parameters: " + queryString);
        Query query = createQuery(queryString, params);
        return query.list();
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#find(java.lang.String, java.util.Map, int, int)
	 */
    @Override
	public List<T> find(String queryString, Map<String, Object> params, int startRow, int pageSize) throws DataAccessException {
        log.debug("DAO:Running HQL query with params by page :" + queryString);
        Query query = createQuery(queryString, params, startRow, pageSize);
        return query.list();
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#findUniqueResult(java.lang.String, java.util.Map)
	 */
    @Override
	public T findUniqueResult(String queryString, Map<String, Object> params) throws DataAccessException {
        log.debug("DAO:Running HQL query with parameters:" + queryString);
        Query query = createQuery(queryString, params);
        return (T) query.uniqueResult();
    }

    /* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.BaseDao#excute(java.lang.String, java.util.Map)
	 */
    @Override
	public int excute(String queryString, Map<String, Object> params) throws DataAccessException {
        log.debug("DAO:Excute HQL update :" + queryString);
        Query query = createQuery(queryString, params);
        return query.executeUpdate();
    }
}
