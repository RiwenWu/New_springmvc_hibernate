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
     * ���Model��Ϣ
     *
     * @param messages
     */
    protected void addMessage(Model model, String messages) {
        model.addAttribute("message", messages);
    }
    
    /**
     * ���Model��Ϣ
     * @param type ��Ϣ����
     * @param messages
     */
    protected void addMessage(Model model,String type, String messages) {
        model.addAttribute("message", messages);
        model.addAttribute("type", type);
    }
    
    /**
     * ���Flash��Ϣ
     *
     * @param messages
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String messages) {
        redirectAttributes.addFlashAttribute("message", messages);
    }
    
    /**
     * ���Flash��Ϣ
     * @param type ��Ϣ����
     * @param messages
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String type, String messages) {
        redirectAttributes.addFlashAttribute("message", messages);
        redirectAttributes.addFlashAttribute("type", type);
    }
    
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, HttpServletRequest request){
        if(ex instanceof UnauthorizedException){
            log.error("��ǰ�û�û�д�Ȩ��");
            return "/403.jsp";
        }else {
            log.error("ϵͳ�����쳣", ex);
            ex.printStackTrace();
            request.setAttribute("exMsg", ex.getMessage());
            return "errors/exception";
        }
    }
}
