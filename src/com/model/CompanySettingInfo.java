package com.model;

public class CompanySettingInfo {
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 公司代码
	 */
	private String companyCode;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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
