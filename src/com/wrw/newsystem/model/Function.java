package com.wrw.newsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * ��ҳ������Ϣ�࣬������ҳ������Ϣ���Լ���Ӧ��Ȩ��(һ��һ)���ɫ(һ��һ)
 */

@Entity(name = "t_function")
public class Function{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String value;
	
	@OneToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;
	
	@OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
	
    private String type;

    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
    
}
