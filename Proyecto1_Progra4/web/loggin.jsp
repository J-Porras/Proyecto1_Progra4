<%-- 
    Document   : login
    Created on : 13/04/2021, 06:11:11 PM
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Inicio de sesión</title>
    </head>
    <body>
        <%@include file = "nav_bar.jsp"%>
        <section class="ftco-section">
            <div class="bg-blend-overlay">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-6 text-center mb-5">
                            <h2 class="heading-section">Inicio de sesión</h2>
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="col-md-6 col-lg-5">
                            <div class="login-wrap p-md-1">
                                <form action="IniciarSesion" method="POST" class="login-form">
                                    <div class="form-group">
                                        <input type="text" class="form-control rounded-right" placeholder="Nombre de usuario" name="id" />
                                    </div>
                                    <div/>
                                    <div class="form-group">    
                                        <input type="password" class="form-control rounded-left" placeholder="Contraseña" name ="contrasenna"/>
                                    </div>
                                    <div/>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary rounded submit w-100" >Iniciar sesión</button>
                                    </div>
                                    <div class="form-group d-md-flex ">
                                        <div class="w-100 text-md-center">
                                            <a href="#">Crear cuenta</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
    <%@include file = "footer.jsp"%>
</html>
