<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码</title>
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
</head>
<body onload="errSetting()">
<font style="color:red">${errorMsg}</font>
<input  type="hidden"  id="errorMsg" value="${errorElementId}">
<form action="user.do?method=updatePassword" method="post">
	<table>
		<tr>
			<td>旧密码</td><td><input type="password" name="oldPassword" id="oldPassword"></td>
		</tr>
		<tr>
			<td>新密码</td><td><input type="password" name="password" id="password"></td>
		</tr>
		<tr>
			<td>再次输入密码</td><td><input type="password" id="secondPassword"></td>
		</tr>
	</table>
	<input type="submit" value="修改" >
	<input type="button" value="取消" onclick="window.open('user.do?method=mainPage','_self')">
</form>
</body>
</html>