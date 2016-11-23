package com.bean;

import java.util.List;

import com.model.Contract;
import com.model.Invoice;

public class ContractBean {
	
	private Contract contract;
	
	private List<Invoice> invoiceList;
	
	private List<Contract> contractList;

	public List<Contract> getContractList() {
		return contractList;
	}

	public void setContractList(List<Contract> contractList) {
		this.contractList = contractList;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
}
