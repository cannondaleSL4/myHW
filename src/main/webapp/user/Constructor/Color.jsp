<%@ page import="com.carEntity.ColorSet" %>
<%@ page import="java.util.List" %>
<%@ page import="com.api.Factory" %>
<%@ page import="com.carEntity.ModelName" %>
<%@ page import="com.carEntity.Color" %>
<%@ page import="java.util.ArrayList" %><%--
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
        <link href="../../css/Color.css" rel="stylesheet" type="text/css">
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
                <p><a href = "/logout">Exit</a></p>
            </form>
        </section>
        <div class="header">

            <div class = "picOne">
                <img src="../../img/logo.png">
            </div>
            
            <ul id="menu">
                <li><a href="../../overall/Model.jsp">Models</a></li>
                <li><a href="../../overall/Contact.jsp">Contact Us</a></li>
                <li><a href="../../overall/About.jsp">About</a></li>
            </ul>
        </div>
        <div id = "main">
            <%
                List<ColorSet> colorSetList = (ArrayList<ColorSet>) session.getAttribute("colorSet");
                    if (colorSetList!=null){
                        String colorsName = " ";
                        for(ColorSet colorSet:colorSetList){
            %>
                <div class = "photoColor">
                    <a href="result?colorset=<%=colorSet.getIdColorSet()%>">
                <%
                    for(Color color: colorSet.getColors()){
                    colorsName+=color.getColorName() + " ";
                %>
                    <div class  = "color"><p><img src = "../../img/color/<%=color.getImgAdress()%>"></p></div>
                <% } %>
                    <p class = "caption"> <%=colorsName%></p>
                        <%session.setAttribute("colorS",colorsName); %>
                    </a>
                </div>

            <% } }%>
        </div>
    </body>
</html>