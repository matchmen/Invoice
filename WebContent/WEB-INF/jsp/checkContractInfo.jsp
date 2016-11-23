<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>合同信息</title>
<link href="css/invoice.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/invoice.js"></script>
</head>
<body onload="errSetting()">
<font style="color:red">${errorMsg}</font>
<input  type="hidden"  id="errorMsg" value="${errorElementId}">
<form action="contract.do?method=findCheck" method="post">
	合同名称<input type="text" name="contractName" value="${contractName}">
	客户名称<input type="text" name="firstName" value="${firstName}">
	签订合同日期<input id="inp" placeholder="yyyy/mm/dd" type="text" name="signDateStart" value="${signDateStart}"/>
	-<input class="inline laydate-icon" placeholder="yyyy/mm/dd"  type="text" name="signDateEnd" value="${signDateEnd}">
	<input type="submit" value="搜索">
<c:if test="${!empty contractBean.contractList}">
<table>
	<tr>	
		<td>合同编号</td>
		<td>合同类型</td>
		<td>产品和服务</td>
		<td>甲方（客户）单位全称</td>
		<td>甲方联系人</td>
		<td>乙方（自己）单位全称</td>
		<td>销售代表</td>
		<td>丙方单位全称</td>
		<td>丙方联系人</td>
		<td>合同签订日期</td>
		<td>合同生效日期</td>
		<td>合同终止日期</td>
		<td>合同金额</td>
		<td>付款期数</td>
		<td>已开发票</td>
		<td>已开票金额</td>
		<td>未开票金额</td>
		<td>备注</td>
	</tr>
	<c:forEach items="${contractBean.contractList}" var="contract">
		<tr>
			<td><a href="contract.do?method=findConAndInvList1&contractId=${contract.contractId}">${contract.contractId}</a></td>
			<td><input disabled="disabled" value="${contract.contractType}"></td>
			<td><input disabled="disabled" value="${contract.itemName}"></td>
			<td><input disabled="disabled" value="${contract.companyNameOfFirst}"></td>
			<td><input disabled="disabled" value="${contract.contactNameOfFirst}"></td>
			<td><input disabled="disabled" value="${contract.companyNameOfSecond}"></td>
			<td><input disabled="disabled" value="${contract.sales}"></td>
			<td><input disabled="disabled" value="${contract.companyNameOfThird}"></td>
			<td><input disabled="disabled" value="${contract.contactNameOfThird}"></td>
			<td><input disabled="disabled" value="${contract.contractSignDate}"></td>
			<td><input disabled="disabled" value="${contract.contractStartDate}"></td>
			<td><input disabled="disabled" value="${contract.contractEndDate}"></td>
			<td><input disabled="disabled" value="${contract.amt}"></td>
			<td><input disabled="disabled" value="${contract.paymentTimes}"></td>
			<td><input disabled="disabled" value="${contract.comleteInvoiceNumber}"></td>
			<td><input disabled="disabled" value="${contract.completeInvoiceAmt}"></td>
			<td><input disabled="disabled" value="${contract.remainInvoiceAmt}"></td>
			<td><input disabled="disabled" value="${contract.remark}"></td>
		</tr>
	</c:forEach>
</table>
</c:if>	
<c:if test="${!empty contractBean.contract}">
<table>
	<tr>	
		<td>合同编号</td>
		<td>合同类型</td>
		<td>产品和服务</td>
		<td>甲方（客户）单位全称</td>
		<td>甲方联系人</td>
		<td>乙方（自己）单位全称</td>
		<td>销售代表</td>
		<td>丙方单位全称</td>
		<td>丙方联系人</td>
		<td>合同签订日期</td>
		<td>合同生效日期</td>
		<td>合同终止日期</td>
		<td>合同金额</td>
		<td>付款期数</td>
		<td>已开发票</td>
		<td>已开票金额</td>
		<td>未开票金额</td>
		<td>备注</td>
	</tr>
	<tr>
		<td><input disabled="disabled" value="${contractBean.contract.contractId}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.contractType}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.itemName}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.companyNameOfFirst}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.contactNameOfFirst}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.companyNameOfSecond}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.sales}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.companyNameOfThird}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.contactNameOfThird}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.contractSignDate}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.contractStartDate}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.contractEndDate}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.amt}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.paymentTimes}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.comleteInvoiceNumber}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.completeInvoiceAmt}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.remainInvoiceAmt}"></td>
		<td><input disabled="disabled" value="${contractBean.contract.remark}"></td>
	</tr>
</table>
</c:if>
<c:if test="${!empty contractBean.invoiceList}">
<font>开票信息</font>
<table>
	<tr>
		<td>开票批次</td>
		<td>发票状态</td>
		<td>发票编号</td>
		<td>备注类型</td>
		<td>计划开票日期</td>
		<td>预计到账日期</td>
		<td>出票公司</td>
		<td>发票内容</td>
		<td>发票类型</td>
		<td>发票金额</td>
		<td>税额</td>
		<td>不含税金额</td>
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
	</tr>
	<c:forEach items="${contractBean.invoiceList}" var="item">
	<tr>
		<td><input disabled="disabled" value="${item.invoiceIndex}"></td>
	    <td><input disabled="disabled" value="${item.invoiceStatus}"></td>
	    <td><input disabled="disabled" value="${item.invoiceId}"></td>
	    <td><input disabled="disabled" value="${item.remarkType}"></td>
	    <td><input disabled="disabled" value="${item.expectMakeInvoceDate}"></td>
	    <td><input disabled="disabled" value="${item.expectPaymentDate}"></td>
	    <td><input disabled="disabled" value="${item.makeInvoiceCompanyName}"></td>
		<td><input disabled="disabled" value="${item.invoiceContent}"></td>
		<td><input disabled="disabled" value="${item.invoiceType}"></td>
		<td><input disabled="disabled" value="${item.amt}"></td>
		<td><input disabled="disabled" value="${item.amtOfTax}"></td>
		<td><input disabled="disabled" value="${item.amtOfNoTax}"></td>
		<td><input disabled="disabled" value="${item.companyNameOfPurchaser}"></td>
		<td><input disabled="disabled" value="${item.tpIdeNumOfPurchaser}"></td>
		<td><input disabled="disabled" value="${item.bankTypeOfPurchaser}"></td>
		<td><input disabled="disabled" value="${item.bankNumberOfPurchaser}"></td>
		<td><input disabled="disabled" value="${item.companyNameOfSale}"></td>
		<td><input disabled="disabled" value="${item.tpIdeNumOfSale}"></td>
		<td><input disabled="disabled" value="${item.bankTypeOfSale}"></td>
		<td><input disabled="disabled" value="${item.addresseeName}"></td>
		<td><input disabled="disabled" value="${item.address}"></td>
		<td><input disabled="disabled" value="${item.phoneNumber}"></td>
	</tr>
	</c:forEach>
</table>
</c:if>
</form>
</body>
</html>