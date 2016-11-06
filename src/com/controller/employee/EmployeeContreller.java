package com.controller.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.controller.BaseController;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.Employee;
import com.service.EmployeeService;

@Controller
@RequestMapping("employee.do")
@SessionAttributes("currEmployee")
public class EmployeeContreller extends BaseController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(params="method=login")
	public String login(String username,String password,ModelMap map) throws SystemException{
		
		Employee currEmployee = employeeService.login(username,password);
		
		if(null!=currEmployee){
			
			map.addAttribute("currEmployee", currEmployee);
			
			return "main";
			
		}else{
			map.addAttribute("errorMsg", "用户名或密码不正确!");
			return "login";
		}
	}
	@RequestMapping(params="method=logout")
	public String logout(HttpServletRequest request,ModelMap map) throws SystemException{
		return "login";
	}
	@RequestMapping(params="method=addEmployee")
	public String addEmployee(Employee employee,ModelMap map) throws ParameterException, SystemException{
		
		Employee currEmployee = (Employee)map.get("currEmployee");
		//设置公司代码
		employee.setCompanyCode(currEmployee.getCompanyCode());
		//设置公司名称
		employee.setCompanyName(currEmployee.getCompanyName());
		
		employeeService.addEmployee(employee);
		
		return "addEmployeeSuccess";
	}
	@RequestMapping(params="method=checkEmployeeInfo")
	public String checkEmployeeInfo(ModelMap map){
		
		Employee currEmployee = (Employee)map.get("currEmployee");
		
		Employee em = employeeService.findEmployee(currEmployee.getId());
		
		map.addAttribute("currEmployee", em);
		
		return "checkEmployeeInfo";
	}
	@RequestMapping(params="method=updatePassword")
	public String updatePassword(HttpSession session,ModelMap map,String password,String oldPassword) throws ParameterException, SystemException{
		
		Employee employee = (Employee)map.get("currEmployee");
		
		employeeService.updatePassword(employee, oldPassword,password);
		
		session.removeAttribute("currEmployee");
		
		return "updatePasswordSuccess";
	}
	@RequestMapping(params="method=updateEmployeeInfo")
	public String updateEmployeeInfo(HttpSession session,ModelMap map,Employee employee) throws ParameterException, SystemException{
		
		Employee em = (Employee)map.get("currEmployee");
		
		employee.setId(em.getId());
		
		employee.setCompanyCode(em.getCompanyCode());
		
		employee.setCompanyName(em.getCompanyName());
		
		em = employeeService.updateEmployeeInfo(employee);
		
		session.removeAttribute("currEmployee");
		
		session.setAttribute("currEmployee", em);
		
		return "updateEmployeeInfoSuccess";
	}
	@RequestMapping(params="method=loginPage")
	public String loginPage(){
		return "login";
	}
	@RequestMapping(params="method=addEmployeePage")
	public String addEmployeePage(){
		return "addEmployee";
	}
	@RequestMapping(params="method=setCpInfoPage")
	public String setCpInfoPage(){
		return "setCpInfo";
	}
	@RequestMapping(params="method=mainPage")
	public String mainPage(){
		return "main";
	}
	@RequestMapping(params="method=updatePasswordPage")
	public String updatePasswordPage(){
		return "updatePassword";
	}
	@RequestMapping(params="method=updateEmployeeInfoPage")
	public String updateEmployeeInfoPage(ModelMap map,Employee employee){
		Employee em = (Employee)map.get("currEmployee");
		map.addAttribute("employee", em);
		return "updateEmployeeInfo";
	}
	
	@RequestMapping(params="method=removeEmployee")
	public String removeEmployee(ModelMap map,String str) throws ParameterException, BusinessException{
		
		Employee em = (Employee)map.get("currEmployee");
		
		employeeService.removeEmployee(str,em.getId());
		
		return "success";
	}
	
	@RequestMapping(params="method=removeEmployeePage")
	public String removeEmployeePage(){
		return "removeEmployee";
	}
}
