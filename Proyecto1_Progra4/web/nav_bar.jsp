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
        <nav class ="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <div class="navbar-nav">
                    <a class="navbar-brand text-center text-white" href="InicioPrincipal">CursosLibres.com</a>
                    <%Usuarios actual = (Usuarios) session.getAttribute("Usuario");%>
                    <%if (actual != null) {%>
                    <a class="navbar-brand text-center text-white" href="CerrarSesion">Logout</a>
                    <a class="navbar-brand text-center text-white" href="#">Perfil</a>
                    <%if (actual.getRol() == 1) {%>
                    <a class="navbar-brand text-center text-white" href="CrearCursos">Gestionar cursos</a>
                    <a class="navbar-brand text-center text-white" href="#">Gestionar grupos</a>
                    <a class="navbar-brand text-center text-white" href="CrearProfesores">Gestionar profesores</a>
                    <%}%>
                    <%if (actual.getRol() == 2) {%>
                    <a class="navbar-brand text-center text-white" href="#">Mis grupos</a>
                    <%}%>
                    <%if (actual.getRol() == 3) {%>
                    <a class="navbar-brand text-center text-white" href="#">Mis cursos</a>
                    <%}%>  
                    <%} else {%>
                    <a class="navbar-brand text-center text-white" href="loggin.jsp">Iniciar Sesion</a>
                    <%}%>
                </div>
                <div class="col-md-2 navbar-nav-right">
                    <form action="" class="search-form">
                        <div class="form-group">
                            <input type="text" class="form-control" name="nombrecurso" placeholder="Buscar curso...">
                        </div>
                    </form>
                </div>
            </div>
        </nav>
    </body>
</html>
