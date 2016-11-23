package com.bean;

import java.util.List;

import com.model.User;
import com.model.Invoice;

public class InvoiceBean {

	private User user;

	private Invoice invoice;
	
	private List<Invoice> invoiceList;
	
	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}
