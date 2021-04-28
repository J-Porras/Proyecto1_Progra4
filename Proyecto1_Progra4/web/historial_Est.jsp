<%-- 
    Document   : historial_Est
    Created on : 26/04/2021, 01:27:44 PM
    Author     : Usuario
--%>

<%@page import="Controller_Est.Model_est"%>
<%@page import="Matriculas.Logic.Matricula"%>
<%@page import="Matriculas.Logic.Matricula"%>
<%@page import="Grupos.Logica.Grupo"%>
<%@page import="Cursos.Logica.Curso"%>
<%@page import="Cursos.Logica.Curso"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Model_est model = (Model_est) request.getAttribute("Model_est");
    List<Matricula> cursos_est = model.getEstudiantes_matricula();
    List<Grupo> grupos = model.getTodos_grupos();
    List<Curso> cursos = model.getTodos_cursos();
    System.out.println(cursos_est.size());
%>
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
                                    <%for (Matricula c : cursos_est) {%>
                                        <tr>
                                            <th scope="row" ><%out.print(c.getId_grupo());%></th>
                                            <%
                                                String profe= " ";
                                                String nombreC= " ";
                                                for(Grupo x: grupos){
                                                    if(x.getNum_grupo()==c.getId_grupo()){
                                                        profe =x.getProf_titular();

                                                        for(Curso u: cursos)
                                                        {
                                                            if(u.getId()==x.getId_curso()){
                                                                nombreC =u.getNombre();
                                                                break;
                                                            }
                                                        }
                                                        break;
                                                    }
                                                }
                                            %>
                                            <td><%out.print(profe);%></td>

                                            <td><%out.print(nombreC);%></td>
                                            <td><%out.print(c.getFec_matricula().replace(',','-'));%></td>
                                            <%
                                            if(c.getCalificacion()==0){%>
                                                <td><%out.print("En curso");%></td>
                                            <% }else{%>
                                                <td><%out.print(c.getCalificacion());%></td>
                                            <%}%>
                                       
                                        </tr>
                                    <%}%>
                                </tbody>
                            </table>
                            <a target='_blank' href="href='/CursosImagenPdf/presentation/cursos/HistorialPDF'>  " class="btn btn-outline-dark">Descargar version en PDF</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <%@include file="footer.jsp" %>
</html>
