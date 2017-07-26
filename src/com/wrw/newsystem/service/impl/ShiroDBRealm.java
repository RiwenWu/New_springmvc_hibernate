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

	//��ȡ��Ȩ��Ϣ
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//��ȡ��¼ʱ������û���
		String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
		//�����ݿ��ȡ���û�
		User user = userService.findByUerName(loginName);
		if(user != null){
			//Ȩ����Ϣ����info,������Ų�����û������� role �� permission
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			//�û���role����
			info.setRoles(user.getRolesName());
			//�û���role��Ӧ������permission�����ֻʹ��role�������Ȩ��
			Collection<Role> roleList = user.getRoleList();
			for(Role role : roleList){
				info.addStringPermissions(role.getPermissionsName());
			}
			return info;
		}
		return null;
	}

	//��ȡ�����֤�����Ϣ
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationTaken) throws AuthenticationException {
		//UsernamePasswordToken������������ύ�ĵ�¼��Ϣ  
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationTaken;
		//����Ƿ��д��û�  
        User user=userService.findByUerName(token.getUsername());
        if(user!=null){
            //�����ڣ������û���ŵ���¼��֤info��  
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
