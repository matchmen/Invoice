package com.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bean.InvoiceBean;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.Invoice;
import com.model.User;

public interface InvoiceService {

	public List<Invoice> checkInvoiceInfoList(String firstName,String makeInvoceDateStart,String makeInvoceDateEnd,String paymentDateStart,String paymentDateEnd,String invStatus,User user ) throws ParameterException;
	
	public void updateInvoiceInfo(Invoice invoice,Integer empId) throws BusinessException,ParameterException;

	public void addInvoice(InvoiceBean bean) throws BusinessException,ParameterException;
	
	public List<Invoice> getInvoiceInfo(CommonsMultipartFile file) throws ParameterException, BusinessException;
	
	public Invoice findInv(String invoiceId,User user);
	
	public Invoice findInv(Integer id,User user);
	
	public void send(Integer id,User user,String rMail)throws BusinessException, SystemException;

}
