package com.wrw.newsystem.service.impl;

import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.wrw.newsystem.dao.UserDao;
import com.wrw.newsystem.model.User;
import com.wrw.newsystem.service.UserService;


@ContextConfiguration("classpath:applicationContent.xml")
public class testService extends AbstractJUnit4SpringContextTests{

	@Resource(name = "userService")
	private UserService userService;
	
	
	@Test//²âÊÔ×¢²á
	public void testRegist() {
		User u = new User();
		u.setUserName("c");
		u.setUserPassword("c");
		u.prePersist();
		userService.regist(u);
		
	}
	
	
	@Test//²âÊÔµÇÂ½ºÍµÇÂ½ÈÕÖ¾ÊÂÎñ
	public void testLogin(){
		User u = new User("a", "v");
		User u1 = userService.login(u);
		Assert.assertEquals("a", u1.getUserName());
	}

}
