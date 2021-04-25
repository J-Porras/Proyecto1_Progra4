<%-- 
    Document   : profesores
    Created on : 24/04/2021, 08:24:09 PM
    Author     : Usuario
--%>

<%@page import="Usuarios.logica.Usuarios"%>
<%@page import="java.util.List"%>
<%@page import="Controller_Admin.Model_Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Model_Admin model = (Model_Admin) request.getAttribute("Model_Admin");
    List<Usuarios> profesores= model.getProfesores();
    System.out.println(profesores.size());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Profesores</title>
    </head>
    <body>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Password</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Especialidad</th>
                    <th scope="col">Telefono</th>
                    <th scope="col">Email</th>
                </tr>
            </thead>
            <tbody>
                 <%for (Usuarios c : profesores) {%>
                <tr>
                    <th scope="row"><%out.print(c.getId());%></th>
                     <td><%out.print(c.getContrasenna());%></td>
                    <td><%out.print(c.getNombre());%></td>
                    <td><%out.print(c.getEspecialidad());%></td>
                    <td><%out.print(c.getTelefono());%></td>
                    <td><%out.print(c.getEmail());%></td>
                </tr>
                <%}%>
               
            </tbody>
        </table>
    </body>
</html>
