package com.bean;

import com.model.Employee;

public class Admin {

	private String str;
	
	private Employee employee;  
	
	private String comId;
	
	private String contractId;
	
	private String invoiceId;
	
	private Boolean isAll;
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Boolean getIsAll() {
		return isAll;
	}

	public void setIsAll(Boolean isAll) {
		this.isAll = isAll;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	
}
