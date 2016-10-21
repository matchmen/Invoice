package com.service;

import com.bean.CompanyBean;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.Company;
import com.model.CompanySettingInfo;
import com.model.Employee;

public interface CompanyService {
	
	public void register(Company company,Employee employee) throws ParameterException, SystemException;
	
	public void updateCompanyInfo(Company company);
	
	public void cancellate(Company company);
	
	public void validate(CompanyBean bean) throws ParameterException;
	
	public Company findByCompanyCode(String companyCode);
	
	public CompanySettingInfo findCompanySettingInfo(String companyCode);
	
	public void updateCompanySettingInfo(CompanySettingInfo info) throws ParameterException, SystemException;
}
