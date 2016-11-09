package com.service;

import com.bean.UserBean;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.BaseInfo;
import com.model.User;

public interface UserService {

	public User login(String str,String password) throws SystemException;
	
	public void logout(User employee);
	
	public User findUser(Integer id);
	
	public void addUser(User user,User manager) throws ParameterException, SystemException;
	
	public void updatePassword(User employee,String oldPassword,String password) throws ParameterException, SystemException;
	
	public User updateUserInfo(User employee) throws ParameterException;
	
	public void removeUser(String str,User user) throws ParameterException, BusinessException;

	public void register(UserBean userBean) throws SystemException, ParameterException;
	
	public void setBaseInfo(BaseInfo baseInfo,User user) throws SystemException, ParameterException;
	
	public BaseInfo findBaseInfo(User user);
	
}
