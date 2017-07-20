package com.wrw.newsystem.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wrw.newsystem.dto.UserDto;
import com.wrw.newsystem.model.User;
import com.wrw.newsystem.service.UserService;

@Controller
public class RegistController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/toregist.do")
	public String toRegist(){
		return "forward:/regist.jsp";
	}
	
	@RequestMapping("/regist.do")
	public String registUser(UserDto uDto){
		if (!uDto.getUserPwd1().equals(uDto.getUserPwd2()))
			return "forward:/regist.jsp";
		User u = new User(uDto.getUserName(), uDto.getUserPwd1(), new Date());
		System.out.println(u.getUserName());
		userService.regist(u);
		return "forward:/login.jsp";
	}
}
