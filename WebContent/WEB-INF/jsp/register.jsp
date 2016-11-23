<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="WebContent/css/invoice.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="WebContent/js/invoice.js"></script>
<title>注 册</title>
<style type="text/css">
body {
	background:#D2E0F2;
}

</style>
</head>
<body onload="errSetting()">
	<font style="color:red">${errorMsg}</font>
	<input  type="hidden"  id="errorMsg" value="${errorElementId}">
	<form action="user.do?method=register" method="post">
	<input  type="hidden"  name="isCompany" value="${userBean.user.isCompany}">
	<c:if test="${userBean.user.isCompany}">
		<font>请输入公司信息</font>
		<table>
			<tr>
				<td>公司名称</td><td><input type="text" name="companyName" id="companyName" value="${userBean.company.companyName}"><font style="color:red">*</font></td>		
			</tr>		
			<tr>
				<td>公司代码</td><td><input type="text" name="companyCode" id="companyCode" value="${userBean.company.companyCode}"><font style="color:red">*</font></td>		
			</tr>		
			<tr>
				<td>公司邮箱</td><td><input type="text" name="companyEmail" id="companyEmail" value="${userBean.company.companyEmail}"></td>		
			</tr>
			<tr>
				<td>公司电话</td><td><input type="text" name="tellphoneNumber" id="tellphoneNumber" value="${userBean.company.tellphoneNumber}"></td>		
			</tr>
		</table>
		</c:if>
		<font>请输入个人信息</font>
		<table>
			<tr>
				<td>姓名</td><td><input type="text" name="username" id="username" value="${userBean.user.username}"><font style="color:red">*</font></td>
			</tr>
			<tr>
				<td>手机号码</td><td><input type="text" name="phoneNumber" id="phoneNumber" value="${userBean.user.phoneNumber}"><font style="color:red">*</font></td>
			</tr>
			<tr>
				<td>邮箱</td><td><input type="text" name="email" id="email" value="${userBean.user.email}"><font style="color:red">*</font></td>
			</tr>
			<tr>
				<td>登录密码</td><td><input type="password" name="password" id="password"><font style="color:red">*</font></td>		
			</tr>
			<tr>
				<td>再次输入密码</td><td><input type="password" id="secondPassword" ></td>		
			</tr>
		<c:if test="${userBean.user.isCompany}">	
			<tr>
				<td>员工编号</td><td><input type="text" name="employeeId" value="${companybean.user.employeeId}"></td>	
			</tr>
			<tr>
				<td>部门</td><td><input type="text" name="department" id="department" value="${companybean.user.department}"></td>
			</tr>
			<tr>
				<td>职位</td><td><input type="text" name="position" id="position" value="${companybean.user.position}"></td>
			</tr>
		</c:if>		
		</table>
		<input type="submit" value="注册" >
	</form>
</body>
</html>
