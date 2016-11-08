package com.controller.contract;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bean.Admin;
import com.bean.ContractBean;
import com.constant.Constants;
import com.controller.BaseController;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.Contract;
import com.model.Employee;
import com.service.ContractService;

@Controller
@RequestMapping("/contract.do")
public class ContractController extends BaseController {
	
	@Autowired
	private ContractService contractService;
	@Autowired
	private String sourceUrl;
	
	@RequestMapping(params="method=addContractInfoManage")
	public String addContractInfoManage(Admin admin,HttpSession httpSession) throws ParameterException{
		
		Employee employee = (Employee)httpSession.getAttribute("currEmployee");
		
		admin.setEmployee(employee);
		
		contractService.addContractInfoManage(admin);
		
		return "addContractInfoManageSuccess";
	}
	@RequestMapping(params="method=addContractInfoManagePage")
	public String addContractInfoManagePage() throws ParameterException{
		
		return "addContractInfoManage";
	}
	
	@RequestMapping(params="method=updateContractInfo")
	public String updateContractInfo(ContractBean contractbean,Contract contract,ModelMap map,HttpSession httpSession) throws ParameterException, BusinessException{
		
		Employee employee = (Employee)httpSession.getAttribute("currEmployee");
		
		contractbean.setContract(contract);
		
		contractService.updateContract(contractbean,employee.getId());
		
		return "updateContractInfoSuccess";
	}
	
	@RequestMapping(params="method=findContractInfo")
	public String findContractInfo(String contractIds,ModelMap map,HttpSession httpSession) throws ParameterException{
		
		Employee employee = (Employee)httpSession.getAttribute("currEmployee");
		
		ContractBean contractBean = contractService.findContract(contractIds,employee.getId());
		if(null==contractBean){
			map.addAttribute("errorMsg", "查询信息不存在");
		}else{
			if(null!=contractBean.getContract())
				map.addAttribute("contract", contractBean.getContract());
			if(null!=contractBean.getInvoiceList())
			map.addAttribute("invoiceList", contractBean.getInvoiceList());
		}
		
		return "updateContractInfo";
	}
	
	@RequestMapping(params="method=updateContractInfoPage")
	public String updateContractInfoPage() throws ParameterException{
		
		return "updateContractInfo";
	}
	
	@RequestMapping(params="method=checkContractInfo")
	public String checkContractInfo(String contractId,ModelMap map,HttpSession httpSession) throws ParameterException{
		Employee employee = (Employee)httpSession.getAttribute("currEmployee");
		
		ContractBean contractBean = contractService.findContract(contractId,employee.getId());
		
		if(null==contractBean){
			map.addAttribute("errorMsg", "查询信息不存在");
		}else{
			if(null!=contractBean.getContract()){
				map.addAttribute("contract", contractBean.getContract());
				if(null!=contractBean.getInvoiceList())
					map.addAttribute("invoiceList", contractBean.getInvoiceList());
			}
		}
		
		return "checkContractInfo";
	}

	@RequestMapping(params="method=importContractFile")
	public String importContractFile(ContractBean contractBean,Contract contract, HttpSession httpSession) throws ParameterException, SystemException{
		
		Employee employee = (Employee)httpSession.getAttribute("currEmployee");
		
		contractBean.setContract(contract);
		
		contractService.preserveContract(contractBean,employee.getId());
		
		return "importContractFileSuccess";
	}
	
	@RequestMapping(params="method=importContractFilePage")
	public String importContractFilePage(){
		
		return "importContractFile";
	}
	@RequestMapping(params="method=checkContractInfoPage")
	public String checkContractInfoPage(){
		
		return "checkContractInfo";
	}
	
	@RequestMapping(params="method=downloadExcel")
	public String downloadExcel(HttpServletResponse response,HttpServletRequest req) throws SystemException, UnsupportedEncodingException{
		//设置文件MIME类型  
        response.setContentType(req.getServletContext().getMimeType(Constants.EXCEL_FILENAME));  
        //设置Content-Disposition  
        response.setHeader("Content-Disposition", "attachment;filename="+new String(Constants.EXCEL_FILENAME.getBytes(),"iso-8859-1"));  
        //读取目标文件，通过response将目标文件写到客户端  
        //获取目标文件的绝对路径  
        String fullFileName = sourceUrl+Constants.EXCEL_FILENAME;
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
		
        return "importContractFile";
	}
	
	@RequestMapping(params="method=uploadExcel")
	public String uploadExcel(@RequestParam("file") CommonsMultipartFile file, ModelMap map) throws ParameterException, BusinessException {
		
		ContractBean contractBean = contractService.getContractInfo(file);
		
		map.addAttribute("contract", contractBean.getContract());
		if(null!=contractBean.getInvoiceList())					
			map.addAttribute("invoiceList",contractBean.getInvoiceList());
		
		return "importContractFile";
	}
	
}
