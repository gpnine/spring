<%@ page import="com.zcl.study.spring.model.Person" %><%--
  Created by IntelliJ IDEA.
  User: ChengLin Zhu
  Date: 19-1-9
  Time: 下午5:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
    <%
//        request.setAttribute("name","tom");
//        request.setAttribute("name","jerry");
//        request.removeAttribute("name");

        session.setAttribute("u", new Person());
        long lastAccessedTime = session.getLastAccessedTime();
        System.out.println(lastAccessedTime);
    %>
</head>
<body>
<h1>home</h1>
</body>
</html>
