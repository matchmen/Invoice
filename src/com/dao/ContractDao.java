package com.dao;

import com.model.Contract;

public interface ContractDao {

	public void add(Contract contract);
	
	public void remove(Integer id);
	
	public void update(Contract contract);
	
	public Contract find(Integer id);
	
	public Contract findByContractId(String ContractId);
}
