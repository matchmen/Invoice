<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改发票信息</title>
<link href="WebContent/css/invoice.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="WebContent/js/invoice.js"></script>
<script>
function find(){
	var formElement = document.getElementById('formId');
	formElement.setAttribute('action','invoice.do?method=findInvoiceInfo'); 
	formElement.submit(); 
}
</script>
</head>
<body onload="errSetting()">
<font style="color:red">${errorMsg}</font>
<input  type="hidden"  id="errorMsg" value="${errorElementId}">
<form id="formId" method="post" action="invoice.do?method=updateInvoiceInfo">
	客户名称<input type="text" name="firstName" id="firstName" value="${firstName}">
	开票日期<input type="text" placeholder="yyyy/mm/dd" name="makeInvoceDateStart" id="makeInvoceDateStart" value="${makeInvoceDateStart}">
	-<input type="text" placeholder="yyyy/mm/dd" name="makeInvoceDateEnd" id="makeInvoceDateEnd" value="${makeInvoceDateEnd}">
	收款日期<input type="text" placeholder="yyyy/mm/dd" name="paymentDateStart" id="paymentDateStart" value="${paymentDateStart}">
	-<input type="text" placeholder="yyyy/mm/dd" name="paymentDateEnd" id="paymentDateEnd" value="${paymentDateEnd}">
	发票状态<select name="invStatus" id="invStatus" >
	    	<option value="01">已开具</option>
			<option value="02">已检视</option>
			<option value="03">已寄出</option>
			<option value="04">已收到</option>
			<option value="05">已入账</option>
			<option value="06">已付款</option>
			<option value="07">已确认开错</option>
			<option value="08">已寄出(错)</option>
			<option value="09">已收到(错)</option>
			<option value="10">已作废</option>
			<option value="11">已红冲</option>
			<option value="12">已重开</option>
	    </select>
<input type="button" value="搜索" onclick="find()">
<input type="hidden" name="invId" id="invId" value="${invoiceBean.invoice.invId}" >
<table>
<c:if test="${!empty invoiceBean}">
	<tr>
		<td>合同编号</td>
		<td>发票编号</td>
		<td>开票批次</td>
		<td>发票状态</td>
		<td>备注类型</td>
		<td>计划开票日期</td>
		<td>实际开票日期</td>
		<td>预计到账日期</td>
		<td>实际到账日期</td>
		<td>出票公司</td>
		<td>发票内容</td>
		<td>发票类型</td>
		<td>发票总金额</td>
		<td>税额</td>
		<td>税率</td>
		<td>不含税金额</td>
		<td>已入账金额</td>
		<td>未入账金额</td>
		<td>发票抬头</td>
		<td>甲方纳税人编号</td>
		<td>甲方开户行</td>
		<td>甲方银行账户</td>
		<td>乙方公司名称或个人姓名</td>
		<td>乙方纳税人编号</td>
		<td>乙方开户行</td>
		<td>乙方银行账户</td>
		<td>发票收件人</td>
		<td>收件地址</td>
		<td>收件人电话</td>
		<td>寄送日期</td>
	</tr>
<c:if test="${!empty invoiceBean.invoice}">
	<tr>
		<td><input type="text" readonly="readonly" name="contractId" id="contractId" value="${invoiceBean.invoice.contractId}" ></td>
		<td><input type="text" readonly="readonly" name="invoiceId" id="invoiceId" value="${invoiceBean.invoice.invoiceId}" ></td>
		<td><input type="text" name="invoiceIndex" id="invoiceIndex" value="${invoiceBean.invoice.invoiceIndex}" ></td>
		<td><select name="invoiceStatus" id="invoiceStatus" >
	    	<option value="01">已开具</option>
			<option value="02">已检视</option>
			<option value="03">已寄出</option>
			<option value="04">已收到</option>
			<option value="05">已入账</option>
			<option value="06">已付款</option>
			<option value="07">已确认开错</option>
			<option value="08">已寄出(错)</option>
			<option value="09">已收到(错)</option>
			<option value="10">已作废</option>
			<option value="11">已红冲</option>
			<option value="12">已重开</option>
	    </select></td>
	    <td><input type="text" name="remarkType" id="remarkType" value="${invoiceBean.invoice.remarkType}" ></td>
	    <td><input type="text" name="expectMakeInvoceDate" id="expectMakeInvoceDate" value="${invoiceBean.invoice.expectMakeInvoceDate}" ></td>
	    <td><input type="text" name="actualMakeInvoiceDate" id="actualMakeInvoiceDate" value="${invoiceBean.invoice.actualMakeInvoiceDate}" ></td>
	    <td><input type="text" name="expectPaymentDate" id="expectPaymentDate" value="${invoiceBean.invoice.expectPaymentDate}" ></td>
	    <td><input type="text" name="actualPaymentDate" id=actualPaymentDate value="${invoiceBean.invoice.actualPaymentDate}" ></td>
	    <td><input type="text" name="makeInvoiceCompanyName" id="makeInvoiceCompanyName" value="${invoiceBean.invoice.makeInvoiceCompanyName}" ></td>
	    <td><input type="text" name="invoiceContent" id="invoiceContent" value="${invoiceBean.invoice.invoiceContent}" ></td>
	    <td><input type="text" name="invoiceType" id="invoiceType" value="${invoiceBean.invoice.invoiceType}" ></td>
	    <td><input type="text" name="amt" id="amt" value="${invoiceBean.invoice.amt}" ></td>
	    <td><input type="text" name="amtOfTax" id="amtOfTax" value="${invoiceBean.invoice.amtOfTax}" ></td>
	    <td><input type="text" name="rate" id="rate" value="${invoiceBean.invoice.rate}" ></td>
	    <td><input type="text" name="amtOfNoTax" id="amtOfNoTax" value="${invoiceBean.invoice.amtOfNoTax}" ></td>
	    <td><input type="text" name="receiveAmt" id="receiveAmt" value="${invoiceBean.invoice.receiveAmt}" ></td>
	    <td><input type="text" name="notReceiveAmt" id="notReceiveAmt" value="${invoiceBean.invoice.notReceiveAmt}" ></td>
	    <td><input type="text" name="companyNameOfPurchaser" id="companyNameOfPurchaser" value="${invoiceBean.invoice.companyNameOfPurchaser}" ></td>
	    <td><input type="text" name="tpIdeNumOfPurchaser" id="tpIdeNumOfPurchaser" value="${invoiceBean.invoice.tpIdeNumOfPurchaser}" ></td>
	    <td><input type="text" name="bankTypeOfPurchaser" id="bankTypeOfPurchaser" value="${invoiceBean.invoice.bankTypeOfPurchaser}" ></td>
	    <td><input type="text" name="bankNumberOfPurchaser" id="bankNumberOfPurchaser" value="${invoiceBean.invoice.bankNumberOfPurchaser}" ></td>
	    <td><input type="text" name="companyNameOfSale" id="companyNameOfSale" value="${invoiceBean.invoice.companyNameOfSale}" ></td>
	    <td><input type="text" name="tpIdeNumOfSale" id="tpIdeNumOfSale" value="${invoiceBean.invoice.tpIdeNumOfSale}" ></td>
	    <td><input type="text" name="bankTypeOfSale" id="bankTypeOfSale" value="${invoiceBean.invoice.bankTypeOfSale}" ></td>
	    <td><input type="text" name="bankNumberOfSale" id="bankNumberOfSale" value="${invoiceBean.invoice.bankNumberOfSale}" ></td>
	    <td><input type="text" name="addresseeName" id="addresseeName" value="${invoiceBean.invoice.addresseeName}" ></td>
	    <td><input type="text" name="address" id="address" value="${invoiceBean.invoice.address}" ></td>
	    <td><input type="text" name="phoneNumber" id="phoneNumber" value="${invoiceBean.invoice.phoneNumber}" ></td>
	    <td><input type="text" name="expressDate" id="expressDate" value="${invoiceBean.invoice.expressDate}" ></td>
	</tr>
</c:if>
<c:forEach items="${invoiceBean.invoiceList}" var="invoice">
	<tr>
		<td><a href="invoice.do?method=findInv&invoiceId=${invoice.invoiceId}">${invoice.invoiceId}</a></td>
		<td><input type="text" readonly="readonly" name="invoiceIndex" id="invoiceIndex" value="${invoice.invoiceIndex}" ></td>
		<td><input type="text" readonly="readonly" name="contractId" id="contractId" value="${invoice.contractId}" ></td>
		<td><input type="text" readonly="readonly" name="invoiceStatus" id="invoiceStatus" value="${invoice.invoiceStatus}" ></td>
	    <td><input type="text" readonly="readonly" name="remarkType" id="remarkType" value="${invoice.remarkType}" ></td>
	    <td><input type="text" readonly="readonly" name="expectMakeInvoceDate" id="expectMakeInvoceDate" value="${invoice.expectMakeInvoceDate}" ></td>
	    <td><input type="text" readonly="readonly" name="actualMakeInvoiceDate" id="actualMakeInvoiceDate" value="${invoice.actualMakeInvoiceDate}" ></td>
	    <td><input type="text" readonly="readonly" name="expectPaymentDate" id="expectPaymentDate" value="${invoice.expectPaymentDate}" ></td>
	    <td><input type="text" readonly="readonly" name="actualPaymentDate" id=actualPaymentDate value="${invoice.actualPaymentDate}" ></td>
	    <td><input type="text" readonly="readonly" name="makeInvoiceCompanyName" id="makeInvoiceCompanyName" value="${invoice.makeInvoiceCompanyName}" ></td>
	    <td><input type="text" readonly="readonly" name="invoiceContent" id="invoiceContent" value="${invoice.invoiceContent}" ></td>
	    <td><input type="text" readonly="readonly" name="invoiceType" id="invoiceType" value="${invoice.invoiceType}" ></td>
	    <td><input type="text" readonly="readonly" name="amt" id="amt" value="${invoice.amt}" ></td>
	    <td><input type="text" readonly="readonly" name="amtOfTax" id="amtOfTax" value="${invoice.amtOfTax}" ></td>
	    <td><input type="text" readonly="readonly" name="rate" id="rate" value="${invoice.rate}" ></td>
	    <td><input type="text" readonly="readonly" name="amtOfNoTax" id="amtOfNoTax" value="${invoice.amtOfNoTax}" ></td>
	    <td><input type="text" readonly="readonly" name="receiveAmt" id="receiveAmt" value="${invoice.receiveAmt}" ></td>
	    <td><input type="text" readonly="readonly" name="notReceiveAmt" id="notReceiveAmt" value="${invoice.notReceiveAmt}" ></td>
	    <td><input type="text" readonly="readonly" name="companyNameOfPurchaser" id="companyNameOfPurchaser" value="${invoice.companyNameOfPurchaser}" ></td>
	    <td><input type="text" readonly="readonly" name="tpIdeNumOfPurchaser" id="tpIdeNumOfPurchaser" value="${invoice.tpIdeNumOfPurchaser}" ></td>
	    <td><input type="text" readonly="readonly" name="bankTypeOfPurchaser" id="bankTypeOfPurchaser" value="${invoice.bankTypeOfPurchaser}" ></td>
	    <td><input type="text" readonly="readonly" name="bankNumberOfPurchaser" id="bankNumberOfPurchaser" value="${invoice.bankNumberOfPurchaser}" ></td>
	    <td><input type="text" readonly="readonly" name="companyNameOfSale" id="companyNameOfSale" value="${invoice.companyNameOfSale}" ></td>
	    <td><input type="text" readonly="readonly" name="tpIdeNumOfSale" id="tpIdeNumOfSale" value="${invoice.tpIdeNumOfSale}" ></td>
	    <td><input type="text" readonly="readonly" name="bankTypeOfSale" id="bankTypeOfSale" value="${invoice.bankTypeOfSale}" ></td>
	    <td><input type="text" readonly="readonly" name="addresseeName" id="addresseeName" value="${invoice.addresseeName}" ></td>
	    <td><input type="text" readonly="readonly" name="address" id="address" value="${invoice.address}" ></td>
	    <td><input type="text" readonly="readonly" name="phoneNumber" id="phoneNumber" value="${invoice.phoneNumber}" ></td>
	    <td><input type="text" readonly="readonly" name="expressDate" id="expressDate" value="${invoice.expressDate}" ></td>
	</tr>
</c:forEach>	
</c:if>
</table>
<c:if test="${!empty invoiceBean.invoice}">
<input type="submit" value="保存修改" >
</c:if>
</form>
</body>
</html>