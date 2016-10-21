package com.dao;

import com.model.Invoice;


public interface InvoiceDao {

	public void add(Invoice invoice);
	
	public void remove(Integer id);
	
	public void update(Invoice invoice);
	
	public Invoice find(Integer id);
}
