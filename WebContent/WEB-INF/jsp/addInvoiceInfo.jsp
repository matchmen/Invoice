<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加发票</title>
<link href="WebContent/css/invoice.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="WebContent/js/invoice.js"></script>
<script>
function uploadfile(){
	var formElement = document.getElementById('formId');
 	formElement.setAttribute('enctype','multipart/form-data');
	formElement.setAttribute('action','invoice.do?method=uploadIvoice'); 
	formElement.submit();
}
</script>
</head>
<body onload="errSetting()">
<font style="color:red">${errorMsg}</font>
<input  type="hidden"  id="errorMsg" value="${errorElementId}">
<form id="formId" action="invoice.do?method=addInvoice" method="post">
<input type="file"  id="uploadedfile" name="file" onchange="uploadfile()" accept=".xlsx" ><br>
<a href="invoice.do?method=downloadExcel" >下载合同模板</a>
<c:if test="${!empty invoiceList}">
<table>
	<tr>
		<td>合同编号</td>
		<td>开票批次</td>
		<td>发票编号</td>
		<td>备注类型</td>
		<td>发票状态</td>
		<td>出票公司</td>
		<td>发票内容</td>
		<td>发票类型</td>
		<td>计划开票日期</td>
		<td>实际开票日期</td>
		<td>预计到账日期</td>
		<td>实际到账日期</td>
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
	<c:forEach items="${invoiceList}" var="invoice">
	<tr>		
		<%-- <td><input type="checkbox" name="invoiceList[${invoice.invId}].isContract" id="isContract" value="${invoice.isContract}"></td> --%>
		<td><input type="text" name="invoiceList[${invoice.invId}].contractId" id="contractId" value="${invoice.contractId}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].invoiceIndex" id="invoiceIndex" value="${invoice.invoiceIndex}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].invoiceId" id="invoiceId" value="${invoice.invoiceId}"></td>
    	<td><input type="text" name="invoiceList[${invoice.invId}].remarkType" id="remarkType" value="${invoice.remarkType}"></td>
		<td><select name="invoiceList[${invoice.invId}].invoiceStatus" id="invoiceStatus" >
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
		<td><input type="text" name="invoiceList[${invoice.invId}].makeInvoiceCompanyName" id="makeInvoiceCompanyName" value="${invoice.makeInvoiceCompanyName}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].invoiceContent" id="invoiceContent" value="${invoice.invoiceContent}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].invoiceType" id="invoiceType" value="${invoice.invoiceType}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].expectMakeInvoceDate" id="expectMakeInvoceDate" value="${invoice.expectMakeInvoceDate}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].actualMakeInvoiceDate" id="actualMakeInvoiceDate" value="${invoice.actualMakeInvoiceDate}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].expectPaymentDate" id="expectPaymentDate" value="${invoice.expectPaymentDate}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].actualPaymentDate" id="actualPaymentDate" value="${invoice.actualPaymentDate}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].amt" id="amt" value="${invoice.amt}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].amtOfTax" id="amtOfTax" value="${invoice.amtOfTax}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].rate" id="rate" value="${invoice.rate}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].amtOfNoTax" id="amtOfNoTax" value="${invoice.amtOfNoTax}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].receiveAmt" id="receiveAmt" value="${invoice.receiveAmt}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].notReceiveAmt" id="notReceiveAmt" value="${invoice.notReceiveAmt}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].companyNameOfPurchaser" id="companyNameOfPurchaser" value="${invoice.companyNameOfPurchaser}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].tpIdeNumOfPurchaser" id="tpIdeNumOfPurchaser" value="${invoice.tpIdeNumOfPurchaser}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].bankTypeOfPurchaser" id="bankTypeOfPurchaser" value="${invoice.bankTypeOfPurchaser}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].bankNumberOfPurchaser" id="bankNumberOfPurchaser" value="${invoice.bankNumberOfPurchaser}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].companyNameOfSale" id="companyNameOfSale" value="${invoice.companyNameOfSale}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].tpIdeNumOfSale" id="tpIdeNumOfSale" value="${invoice.tpIdeNumOfSale}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].bankTypeOfSale" id="bankTypeOfSale" value="${invoice.bankTypeOfSale}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].bankNumberOfSale" id="bankNumberOfSale" value="${invoice.bankNumberOfSale}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].address" id="address" value="${invoice.address}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].phoneNumber" id="phoneNumber" value="${invoice.phoneNumber}"></td>
		<td><input type="text" name="invoiceList[${invoice.invId}].expressDate" id="expressDate" value="${invoice.expressDate}"></td>
	</tr>
	</c:forEach>
</table>
<input type="submit" value="添加" >
</c:if>
</form>
</body>
</html>