<%@ page import="com.carEntity.ModelName" %>
<%@ page import="com.api.Factory" %>
<%@ page import="java.util.List" %>
<%@ page import="com.carEntity.CarParametrs" %>
<%@ page import="com.carEntity.Engine" %><%--
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
        <meta charset="utf-8">
        <title>Engine</title>
        <link rel="shortcut icon" href="img/plogo.png">
        <link rel="stylesheet" href="../../css/Constructor.css" type="text/css">
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
        <form action="kindOfBody" method=post>
            <h3>Select you Engine for <%=session.getAttribute("modelName")%> </h3>
            <% List <Engine> engineList= (List<Engine>) session.getAttribute("engineList");
                if (engineList!= null){
                    for(Engine engine: engineList){

            %>
            <div class = "check">
                <div class = "block1"><img src = "../../img/engine/<%=engine.getImgAdress()%>"></div>
                <div class = "block1"><%=engine.getNameOfEngine()%></div>
                <div class = "block2"><input type="radio" name="engineName" value="<%=engine.getNameOfEngine()%>"/></div>
            </div>
            <%}
                }%>
            <div class = "checkbut"><input type="submit" value="Сhoose"></div>
        </form>
    </div>
    </body>
</html>