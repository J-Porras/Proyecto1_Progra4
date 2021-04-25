<%-- 
    Document   : cursos
    Created on : 14/04/2021, 10:47:38 AM
    Author     : Usuario
--%>

<%@page import="Controller_Admin.Model_Admin"%>
<%@page import="Controller_index.Model_index"%>
<%@page import="Cursos.Logica.Curso"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Model_Admin model = (Model_Admin) request.getAttribute("Model_Admin");
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
        <div class="row justify-content-center">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Codigo</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Tematica</th>
                        <th scope="col">Precio matricula</th>
                        <th scope="col">Estado</th>
                    </tr>
                </thead>
                <tbody>
                    <%for (Curso c : cursos) {%>
                    <tr>
                        <th scope="row"><%out.print(c.getId());%></th>
                        <td><%out.print(c.getNombre());%></td>
                        <td><%out.print(c.getTematica());%></td>
                        <td><%out.print(c.getPrecio());%></td>
                        <td><%out.print(c.getEstado()?"En oferta":"Archivado");%></td>
                    </tr>
                    <%}%>
                </tbody>
        </div>
    </body>
</html>
