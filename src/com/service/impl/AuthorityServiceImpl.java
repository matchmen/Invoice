package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.AuthorityDao;
import com.model.Authority;
import com.service.AuthorityService;
@Repository("authorityService")
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	private AuthorityDao authorityDao;
	@Override
	public Boolean authValidate(Authority authority) {
		Authority au = authorityDao.findCon(authority);
		if(null!=au){
			return true;
		}
		return null;
	}
	
	@Override
	public void addAuthority(Authority authority) {
		
	}
	
}
