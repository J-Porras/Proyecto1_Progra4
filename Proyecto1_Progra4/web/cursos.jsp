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
    System.out.println(cursos.size());
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
        <div class="container pb-xxl-1">
            <div class="row">
                <%for (Curso c : cursos) {%>
                <div class="col-4">
                    <img class="w-100 h-50" src="images/image?codigo=<%=c.getId()%>">
                    <h5 class="text-center"><%out.print(c.getNombre());%></h5>
                    <div class="col-4">
                        <div class="text-dark"><%out.print(c.getTematica());%></div>
                        <div class="text-dark">Precio:<%out.print(c.getPrecio());%></div>
                        <%if (u != null && u.getRol() == 3) {%>
                        <a class="btn btn-outline-danger" href="GruposSistema?codigo=<%=c.getId()%>">Matricularse</a>
                        <%}%>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </body>
</html>
