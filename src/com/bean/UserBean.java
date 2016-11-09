package com.bean;

import com.model.Company;
import com.model.User;

public class UserBean {

	private User user;
	
	private Company company;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}
