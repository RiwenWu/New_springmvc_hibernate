package com.wrw.newsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wrw.newsystem.model.User;
import com.wrw.newsystem.service.UserService;

@Controller
//将要写入session中key 通过注解来设置
@SessionAttributes("user")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login.do")
	public String loginUser(User u, ModelMap map){
		User user = userService.login(u);
		if (user != null){
			map.addAttribute("user", user);
			return "forward:/index.jsp";
		}
		return "forward:/login.jsp";
	}
	
	@RequestMapping("/tologin.do")
	public String tologin(){
		return "forward:/login.jsp";
	}
	
	
}
