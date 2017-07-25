package com.wrw.newsystem.model;

import java.io.Serializable;
import java.util.Date;
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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "t_user")
public class User extends BaseModel{
	
	private String userName;
	private String userPassword;
	/*@ManyToMany//(mappedBy="userList")
    @JoinTable(name = "t_user_role", 
        joinColumns = {@JoinColumn(name = "user_id")}, 
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
	*/
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_user_role", 
			joinColumns = {@JoinColumn(name = "user_id")}, 
			inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private Set<Role> roleList;
	
	public User(){
		
	}
	
	public User(String name, String password){
		this.userName = name;
		this.userPassword = password;
	}
	
	public User(String name, String password, Date date){
		this.userName = name;
		this.userPassword = password;
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

}
