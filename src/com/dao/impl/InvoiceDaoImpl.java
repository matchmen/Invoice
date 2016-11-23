package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.constant.Constants;
import com.dao.DaoJdbcTemplate;
import com.dao.InvoiceDao;
import com.model.Invoice;
import com.util.DateUtils;
import com.util.StringUtils;
@Repository("invoiceDao")
public class InvoiceDaoImpl extends DaoJdbcTemplate implements InvoiceDao {

	@Override
	public void add(Invoice invoice) {
		
		int isContract = 0;
		
		int isCompany = 0;
		
		if(null!=invoice.getIsContract()&&invoice.getIsContract()){
			isContract = 1;
		}
		if(null!=invoice.getIsCompany()&&invoice.getIsCompany()){
			isCompany = 1;
		}
		
		StringBuffer sb = new StringBuffer("insert into t_invoice(")
			.append("INVOICE_ID,")
			.append("CONTRACT_ID,")
			.append("INVOICE_INDEX,")
			.append("EXPECT_MAKE_INVOICE_DATE,")
			.append("ACTUAL_MAKE_INVOICE_DATE,")
			.append("EXPECT_PAYMENT_DATE,")
			.append("ACTUAL_PAYMENT_DATE,")
			.append("MAKE_INVOICE_COMPANY_NAME,")
			.append("AMT_OF_TAX,")
			.append("AMT_OF_NO_TAX,")
			.append("AMT,")
			.append("RATE,")
			.append("RECEIVE_AMT,")
			.append("NOT_RECEIVE_AMT,")
			.append("INVOICE_STATUS,")
			.append("INVOICE_CONTENT,")
			.append("INVOICE_TYPE,")
			.append("COMPANY_NAME_OF_PURCHASER,")
			.append("TP_I_DE_NUM_OF_PURCHASER,")
			.append("BANK_TYPE_OF_PURCHASER,")
			.append("BANK_NUM_OF_PURCHASER,")
			.append("COMPANY_NAME_OF_SALE,")
			.append("TP_I_DE_NUM_OF_SALE,")
			.append("BANK_TYPE_OF_SALE,")
			.append("BANK_NUM_OF_SALE,")
			.append("REMARK_TYPE,")
			.append("ADDRESSEE_NAME,")
			.append("ADRESS,")
			.append("PHONE_NUMBER_OF_ADDRESSEE,")
			.append("IS_CONTRACT,")
			.append("IS_COMPANY,")
			.append("EXPRESS_DATE,")
			.append("INV_REMARK)")
			.append(" values('")
			.append(invoice.getInvoiceId())
			.append("','")
			.append(invoice.getContractId())
			.append("','")
			.append(invoice.getInvoiceIndex())
			.append("',");
		if(null!=invoice.getExpectMakeInvoceDate()
				&&!StringUtils.isBlank(invoice.getExpectMakeInvoceDate())
				&&!StringUtils.isEmpty(invoice.getExpectMakeInvoceDate())){
			sb.append("'");
			sb.append(invoice.getExpectMakeInvoceDate());
			sb.append("'");
		}
		else{
			sb.append("null");
		}
		sb.append(",");
		if(null!=invoice.getActualMakeInvoiceDate()
				&&!StringUtils.isBlank(invoice.getActualMakeInvoiceDate())
				&&!StringUtils.isEmpty(invoice.getActualMakeInvoiceDate())){
			sb.append("'");
			sb.append(invoice.getActualMakeInvoiceDate());
			sb.append("'");
		}
		else{
			sb.append("null");
		}
			sb.append(",");
		if(null!=invoice.getExpectPaymentDate()
				&&!StringUtils.isBlank(invoice.getExpectPaymentDate())
				&&!StringUtils.isEmpty(invoice.getExpectPaymentDate())){
			sb.append("'");
			sb.append(invoice.getExpectPaymentDate());
			sb.append("'");
		}
		else
			sb.append("null");
			sb.append(",");
		if(null!=invoice.getActualPaymentDate()
				&&!StringUtils.isBlank(invoice.getActualPaymentDate())
				&&!StringUtils.isEmpty(invoice.getActualPaymentDate())){
			sb.append("'");
			sb.append(invoice.getActualPaymentDate());
			sb.append("'");
		}
		else
			sb.append("null");
			sb.append(",'")
			.append(invoice.getMakeInvoiceCompanyName())
			.append("',")
			.append(invoice.getAmtOfTax())
			.append(",")
			.append(invoice.getAmtOfNoTax())
			.append(",")
			.append(invoice.getAmt())
			.append(",")
			.append(invoice.getRate())
			.append(",")
			.append(invoice.getReceiveAmt())
			.append(",")
			.append(invoice.getNotReceiveAmt())
			.append(",'")
			.append(invoice.getInvoiceStatus())
			.append("','")
			.append(invoice.getInvoiceContent())
			.append("','")
			.append(Constants.parseToNum(invoice.getInvoiceType()))
			.append("','")
			.append(invoice.getCompanyNameOfPurchaser())
			.append("','")
			.append(invoice.getTpIdeNumOfPurchaser())
			.append("','")
			.append(invoice.getBankTypeOfPurchaser())
			.append("','")
			.append(invoice.getBankNumberOfPurchaser())
			.append("','")
			.append(invoice.getCompanyNameOfSale())
			.append("','")
			.append(invoice.getTpIdeNumOfSale())
			.append("','")
			.append(invoice.getBankTypeOfPurchaser())
			.append("','")
			.append(invoice.getBankNumberOfSale())
			.append("','")
			.append(invoice.getRemarkType())
			.append("','")
			.append(invoice.getAddresseeName())
			.append("','")
			.append(invoice.getAddress())
			.append("','")
			.append(invoice.getPhoneNumber())
			.append("','")
			.append(isContract)
			.append("','")
			.append(isCompany)
			.append("',");
		if(null!=invoice.getExpressDate()
				&&!StringUtils.isBlank(invoice.getExpressDate())
				&&!StringUtils.isEmpty(invoice.getExpressDate())){
			sb.append("'");
			sb.append(invoice.getExpressDate());
			sb.append("'");
		}
		else{
			sb.append("null");
		}
			sb.append(",'")
			.append(invoice.getInvRemark())
			.append("')");
		getJdbcTempalte().execute(sb.toString());
	}

	@Override
	public void remove(Integer id) {
		String sql = "update t_invoice set ABLED = 0 where id = ?";
		getJdbcTempalte().update(sql, new Object[]{id});
	}

	@Override
	public void update(Invoice invoice) {
		StringBuffer sb = new StringBuffer("update t_invoice set ")
				.append("INVOICE_ID = ? ,")
				.append("CONTRACT_ID = ? ,")
				.append("INVOICE_INDEX = ? ,")
				.append("EXPECT_MAKE_INVOICE_DATE = ? ,")
				.append("ACTUAL_MAKE_INVOICE_DATE = ? ,")
				.append("EXPECT_PAYMENT_DATE = ? ,")
				.append("ACTUAL_PAYMENT_DATE = ? ,")
				.append("MAKE_INVOICE_COMPANY_NAME = ? ,")
				.append("AMT_OF_TAX = ? ,")
				.append("AMT_OF_NO_TAX = ? ,")
				.append("AMT = ? ,")
				.append("RATE = ? ,")
				.append("RECEIVE_AMT = ? ,")
				.append("NOT_RECEIVE_AMT = ? ,")
				.append("INVOICE_STATUS = ? ,")
				.append("INVOICE_CONTENT = ? ,")
				.append("INVOICE_TYPE = ? ,")
				.append("COMPANY_NAME_OF_PURCHASER = ? ,")
				.append("TP_I_DE_NUM_OF_PURCHASER = ? ,")
				.append("BANK_TYPE_OF_PURCHASER = ? ,")
				.append("BANK_NUM_OF_PURCHASER = ? ,")
				.append("COMPANY_NAME_OF_SALE = ? ,")
				.append("TP_I_DE_NUM_OF_SALE = ? ,")
				.append("BANK_TYPE_OF_SALE = ? ,")
				.append("BANK_NUM_OF_SALE = ? ,")
				.append("REMARK_TYPE = ? ,")
				.append("ADDRESSEE_NAME = ? ,")
				.append("ADRESS = ? ,")
				.append("PHONE_NUMBER_OF_ADDRESSEE = ? ,")
				.append("EXPRESS_DATE = ? ,")
				.append("INV_REMARK = ? ")
				.append("where ID = ?");
		getJdbcTempalte().update(sb.toString(), new Object[]{
				invoice.getInvoiceId(),
				invoice.getContractId(),
				invoice.getInvoiceIndex(),
				DateUtils.dateNullUtil(invoice.getExpectMakeInvoceDate()),
				DateUtils.dateNullUtil(invoice.getActualMakeInvoiceDate()),
				DateUtils.dateNullUtil(invoice.getExpectPaymentDate()),
				DateUtils.dateNullUtil(invoice.getActualPaymentDate()),
				invoice.getMakeInvoiceCompanyName(),
				invoice.getAmtOfTax(),
				invoice.getAmtOfNoTax(),
				invoice.getAmt(),
				invoice.getRate(),
				invoice.getReceiveAmt(),
				invoice.getNotReceiveAmt(),
				invoice.getInvoiceStatus(),
				invoice.getInvoiceContent(),
				Constants.parseToNum(invoice.getInvoiceType()),
				invoice.getCompanyNameOfPurchaser(),
				invoice.getTpIdeNumOfPurchaser(),
				invoice.getBankTypeOfPurchaser(),
				invoice.getBankNumberOfPurchaser(),
				invoice.getCompanyNameOfSale(),
				invoice.getTpIdeNumOfSale(),
				invoice.getBankTypeOfSale(),
				invoice.getBankNumberOfSale(),
				invoice.getRemarkType(),
				invoice.getAddresseeName(),
				invoice.getAddress(),
				invoice.getPhoneNumber(),
				DateUtils.dateNullUtil(invoice.getExpressDate()),
				invoice.getInvRemark(),
				invoice.getInvId()
		});
	}

	@Override
	public Invoice find(Integer id,Integer userId) {
		
		String sql ="select * from t_invoice t1,t_com_user_inv t2 where T1.ID = T2.INV_ID AND T2.USER_ID= ? AND T1.ID = ? AND T2.ABLED = 1 AND T1.ABLED=1 ";
		
		List<Invoice> invoiceList = getJdbcTempalte().query(sql, new Object[]{userId,id}, new RowMapper<Invoice>(){

			@Override
			public Invoice mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Invoice invoice = new Invoice();
				invoice.setInvoiceId(rs.getString("INVOICE_ID"));
				invoice.setContractId(rs.getString("CONTRACT_ID"));
				invoice.setInvoiceIndex(rs.getInt("INVOICE_INDEX"));
				invoice.setExpectMakeInvoceDate(DateUtils.dateParseToString(rs.getDate("EXPECT_MAKE_INVOICE_DATE")));
				invoice.setActualMakeInvoiceDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_MAKE_INVOICE_DATE")));
				invoice.setExpectPaymentDate(DateUtils.dateParseToString(rs.getDate("EXPECT_PAYMENT_DATE")));
				invoice.setActualPaymentDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_PAYMENT_DATE")));
				invoice.setMakeInvoiceCompanyName(rs.getString("MAKE_INVOICE_COMPANY_NAME"));
				invoice.setAmtOfTax(rs.getBigDecimal("AMT_OF_TAX"));
				invoice.setAmtOfNoTax(rs.getBigDecimal("AMT_OF_NO_TAX"));
				invoice.setAmt(rs.getBigDecimal("AMT"));
				invoice.setRate(rs.getDouble("RATE"));
				invoice.setReceiveAmt(rs.getBigDecimal("RECEIVE_AMT"));
				invoice.setNotReceiveAmt(rs.getBigDecimal("NOT_RECEIVE_AMT"));
				invoice.setInvoiceStatus(Constants.numParseStr(rs.getString("INVOICE_STATUS")));
				invoice.setInvoiceType(Constants.parseToStr(rs.getString("INVOICE_TYPE")));
				invoice.setCompanyNameOfPurchaser(rs.getString("COMPANY_NAME_OF_PURCHASER"));
				invoice.setTpIdeNumOfPurchaser(rs.getString("TP_I_DE_NUM_OF_PURCHASER"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfPurchaser(rs.getString("BANK_NUM_OF_PURCHASER"));
				invoice.setCompanyNameOfSale(rs.getString("COMPANY_NAME_OF_SALE"));
				invoice.setTpIdeNumOfSale(rs.getString("TP_I_DE_NUM_OF_SALE"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfSale(rs.getString("BANK_NUM_OF_SALE"));
				invoice.setBankTypeOfSale(rs.getString("BANK_TYPE_OF_SALE"));
				invoice.setRemarkType(rs.getString("REMARK_TYPE"));
				invoice.setAddresseeName(rs.getString("ADDRESSEE_NAME"));
				invoice.setAddress(rs.getString("ADRESS"));
				invoice.setPhoneNumber(rs.getString("PHONE_NUMBER_OF_ADDRESSEE"));
				invoice.setExpressDate(DateUtils.dateParseToString(rs.getDate("EXPRESS_DATE")));
				invoice.setInvRemark(rs.getString("INV_REMARK"));
				invoice.setInvoiceContent(rs.getString("INVOICE_CONTENT"));
				invoice.setIsContract(rs.getBoolean("IS_CONTRACT"));
				invoice.setIsCompany(rs.getBoolean("IS_COMPANY"));
				return invoice;
			}});
		if(null!=invoiceList&&invoiceList.size()>0){
			return invoiceList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public Invoice findByConIDAndIndex(String contractId,Integer index) {
		
		String sql ="select * from t_invoice where CONTRACT_ID = ? AND INVOICE_INDEX = ?";
		
		List<Invoice> invoiceList = getJdbcTempalte().query(sql, new Object[]{contractId,index}, new RowMapper<Invoice>(){

			@Override
			public Invoice mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Invoice invoice = new Invoice();
				invoice.setInvId(rs.getInt("ID"));
				invoice.setInvoiceContent(rs.getString("INVOICE_CONTENT"));
				invoice.setInvoiceId(rs.getString("INVOICE_ID"));
				invoice.setContractId(rs.getString("CONTRACT_ID"));
				invoice.setInvoiceIndex(rs.getInt("INVOICE_INDEX"));
				invoice.setExpectMakeInvoceDate(DateUtils.dateParseToString(rs.getDate("EXPECT_MAKE_INVOICE_DATE")));
				invoice.setActualMakeInvoiceDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_MAKE_INVOICE_DATE")));
				invoice.setExpectPaymentDate(DateUtils.dateParseToString(rs.getDate("EXPECT_PAYMENT_DATE")));
				invoice.setActualPaymentDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_PAYMENT_DATE")));
				invoice.setMakeInvoiceCompanyName(rs.getString("MAKE_INVOICE_COMPANY_NAME"));
				invoice.setAmtOfTax(rs.getBigDecimal("AMT_OF_TAX"));
				invoice.setAmtOfNoTax(rs.getBigDecimal("AMT_OF_NO_TAX"));
				invoice.setAmt(rs.getBigDecimal("AMT"));
				invoice.setRate(rs.getDouble("RATE"));
				invoice.setReceiveAmt(rs.getBigDecimal("RECEIVE_AMT"));
				invoice.setNotReceiveAmt(rs.getBigDecimal("NOT_RECEIVE_AMT"));
				invoice.setInvoiceStatus(Constants.numParseStr(rs.getString("INVOICE_STATUS")));
				invoice.setInvoiceType(Constants.parseToStr(rs.getString("INVOICE_TYPE")));
				invoice.setCompanyNameOfPurchaser(rs.getString("COMPANY_NAME_OF_PURCHASER"));
				invoice.setTpIdeNumOfPurchaser(rs.getString("TP_I_DE_NUM_OF_PURCHASER"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfPurchaser(rs.getString("BANK_NUM_OF_PURCHASER"));
				invoice.setCompanyNameOfSale(rs.getString("COMPANY_NAME_OF_SALE"));
				invoice.setTpIdeNumOfSale(rs.getString("TP_I_DE_NUM_OF_SALE"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfSale(rs.getString("BANK_NUM_OF_SALE"));
				invoice.setBankTypeOfSale(rs.getString("BANK_TYPE_OF_SALE"));
				invoice.setRemarkType(rs.getString("REMARK_TYPE"));
				invoice.setAddresseeName(rs.getString("ADDRESSEE_NAME"));
				invoice.setAddress(rs.getString("ADRESS"));
				invoice.setPhoneNumber(rs.getString("PHONE_NUMBER_OF_ADDRESSEE"));
				invoice.setExpressDate(DateUtils.dateParseToString(rs.getDate("EXPRESS_DATE")));
				invoice.setInvRemark(rs.getString("INV_REMARK"));
				invoice.setIsContract(rs.getBoolean("IS_CONTRACT"));
				invoice.setIsCompany(rs.getBoolean("IS_COMPANY"));
				return invoice;
			}});
		if(null!=invoiceList&&invoiceList.size()>0){
			return invoiceList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Invoice> findByContractId(String contractId) {
		
		String sql ="select * from t_invoice where CONTRACT_ID = ?";
		
		List<Invoice> invoiceList = getJdbcTempalte().query(sql, new Object[]{contractId}, new RowMapper<Invoice>(){

			@Override
			public Invoice mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Invoice invoice = new Invoice();
				invoice.setInvId(rs.getInt("ID"));
				invoice.setInvoiceId(rs.getString("INVOICE_ID"));
				invoice.setContractId(rs.getString("CONTRACT_ID"));
				invoice.setInvoiceIndex(rs.getInt("INVOICE_INDEX"));
				invoice.setExpectMakeInvoceDate(DateUtils.dateParseToString(rs.getDate("EXPECT_MAKE_INVOICE_DATE")));
				invoice.setActualMakeInvoiceDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_MAKE_INVOICE_DATE")));
				invoice.setExpectPaymentDate(DateUtils.dateParseToString(rs.getDate("EXPECT_PAYMENT_DATE")));
				invoice.setActualPaymentDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_PAYMENT_DATE")));
				invoice.setMakeInvoiceCompanyName(rs.getString("MAKE_INVOICE_COMPANY_NAME"));
				invoice.setAmtOfTax(rs.getBigDecimal("AMT_OF_TAX"));
				invoice.setAmtOfNoTax(rs.getBigDecimal("AMT_OF_NO_TAX"));
				invoice.setAmt(rs.getBigDecimal("AMT"));
				invoice.setRate(rs.getDouble("RATE"));
				invoice.setReceiveAmt(rs.getBigDecimal("RECEIVE_AMT"));
				invoice.setNotReceiveAmt(rs.getBigDecimal("NOT_RECEIVE_AMT"));
				invoice.setInvoiceStatus(Constants.numParseStr(rs.getString("INVOICE_STATUS")));
				invoice.setInvoiceType(Constants.parseToStr(rs.getString("INVOICE_TYPE")));
				invoice.setCompanyNameOfPurchaser(rs.getString("COMPANY_NAME_OF_PURCHASER"));
				invoice.setTpIdeNumOfPurchaser(rs.getString("TP_I_DE_NUM_OF_PURCHASER"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfPurchaser(rs.getString("BANK_NUM_OF_PURCHASER"));
				invoice.setCompanyNameOfSale(rs.getString("COMPANY_NAME_OF_SALE"));
				invoice.setTpIdeNumOfSale(rs.getString("TP_I_DE_NUM_OF_SALE"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfSale(rs.getString("BANK_NUM_OF_SALE"));
				invoice.setBankTypeOfSale(rs.getString("BANK_TYPE_OF_SALE"));
				invoice.setRemarkType(rs.getString("REMARK_TYPE"));
				invoice.setAddresseeName(rs.getString("ADDRESSEE_NAME"));
				invoice.setAddress(rs.getString("ADRESS"));
				invoice.setPhoneNumber(rs.getString("PHONE_NUMBER_OF_ADDRESSEE"));
				invoice.setExpressDate(DateUtils.dateParseToString(rs.getDate("EXPRESS_DATE")));
				invoice.setInvRemark(rs.getString("INV_REMARK"));
				invoice.setIsContract(rs.getBoolean("IS_CONTRACT"));
				invoice.setIsCompany(rs.getBoolean("IS_COMPANY"));
				invoice.setInvoiceContent(rs.getString("INVOICE_CONTENT"));
				return invoice;
			}});
		if(null!=invoiceList&&invoiceList.size()>0){
			return invoiceList;
		}else{
			return null;
		}
	}

	@Override
	public Invoice findByInvoiceId(String invoiceId,Integer userId) {
		
		String sql ="select * from t_invoice where INVOICE_ID = ? AND ABLED = 1 AND ID IN (SELECT INV_ID FROM T_COM_USER_INV WHERE USER_ID = ? AND ABLED =1)";
		
		List<Invoice> invoiceList = getJdbcTempalte().query(sql, new Object[]{invoiceId,userId}, new RowMapper<Invoice>(){

			@Override
			public Invoice mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Invoice invoice = new Invoice();
				invoice.setInvId(rs.getInt("ID"));
				invoice.setInvoiceId(rs.getString("INVOICE_ID"));
				invoice.setContractId(rs.getString("CONTRACT_ID"));
				invoice.setInvoiceIndex(rs.getInt("INVOICE_INDEX"));
				invoice.setExpectMakeInvoceDate(DateUtils.dateParseToString(rs.getDate("EXPECT_MAKE_INVOICE_DATE")));
				invoice.setActualMakeInvoiceDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_MAKE_INVOICE_DATE")));
				invoice.setExpectPaymentDate(DateUtils.dateParseToString(rs.getDate("EXPECT_PAYMENT_DATE")));
				invoice.setActualPaymentDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_PAYMENT_DATE")));
				invoice.setMakeInvoiceCompanyName(rs.getString("MAKE_INVOICE_COMPANY_NAME"));
				invoice.setAmtOfTax(rs.getBigDecimal("AMT_OF_TAX"));
				invoice.setAmtOfNoTax(rs.getBigDecimal("AMT_OF_NO_TAX"));
				invoice.setAmt(rs.getBigDecimal("AMT"));
				invoice.setRate(rs.getDouble("RATE"));
				invoice.setReceiveAmt(rs.getBigDecimal("RECEIVE_AMT"));
				invoice.setNotReceiveAmt(rs.getBigDecimal("NOT_RECEIVE_AMT"));
				invoice.setInvoiceStatus(Constants.numParseStr(rs.getString("INVOICE_STATUS")));
				invoice.setInvoiceType(Constants.parseToStr(rs.getString("INVOICE_TYPE")));
				invoice.setCompanyNameOfPurchaser(rs.getString("COMPANY_NAME_OF_PURCHASER"));
				invoice.setTpIdeNumOfPurchaser(rs.getString("TP_I_DE_NUM_OF_PURCHASER"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfPurchaser(rs.getString("BANK_NUM_OF_PURCHASER"));
				invoice.setCompanyNameOfSale(rs.getString("COMPANY_NAME_OF_SALE"));
				invoice.setTpIdeNumOfSale(rs.getString("TP_I_DE_NUM_OF_SALE"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfSale(rs.getString("BANK_NUM_OF_SALE"));
				invoice.setBankTypeOfSale(rs.getString("BANK_TYPE_OF_SALE"));
				invoice.setRemarkType(rs.getString("REMARK_TYPE"));
				invoice.setAddresseeName(rs.getString("ADDRESSEE_NAME"));
				invoice.setAddress(rs.getString("ADRESS"));
				invoice.setPhoneNumber(rs.getString("PHONE_NUMBER_OF_ADDRESSEE"));
				invoice.setExpressDate(DateUtils.dateParseToString(rs.getDate("EXPRESS_DATE")));
				invoice.setInvRemark(rs.getString("INV_REMARK"));
				invoice.setIsContract(rs.getBoolean("IS_CONTRACT"));
				invoice.setIsCompany(rs.getBoolean("IS_COMPANY"));
				invoice.setInvoiceContent(rs.getString("INVOICE_CONTENT"));
				return invoice;
			}});
		if(null!=invoiceList&&invoiceList.size()>0){
			return invoiceList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Invoice> find() {
		 String sql ="select * from t_invoice where ABLED = 1 AND INVOICE_STATUS = '00'  or INVOICE_STATUS = '01'";
		return getJdbcTempalte().query(sql, new RowMapper<Invoice>(){
			@Override
			public Invoice mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Invoice invoice = new Invoice();
				invoice.setInvId(rs.getInt("ID"));
				invoice.setInvoiceId(rs.getString("INVOICE_ID"));
				invoice.setContractId(rs.getString("CONTRACT_ID"));
				invoice.setInvoiceIndex(rs.getInt("INVOICE_INDEX"));
				invoice.setExpectMakeInvoceDate(DateUtils.dateParseToString(rs.getDate("EXPECT_MAKE_INVOICE_DATE")));
				invoice.setActualMakeInvoiceDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_MAKE_INVOICE_DATE")));
				invoice.setExpectPaymentDate(DateUtils.dateParseToString(rs.getDate("EXPECT_PAYMENT_DATE")));
				invoice.setActualPaymentDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_PAYMENT_DATE")));
				invoice.setMakeInvoiceCompanyName(rs.getString("MAKE_INVOICE_COMPANY_NAME"));
				invoice.setAmtOfTax(rs.getBigDecimal("AMT_OF_TAX"));
				invoice.setAmtOfNoTax(rs.getBigDecimal("AMT_OF_NO_TAX"));
				invoice.setAmt(rs.getBigDecimal("AMT"));
				invoice.setRate(rs.getDouble("RATE"));
				invoice.setReceiveAmt(rs.getBigDecimal("RECEIVE_AMT"));
				invoice.setNotReceiveAmt(rs.getBigDecimal("NOT_RECEIVE_AMT"));
				invoice.setInvoiceStatus(Constants.numParseStr(rs.getString("INVOICE_STATUS")));
				invoice.setInvoiceType(Constants.parseToStr(rs.getString("INVOICE_TYPE")));
				invoice.setCompanyNameOfPurchaser(rs.getString("COMPANY_NAME_OF_PURCHASER"));
				invoice.setTpIdeNumOfPurchaser(rs.getString("TP_I_DE_NUM_OF_PURCHASER"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfPurchaser(rs.getString("BANK_NUM_OF_PURCHASER"));
				invoice.setCompanyNameOfSale(rs.getString("COMPANY_NAME_OF_SALE"));
				invoice.setTpIdeNumOfSale(rs.getString("TP_I_DE_NUM_OF_SALE"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfSale(rs.getString("BANK_NUM_OF_SALE"));
				invoice.setBankTypeOfSale(rs.getString("BANK_TYPE_OF_SALE"));
				invoice.setRemarkType(rs.getString("REMARK_TYPE"));
				invoice.setAddresseeName(rs.getString("ADDRESSEE_NAME"));
				invoice.setAddress(rs.getString("ADRESS"));
				invoice.setPhoneNumber(rs.getString("PHONE_NUMBER_OF_ADDRESSEE"));
				invoice.setExpressDate(DateUtils.dateParseToString(rs.getDate("EXPRESS_DATE")));
				invoice.setInvRemark(rs.getString("INV_REMARK"));
				invoice.setIsCompany(rs.getBoolean("IS_COMPANY"));
				invoice.setInvoiceContent(rs.getString("INVOICE_CONTENT"));
				invoice.setIsCompany(rs.getBoolean("IS_CONTRACT"));
				return invoice;
			}});
	}

	@Override
	public List<Invoice> findList(String firstName, String makeInvoceDateStart,
			String makeInvoceDateEnd, String paymentDateStart,
			String paymentDateEnd, String invStatus, Integer userId) {
		//sql构建
		StringBuffer sb = new StringBuffer("SELECT * FROM T_INVOICE WHERE ABLED = 1 AND ID IN (SELECT INV_ID FROM T_COM_USER_INV WHERE USER_ID = ? AND ABLED =1 )"); 
		//参数构建
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		if(!StringUtils.isSpace(firstName)){
			sb.append("AND INVOICE_STATUS = ?");
			params.add(invStatus);
		}
		//起始时间不为空，终止时间为空
		if(!StringUtils.isSpace(makeInvoceDateStart)&&StringUtils.isSpace(makeInvoceDateEnd)){
			sb.append("AND EXPECT_MAKE_INVOICE_DATE >= ?");
			params.add(makeInvoceDateStart);
		}
		//起始时间为空，终止时间不为空
		if(StringUtils.isSpace(makeInvoceDateStart)&&!StringUtils.isSpace(makeInvoceDateEnd)){
			sb.append("AND EXPECT_MAKE_INVOICE_DATE <= ?");
			params.add(makeInvoceDateEnd);
		}
		//起始时间不为空，终止时间不为空
		if(!StringUtils.isSpace(makeInvoceDateStart)&&!StringUtils.isSpace(makeInvoceDateEnd)){
			sb.append("AND EXPECT_MAKE_INVOICE_DATE BETWEEN ? AND ?");
			params.add(makeInvoceDateStart);
			params.add(makeInvoceDateEnd);
		}
		//起始时间不为空，终止时间为空
		if(!StringUtils.isSpace(paymentDateStart)&&StringUtils.isSpace(paymentDateEnd)){
			sb.append("AND EXPECT_MAKE_INVOICE_DATE >= ?");
			params.add(paymentDateStart);
		}
		//起始时间为空，终止时间不为空
		if(StringUtils.isSpace(paymentDateStart)&&!StringUtils.isSpace(paymentDateEnd)){
			sb.append("AND EXPECT_MAKE_INVOICE_DATE <= ?");
			params.add(paymentDateEnd);
		}
		//起始时间不为空，终止时间不为空
		if(!StringUtils.isSpace(paymentDateStart)&&!StringUtils.isSpace(paymentDateEnd)){
			sb.append("AND EXPECT_MAKE_INVOICE_DATE BETWEEN ? AND ?");
			params.add(paymentDateStart);
			params.add(paymentDateEnd);
		}
		if(!StringUtils.isSpace(invStatus)){
			sb.append("AND INVOICE_STATUS = ? ");
			params.add(invStatus);
		}
		
		List<Invoice> invoiceList = getJdbcTempalte().query(sb.toString(), params.toArray(),new RowMapper<Invoice>(){
			@Override
			public Invoice mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Invoice invoice = new Invoice();
				invoice.setInvId(rs.getInt("ID"));
				invoice.setInvoiceId(rs.getString("INVOICE_ID"));
				invoice.setContractId(rs.getString("CONTRACT_ID"));
				invoice.setInvoiceIndex(rs.getInt("INVOICE_INDEX"));
				invoice.setExpectMakeInvoceDate(DateUtils.dateParseToString(rs.getDate("EXPECT_MAKE_INVOICE_DATE")));
				invoice.setActualMakeInvoiceDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_MAKE_INVOICE_DATE")));
				invoice.setExpectPaymentDate(DateUtils.dateParseToString(rs.getDate("EXPECT_PAYMENT_DATE")));
				invoice.setActualPaymentDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_PAYMENT_DATE")));
				invoice.setMakeInvoiceCompanyName(rs.getString("MAKE_INVOICE_COMPANY_NAME"));
				invoice.setAmtOfTax(rs.getBigDecimal("AMT_OF_TAX"));
				invoice.setAmtOfNoTax(rs.getBigDecimal("AMT_OF_NO_TAX"));
				invoice.setAmt(rs.getBigDecimal("AMT"));
				invoice.setRate(rs.getDouble("RATE"));
				invoice.setReceiveAmt(rs.getBigDecimal("RECEIVE_AMT"));
				invoice.setNotReceiveAmt(rs.getBigDecimal("NOT_RECEIVE_AMT"));
				invoice.setInvoiceStatus(Constants.numParseStr(rs.getString("INVOICE_STATUS")));
				invoice.setInvoiceType(Constants.parseToStr(rs.getString("INVOICE_TYPE")));
				invoice.setCompanyNameOfPurchaser(rs.getString("COMPANY_NAME_OF_PURCHASER"));
				invoice.setTpIdeNumOfPurchaser(rs.getString("TP_I_DE_NUM_OF_PURCHASER"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfPurchaser(rs.getString("BANK_NUM_OF_PURCHASER"));
				invoice.setCompanyNameOfSale(rs.getString("COMPANY_NAME_OF_SALE"));
				invoice.setTpIdeNumOfSale(rs.getString("TP_I_DE_NUM_OF_SALE"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfSale(rs.getString("BANK_NUM_OF_SALE"));
				invoice.setBankTypeOfSale(rs.getString("BANK_TYPE_OF_SALE"));
				invoice.setRemarkType(rs.getString("REMARK_TYPE"));
				invoice.setAddresseeName(rs.getString("ADDRESSEE_NAME"));
				invoice.setAddress(rs.getString("ADRESS"));
				invoice.setPhoneNumber(rs.getString("PHONE_NUMBER_OF_ADDRESSEE"));
				invoice.setExpressDate(DateUtils.dateParseToString(rs.getDate("EXPRESS_DATE")));
				invoice.setInvRemark(rs.getString("INV_REMARK"));
				invoice.setIsCompany(rs.getBoolean("IS_COMPANY"));
				invoice.setInvoiceContent(rs.getString("INVOICE_CONTENT"));
				invoice.setIsCompany(rs.getBoolean("IS_CONTRACT"));
				return invoice;
			}});
		if(null!=invoiceList&&invoiceList.size()>0){
			return invoiceList;
		}
		
		return null;
	}

	@Override
	public Invoice findByInvoiceId(String invoiceId) {
		
		String sql ="select * from t_invoice where INVOICE_ID = ? AND ABLED = 1 ";
		
		List<Invoice> invoiceList = getJdbcTempalte().query(sql, new Object[]{invoiceId}, new RowMapper<Invoice>(){

			@Override
			public Invoice mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Invoice invoice = new Invoice();
				invoice.setInvId(rs.getInt("ID"));
				invoice.setInvoiceId(rs.getString("INVOICE_ID"));
				invoice.setContractId(rs.getString("CONTRACT_ID"));
				invoice.setInvoiceIndex(rs.getInt("INVOICE_INDEX"));
				invoice.setExpectMakeInvoceDate(DateUtils.dateParseToString(rs.getDate("EXPECT_MAKE_INVOICE_DATE")));
				invoice.setActualMakeInvoiceDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_MAKE_INVOICE_DATE")));
				invoice.setExpectPaymentDate(DateUtils.dateParseToString(rs.getDate("EXPECT_PAYMENT_DATE")));
				invoice.setActualPaymentDate(DateUtils.dateParseToString(rs.getDate("ACTUAL_PAYMENT_DATE")));
				invoice.setMakeInvoiceCompanyName(rs.getString("MAKE_INVOICE_COMPANY_NAME"));
				invoice.setAmtOfTax(rs.getBigDecimal("AMT_OF_TAX"));
				invoice.setAmtOfNoTax(rs.getBigDecimal("AMT_OF_NO_TAX"));
				invoice.setAmt(rs.getBigDecimal("AMT"));
				invoice.setRate(rs.getDouble("RATE"));
				invoice.setReceiveAmt(rs.getBigDecimal("RECEIVE_AMT"));
				invoice.setNotReceiveAmt(rs.getBigDecimal("NOT_RECEIVE_AMT"));
				invoice.setInvoiceStatus(Constants.numParseStr(rs.getString("INVOICE_STATUS")));
				invoice.setInvoiceType(Constants.parseToStr(rs.getString("INVOICE_TYPE")));
				invoice.setCompanyNameOfPurchaser(rs.getString("COMPANY_NAME_OF_PURCHASER"));
				invoice.setTpIdeNumOfPurchaser(rs.getString("TP_I_DE_NUM_OF_PURCHASER"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfPurchaser(rs.getString("BANK_NUM_OF_PURCHASER"));
				invoice.setCompanyNameOfSale(rs.getString("COMPANY_NAME_OF_SALE"));
				invoice.setTpIdeNumOfSale(rs.getString("TP_I_DE_NUM_OF_SALE"));
				invoice.setBankTypeOfPurchaser(rs.getString("BANK_TYPE_OF_PURCHASER"));
				invoice.setBankNumberOfSale(rs.getString("BANK_NUM_OF_SALE"));
				invoice.setBankTypeOfSale(rs.getString("BANK_TYPE_OF_SALE"));
				invoice.setRemarkType(rs.getString("REMARK_TYPE"));
				invoice.setAddresseeName(rs.getString("ADDRESSEE_NAME"));
				invoice.setAddress(rs.getString("ADRESS"));
				invoice.setPhoneNumber(rs.getString("PHONE_NUMBER_OF_ADDRESSEE"));
				invoice.setExpressDate(DateUtils.dateParseToString(rs.getDate("EXPRESS_DATE")));
				invoice.setInvRemark(rs.getString("INV_REMARK"));
				invoice.setIsContract(rs.getBoolean("IS_CONTRACT"));
				invoice.setInvoiceContent(rs.getString("INVOICE_CONTENT"));
				invoice.setIsCompany(rs.getBoolean("IS_COMPANY"));
				return invoice;
			}});
		if(null!=invoiceList&&invoiceList.size()>0){
			return invoiceList.get(0);
		}else{
			return null;
		}
	}
}
