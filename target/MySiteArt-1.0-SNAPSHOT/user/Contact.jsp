<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="utf-8" />
        <title>Ford it's a future for right now!</title>
        <link rel="shortcut icon" href="img/plogo.png">
        <link href="../css/Main.css" rel="stylesheet" type="text/css">
        <%
            if(session.getAttribute("userName")==null){
                response.sendRedirect("/login.jsp");
            }
        %>
    </head>
    <body>
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
                <li><a href="Constructor/Model.jsp">Models</a></li>
                <li><a href="Contact.jsp">Contact Us</a></li>
                <li><a href="About.jsp">About</a></li>
            </ul>
        </div>

            <div id = "main">
                <div class ="text">
                    <h2>Phone</h2>

                    <p>Do you have a question about Ford, Mercury or Lincoln?</p>

                    <p><b>Ford and Mercury United States<br></b>
                    1-800-392-3673<br>
                    1-800-232-5952 (TDD for the Hearing Impaired)<br>
                    Monday-Friday, 8:00 a.m.- 8:00 p.m.<br> Eastern Time<br>
                    Saturday, 9:00 a.m.- 5:30 p.m. Eastern Time</p>
                    <p><b>Ford and Mercury Canada</b></br>
                    1-800-565-3673<br>
                    Monday-Friday, 8:30 a.m.- 5:00 p.m. Local Time</p>
                    <p><b>Lincoln Concierge United States</b><br>
                    1-800-521-4140<br>
                    24/7.365</p>

                    <p>1-800-232-5952 (TDD for the Hearing Impaired)<br>
                    Monday- Friday, 8:00 a.m.- 8:00 p.m. Eastern Time<br>
                    Saturday, 9:00 a.m.- 5:30 p.m. Eastern Time</p>
                    <p><b>Lincoln Service Canada</b><br>
                    1-800-387-9333<br>
                    Monday-Friday, 8:30 a.m.- 5:00 p.m. Local Time</p>
                    <p><b>Lincoln Concierge Canada</b><br>
                    1-888-658-6554<br>
                    Monday-Friday, 8:30 a.m.- 5:00 p.m. Eastern Time</p>
                </div>
            </div>
    </body>
</html>