package com.wrw.newsystem.service;

import com.wrw.newsystem.model.User;

public interface UserService {
	User login(User u);
	
	User regist(User u);
	
	User findByUerName(String name);
}
