package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import constant.Constants;

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
}
