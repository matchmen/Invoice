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
<c:if test="${empty contract}">
	<input type="file"  id="uploadedfile" name="file" onchange="uploadfile()" accept=".xls" ><br>
	<a href="contract.do?method=downloadExcel" >下载合同模板</a>
	<br>
</c:if>
<c:if test="${!empty contract}">
<table>	
	<tr>
		<td>合同编号</td><td><input type="text" name="contractId" value="${contract.contractId}" id="contractId"><font color="red">*</font></td>
	</tr>
	<tr>
		<td>合同类型</td><td><input type="text" name="contractType" value="${contract.contractType}" id="contractType"><font color="red">*</font></td>
	</tr>
	<tr>
		<td>产品和服务</td><td><input type="text" name="itemName" value="${contract.itemName}" id="itemName"></td>
	</tr>
	<tr>
		<td>甲方（客户）单位全称</td><td><input type="text" name="companyNameOfFirst" value="${contract.companyNameOfFirst}" id="companyNameOfFirst"><font color="red">*</font></td>
		<td>甲方联系人</td><td><input type="text" name="contactNameOfFirst" value="${contract.contactNameOfFirst}" id="contactNameOfFirst"><font color="red">*</font></td>
	</tr>
	<tr>
		<td>乙方（自己）单位全称</td><td><input type="text" name="companyNameOfSecond" value="${contract.companyNameOfSecond}" id="companyNameOfSecond"><font color="red">*</font></td>
		<td>销售代表</td><td><input type="text" name="contactNameOfSecond" value="${contract.sales}" id="contactNameOfSecond"><font color="red">*</font></td>
	</tr>
	<tr>
		<td>丙方单位全称</td><td><input type="text" name="companyNameOfThird" value="${contract.companyNameOfThird}" id="companyNameOfThird"></td>
		<td>丙方联系人</td><td><input type="text" name="contactNameOfThird" value="${contract.contactNameOfThird}" id="contactNameOfThird"></td>
	</tr>
	<tr>
		<td>合同签订日期</td><td><input type="text" name="contractSignDate" value="${contract.contractSignDate}" id="contractSignDate"><font color="red">*</font></td>
	</tr>
	<tr>
		<td>合同生效日期</td><td><input type="text" name="contractStartDate" value="${contract.contractStartDate}" id="contractStartDate"></td>
		<td>合同终止日期</td><td><input type="text" name="contractEndDate" value="${contract.contractEndDate}" id="contractEndDate"></td>
	</tr>
	<tr>
		<td>合同金额</td><td><input type="text" name="amt" value="${contract.amt}" id="amt"></td>
		<td>付款期数</td><td><input type="text" name="paymentTimes" value="${contract.paymentTimes}" id="paymentTimes"></td>
	</tr>
	<tr>
		<td>已开发票</td><td><input type="text" name="comleteInvoiceNumber" value="${contract.comleteInvoiceNumber}" id="comleteInvoiceNumber"></td>
		<td>已开票金额</td><td><input type="text" name="completeInvoiceAmt" value="${contract.completeInvoiceAmt}" id="completeInvoiceAmt"></td>
	</tr>
	<tr>
		<td>未开票金额</td><td><input type="text" name="remainInvoiceAmt" value="${contract.remainInvoiceAmt}" id="remainInvoiceAmt"></td>
	</tr>
	<tr>
		<td>备注</td><td><input type="text" name="remark" value="${contract.remark}" id="remark"></td>
	</tr>
</table>
<input type="submit" value="导入系统" >
</c:if>
<input type="button" value="取消" onclick="window.open('employee.do?method=mainPage','_self')"><br>
</form>
</body>
</html>