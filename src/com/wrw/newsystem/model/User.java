package com.wrw.newsystem.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "t_user")
public class User extends BaseModel{
	
	@NotEmpty(message = "用户名不能为空")
	private String userName;
	@NotEmpty(message = "密码不能为空")
	private String userPassword;
	
	private Set<Role> roleList;
	
	public User(){
		
	}
	
	public User(String name, String pwd){
		this.userName = name;
		this.userPassword = pwd;
	}
	
	public User(String name, String pwd, Date date){
		this.userName = name;
		this.userPassword = pwd;
		this.createDate = date;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@ManyToMany
	@JoinTable(name = "t_user_role", 
			joinColumns = {@JoinColumn(name = "user_id")}, 
			inverseJoinColumns = {@JoinColumn(name = "role_id")})
	public Set<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<Role> roleList) {
		this.roleList = roleList;
	}

	//@Transient不知道有没有导错
	@Transient
    public Set<String> getRolesName() {
        Set<Role> roles = getRoleList();
        Set<String> set = new HashSet<String>();
        for (Role role : roles) {
            set.add(role.getRoleName());
        }
        return set;
    }

}
