<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看发票信息</title>
<link href="WebContent/css/invoice.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="WebContent/js/invoice.js"></script>
</head>
<body onload="errSetting()">
<font style="color:red">${errorMsg}</font>
<input  type="hidden"  id="errorMsg" value="${errorElementId}">
<form action="invoice.do?method=checkInvoiceInfo" method="post">
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
	<input type="submit" value="搜索">
</form>
<c:if test="${!empty invoiceList}">
<table>
	<tr>
		<td>操作</td>
		<td>开票批次</td>
		<td>合同编号</td>
		<td>发票状态</td>
		<td>备注类型</td>
		<td>计划开票日期</td>
		<td>实际开票日期</td>
		<td>预计到账日期</td>
		<td>实际到账日期</td>
		<td>出票公司</td>
		<td>发票内容</td>
		<td>发票类型</td>
		<td>发票金额</td>
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
	<c:forEach items="${invoiceList}" var="invoice">
	<tr>
		<td><input type="button" onclick="window.open('invoice.do?method=view&id=${invoice.invId}')" value="预览" ></td>
		<td><input disabled="disabled" value="${invoice.invoiceIndex}"></td>
		<td><input disabled="disabled" value="${invoice.contractId}"></td>
	    <td><input disabled="disabled" value="${invoice.invoiceStatus}"></td>
	    <td><input disabled="disabled" value="${invoice.remarkType}"></td>
	    <td><input disabled="disabled" value="${invoice.expectMakeInvoceDate}"></td>
	    <td><input disabled="disabled" value="${invoice.actualMakeInvoiceDate}"></td>
	    <td><input disabled="disabled" value="${invoice.expectPaymentDate}"></td>
	    <td><input disabled="disabled" value="${invoice.actualPaymentDate}"></td>
	    <td><input disabled="disabled" value="${invoice.makeInvoiceCompanyName}"></td>
		<td><input disabled="disabled" value="${invoice.invoiceContent}"></td>
		<td><input disabled="disabled" value="${invoice.invoiceType}"></td>
		<td><input disabled="disabled" value="${invoice.amt}"></td>
		<td><input disabled="disabled" value="${invoice.amtOfTax}"></td>
		<td><input disabled="disabled" value="${invoice.rate}"></td>
		<td><input disabled="disabled" value="${invoice.amtOfNoTax}"></td>
		<td><input disabled="disabled" value="${invoice.receiveAmt}"></td>
		<td><input disabled="disabled" value="${invoice.notReceiveAmt}"></td>
		<td><input disabled="disabled" value="${invoice.companyNameOfPurchaser}"></td>
		<td><input disabled="disabled" value="${invoice.tpIdeNumOfPurchaser}"></td>
		<td><input disabled="disabled" value="${invoice.bankTypeOfPurchaser}"></td>
		<td><input disabled="disabled" value="${invoice.bankNumberOfPurchaser}"></td>
		<td><input disabled="disabled" value="${invoice.companyNameOfSale}"></td>
		<td><input disabled="disabled" value="${invoice.tpIdeNumOfSale}"></td>
		<td><input disabled="disabled" value="${invoice.bankTypeOfSale}"></td>
		<td><input disabled="disabled" value="${invoice.addresseeName}"></td>
		<td><input disabled="disabled" value="${invoice.address}"></td>
		<td><input disabled="disabled" value="${invoice.phoneNumber}"></td>
		<td><input disabled="disabled" value="${invoice.expressDate}"></td>
	</tr>
	</c:forEach>
</table>
</c:if>
</body>
</html>