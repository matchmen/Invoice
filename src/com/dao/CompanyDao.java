package com.dao;

import com.model.Company;

public interface CompanyDao {
	
	public void add(Company company);
	
	public void remove(String companyName);
	
	public void update(Company company);
	
	public Company find(String companyCode);
	
	public Company findByOtherInfo(String str);
}
