
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AlphaGo
  Date: 2019/11/22
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>上机考试系统</title>
    <link rel="stylesheet" href="../static/css/index1.css">
    <script type="text/javascript">
        window.onload = function () {
            var features='<%=(Integer)session.getAttribute("features")%>';
            if(features!=1){
                document.form1.submit();
            }
        }
        function hide(){
            document.getElementById("modal-one").style.display="none";
        }
    </script>
    <link rel="stylesheet" href="../static/css/index1.css">
    <link rel="stylesheet" type="text/css" href="../static/css/student_modalCss.css">
    <link rel="stylesheet" type="text/css" href="../static/css/student_loginCSS.css">
</head>
<body>
<%
    String examname = (String) session.getAttribute("examname");
%>
<!-- 顶部条 -->
<div class="box">
    <div class="cen clear">
        <div class="lefta" style="font-size:20px;color:#eee;display:block;float:left">
            <li style="float:left">
                上级考试系统  <span class="shu">|</span>
            </li>
            <a href="student_login">学生登录</a>  <span class="shu">|</span>
            <a href="index">教师登录</a>  <span class="shu">|</span>
            <a href="admin_login">管理员登录</a>
        </div>
    </div>
</div>
<form name="form1" method="post" action="on_load_action">
    <div class="modal" id="modal-one" aria-hidden="true">
        <div  class="modal-dialog">
            <div class="modal-header" style="padding: 5px;">
                <h2>
                    <%
                        if (examname != null) out.print("考试" + examname + " 正在进行中...");
                        else out.print("当前没有进行中的考试，不能登陆");%>
                </h2>
                <a onclick="hide()" href="#" class="btn-close" aria-hidden="true">×</a>
            </div>
        </div>
    </div>
</form>
<div id="login">
    <h1>学生登陆</h1>
    <form action="stu_login" method="post">
        <input  type="text" required="required" placeholder="学号" name="stu_id"/>
        <input  type="text" required="required" placeholder="姓名" name="stu_name"/>
        <button class="but" type="submit">登录</button>
    </form>
</div>
</body>
</html>