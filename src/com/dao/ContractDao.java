package com.dao;

import java.util.List;

import com.model.Contract;

public interface ContractDao {

	public void add(Contract contract);
	
	public void remove(Integer id);
	
	public void update(Contract contract);
	
	public Contract find(Integer id);
	
	public Contract findByContractId(String ContractId,Integer userId);
	
	public Contract findByContractId(String ContractId);
	
	public List<Contract> findList(String contractName,String firstName,String signDateStart,String signDateEnd,Integer userId);
}
