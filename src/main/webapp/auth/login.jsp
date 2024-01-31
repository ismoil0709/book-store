<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String message = (String) request.getAttribute("message");
%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>
    <form action="/auth/login" method="post">
        <label for="username">Username</label>
        <input type="text" id="username" name="username">
        <br>
        <br>
        <label for="password">Password</label>
        <input type="password" id="password" name="password">
        <br>
        <br>
        <button type="submit">Login</button>
        <a href="/auth/register"><button type="button">Sign-up</button></a>
        <br>
        <br>
        <%
            if (message != null){ %> <h4><%=message%></h4><%}%>
    </form>
</div>
</body>
</html>
