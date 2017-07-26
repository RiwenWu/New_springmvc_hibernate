package com.wrw.newsystem.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wrw.newsystem.dao.FunctionDao;
import com.wrw.newsystem.model.Function;

@Repository("FunctionDao")
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements FunctionDao{

	/* (non-Javadoc)
	 * @see com.wrw.newsystem.dao.impl.FunctionDao#findAll()
	 */
	@Override
	public List<Function> findAll() {
		String hql = "from Function f";
        return this.find(hql);
    }
}
