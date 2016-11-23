package com.dao;

import java.util.List;

import com.model.Invoice;


public interface InvoiceDao {

	public void add(Invoice invoice);
	
	public void remove(Integer id);
	
	public void update(Invoice invoice);
	
	public Invoice find(Integer id,Integer userId);
	
	public Invoice findByConIDAndIndex(String contractId,Integer index);
	
	public List<Invoice> findByContractId(String contractId);
	
	public Invoice findByInvoiceId(String invoiceId,Integer userId);
	
	public Invoice findByInvoiceId(String invoiceId);
	
	public List<Invoice> find();
	
	public List<Invoice> findList(String firstName,String makeInvoceDateStart,String makeInvoceDateEnd,String paymentDateStart,String paymentDateEnd,String invStatus,Integer userId );
}
