package com.wrw.newsystem.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Loginlog {

	@Id
	@GeneratedValue
	private Long id;
	private String userName;
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginDate;
	
	public Loginlog(){
		
	}
	
	public Loginlog(String userName, Date loginDate){
		this.userName = userName;
		this.loginDate = loginDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	
}
