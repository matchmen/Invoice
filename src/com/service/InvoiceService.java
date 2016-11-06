package com.service;

import com.bean.InvoiceBean;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.model.Invoice;

public interface InvoiceService {

	
	public Invoice checkInvoiceInfo(String invoice,Integer empId ) throws ParameterException;
	
	public void updateInvoiceInfo(Invoice invoice,Integer empId) throws BusinessException,ParameterException;

	public void addInvoice(InvoiceBean bean) throws BusinessException,ParameterException;
}
