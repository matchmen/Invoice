package com.service;

import com.exception.BusinessException;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.Employee;

public interface EmployeeService {

	public Employee login(String str,String password) throws SystemException;
	
	public void logout(Employee employee);
	
	public Employee findEmployee(Integer id);
	
	public void addEmployee(Employee employee) throws ParameterException, SystemException;
	
	public void updatePassword(Employee employee,String oldPassword,String password) throws ParameterException, SystemException;
	
	public Employee updateEmployeeInfo(Employee employee) throws ParameterException;
	
	public void removeEmployee(String str,Integer id) throws ParameterException, BusinessException;
}
