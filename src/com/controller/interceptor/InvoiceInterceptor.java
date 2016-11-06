package com.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class InvoiceInterceptor implements HandlerInterceptor{

	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse reponse, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse reponse,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse reponse,
			Object arg2) throws Exception {
		
		
		
		return false;
	}

}
