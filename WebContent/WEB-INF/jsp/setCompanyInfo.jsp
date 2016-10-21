<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公司信息设置</title>
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
	<form action="company.do?method=setCompanyInfo" method="post">
		<table>
			<tr>
				<td>初始密码</td><td><input type="password" name="initPassword" id="initPassword"></td>
			</tr>
			<tr>
				<td>再次输入密码</td><td><input type="password" id="secondPassword"  ></td>
			</tr>
			<tr>
				<td>财务开票提示时间</td><td><input type="text" id="cueTimeFinance" name="cueTimeFinance" value="${info.cueTimeFinance}" >天</td>
			</tr>
			<tr>
				<td>销售催款提示时间</td><td><input type="text" id="cueTimeSales" name="cueTimeSales" value="${info.cueTimeSales}" >天</td>
			</tr>
		</table>
		<input type="submit" value="保存" ><input type="button" value="取消" onclick="window.open('employee.do?method=mainPage','_self')">
	</form>
</body>
</html>