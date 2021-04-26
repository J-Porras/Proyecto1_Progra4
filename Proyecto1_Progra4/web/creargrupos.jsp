<%-- 
    Document   : creargrupos
    Created on : 25/04/2021, 03:06:27 PM
    Author     : Usuario
--%>
<%@page import="Grupos.Logica.Grupo"%>
<%@page import="Cursos.Logica.Curso"%>
<%@page import="java.util.List"%>
<%@page import="Controller_Admin.Model_Admin"%>
<% Model_Admin model = (Model_Admin) request.getAttribute("Model_Admin");
    List<Usuarios> profesores = model.getProfesores();
    List<Curso> cursos = model.getCursos();
    List<Grupo> grupos = model.getGrupos();
    List<Grupo> grupostodos=model.getGrupostodos();
    System.out.println(profesores.size());
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Gestion de grupos</title>
    </head>
    <body>
        <%@include file="nav_bar.jsp"%>
        <div class="row justify-content-center">
            <h3 class="text-center">Ingresar nuevo grupo</h3>
        </div>
        <div class = "container p-3">
            <form action="GrupoRegistrado" class="form-check-inline" method="POST">
                <div class="row">
                    <div class="col-4 mb-3">
                        <label class="form-select-label" for="selectCurso"><b>Curso para creacion: </b></label>
                        <select class="form-select-sm" aria-label="default select example" id="selectCurso" name="selectCurso">
                            <option selected>Tematica</option>
                           <%for (Curso c : cursos) {%>
                            <option value="<%=c.getId()%>"><%out.print(c.getNombre());%></option>
                            <%}%>
                        
                        </select>
                    </div>
                    <div class="col-4 mb-3">
                        <label class="form-select-label" for="selectProfe"><b>Profesor a asignar: </b></label>
                        <select class="form-select-sm" aria-label="default select example" id="selectProfe" name="selectProfe">
                            <option selected>Profesor</option>
                              <%for (Usuarios c : profesores) {%>
                            <option value="<%=c.getId()%>"><%out.print(c.getNombre());%></option>
                             <%}%>
                        </select>
                    </div>
                    <div class="col-4 mb-3">
                        <label class="form-select-label" for="selectHora"><b>Horario:  </b></label>
                        <select class="form-select-sm" aria-label="default select example" id="selectHora" name="selectHora">
                            <option selected>Hora Inicial </option>
                            <%for (int i = 8; i <= 20; i++) {%>
                            <option value=<%=i%>><%out.print(i);%>:00</option>
                            <%}%>
                        </select>
                           <select class="form-select-sm" aria-label="default select example" id="selectHoraF" name="selectHoraF">
                            <option selected>Hora Final</option>
                            <%for (int i = 8; i <= 20; i++) {%>
                            <option value=<%=i%>><%out.print(i);%>:00</option>
                            <%}%>
                        </select>
                    </div>
                    <div class="col-12 mb-3 text-center">
                        <label class="form-check-label"><b>Dias a impartir clase: </b></label> 
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="Lunes" value="L" name="dias">
                            <label class="form-check-label" for="Lunes">Lunes(L)</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="Martes" value="M" name="dias">
                            <label class="form-check-label" for="Martes">Martes(M)</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="Miercoles" value="K" name="dias">
                            <label class="form-check-label" for="Miercoles">Miercoles(K)</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="Jueves" value="J" name="dias">
                            <label class="form-check-label" for="Jueves">Jueves(J)</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="Viernes" value="V" name="dias">
                            <label class="form-check-label" for="Viernes">Viernes(V)</label>
                        </div>
                    </div>
                    <div class="col">
                        <div class="p-t-10">
                            <button class="btn btn-primary rounded submit w-100" type="submit">Registrar nuevo grupo</button>
                        </div>
                    </div>
                </div>
            </form>
            <div class="row justify-content-center">
                <h3 class="text-center">Listado de grupos en sistema</h3>
            </div>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-7">
                        <div class="card-body">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Num.Grupo</th>
                                            <th scope="col">Curso</th>
                                            <th scope="col">Profesor titular</th>
                                            <th scope="col">Dias lectivos</th>
                                            <th scope="col">Horario(24h)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                      
                                        <%for (Grupo c : grupos) {%>
                                          <tr>
                                            <th scope="row"><%out.print(c.getNum_grupo());%></th>
                                            <td><%out.print(c.getId_curso());%></td>
                                            <td><%out.print(c.getProf_titular());%></td>v
                                            <td><%out.print(c.getDias());%></td>
                                            <td><%out.print(c.getHorario());%></td>
                                          </tr>
                                            <%}%>
                                    </tbody>
                                </table>
                          
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/bootstrap.bundle.js" type="text/javascript"></script>
    </body>
    <%@include file="footer.jsp"%>
</html>
