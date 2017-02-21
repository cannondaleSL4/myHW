<%@ page import="com.authentification.userEntity.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="utf-8" />
        <title>Ford it's a future for right now!</title>
        <link href="../css/Main.css" rel="stylesheet" type="text/css">
        <%
            if(session.getAttribute("userName")== null){
                response.sendRedirect("/logout");
            }else if(!session.getAttribute("userRole").toString().equalsIgnoreCase("ADMINISTRATOR")) {
                response.sendRedirect("/logout");
            }
        %>
    </head>
    <body>
    <%
        String username = request.getRemoteUser();
    %>
        <section>
            <form>
                <p><%= session.getAttribute("userName") %></p>
                <p><a href = "/logout">Exit</a></p>
            </form>
        </section>
        <div class="header">
            <div class = "picOne">
                <img src="../img/logo.png">
            </div>
            <ul id="menu">
                <li><a href="/user/Constructor/model">Model for user</a></li>
                <li><a href="/staff/staffmodel">Model for dealership</a></li>
                <li><a href="../overall/Contact.jsp">Contact Us</a></li>
                <li><a href="../overal/About.jsp">About</a></li>
            </ul>
        </div>

    <div id = "main">
        <%
            List<User> userList= (List<User>) session.getAttribute("userList");
            if (userList!=null){
                for(User user:userList){
        %>
        <div class = "photo">
            <p><a href="editUser?userName=<%=user.getName()%>"><img src = "../img/user.png"> </a></p>
            <p class = "caption"><%=user.getName()%></p>
            </div>
            <%}
            }%>
            <div class = "photo">
                <p>
                    <a href="addUser?userName=y"> <img src = "../img/pl.png"></a>
                </p>
            </div>
        </div>
    </div>
    </body>
</html>