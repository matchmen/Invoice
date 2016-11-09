package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.BaseInfoDao;
import com.dao.CompanyDao;
import com.dao.UserDao;
import com.model.Company;
import com.service.CompanyService;

@Repository("companyService")
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BaseInfoDao baseInfoDao;
	

	@Override
	public void updateCompanyInfo(Company company) {
		//数据验证
		//validate(company);
		//添加
		companyDao.update(company);
	}

	@Override
	public void cancellate(Company company) {
		
	}


	@Override
	public Company findByCompanyCode(String companyCode) {
		
		Company company = companyDao.find(companyCode);
		
		return company;
	}

}
