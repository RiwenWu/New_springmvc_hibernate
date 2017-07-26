package com.wrw.newsystem.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface BaseDao<T> {

	public abstract void flush();

	public abstract void evict(T modele);

	public abstract T get(long id) throws DataAccessException;

	public abstract void save(T modele) throws DataAccessException;

	public abstract void update(T modele) throws DataAccessException;

	public abstract void saveOrUpdate(T modele) throws DataAccessException;

	public abstract void delete(T modele) throws DataAccessException;

	public abstract void deleteById(long id) throws DataAccessException;

	public abstract int excute(String queryString) throws DataAccessException;

	public abstract List<T> find(String queryString) throws DataAccessException;

	public abstract List<T> find(String queryString, int startRow, int pageSize)
			throws DataAccessException;

	public abstract int getTotalCount(String queryString)
			throws DataAccessException;

	public abstract int getTotalCount(String queryString,
			Map<String, Object> params) throws DataAccessException;

	public abstract List findWithSelect(String queryString)
			throws DataAccessException;

	public abstract List findWithSelect(String queryString,
			Map<String, Object> params) throws DataAccessException;

	public abstract List findWithSelect(String queryString,
			Map<String, Object> params, int startRow, int pageSize)
			throws DataAccessException;

	public abstract List<T> find(String queryString, Map<String, Object> params)
			throws DataAccessException;

	public abstract List<T> find(String queryString,
			Map<String, Object> params, int startRow, int pageSize)
			throws DataAccessException;

	public abstract T findUniqueResult(String queryString,
			Map<String, Object> params) throws DataAccessException;

	public abstract int excute(String queryString, Map<String, Object> params)
			throws DataAccessException;

}