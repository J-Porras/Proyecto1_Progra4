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
        
        
        <!--
        Viejo espacio de matricular
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
            
            
        </div>-->
        
        
        //tabla de cursos en descuento, si no hay cursos no genera la tabla del todo
        <%List<Curso> cursos_off = (List<Curso>)request.getAttribute("cursos");%>
        <%if(cursos_off.isEmpty()){%>
            <p>Actualmente no hay cursos en descuento, vuelva mas tarde!</p>
        <%}%>
        
        <%if(!cursos_off.isEmpty()){%>
         <table class="table">
            <thead>
                <tr>
                    <th scope="col">Nombre</th>
                    <th scope="col">Descripcion</th>
                    <th scope="col">Imagen</th>
                    <th scope="col">Precio</th>
                    <th scope="col">Matricular</th>
                </tr>
            </thead>
            <tbody>
              <tr>
                    <th scope="row">nombre prueba</th>
                    <td>texto prueba</td>
                    <td>imagen aca</td>
                    <td>texto prueba</td>
                    <td>texto prueba</td>
              </tr>
              <%for (Curso c: cursos_off) {%>
                    <th scope="row"><%= c.getTematica() %></th>
                    
                    <th scope="row"><%= c.getDescripcion() %></th>
                    //falta la imagen   
                    <!-- <td><img src='/Proyecto1_Progra4/cursos/image?codigo=<%=c.getId()%>'></td> -->
                    <td><%=c.getPrecio() %></td> 
                    <th scope="row"><a class="btn btn-primary" href="#">Matricularse</a></th>
                <%} %>
              
            </tbody>
         </table>       
                
                      
         <%} %>
        
        
        
        
    </body>
</html>
