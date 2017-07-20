package com.wrw.newsystem.dto;

import java.util.Date;

public class UserDto {
	
	private String userName;
	private String userPwd1;
	private String userPwd2;
	private Date registDate;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd1() {
		return userPwd1;
	}
	public void setUserPwd1(String userPwd1) {
		this.userPwd1 = userPwd1;
	}
	public String getUserPwd2() {
		return userPwd2;
	}
	public void setUserPwd2(String userPwd2) {
		this.userPwd2 = userPwd2;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	
	
}
