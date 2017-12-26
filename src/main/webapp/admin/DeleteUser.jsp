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
        List<User> listOfUser = (List<User>) Factory.getInstance().getUserDAO().getAll();
    %>

    <form action = "/admin/deleteUser" method="post">
        <%
            for(User user : listOfUser){
                if(user.getName().equals(session.getAttribute("userName"))){ %>
                    <input type="checkbox" name=<%= user.getName()%> value=<%= user.getName()%>>
                }
        <%
            }
            %>
    <input type="submit" value="Submit!">
    </form>
        <%
        %>
    </form>
</div>

</body>
</html>
