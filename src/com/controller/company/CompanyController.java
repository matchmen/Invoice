package com.controller.company;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.controller.BaseController;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.Company;
import com.model.CompanySettingInfo;
import com.model.Employee;
import com.service.CompanyService;
@Controller
@RequestMapping("/company.do")
public class CompanyController extends BaseController{
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(params="method=register")
	public String register(@ModelAttribute Company company,@ModelAttribute Employee employee,ModelMap map) throws ParameterException, SystemException{
								
		companyService.register(company,employee);
		
		return "registerSuccess";
	}
	@RequestMapping(params="method=registerType")
	public String registerType(Employee employee,ModelMap map) throws ParameterException, SystemException{
		
		map.addAttribute("employee", employee);
		
		return "register";
	}
	
	@RequestMapping(params="method=registerTypePage")
	public String registerTypePage(){
		return "registerType";
	}
	@RequestMapping(params="method=setCompanyInfo")
	public String setCompanyInfo(CompanySettingInfo info,HttpSession httpSession) throws ParameterException, SystemException{
		
		Employee employee = (Employee)httpSession.getAttribute("currEmployee");
		
		info.setCompanyCode(employee.getCompanyCode());
		
		companyService.updateCompanySettingInfo(info);
		
		return "setCompanyInfoSuccess";
	}
	@RequestMapping(params="method=checkCompanyInfo")
	public String checkCompanyInfo(HttpSession httpSession,ModelMap map) throws ParameterException{
		Employee employee = (Employee)httpSession.getAttribute("currEmployee");
		
		CompanySettingInfo info = companyService.findCompanySettingInfo(employee.getCompanyCode());
		map.addAttribute("info", info);
		return "setCompanyInfo";
	}
}
