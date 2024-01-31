<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="uz.pdp.bookstoreproject.entity.Book" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Paths" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Optional" %>
<%@ page import="uz.pdp.bookstoreproject.service.impl.UserServiceImpl" %>
<%
    Book book = (Book) request.getAttribute("book");
    request.setAttribute("book",book);
    byte[] cover = Files.readAllBytes(Paths.get(book.getCover_path()));
    request.setAttribute("cover", cover);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <img class="img" src="data:image/jpeg;base64,${Base64.encodeBase64String(cover)}" alt="photo">
    <h1 class="title"><%=book.getTitle()%>
    </h1>
        <h4>Description : <%=book.getDescription()%>
        </h4>
        <h4>Price : <%=book.getPrice()%>
        </h4>
    <h4>Authors : <c:forEach var="a" items="${book.authors}">
        <c:out value="${a.full_name}"/> ,
    </c:forEach>
        </h4>
        <h4>Rating :  <%=book.getRating()%>
        </h4>
    <a href="/download?id=${book.id}"><button>Download</button></a>
    <%
        String message = (String) session.getAttribute("message");
        Optional<Cookie> user = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("user")).findFirst();
        if (user.isPresent()) {
            long id = Long.parseLong(user.get().getValue());
            if (UserServiceImpl.getInstance().getById(id).isAdmin()) {
    %><%
    if (message != null){
%><h4><%=message%></h4><%}%>
    <a href="/admin/delete?id=${book.id}"><button>Delete</button></a>
    <a href="/admin/update?id=${book.id}"><button>Update</button></a>
    <%
            }
        }
    %>
</div>
</body>
<style>
    h4 ,button{
        margin-left: 750px;
    }

    .title {
        margin-left: 800px;
        margin-top: -800px;
    }

    .img {
        width: 700px;
        height: 800px;
    }
</style>
</html>
