package com.wrw.newsystem.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wrw
 * �����û������Ŀ�����
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@RequiresPermissions(value = "user:add")
    @RequestMapping("/add.html")
    public String addUser() throws UnauthorizedException {
        return "/success.jsp";
    }

    @RequiresPermissions(value = "user:edit")
    @RequestMapping("/edit.html")
    public String updateUser(int id){
        System.out.println("=========================================>Ҫ�޸ĵ�idΪ:" + id);
        return "/success.jsp";
    }

    @RequiresPermissions(value = "user:del")
    @ResponseBody
    @RequestMapping(value = "/del.html",produces = "text/html; charset=utf-8",method= RequestMethod.GET)
    public String deleteUser(String id){
        System.out.println("=========================================>Ҫɾ����idΪ:" + id);
        return "";
    }
}
