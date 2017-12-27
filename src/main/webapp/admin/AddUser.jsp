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
        <li><a href="DeleteUser.jsp">Delete User</a></li>
    </ul>
</div>

<div id = "main">
    <form action="/admin/addUser" method=post>
        <input type="text"  name= "userName" placeholder="Username or email" >
        <input type="password"  name= "password" placeholder="Password" >
        <!--<select size = "3" multiple role = "role">-->
        <select name="role">
            <option disabled> choose role for new user </option>
            <option value = "USER">USER</option>
            <option value = "STAFF">STAFF</option>
            <option value = "ADMINISTRATOR">ADMINISTRATOR</option>
        </select>
        </br>
        <input type="submit" class="Button" value="Enter">
    </form>
</div>
</div>
</body>
</html>