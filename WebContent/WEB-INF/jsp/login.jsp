<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登 陆</title>
</head>
<body>
	<form action="employee.do?method=login" method="post">
	<table>
		<tr><td><font style="color:red">${errorMsg}</font></td></tr>
		<tr>
			<td>邮箱或手机号码</td><td><input type="text" name="username" id="companyCode" ></td>
		</tr>
		<tr>
			<td>密码</td><td><input type="password" name="password" id="password" ></td>
		</tr>
	</table>
	<input type="submit" value="登陆" ><input type="button" onclick="window.open('company.do?method=registerPage','_self')" value="注册公司" >
	</form>
</body>
</html>