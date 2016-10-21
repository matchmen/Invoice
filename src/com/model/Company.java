package com.model;

public class Company {

	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮箱
	 */
	private String companyEmail;
	/**
	 * 电话
	 */
	private String tellphoneNumber;
	/**
	 * 有效
	 */
	private Boolean abled;
	/**
	 *公司代码
	 */
	private String companyCode;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getTellphoneNumber() {
		return tellphoneNumber;
	}

	public void setTellphoneNumber(String tellphoneNumber) {
		this.tellphoneNumber = tellphoneNumber;
	}

}
