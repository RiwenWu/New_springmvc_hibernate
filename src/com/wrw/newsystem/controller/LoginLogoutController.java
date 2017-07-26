package com.wrw.newsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wrw.newsystem.model.User;
import com.wrw.newsystem.service.UserService;

/**
 * @author wrw
 * �����û���¼�ǳ��Ŀ�����
 */

@Controller
public class LoginLogoutController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login.html",method=RequestMethod.GET,produces = "text/html; charset=utf-8")
    public String loginForm(Model model,String message){
        if(!StringUtils.isEmpty(message))
            model.addAttribute(message);
        model.addAttribute("user", new User());
        return "/login.jsp";
    }
	
	@RequestMapping(value="/login.html",method=RequestMethod.POST,produces = "text/html; charset=utf-8")
    public String login(@Valid User user,BindingResult bindingResult,Model model,RedirectAttributes attr){
        try {
            if(bindingResult.hasErrors()){
                addMessage(attr, "�û������������");
                return "redirect:/login.html";
            }
            //ʹ��shiro�����¼
            SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getUserName(), user.getUserPassword()));
            //��ȡ�����û���Ϣ��Ȩ����ǰ��shiro��ǩ����
            List<User> userList = userService.getAllUser();
            model.addAttribute("userList", userList);
            return "/user.jsp";
        } catch (AuthenticationException e) {
            addMessage(attr, "�û������������");
            return "redirect:/login.html";
        }
    }
	
	@RequestMapping(value="/logout.html",method=RequestMethod.GET)
    public String logout(RedirectAttributes attr){
        //ʹ��Ȩ�޹����߽����û����˳���ע����¼
        SecurityUtils.getSubject().logout();
        //addMessage(attr, "���Ѱ�ȫ�˳�");
        return "redirect:/login.html";
    }

    @RequestMapping("/403.html")
    public String unauthorizedRole(){
        return "/403.jsp";
    }
}
