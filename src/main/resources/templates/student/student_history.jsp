<%--
  Created by IntelliJ IDEA.
  User: AlphaGo
  Date: 2019/11/23
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>历史记录</title>
    <style type="text/css">
        table.gridtable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }
        table.gridtable th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }
    </style>

</head>
<body>
<jsp:include page="student_head.jsp"></jsp:include>

<div class="container">
    <h3><strong>已上传文件列表</strong></h3>
    <table name="his_table" class="gridtable">
        <tr>
            <th class="col-md-4">文件名</th>
            <th class="col-md-4">开始时间</th>
            <th class="col-md-4">课程教师</th>
        </tr>
        ${table}
    </table>
</div>
</body>
</html>
