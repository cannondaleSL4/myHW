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
    <link href="../css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<section>

    <form method=post action="j_security_check" >
        <input type="text"  name= "j_username" placeholder="Username or email" >
        <input type="password"  name= "j_password" placeholder="Password" >
        </br>
        <a href="register.jsp">Sign Up</a>
        <input type="submit" class="Button" value="Enter">
    </form>
</section>
    <script>
        window.onload = function() {
            alert( "you name/pass add success, please log in" );
        };
    </script>
</body>
</html>
