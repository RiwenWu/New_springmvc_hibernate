package com.wrw.newsystem.service.impl;

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wrw.newsystem.model.Role;
import com.wrw.newsystem.model.User;
import com.wrw.newsystem.service.UserService;

@Service("shiroDBRealm")
public class ShiroDBRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;

	//获取授权信息
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//获取登录时输入的用户名
		String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
		//到数据库获取该用户
		User user = userService.findByUerName(loginName);
		if(user != null){
			//权限信息对象info,用来存放查出的用户的所有 role 和 permission
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			//用户的role集合
			info.setRoles(user.getRolesName());
			//用户的role对应的所有permission，如果只使用role定义访问权限
			Collection<Role> roleList = user.getRoleList();
			for(Role role : roleList){
				info.addStringPermissions(role.getPermissionsName());
			}
			return info;
		}
		return null;
	}

	//获取身份验证相关信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationTaken) throws AuthenticationException {
		//UsernamePasswordToken对象用来存放提交的登录信息  
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationTaken;
		//查出是否有此用户  
        User user=userService.findByUerName(token.getUsername());
        if(user!=null){
            //若存在，将此用户存放到登录认证info中  
            return new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(), getName());
        }
		return null;
	}
	
	@Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
