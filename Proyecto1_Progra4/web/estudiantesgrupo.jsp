<%-- 
    Document   : estudiantesgrupo
    Created on : 27/04/2021, 05:54:21 PM
    Author     : Usuario
--%>
<%@page import="Matriculas.Logic.Matricula"%>
<%@page import="java.util.List"%>
<%@page import="Controller_Grupos.Model_Grupos"%>
<% Model_Grupos model = (Model_Grupos) request.getAttribute("Model_Grupos");
    List<Matricula>  est = model.getEstudiantes_profe();
   
    System.out.println(est.size());
%>
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
                        <%
                         String grupo =request.getParameter("codigo");
                        %>
                        <h3> Grupo: <%out.print(grupo);%></h3>
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
                                    <%for(Matricula c:est){%>
                                    <tr>
                                <form action="actualizarNota?codigo=<%=c.getId_grupo()%>" method="POST">
                                    <th scope="row" ><input type="hidden" name="idestu" value="<%=c.getId_est()%>"/><%out.print(c.getId_est());%></th>
                                    <%
                                        if(c.getCalificacion()==0){
                                            %> <td><input type="text" name="valornota" placeholder="<%="En curso"%>"></td><%
                                             }else{%>
                                                <td><input type="text" name="valornota" placeholder="<%out.print(c.getCalificacion());%>"></td>
                                            <%}%>
                                 
                                    <!--td><a href="calificacion?id=1&valornota=" class="btn btn-outline-dark btn-sm" name="matricularse" value="" method="POST" >Actualizar calificacion<a/></td-->
                                    <td><button type="submit" class="btn-outline-dark rounded submit w-100">Ingresar</button></td>
                                </form>
                                </tr>
                              <%}%>
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
