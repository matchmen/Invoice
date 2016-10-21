package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.CompanySettingInfoDao;
import com.dao.EmployeeDao;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.CompanySettingInfo;
import com.model.Employee;
import com.service.EmployeeService;
import com.util.StringUtils;
@Repository("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private CompanySettingInfoDao companySettingInfoDao;	
	
	@Override
	public Employee login(String str,String password) throws SystemException {
		//查询ID
		Integer id = employeeDao.findId(str);
		
		if(null!=id){
			//員工信息
			Employee ep = employeeDao.find(id);
			//判断查询结果,检验公司代码是否已经注册
			if(null!=ep && ep.getPassword().equals(StringUtils.md5Password(password))){
				return ep;
			}else{
				return null;
			}
		}
		else{
			return null;
		}
	}

	@Override
	public void logout(Employee employee) {

	}

	@Override
	public Employee findEmployee(Integer id) {
		return employeeDao.find(id);
	}

	@Override
	public void addEmployee(Employee employee) throws ParameterException, SystemException {
		//数据验证
		validate(employee);
		
		CompanySettingInfo info = companySettingInfoDao.findByCompanyCode(employee.getCompanyCode());
		//初始密码设置
		employee.setPassword(info.getInitPassword());
		//添加
		employeeDao.add(employee);
	}
	
	private void validate(Employee employee) throws ParameterException{
		//为空验证
		if(null==employee.getEmployeeId()
				||StringUtils.isBlank(employee.getEmployeeId())
				||StringUtils.isEmpty(employee.getEmployeeId())){
			throw new ParameterException("addEmployee", "employeeId", "员工号不能为空!", employee, "employee");
		}
		//为空验证
		if(null==employee.getEmployeeName()
				||StringUtils.isBlank(employee.getEmployeeName())
				||StringUtils.isEmpty(employee.getEmployeeName())){
			throw new ParameterException("addEmployee", "employeeId", "员工姓名不能为空!", employee, "employee");
		}
		//电话号码验证
		if(null==employee.getPhoneNumber()
				||StringUtils.isBlank(employee.getPhoneNumber())
				||StringUtils.isEmpty(employee.getPhoneNumber())){
			throw new ParameterException("addEmployee", "phoneNumber", "电话号码不能为空!", employee, "employee");
		}
		//格式验证
		if(!StringUtils.isNumber(employee.getPhoneNumber())){
			throw new ParameterException("addEmployee", "phoneNumber", "电话号码格式正确!", employee, "employee");
		}
		Integer id = employeeDao.findId(employee.getPhoneNumber());
		//数据库存在验证
		if(null!=id){
			throw new ParameterException("addEmployee", "phoneNumber", "电话号码已经被注册!", employee, "employee");
		}
		//个人邮箱验证
		if(null==employee.getEmail()
				||StringUtils.isBlank(employee.getEmail())
				||StringUtils.isEmpty(employee.getEmail())){
			throw new ParameterException("addEmployee", "email", "个人邮箱不能为空!", employee, "employee");
		}
		//邮箱格式验证
		if(!StringUtils.isEmail(employee.getEmail())){
			throw new ParameterException("addEmployee", "email", "邮箱格式不正确!", employee, "employee");
		}
		id = employeeDao.findId(employee.getEmail());
		//数据库存在验证
		if(null!=id){
			throw new ParameterException("addEmployee", "email", "邮箱已经被注册!", employee, "employee");
		}
		//密码验证
		/*if(null==employee.getPassword()
				||StringUtils.isBlank(employee.getPassword())
				||StringUtils.isEmpty(employee.getPassword())){
			throw new ParameterException("addEmployee", "password", "密码不能为空!", employee, "employee");
		}*/
	}

	@Override
	public void updatePassword(Employee employee,String oldPassword,String password) throws ParameterException, SystemException {
		
		//旧密码为空验证
		if(null==oldPassword
				||StringUtils.isBlank(oldPassword)
				||StringUtils.isEmpty(oldPassword)){
			throw new ParameterException("updatePassword", "oldPassword", "旧密码不能为空!", null, null);
		}
		//新密码为空验证
		if(null==password
				||StringUtils.isBlank(password)
				||StringUtils.isEmpty(password)){
			throw new ParameterException("updatePassword", "password", "密码不能为空!", employee.getPassword(), "password");
		}
		//旧密码是否一致
		if(!StringUtils.md5Password(oldPassword).equals(employee.getPassword())){
			throw new ParameterException("updatePassword", "oldPassword", "旧密码验证错误!", null, null);
		}
		
		employeeDao.updatePw(StringUtils.md5Password(password), employee.getId());
	}

	@Override
	public Employee updateEmployeeInfo(Employee employee) throws ParameterException {
		
		Employee oldEmployee = employeeDao.find(employee.getId());
		
		//为空验证
		if(null==employee.getEmployeeId()
				||StringUtils.isBlank(employee.getEmployeeId())
				||StringUtils.isEmpty(employee.getEmployeeId())){
			throw new ParameterException("updateEmployeeInfo", "employeeId", "员工号不能为空!", employee, "employee");
		}
		//为空验证
		if(null==employee.getEmployeeName()
				||StringUtils.isBlank(employee.getEmployeeName())
				||StringUtils.isEmpty(employee.getEmployeeName())){
			throw new ParameterException("updateEmployeeInfo", "employeeId", "员工姓名不能为空!", employee, "employee");
		}
		//电话号码验证
		if(null==employee.getPhoneNumber()
				||StringUtils.isBlank(employee.getPhoneNumber())
				||StringUtils.isEmpty(employee.getPhoneNumber())){
			throw new ParameterException("updateEmployeeInfo", "phoneNumber", "电话号码不能为空!", employee, "employee");
		}
		//格式验证
		if(!StringUtils.isNumber(employee.getPhoneNumber())){
			throw new ParameterException("updateEmployeeInfo", "phoneNumber", "电话号码格式正确!", employee, "employee");
		}
		
		if(null!=oldEmployee){
			if(!oldEmployee.getPhoneNumber().equals(employee.getPhoneNumber())){
				List<Employee> employeeList = employeeDao.findList(employee.getPhoneNumber());
				//数据库存在验证
				if(null!=employeeList&&employeeList.size()>0){
					throw new ParameterException("updateEmployeeInfo", "phoneNumber", "电话号码已经被注册!", employee, "employee");
				}
			}
		}
		
		//个人邮箱验证
		if(null==employee.getEmail()
				||StringUtils.isBlank(employee.getEmail())
				||StringUtils.isEmpty(employee.getEmail())){
			throw new ParameterException("updateEmployeeInfo", "email", "个人邮箱不能为空!", employee, "employee");
		}
		//邮箱格式验证
		if(!StringUtils.isEmail(employee.getEmail())){
			throw new ParameterException("updateEmployeeInfo", "email", "邮箱格式不正确!", employee, "employee");
		}
		
		if(null!=oldEmployee){
			if(!oldEmployee.getEmail().equals(employee.getEmail())){
				List<Employee> employeeList = employeeDao.findList(employee.getEmail());
				//数据库存在验证
				if(null!=employeeList&&employeeList.size()>0){
					throw new ParameterException("updateEmployeeInfo", "email", "邮箱已经被注册!", employee, "employee");
				}
			}
		}
		
		employeeDao.update(employee);
		
		return employeeDao.find(employee.getId());
	}
}
