<%-- 
    Document   : historial_Est
    Created on : 26/04/2021, 01:27:44 PM
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Historial estudiante</title>
    </head>
    <body>
        <%@include file="nav_bar.jsp" %>
        <h2 class="text-center">Historial de estudiante</h2>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-7">
                    <div class="card-body">
                        <div class="row justify-content-center">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Num. grupo</th>
                                        <th scope="col">Profesor</th>
                                        <th scope="col">Tematica de clase</th>
                                        <th scope="col">Fecha de matricula</th>
                                        <th scope="col">Cal. Final</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row" >01</th>
                                        <td>Profesor01</td>
                                        <td>Curso01</td>
                                        <td>Fecha01</td>
                                        <td>0</td>
                                    </tr>
                                    <tr>
                                        <th scope="row" >02</th>
                                        <td>Profesor02</td>
                                        <td>Curso02</td>
                                        <td>Fecha02</td>
                                        <td>0</td>
                                    </tr>
                                    <tr>
                                        <th scope="row" >03</th>
                                        <td>Profesor03</td>
                                        <td>Curso03</td>
                                        <td>Fecha03</td>
                                        <td>0</td>
                                    </tr>
                                </tbody>
                            </table>
                            <a href="HistorialPDF" class="btn btn-outline-dark">Descargar version en PDF</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <%@include file="footer.jsp" %>
</html>
