<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/invoice.css" rel="stylesheet" type="text/css" >
<script type="text/javascript">
function errSetting(){
	var errorElementId = document.getElementById("errorMsg").value;
	document.getElementById(errorElementId).setAttribute('class','error');
}
</script>
<style>
    .error{
        border-color: red;
    }
</style>
<title>注 册</title>
</head>
<body onload="errSetting()">
	<font style="color:red">${errorMsg}</font>
	<input  type="hidden"  id="errorMsg" value="${errorElementId}">
	<form action="company.do?method=register" method="post">
		<font>请输入公司信息</font>
		<table>
			<tr>
				<td>公司名称</td><td><input type="text" name="companyName" id="companyName" value="${companybean.company.companyName}"><font style="color:red">*</font></td>		
			</tr>		
			<tr>
				<td>公司代码</td><td><input type="text" name="companyCode" id="companyCode" value="${companybean.company.companyCode}"><font style="color:red">*</font></td>		
			</tr>		
			<tr>
				<td>公司邮箱</td><td><input type="text" name="companyEmail" id="companyEmail" value="${companybean.company.companyEmail}"><font style="color:red">*</font></td>		
			</tr>
			<tr>
				<td>公司电话</td><td><input type="text" name="tellphoneNumber" id="tellphoneNumber" value="${companybean.company.tellphoneNumber}"><font style="color:red">*</font></td>		
			</tr>
		</table>
		<font>请输入管理员信息</font>
		<table>
			<tr>
				<td>员工号</td><td><input type="text" name="employeeId" value="${companybean.employee.employeeId}"><font style="color:red">*</font></td>
			</tr>
			<tr>
				<td>员工姓名</td><td><input type="text" name="employeeName" value="${companybean.employee.employeeName}"><font style="color:red">*</font></td>
			</tr>
			<tr>
				<td>手机号码</td><td><input type="text" name="phoneNumber" value="${companybean.employee.phoneNumber}"><font style="color:red">*</font></td>
			</tr>
			<tr>
				<td>邮箱</td><td><input type="text" name="email" id="email" value="${companybean.employee.email}"><font style="color:red">*</font></td>
			</tr>
			<tr>
				<td>登录密码</td><td><input type="password" name="password" id="password"><font style="color:red">*</font></td>		
			</tr>
			<tr>
				<td>再次输入密码</td><td><input type="password" id="secondPassword" ></td>		
			</tr>
			<tr>
				<td>部门</td><td><input type="text" name="department" id="department" value="${companybean.employee.department}"></td>
			</tr>
			<tr>
				<td>职位</td><td><input type="text" name="position" id="position" value="${companybean.employee.position}"></td>
			</tr>
		</table>
		<input type="submit" value="注册公司" ><input type="button" value="取消" onclick="window.open('employee.do?method=loginPage','_self')">
	</form>
</body>
</html>
