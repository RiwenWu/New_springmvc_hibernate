package com.wrw.newsystem.model;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author wrw
 * 权限类，保存权限信息与对应的角色（多对一）
 */

@Entity(name = "t_permission")
public class Permission extends BaseModel{
	
	private String permissionname;
    private Role role;
	
	public String getPermissionname() {
		return permissionname;
	}
	
	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}
	
	@ManyToOne(targetEntity = Role.class)
    @JoinTable(name = "t_role_permission", 
        joinColumns = {@JoinColumn(name = "permission_id")}, 
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
