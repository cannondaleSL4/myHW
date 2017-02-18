<%@ page import="com.carEntity.ModelName" %>
<%@ page import="com.api.Factory" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="utf-8" />
        <title>Ford it's a future for right now!</title>
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
				<p><%=request.getSession().getAttribute("userName")%></p>
				<p><a href = "../../login.jsp">Exit</a></p>
			</form>
		</section>
        <div class="header">
            <div class = "picOne">
                <img src="../../img/logo.png">
            </div>

            <ul id="menu">
				<li><a href="Model.jsp">Models</a></li>
				<li><a href="../Contact.jsp">Contact Us</a></li>
				<li><a href="../About.jsp">About</a></li>
			</ul>
        </div>

        <div id = "main">
			<%
				List<ModelName> modelNameList= (List<ModelName>) session.getAttribute("modelList");
				if (modelNameList!=null){
					for(ModelName modelName:modelNameList){
			%>
			<div class = "photo">
				<p><a href="engine?modelName=<%=modelName.getModelName()%>"><img src = "../../img/<%=modelName.getImgAdress()%>"> </a></p>
				<p class = "caption"><%=modelName.getModelName()%></p>
			</div>
			<%}
			}%>
        </div>
    </body>
</html>

