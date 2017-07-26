package com.wrw.newsystem.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wrw.newsystem.dao.LoginlogDao;
import com.wrw.newsystem.dao.UserDao;
import com.wrw.newsystem.model.Loginlog;
import com.wrw.newsystem.model.User;
import com.wrw.newsystem.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginlogDao loginlogDao;
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession(){
        return this.sessionFactory.getCurrentSession();
    }

	@Override
	public User login(User u) {
		Transaction ts = null;
		User user = null;
		Loginlog log = null;
		try {
			ts = getCurrentSession().beginTransaction();
			user = userDao.login(u);
			log = new Loginlog(u.getUserName(), new Date());
			loginlogDao.savelogn(log);
			ts.commit();
		}catch (Exception e) {
			if (ts != null) {
				ts.rollback();
				return null;
			}
		}
		return user;
	}

	@Override
	public void regist(User u) {
		Transaction ts = null;
		User user = null;
		try {
			ts = getCurrentSession().beginTransaction();
			//ÅÐ¶ÏÃû×ÖÊÇ·ñÎ¨Ò»
			if (!userDao.isExiteName(u.getUserName()))
				ts.rollback();
			userDao.add(u);	
			ts.commit();
		}catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
		}
	}

	@Override
	public User findByUerName(String name) {
		Transaction ts = null;
		User user = null;
		try {
	        user = userDao.findByName(name);
			ts.commit();
		}catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
		}
		return user;
	}

	@Override
	public List<User> getAllUser() {
		Transaction ts = null;
		List list = null;
		try {
	        list = userDao.finalAll();
			ts.commit();
		}catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
		}
		return list;
	}
	
	
}
