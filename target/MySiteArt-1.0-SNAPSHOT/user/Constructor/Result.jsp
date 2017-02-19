<%@ page import="com.carEntity.ModelName" %>
<%@ page import="com.api.Factory" %>
<%@ page import="java.util.List" %>
<%@ page import="com.carEntity.CarParametrs" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <title>Ford it's a future for right now!</title>
    <link rel="shortcut icon" href="img/plogo.png">
    <link href="../../css/Result.css" rel="stylesheet" type="text/css">
    <%
        if(session.getAttribute("userName")==null){
            response.sendRedirect("/login.jsp");
        }
    %>
</head>
<body>
<section>
    <form>
        <p><%=session.getAttribute("userName")%></p>
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
        <% List<CarParametrs>listResults = (List <CarParametrs>) session.getAttribute("result");
            if (listResults!=null){
                for(CarParametrs carParametrs: listResults){
                    %><div class  = "result">
                        <div class = "inresult">
                            <table>
                                <tr>
                                    <th> Ford <%=carParametrs.getModelName().getModelName()%> </th>
                                </tr>

                                <tr>
                                    <td> Engine </td><td> <%=carParametrs.getEngine().getNameOfEngine()%> </td>
                                </tr>

                                <tr>
                                    <td> Transmission </td><td> <%=carParametrs.getTransmission().getTransmissionName()%> </td>
                                </tr>

                                <tr>
                                    <td> Body </td><td> <%=carParametrs.getKindOfBody().getNameKindOfBody()%> </td>
                                </tr>

                                <tr>
                                    <td> Color </td><td> <%=session.getAttribute("colorS")%> </td>
                                </tr>
                            </table>
                    </div>
                    <div class ="inresultR"><img src="../../img/<%=carParametrs.getModelName().getImgAdress()%>"></div>
                </div>
        <%
                }
            }
        %>
</div>
</body>
</html>

