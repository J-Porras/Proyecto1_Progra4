<%-- 
    Document   : grupos
    Created on : 27/04/2021, 04:47:42 PM
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Grupos</title>
    </head>
    <body>
        <%@include file = "nav_bar.jsp"%>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-9">
                    <div class="card-body">
                        <div class="row justify-content-center">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Num. Grupo</th>
                                            <%if (actual.getRol() != 2) {%>
                                        <th scope="col">Profesor</th>
                                            <%}%>
                                        <th scope="col">Dias lectivos</th>
                                        <th scope="col">Horario</th>
                                        <th scope="col">Seleccione el grupo</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row" >1</th>
                                            <%if (actual.getRol() != 2) {%>
                                        <td>Profesor 1</td>
                                        <%}%> 
                                        <td>L-M-K-J-V</td>
                                        <td>8:00 - 10:00</td>
                                        <%if (actual.getRol() == 2) {%>
                                        <td > <a href="asignarNotas?id=xd" class="btn btn-outline-dark" name="asignarNotas" value="" method="POST" >Link al codigo del grupo 1(Asignar Notas) Modificar<a/></td>
                                        <%} else {%>
                                        <td > <a href="##" class="btn btn-outline-dark" name="matricularse" value="" method="POST" >Link al codigo del grupo 1(Matricularme) Modificar<a/></td>
                                        <%}%>
                                    </tr>
                                    <tr>
                                        <th scope="row" >2</th>
                                            <%if (actual.getRol() != 2) {%>
                                        <td>Profesor 2</td>
                                        <%}%>
                                        <td>L-M-K-J-V</td>
                                        <td>8:00 - 10:00</td>
                                        <%if (actual.getRol() == 2) {%>
                                        <td > <a href="asignarNotas?id=xd" class="btn btn-outline-dark" name="asignarNotas" value="" method="POST" >Link al codigo del grupo 1(Asignar Notas) Modificar<a/></td>
                                        <%} else {%>
                                        <td > <a href="##" class="btn btn-outline-dark" name="matricularse" value="" method="POST" >Link al codigo del grupo 1(Matricularme) Modificar<a/></td>
                                        <%}%>
                                    </tr>
                                    <tr>
                                        <th scope="row" >3</th>
                                            <%if (actual.getRol() != 2) {%>
                                        <td>Profesor 3</td>
                                        <%}%>
                                        <td>L-M-K-J-V</td>
                                        <td>8:00 - 10:00</td>
                                        <%if (actual.getRol() == 2) {%>
                                        <td > <a href="asignarNotas?id=xd" class="btn btn-outline-dark" name="asignarNotas" value="asignarNotas" method="POST" >Link al codigo del grupo 1(Asignar Notas) Modificar<a/></td>
                                        <%} else {%>
                                        <td > <a href="##" class="btn btn-outline-dark" name="matricularse" value="" method="POST" >Link al codigo del grupo 1(Matricularme) Modificar<a/></td>
                                        <%}%>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <%@include file = "footer.jsp"%>
</html>
