<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>main</title>
</head>
<body>
<%@include file="header.jsp"%>
<%--<%--%>
<%--    if(session.getAttribute("uid")==null)--%>
<%--        response.sendRedirect(("/login"));--%>
<%--%>--%>


        <h1>환영합니다.</h1>
<img src="/img/img.png">

<h1> <%=(String) request.getAttribute("uname")%> 환영합니다.</h1>
<h1>${uname} 환영합니다.</h1>

<%@include file="todos.jsp"%>
<%@include file="todolist.jsp"%>

<form action="/main" method="get">
    <input type="text" name = "keyword" placeholder="검색어를 입력해주세요.">
    <input type="submit" value="찾기">
</form>
</body>
</html>