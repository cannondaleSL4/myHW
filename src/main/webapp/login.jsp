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
    <!--<form action="LoginServlet" method=post>
        <input type="text"  name= "userName" placeholder="Username or email" >
        <input type="password"  name= "password" placeholder="Password" >
        </br>
        <a href="register.jsp">Sign Up</a>
        <input type="submit" class="Button" value="Enter">
    </form>-->
    <!--<form action="j_security_check" method=post>-->
    <form method="POST" action="<%=request.getContextPath()%>/j_security_check">
    <input type="text"  name= "j_username" placeholder="Username or email" >
        <input type="password"  name= "j_password" placeholder="Password" >
        </br>
        <a href="register.jsp">Sign Up</a>
        <input type="submit" class="Button" value="Enter">
    </form>
</section>
</body>
<%
    if(session.getAttribute("errorMessage")!=null)
    {
        out.println("<script>\n" +
                "  window.onload = function() {\n" +
                "    alert( '" +session.getAttribute("errorMessage") + "' );\n" +
                "  };\n" +
                "</script>");
    }
    if(session.getAttribute("messageSuccess")!=null)
    {
        out.println("<script>\n" +
                "  window.onload = function() {\n" +
                "    alert( '" +session.getAttribute("\"messageSuccess\"") + "' );\n" +
                "  };\n" +
                "</script>");
    }
%>
</html>