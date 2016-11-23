<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册类型</title>
<style type="text/css">
body {
	background:#D2E0F2;
}

</style>
</head>
<body>
<form action="user.do?method=registerType" method="post">
<div style="margin-left: 40%;margin-top: 20%;">
	个人注册<input type="radio" name="isCompany" checked="checked" value="false" >
	公司注册<input type="radio" name="isCompany" value="true">
	<input type="submit" value="下一步">
</div>
</form>
</body>
</html>