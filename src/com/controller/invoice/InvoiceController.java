package com.controller.invoice;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bean.InvoiceBean;
import com.controller.BaseController;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.model.User;
import com.model.Invoice;
import com.service.InvoiceService;
import com.util.StringUtils;

@Controller
@RequestMapping("/invoice.do")
public class InvoiceController extends BaseController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping(params="method=checkInvoiceInfoPage")
	public String checkInvoiceInfoPage(){
		return "checkInvoiceInfo";
	}
	@RequestMapping(params="method=findInvoiceInfo")
	public String findInvoiceInfo(String invoId,HttpSession session,ModelMap map) throws ParameterException{
		
		if(null==invoId
				||StringUtils.isBlank(invoId)
				||StringUtils.isEmpty(invoId)){
			throw new ParameterException("updateInvoiceInfo", "", "发票编号不能为空", null, "");
		}
		
		User emp = (User)session.getAttribute("currEmployee");
		
		Invoice invoice = invoiceService.checkInvoiceInfo(invoId, emp.getId());
		
		if(null==invoice){
			map.addAttribute("errMessage", "发票编号不存在!");
		}
		
		map.addAttribute("invoice", invoice);
		
		return "updateInvoiceInfo";
	}
	
	@RequestMapping(params="method=checkInvoiceInfo")
	public String checkInvoiceInfo(String invoiceId,HttpSession session,ModelMap map) throws ParameterException{
		
		User emp = (User)session.getAttribute("currUser");
		
		Invoice invoice = invoiceService.checkInvoiceInfo(invoiceId, emp.getId());
		
		if(null==invoice){
			map.addAttribute("errMessage", "发票编号不存在!");
		}
		
		map.addAttribute("invoice", invoice);
		
		return "checkInvoiceInfo";
	}
	
	@RequestMapping(params="method=updateInvoiceInfoPage")
	public String updateInvoiceInfoPage(){
		return "updateInvoiceInfo";
	}
	
	@RequestMapping(params="method=updateInvoiceInfo")
	public String updateInvoiceInfo(Invoice invoice,HttpSession session) throws BusinessException, ParameterException{
		
		User emp = (User)session.getAttribute("currUser");
		
		invoiceService.updateInvoiceInfo(invoice, emp.getId());
		
		return "updateInvoiceInfoSuccess";
	}
	
	@RequestMapping(params="method=addInvoice")
	public String addInvoice(Invoice invoice,HttpSession session) throws BusinessException, ParameterException{
		
		User emp = (User)session.getAttribute("currUser");
		
		InvoiceBean bean = new InvoiceBean();
		
		bean.setEmplpoyee(emp);
		
		bean.setInvoice(invoice);
		
		invoiceService.addInvoice(bean);
		
		return "updateInvoiceInfoSuccess";
	}
	
	@RequestMapping(params="method=addInvoicePage")
	public String addInvoicePage() throws BusinessException, ParameterException{
		
		return "addInvoiceInfo";
	}
	
}
