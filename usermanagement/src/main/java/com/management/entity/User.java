package com.management.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="user_master")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	private String  uname;
	
	@Column(unique=true)
	private String  email;
	private Long phno;
	private String  pwd;
	private String pwdUpdate;
	
	@ManyToOne
	@JoinColumn(name="countryId")
	private Country country; //fk
	
	@ManyToOne
	@JoinColumn(name="stateId")
	private State state; //fk
	
	@ManyToOne
   @JoinColumn(name="cityId")
	private City city; //fk
	
	
	@CreationTimestamp
	@Column(name="createdate", updatable=false)
	private LocalDate createdDate;
	@UpdateTimestamp
	@Column(name="updatedate", updatable=false)
	private LocalDate updatedDate;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhno() {
		return phno;
	}
	public void setPhno(Long phno) {
		this.phno = phno;
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
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	
	
	
	
	
	
}