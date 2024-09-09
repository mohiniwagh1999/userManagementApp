package com.management.dto;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.management.entity.City;
import com.management.entity.Country;
import com.management.entity.State;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UserDTO {
	
private Integer userId;
	
	private String  email;
	
	private String  pwd;
	
	private String pwdUpdate;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwdUpdate() {
		return pwdUpdate;
	}

	public void setPwdUpdate(String pwdUpdate) {
		this.pwdUpdate = pwdUpdate;
	}
	
	
	
	
	
	
	
	

}
