<%-- 
    Document   : cursos
    Created on : 14/04/2021, 10:47:38 AM
    Author     : Usuario
--%>

<%@page import="Usuarios.logica.Usuarios"%>
<%@page import="Controller_index.Model_index"%>
<%@page import="Cursos.Logica.Curso"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Model_index model = (Model_index) request.getAttribute("Model_index");
    List<Curso> cursos = model.getCursos();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Cursos</title>
    </head>
    <body>
        <%Usuarios u = (Usuarios) session.getAttribute("Usuario");%>
        <div class="container pb-5">
            <div class="row mb-5">
                <%for (Curso c : cursos) {%>
                <div class="col-4 card">
                    <img class="rounded m-2" height="175" width="400" src="images/image?codigo=<%=c.getId()%>">
                    <h5 class="text-center"><b>Nombre: <%out.print(c.getNombre());%></b></h5>
                    <div class="card-body">
                        <div class="text-dark text-center">Tematica: <%out.print(c.getTematica());%></div>
                        <div class="text-dark text-center">Precio de matricula: <%out.print(c.getPrecio());%> colones</div>
                        <%if (u != null) {%>
                        <%if (u.getRol() != 2 || u.getRol() != 3) {%>
                        <div class="d-grid gap-2">
                            <a class="btn btn-outline-primary" method="POST" href="GruposSistema?codigo=<%=c.getId()%>">Matricularse</a>
                        </div>
                        <%}%>
                        <%} else {%>
                        <div class="d-grid gap-2">
                            <a class="btn btn-outline-primary" method="POST" href="GruposSistema?codigo=<%=c.getId()%>">Matricularse</a>
                        </div>
                        <%}%>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </body>
</html>
