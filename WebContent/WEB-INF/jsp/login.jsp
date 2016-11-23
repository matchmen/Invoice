<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登 陆</title>
<style type="text/css">
body {
	background:#D2E0F2;
}

</style>
</head>
<body>
	<form action="user.do?method=login" method="post">
	<div style="text-align: center;margin-top: 150px;">
	<div style="margin-left: 40%;">
	<table>
		<tr>
		<td>邮箱或手机号码</td><td><input type="text" name="username" id="username" ></td>
		</tr>
		<tr>
		<td>密码</td><td><input type="password" name="password" id="password" ><font style="color:red">${errorMsg}</font></td>
		</tr>
	</table>
	</div>
	<input type="submit" value="登陆" >
	<input type="button" onclick="window.open('user.do?method=registerTypePage','_self')" value="注册" >
	</div>
	</form>
</body>
</html>