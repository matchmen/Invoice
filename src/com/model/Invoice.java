package com.model;

import java.math.BigDecimal;

public class Invoice {
	/**
	 * ID
	 */
	private Integer invId;
	/**
	 * 发票编号
	 */
	private String invoiceId;
	/**
	 * 合同编号
	 */
	private String contractId;
	/**
	 * 开票批次
	 */
	private Integer invoiceIndex;
	/**
	 * 计划开票日期
	 */
	private String expectMakeInvoceDate;
	/**
	 * 实际开票日期
	 */
	private String actualMakeInvoiceDate;
	/**
	 * 预计到账日期
	 */
	private String expectPaymentDate;
	/**
	 * 实际到账日期
	 */
	private String actualPaymentDate;
	/**
	 * 出票公司
	 */
	private String makeInvoiceCompanyName;
	/**
	 * 税额
	 */
	private BigDecimal amtOfTax;
	/**
	 * 税率
	 */
	private Double rate;
	/**
	 * 不含税金额
	 */
	private BigDecimal amtOfNoTax;
	/**
	 * 总金额
	 */
	private BigDecimal amt;
	/**
	 * 已入账金额
	 */
	private BigDecimal receiveAmt;
	/**
	 * 未入账金额
	 */
	private BigDecimal notReceiveAmt;
	/**
	 * 发票状态
	 */
	private String invoiceStatus;
	/**
	 * 发票内容
	 */
	private String invoiceContent;
	/**
	 * 发票类型
	 */
	private String invoiceType;
	/**
	 * 销售
	 */
	private String sales;
	/**
	 * 购买方名称
	 */
	private String companyNameOfPurchaser;
	/**
	 * 购买方纳税人识别编号
	 */
	private String tpIdeNumOfPurchaser;
	/**
	 * 购买方开户行
	 */
	private String bankTypeOfPurchaser;
	/**
	 * 购买方账号
	 */
	private String bankNumberOfPurchaser;
	/**
	 * 销售方名称
	 */
	private String companyNameOfSale;
	/**
	 * 销售方纳税人识别编号
	 */
	private String tpIdeNumOfSale;
	/**
	 * 销售方开户行
	 */
	private String bankTypeOfSale;
	/**
	 * 销售方账号
	 */
	private String bankNumberOfSale;
	/**
	 * 备注类型
	 */
	private String remarkType;
	/**
	 * 开票人
	 */
	private String drawer;
	/**
	 * 收票人姓名
	 */
	private String addresseeName;
	/**
	 * 收票地址
	 */
	private String address;
	/**
	 * 收票人联系电话
	 */
	private String phoneNumber;
	/**
	 * 寄送日期
	 */
	private String expressDate;
	
	/**
	 * 备注
	 */
	private String invRemark;
	/**
	 * 是否绑定合同
	 */
	private Boolean isContract;
	/**
	 * 是否绑定公司
	 */
	private Boolean isCompany;
	
	public Boolean getIsCompany() {
		return isCompany;
	}

	public void setIsCompany(Boolean isCompany) {
		this.isCompany = isCompany;
	}

	public Boolean getIsContract() {
		return isContract;
	}

	public void setIsContract(Boolean isContract) {
		this.isContract = isContract;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getExpressDate() {
		return expressDate;
	}

	public String getInvRemark() {
		return invRemark;
	}

	public void setInvRemark(String invRemark) {
		this.invRemark = invRemark;
	}

	public void setExpressDate(String expressDate) {
		this.expressDate = expressDate;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public Integer getInvId() {
		return invId;
	}

	public void setInvId(Integer invId) {
		this.invId = invId;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public Integer getInvoiceIndex() {
		return invoiceIndex;
	}

	public void setInvoiceIndex(Integer invoiceIndex) {
		this.invoiceIndex = invoiceIndex;
	}

	public String getExpectMakeInvoceDate() {
		return expectMakeInvoceDate;
	}

	public void setExpectMakeInvoceDate(String expectMakeInvoceDate) {
		this.expectMakeInvoceDate = expectMakeInvoceDate;
	}

	public String getActualMakeInvoiceDate() {
		return actualMakeInvoiceDate;
	}

	public void setActualMakeInvoiceDate(String actualMakeInvoiceDate) {
		this.actualMakeInvoiceDate = actualMakeInvoiceDate;
	}

	public String getExpectPaymentDate() {
		return expectPaymentDate;
	}

	public void setExpectPaymentDate(String expectPaymentDate) {
		this.expectPaymentDate = expectPaymentDate;
	}

	public String getActualPaymentDate() {
		return actualPaymentDate;
	}

	public void setActualPaymentDate(String actualPaymentDate) {
		this.actualPaymentDate = actualPaymentDate;
	}

	public String getMakeInvoiceCompanyName() {
		return makeInvoiceCompanyName;
	}

	public void setMakeInvoiceCompanyName(String makeInvoiceCompanyName) {
		this.makeInvoiceCompanyName = makeInvoiceCompanyName;
	}

	public BigDecimal getAmtOfTax() {
		return amtOfTax;
	}

	public void setAmtOfTax(BigDecimal amtOfTax) {
		this.amtOfTax = amtOfTax;
	}

	public BigDecimal getAmtOfNoTax() {
		return amtOfNoTax;
	}

	public void setAmtOfNoTax(BigDecimal amtOfNoTax) {
		this.amtOfNoTax = amtOfNoTax;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
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

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getCompanyNameOfPurchaser() {
		return companyNameOfPurchaser;
	}

	public void setCompanyNameOfPurchaser(String companyNameOfPurchaser) {
		this.companyNameOfPurchaser = companyNameOfPurchaser;
	}

	public String getTpIdeNumOfPurchaser() {
		return tpIdeNumOfPurchaser;
	}

	public void setTpIdeNumOfPurchaser(String tpIdeNumOfPurchaser) {
		this.tpIdeNumOfPurchaser = tpIdeNumOfPurchaser;
	}

	public String getBankTypeOfPurchaser() {
		return bankTypeOfPurchaser;
	}

	public void setBankTypeOfPurchaser(String bankTypeOfPurchaser) {
		this.bankTypeOfPurchaser = bankTypeOfPurchaser;
	}

	public String getBankNumberOfPurchaser() {
		return bankNumberOfPurchaser;
	}

	public void setBankNumberOfPurchaser(String bankNumberOfPurchaser) {
		this.bankNumberOfPurchaser = bankNumberOfPurchaser;
	}

	public String getCompanyNameOfSale() {
		return companyNameOfSale;
	}

	public void setCompanyNameOfSale(String companyNameOfSale) {
		this.companyNameOfSale = companyNameOfSale;
	}

	public String getTpIdeNumOfSale() {
		return tpIdeNumOfSale;
	}

	public void setTpIdeNumOfSale(String tpIdeNumOfSale) {
		this.tpIdeNumOfSale = tpIdeNumOfSale;
	}

	public String getBankTypeOfSale() {
		return bankTypeOfSale;
	}

	public void setBankTypeOfSale(String bankTypeOfSale) {
		this.bankTypeOfSale = bankTypeOfSale;
	}

	public String getBankNumberOfSale() {
		return bankNumberOfSale;
	}

	public void setBankNumberOfSale(String bankNumberOfSale) {
		this.bankNumberOfSale = bankNumberOfSale;
	}

	public String getRemarkType() {
		return remarkType;
	}

	public void setRemarkType(String remarkType) {
		this.remarkType = remarkType;
	}

	public String getDrawer() {
		return drawer;
	}

	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}

	public String getAddresseeName() {
		return addresseeName;
	}

	public void setAddresseeName(String addresseeName) {
		this.addresseeName = addresseeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
