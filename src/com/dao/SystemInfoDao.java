package com.dao;

import com.model.SystemInfo;

public interface SystemInfoDao {

	public void add(SystemInfo systemInfo); 
	
	public void update(SystemInfo systemInfo);
	
	public SystemInfo find();
	
	public void remove(Integer id);
	
	
}
