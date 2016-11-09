package com.bean;

import com.model.User;
import com.model.Invoice;

public class InvoiceBean {

	private User emplpoyee;

	private Invoice invoice;

	public User getEmplpoyee() {
		return emplpoyee;
	}

	public void setEmplpoyee(User emplpoyee) {
		this.emplpoyee = emplpoyee;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}
