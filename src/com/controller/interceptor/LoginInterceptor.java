package com.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.model.Employee;

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
		Employee currE = (Employee)request.getSession().getAttribute("currEmployee");
		String url = request.getParameter("method");
		if(null==currE&&!(url.equals("register")||url.equals("registerPage")||url.equals("loginPage")||url.equals("login"))){
			reponse.sendRedirect("employee.do?method=loginPage");
			return false;
		}else{
			return true;
		}
	}

	
	
}
