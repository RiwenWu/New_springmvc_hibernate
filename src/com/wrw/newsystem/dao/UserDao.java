package com.wrw.newsystem.dao;

import java.util.List;

import com.wrw.newsystem.model.User;

public interface UserDao {
	
	User add(User u);
	
	Boolean isExiteName(String username);
	
	User deleteByuserId(String userId);
	
	List<User> finalAll();
	
	User login(User u);
}
