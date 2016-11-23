package com.model;

public class SystemInfo {
	
	private Integer id;
	
	private String userInitPW;
	
	private Integer cueTimeOfSales;
	
	private Integer cueTimeOfFinance;
	
	private String systemMail;
	
	private String systemMailPW;
	
	private String systemMailType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserInitPW() {
		return userInitPW;
	}

	public void setUserInitPW(String userInitPW) {
		this.userInitPW = userInitPW;
	}

	public Integer getCueTimeOfSales() {
		return cueTimeOfSales;
	}

	public void setCueTimeOfSales(Integer cueTimeOfSales) {
		this.cueTimeOfSales = cueTimeOfSales;
	}

	public Integer getCueTimeOfFinance() {
		return cueTimeOfFinance;
	}

	public void setCueTimeOfFinance(Integer cueTimeOfFinance) {
		this.cueTimeOfFinance = cueTimeOfFinance;
	}

	public String getSystemMail() {
		return systemMail;
	}

	public void setSystemMail(String systemMail) {
		this.systemMail = systemMail;
	}

	public String getSystemMailPW() {
		return systemMailPW;
	}

	public void setSystemMailPW(String systemMailPW) {
		this.systemMailPW = systemMailPW;
	}

	public String getSystemMailType() {
		return systemMailType;
	}

	public void setSystemMailType(String systemMailType) {
		this.systemMailType = systemMailType;
	}
}
