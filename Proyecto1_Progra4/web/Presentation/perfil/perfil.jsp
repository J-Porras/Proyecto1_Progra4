<%-- 
    Document   : perfil
    Created on : 14/04/2021, 11:03:45 AM
    Author     : Usuario
--%>

<%@page import="Login.Model_Login"%>
<%@page import="Usuarios.logica.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%  Model_Login model =( Model_Login)request.getAttribute("Model_Login");
Usuarios u = model.getCurrent_user();
System.out.println(u.getNombre());
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/Proyecto1_Progra4/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="/Proyecto1_progra4/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Perfil</title>
    </head>
    <body>
        <div class="row container d-flex justify-content-center">
            <div class="col-xl-6 col-md-12">
                <div class="card user-card-full">
                    <div class="row m-l-0 m-r-0">
                        <div class="col-sm-4 bg-c-lite-green user-profile">
                            <div class="card-block text-center text-white">
                                <div class="m-b-25"> <img src="https://img.icons8.com/bubbles/100/000000/user.png" class="img-radius" alt="User-Profile-Image"> </div>
                            </div>
                        </div> 
                        <div class="col-sm-8">
                            <div class="card-block">
                                <h6 class="m-b-20 p-b-5 b-b-default f-w-600">Información</h6>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <p class="m-b-10 f-w-600">Nombre</p>
                                        
                                        <h6 class="text-muted f-w-400"> <%out.print(u.getNombre()); %> </h6>
                                    </div>
                                    <div class="col-sm-6">
                                        <p class="m-b-10 f-w-600">Identificación</p>
                                        <h6 class="text-muted f-w-400"><%out.print(u.getId()); %></h6>
                                    </div>
                                    <div class="col-sm-6">
                                        <p class="m-b-10 f-w-600">Email</p>
                                        <h6 class="text-muted f-w-400"><%out.print(u.getEmail()); %></h6>
                                    </div>
                                    <div class="col-sm-6">
                                        <p class="m-b-10 f-w-600">Num. telefono</p>
                                        <h6 class="text-muted f-w-400"><%out.print(u.getTelefono()); %></h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>
