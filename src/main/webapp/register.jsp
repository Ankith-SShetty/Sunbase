
<html>
<head>
    <style><%@include file="./styles/styles.css"%></style>
    <%@ include file="allCss.jsp"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored = "false"%>

</head>


<body>
<h3 class="text-center">Register</h3>
<div>
    <c:if test = "${not empty RegisterErrMsg}">
        <p class = "text-center text-success">${RegisterErrMsg}</p>
        <c:remove var="RegisterErrMsg"/>
    </c:if>
</div>
<div class="d-flex justify-content-center">

    <form method="POST" action="RegisterServlet" class="d-block w-25 text-center">
        <!-- Email input -->
        <div data-mdb-input-init class="form-outline mb-4">
            <label class="form-label" >Name</label>
            <input required type="text" name="name"  class="form-control" />

        </div>
        <div data-mdb-input-init class="form-outline mb-4">
            <label class="form-label" >Email</label>
            <input required type="email" name="email" class="form-control" />

        </div>
        <!-- Password input -->
        <div data-mdb-input-init class="form-outline mb-4">
            <label class="form-label" >Password</label>
            <input required type="password" name="password" class="form-control" />
        </div>

        <!-- Admin or User -->
        <div data-mdb-input-init class="form-outline mb-4">
            <label for="role">Role</label>
            <select required id="role" name="role" class="form-select form-select-sm" aria-label="User role selection">
                <option value="user">User</option>
                <option value="admin">Admin</option>
            </select>
        </div>

        <button class="btn btn-md btn-outline-primary" type="submit">Register</button>
        <a href="login.jsp" class="btn btn-md btn-outline-secondary">Login</a>
    </form>




</div>