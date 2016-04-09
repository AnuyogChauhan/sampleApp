package com.web.anuyog.RestApplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity(name="User_Balance")
public class Balance {
	@Id @GeneratedValue
	int Id;
	@Version
    @Column(name="OPTLOCK")
	private int version;
	@OneToOne
    @JoinColumn(name = "User")
    private User user;
	public int getUserId() {
		return Id;
	}
	public void setUserId(int userId) {
		this.Id = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double bal) {
		this.balance = bal;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	@Column(name="Balance")
	double balance=0.00;
	
}
