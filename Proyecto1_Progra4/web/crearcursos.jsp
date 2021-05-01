<%-- 
    Document   : crearcursos
    Created on : 24/04/2021, 01:42:44 PM
    Author     : Usuario
--%>

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Creacion de cursos</title>
    </head>
    <body>
        <%@include file="nav_bar.jsp"%>
        <%Map<String, String> errores = (Map<String, String>) request.getAttribute("Error");%>
        <div class="ftco-section p-5">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-5">
                    <div class="card-heading"></div>
                    <div class="card-body">
                        <div class="row justify-content-center">
                            <div class="col-md-6 text-center mb-5">
                                <h2 class="title text-center">Información de nuevo curso</h2>
                            </div>
                        </div>
                        <%
                            String nombre = "";
                            String tematica = "";
                            String precio = "";
                            if (errores != null) {
                                nombre = errores.get("nombre");
                                tematica = errores.get("tematica");
                                precio = errores.get("precio");
                        %><div class="alert alert-danger" role="alert">Los datos ingresados no son correctos o están incompletos, intente nuevamente</div>
                        <%}%>
                        <form action="CursoRegistrado" method="POST" enctype="multipart/form-data" >
                            <div class="form-group">
                                <input class="form-control rounded-right" type="text" placeholder="Nombre del curso" name="nombre" value="<%=nombre%>">
                            </div>
                            <div class="form-group">
                                <input class="form-control rounded-right" type="text" placeholder="Tematica" name="tematica" value="<%=tematica%>">
                            </div>
                            <div class="form-check-inline">
                                <input class="form-check-input" type="checkbox" id="flexCheckDefault" name="oferta" value="1">
                                <label class="form-check-label" for="flexRadioDefault2">En Oferta</label>
                            </div>
                            <div class="form-group">
                                <input class="form-control rounded-right" type="text" placeholder="Precio de matricula" name="precio" value="<%=precio%>">
                            </div>
                            <div class="form-group">
                                <label class="form-label" for="archivo">Seleccione archivo</label>
                                <input type="file" name="archivo" class="form-control" id="archivo">
                            </div>
                            <div class="p-t-10">
                                <button class="btn btn-primary rounded submit w-100" type="submit">Registrar nuevo curso</button>
                            </div>
                        </form>
                    </div>
                </div>
                <%@include file = "cursos_Admin.jsp"%>
            </div>

        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>
