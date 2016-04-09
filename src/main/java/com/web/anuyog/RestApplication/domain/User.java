package com.web.anuyog.RestApplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="User")
public class User {
	@Id @GeneratedValue
	int userId;
	@Column(name="name")
	String name;
	
	@Column(name="AccNumber")
	int accNum;
	@Column(name="pinNumber")
	int pinNum;
	@Column(name="DOB")
	String dob;
	@Column(name="City")
	String city;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAccNum() {
		return accNum;
	}
	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}
	public int getPinNum() {
		return pinNum;
	}
	public void setPinNum(int pinNum) {
		this.pinNum = pinNum;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
