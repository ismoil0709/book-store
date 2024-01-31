<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String message = (String) request.getAttribute("message"); %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<div>
    <form action="/auth/register" method="post">
        <label for="username">Username</label>
        <input type="text" id="username" name="username">
        <br>
        <br>
        <label for="password">Password</label>
        <input type="password" id="password" name="password">
        <br>
        <br>
        <label for="name">Name</label>
        <input type="text" id="name" name="name">
        <br>
        <br>
        <label for="surname">Surname</label>
        <input type="text" id="surname" name="surname">
        <br>
        <br>
        <label for="email">Email</label>
        <input type="email" id="email" name="email">
        <br>
        <br>
        <button type="submit">Sign up</button>
        <a href="/auth/login"><button type="button">Login</button></a>
        <br>
        <br>
        <%
            if (message != null){ %> <h4><%=message%></h4><%}%>
    </form>
</div>
</body>
</html>
