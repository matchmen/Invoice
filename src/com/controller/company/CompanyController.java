package com.controller.company;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.controller.BaseController;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.BaseInfo;
import com.service.CompanyService;
@Controller
@RequestMapping("/company.do")
public class CompanyController extends BaseController{
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(params="method=setCompanyInfo")
	public String setCompanyInfo(BaseInfo info,HttpSession httpSession) throws ParameterException, SystemException{
		
		//User employee = (User)httpSession.getAttribute("currUser");
		
		return "setCompanyInfoSuccess";
	}
}
