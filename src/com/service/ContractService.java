package com.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.exception.ParameterException;
import com.model.Contract;

public interface ContractService {

	public Contract createContract(CommonsMultipartFile file) throws ParameterException;
	
	public void preserveContract(Contract contract) throws ParameterException;
	
	public Contract findContract(String contractId) throws ParameterException;
	
	public void updateContract(Contract contract) throws ParameterException;
}
