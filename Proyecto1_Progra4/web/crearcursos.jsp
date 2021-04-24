<%-- 
    Document   : crearcursos
    Created on : 24/04/2021, 01:42:44 PM
    Author     : Usuario
--%>

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
        <div class="container">
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
                            <form action="ingresarcurso" enctype="multipart/form-data" method="POST">
                                <div class="form-group">
                                    <input class="form-control rounded-right" type="text" placeholder="Nombre del curso" name="nombre">
                                </div>
                                <div class="form-group">
                                    <input class="form-control rounded-right" type="text" placeholder="Descripción" name="descripcion">
                                </div>
                                <div class="form-check-inline">
                                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                                    <label class="form-check-label" for="flexRadioDefault1">En oferta</label>
                                </div>
                                <div class="form-check-inline">
                                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
                                    <label class="form-check-label" for="flexRadioDefault2">No oferta</label>
                                </div>
                                <div class="form-group">
                                    <input class="form-control rounded-right" type="text" placeholder="Precio de matricula" name="precio">
                                </div>
                                <div class="form-group">
                                    <label class="form-label" for="archivo">Ingrese su archivo</label>
                                    <input type="file" class="form-control" id="archivo" />
                                </div>

                                <div class="p-t-10">
                                    <button class="btn btn-primary rounded submit w-100" type="submit">Registrar nuevo curso</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <%@include file = "cursos.jsp"%>
        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>
