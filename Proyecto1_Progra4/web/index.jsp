<%-- 
    Document   : index
    Created on : 16/04/2021, 01:15:53 PM
    Author     : Usuario
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pag. Principal</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%if (session.getAttribute("Usuario") != null) {%>
        <h1>Hay un usuario en el session</h1>
        <%}%>

        <%@include file = "nav_bar.jsp"%>
        <%@include file = "cursos.jsp"%>
        <%@include file = "footer.jsp"%>
    </body>
</html>
