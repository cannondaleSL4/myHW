<%@ page import="com.carEntity.ModelName" %>
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
            response.sendRedirect("/logout");
            System.out.println(888888888);
        }else if(!session.getAttribute("userRole").toString().equalsIgnoreCase("ADMINISTRATOR") &&
                !session.getAttribute("userRole").toString().equalsIgnoreCase("STAFF")) {
            response.sendRedirect("/logout");
        }
    %>
</head>
<body>
<section>
    <form>
        <p><%=request.getSession().getAttribute("userName")%></p>
        <p><a href = "/logout">Exit</a></p>
    </form>
</section>
<div class="header">
    <div class = "picOne">
        <img src="../../img/logo.png">
    </div>

    <ul id="menu">
        <li><a href="/user/Constructor/model">Model for user</a></li>
        <li><a href="../overall/Contact.jsp">Contact Us</a></li>
        <li><a href="../overal/About.jsp">About</a></li>
    </ul>
</div>

<div id = "main">
    <%
        List<ModelName> modelNameList= (List<ModelName>) session.getAttribute("modelList");
        if (modelNameList!=null){
            for(ModelName modelName:modelNameList){
    %>
    <div class = "photo">
        <p><a href="edit?modelName=<%=modelName.getModelName()%>"><img src = "../img/<%=modelName.getImgAdress()%>"> </a></p>
        <p class = "caption"><%=modelName.getModelName()%></p>
    </div>
    <%}
    }%>
        <div class = "photo">
            <p>
                <a href="add?modelName=y"> <img src = "../img/pl.png"></a>
            </p>
        </div>
    </div>
</div>
</body>
</html>

