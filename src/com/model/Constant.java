package com.model;

public class Constant {
	/**
	 * 画面名称
	 */
	private String pageName;
	/**
	 * 常量名
	 */
	private String constantName;
	/**
	 * 常量值
	 */
	private String constantValue;
	/**
	 * 顺序
	 */
	private int index;
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getConstantName() {
		return constantName;
	}
	public void setConstantName(String constantName) {
		this.constantName = constantName;
	}
	public String getConstantValue() {
		return constantValue;
	}
	public void setConstantValue(String constantValue) {
		this.constantValue = constantValue;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
