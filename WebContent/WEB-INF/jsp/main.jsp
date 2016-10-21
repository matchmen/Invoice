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
<font>${currEmployee.employeeName}</font>
<input type="button" value="退出" onclick="window.open('employee.do?method=loginPage','_self')">
<br>
<c:if test="${currEmployee.isAdmin}">
<table>
	<tr>
		<td>
			<div id="authority_admin">
				<input type="button" value="设置公司信息" onclick="window.open('company.do?method=checkCompanyInfo','_self')"><br>
				<input type="button" value="添加员工" onclick="window.open('employee.do?method=addEmployeePage','_self')"><br>
				<input type="button" value="移除员工" onclick="window.open('employee.do?method=removeEmployeePage','_self')"><br>
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
				<input type="button" value="查看个人信息" onclick="window.open('employee.do?method=checkEmployeeInfo','_self')"><br>
				<input type="button" value="修改个人信息" onclick="window.open('employee.do?method=updateEmployeeInfoPage','_self')"><br>
				<input type="button" value="修改密码" onclick="window.open('employee.do?method=updatePasswordPage','_self')"><br>
			</div>
		</td>
	</tr>
</table>
</body>
</html>