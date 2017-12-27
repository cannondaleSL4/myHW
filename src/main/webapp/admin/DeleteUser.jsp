<%@ page import="com.authentification.userEntity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.api.Factory" %><%--
  Created by IntelliJ IDEA.
  User: dima
  Date: 26.12.17
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>Edit user</title>
    <link href="../css/Check.css" rel="stylesheet" type="text/css">
    <link href="../css/Main.css" rel="stylesheet" type="text/css">
    <%
        String username = (String) session.getAttribute("userName");
        if(username == null){
            response.sendRedirect("/logout");
        }else if(!session.getAttribute("userRole").toString().equalsIgnoreCase("ADMINISTRATOR")) {
            response.sendRedirect("/logout");
        }
    %>
</head>
<body>
<section>
    <form>
        <p><%= username %></p>
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
    <h2>for delete user please mark these users </h2>
    <%
        List<User> listOfUser = (List<User>) Factory.getInstance().getUserDAO().getAll();
    %>

    <form action = "/admin/deleteUser" method="post">
        <%
            for(User user : listOfUser){
                if(!user.getName().equals(username)){ %>
                    <div class = "checkout">
                    <input id="<%= user.getName()%>" type="checkbox" name="delete" value=<%= user.getName()%>>
                    <label for="<%= user.getName()%>"><%= user.getName()%></label>
                    </div>
                <%}
            }
            %>
    <input type="submit" value="Submit!">
    </form>
</div>

</body>
</html>
