<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主 页</title>
<link href="css/main_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>

<style type="text/css">
.menu_1_1{
	list-style-type:none;
	list-style-image: url("images/tree_folder.gif");
	border: 1px solid #D2E0F2; 
	background-color: #D2E0F2;
}
.menu_1{
	background-image: url("images/nav_link.png");
}
</style>
<script type="text/javascript">
var arrMenu_1 = document.getElementsByClassName('menu_1');
for (var i = 0; i < arrMenu_1.length; i++) {
	arrMenu_1[i].onclick = function(){
	/* 	var sib=siblingElems(arrMenu_1[i]); */
		arrMenu_1[i].setAttribute('class','menu_2');
	}
}
</script>
</head>
<body >

<div style="overflow: hidden; height: 30px;
    background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
    line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
     <span style="padding-left:10px; font-size: 16px; "> 发票协作平台</span>
    <span style="float:right; padding-right:20px;" class="head"><font>${currUser.username}</font>
    <a href="user.do?method=logout" id="loginOut"><font color="white">退出</font></a></span>
</div>
<div class="contant" style="display: inline;">
<div id="left">
<div style="width: 100%;height: 20px;text-align: center;background-image:url('images/nav_link.png') "><span >导航菜单</span></div>
<ul class="menu">
<li class="menu_1" onclick="menu_fun('menu_2_1')"><span class="menu_1_span" >平台信息管理<img id="menu_2_img_1" style="margin-left: 5px" src="images/layout_button_down.gif"></span></li>
<li id="menu_2_1" class="menu_2">
<c:if test="${currUser.isAdmin}">
<ul>
	<li onclick="tab_change('user.do?method=setBaseInfoPage')">设置基本信息</li>
<c:if test="${currUser.isCompany}">
	<li onclick="tab_change('user.do?method=addUserPage')">添加员工</li>
	<li onclick="tab_change('user.do?method=removeUserPage')">注销员工信息</li>
</c:if>
</ul>
</c:if>
</li>
<li class="menu_1" onclick="menu_fun('menu_2_2')"><span class="menu_1_span" >个人信息管理</span><img id="menu_2_img_2" src="images/layout_button_down.gif"></li>	
<li id="menu_2_2" class="menu_2">
<ul>
	<li onclick="tab_change('user.do?method=checkUserInfo')">查看个人信息</li>
	<li onclick="tab_change('user.do?method=updateUserInfoPage')">修改个人信息</li>
	<li onclick="tab_change('user.do?method=updatePasswordPage')"> 修改个人密码</li>
</ul>
</li>
<li class="menu_1" onclick="menu_fun('menu_2_3')"><span class="menu_1_span" >合同信息管理</span><img id="menu_2_img_3" src="images/layout_button_down.gif"></li>
<li id="menu_2_3" class="menu_2">
<ul>
	<li onclick="tab_change('contract.do?method=importContractFilePage')">导入合同信息</li>
	<li onclick="tab_change('contract.do?method=checkContractInfoPage')">查看合同信息</li>
	<li onclick="tab_change('contract.do?method=updateContractInfoPage')">修改合同信息</li>
	<c:if test="${currUser.isCompany}">
	<li onclick="tab_change('contract.do?method=addContractInfoManagePage')">添加合同管理者</li>
	</c:if>
</ul>
</li>
<li class="menu_1" onclick="menu_fun('menu_2_4')"><span  class="menu_1_span" >发票信息管理</span><img id="menu_2_img_4" src="images/layout_button_down.gif"></li>
<li id="menu_2_4" class="menu_2">
<ul>
	<li onclick="tab_change('invoice.do?method=addInvoicePage')">添加发票</li>
	<li onclick="tab_change('invoice.do?method=checkInvoiceInfoPage')">查看发票/发送邮件</li>
	<li onclick="tab_change('invoice.do?method=updateInvoiceInfoPage')">修改发票信息</li>
</ul>
</li>
</ul>
</div>
<div id="right">
<iframe id="ifra" style="border: none;width: 100%;height: 90%;background-color: white;" scrolling="auto"  src=""></iframe>
</div>
</div>
</body>
</html>