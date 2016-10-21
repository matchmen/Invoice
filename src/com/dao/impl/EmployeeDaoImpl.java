package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dao.DaoJdbcTemplate;
import com.dao.EmployeeDao;
import com.exception.SystemException;
import com.model.Employee;
@Repository("employeeDao")
public class EmployeeDaoImpl extends DaoJdbcTemplate implements EmployeeDao {

	@Override
	public void add(Employee employee) throws SystemException {
		String isAmain = "0";
		if(employee.getIsAdmin()){
			isAmain = "1";
		}
		//sql拼接
		StringBuffer sb = new StringBuffer("INSERT INTO t_employee(EMPLOYEE_ID,EMPLOYEE_NAME,COMPANY_NAME,COMPANY_CODE,PWORD,EMAIL,PHONE_NUMBER,DEPARTMENT,JOB,ADMINISTRATOR) VALUES('")
		//员工编号
		.append(employee.getEmployeeId())
		.append("','")
		//姓名
		.append(employee.getEmployeeName())
		.append("','")
		//单位
		.append(employee.getCompanyName())
		.append("','")
		//单位代码
		.append(employee.getCompanyCode())
		.append("','")
		//密码
		.append(employee.getPassword())
		.append("','")
		//邮箱
		.append(employee.getEmail())
		.append("','")
		//电话
		.append(employee.getPhoneNumber())
		.append("','")
		//部门
		.append(employee.getDepartment())
		.append("','")
		//职位
		.append(employee.getPosition())
		.append("','")
		//职位
		.append(isAmain)
		.append("')");
		//插入
		getJdbcTempalte().execute(sb.toString());
		
	}

	@Override
	public void remove(String id) {

		String sql = "update t_employee set ABLED = 0 WHERE ID = ? ";
		
		getJdbcTempalte().update(sql, new Object[]{id});
		
	}

	@Override
	public void update(Employee employee) {
		
		StringBuffer sb = new StringBuffer()
			.append("update t_employee set ")
			.append("EMPLOYEE_ID = ? ,")
			.append("EMPLOYEE_NAME = ? ,")
			.append("COMPANY_NAME = ? ,")
			.append("COMPANY_CODE = ? ,")
			.append("EMAIL = ? ,")
			.append("PHONE_NUMBER = ? ,")
			.append("DEPARTMENT = ? ,")
			.append("JOB = ? ")
			.append("where ID = ? ");

		getJdbcTempalte().update(sb.toString(),	new Object[]{
			employee.getEmployeeId(),
			employee.getEmployeeName(),
			employee.getCompanyName(),
			employee.getCompanyCode(),
			employee.getEmail(),
			employee.getPhoneNumber(),
			employee.getDepartment(),
			employee.getPosition(),
			employee.getId()});
		
	}

	@Override
	public Employee find(Integer id) {
		
		String sql = "select * from t_employee where ID = ?";
		List<Employee> employeeList = getJdbcTempalte().query(sql, new Object[]{id}, new RowMapper<Employee>(){

			@Override
			public Employee mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Employee employee = new Employee();
				employee.setId(rs.getInt("ID"));
				employee.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				employee.setEmployeeName(rs.getString("EMPLOYEE_NAME"));
				employee.setAbled(rs.getBoolean("ABLED"));
				employee.setCompanyName(rs.getString("COMPANY_NAME"));
				employee.setCompanyCode(rs.getString("COMPANY_CODE"));
				employee.setDepartment(rs.getString("DEPARTMENT"));
				employee.setEmail(rs.getString("EMAIL"));
				employee.setPassword(rs.getString("PWORD"));
				employee.setPosition(rs.getString("JOB"));
				employee.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				employee.setIsAdmin(rs.getBoolean("ADMINISTRATOR"));
				return employee;
			}
		});
		//检索结果件数大于零时返回第一条
		if(employeeList.size()>0){
			return employeeList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Employee> findList(String str) {
		
		String sql = "select * from t_employee where PHONE_NUMBER = ? or EMAIL = ? ";
		List<Employee> employeeList = getJdbcTempalte().query(sql, new Object[]{str,str}, new RowMapper<Employee>(){
			@Override
			public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
				Employee employee = new Employee();
				employee.setId(rs.getInt("ID"));
				employee.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				employee.setEmployeeName(rs.getString("EMPLOYEE_NAME"));
				employee.setAbled(rs.getBoolean("ABLED"));
				employee.setCompanyName(rs.getString("COMPANY_NAME"));
				employee.setCompanyCode(rs.getString("COMPANY_CODE"));
				employee.setDepartment(rs.getString("DEPARTMENT"));
				employee.setEmail(rs.getString("EMAIL"));
				employee.setPassword(rs.getString("PWORD"));
				employee.setPosition(rs.getString("JOB"));
				employee.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				employee.setIsAdmin(rs.getBoolean("ADMINISTRATOR"));
				return employee;
			}
		});
		return employeeList;
	}

	@Override
	public void updatePw(String str,Integer id) {
		
		String sql = "update t_employee set PWORD = ? where id = ?";
		
		getJdbcTempalte().update(sql, new Object[]{str,id});
		
	}
	
	@Override
	public Integer findId(String str) {
		
		String sql = "select ID from t_employee where PHONE_NUMBER = ? or EMAIL = ? ";
		List<Integer> idList = getJdbcTempalte().query(sql, new Object[]{str,str}, new RowMapper<Integer>(){
			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt("ID");
			}
		});
		if(idList.size()>0)
			return idList.get(0);
		else
			return null;
	}
}
