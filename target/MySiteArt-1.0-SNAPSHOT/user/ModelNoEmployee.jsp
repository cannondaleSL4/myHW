<%@ page import="java.util.List" %>
<%@ page import="com.carEntity.ModelName" %>
<%@ page import="com.api.Factory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="utf-8" />
        <title>Ford it's a future for right now!</title>
		<link href="../css/Main.css" rel="stylesheet" type="text/css">
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
				<li><a href="ModelNoEmployee.jsp">Models</a></li>
				<li><a href="Contact.jsp">Contact Us</a></li>
				<li><a href="About.jsp">About</a></li>
			</ul>
        </div>

        <div id = "main">
			<%
				List<ModelName> modelList = Factory.getInstance().getModelNameDAO().getAll();
				for(ModelName modelName:modelList){
			%>
			<div class = "photo">
				<p><a href="<%=modelName.getModelName()%> + .jsp"><img src = "../img/<%=modelName.getImgAdress()%>"> </a></p>
				<p class = "caption"><%=modelName.getModelName()%></p>
			</div>
			<%}%>
        </div>
    </body>
</html>