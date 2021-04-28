<%-- 
    Document   : estudiantesgrupo
    Created on : 27/04/2021, 05:54:21 PM
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Estudiantes en grupo</title>
    </head>
    <body>
        <%@include file="nav_bar.jsp"%>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-7">
                    <div class="card-body">
                        <div class="row justify-content-center">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">ID estudiante</th>
                                        <th scope="col">Nota</th>
                                        <th scope="col">Actualizar nota</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                <form action="actualizarNota" method="POST">
                                    <th scope="row" ><input type="hidden" name="idestu" value="1"/>1</th>
                                    <td><input type="text" name="valornota" placeholder="0"></td>
                                    <!--td><a href="calificacion?id=1&valornota=" class="btn btn-outline-dark btn-sm" name="matricularse" value="" method="POST" >Actualizar calificacion<a/></td-->
                                    <td><button type="submit" class="btn-outline-dark rounded submit w-100">Ingresar</button></td>
                                </form>
                                </tr>
                                <tr>
                                <form action="actualizarNota" method="POST">
                                    <th scope="row" ><input type="hidden" name="idestu" value="2"/>2</th>
                                    <td><input type="text" name="valornota" placeholder="0"></td>
                                    <!--td><a href="calificacion?id=1&valornota=" class="btn btn-outline-dark btn-sm" name="matricularse" value="" method="POST" >Actualizar calificacion<a/></td-->
                                    <td><button type="submit" class="btn-outline-dark rounded submit w-100">Ingresar</button></td>
                                </form>
                                </tr>
                                <tr>
                                <form action="actualizarNota" method="POST">
                                    <th scope="row" ><input type="hidden" name="idestu" value="3"/>3</th>
                                    <td><input type="text" name="valornota" placeholder="0"></td>
                                    <!--td><a href="calificacion?id=1&valornota=" class="btn btn-outline-dark btn-sm" name="matricularse" value="" method="POST" >Actualizar calificacion<a/></td-->
                                    <td><button type="submit" class="btn-outline-dark rounded submit w-100">Ingresar</button></td>
                                </form>                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp"%>

    </body>
</html>
