package com.wrw.newsystem.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wrw.newsystem.dao.FunctionDao;
import com.wrw.newsystem.model.Function;
import com.wrw.newsystem.model.User;
import com.wrw.newsystem.service.FunctionService;

@Service("functionService")
public class FunctionServiceImpl implements FunctionService{

	@Autowired
	private FunctionDao functionDao;
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession(){
        return this.sessionFactory.getCurrentSession();
    }

	@Override
	public List<Function> getAllFunction() {
		Transaction ts = null;
		List list = null;
		try {
			ts = getCurrentSession().beginTransaction();
			list  = functionDao.findAll();
			ts.commit();
		}catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
		}
		return list;
	}
	

}
