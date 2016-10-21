package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.constant.Constants;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.exception.SystemException;


public class BaseController {

	@InitBinder
	public void initBinderDate(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT_OF_DATE);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@InitBinder
	public void initBinderBoolean(WebDataBinder binder){
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(false));
	}
	
	@ExceptionHandler(value=SystemException.class)
	public String systemException(SystemException ex,HttpServletRequest map){
		String pageName ="error";
		if(null!=ex.getPageName())
			pageName = ex.getPageName();
		if(null!=ex.getMessage())
			map.setAttribute("errorMsg", ex.getMessage());
		return pageName;
	}
	
	@ExceptionHandler(value=BusinessException.class)
	public String businessException(BusinessException ex,HttpServletRequest map){
		String pageName ="error";
		if(null!=ex.getPageName())
			pageName = ex.getPageName();
		if(null!=ex.getMessage())
			map.setAttribute("errorMsg", ex.getMessage());
		if(null!=ex.getObjStr()&&null!=ex.getObject())
			map.setAttribute(ex.getObjStr(), ex.getObject());
		return pageName;
	}
	@ExceptionHandler(value=ParameterException.class)
	public String paramaterException(ParameterException ex,HttpServletRequest map){
		String pageName ="error";
		if(null!=ex.getPageName())
			pageName = ex.getPageName();
		if(null!=ex.getErrorMsg())
			map.setAttribute("errorMsg", ex.getErrorMsg());
		if(null!=ex.getElementId())
			map.setAttribute("errorElementId", ex.getElementId());
		if(null!=ex.getObjStr()&&null!=ex.getObject())
			map.setAttribute(ex.getObjStr(), ex.getObject());
		return pageName;
	}
}
