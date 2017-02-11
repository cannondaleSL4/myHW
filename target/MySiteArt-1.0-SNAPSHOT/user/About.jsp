<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="utf-8" />
        <title>Ford it's a future for right now!</title>
        <link href="../css/Main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    <%
        //String username = request.getRemoteUser();
    %>
        <section>
            <form>
                <p><%= session.getAttribute("userName") %></p>
                <p><a href = "../login.jsp">Exit</a></p>
            </form>
        </section>
        <div class="header">

            <div class = "picOne">
                <img src="../img/logo.png">
            </div>
            
            <ul id="menu">
                <li><a href="ModelNoEmployee.jsp">Models</a></li>
                <li><a href="Contact.jsp">Contact Us</a></li>
                <li><a href="About.jsp">About</a></li>
            </ul>
        </div>

            <div id = "main">
                <div class ="text">
                    <p>we are the best company in the world!</p>
                </div>
            </div>
    </body>
</html>