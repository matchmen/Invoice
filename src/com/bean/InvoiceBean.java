package com.bean;

import com.model.Employee;
import com.model.Invoice;

public class InvoiceBean {

	private Employee emplpoyee;

	private Invoice invoice;

	public Employee getEmplpoyee() {
		return emplpoyee;
	}

	public void setEmplpoyee(Employee emplpoyee) {
		this.emplpoyee = emplpoyee;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}
