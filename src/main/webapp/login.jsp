<html>
<head>
    <style><%@include file="./styles/styles.css"%></style>
    <%@ include file="allCss.jsp"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored = "false"%>

</head>


<body>
<h3 class="text-center">Login</h3>

<div>
    <c:if test = "${not empty loginErrMsg}">
        <p class = "text-center text-success">${loginErrMsg}</p>
        <c:remove var="loginErrMsg"/>
    </c:if>
</div>
<div class="d-flex justify-content-center">

    <form method="POST" action="LoginServlet" class="d-block w-25 text-center">
        <div data-mdb-input-init class="form-outline mb-4">
            <label class="form-label">Email</label>
            <input required type="email" name="email" class="form-control" />

        </div>
        <!-- Password input -->
        <div data-mdb-input-init class="form-outline mb-4">
            <label class="form-label" >Password</label>
            <input required type="password" name="password" class="form-control" />
        </div>


        <button class="btn btn-md btn-outline-primary" type="submit">Login</button>
        <a href="register.jsp" class="btn btn-md btn-outline-secondary">Register</a>
    </form>

</div>
