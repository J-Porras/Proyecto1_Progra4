<%-- 
    Document   : cursos
    Created on : 14/04/2021, 10:47:38 AM
    Author     : Usuario
--%>

<%@page import="Controller_index.Model_index"%>
<%@page import="Cursos.Logica.Curso"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Model_index model =( Model_index)request.getAttribute("Model_index");
   List<Curso> cursos=model.getCursos();
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
        <%for(Curso c: cursos){%>
        <div class="row">
            <div class="col-md-3">
                <a href="#">
                    <img class="img-fluid rounded mb-3 mb-md-0" src="http://placehold.it/700x300" alt="">
                </a>
            </div>
            <div class="col-md-5">
                <h3><%out.print(c.getTematica());%></h3>
                <p><%out.print(c.getDescripcion());%></p>
                <p><%out.print(c.getPrecio());%></p>
                <a class="btn btn-primary" href="#">Matricularse</a>
            </div>
        </div>
        <%}%>
    </body>
</html>
