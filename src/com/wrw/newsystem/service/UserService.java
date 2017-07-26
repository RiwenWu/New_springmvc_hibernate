package com.wrw.newsystem.service;

import java.util.List;

import com.wrw.newsystem.model.User;

public interface UserService {
	User login(User u);
	
	void regist(User u);
	
	User findByUerName(String name);
	
	List<User> getAllUser();
}
