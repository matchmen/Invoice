package com.controller.contract;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.model.User;
import com.service.ContractService;

@Controller
@RequestMapping("/contract.do")
public class ContractController extends BaseController {
	
	@Autowired
	private ContractService contractService;
	
	@RequestMapping(params="method=addContractInfoManage")
	public String addContractInfoManage(Admin admin,HttpSession httpSession) throws ParameterException{
		
		User user = (User)httpSession.getAttribute("currUser");
		
		admin.setUser(user);
		
		contractService.addContractInfoManage(admin,user);
		
		return "addContractInfoManageSuccess";
	}
	@RequestMapping(params="method=addContractInfoManagePage")
	public String addContractInfoManagePage() throws ParameterException{
		
		return "addContractInfoManage";
	}
	
	@RequestMapping(params="method=updateContractInfo")
	public String updateContractInfo(ContractBean contractbean,Contract contract,ModelMap map,HttpSession httpSession) throws ParameterException, BusinessException{
		
		User user = (User)httpSession.getAttribute("currUser");
		
		contractbean.setContract(contract);
		
		contractService.updateContract(contractbean,user.getId());
		
		return "updateContractInfoSuccess";
	}
	
	@RequestMapping(params="method=findConAndInvList2")
	public String findConAndInvList2(HttpServletRequest request,ModelMap map,HttpSession httpSession) throws ParameterException{
		
		User user = (User)httpSession.getAttribute("currUser");
		
		String conId = request.getParameter("contractIds");
		
		ContractBean contractBean = contractService.findContract(conId,user.getId());
		
		if(null==contractBean){
			map.addAttribute("errorMsg", "查询信息不存在");
		}else{
			map.addAttribute("contractBean", contractBean);
		}
		
		return "updateContractInfo";
	}
	
	@RequestMapping(params="method=updateContractInfoPage")
	public String updateContractInfoPage() throws ParameterException{
		
		return "updateContractInfo";
	}
	
	@RequestMapping(params="method=findCheck")
	public String findCheck(String contractName,String firstName,String signDateStart,String signDateEnd,ModelMap map,HttpSession httpSession) throws ParameterException{
		
		User user = (User)httpSession.getAttribute("currUser");
		
		ContractBean contractBean = contractService.findContractList(contractName,firstName,signDateStart,signDateEnd,user);
		
		if(null==contractBean||null==contractBean.getContractList()||contractBean.getContractList().size()<1){
			map.addAttribute("errorMsg", "查询信息不存在");
		}else{
			map.addAttribute("contractBean", contractBean);
		}
		map.addAttribute("contractName", contractName);
		map.addAttribute("firstName", firstName);
		map.addAttribute("signDateStart", signDateStart);
		map.addAttribute("signDateEnd", signDateEnd);
		
		return "checkContractInfo";
	}
	@RequestMapping(params="method=findUpdate")
	public String findUpdate(String contractName,String firstName,String signDateStart,String signDateEnd,ModelMap map,HttpSession httpSession) throws ParameterException{
		
		User user = (User)httpSession.getAttribute("currUser");
		
		ContractBean contractBean = contractService.findContractList(contractName,firstName,signDateStart,signDateEnd,user);
		
		if(null==contractBean||null==contractBean.getContractList()||contractBean.getContractList().size()<1){
			map.addAttribute("errorMsg", "查询信息不存在");
		}else{
			map.addAttribute("contractBean", contractBean);
		}
		map.addAttribute("contractName", contractName);
		map.addAttribute("firstName", firstName);
		map.addAttribute("signDateStart", signDateStart);
		map.addAttribute("signDateEnd", signDateEnd);
		
		return "updateContractInfo";
	}
	@RequestMapping(params="method=findConAndInvList1")
	public String findConAndInvList1(HttpSession httpSession,ModelMap map,HttpServletRequest request) throws ParameterException{
		
		User user = (User)httpSession.getAttribute("currUser");
		
		String contractId = request.getParameter("contractId");
		
		ContractBean bean = contractService.findContract(contractId, user.getId());
		
		map.addAttribute("contractBean", bean);
		
		return "checkContractInfo";
	}
	

	@RequestMapping(params="method=importContractFile")
	public String importContractFile(ContractBean contractBean,Contract contract, HttpSession httpSession) throws ParameterException, SystemException{
		
		User user = (User)httpSession.getAttribute("currUser");
		
		contractBean.setContract(contract);
		
		contractService.preserveContract(contractBean,user);
		
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
        String fullFileName = req.getSession().getServletContext().getRealPath("/download/"+Constants.EXCEL_FILENAME);
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
		
		map.addAttribute("contractBean", contractBean);
		/*if(null!=contractBean.getInvoiceList())					
			map.addAttribute("invoiceList",contractBean.getInvoiceList());*/
		
		return "importContractFile";
	}
	
}
