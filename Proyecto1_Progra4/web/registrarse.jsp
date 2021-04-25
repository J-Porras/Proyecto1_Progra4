<%-- 
    Document   : registrarse
    Created on : 18/04/2021, 01:03:28 PM
    Author     : Usuario
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Registro</title>
    </head>
    <body>
        <%@include file="nav_bar.jsp"%>
        <div class="ftco-section p-5">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-5">
                    <div class="card-heading"></div>
                    <div class="card-body">
                        <div class="row justify-content-center">
                            <div class="col-md-6 text-center mb-5">
                                <h2 class="title text-center">Información de inscripción</h2>
                            </div>
                        </div>
                          <%if (actual != null && actual.getRol() == 1) {%>
                           <form action="ProfesorRegistrado" method="POST">
                           <%}else{%>
                           <form action="RegistroCompleto" method="POST">
                             <%}%>
                            <div class="form-group">
                                <input class="form-control rounded-right" type="text" placeholder="Nombre completo" name="nombre">
                            </div>
                            <div class="form-group">
                                <input class="form-control rounded-right" type="text" placeholder="Identificación" name="id">
                            </div>
                            <div class="form-group">
                                <input class="form-control rounded-right" type="text" placeholder="Email" name="email">
                            </div>
                            <div class="form-group">
                                <input class="form-control rounded-right" type="text" placeholder="Num. telefono" name="telefono">
                            </div>
                            <div class="form-group">
                                <input class="form-control rounded-right" type="password" placeholder="Contraseña" name="contrasenna">
                            </div>
                            <!-- Para crear un profesor, en el controler ver sí el campo está presente
                            en el request, es profesor, caso contrario es un estudiante-->
                            <%if (actual != null && actual.getRol() == 1) {%>
                            <div class="form-group">
                                <input class="form-control rounded-right" type="text" placeholder="Especialidad" name="especialidad">
                            </div>
                            <%}%>
                            <div class="p-t-10">
                                <button class="btn btn-primary rounded submit w-100" type="submit">Registrarse</button>
                            </div>
                        </form>
                        <%if (actual != null && actual.getRol() == 1) {%>
                        <div class="row justify-content-center">
                            <div class="col-md-6 text-center mb-5">
                                <h4 class="title text-center">Profesores en sistema</h4>
                            </div>
                        </div>
                        <%@include  file="profesores.jsp"%>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>
