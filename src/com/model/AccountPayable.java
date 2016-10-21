package com.model;

import java.math.BigDecimal;
import java.util.Date;

public class AccountPayable {

	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 合同编号
	 */
	private String contractId;
	/**
	 * 客户公司名称
	 */
	private String companyName;
	/**
	 * 开票内容
	 */
	private String invoiceContent;
	/**
	 * 开票日期
	 */
	private String makeInvoiceDate;
	/**
	 * 含税金额
	 */
	private BigDecimal totalAmt;
	/**
	 * 不含税金额
	 */
	private BigDecimal noTaxAmt;
	/**
	 * 税率
	 */
	private String rate;
	/**
	 * 税额
	 */
	private String taxAmt;
	/**
	 * 合同收款日期
	 */
	private Date expectPaymentDate;
	/**
	 * 合同收款期限
	 */
	private Integer paymentDays;
	/**
	 * 逾期天数
	 */
	private Integer overdueDays;
	/**
	 * 收款时间
	 */
	private Date paymentDate;
	/**
	 * 已收账款
	 */
	private BigDecimal receiveAmt;
	/**
	 * 未收账款
	 */
	private BigDecimal notReceiveAmt;
	/**
	 * 项目销售
	 */
	private String sale;
	/**
	 * 项目经理
	 */
	private String projectManager;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public String getMakeInvoiceDate() {
		return makeInvoiceDate;
	}

	public void setMakeInvoiceDate(String makeInvoiceDate) {
		this.makeInvoiceDate = makeInvoiceDate;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getNoTaxAmt() {
		return noTaxAmt;
	}

	public void setNoTaxAmt(BigDecimal noTaxAmt) {
		this.noTaxAmt = noTaxAmt;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(String taxAmt) {
		this.taxAmt = taxAmt;
	}

	public Date getExpectPaymentDate() {
		return expectPaymentDate;
	}

	public void setExpectPaymentDate(Date expectPaymentDate) {
		this.expectPaymentDate = expectPaymentDate;
	}

	public Integer getPaymentDays() {
		return paymentDays;
	}

	public void setPaymentDays(Integer paymentDays) {
		this.paymentDays = paymentDays;
	}

	public Integer getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getReceiveAmt() {
		return receiveAmt;
	}

	public void setReceiveAmt(BigDecimal receiveAmt) {
		this.receiveAmt = receiveAmt;
	}

	public BigDecimal getNotReceiveAmt() {
		return notReceiveAmt;
	}

	public void setNotReceiveAmt(BigDecimal notReceiveAmt) {
		this.notReceiveAmt = notReceiveAmt;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
