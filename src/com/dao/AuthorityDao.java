package com.dao;

import java.util.List;

import com.model.Authority;

public interface AuthorityDao {

	public void addCon(Authority authority); 
	
	public void removeCon(Integer id);
	
	public void updateCon(Authority authority);
	
	public Authority findCon(Authority authority);
	
	public List<Integer> findConByEmpId(Integer empId);
	
	public void addInv(Authority authority); 
	
	public void removeInv(Integer id);
	
	public void updateInv(Authority authority);
	
	public Authority findInv(Authority authority);
	
	public List<Integer> findInvByEmpId(Integer empId);
	
	public List<Authority> findComIdByInvId(Integer invId);
}
