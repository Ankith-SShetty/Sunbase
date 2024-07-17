<%@ page import="com.dao.BlogDAO" %>
<%@ page import="com.entity.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %><%--
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
<body>


<%
    Cookie[] cookies = request.getCookies();

    String currentUserEmail = "";

    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("email")) {
            currentUserEmail = cookie.getValue();
            break;
        }
    }

    BlogDAO blogDAO = new BlogDAO();
    int id = blogDAO.getIdByEmail(currentUserEmail);
    List<Blog> blogs = blogDAO.selectAllBlogsByCreatorId(id);

%>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="navbar-brand" >Admin Dashboard</div>
        <div class="collapse navbar-collapse">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="addBlog.jsp">Add Blog</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>


<div class="d-flex justify-content-evenly">
        <c:forEach var="blog" items="<%= blogs %>">
            <div class="card mx-5" style="width: 18rem;">
                <img class="card-img-top" src="data:image/jpg;base64, ${blog.file.base64String}" alt="Image">
                <div class="card-body">
                    <h5 class="card-title">${blog.title}</h5>
                    <p class="card-text">${blog.content}</p>
                </div>
                <div class="card-footer">
                    <a href="updatePost.jsp?id=${blog.id}" class="btn btn-primary">Update</a>
                    <a href="delete?id=${blog.id}" class="btn btn-danger">Delete</a>
                </div>
                <c:if test = "${not empty errMsg }">
                    <p class = "text-center text-success">${errMsg }</p>
                    <c:remove var="errMsg"/>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
