<%--
  Created by IntelliJ IDEA.
  User: Ankith Shetty
  Date: 17-07-2024
  Time: 02:49 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="allCss.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3 class="text-center">Update</h3>
<div class="d-flex justify-content-center">
    <form action="updateBlog?id=<%= request.getParameter("id") %>" method="post" enctype="multipart/form-data">

        <div class="form-group">
            <label for="title">title</label>
            <input type="text" id="title" name= "title" required class="form-control">
        </div>
        <br>
        <div class="form-group">
            <label for="content">Content</label>
            <textarea class="form-control" id="content" name="content" rows="3"></textarea>
        </div>
        <br>
        <br>
        <div class="form-group">
            <label for="file">Upload Image/Video:</label>
            <input type="file" id="file" name="file" accept="image/*, video/*">
        </div>
        <br>
        <br>
        <button class="btn btn-primary justify-content-center" type="submit">Update Blog</button>
    </form>
</div>
</body>
</html>
