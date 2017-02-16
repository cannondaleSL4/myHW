<%--
  Created by IntelliJ IDEA.
  User: dima
  Date: 21.01.17
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <title>Sign Up</title>
    <link href="./css/registration.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="login">
    <div class="heading">
        <h2>Sign in</h2>
        <form action="RegisterServlet" method="post">

            <div class="input-group input-group-lg">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" class="form-control" name = "userName" size = "30" placeholder="Username or email">
            </div>

            <div class="input-group input-group-lg">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" name = "password" size = "8" placeholder="Password">
            </div>

            <button type="submit" class="float">Login</button>
            <button type="button" class="float" ><a href="login.jsp" style="color:black; text-decoration:none;">Back</a></button>
        </form>
    </div>
</div>
</body>
<%
    if(request.getAttribute("errorMessage")!=null)
    {
        out.println("<script>\n" +
                "  window.onload = function() {\n" +
                "    alert( '" +request.getAttribute("errorMessage") + "' );\n" +
                "  };\n" +
                "</script>");
    }
    if(request.getAttribute("messageSuccess")!=null)
    {
        out.println("<script>\n" +
                "  window.onload = function() {\n" +
                "    alert( '" +request.getAttribute("\"messageSuccess\"") + "' );\n" +
                "  };\n" +
                "</script>");
    }
%>
</html>