package com.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

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

import com.dao.ContractDao;
import com.exception.ParameterException;
import com.model.Contract;
import com.service.ContractService;
import com.util.StringUtils;
@Repository("contractService")
public class ContractServiceImpl implements ContractService{
	
	@Autowired
	private ContractDao contractDao;
	
	@Override
	public Contract createContract(CommonsMultipartFile file) throws ParameterException {
		
		InputStream inputStream = null;
		Workbook workbook = null;
		Contract contract = new Contract(); 
		try {
			inputStream = file.getInputStream();
			workbook = getWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(1);
			
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
		} catch (IOException | InvalidFormatException e1) {
			throw new ParameterException("importContractFile","","Excel格式不正确",null,null);
		}
		
		return contract;
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
	public void preserveContract(Contract contract) throws ParameterException {
		//数据验证
		validate(contract);
		
		contractDao.add(contract);
	}

	@Override
	public Contract findContract(String contractId) throws ParameterException {
		
		if(null==contractId||StringUtils.isBlank(contractId)||StringUtils.isEmpty(contractId)){
			throw new ParameterException("checkContractInfo","contractId","请输入需要查询的合同编号",contractId,"contractId");
		}
		return  contractDao.findByContractId(contractId);
	}

	@Override
	public void updateContract(Contract contract) throws ParameterException {
		
		validate2(contract);
		
		contractDao.update(contract);
		
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
}
