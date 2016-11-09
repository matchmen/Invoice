package com.model;

public class BaseInfo {
	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 公司ID
	 */
	private Integer companyId;
	/**
	 * 初始密码
	 */
	private String initPassword;
	/**
	 * 提示时间(销售)
	 */
	private Integer cueTimeSales;
	/**
	 * 提示时间(财务)
	 */
	private Integer cueTimeFinance;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInitPassword() {
		return initPassword;
	}

	public void setInitPassword(String initPassword) {
		this.initPassword = initPassword;
	}

	public Integer getCueTimeSales() {
		return cueTimeSales;
	}

	public void setCueTimeSales(Integer cueTimeSales) {
		this.cueTimeSales = cueTimeSales;
	}

	public Integer getCueTimeFinance() {
		return cueTimeFinance;
	}

	public void setCueTimeFinance(Integer cueTimeFinance) {
		this.cueTimeFinance = cueTimeFinance;
	}
}
