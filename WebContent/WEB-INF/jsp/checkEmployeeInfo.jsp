<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息</title>
</head>
<body>
<table>
		<tr>
			<td>员工号</td><td>:${currEmployee.employeeId}</td>
		</tr>
		<tr>
			<td>员工姓名</td><td>:${currEmployee.employeeName}</td>
		</tr>
		<tr>
			<td>手机号码</td><td>:${currEmployee.phoneNumber}</td>
		</tr>
		<tr>
			<td>邮箱</td><td>:${currEmployee.email}</td>
		</tr>
		<tr>
			<td>部门</td><td>:${currEmployee.department}</td>
		</tr>
		<tr>
			<td>职位</td><td>:${currEmployee.position}</td>
		</tr>
</table>
<input type="button" value="返回" onclick="window.open('employee.do?method=mainPage','_self')">
</body>
</html>