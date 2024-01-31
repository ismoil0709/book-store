<%@ page import="uz.pdp.bookstoreproject.service.impl.BookServiceImpl" %>
<%@ page import="uz.pdp.bookstoreproject.entity.Book" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%@ page import="java.util.*" %>
<%
  @SuppressWarnings("unchecked")
  List<Book> books = (List<Book>) session.getAttribute("books");
  if (books == null) {
    books = BookServiceImpl.getInstance().getAll();
    books.sort(Comparator.comparing(Book::getRating).reversed());
    session.setAttribute("books", books);
  }
  List<byte[]> covers = new ArrayList<>();
  for (Book b : books) {
    covers.add(Files.readAllBytes(Paths.get(b.getCover_path())));
  }
  session.setAttribute("covers", covers);
  Long id = null;
  Optional<Cookie> optionalCookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("user")).findFirst();
  if (optionalCookie.isPresent()) id = Long.parseLong(optionalCookie.get().getValue());
%>
<!DOCTYPE html>
<html>
<head>
  <title>Book store</title>
</head>
<body>
<nav>
  <div>
    <form action="/search" method="post">
      <label for="search"></label>
      <input id="search" name="search" type="search">
      <br>
      <br>
      <label for="by_title">Find by title</label>
      <input type="radio" name="search_by" value="title" id="by_title">
      <br>
      <br>
      <label for="by_price">Find by Price</label>
      <input type="radio" name="search_by" value="price" id="by_price">
      <br>
      <br>
      <label for="by_author">Find by Author</label>
      <input type="radio" name="search_by" value="author" id="by_author">
      <br>
      <br>
      <label for="by_rating">Find by Rating</label>
      <input type="radio" name="search_by" value="rating" id="by_rating">
      <br>
      <br>
      <button type="submit">Search</button>
      <br>
      <br>
      <c:if test="${message != null}"><h1><%=session.getAttribute("message")%></h1></c:if>
      <%session.setAttribute("message",null);%>
    </form>
    <a href="/">
      <button>Home</button>
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

  <div>
    <c:forEach var="b" items="${books}" varStatus="status">
      <c:set var="cover" value="${covers[status.index]}" />
      <a href="/books?id=${b.id}">
        <img class="image" src="data:image/jpeg;base64,${Base64.encodeBase64String(cover)}" alt="photo">
      </a>
      <%session.setAttribute("books",null);%>
    </c:forEach>
  </div>
</div>
</body>
<style>
  .image{
    width: 500px;
    height: 700px;
  }
</style>
</html>