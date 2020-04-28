<%--
  Created by IntelliJ IDEA.
  User: AlphaGo
  Date: 2019/11/22
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的主页</title>
    <style type="text/css">
        p{
            font-size: 15px;
            margin-bottom: 20px;
            font-family: "KaiTi";
        }
        h4{
            font-family: "KaiTi";
        }
        .panel {
            margin-bottom: 20px;
            background-color: #fff;
            border: 1px solid transparent;
            border-radius: 4px;
            width: 800px;
            -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
        }
        .panel-success {
            border-color: #d6e9c6;
        }
        .panel-success > .panel-heading {
            color: #3c763d;
            background-color: #dff0d8;
            border-color: #d6e9c6;
        }

        .panel-body {
            padding: 5px;
        }
        .panel-heading {
            padding: 1px 15px;
            border-bottom: 1px solid transparent;
            border-top-left-radius: 5px;
            border-top-right-radius: 5px;
        }
    </style>
</head>
<body style="background-image:url(static/img/stu_about_image.jpg);background-size:cover;">
<jsp:include page="student_head.jsp"></jsp:include>
<div class="container" style="margin-left: 160px;margin-top: 140px">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h4>试卷下载</h4>
        </div>
        <div class="panel-body text-left">
            <p>1、可以下载教师已经发布的试卷</p>
            <p>2、一次只能下载一份试卷</p>
        </div>
    </div>
    <div class="panel panel-success" style="margin-top: -40px;margin-left: 20px">
        <div class="panel-heading">
            <h4>答案上传</h4>
        </div>
        <div class="panel-body text-left">
            <p>1、请在上传截止时间前上传你的答案</p>
        </div>
    </div>
    <div class="panel panel-success" style="margin-top: -40px;margin-left: 40px">
        <div class="panel-heading">
            <h4>查看历史考试结果</h4>
        </div>
        <div class="panel-body text-left">
            <p>1、你可以查看你的历史考试成绩、提交时间、试卷分析结果等信息</p>
            <p>2、试卷保存一定的时间，教师可能会清除试卷</p>
        </div>
    </div>
    <div class="panel panel-success" style="margin-top: -40px;margin-left: 60px">
        <div class="panel-heading">
            <h4>其他的面板</h4>
        </div>
        <div class="panel-body text-left">
            <p>1、此处添加其他的信息</p>
            <p>2、暂时没想好</p>
        </div>
    </div>
</div>
</body>
</html>
