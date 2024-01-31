<%@ page import="uz.pdp.bookstoreproject.service.impl.BookServiceImpl" %>
<%@ page import="uz.pdp.bookstoreproject.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Book> books = BookServiceImpl.getInstance().getAll();
    request.setAttribute("books",books);
    List<byte[]> covers = new ArrayList<>();
    for (Book b : books) {
        covers.add(Files.readAllBytes(Paths.get(b.getCover_path())));
    }
    request.setAttribute("covers",covers);
%>
<html>
<head>
    <title>Books</title>
</head>
<body>
<a href="/admin/home"><button>Back</button></a>
<div>
    <c:forEach var="b" items="${books}" varStatus="status">
        <c:set var="cover" value="${covers[status.index]}" />
        <a href="/books?id=${b.id}">
            <img class="image" src="data:image/jpeg;base64,${Base64.encodeBase64String(cover)}" alt="photo">
        </a>
    </c:forEach>
</div>
</body>
<style>
    .image{
        width: 500px;
        height: 700px;
    }
</style>
</html>
