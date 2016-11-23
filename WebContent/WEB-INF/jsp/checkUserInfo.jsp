<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息</title>
</head>
<body>
<table>
		<tr>
			<td>员工姓名</td><td>:${currUser.username}</td>
		</tr>
		<tr>
			<td>手机号码</td><td>:${currUser.phoneNumber}</td>
		</tr>
		<tr>
			<td>邮箱</td><td>:${currUser.email}</td>
		</tr>
		<c:if test="${currUser.isCompany}">
			<tr>
				<td>员工号</td><td>:${currUser.employeeId}</td>
			</tr>
			
			<tr>
				<td>部门</td><td>:${currUser.department}</td>
			</tr>
			<tr>
				<td>职位</td><td>:${currUser.position}</td>
			</tr>
		</c:if>
</table>
</body>
</html>