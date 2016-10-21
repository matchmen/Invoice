package com.dao;

import com.exception.SystemException;
import com.model.CompanySettingInfo;

public interface CompanySettingInfoDao {
	
	public void add(CompanySettingInfo companySettingInfo)  throws SystemException;
	
	public void reomve(String id);
	
	public void update(CompanySettingInfo companySettingInfo) throws  SystemException ;
	
	public CompanySettingInfo find(String id);
	
	public CompanySettingInfo findByCompanyCode(String companyCode);
}
