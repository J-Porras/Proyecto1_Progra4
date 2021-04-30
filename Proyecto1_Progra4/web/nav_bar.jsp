<%-- 
    Document   : navigation_bar
    Created on : 16/04/2021, 01:16:15 PM
    Author     : Usuario
--%>
<%@page session = "true"%>
<%@page import="Login.Model_Login"%>
<%@page import="Usuarios.logica.Usuarios"%>
<% String myURI = request.getRequestURI(); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css">
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
                    <!-- <a class="navbar-brand text-center text-white" href="CrearGrupos">Gestionar grupos</a>-->
                    <a class="navbar-brand text-center text-white" href="CrearProfesores">Gestionar Profesores</a>
                    <a class="navbar-brand text-center text-white" href="ListaUsuarios">Lista de Usuarios</a>
                    <%}%>
                    <%if (actual.getRol() == 2) {%>
                    <a class="navbar-brand text-center text-white" href="MisGrupos">Mis grupos</a>
                    <%}%>
                    <%if (actual.getRol() == 3) {%>
                    <a class="navbar-brand text-center text-white" href="MisCursos">Mis cursos</a>
                    <%}%>  
                    <%} else {%>
                    <a class="navbar-brand text-center text-white" href="loggin.jsp">Iniciar Sesion</a>
                    <%}%>
                </div>
                <div class="navbar-nav-right">
                     <%if (myURI.equals("/Proyecto1_Progra4/crearcursos.jsp")) {%>
                    <form action="buscarCursosA" class="d-flex" method="POST">
                         <select class="form-select-sm " aria-label="default select-sm example" id="selectFiltro" name="Filtro">
                                    <option selected value="0">Filtro</option>
                                    <option value="1">Nombre</option>
                                    <option value="2">Tematica</option>
                                </select>
                        <div class="col-6">
                            <div class="col"><input type="text" class="form-control" name="curso" placeholder="Cursos..."></div>
                        </div>
                        <%}else {%>
                    <%if (myURI.equals("/Proyecto1_Progra4/usuarios.jsp")) {%>
                    <form action="buscarUsuarios" class="d-flex" method="POST">
                        <div class="col-6">
                            <div class="col"><input type="text" class="form-control" name="Usuario" placeholder="Usuario..."></div>
                        </div>
                        <%} else if ((myURI.equals("/Proyecto1_Progra4/registrarse.jsp") && actual != null && actual.getRol() == 1)) {%>
                        <form action="buscarProfesor" class="d-flex" method="POST">
                            <div class="col-6">
                                <div class="col"><input type="text" class="form-control" name="profesor" placeholder="Usuario..."></div>
                            </div>
                            <%} else {%>
                            <form action="buscarCursos" class="d-flex" method="POST">
                                <select class="form-select-sm " aria-label="default select-sm example" id="selectFiltro" name="Filtro">
                                    <option selected value="0">Filtro</option>
                                    <option value="1">Nombre</option>
                                    <option value="2">Tematica</option>
                                </select>
                                <div class="col-6">
                                    <div class="col"><input type="text" class="form-control" name="curso" placeholder="Curso..."></div>
                                </div>
                                <%}%>
                                <%}%>
                                <button class="btn-sm btn-primary rounded submit w-100" type="submit">Buscar</button>
                            </form>
                            </div>
                            </div>
                            </nav>
                            <script src="js/bootstrap.bundle.js" type="text/javascript"></script>
                            </body>
                            </html>
