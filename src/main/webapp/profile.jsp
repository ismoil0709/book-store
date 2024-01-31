<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String message = (String) request.getAttribute("message");%>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<div>
    <c:set var="u" value="${user}"/>
    <h1>Id : ${u.id}</h1>
    <h1>Name : ${u.name}</h1>
    <form action="/profile?id=${u.id}" method="post">
        <label>
            <input type="text" name="new_name">
        </label>
        <button type="submit">Update</button>
    </form>
    <h1>Surname : ${u.surname}</h1>
    <form action="/profile?id=${u.id}" method="post">
        <label>
            <input type="text" name="new_surname">
        </label>
        <button type="submit">Update</button>
    </form>
    <h1>Username : ${u.username}</h1>
    <form action="/profile?id=${u.id}" method="post">
        <label>
            <input type="text" name="new_username">
        </label>
        <button type="submit">Update</button>
    </form>
    <h1>Email : ${u.email}</h1>
    <form action="/profile?id=${u.id}" method="post">
        <label>
            <input type="email" name="new_email">
        </label>
        <button type="submit">Update</button>
    </form>
    <h1>Password : ${u.password}</h1>
    <form action="/profile?id=${u.id}" method="post">
        <label>
            <input type="password" name="new_password">
            <input type="password" name="confirm_password">
        </label>
        <button type="submit">Update</button>
    </form>
    <h1>Is admin : ${u.admin}</h1>
</div>
</body>
</html>

