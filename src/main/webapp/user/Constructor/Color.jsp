<%@ page import="com.carEntity.ColorSet" %>
<%@ page import="java.util.List" %>
<%@ page import="com.api.Factory" %>
<%@ page import="com.carEntity.ModelName" %>
<%@ page import="com.carEntity.Color" %><%--
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
        <title>Colors</title>
        <link rel="shortcut icon" href="img/plogo.png">
        <link href="../../css/Main.css" rel="stylesheet" type="text/css">
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
            <%
                List<ColorSet> colorSetList = Factory.getInstance().getColorSetDAO().getAll();
                    if (colorSetList!=null){
                        for(ColorSet colorSet:colorSetList){
                            for (Color color: colorSet.getColors()){
            %>
            <div class = "photo">
                <p><a href="Constructor/result?colorset=<%=color.getColorName()%>"><img src = "../../img/color/<%=color.getImgAdress()%>"> </a></p>
                <p class = "caption"><%=color.getColorName()%></p>
            </div>
            <%}
                }
                    }
            %>
        </div>
    </body>
</html>