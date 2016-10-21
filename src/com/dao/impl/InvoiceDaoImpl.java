package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.dao.DaoJdbcTemplate;
import com.dao.InvoiceDao;
import com.model.Invoice;

public class InvoiceDaoImpl extends DaoJdbcTemplate implements InvoiceDao {

	@Override
	public void add(Invoice invoice) {
		StringBuffer sb = new StringBuffer("insert into t_invoce(")
			.append("INVOICE_ID,")
			.append("CONTRACT_ID,")
			.append("INVOICE_INDEX,")
			.append("EXPECT_MAKE_INVOICE_DATE,")
			.append("ACTUAL_MAKE_INVOICE_DATE,")
			.append("EXPECT_PAYMENT_DATEDATE,")
			.append("ACTUAL_PAYMENT_DATEDATE,")
			.append("MAKE_INVOICE_COMPANY_NAME,")
			.append("AMT_OF_TAX,")
			.append("AMT_OF_NO_TAX,")
			.append("AMT,")
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
			.append("EXPRESS_DATE")
			.append(" values('")
			.append(invoice.getInvoiceId())
			.append("','")
			.append(invoice.getContractId())
			.append("','")
			.append(invoice.getInvoiceIndex())
			.append("','")
			.append(invoice.getExpectMakeInvoceDate())
			.append("','")
			.append(invoice.getActualMakeInvoiceDate())
			.append("','")
			.append(invoice.getExpectPaymentDate())
			.append("','")
			.append(invoice.getActualPaymentDate())
			.append("','")
			.append(invoice.getMakeInvoiceCompanyName())
			.append("','")
			.append(invoice.getAmtOfTax())
			.append("','")
			.append(invoice.getAmtOfNoTax())
			.append("','")
			.append(invoice.getAmt())
			.append("','")
			.append(invoice.getReceiveAmt())
			.append("','")
			.append(invoice.getNotReceiveAmt())
			.append("','")
			.append(invoice.getInvoiceStatus())
			.append("','")
			.append(invoice.getInvoiceContent())
			.append("','")
			.append(invoice.getInvoiceType())
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
			.append(invoice.getExpressDate())
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
				.append("EXPECT_PAYMENT_DATEDATE = ? ,")
				.append("ACTUAL_PAYMENT_DATEDATE = ? ,")
				.append("MAKE_INVOICE_COMPANY_NAME = ? ,")
				.append("AMT_OF_TAX = ? ,")
				.append("AMT_OF_NO_TAX = ? ,")
				.append("AMT = ? ,")
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
				.append("EXPRESS_DATE = ? ")
				.append("where ID = ?");
		getJdbcTempalte().update(sb.toString(), new Object[]{
				invoice.getInvoiceId(),
				invoice.getContractId(),
				invoice.getInvoiceIndex(),
				invoice.getExpectMakeInvoceDate(),
				invoice.getActualMakeInvoiceDate(),
				invoice.getExpectPaymentDate(),
				invoice.getActualPaymentDate(),
				invoice.getMakeInvoiceCompanyName(),
				invoice.getAmtOfTax(),
				invoice.getAmtOfNoTax(),
				invoice.getAmt(),
				invoice.getReceiveAmt(),
				invoice.getNotReceiveAmt(),
				invoice.getInvoiceStatus(),
				invoice.getInvoiceType(),
				invoice.getCompanyNameOfPurchaser(),
				invoice.getTpIdeNumOfPurchaser(),
				invoice.getBankTypeOfPurchaser(),
				invoice.getBankNumberOfPurchaser(),
				invoice.getCompanyNameOfSale(),
				invoice.getTpIdeNumOfSale(),
				invoice.getBankTypeOfPurchaser(),
				invoice.getBankNumberOfSale(),
				invoice.getRemarkType(),
				invoice.getAddresseeName(),
				invoice.getAddress(),
				invoice.getPhoneNumber(),
				invoice.getExpressDate(),
				invoice.getId()
		});
	}

	@Override
	public Invoice find(Integer id) {
		
		String sql ="select * from t_invoice where id = ?";
		
		List<Invoice> invoiceList = getJdbcTempalte().query(sql, new Object[]{id}, new RowMapper<Invoice>(){

			@Override
			public Invoice mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Invoice invoice = new Invoice();
				invoice.setInvoiceId(rs.getString("INVOICE_ID"));
				invoice.setContractId(rs.getString("CONTRACT_ID"));
				invoice.setInvoiceIndex(rs.getInt("INVOICE_INDEX"));
				invoice.setExpectMakeInvoceDate(rs.getDate("EXPECT_MAKE_INVOICE_DATE"));
				invoice.setActualMakeInvoiceDate(rs.getDate("ACTUAL_MAKE_INVOICE_DATE"));
				invoice.setExpectPaymentDate(rs.getDate("EXPECT_PAYMENT_DATE"));
				invoice.setActualPaymentDate(rs.getDate("ACTUAL_PAYMENT_DATE"));
				invoice.setMakeInvoiceCompanyName(rs.getString("MAKE_INVOICE_COMPANY_NAME"));
				invoice.setAmtOfTax(rs.getBigDecimal("AMT_OF_TAX"));
				invoice.setAmtOfNoTax(rs.getBigDecimal("AMT_OF_NO_TAX"));
				invoice.setAmt(rs.getBigDecimal("AMT"));
				invoice.setReceiveAmt(rs.getBigDecimal("RECEIVE_AMT"));
				invoice.setNotReceiveAmt(rs.getBigDecimal("NOT_RECEIVE_AMT"));
				invoice.setInvoiceStatus(rs.getString("INVOICE_STATUS"));
				invoice.setInvoiceType(rs.getString("INVOICE_TYPE"));
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
				invoice.setExpressDate(rs.getDate("EXPRESS_DATE"));
				return invoice;
			}});
		if(null!=invoiceList&&invoiceList.size()>0){
			return invoiceList.get(0);
		}else{
			return null;
		}
	}

}
