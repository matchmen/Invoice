package com.controller.invoice;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bean.InvoiceBean;
import com.constant.Constants;
import com.controller.BaseController;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.User;
import com.model.Invoice;
import com.service.InvoiceService;

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
	public String findInvoiceInfo(String firstName,String makeInvoceDateStart,
			String makeInvoceDateEnd,String paymentDateStart,String paymentDateEnd,
			String invStatus,HttpSession session,ModelMap map) throws ParameterException{
		
		User user = (User)session.getAttribute("currUser");
		
		List<Invoice> invoiceList = invoiceService.checkInvoiceInfoList(firstName, 
				makeInvoceDateStart, 
				makeInvoceDateEnd, 
				paymentDateStart, 
				paymentDateEnd, 
				invStatus, 
				user);
		
		if(null==invoiceList){
			map.addAttribute("errMessage", "查询信息不存在!");
		}
		
		InvoiceBean bean = new InvoiceBean();
		
		bean.setInvoiceList(invoiceList);
		
		map.addAttribute("invoiceBean", bean);
		
		return "updateInvoiceInfo";
	}
	@RequestMapping(params="method=findInv")
	public String findInv(ModelMap map,HttpSession session,HttpServletRequest request){
		
		User user = (User)session.getAttribute("currUser");
		
		String invoiceId= request.getParameter("invoiceId");
		
		InvoiceBean bean = new InvoiceBean();
		
		Invoice invoice = invoiceService.findInv(invoiceId, user);

		bean.setInvoice(invoice);
		
		map.addAttribute("invoiceBean", bean);
		
		return "updateInvoiceInfo";
	}
	
	@RequestMapping(params="method=checkInvoiceInfo")
	public String checkInvoiceInfo(String firstName,String makeInvoceDateStart,
			String makeInvoceDateEnd,String paymentDateStart,String paymentDateEnd,
			String invStatus,HttpSession session,ModelMap map) throws ParameterException{
		
		User emp = (User)session.getAttribute("currUser");
		
		List<Invoice> invoiceList = invoiceService.checkInvoiceInfoList(firstName, 
				makeInvoceDateStart, 
				makeInvoceDateEnd, 
				paymentDateStart, 
				paymentDateEnd, 
				invStatus, 
				emp);
		
		if(null==invoiceList||invoiceList.size()<1){
			map.addAttribute("errMessage", "查询信息不存在!");
		}
		map.addAttribute("firstName", firstName);
		map.addAttribute("makeInvoceDateStart", makeInvoceDateStart);
		map.addAttribute("makeInvoceDateEnd", makeInvoceDateEnd);
		map.addAttribute("paymentDateStart", paymentDateStart);
		map.addAttribute("invStatus", invStatus);
		map.addAttribute("emp", emp);
		
		map.addAttribute("invoiceList", invoiceList);
		
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
	public String addInvoice(InvoiceBean invoiceBean,HttpSession session) throws BusinessException, ParameterException{
		
		User emp = (User)session.getAttribute("currUser");
		
		invoiceBean.setUser(emp);
		
		invoiceService.addInvoice(invoiceBean);
		
		return "updateInvoiceInfoSuccess";
	}
	
	@RequestMapping(params="method=addInvoicePage")
	public String addInvoicePage() throws BusinessException, ParameterException{
		
		return "addInvoiceInfo";
	}
	
	@RequestMapping(params="method=uploadIvoice")
	public String uploadIvoice(@RequestParam("file") CommonsMultipartFile file, ModelMap map) throws ParameterException, BusinessException{
		
		List<Invoice> invoiceList = invoiceService.getInvoiceInfo(file);
		
		map.addAttribute("invoiceList", invoiceList);
		
		return "addInvoiceInfo";
	}
	
	@RequestMapping(params="method=downloadExcel")
	public String downloadExcel(HttpServletResponse response,HttpServletRequest req) throws SystemException, UnsupportedEncodingException{
		//设置文件MIME类型  
        response.setContentType(req.getServletContext().getMimeType(Constants.EXCEL_FILENAME_INVOICE));  
        //设置Content-Disposition  
        response.setHeader("Content-Disposition", "attachment;filename="+new String(Constants.EXCEL_FILENAME_INVOICE.getBytes(),"iso-8859-1"));  
        //读取目标文件，通过response将目标文件写到客户端  
        //获取目标文件的绝对路径  
        String fullFileName = req.getSession().getServletContext().getRealPath("/download/"+Constants.EXCEL_FILENAME_INVOICE);
        //读取文件  
        InputStream in;
        OutputStream out;
		try {
			in = new FileInputStream(fullFileName);
			out = response.getOutputStream();
			 //写文件  
	        int b = 0;  
	        while((b=in.read())!= -1)  
	        {  
	            out.write(b);  
	        }  
	        in.close();  
	        response.flushBuffer();
	        out.close();
	        
		} catch (Exception e) {
			throw new SystemException("error", "下载异常！");
		}finally{
			
		}
		
        return "addInvoiceInfo";
	}
	@RequestMapping(params="method=view")
	public String view(Integer id,ModelMap map,HttpSession session){
		
		User emp = (User)session.getAttribute("currUser");
		
		Invoice invoice = invoiceService.findInv(id, emp);
		
		map.addAttribute("invoice", invoice);
		
		return "invoiceInfo";
	}
	@RequestMapping(params="method=send")
	public String send(Integer id,ModelMap map,HttpSession session,String email) throws BusinessException, SystemException{
		
		User emp = (User)session.getAttribute("currUser");
		
		invoiceService.send(id, emp,email);
		
		return "invoiceInfo";
	}
}
