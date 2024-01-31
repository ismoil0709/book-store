<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = (String) request.getSession().getAttribute("message");
%>
<html>
<head>
    <title>Update</title>
</head>
<body>
<div>
    <c:set var="action" value="/admin/update"/>
    <c:set var="method" value="post"/>
    <form action="${action}" method="${method}">
        <label for="title">Title</label>
        <input type="text" name="title" id="title">
        <button type="submit">Update</button>
        <br>
        <br>
    </form>
    <form action="${action}" method="${method}">
        <label for="description">Description</label>
        <input type="text" name="description" id="description">
        <button type="submit">Update</button>
        <br>
        <br>
    </form>
    <form action="${action}" method="${method}">
        <label for="price">Price</label>
        <input type="number" step="any" name="price" id="price">
        <button type="submit">Update</button>
        <br>
        <br>
    </form>
    <form action="${action}" method="${method}">
        <label for="author">Author</label>
        <input type="text" name="author" id="author">
        <button type="submit">Update</button>
        <br>
        <br>
    </form>
    <form action="${action}" method="${method}" enctype="multipart/form-data">
        <label for="cover">Cover</label>
        <input type="file" name="cover" id="cover">
        <button type="submit">Update</button>
        <br>
        <br>
    </form>
    <form action="${action}" method="${method}" enctype="multipart/form-data">
        <label for="file">File (Only pdf)</label>
        <input type="file" name="file" id="file">
        <button type="submit">Update</button>
        <br>
        <br>
    </form>
    <%if (message!=null){%>
    <h1><%=message%></h1>
    <%}%>
</div>
</body>
</html>
