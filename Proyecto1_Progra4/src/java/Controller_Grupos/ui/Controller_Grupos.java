/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Grupos.ui;

import Controller_Grupos.Model_Grupos;
import Cursos.Logica.Curso;
import Data.Service.logic.Service;
import Grupos.Logica.Grupo;
import Matriculas.Logic.Matricula;
import Usuarios.logica.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pg300
 */
@WebServlet(name = "Controller_Grupos", urlPatterns = {"/GrupoRegistrado", "/GruposSistema", "/MisGrupos","/asignarNotas"})
public class Controller_Grupos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Model_Grupos model;
    public Controller_Grupos() {
        model = new Model_Grupos();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        String ruta = request.getServletPath();
        String respuesta = "";
        switch (ruta) {
            case "/GrupoRegistrado": {
                respuesta = "/CrearGrupos";
                String curso = request.getParameter("selectCurso");
                String profe = request.getParameter("selectProfe");
                String Hora = request.getParameter("selectHora");
                String HoraF = request.getParameter("selectHoraF");
                if (curso.equals("Tematica") || profe.equals("Profesor") || Hora.equals("Hora Incial") || HoraF.equals("Hora Final")) {
                    System.out.println("vacio");
                    request.getRequestDispatcher(respuesta).forward(request, response);
                    break;
                }
                if (HoraF.equals(Hora)) {
                    request.getRequestDispatcher(respuesta).forward(request, response);
                    break;
                }
                Hora = Hora + ":00";
                HoraF = HoraF + ":00";
                String HoraD = Hora + "-" + HoraF;
                String[] dias = request.getParameterValues("dias");

                if (dias == null) {
                    request.getRequestDispatcher(respuesta).forward(request, response);
                    break;
                }
                List<String> dias_lista = Arrays.asList(dias);
                System.out.println("Controller_Grupos");
                System.out.println(dias_lista.toString());

                String diasListo = " ";
                for (int i = 0; i < dias_lista.size(); i++) {
                    if (i != (dias_lista.size() - 1)) {
                        diasListo = diasListo + dias_lista.get(i) + "-";
                    } else {
                        diasListo = diasListo + dias_lista.get(i);
                    }
                }
                System.out.println(diasListo);

                Grupo u = Service.instance().crear_grupo(new Grupo(0, Integer.parseInt(curso), profe, diasListo, HoraD));
                if (u == null) {
                    request.getRequestDispatcher(respuesta).forward(request, response);
                    break;
                }
                respuesta = respuesta + "?id=" + curso;
                request.getRequestDispatcher(respuesta).forward(request, response);
                break;
            }
            case "/GruposSistema": {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                List<Grupo> grupos = Service.instance().read_grupos_curso(codigo);
                List<Usuarios> profesor = Service.instance().read_all_profesores();
                List<Curso> cursos = Service.instance().lista_cursos();
                model.setCursos(cursos);
                model.setPosibleProfesor(profesor);
                model.setGrupos(grupos);
                request.setAttribute("Model_Grupos", model);;
                respuesta = "grupos.jsp";
//Hacer acá toda la lógica relacionada para mostrar los grupos de un curso x, revisar los parametros del encabezado
break;
            }
            case "/asignarNotas":{
                int num_grupo = Integer.parseInt(request.getParameter("codigo"));
                List<Matricula> matriculaEst =Service.instance().read_estudiantes_profesor(num_grupo);
                model.setEstudiantes_profe(matriculaEst);
                
                request.setAttribute("Model_Grupos", model);;
                System.out.println(matriculaEst.size());
//Revisar las matriculas del número de grupo en la base de datos, en base al número de grupo
           respuesta = "estudiantesgrupo.jsp";
           break;
            }
            case "/MisGrupos" :{
                System.out.println("Process request");
                HttpSession session = request.getSession(true);
                 List<Curso> cursos = Service.instance().lista_cursos(); 
                Usuarios actual = (Usuarios) session.getAttribute("Usuario");
                List<Grupo> grupos = Service.instance().read_grupos_profesor(actual.getId());
                model.setGrupos(grupos);
                model.setCursos(cursos);
                request.setAttribute("Model_Grupos", model);
                  System.out.println("Process request2");
                respuesta = "grupos.jsp";
            break;
            }
            
            default:
                respuesta = "grupos.jsp";
//Hacer acá toda la lógica relacionada para mostrar los grupos de un profesor, revisar el session para sacar el id del profesor
                
                break;
        }
        request.getRequestDispatcher(respuesta).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller_Grupos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller_Grupos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
