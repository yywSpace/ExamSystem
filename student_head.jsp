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
    <title>student_head</title>
    <style>
        ul {
            position: fixed;
            top: 0;
            left:0;
            width: 100%;
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li {
            float: left;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-family: "KaiTi";
        }

        li a:hover {
            background-color: #111;
        }
    </style>
</head>
<body>
<ul>
    <li><a href="stu_main">主页</a></li>
    <li><a href="stu_paper_download">试卷下载</a></li>
    <li><a href="stu_upload">答案上传</a></li>
    <li><a href="stu_file_history">查看历史考试结果</a></li>
</ul>
</body>
</html>
