<%--
  Created by IntelliJ IDEA.
  User: Ankith Shetty
  Date: 17-07-2024
  Time: 01:24 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="allCss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false"%>
<html>
<head>
</head>
<body>
<h3 class="text-center">Add Blogs</h3>
<div>
    <c:if test = "${not empty AddErrMsg}">
        <p class = "text-center text-success">${AddErrMsg}</p>
        <c:remove var="AddErrMsg"/>
    </c:if>
</div>
<div class="d-flex justify-content-center">
<form action="AddBlogServlet" method="POST" enctype="multipart/form-data">

    <div class="form-group">
        <label for="title">Title</label>
        <input type="text" id="title" name="blogTitle" required class="form-control">
    </div>
    <br>
    <div class="form-group">
        <label for="content">Content</label>
        <textarea class="form-control" id="content" name="blogContent" rows="3"></textarea>
    </div>
    <br>
    <br>
    <div class="form-group">
    <label for="file">Upload Image/Video:</label>
    <input type="file" id="file" name="blogFile" accept="image/*, video/*" class="form-control">
    </div>
    <br>
    <button class="btn btn-primary justify-content-center" type="submit">Add Blog</button>
</form>
</div>
</body>
</html>
