package com.wrw.newsystem.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wrw.newsystem.dao.LoginlogDao;
import com.wrw.newsystem.model.Loginlog;

@Repository("loginlogDao")
public class LoginlogDaoImpl implements LoginlogDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession(){
        return this.sessionFactory.getCurrentSession();
    }
	
	@Override
	public void savelogn(Loginlog log) {
		this.getCurrentSession().save(log);
	}

}
