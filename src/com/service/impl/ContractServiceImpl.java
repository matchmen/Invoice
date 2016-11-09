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

import com.bean.Admin;
import com.bean.ContractBean;
import com.constant.Constants;
import com.dao.AuthorityDao;
import com.dao.CompanyDao;
import com.dao.ContractDao;
import com.dao.UserDao;
import com.dao.InvoiceDao;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.Authority;
import com.model.Company;
import com.model.Contract;
import com.model.User;
import com.model.Invoice;
import com.service.ContractService;
import com.util.StringUtils;
@Repository("contractService")
public class ContractServiceImpl implements ContractService{
	
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private AuthorityDao authorityDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Override
	public ContractBean getContractInfo(CommonsMultipartFile file) throws ParameterException, BusinessException {
		
		ContractBean bean = new ContractBean();
		
		InputStream inputStream = null;
		
		Workbook workbook = null;
		
		try {
			inputStream = file.getInputStream();
			workbook = getWorkbook(inputStream);
			//获取合同信息sheet
			Sheet sheetConrtact = workbook.getSheetAt(0);
			//生成Contract
			Contract contract = createContract(sheetConrtact);
			//如果contract不为null,则赋值给bean;否则返回null,退出此方法
			if(null!=contract){
				bean.setContract(contract);
			}else{
				return null;
			}
			//获取第二个sheet，也就是开票信息sheet。
			Sheet sheetInvocie = workbook.getSheetAt(1);
			//如果合同暂时没有开票信息，则不进行任何操作,反之，则赋值给读取开票信息
			if(null!=sheetInvocie){
				List<Invoice> invoiceList = createInvoice(sheetInvocie);
				//判断开票信息是否存在,不存在则不进行任何操作；反之赋值给bean
				if(null!=invoiceList){
					//开票批次验证
					if(invoiceList.size()>contract.getPaymentTimes()){
						throw new ParameterException("importContractFile","","开票信息的批次与合同信息不符",null,null);
					}
					
					for (Invoice inn : invoiceList) {
						//开票批次最大验证
						if(inn.getInvoiceIndex()>contract.getPaymentTimes()){
							throw new ParameterException("importContractFile","","第"+inn.getInvoiceIndex()+"次开票的开票信息的最大批次大于合同开票批次数",null,null);
						}
						//合同编号验证
						if(!inn.getContractId().equals(contract.getContractId())){
							throw new ParameterException("importContractFile","","第"+inn.getInvoiceIndex()+"次开票的开票信息的合同编号与合同信息不符",null,null);
						}
					}
					bean.setInvoiceList(invoiceList);
				}
			}
			
		} catch (IOException | InvalidFormatException e1) {
			throw new ParameterException("importContractFile","","Excel格式不正确",null,null);
		}
		
		return bean;
	}
	/**
	 * 根据sheet获取合同信息
	 * @param sheet
	 * @return
	 * @throws BusinessException 
	 */
	private Contract createContract(Sheet sheet) throws BusinessException{
		
		Contract contract = new Contract();
		//第一行为表头，所以直接读取第二行合同信息
		Row row = sheet.getRow(1);
		try {
			contract.setCompanyNameOfFirst(getStringVal(row.getCell(1)));
			contract.setItemName(getStringVal(row.getCell(2)));
			contract.setContactNameOfFirst(getStringVal(row.getCell(3)));
			contract.setCompanyNameOfSecond(getStringVal(row.getCell(4)));
			contract.setSales(getStringVal(row.getCell(5)));
			contract.setCompanyNameOfThird(getStringVal(row.getCell(6)));
			contract.setContractType(getStringVal(row.getCell(8)));
			contract.setContractId(getStringVal(row.getCell(9)));
			if(!StringUtils.isEmpty(getStringVal(row.getCell(10))))
				contract.setContractSignDate(getStringVal(row.getCell(10)));
			if(!StringUtils.isEmpty(getStringVal(row.getCell(11))))
				contract.setContractStartDate(getStringVal(row.getCell(11)));
			if(!StringUtils.isEmpty(getStringVal(row.getCell(12))))
				contract.setContractEndDate(getStringVal(row.getCell(12)));
			if(!StringUtils.isEmpty(getStringVal(row.getCell(13))))
				contract.setAmt(new BigDecimal(getStringVal(row.getCell(13))));
			if(!StringUtils.isEmpty(getStringVal(row.getCell(15))))
				contract.setPaymentTimes(Integer.valueOf(getStringVal(row.getCell(15))));
			if(!StringUtils.isEmpty(getStringVal(row.getCell(16))))
				contract.setComleteInvoiceNumber(Integer.valueOf(getStringVal(row.getCell(16))));
			if(!StringUtils.isEmpty(getStringVal(row.getCell(17))))
				contract.setCompleteInvoiceAmt(new BigDecimal(getStringVal(row.getCell(17))));
			if(!StringUtils.isEmpty(getStringVal(row.getCell(18))))
				contract.setRemainInvoiceAmt(new BigDecimal(getStringVal(row.getCell(18))));
			contract.setRemark(getStringVal(row.getCell(19)));
		} catch (Exception e) {
			throw new BusinessException("importContractFile", "Excel格式不正确,请检查你的Excel格式。", null, null);
		}
		return contract;
	}
	/**
	 * 根据sheet获取开票信息
	 * @param sheet
	 * @return
	 * @throws BusinessException 
	 */
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
				invoice.setContractId(getStringVal(row.getCell(0)));
				if(!StringUtils.isEmpty(getStringVal(row.getCell(1)))){
					for (Invoice inv : invoiceList) {
						if(Double.valueOf(getStringVal(row.getCell(1))).intValue()==inv.getInvoiceIndex()){
							throw new BusinessException("importContractFile", "开票信息sheet中,开票批次设置不正确", null, null);
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
					invoice.setRate(new BigDecimal(getStringVal(row.getCell(13))));
				invoice.setInvoiceStatus(getStringVal(row.getCell(14)));
				invoiceList.add(invoice);
				
			}
		} catch (Exception e) {
			throw new BusinessException("importContractFile", "Excel格式不正确,请检查你的Excel格式。", null, null);
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

	private void validate(Contract contract) throws ParameterException{
		//合同编号验证
		if(null==contract.getContractId()||
				StringUtils.isBlank(contract.getContractId())||
				StringUtils.isEmpty(contract.getContractId())){
			throw new ParameterException("importContractFile","contractId","合同编号不能为空",contract,"contract");
		}
		Contract ct = contractDao.findByContractId(contract.getContractId());
		if(null!=ct){
			throw new ParameterException("importContractFile","contractId","合同编号已经存在",contract,"contract");
		}
		
		//合同类型验证
		if(null==contract.getContractType()||
				StringUtils.isBlank(contract.getContractType())||
				StringUtils.isEmpty(contract.getContractType())){
			throw new ParameterException("importContractFile","contractType","合同类型不能为空",contract,"contract");
		}
		
		//甲方（客户）单位全称	
		if(null==contract.getContractType()||
				StringUtils.isBlank(contract.getContractType())||
				StringUtils.isEmpty(contract.getContractType())){
			throw new ParameterException("importContractFile","companyNameOfFirst","甲方（客户）单位不能为空",contract,"contract");
		}
		
		//甲方联系人	
		if(null==contract.getContactNameOfFirst()||
				StringUtils.isBlank(contract.getContactNameOfFirst())||
				StringUtils.isEmpty(contract.getContactNameOfFirst())){
			throw new ParameterException("importContractFile","contactNameOfFirst","甲方联系人不能为空",contract,"contract");
		}
		
		//乙方（自己）单位全称
		if(null==contract.getCompanyNameOfSecond()||
				StringUtils.isBlank(contract.getCompanyNameOfSecond())||
				StringUtils.isEmpty(contract.getCompanyNameOfSecond())){
			throw new ParameterException("importContractFile","companyNameOfSecond","乙方（自己）单位全称不能为空",contract,"contract");
		}
		
		//销售代表
		if(null==contract.getContactNameOfFirst()||
				StringUtils.isBlank(contract.getContactNameOfFirst())||
				StringUtils.isEmpty(contract.getContactNameOfFirst())){
			throw new ParameterException("importContractFile","contactNameOfSecond","销售代表不能为空",contract,"contract");
		}
		//合同签订日期
		if(null==contract.getContractSignDate()||
				StringUtils.isBlank(contract.getContractSignDate())||
				StringUtils.isEmpty(contract.getContractSignDate())){
			throw new ParameterException("importContractFile","contractSignDate","合同签订日期不能为空",contract,"contract");
		}
	}
	
	@Override
	public void preserveContract(ContractBean contractBean,Integer empId) throws ParameterException, SystemException {
		
		if(null!=contractBean){
			if (null != contractBean.getContract()) {
				// 数据验证
				validate(contractBean.getContract());

				User emp = userDao.find(empId);

				if (null != emp) {
					Company com = companyDao.find(emp.getCompanyCode());
					if (null != com) {
						Authority authority = new Authority();
						authority.setComId(com.getId());
						authority.setEmpId(empId);
						contractDao.add(contractBean.getContract());
						Contract con = contractDao.findByContractId(contractBean.getContract().getContractId());
						authority.setConId(con.getId());
						// 权限绑定
						authorityDao.addCon(authority);
						//判断是否有开票信息
						if(null!=contractBean.getInvoiceList()){
							for (Invoice invoice : contractBean.getInvoiceList()) {
								invoice.setContractId(contractBean.getContract().getContractId());
								invoice.setInvoiceStatus(Constants.strParseNum(invoice.getInvoiceStatus()));
								if(null!=invoice.getInvoiceId()
										&&!StringUtils.isBlank(invoice.getInvoiceId())
										&&!StringUtils.isEmpty(invoice.getInvoiceId())){
									throw new ParameterException("checkContractInfo","contractId","发票编号已经存在",contractBean,"contractBean");
								}
								invoiceDao.add(invoice);
								Invoice inv = invoiceDao.findByConIDAndIndex(invoice.getContractId(), invoice.getInvoiceIndex());
								if(null!=inv){
									authority.setInvId(inv.getInvId());
									authorityDao.addInv(authority);
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public ContractBean findContract(String contractId,Integer empId) throws ParameterException {

		if(null==contractId||StringUtils.isBlank(contractId)||StringUtils.isEmpty(contractId)){
			throw new ParameterException("checkContractInfo","contractId","请输入需要查询的合同编号",contractId,"contractId");
		}
		
		Contract con = contractDao.findByContractId(contractId);
		
		User emp = userDao.find(empId);
		
		Company com = companyDao.find(emp.getCompanyCode());
		//为null判断，几个对象中只要有一个为空则返回null
		if(null==con||emp==null||com==null){
			return null;
		}
		
		List<Invoice> invoiceList = invoiceDao.findByContractId(contractId);
		//定义返回对象
		ContractBean bean = new ContractBean();
		//将检索到的 合同数据付给bean
		bean.setContract(con);
		if(null!=invoiceList){
			bean.setInvoiceList(invoiceList);
		}
		
		if(!authValidate(con.getId(),com.getId(),emp.getId())){
			return null;
		}
		
		return  bean;
	}

	@Override
	public void updateContract(ContractBean contractBean,Integer empId) throws ParameterException, BusinessException {
		
		if(null!=contractBean){
			
			if(null!=contractBean.getContract()){
				
				Contract contract = contractBean.getContract();
				
				validate2(contract);
				
				Contract con = contractDao.findByContractId(contract.getContractId());
				
				User emp = userDao.find(empId);
				
				Company com = companyDao.find(emp.getCompanyCode());
				
				if(null==con||emp==null||com==null){
					throw new BusinessException("", "没有修改权限", null, null);
				}else if(!authValidate(con.getId(),com.getId(),emp.getId())){
					throw new BusinessException("", "没有修改权限", null, null);
				}
				contractDao.update(contract);
			}
			
			if(null!=contractBean.getInvoiceList()){
				//开票批次验证
				if(contractBean.getInvoiceList().size()>contractBean.getContract().getPaymentTimes()){
					throw new ParameterException("importContractFile","","开票信息的批次与合同信息不符",contractBean,"contractBean");
				}
				
				for (Invoice inn : contractBean.getInvoiceList()) {
					//开票批次最大验证
					if(inn.getInvoiceIndex()>contractBean.getContract().getPaymentTimes()){
						throw new ParameterException("importContractFile","","第"+inn.getInvoiceIndex()+"次开票的开票信息的最大批次大于合同开票批次数",contractBean,"contractBean");
					}
				}
				for (Invoice invoice : contractBean.getInvoiceList()) {
					invoice.setContractId(contractBean.getContract().getContractId());
					invoice.setInvoiceStatus(Constants.strParseNum(invoice.getInvoiceStatus()));
					//数据库中发票编号存在验证
					Invoice oldInv = invoiceDao.find(invoice.getInvId());
					if(null!=oldInv){
						if(!oldInv.getInvoiceId().equals(invoice.getInvoiceId())){
							if(null!=invoiceDao.findByInvoiceId(invoice.getInvoiceId()))
								throw new ParameterException("importContractFile","","第"+invoice.getInvoiceIndex()+"次开票的开票信息的合同编号已经存在",contractBean,"contractBean");
						}
					}
					invoiceDao.update(invoice);
				}
			}
		}
	}
	
	private void validate2(Contract contract) throws ParameterException{
		//合同编号验证
		if(null==contract.getContractId()||
				StringUtils.isBlank(contract.getContractId())||
				StringUtils.isEmpty(contract.getContractId())){
			throw new ParameterException("updateContractInfo","contractId","合同编号不能为空",contract,"contract");
		}
		Contract oldcontract = contractDao.find(contract.getId());
		if(null!=oldcontract){
			if(!oldcontract.getContractId().equals(contract.getContractId())){
				Contract ct = contractDao.findByContractId(contract.getContractId());
				if(null!=ct){
					throw new ParameterException("updateContractInfo","contractId","合同编号已经存在",contract,"contract");
				}
			}
		}
		//合同类型验证
		if(null==contract.getContractType()||
				StringUtils.isBlank(contract.getContractType())||
				StringUtils.isEmpty(contract.getContractType())){
			throw new ParameterException("updateContractInfo","contractType","合同类型不能为空",contract,"contract");
		}
		
		//甲方（客户）单位全称	
		if(null==contract.getContractType()||
				StringUtils.isBlank(contract.getContractType())||
				StringUtils.isEmpty(contract.getContractType())){
			throw new ParameterException("updateContractInfo","companyNameOfFirst","甲方（客户）单位不能为空",contract,"contract");
		}
		
		//甲方联系人	
		if(null==contract.getContactNameOfFirst()||
				StringUtils.isBlank(contract.getContactNameOfFirst())||
				StringUtils.isEmpty(contract.getContactNameOfFirst())){
			throw new ParameterException("updateContractInfo","contactNameOfFirst","甲方联系人不能为空",contract,"contract");
		}
		
		//乙方（自己）单位全称
		if(null==contract.getCompanyNameOfSecond()||
				StringUtils.isBlank(contract.getCompanyNameOfSecond())||
				StringUtils.isEmpty(contract.getCompanyNameOfSecond())){
			throw new ParameterException("updateContractInfo","companyNameOfSecond","乙方（自己）单位全称不能为空",contract,"contract");
		}
		
		//销售代表
		if(null==contract.getContactNameOfFirst()||
				StringUtils.isBlank(contract.getContactNameOfFirst())||
				StringUtils.isEmpty(contract.getContactNameOfFirst())){
			throw new ParameterException("updateContractInfo","contactNameOfSecond","销售代表不能为空",contract,"contract");
		}
		//合同签订日期
		if(null==contract.getContractSignDate()||
				StringUtils.isBlank(contract.getContractSignDate())||
				StringUtils.isEmpty(contract.getContractSignDate())){
			throw new ParameterException("importContractFile","contractSignDate","合同签订日期不能为空",contract,"contract");
		}
	}

	private boolean authValidate(Integer conId, Integer comId, Integer empId){
				
		Authority authority = new Authority();
		
		authority.setComId(comId);
		authority.setConId(conId);
		authority.setEmpId(empId);
		
		Authority au = authorityDao.findCon(authority);
		if(null==au){
			return false;
		}
		return true;
	}
	@Override
	public void addContractInfoManage(Admin admin) throws ParameterException {
		if(null!=admin){
			Company company = null;
			Authority auth = new Authority();
			Contract contract = null;
			User addEmp = null;
			//合同编号为空验证
			if(null==admin.getContractId()
					||StringUtils.isBlank(admin.getContractId())
					||StringUtils.isEmpty(admin.getContractId())){
				throw new ParameterException("addContractInfoManage","contractId","合同编号不能为空!",admin,"admin");
			}else{
				contract = contractDao.findByContractId(admin.getContractId());
				if(null==contract){
					throw new ParameterException("addContractInfoManage","contractId","合同编号不存在!",admin,"admin");
				}
			}
			//添加人员身份验证
			if(null!=admin.getUser()){
				company = companyDao.find(admin.getUser().getCompanyCode());
				//判断是否有权限
				if(null==company){
					throw new ParameterException("addContractInfoManage","","您没有此合同管理权限!",admin,"admin");
				}
				auth.setComId(company.getId());
				auth.setConId(contract.getId());
				auth.setEmpId(admin.getUser().getId());
				if(null==authorityDao.findCon(auth)){
					throw new ParameterException("addContractInfoManage","","您没有此合同管理权限!",admin,"admin");
				}
			}else{
				throw new ParameterException("addContractInfoManage","","身份验证已过期，请重新登录!",null,"");
			}
			//被添加人员身份验证
			if(null!=admin.getStr()
					||!StringUtils.isBlank(admin.getStr())
					||!StringUtils.isEmpty(admin.getStr())){
				List<User> empList = userDao.findList(admin.getStr());
				//判断该手机号的用户是否在对应公司下
				if(null==empList
						||null==empList.get(0)
						||!admin.getUser().getCompanyCode().equals(empList.get(0).getCompanyCode())){
					throw new ParameterException("addContractInfoManage","str","邮箱或手机号不存在",admin,"admin");
				}
				addEmp=empList.get(0);
			}else{
				throw new ParameterException("addContractInfoManage","str","邮箱或手机号不能为空",admin,"admin");
			}
			auth.setEmpId(addEmp.getId());
			if(null!=authorityDao.findCon(auth)){
				throw new ParameterException("addContractInfoManage","str","添加的管理者已经拥有权限!",admin,"admin");
			}
			//添加
			authorityDao.addCon(auth);
			//判断是否添加发票管理权限
			//admin.getIsAll()
			if(true){
				List<Invoice> invoiceList = invoiceDao.findByContractId(admin.getContractId());
				if(null!=invoiceList&&invoiceList.size()>0){
					for (Invoice invoice : invoiceList) {
						auth.setInvId(invoice.getInvId());
						//addInvoiceInfoManage(admin);
						authorityDao.addInv(auth);
					}
				}
			}
		}
	}
	@Override
	public void addInvoiceInfoManage(Admin admin) throws ParameterException {
		
	}
}
