<%-- 
    Document   : login
    Created on : 13/04/2021, 06:11:11 PM
    Author     : Usuario
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
        <%Map<String, String> errores = (Map<String, String>) request.getAttribute("Error");%>
        <%@include file = "nav_bar.jsp"%>
        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center p-5">
                    <div class="col-md-6 text-center mb-5">
                        <h2 class="heading-section">Inicio de sesión</h2>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-5">
                        <div class="login-wrap p-md-1">
                            <form action="IniciarSesion" method="POST" class="login-form p-5">
                                <%
                                    String idUsuario = "";
                                    String contrasenna = "";
                                    if (errores != null) {
                                        idUsuario = errores.get("id");
                                        contrasenna = errores.get("contrasenna");
                                %>
                                <div class="alert alert-danger" role="alert">Usuario o contraseña incorrecto</div>
                                <%}%>
                                <div class="form-group">
                                    <input type="text" class="form-control rounded-right" placeholder="Nombre de usuario" name="id" value="<%=idUsuario%>"/>
                                </div>
                                <div/>
                                <div class="form-group">    
                                    <input type="password" class="form-control rounded-left" placeholder="Contraseña" name ="contrasenna" value="<%=contrasenna%>"/>
                                </div>
                                <div/>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary rounded submit w-100" >Iniciar sesión</button>
                                </div>
                                <div class="form-group d-md-flex p-4">
                                    <div class="w-100 text-md-center">
                                        <a href="Registrarse">Crear cuenta</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
    <%@include file = "footer.jsp"%>
</html>
