package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.ConstantDao;
import com.model.Constant;
import com.service.ConstantService;
@Repository("constantService")
public class ConstantServiceImpl implements ConstantService{
	@Autowired
	private ConstantDao<Constant> constantDao;
	
	private String pageName;
	
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	@Override
	public List<String> findList(String constantName) {
		Constant constant = new Constant();
		constant.setPageName(this.pageName);
		constant.setConstantName(constantName);
		List<Constant> constants = constantDao.find(constant);
		List<String> constantVlues = new ArrayList<String>();
		for (Constant con : constants) {
			constantVlues.add(con.getConstantValue());
		}
		return constantVlues;
	}
}
