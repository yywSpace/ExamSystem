<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>试卷下载</title>
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
<div>
    <h3>
        <strong>试卷下载</strong>
    </h3>

    <!-- 显示a标签 -->
    <a class="btn btn-primary btn-lg" role="button" href="stu_paper_download"
       style="margin-left: 60px;%>"><span
            class="glyphicon glyphicon-eye-open"></span>下载查看</a>

</div>

<div id="autore" style="margin:60px">
<c:forEach items="${sessionScope.messages}" var="message"  >
    <text id="text" style="color: red">${message}</text>
    <br>
</c:forEach>
</div>


</body>
</html>
