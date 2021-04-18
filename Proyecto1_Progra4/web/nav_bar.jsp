<%-- 
    Document   : navigation_bar
    Created on : 16/04/2021, 01:16:15 PM
    Author     : Usuario
--%>
<%@page session = "true"%>
<%@page import="Login.Model_Login"%>
<%@page import="Usuarios.logica.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <nav class ="navbar navbar-dark bg-dark navbar-expand-lg">
            <div class="navbar-nav">
                <a class="navbar-brand text-center text-white" href="Inicio">CursosLibres.com</a>
            </div>
            <div class="navbar-nav">
                <%Usuarios actual = (Usuarios) session.getAttribute("Usuario");%>
                <%if (actual != null) {%>
                <a class="nav-link text-white" href="CerrarSesion">Logout</a>
                <%} else {%>
                <a class="nav-link text-white" href="loggin.jsp">Iniciar Sesion</a><%-- Se dirige al jsp --%>
                <%}%>
            </div>
        </nav>
    </body>
</html>
