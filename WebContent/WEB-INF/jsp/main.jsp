<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主 页</title>
<!-- <script type="text/javascript">
function show(){
	var showElementId = document.getElementById("isAdmin").value;
	if(!true==showElementId){
		document.getElementById("authority_admin").setAttribute("class", "authority_admin")
	}
}
</script>
<style type="text/css">
.authority_admin{
	display: none;
}
</style> -->
</head>
<body>
<font>${currUser.username}</font>
<input type="button" value="退出" onclick="window.open('user.do?method=logout','_self')">
<br>
<c:if test="${currUser.isAdmin}">
<table>
	<tr>
		<td>
			<div id="authority_admin">
				<input type="button" value="设置基本信息" onclick="window.open('user.do?method=setBaseInfoPage','_self')"><br>
				<c:if test="${currUser.isCompany}">
					<input type="button" value="添加员工" onclick="window.open('user.do?method=addUserPage','_self')"><br>
					<input type="button" value="注销员工信息" onclick="window.open('user.do?method=removeUserPage','_self')"><br>
				</c:if>
			</div>
		</td>
	</tr>
</table>
</c:if>
<br>
<table>	
	<tr>
		<td>
			<div>
				<input type="button" value="查看个人信息" onclick="window.open('user.do?method=checkUserInfo','_self')"><br>
				<input type="button" value="修改个人信息" onclick="window.open('user.do?method=updateUserInfoPage','_self')"><br>
				<input type="button" value="修改密码" onclick="window.open('user.do?method=updatePasswordPage','_self')"><br>
			</div>
		</td>
	</tr>
</table>
<br>
<table>	
	<tr>
		<td>
			<div>
				<input type="button" value="导入合同信息" onclick="window.open('contract.do?method=importContractFilePage','_self')"><br>
				<input type="button" value="浏览合同信息" onclick="window.open('contract.do?method=checkContractInfoPage','_self')"><br>
				<input type="button" value="修改合同信息" onclick="window.open('contract.do?method=updateContractInfoPage','_self')"><br>
				<input type="button" value="添加合同管理者" onclick="window.open('contract.do?method=addContractInfoManagePage','_self')"><br>
			</div>
		</td>
	</tr>
</table>
<br>
<table>	
	<tr>
		<td>
			<div>
				<input type="button" value="添加发票" onclick="window.open('invoice.do?method=addInvoicePage','_self')"><br>
				<input type="button" value="查看发票信息" onclick="window.open('invoice.do?method=checkInvoiceInfoPage','_self')"><br>
				<input type="button" value="修改发票信息" onclick="window.open('invoice.do?method=updateInvoiceInfoPage','_self')"><br>
			</div>
		</td>
	</tr>
</table>
</body>
</html>