package com.bean;

import com.model.Company;
import com.model.Employee;

public class CompanyBean {
	
	private Employee employee;
	
	private Company company;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
