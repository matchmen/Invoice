package com.service;

import com.model.Authority;

public interface AuthorityService {

	public Boolean authValidate(Authority authority);
	
	public void addAuthority(Authority authority);
	
}
