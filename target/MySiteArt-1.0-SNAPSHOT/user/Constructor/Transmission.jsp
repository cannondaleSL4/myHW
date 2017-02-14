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
        <title><%= session.getAttribute("userName") %></title>
        <link rel="shortcut icon" href="img/plogo.png">
        <link rel="stylesheet" href="../../css/Constructor.css" type="text/css">
    </head>
    <body>
        <section>
            <form>
                <p><%= session.getAttribute("userName") %></p>
                <p><a href = "../../login.jsp">Exit</a></p>
            </form>
        </section>
        <div class="header">

            <div class = "picOne">
                <img src="../../img/logo.png">
            </div>
            
            <ul id="menu">
                <li><a href="../ModelNoEmployee.jsp">Models</a></li>
                <li><a href="../Contact.jsp">Contact Us</a></li>
                <li><a href="../About.jsp">About</a></li>
            </ul>
        </div>

            <div id = "main">
                <div class ="text">
                    <p>we are the best company in the world!</p>
                </div>
            </div>
    </body>
</html>