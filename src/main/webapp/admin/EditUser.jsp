<%--
  Created by IntelliJ IDEA.
  User: dima
  Date: 26.12.17
  Time: 8:13
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
        String name = request.getParameter("nameForChange");
        session.setAttribute("nameForChange",name);
    %>
    <form action="/admin/editUser" method=post>
        <br>
        The user name is <%=name%> please input new name
        <br>
        <input type="text"  name= "newName" placeholder="Input new name" >
        </br>
        <input type="submit" class="Button" value="Enter">
    </form>
</div>
</body>
</html>
