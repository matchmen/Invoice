<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册类型</title>
</head>
<body>
<form action="company.do?method=registerType" method="post">
	个人注册<input type="checkbox" name="isComapny" value="false" >
	公司注册<input type="checkbox" name="isComapny" value="true">
	<input type="submit" value="下一步">
	<input type="button" value="取消" onclick="window.open('employee.do?method=loginPage','_self')">
</form>
</body>
</html>