package com.wrw.newsystem.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wrw.newsystem.dao.UserDao;
import com.wrw.newsystem.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession(){
        return this.sessionFactory.getCurrentSession();
    }
	
	@Override
	public User add(User u) {
		this.getCurrentSession().save(u);
		return u;
	}

	@Override
	public User deleteByuserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> finalAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(User u) {
		String hql = "from User u where u.userName=? and u.userPassword=?";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, u.getUserName());
		query.setParameter(1, u.getUserPassword());
		List<User> list = query.list();
		return list.get(0);
	}

	@Override
	public Boolean isExiteName(String username) {
		String hql = "from User u where u.userName=?";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, username);
		List list = query.list(); 
		return list.isEmpty();
	}

}
