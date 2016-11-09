package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dao.DaoJdbcTemplate;
import com.dao.UserDao;
import com.exception.SystemException;
import com.model.User;
@Repository("userDao")
public class UserDaoImpl extends DaoJdbcTemplate implements UserDao {

	@Override
	public void add(User user) throws SystemException {
		
		int isCom = 0;
		
		int isAd = 0;
		if(user.getIsCompany()){
			isCom = 1;
		}
		if(user.getIsAdmin()){
			isAd = 1;
		}
		
		//sql拼接
		StringBuffer sb = new StringBuffer("INSERT INTO t_user(EMPLOYEE_ID,USERNAME,PWORD,EMAIL,PHONE_NUMBER,DEPARTMENT,JOB,IS_COMPANY,ADMINISTRATOR) VALUES('")
		//员工编号
		.append(user.getEmployeeId())
		.append("','")
		//姓名
		.append(user.getUsername())
		.append("','")
		//密码
		.append(user.getPassword())
		.append("','")
		//邮箱
		.append(user.getEmail())
		.append("','")
		//电话
		.append(user.getPhoneNumber())
		.append("','")
		//部门
		.append(user.getDepartment())
		.append("','")
		//职位
		.append(user.getPosition())
		.append("','")
		//是否属于公司
		.append(isCom)
		.append("','")
		//管理员
		.append(isAd)
		.append("')");
		//插入
		getJdbcTempalte().execute(sb.toString());
		
	}

	@Override
	public void remove(Integer id) {

		String sql = "update t_user set ABLED = 0 WHERE ID = ? ";
		
		getJdbcTempalte().update(sql, new Object[]{id});
		
	}

	@Override
	public void update(User user) {
		
		StringBuffer sb = new StringBuffer()
			.append("update t_user set ")
			.append("EMPLOYEE_ID = ? ,")
			.append("USERNAME = ? ,")
			.append("EMAIL = ? ,")
			.append("PHONE_NUMBER = ? ,")
			.append("DEPARTMENT = ? ,")
			.append("JOB = ? ")
			.append("where ID = ? ");

		getJdbcTempalte().update(sb.toString(),	new Object[]{
			user.getEmployeeId(),
			user.getUsername(),
			user.getEmail(),
			user.getPhoneNumber(),
			user.getDepartment(),
			user.getPosition(),
			user.getId()});
		
	}

	@Override
	public User find(Integer id) {
		
		String sql = "select * from t_user where ID = ? and ABLED = 1";
		List<User> employeeList = getJdbcTempalte().query(sql, new Object[]{id}, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int arg1)
					throws SQLException {
				User uu = new User();
				uu.setId(rs.getInt("ID"));
				uu.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				uu.setUsername(rs.getString("USERNAME"));
				uu.setAbled(rs.getBoolean("ABLED"));
				uu.setDepartment(rs.getString("DEPARTMENT"));
				uu.setEmail(rs.getString("EMAIL"));
				uu.setPassword(rs.getString("PWORD"));
				uu.setPosition(rs.getString("JOB"));
				uu.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				uu.setIsAdmin(rs.getBoolean("ADMINISTRATOR"));
				uu.setIsAdmin(rs.getBoolean("ADMINISTRATOR"));
				uu.setIsCompany(rs.getBoolean("IS_COMPANY"));
				return uu;
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
	public List<User> findList(String str) {
		
		String sql = "select * from t_user where PHONE_NUMBER = ? or EMAIL = ? ";
		List<User> employeeList = getJdbcTempalte().query(sql, new Object[]{str,str}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User uu = new User();
				uu.setId(rs.getInt("ID"));
				uu.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				uu.setUsername(rs.getString("USERNAME"));
				uu.setAbled(rs.getBoolean("ABLED"));
				uu.setDepartment(rs.getString("DEPARTMENT"));
				uu.setEmail(rs.getString("EMAIL"));
				uu.setPassword(rs.getString("PWORD"));
				uu.setPosition(rs.getString("JOB"));
				uu.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				uu.setIsAdmin(rs.getBoolean("ADMINISTRATOR"));
				uu.setIsAdmin(rs.getBoolean("ADMINISTRATOR"));
				uu.setIsCompany(rs.getBoolean("IS_COMPANY"));
				return uu;
			}
		});
		return employeeList;
	}

	@Override
	public void updatePw(String str,Integer id) {
		
		String sql = "update t_user set PWORD = ? where id = ?";
		
		getJdbcTempalte().update(sql, new Object[]{str,id});
		
	}
	
	@Override
	public Integer findId(String str) {
		
		String sql = "select ID from t_user where PHONE_NUMBER = ? or EMAIL = ? ";
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

	@Override
	public User findByStr(String str) {
		
		String sql = "select * from t_user where ABLED = 1 and PHONE_NUMBER = ? or EMAIL = ?  ";
		List<User> employeeList = getJdbcTempalte().query(sql, new Object[]{str,str}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User employee = new User();
				employee.setId(rs.getInt("ID"));
				employee.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				employee.setUsername(rs.getString("USERNAME"));
				employee.setAbled(rs.getBoolean("ABLED"));
				employee.setDepartment(rs.getString("DEPARTMENT"));
				employee.setEmail(rs.getString("EMAIL"));
				employee.setPassword(rs.getString("PWORD"));
				employee.setPosition(rs.getString("JOB"));
				employee.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				employee.setIsAdmin(rs.getBoolean("ADMINISTRATOR"));
				employee.setIsAdmin(rs.getBoolean("ADMINISTRATOR"));
				employee.setIsCompany(rs.getBoolean("IS_COMPANY"));
				return employee;
			}
		});
		if(null!=employeeList&&employeeList.size()>0){
			return employeeList.get(0);
		}
		return null;
	}

	@Override
	public void addBind(Integer comId, Integer userId) {
		
		StringBuffer sql = new StringBuffer("insert into T_COMPANY_USER(USER_ID,COMPANY_ID,ABLED) values(");
		
		sql.append(userId)
		.append(",")
		.append(comId)
		.append(",")
		.append(1)
		.append(")");
		
		getJdbcTempalte().execute(sql.toString());
		
	}
	
	@Override
	public Integer findComId(Integer userId){
		
		String sql = "select COMPANY_ID from T_COMPANY_USER WHERE USER_ID = ? AND ABLED = 1 ";
		
		List<Integer> idList = getJdbcTempalte().query(sql, new Object[]{userId}, new RowMapper<Integer>(){

			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt("COMPANY_ID");
			}
		});
		if(null!=idList&&idList.size()>0){
			return idList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void removeBind(Integer userId) {
		
		String sql = "update T_COMPANY_USER set ABLED = 0 WHERE USER_ID = ?";
		
		getJdbcTempalte().update(sql, new Object[]{userId});
		
	}
	@Override
	public void leaveCompany(Integer userId) {
		
		String sql = "update T_USER set IS_COMPANY = 0 WHERE USER_ID = ?";
		
		getJdbcTempalte().update(sql, new Object[]{userId});
		
	}
}
