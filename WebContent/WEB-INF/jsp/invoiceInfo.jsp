<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>预览</title>
</head>
<body>
<form action="invoice.do?method=send" method="post" >
	<input type="hidden" name="id" value="${invoice.invId}" >
	<table>
		<tr>
			<td>发票内容:</td>
			<td>${invoice.invoiceContent}</td>
		</tr>
		<tr>
			<td>发票类型:</td>
			<td>${invoice.invoiceType}</td>
		</tr>
		<tr>
			<td>开票期次:</td>
			<td>第${invoice.invoiceIndex}期</td>
		</tr>
		<tr>
			<td>计划开票日期:</td>
			<td>${invoice.expectMakeInvoceDate}</td>
			<td>预计付款日期:</td>
			<td>${invoice.expectPaymentDate}</td>
		</tr>
		<tr>
			<td>出票公司名称:</td>
			<td>${invoice.makeInvoiceCompanyName}</td>
		</tr>
		<tr>
			<td>发票抬头:</td>
			<td>${invoice.companyNameOfPurchaser}</td>
			<td>买方纳税人编号:</td>
			<td>${invoice.tpIdeNumOfPurchaser}</td>
		</tr>
		<tr>
			<td>购买方开户行/账户:</td>
			<td>${invoice.bankTypeOfPurchaser}/${invoice.bankNumberOfPurchaser}</td>
		</tr>
		<tr>
			<td>税率:</td>
			<td>${invoice.rate}</td>
			<td>税额:</td>
			<td>${invoice.amtOfTax}</td>
		</tr>
		<tr>
			<td>不含税金额:</td>
			<td>${invoice.amtOfNoTax}</td>
			<td>总金额:</td>
			<td>${invoice.amt}</td>
		</tr>
		<tr>
			<td>卖方纳税人编号:</td>
			<td>${invoice.tpIdeNumOfSale}</td>
			<td>卖方开户行/账户:</td>
			<td>${invoice.bankTypeOfSale}/${invoice.bankNumberOfSale}</td>
		</tr>
		<tr>
			<td>收票人姓名:</td>
			<td>${invoice.addresseeName}</td>
			<td>收票人电话:</td>
			<td>${invoice.phoneNumber}</td>
			<td>发票寄送地址:</td>
			<td>${invoice.address}</td>
		</tr>
	</table>
	<input type="submit" value="发送"  >	
	<input type="button" value="关闭" onclick="window.close()" >	
</form>
</body>
</html>