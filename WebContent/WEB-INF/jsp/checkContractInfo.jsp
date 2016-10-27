<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>合同信息</title>
<link href="WebContent/css/invoice.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="WebContent/js/invoice.js"></script>
</head>
<body onload="errSetting()">
<font style="color:red">${errorMsg}</font>
<input  type="hidden"  id="errorMsg" value="${errorElementId}">
<form action="contract.do?method=checkContractInfo" method="post">
	合同编号<input type="text" name="contractId" value="${contract.contractId}" id="contractId">
	<input type="submit" value="搜索">
</form>
<c:if test="${!empty contract}">
<table>	
	<tr>
		<td>合同编号</td><td>:${contract.contractId}</td>
	</tr>
	<tr>
		<td>合同类型</td><td>:${contract.contractType}</td>
	</tr>
	<tr>
		<td>产品和服务</td><td>:${contract.itemName}</td>
	</tr>
	<tr>
		<td>甲方（客户）单位全称</td><td>:${contract.companyNameOfFirst}</td>
		<td>甲方联系人</td><td>:${contract.contactNameOfFirst}</td>
	</tr>
	<tr>
		<td>乙方（自己）单位全称</td><td>:${contract.companyNameOfSecond}</td>
		<td>销售代表</td><td>:${contract.sales}</td>
	</tr>
	<tr>
		<td>丙方单位全称</td><td>:${contract.companyNameOfThird}</td>
		<td>丙方联系人</td><td>:${contract.contactNameOfThird}</td>
	</tr>
	<tr>
		<td>合同签订日期</td><td>:${contract.contractSignDate}</td>
	</tr>
	<tr>
		<td>合同生效日期</td><td>:${contract.contractStartDate}</td>
		<td>合同终止日期</td><td>:${contract.contractEndDate}</td>
	</tr>
	<tr>
		<td>合同金额</td><td>:${contract.amt}</td>
		<td>付款期数</td><td>:${contract.paymentTimes}</td>
	</tr>
	<tr>
		<td>已开发票</td><td>:${contract.comleteInvoiceNumber}</td>
		<td>已开票金额</td><td>:${contract.completeInvoiceAmt}</td>
	</tr>
	<tr>
		<td>未开票金额</td><td>:${contract.remainInvoiceAmt}</td>
	</tr>
	<tr>
		<td>备注</td><td>:${contract.remark}</td>
	</tr>
</table>
</c:if>
<input type="button" value="返回" onclick="window.open('employee.do?method=mainPage','_self')"><br>
</body>
</html>