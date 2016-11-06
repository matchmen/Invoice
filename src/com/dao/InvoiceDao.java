package com.dao;

import java.util.List;

import com.model.Invoice;


public interface InvoiceDao {

	public void add(Invoice invoice);
	
	public void remove(Integer id);
	
	public void update(Invoice invoice);
	
	public Invoice find(Integer id);
	
	public Invoice findByConIDAndIndex(String contractId,Integer index);
	
	public List<Invoice> findByContractId(String contractId);
	
	public Invoice findByInvoiceId(String invoiceId);
	
	public List<Invoice> find();
}
