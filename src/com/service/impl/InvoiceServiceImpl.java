package com.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bean.InvoiceBean;
import com.dao.AuthorityDao;
import com.dao.CompanyDao;
import com.dao.ContractDao;
import com.dao.SystemInfoDao;
import com.dao.UserDao;
import com.dao.InvoiceDao;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.Authority;
import com.model.Contract;
import com.model.Email;
import com.model.SystemInfo;
import com.model.User;
import com.model.Invoice;
import com.service.InvoiceService;
import com.util.SendEmail;
import com.util.StringUtils;
@Repository("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceDao invoiceDao;
	@Autowired
	private AuthorityDao authorityDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private SystemInfoDao systemInfoDao;
	
	@Override
	public List<Invoice> checkInvoiceInfoList(String firstName,
			String makeInvoceDateStart,
			String makeInvoceDateEnd,
			String paymentDateStart,
			String paymentDateEnd,
			String invStatus,User user ) throws ParameterException {
		
		List<Invoice> invoiceList = invoiceDao.findList(firstName, makeInvoceDateStart, makeInvoceDateEnd, paymentDateStart, paymentDateEnd, invStatus, user.getId());	
		
		return invoiceList;
	}

	@Override
	public void updateInvoiceInfo(Invoice invoice, Integer empId) throws BusinessException,ParameterException {
		
		if(null!=invoice){
			invoiceDao.update(invoice);
		}
		
	}
	/**
	 * 权限验证
	 * @param invoiceId
	 * @param invid
	 * @return
	 */
/*	private boolean validateAuth(String invoiceId,Integer empId){

		if(null!=invoiceId&&null!=empId){
			
			Invoice invoice = invoiceDao.findByInvoiceId(invoiceId,empId);
			
			if(null==invoice){
				return false;
			}
			
			User emp = userDao.find(empId);
			
			if(null==emp){
				return false;
			}
			
			Company com = companyDao.find(emp.getCompanyCode());
			
			if(null==com){
				return false;
			}
			
			Authority authority = new Authority();
			
			authority.setInvId(invoice.getInvId());
			
			authority.setEmpId(empId);
			
			authority.setComId(com.getId());
			
			Authority au = authorityDao.findInv(authority);
			
			if(null!=au){
				return true;
			}else{
				return false;
			}
			
		}
		
		
		return false;
	}
	*//**
	 * 
	 * @param invoiceId
	 * @param invid
	 * @return
	 *//*
	private boolean validateAuth2(Integer invId,Integer empid){
		
		if(null!=invId&&null!=empid){
			
			Invoice invoice = invoiceDao.find(invId,empid);
			
			if(null==invoice){
				return false;
			}
			
			User emp = userDao.find(empid);
			
			if(null==emp){
				return false;
			}
			
			Company com = companyDao.find(emp.getCompanyCode());
			
			if(null==com){
				return false;
			}
			
			Authority authority = new Authority();
			
			authority.setInvId(invId);
			
			authority.setEmpId(empid);
			
			authority.setComId(com.getId());
			
			Authority au = authorityDao.findInv(authority);
			
			if(null!=au){
				return true;
			}else{
				return false;
			}
			
		}
		return false;
	}*/

	@Override
	public void addInvoice(InvoiceBean bean) throws BusinessException,
			ParameterException{
		//数据信息验证
		validate(bean);
		//遍历List
		for (Invoice invoice : bean.getInvoiceList()) {
			//设假值  TODO
			invoice.setIsContract(true);
			//权限绑定
			Authority auth = new Authority();
			//绑定用户
			auth.setEmpId(bean.getUser().getId());
			//公司ID
			Integer comId = 0;
			//是否属于公司
			if(bean.getUser().getIsCompany()){
				//查询公司ID
				comId = userDao.findComId(bean.getUser().getId());
				//绑定公司
				invoice.setIsCompany(true);
			}else{
				invoice.setIsCompany(false);
			}
			auth.setComId(comId);
			//添加数据库
			invoiceDao.add(invoice);
			
			auth.setInvId(invoiceDao.findByInvoiceId(invoice.getInvoiceId()).getInvId());
			//权限绑定
			authorityDao.addInv(auth);
		}
	}
	
	private void validate(InvoiceBean bean) throws ParameterException{
		if(null!=bean&&null!=bean.getInvoiceList()){
			for (Invoice invoice : bean.getInvoiceList()) {
				//设假值  TODO
				invoice.setIsContract(true);
				//合同编号验证
				if(invoice.getIsContract()&&(null==invoice.getContractId()
						||StringUtils.isBlank(invoice.getContractId())
						||StringUtils.isEmpty(invoice.getContractId()))){
					throw new ParameterException("addInvoiceInfo", "contractId", "合同编号不能为空", invoice, "invoice");
				}
				if(invoice.getIsContract()){
					//数据库存在验证
					Contract contract = contractDao.findByContractId(invoice.getContractId(),bean.getUser().getId());
					
					if(null==contract){
						throw new ParameterException("addInvoiceInfo", "contractId", "合同编号不存在", invoice, "invoice");
					}
					//数据库存在验证
					List<Invoice> invoiceList = invoiceDao.findByContractId(invoice.getContractId());
					
					if(null!=invoiceList&&invoiceList.size()>0){
						for (Invoice invoice2 : invoiceList) {
							if(invoice.getInvoiceIndex()==invoice2.getInvoiceIndex()){
								throw new ParameterException("addInvoiceInfo", "invoiceIndex", "开票批次已经存在", invoice, "invoice");
							}
						}
					}
					//开票批次为空验证
					if(null==invoice.getInvoiceIndex()
							||StringUtils.isBlank(String.valueOf(invoice.getInvoiceIndex()))
							||StringUtils.isEmpty(String.valueOf(invoice.getInvoiceIndex()))){
						throw new ParameterException("addInvoiceInfo", "invoiceIndex", "开票批次不能为空", invoice, "invoice");
					}
				}
				
				//发票编号验证
				if(null!=invoice.getInvoiceId()
						&&!StringUtils.isBlank(invoice.getInvoiceId())
						&&!StringUtils.isEmpty(invoice.getInvoiceId())){
					Invoice inv = invoiceDao.findByInvoiceId(invoice.getInvoiceId(),bean.getUser().getId());
					if(null!=inv){
						throw new ParameterException("addInvoiceInfo", "invoiceId", "发票编号已经存在", invoice, "invoice");
					}
				}
			}
		}else{
			throw new ParameterException("addInvoiceInfo", "", "操作失败!",null , "");
		}
	}

	@Override
	public List<Invoice> getInvoiceInfo(CommonsMultipartFile file)
			throws ParameterException, BusinessException {
		
		InputStream inputStream = null;
		
		Workbook workbook = null;
		
		List<Invoice> invoiceList = null;
		
		try {
			inputStream = file.getInputStream();
			workbook = getWorkbook(inputStream);
			//获取第1个sheet，也就是开票信息sheet。
			Sheet sheetInvocie = workbook.getSheetAt(0);
			if(null!=sheetInvocie){
				invoiceList = createInvoice(sheetInvocie);
			}
			
		} catch (IOException | InvalidFormatException e1) {
			throw new ParameterException("importContractFile","","Excel格式不正确",null,null);
		}
		
		return invoiceList;
	}
	
	private Workbook getWorkbook(InputStream inputStream)
			throws InvalidFormatException, IOException {
		if (!inputStream.markSupported()) {

			inputStream = new PushbackInputStream(inputStream, 8);

		}

		if (POIFSFileSystem.hasPOIFSHeader(inputStream)) {

			return new HSSFWorkbook(inputStream);

		}

		if (POIXMLDocument.hasOOXMLHeader(inputStream)) {

			return new XSSFWorkbook(OPCPackage.open(inputStream));

		}

		throw new IllegalArgumentException("你的excel版本目前poi解析不了");
	}
	
	private List<Invoice> createInvoice(Sheet sheet) throws BusinessException{
		//获取开票信息sheet行数
		int rowNum = sheet.getLastRowNum();
		//如果开票信息sheet行数小于2，说明该sheet没有信息或只有表头或格式不对，直接返回null，退出方法
		if(rowNum<1){
			return null;
		}
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		try {
			//遍历获取开票信息
			for(int i = 1;i<=rowNum;i++){
				Row row = sheet.getRow(i);
				Invoice invoice = new Invoice();
				invoice.setInvId(i-1);
				invoice.setContractId(getStringVal(row.getCell(0)));
				if(!StringUtils.isEmpty(getStringVal(row.getCell(1)))){
					for (Invoice inv : invoiceList) {
						if(Double.valueOf(getStringVal(row.getCell(1))).intValue()==inv.getInvoiceIndex()){
							throw new BusinessException("addInvoice", "开票信息sheet中,开票批次设置不正确", null, null);
						}
					}
					invoice.setInvoiceIndex(Double.valueOf(getStringVal(row.getCell(1))).intValue());
				}
				invoice.setExpectMakeInvoceDate(getStringVal(row.getCell(2)));
				invoice.setAddresseeName(getStringVal(row.getCell(3)));
				invoice.setExpectPaymentDate(getStringVal(row.getCell(4)));
				invoice.setMakeInvoiceCompanyName(getStringVal(row.getCell(5)));
				invoice.setSales(getStringVal(row.getCell(6)));
				invoice.setCompanyNameOfPurchaser(getStringVal(row.getCell(7)));
				if(!StringUtils.isEmpty(getStringVal(row.getCell(8))))
					invoice.setAmt(new BigDecimal(getStringVal(row.getCell(8))));
				invoice.setInvoiceContent(getStringVal(row.getCell(9)));
				invoice.setInvoiceType(getStringVal(row.getCell(10)));
				invoice.setInvRemark(getStringVal(row.getCell(11)));
				invoice.setRemarkType(getStringVal(row.getCell(12)));
				if(!StringUtils.isEmpty(getStringVal(row.getCell(13))))
					invoice.setRate(new Double(getStringVal(row.getCell(13))));
				invoice.setInvoiceStatus(getStringVal(row.getCell(14)));
				invoiceList.add(invoice);
				
			}
		} catch (Exception e) {
			throw new BusinessException("importContractFile", "Excel格式不正确,请检查你的Excel格式。", null, null);
		}
		
		return invoiceList;
	}
	
	public String getStringVal(Cell cell) {
		if (null != cell) {
			String value = "";
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
				value = "";
				break;
			case Cell.CELL_TYPE_ERROR:
				value = "";
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
				}else{
					value = String.valueOf(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_FORMULA:
				try {
					value = String.valueOf(cell.getNumericCellValue());
					} catch (IllegalStateException e) {
						value =  String.valueOf(cell.getRichStringCellValue());
					}
				break;
			default:
				break;
			}
			return value;
		} else {
			return "";
		}
	}
	
	@Override
	public Invoice findInv(String invoiceId,User user){
		
		return invoiceDao.findByInvoiceId(invoiceId,user.getId());
		
	}
	@Override
	public Invoice findInv(Integer id,User user){
		
		return invoiceDao.find(id,user.getId());
	}
	@Override
	public void send(Integer id,User user,String rMail) throws BusinessException, SystemException{
		
		
		Email mail = new Email();
		
		SystemInfo systemInfo = systemInfoDao.find();
		
		Invoice invoce = invoiceDao.find(id, user.getId());
		
		if(null==systemInfo){
			throw new SystemException("error","系统异常");
		}
		
		if(null==invoce){
			throw new SystemException("error","系统异常");
		}
		mail.setMailSmtpAuth("true");
		mail.setMailSmtpHost(systemInfo.getSystemMailType());
		mail.setSmailUser(systemInfo.getSystemMail());
		mail.setPassword(systemInfo.getSystemMailPW());
		mail.setRmailUser(rMail);
		mail.setHead("发票信息");
		mail.setMessage(SendEmail.ctreateMail(invoce));
		
		try {
			SendEmail.send(mail);
		} catch (BusinessException e) {
			
			throw new BusinessException("invoiceInfo","发送失败!",null,"");
			
		}
		
	}
	
}
