<%@ page import="uz.pdp.bookstoreproject.service.impl.UserServiceImpl" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("users", UserServiceImpl.getInstance().getAll());
%>
<html>
<head>
    <title>Users</title>
</head>
<body>
<a href="/admin/home"><button>Back</button></a>
<table>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Username</th>
        <th>Email</th>
        <th>Password</th>
        <th>isAdmin</th>
    </tr>

    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.id}</td>
            <td><a href="/profile?id=${u.id}">${u.name}</a></td>
            <td>${u.surname}</td>
            <td>${u.username}</td>
            <td>${u.email}</td>
            <td>${u.password}</td>
            <td>${u.admin}</td>
            <td>
                <form action="/admin/users?id=${u.id}" method="post">
                    <label for="makeAdmin">Make Admin</label>
                    <input type="checkbox" id="makeAdmin" name="isAdmin" ${u.admin ? 'checked' : ''}>
                    <button type="submit">Submit</button>
                </form>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
<style>
    th,td{
        border: black 2px solid;
    }
</style>
</html>
