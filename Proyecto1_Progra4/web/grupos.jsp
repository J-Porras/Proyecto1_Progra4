<%-- 
    Document   : grupos
    Created on : 27/04/2021, 04:47:42 PM
    Author     : Usuario
--%>
<%@page import="Cursos.Logica.Curso"%>
<%@page import="Grupos.Logica.Grupo"%>
<%@page import="java.util.List"%>
<%@page import="Controller_Grupos.Model_Grupos"%>
<% Model_Grupos model = (Model_Grupos) request.getAttribute("Model_Grupos");
    List<Curso>  cursos = model.getCursos();
    List<Grupo> grupos = model.getGrupos();
    System.out.println(grupos.size());
%>
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
                            <%
                                String cursoN= " ";
                                if(actual!=null){
                              if (actual.getRol() != 2) {
                                  for(Curso t:cursos){
                                      if(t.getId()==Integer.parseInt(request.getParameter("codigo"))){
                                          cursoN=t.getNombre();
                                      }
                                  } 
                              
                                  %>
                                  <h3>Matriculando en: <%out.print(cursoN);%></h3> 
                              <%}else{%>
                              <h3>Mis Grupos:</h3>
                         <%}%>
                            <%}else{
                            for(Curso t:cursos){
                                      if(t.getId()==Integer.parseInt(request.getParameter("codigo"))){
                                          cursoN=t.getNombre();
                                      }
                                  } 

                             }%>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Num. Grupo</th>
                                        <%if (actual!=null) {%>
                                            <%if (actual.getRol() != 2) {%>
                                        
                                        <th scope="col">Profesor</th>
                                            <%}else{%>
                                        <th scope="col">Curso</th>
                                        <%}%>
                                         <%}else{%>
                                          <th scope="col">Profesor</th>
                                         <%}%>
                                          
                                        <th scope="col">Dias lectivos</th>
                                        <th scope="col">Horario</th>
                                        <th scope="col">Seleccione el grupo</th>
                                    </tr>
                                </thead>
                                <tbody>
                                     <%   for(Grupo c: grupos){%>
                                    <tr>
                                        <th scope="row" ><%out.print(c.getNum_grupo());%></th>
                                            <%String profe=" ";
                                            if(actual!=null){
                                                if (actual.getRol() != 2) {
                                                List<Usuarios> profesor = model.getPosibleProfesor();
                                                for(Usuarios u:profesor){
                                                     System.out.println(u.getNombre());
                                                    if(u.getId().equals(c.getProf_titular())){
                                                        System.out.println("Sirvio");
                                                       profe = u.getNombre();
                                                    }
                                                }
                                                
                                                
                                            %>
                                        <td><%out.print(profe);%></td>
                                       <%}else{%>
                                       <%
                                           
                                       for(Curso t:cursos){
                                            System.out.println("Sirvio");
                                       
                                      if(t.getId()==c.getId_curso()){
                                          cursoN=t.getNombre();
                                          
                                          
                                      }
                                  }  
                                        System.out.println("sirvio");
                      
                                       %>
                                        <td><%out.print(cursoN);%></td>
                                       <%}%>
                                       <%}else{
                                             {
                                                List<Usuarios> profesor = model.getPosibleProfesor();
                                                for(Usuarios u:profesor){
                                                     System.out.println(u.getNombre());
                                                    if(u.getId().equals(c.getProf_titular())){
                                                        System.out.println("Sirvio");
                                                       profe = u.getNombre();
                                                    }
                                                }
                                                
                                                
                                            %>
                                        <td><%out.print(profe);%></td>
                                     
                                        <%}%>
                                            <%}%>
                                        <td><%out.print(c.getDias());%></td>
                                        <td><%out.print(c.getHorario());%></td>
                                        <%if (actual!= null) {%> 
                                        <%if (actual.getRol() == 2) {%>
                                        <td > <a href="asignarNotas?codigo=<%=c.getNum_grupo()%>" class="btn btn-outline-dark" name="asignarNotas" value="" method="POST" >Link al codigo del grupo 1(Asignar Notas) Modificar<a/></td>
                                        <%} else {%>
                                       
                                        <td > <a href="Matricularme?codigo=<%=c.getNum_grupo()%>" class="btn btn-outline-dark" name="matricularse" value="" method="POST" >Link al codigo del grupo 1(Matricularme) Modificar<a/></td>
                                        <%}%>
                                         <%}else{%>
                                          <td > <a href="loggin.jsp" class="btn btn-outline-dark" name="matricularNoLogin" value="" method="POST" >Link al codigo del grupo 1(Matricularme) Modificar<a/></td>
                                          <%}%>
                                    </tr>
                                     <%}%> 
                                  
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
