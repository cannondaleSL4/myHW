<%--
  Created by IntelliJ IDEA.
  User: dima
  Date: 19.01.17
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <title>Enter Ford dealership</title>
    <link rel="shortcut icon" href="img/plogo.png">
    <link href="../css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<section>
    <form action="LoginServlet" method=post>
        <input type="text"  name= "userName" placeholder="Username or email" >
        <input type="password"  name= "password" placeholder="Password" >
        </br>
        <a href="register.jsp">Sign Up</a>
        <input type="submit" class="Button" value="Enter">
    </form>
</section>
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