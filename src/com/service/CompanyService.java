package com.service;

import com.model.Company;

public interface CompanyService {
	
	public void cancellate(Company company);
	
	public Company findByCompanyCode(String companyCode);
	
	public void updateCompanyInfo(Company company);
}
