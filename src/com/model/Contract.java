package com.model;

import java.math.BigDecimal;
import java.util.Date;

public class Contract {
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 合同编号
	 */
	private String contractId;
	/**
	 * 合同类型
	 */
	private String contractType;
	/**
	 * 甲方单位名称
	 */
	private String companyNameOfFirst;
	/**
	 * 甲方联系人
	 */
	private String contactNameOfFirst;
	/**
	 * 乙方单位名称
	 */
	private String companyNameOfSecond;
	/**
	 * 乙方联系人
	 */
	private String contactNameOfSecond;
	/**
	 * 丙方单位名称
	 */
	private String companyNameOfThird;
	/**
	 * 丙方联系人
	 */
	private String contactNameOfThird;
	/**
	 * 销售
	 */
	private String sales;
	/**
	 * 项目名称
	 */
	private String itemName;
	/**
	 * 合同签订日期
	 */
	private Date contractSignDate;
	/**
	 * 合同生效日期
	 */
	private Date contractStartDate;
	/**
	 * 合同终止日期
	 */
	private Date contractEndDate;
	/**
	 * 金额
	 */
	private BigDecimal amt;
	/**
	 * 付款期数
	 */
	private Integer paymentTimes;
	/**
	 * 已开发票数
	 */
	private Integer comleteInvoiceNumber;
	/**
	 * 已开票金额
	 */
	private BigDecimal completeInvoiceAmt;
	/**
	 * 未开票金额
	 */
	private BigDecimal remainInvoiceAmt;
	/**
	 * 备注
	 */
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getCompanyNameOfFirst() {
		return companyNameOfFirst;
	}

	public void setCompanyNameOfFirst(String companyNameOfFirst) {
		this.companyNameOfFirst = companyNameOfFirst;
	}

	public String getContactNameOfFirst() {
		return contactNameOfFirst;
	}

	public void setContactNameOfFirst(String contactNameOfFirst) {
		this.contactNameOfFirst = contactNameOfFirst;
	}

	public String getCompanyNameOfSecond() {
		return companyNameOfSecond;
	}

	public void setCompanyNameOfSecond(String companyNameOfSecond) {
		this.companyNameOfSecond = companyNameOfSecond;
	}

	public String getContactNameOfSecond() {
		return contactNameOfSecond;
	}

	public void setContactNameOfSecond(String contactNameOfSecond) {
		this.contactNameOfSecond = contactNameOfSecond;
	}

	public String getCompanyNameOfThird() {
		return companyNameOfThird;
	}

	public void setCompanyNameOfThird(String companyNameOfThird) {
		this.companyNameOfThird = companyNameOfThird;
	}

	public String getContactNameOfThird() {
		return contactNameOfThird;
	}

	public void setContactNameOfThird(String contactNameOfThird) {
		this.contactNameOfThird = contactNameOfThird;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getContractSignDate() {
		return contractSignDate;
	}

	public void setContractSignDate(Date contractSignDate) {
		this.contractSignDate = contractSignDate;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public Integer getPaymentTimes() {
		return paymentTimes;
	}

	public void setPaymentTimes(Integer paymentTimes) {
		this.paymentTimes = paymentTimes;
	}

	public Integer getComleteInvoiceNumber() {
		return comleteInvoiceNumber;
	}

	public void setComleteInvoiceNumber(Integer comleteInvoiceNumber) {
		this.comleteInvoiceNumber = comleteInvoiceNumber;
	}

	public BigDecimal getCompleteInvoiceAmt() {
		return completeInvoiceAmt;
	}

	public void setCompleteInvoiceAmt(BigDecimal completeInvoiceAmt) {
		this.completeInvoiceAmt = completeInvoiceAmt;
	}

	public BigDecimal getRemainInvoiceAmt() {
		return remainInvoiceAmt;
	}

	public void setRemainInvoiceAmt(BigDecimal remainInvoiceAmt) {
		this.remainInvoiceAmt = remainInvoiceAmt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
