<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
        <title>登 陆</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <style type="text/css">
        </style>
    </head>

    <body>
        <!--
        <form action="user.do?method=login" method="post">
            <div style="text-align: center;margin-top: 150px;">
                <div style="margin-left: 40%;">
                    <table>
                        <tr>
                            <td>邮箱或手机号码</td>
                            <td>
                                <input type="text" name="username" id="username"> </td>
                        </tr>
                        <tr>
                            <td>密码</td>
                            <td>
                                <input type="password" name="password" id="password"><font style="color:red">${errorMsg}</font></td>
                        </tr>
                    </table>
                </div>
                <input type="submit" value="登陆">
                <input type="button" onclick="window.open('user.do?method=registerTypePage','_self')" value="注册"> </div>
        </form> -->
        <div class="container" style="margin-top:180px;">
            <form action="user.do?method=login" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="username" id="username" placeholder="邮箱或手机号码"> </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码"> </div>
                <button type="submit" class="btn btn-primary btn-block">登陆</button>
                <button type="button" class="btn btn-info btn-block" onclick="window.open('user.do?method=registerTypePage','_self')">注册</button>
            </form>
        </div>
    </body>

    </html>