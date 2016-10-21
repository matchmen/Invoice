<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加员工</title>
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
<body>
<font style="color:red">${errorMsg}</font>
<input  type="hidden"  id="errorMsg" value="${errorElementId}">
<form action="employee.do?method=addEmployee" method="post">
<table>
		<tr>
			<td>员工号</td><td><input type="text" name="employeeId" value="${employee.employeeId}"><font style="color:red">*</font></td>
		</tr>
		<tr>
			<td>员工姓名</td><td><input type="text" name="employeeName" value="${employee.employeeName}"><font style="color:red">*</font></td>
		</tr>
		<tr>
			<td>手机号码</td><td><input type="text" name="phoneNumber" value="${employee.phoneNumber}"><font style="color:red">*</font></td>
		</tr>
		<tr>
			<td>邮箱</td><td><input type="text" name="email" id="email" value="${employee.email}"><font style="color:red">*</font></td>
		</tr>
		<tr>
			<td>部门</td><td><input type="text" name="department" id="department" value="${employee.department}"></td>
		</tr>
		<tr>
			<td>职位</td><td><input type="text" name="position" id="position" value="${employee.position}"></td>
		</tr>
		<tr>
			<td>设为管理员</td><td>  Yes<input type="radio" name="isAdmin" value="true" >No<input type="radio" name="isAdmin" checked="checked" value="false"></td>
		</tr>
</table>
<input type="submit" value="添加" ><input type="button" value="取消" onclick="window.open('employee.do?method=mainPage','_self')">
</form>
</body>
</html>