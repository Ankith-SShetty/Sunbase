<%@ page import="com.dao.BlogDAO" %>
<%@ page import="com.entity.Blog" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ankith Shetty
  Date: 16-07-2024
  Time: 03:26 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false"%>
<%@ include file="allCss.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int id = (Integer) request.getAttribute("id");
    System.out.println(">>>111" + id);
    BlogDAO blogDAO = new BlogDAO();
    List<Blog> blogs = blogDAO.selectAllBlogsByCreatorId(id);
    blogs.forEach(s -> {
        System.out.println("SSS" + s);
    });
%>

<c:forEach var="blog" items="<%= blogs %>">
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">${blog.title}</h5>
            <p class="card-text">${blog.content}</p>
            <a href="updatePost.jsp" class="btn btn-primary">Update</a>
            <a href="#" class="btn btn-danger">Delete</a>
        </div>
    </div>
</c:forEach>
</body>
</html>
