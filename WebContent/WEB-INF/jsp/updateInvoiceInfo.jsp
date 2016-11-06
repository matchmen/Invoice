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
<c:if test="${empty invoice}">
合同编号<input type="text" name="invoId" value="${invoice.invoiceId}" id="invoId">
<input type="button" value="搜索" onclick="find()">
</c:if>
<c:if test="${!empty invoice}">
<input  type="hidden" name="invId" id="invId" value="${invoice.invId}">
<table>
	<tr>
		<td>开票批次</td>
		<td>合同编号</td>
		<td>发票编号</td>
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
		<td>乙方纳税人编号</td>
		<td>乙方开户行</td>
		<td>乙方银行账户</td>
		<td>发票收件人</td>
		<td>收件地址</td>
		<td>收件人电话</td>
		<td>寄送日期</td>
	</tr>
	<tr>
		<td><input type="text" readonly="readonly" name="invoiceIndex" id="invoiceIndex" value="${invoice.invoiceIndex}" ></td>
		<td><input type="text" readonly="readonly" name="contractId" id="contractId" value="${invoice.contractId}" ></td>
		<td><input type="text" readonly="readonly" name="invoiceId" id="invoiceId" value="${invoice.invoiceId}" ></td>
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
	    <td><input type="text" name="remarkType" id="remarkType" value="${invoice.remarkType}" ></td>
	    <td><input type="text" name="expectMakeInvoceDate" id="expectMakeInvoceDate" value="${invoice.expectMakeInvoceDate}" ></td>
	    <td><input type="text" name="actualMakeInvoiceDate" id="actualMakeInvoiceDate" value="${invoice.actualMakeInvoiceDate}" ></td>
	    <td><input type="text" name="expectPaymentDate" id="expectPaymentDate" value="${invoice.expectPaymentDate}" ></td>
	    <td><input type="text" name="actualPaymentDate" id=actualPaymentDate value="${invoice.actualPaymentDate}" ></td>
	    <td><input type="text" name="makeInvoiceCompanyName" id="makeInvoiceCompanyName" value="${invoice.makeInvoiceCompanyName}" ></td>
	    <td><input type="text" name="invoiceContent" id="invoiceContent" value="${invoice.invoiceContent}" ></td>
	    <td><input type="text" name="invoiceType" id="invoiceType" value="${invoice.invoiceType}" ></td>
	    <td><input type="text" name="amt" id="amt" value="${invoice.amt}" ></td>
	    <td><input type="text" name="amtOfTax" id="amtOfTax" value="${invoice.amtOfTax}" ></td>
	    <td><input type="text" name="rate" id="rate" value="${invoice.rate}" ></td>
	    <td><input type="text" name="amtOfNoTax" id="amtOfNoTax" value="${invoice.amtOfNoTax}" ></td>
	    <td><input type="text" name="receiveAmt" id="receiveAmt" value="${invoice.receiveAmt}" ></td>
	    <td><input type="text" name="notReceiveAmt" id="notReceiveAmt" value="${invoice.notReceiveAmt}" ></td>
	    <td><input type="text" name="companyNameOfPurchaser" id="companyNameOfPurchaser" value="${invoice.companyNameOfPurchaser}" ></td>
	    <td><input type="text" name="tpIdeNumOfPurchaser" id="tpIdeNumOfPurchaser" value="${invoice.tpIdeNumOfPurchaser}" ></td>
	    <td><input type="text" name="bankTypeOfPurchaser" id="bankTypeOfPurchaser" value="${invoice.bankTypeOfPurchaser}" ></td>
	    <td><input type="text" name="bankNumberOfPurchaser" id="bankNumberOfPurchaser" value="${invoice.bankNumberOfPurchaser}" ></td>
	    <td><input type="text" name="companyNameOfSale" id="companyNameOfSale" value="${invoice.companyNameOfSale}" ></td>
	    <td><input type="text" name="tpIdeNumOfSale" id="tpIdeNumOfSale" value="${invoice.tpIdeNumOfSale}" ></td>
	    <td><input type="text" name="bankTypeOfSale" id="bankTypeOfSale" value="${invoice.bankTypeOfSale}" ></td>
	    <td><input type="text" name="addresseeName" id="addresseeName" value="${invoice.addresseeName}" ></td>
	    <td><input type="text" name="address" id="address" value="${invoice.address}" ></td>
	    <td><input type="text" name="phoneNumber" id="phoneNumber" value="${invoice.phoneNumber}" ></td>
	    <td><input type="text" name="expressDate" id="expressDate" value="${invoice.expressDate}" ></td>
	</tr>
</table>	
</c:if>
<c:if test="${!empty invoice}">
<input type="submit" value="保存修改" >
</c:if>
<input type="button" value="取消" onclick="window.open('employee.do?method=mainPage','_self')"><br>
</form>
</body>
</html>