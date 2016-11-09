package com.dao;

import java.util.List;

import com.exception.SystemException;
import com.model.User;

public interface UserDao {
	
	public void add(User employee) throws SystemException ;
	
	public void remove(Integer id);
	
	public void update(User employee);
	
	public User find(Integer id);
	
	public Integer findId(String str);
	
	public void updatePw(String str,Integer id);
	
	public List<User> findList(String str);
	
	public User findByStr(String str);
	
	public void addBind(Integer comId,Integer userId);
	
	public Integer findComId(Integer userId);
	
	public void removeBind(Integer userId);
	
	public void leaveCompany(Integer userId);
}
