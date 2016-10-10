package com.exception;

public class BusinessException extends Exception{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 画面名称
	 */
	private String pageName;
	/**
	 * 出错信息
	 */
	private String errorMsg;
	/**
	 * 传递对象
	 */
	private Object object;
	/**
	 * 页面对象名
	 */
	private String objStr;
	
	public BusinessException(String pageName, String errorMsg, Object object,
			String objStr) {
		this.pageName = pageName;
		this.errorMsg = errorMsg;
		this.object = object;
		this.objStr = objStr;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getObjStr() {
		return objStr;
	}
	public void setObjStr(String objStr) {
		this.objStr = objStr;
	}
	
}
