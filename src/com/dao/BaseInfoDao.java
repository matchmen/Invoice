package com.dao;

import com.exception.SystemException;
import com.model.BaseInfo;

public interface BaseInfoDao {
	
	public void add(BaseInfo baseInfo)  throws SystemException;
	
	public void reomve(String id);
	
	public void update(BaseInfo baseInfo) throws  SystemException ;
	
	public BaseInfo find(String id);
	
	public BaseInfo findByComId(Integer id);
	
	public BaseInfo findByUserId(Integer id);
	
}
