package com.wrw.newsystem.dao;

import java.util.List;

import com.wrw.newsystem.model.User;

public interface UserDao extends BaseDao<User>{
	
	void add(User u);
	
	Boolean isExiteName(String username);
	
	User deleteByuserName(String userName);
	
	List<User> finalAll();
	
	User login(User u);
	
	User findByName(String username);
}
