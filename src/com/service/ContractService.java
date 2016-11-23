package com.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bean.Admin;
import com.bean.ContractBean;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.User;

public interface ContractService {

	public ContractBean getContractInfo(CommonsMultipartFile file) throws ParameterException, BusinessException;
	
	public void preserveContract(ContractBean contractBean,User user) throws ParameterException, SystemException;
	
	public ContractBean findContract(String contractId,Integer empId) throws ParameterException;
	
	public void updateContract(ContractBean contractBean,Integer empId) throws ParameterException, BusinessException;
	
	public void addContractInfoManage(Admin admin,User user) throws ParameterException;
	
	public void addInvoiceInfoManage(Admin admin) throws ParameterException;
	
	public ContractBean findContractList(String contractName,String firstName,String signDateStart,String signDateEnd,User user) throws ParameterException;
}
