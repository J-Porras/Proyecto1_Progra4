<%-- 
    Document   : cursos
    Created on : 14/04/2021, 10:47:38 AM
    Author     : Usuario
--%>

<%@page import="Cursos.Logica.Curso"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Cursos</title>
    </head>
    <body>
        
        
        <div class="row">
            <div class="col-md-3">
                <a href="#">
                    <img class="img-fluid rounded mb-3 mb-md-0" src="http://placehold.it/700x300" alt="">
                </a>
            </div>
            <div class="col-md-5">
                <h3>Curso 1</h3>
                <p>Descripción de curso</p>
                <a class="btn btn-primary" href="#">Matricularse</a>
            </div>
            
            
        </div>
        
        
        //tabla de cursos en descuento
        // si no hay cursos no genera la tabla del todo
        <%List<Curso> cursos_off = (List<Curso>)request.getAttribute("cursos");%>
        <%if(cursos_off.isEmpty()){%>
            <p>Actualmente no hay cursos en descuento, vuelva mas tarde!</p>
        <%}%>
        
        <%else{%>
         <table class="table">
            <thead>
                <tr>
                    <th scope="col">Nombre</th>
                    <th scope="col">Precio</th>
                    <th scope="col">Imagen</th>
                </tr>
            </thead>
            <tbody>
              <tr>
                    <th scope="row">1</th>
                    <td>Mark prueba</td>
                    <td>Otto prueba</td>
                    <td>@mdo prueba</td>
              </tr>
              <%for (Curso c: cursos_off) {%>
                    <th scope="row"><%= c.getTematica() %></th>
                    <td><%=c.getPrecio() %></td> 
                    //falta la imagen
                    //<td><img src='/Proyecto1_Progra4/cursos/image?codigo=<%=c.getId()%>'></td> 
                <%} %>
              
            </tbody>
         </table>       
                
                      
         <%} %>
        
        
        
        
    </body>
</html>
