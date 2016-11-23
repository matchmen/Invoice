<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改合同信息</title>
<link href="WebContent/css/invoice.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="WebContent/js/invoice.js"></script>
<script>
function find(){
	var formElement = document.getElementById('formId');
	formElement.setAttribute('action','contract.do?method=findUpdate'); 
	formElement.submit(); 
}
</script>
</head>
<body onload="errSetting()">
<font style="color:red">${errorMsg}</font>
<input  type="hidden"  id="errorMsg" value="${errorElementId}">
<form id="formId" method="post" action="contract.do?method=updateContractInfo">
	合同名称<input type="text" name="contractName" value="${contractName}">
	客户名称<input type="text" name="firstName" value="${firstName}">
	签订合同日期<input type="text" name="signDateStart" value="${signDateStart}">
	-<input type="text" name="signDateEnd" value="${signDateEnd}">
	<input type="button" value="搜索" onclick="find()">
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
			<td><a href="contract.do?method=findConAndInvList2&contractIds=${contract.contractId}">${contract.contractId}</a></td>
			<td><input readonly="readonly" value="${contract.contractType}"></td>
			<td><input readonly="readonly" value="${contract.itemName}"></td>
			<td><input readonly="readonly" value="${contract.companyNameOfFirst}"></td>
			<td><input readonly="readonly" value="${contract.contactNameOfFirst}"></td>
			<td><input readonly="readonly" value="${contract.companyNameOfSecond}"></td>
			<td><input readonly="readonly" value="${contract.sales}"></td>
			<td><input readonly="readonly" value="${contract.companyNameOfThird}"></td>
			<td><input readonly="readonly" value="${contract.contactNameOfThird}"></td>
			<td><input readonly="readonly" value="${contract.contractSignDate}"></td>
			<td><input readonly="readonly" value="${contract.contractStartDate}"></td>
			<td><input readonly="readonly" value="${contract.contractEndDate}"></td>
			<td><input readonly="readonly" value="${contract.amt}"></td>
			<td><input readonly="readonly" value="${contract.paymentTimes}"></td>
			<td><input readonly="readonly" value="${contract.comleteInvoiceNumber}"></td>
			<td><input readonly="readonly" value="${contract.completeInvoiceAmt}"></td>
			<td><input readonly="readonly" value="${contract.remainInvoiceAmt}"></td>
			<td><input readonly="readonly" value="${contract.remark}"></td>
		</tr>
	</c:forEach>
</table>
</c:if>		
<c:if test="${!empty contractBean.contract}">
<input  type="hidden"  id="id" name="id" value="${contractBean.contract.id}">
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
	</tr>
	<tr>
		<td><input type="text" name="contractId" value="${contractBean.contract.contractId}" id="contractId"></td>
   		<td><input type="text" name="contractType" value="${contractBean.contract.contractType}" id="contractType"></td>
		<td><input type="text" name="itemName" value="${contractBean.contract.itemName}" id="itemName"></td>
		<td><input type="text" name="companyNameOfFirst" value="${contractBean.contract.companyNameOfFirst}" id="companyNameOfFirst"></td>
		<td><input type="text" name="contactNameOfFirst" value="${contractBean.contract.contactNameOfFirst}" id="contactNameOfFirst"></td>
		<td><input type="text" name="companyNameOfSecond" value="${contractBean.contract.companyNameOfSecond}" id="companyNameOfSecond"></td>
   		<td><input type="text" name="contactNameOfSecond" value="${contractBean.contract.sales}" id="contactNameOfSecond"></td>
  		<td><input type="text" name="companyNameOfThird" value="${contractBean.contract.companyNameOfThird}" id="companyNameOfThird"></td>
		<td><input type="text" name="contactNameOfThird" value="${contractBean.contract.contactNameOfThird}" id="contactNameOfThird"></td>
  		<td><input type="text" name="contractSignDate" value="${contractBean.contract.contractSignDate}" id="contractSignDate"></td>
 		<td><input type="text" name="contractStartDate" value="${contractBean.contract.contractStartDate}" id="contractStartDate"></td>
 		<td><input type="text" name="contractEndDate" value="${contractBean.contract.contractEndDate}" id="contractEndDate"></td>
   		<td><input type="text" name="amt" value="${contractBean.contract.amt}" id="amt"></td>
   		<td><input type="text" name="paymentTimes" value="${contractBean.contract.paymentTimes}" id="paymentTimes"></td>
   		<td><input type="text" name="comleteInvoiceNumber" value="${contractBean.contract.comleteInvoiceNumber}" id="comleteInvoiceNumber"></td>
		<td><input type="text" name="completeInvoiceAmt" value="${contractBean.contract.completeInvoiceAmt}" id="completeInvoiceAmt"></td>
		<td><input type="text" name="remainInvoiceAmt" value="${contractBean.contract.remainInvoiceAmt}" id="remainInvoiceAmt"></td>
    	<td><input type="text" name="remark" value="${contractBean.contract.remark}" id="remark"></td>
	</tr>
</table>
</c:if>
<c:if test="${!empty contractBean.invoiceList}">
<br>
<font>开票信息</font>
<table>
	<tr>
		<td>开票批次</td>
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
	<c:forEach items="${contractBean.invoiceList}" var="item">
	<tr>
		<td><input type="text" name="invoiceList[${item.invoiceIndex-1}].invoiceIndex" id="invoiceIndex" value="${item.invoiceIndex}" ></td>
		<td><input type="text" name="invoiceList[${item.invoiceIndex-1}].invoiceId" id="invoiceId" value="${item.invoiceId}" ></td>
	    <td><select name="invoiceList[${item.invoiceIndex-1}].invoiceStatus" id="invoiceStatus" >
	    	<option value="已开具">已开具</option>
			<option value="已检视">已检视</option>
			<option value="已寄出">已寄出</option>
			<option value="已收到">已收到</option>
			<option value="已入账">已入账</option>
			<option value="已付款">已付款</option>
			<option value="已确认开错">已确认开错</option>
			<option value="已寄出(错)">已寄出(错)</option>
			<option value="已收到(错)">已收到(错)</option>
			<option value="已作废">已作废</option>
			<option value="已红冲">已红冲</option>
			<option value="已重开">已重开</option>
	    </select></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].remarkType" id="remarkType" value="${item.remarkType}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].expectMakeInvoceDate" id="expectMakeInvoceDate" value="${item.expectMakeInvoceDate}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].actualMakeInvoiceDate" id="actualMakeInvoiceDate" value="${item.actualMakeInvoiceDate}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].expectPaymentDate" id="expectPaymentDate" value="${item.expectPaymentDate}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].actualPaymentDate" id=actualPaymentDate value="${item.actualPaymentDate}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].makeInvoiceCompanyName" id="makeInvoiceCompanyName" value="${item.makeInvoiceCompanyName}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].invoiceContent" id="invoiceContent" value="${item.invoiceContent}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].invoiceType" id="invoiceType" value="${item.invoiceType}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].amt" id="amt" value="${item.amt}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].amtOfTax" id="amtOfTax" value="${item.amtOfTax}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].rate" id="rate" value="${item.rate}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].amtOfNoTax" id="amtOfNoTax" value="${item.amtOfNoTax}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].receiveAmt" id="receiveAmt" value="${item.receiveAmt}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].notReceiveAmt" id="notReceiveAmt" value="${item.notReceiveAmt}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].companyNameOfPurchaser" id="companyNameOfPurchaser" value="${item.companyNameOfPurchaser}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].tpIdeNumOfPurchaser" id="tpIdeNumOfPurchaser" value="${item.tpIdeNumOfPurchaser}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].bankTypeOfPurchaser" id="bankTypeOfPurchaser" value="${item.bankTypeOfPurchaser}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].bankNumberOfPurchaser" id="bankNumberOfPurchaser" value="${item.bankNumberOfPurchaser}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].companyNameOfSale" id="companyNameOfSale" value="${item.companyNameOfSale}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].tpIdeNumOfSale" id="tpIdeNumOfSale" value="${item.tpIdeNumOfSale}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].bankTypeOfSale" id="bankTypeOfSale" value="${item.bankTypeOfSale}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].addresseeName" id="addresseeName" value="${item.addresseeName}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].address" id="address" value="${item.address}" ></td>
	   <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].phoneNumber" id="phoneNumber" value="${item.phoneNumber}" ></td>
	   <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].expressDate" id="expressDate" value="${item.expressDate}" ></td>
	</tr>
	</c:forEach>
</table>
<c:forEach items="${contractBean.invoiceList}" var="item">
	<tr><td><input type="hidden" name="invoiceList[${item.invoiceIndex-1}].invId" id="invId" value="${item.invId}" ></td></tr>
</c:forEach>
</c:if>
<c:if test="${!empty contractBean.contract}">
<input type="submit" value="保存修改" >
</c:if>
</form>
</body>
</html>