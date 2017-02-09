
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
                <li><a href="ForEmployee.jsp">Constructor</a></li>
                <li><a href="Models.jsp">Models</a></li>
                <li><a href="Color.jsp">Colors</a></li>
                <li><a href="Transmission.jsp">Transmission</a></li>
                <li><a href="Engine.jsp">Engine</a></li>
                <li><a href="KindOfBody.jsp">Kind of body</a></li>
            </ul>
        </div>

            <div id = "main">
                
            </div>
    </body>
</html>