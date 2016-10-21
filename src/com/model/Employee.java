package com.model;

public class Employee {
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 员工编号
	 */
	private String employeeId;
	/**
	 * 姓名
	 */
	private String employeeName;
	/**
	 * 电话
	 */
	private String phoneNumber;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 单位
	 */
	private String companyName;
	/**
	 * 单位代码
	 */
	private String companyCode;
	/**
	 * 部门
	 */
	private String department;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 有效
	 */
	private Boolean abled;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 是否是管理员
	 */
	private Boolean isAdmin;
	
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAbled() {
		return abled;
	}

	public void setAbled(Boolean abled) {
		this.abled = abled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
