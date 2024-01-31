<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
   String message = (String) request.getSession().getAttribute("message");
%>
<html>
<head>
    <title>Upload</title>
</head>
<body>
<div>
    <form action="/admin/upload" method="post" enctype="multipart/form-data">
        <label for="title">Title</label>
        <input type="text" name="title" id="title">
        <br>
        <br>
        <label for="description">Description</label>
        <input type="text" name="description" id="description">
        <br>
        <br>
        <label for="price">Price</label>
        <input type="number" step="any" name="price" id="price">
        <br>
        <br>
        <label for="author">Author</label>
        <input type="text" name="author" id="author">
        <br>
        <br>

        <label for="cover">Cover</label>
        <input type="file" name="cover" id="cover">
        <br>
        <br>
        <label for="file">File (Only pdf)</label>
        <input type="file" name="file" id="file">
        <br>
        <br>
        <button type="submit">Upload</button>
    </form>
    <%if (message!=null){%>
    <h1><%=message%></h1>
    <%}%>
</div>
</body>
</html>
