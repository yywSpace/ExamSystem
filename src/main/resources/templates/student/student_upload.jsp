<%--
  Created by IntelliJ IDEA.
  User: AlphaGo
  Date: 2019/11/23
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>答案上传</title>
    <script type="text/javascript" src="../static/js/jquery-1.8.3.min.js" charset="UTF-8"></script>
    <style>

        .file {
            position: relative;
            display: inline-block;
            background: #D0EEFF;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            color: #1E88C7;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
        }
        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }
        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }
        .filename{
            margin-left: 65px;
            margin-bottom: 10px;
            font-size: 18px;
            background: #ffffff;
            border: 0;
        }
    </style>
    <script type="text/javascript">
        function z() {
            setInterval("showInfo()", 1000);
        };

        function showInfo() {
            $.ajax({
                type : "post",
                url : "../../ShowInformation",
                success : function(text) {
                    $("#show").html(text);
                }
            });
        }

        function check_upload() {
            var file = document.getElementById("file").value;
            if (file == null || file == "")
                return false;
            else
                return true;
        }
    </script>
    <script>
        function handle() {
            var file = $('#file'),
                aim = $('#aim');
            file.on('change', function( e ){
                //e.currentTarget.files 是一个数组，如果支持多个文件，则需要遍历
                var name = e.currentTarget.files[0].name;
                aim.val( name );
            });
        }
    </script>
    <script type="text/javascript">
        $(function () {
            setInterval(function () {
                $("#autore").load(location.href + " #autore");//注意后面DIV的ID前面的空格，很重要！没有空格的话，会出双眼皮！（也可以使用类名）
            }, 8000);//8秒自动刷新
        })

    </script>
</head>
<body>
<jsp:include page="student_head.jsp"></jsp:include>
<div style="margin-top: 50px">
    <span style="margin-left: 60px;font-size: 18px">
        请按照考试要求将答案文件打包，再进行上传。同名文件多次上传将会覆盖。</span><br><br>
    <form  name="file" role="form"
           action="stu_answer_upload" enctype="multipart/form-data"
           method="post">

        <a class="file" style="margin-left: 60px;" onclick="handle()" >
            选择要上传的文件<input type="file" id="file" name="file" required >
        </a>
        <br>
        <input type="text" id="aim" class="filename" disabled/>
        <br>
        <button type="submit" onclick="return check_upload()" class="file" style="margin-left: 60px;">文件上传</button>
    </form>
</div>


<div id="autore" style="margin:60px">
    <c:forEach items="${sessionScope.messages}" var="message"  >
        <text id="text" style="color: red">${message}</text>
        <br>
    </c:forEach>
</div>
</body>
</html>
