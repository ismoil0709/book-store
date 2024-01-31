<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    long id = Long.parseLong(Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("user")).findFirst().get().getValue());
%>
<!DOCTYPE html>
<html>
<head>
    <title>Book store</title>
</head>
<body>
<nav>
    <div>
        <a href="/">
            <button>Home</button>
        </a>
        <a href="/admin/users">
            <button>Users</button>
        </a>
        <a href="/admin/books">
            <button>All books</button>
        </a>
        <a href="/admin/authors">
            <button>Authors</button>
        </a>
        <a href="/admin/upload">
            <button>Upload</button>
        </a>
        <a href="/profile?id=<%=id%>">
            <button>Profile</button>
        </a>
        <a href="/log-out">
            <button>Log Out</button>
        </a>
    </div>
</nav>
<div>
    <h1>Welcome</h1>
</div>
</body>
<style>
</style>
</html>