package com.wrw.newsystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class BaseController {
	
	public final static String ERROR = "error";
    public final static String SUCCESS = "success";
    
    protected Logger log = Logger.getLogger(this.getClass());
    
    /**
     * 添加Model消息
     *
     * @param messages
     */
    protected void addMessage(Model model, String messages) {
        model.addAttribute("message", messages);
    }
    
    /**
     * 添加Model消息
     * @param type 消息类型
     * @param messages
     */
    protected void addMessage(Model model,String type, String messages) {
        model.addAttribute("message", messages);
        model.addAttribute("type", type);
    }
    
    /**
     * 添加Flash消息
     *
     * @param messages
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String messages) {
        redirectAttributes.addFlashAttribute("message", messages);
    }
    
    /**
     * 添加Flash消息
     * @param type 消息类型
     * @param messages
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String type, String messages) {
        redirectAttributes.addFlashAttribute("message", messages);
        redirectAttributes.addFlashAttribute("type", type);
    }
    
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, HttpServletRequest request){
        if(ex instanceof UnauthorizedException){
            log.error("当前用户没有此权限");
            return "/403.jsp";
        }else {
            log.error("系统发生异常", ex);
            ex.printStackTrace();
            request.setAttribute("exMsg", ex.getMessage());
            return "errors/exception";
        }
    }
}
