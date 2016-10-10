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
		String page = ex.getPageName();
		map.setAttribute("errorMsg", ex.getMessage());
		return page;
	}
	
	@ExceptionHandler(value=ParameterException.class)
	public String businessException(ParameterException ex,HttpServletRequest map){
		String pageName = ex.getPageName();
		map.setAttribute("errorElementId", ex.getElementId());
		map.setAttribute("errorMsg", ex.getMessage());
		map.setAttribute(ex.getObjStr(), ex.getObject());
		return pageName;
	}
	@ExceptionHandler(value=ParameterException.class)
	public String paramaterException(ParameterException ex,HttpServletRequest map){
		String pageName = ex.getPageName();
		map.setAttribute("errorMsg", ex.getMessage());
		map.setAttribute(ex.getObjStr(), ex.getObject());
		return pageName;
	}
}
