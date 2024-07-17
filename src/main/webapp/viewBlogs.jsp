<%@ page import="com.dao.BlogDAO" %>
<%@ page import="com.entity.Blog" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ankith Shetty
  Date: 16-07-2024
  Time: 12:59 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false"%>
<%@ include file="allCss.jsp"%>
<html>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="navbar-brand" >Blogs for you</div>
        <div class="collapse navbar-collapse">
            <div class="collapse navbar-collapse">

            </div>
        </div>
    </div>
</nav>
<%
    BlogDAO blogDAO = new BlogDAO();
    List<Blog> blogs = blogDAO.getAllBlogs();
%>
<div class="d-flex justify-content-evenly">
    <c:forEach var="blog" items="<%= blogs %>">
        <div class="card mx-5" style="width: 18rem;">
            <img class="card-img-top" src="data:image/jpg;base64, ${blog.file.base64String}" alt="Image">
            <div class="card-body">
                <h5 class="card-title">${blog.title}</h5>
                <p class="card-text">${blog.content}</p>
            </div>
            <c:if test = "${not empty errMsg }">
                <p class = "text-center text-success">${errMsg }</p>
                <c:remove var="errMsg"/>
            </c:if>
        </div>
    </c:forEach>
</div>

<%--    <div>--%>
<%--    <c:forEach var="blog" items="<%= blogs %>">--%>
<%--        <div class="card" style="width: 18rem;">--%>
<%--            <img class="card-img-top" src="data:image/jpg;base64, ${blog.file.base64String}" alt="Image">--%>
<%--            <div class="card-body">--%>
<%--                <h5 class="card-title">${blog.title}</h5>--%>
<%--                <p class="card-text">${blog.content}</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    </div>--%>
</body>
</html>
