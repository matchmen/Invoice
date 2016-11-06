<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加合同信息管理者</title>
<link href="WebContent/css/invoice.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="WebContent/js/invoice.js"></script>
</head>
<body onload="errSetting()">
<font style="color:red">${errorMsg}</font>
<input  type="hidden"  id="errorMsg" value="${errorElementId}">
<form action="contract.do?method=addContractInfoManage" method="post">
	<table>
		<tr>
			<td>合同编号</td>
			<td><input type="text" name="contractId" id="contractId" value="${admin.contractId}"></td>
		</tr>
		<tr>
			<td>添加人邮箱或手机号码</td>
			<td><input type="text" name="str" id="str" value="${admin.str}"></td>
		</tr>
		<!-- <tr>
			<td>同时绑定该合同下所有发票的管理权限</td>
			<td>  Yes<input type="radio" name="isAll" value="true" >
				  No<input type="radio" name="isAdmin" checked="checked" value="false"></td>
		</tr> -->
	</table>
	<input type="submit" value="添加" >
	<input type="button" value="取消" onclick="window.open('employee.do?method=mainPage','_self')"><br>
</form>
</body>
</html>