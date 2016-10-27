package com.controller.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.controller.BaseController;
import com.exception.ParameterException;
import com.model.Contract;
import com.service.ContractService;

@Controller
@RequestMapping("/contract.do")
public class ContractController extends BaseController {
	
	@Autowired
	private ContractService contractService;
	
	
	
	@RequestMapping(params="method=updateContractInfo")
	public String updateContractInfo(Contract contract,ModelMap map) throws ParameterException{
		
		contractService.updateContract(contract);
		
		return "updateContractInfoSuccess";
	}
	
	@RequestMapping(params="method=findContractInfo")
	public String findContractInfo(String contractIds,ModelMap map) throws ParameterException{
		
		Contract contract = contractService.findContract(contractIds);
		
		map.addAttribute("contract", contract);
		
		return "updateContractInfo";
	}
	
	@RequestMapping(params="method=updateContractInfoPage")
	public String updateContractInfoPage() throws ParameterException{
		
		return "updateContractInfo";
	}
	
	@RequestMapping(params="method=checkContractInfo")
	public String checkContractInfo(String contractId,ModelMap map) throws ParameterException{
		
		Contract contract = contractService.findContract(contractId);
		
		map.addAttribute("contract", contract);
		
		return "checkContractInfo";
	}
	
	@RequestMapping(params="method=importContractFile")
	public String importContractFile(Contract contract) throws ParameterException{
		
		contractService.preserveContract(contract);
		
		return "importContractFileSuccess";
	}
	
	@RequestMapping(params="method=importContractFilePage")
	public String importContractFilePage(){
		
		return "importContractFile";
	}
	@RequestMapping(params="method=checkContractInfoPage")
	public String checkContractInfoPage(){
		
		return "checkContractInfo";
	}
	
	@RequestMapping(params="method=downloadExcel")
	public String downloadExcel(){
		
		return "importContractFile";
	}
	
	@RequestMapping(params="method=uploadExcel")
	public String uploadExcel(@RequestParam("file") CommonsMultipartFile file, ModelMap map) throws ParameterException {
		
		Contract contract = contractService.createContract(file);
		map.addAttribute("contract", contract);
		return "importContractFile";
	}
	
}
