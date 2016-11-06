package com.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AuthorityDao;
import com.dao.CompanyDao;
import com.dao.ContractDao;

public class ContractInterceptor implements HandlerInterceptor {
	
	@Autowired
	private AuthorityDao authorityDao;
	@Autowired	
	private ContractDao contractDao;
	@Autowired
	private CompanyDao companyDao;
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse reponse,
			Object arg2) throws Exception {
		
		/*Employee employee = (Employee)request.getSession().getAttribute("currEmployee");
		String contractId = (Contract)request.getAttribute("contract");
		
		Company company = companyDao.find(employee.getCompanyCode());
		contractDao.findByContractId(contract.getContractId());
		
		Authority authority = new Authority();
		authorityService.authValidate(authority);*/
		
		return false;
	}

}
