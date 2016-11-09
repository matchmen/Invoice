package com.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse reponse,
			Object arg2, ModelAndView arg3) throws Exception {
			
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse reponse,
			Object arg2) throws Exception {
		User currE = (User)request.getSession().getAttribute("currUser");
		String url = request.getParameter("method");
		if(null==currE&&!(url.equals("register")||url.equals("registerTypePage")||url.equals("loginPage")||url.equals("login")||url.equals("registerType"))){
			reponse.sendRedirect("user.do?method=loginPage");
			return false;
		}else{
			return true;
		}
	}

	
	
}
