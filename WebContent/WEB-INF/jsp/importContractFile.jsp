<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>导入合同信息</title>
<link href="WebContent/css/invoice.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="WebContent/js/invoice.js"></script>
<script>
function uploadfile(){
	var formElement = document.getElementById('formId');
 	formElement.setAttribute('enctype','multipart/form-data');
	formElement.setAttribute('action','contract.do?method=uploadExcel'); 
	formElement.submit();
}
</script>
</head>
<body onload="errSetting()">
<font style="color:red">${errorMsg}</font>
<input  type="hidden"  id="errorMsg" value="${errorElementId}">
<form id="formId" method="post"  action="contract.do?method=importContractFile">
<c:if test="${empty contractBean}">
	<input type="file"  id="uploadedfile" name="file" onchange="uploadfile()" accept=".xlsx" ><br>
	<a href="contract.do?method=downloadExcel" >下载合同模板</a>
	<br>
</c:if>
<c:if test="${!empty contractBean}">
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
	
</table>
<c:if test="${!empty contractBean.invoiceList}">
<font>开票信息</font>
<table>
	<tr>
		<td>开票批次</td>
		<td>发票状态</td>
		<td>备注类型</td>
		<td>计划开票日期</td>
		<td>预计到账日期</td>
		<td>出票公司</td>
		<td>发票内容</td>
		<td>发票类型</td>
		<td>发票金额</td>
		<td>税额</td>
		<td>税率</td>
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
		<td><input type="text" name="invoiceList[${item.invoiceIndex-1}].invoiceIndex" id="invoiceIndex" value="${item.invoiceIndex}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].invoiceStatus" id="invoiceStatus" value="${item.invoiceStatus}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].remarkType" id="remarkType" value="${item.remarkType}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].expectMakeInvoceDate" id="expectMakeInvoceDate" value="${item.expectMakeInvoceDate}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].expectPaymentDate" id="expectPaymentDate" value="${item.expectPaymentDate}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].makeInvoiceCompanyName" id="makeInvoiceCompanyName" value="${item.makeInvoiceCompanyName}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].invoiceContent" id="invoiceContent" value="${item.invoiceContent}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].invoiceType" id="invoiceType" value="${item.invoiceType}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].amt" id="amt" value="${item.amt}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].amtOfTax" id="amtOfTax" value="${item.amtOfTax}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].rate" id="rate" value="${item.rate}" ></td>
	    <td><input type="text" name="invoiceList[${item.invoiceIndex-1}].amtOfNoTax" id="amtOfNoTax" value="${item.amtOfNoTax}" ></td>
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
	</tr>
	</c:forEach>
</table>
</c:if>
<input type="submit" value="导入系统" >
</c:if>
</form>
</body>
</html>