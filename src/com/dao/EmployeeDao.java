package com.dao;

import java.util.List;

import com.exception.SystemException;
import com.model.Employee;

public interface EmployeeDao {
	
	public void add(Employee employee) throws SystemException ;
	
	public void remove(String id);
	
	public void update(Employee employee);
	
	public Employee find(Integer id);
	
	public Integer findId(String str);
	
	public void updatePw(String str,Integer id);
	
	public List<Employee> findList(String str);
}
