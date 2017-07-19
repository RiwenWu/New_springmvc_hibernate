package com.wrw.newsystem.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class User {
	/*
	@Id  
    @GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name = "system-uuid",strategy="uuid")  
    @Column(length=32)  
	*/
	@Id
	@GeneratedValue
	private Long userId;
	private String userName;
	private String userPassword;
	@Temporal(TemporalType.TIMESTAMP)
	private Date registDate;
	
	public User(){
		
	}
	
	public User(String name, String password){
		this.userName = name;
		this.userPassword = password;
	}
	
	public User(String name, String password, Date date){
		this.userName = name;
		this.userPassword = password;
		this.registDate = date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	
}
