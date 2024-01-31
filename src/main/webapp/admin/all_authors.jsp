<%@ page import="uz.pdp.bookstoreproject.repo.impl.AuthorRepoImpl" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("authors", AuthorRepoImpl.getInstance().getAll());
%>
<html>
<head>
    <title>Authors</title>
</head>
<body>
<a href="/admin/home"><button>Back</button></a>
<table>
    <tr>
        <th>id</th>
        <th>Name</th>
    </tr>

    <c:forEach var="u" items="${authors}">
    <tr>
        <td>${u.id}</td>
        <td>${u.full_name}</td>
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
