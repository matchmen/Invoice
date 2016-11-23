<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>  
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
	<form action="user.do?method=setBaseInfo" method="post">
		<input  type="hidden" name="id" value="${baseInfo.id}">
		<input  type="hidden" name="userId" value="${baseInfo.userId}">
		<input  type="hidden" name="companyId" value="${baseInfo.companyId}">
		<table>
		<c:if test="${currUser.isCompany}">
			<tr>
				<td>初始密码</td><td><input type="password" name="initPassword" id="initPassword"></td>
			</tr>
			<tr>
				<td>再次输入密码</td><td><input type="password" id="secondPassword"  ></td>
			</tr>
		</c:if>	
			<tr>
				<td>财务开票提示时间</td><td><input type="text" id="cueTimeFinance" name="cueTimeFinance" value="${baseInfo.cueTimeFinance}" >天</td>
			</tr>
			<tr>
				<td>销售催款提示时间</td><td><input type="text" id="cueTimeSales" name="cueTimeSales" value="${baseInfo.cueTimeSales}" >天</td>
			</tr>
		</table>
		<input type="submit" value="保存" >
	</form>
</body>
</html>