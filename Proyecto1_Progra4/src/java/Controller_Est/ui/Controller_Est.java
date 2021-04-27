/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Est.ui;

import Controller_Est.Model_est;
import Cursos.Logica.Curso;
import Data.Service.logic.Service;
import Grupos.Logica.Grupo;
import Matriculas.Logic.Matricula;
import Usuarios.logica.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Usuario
 */
@WebServlet(name = "Controller_Est", urlPatterns = {"/MisCursos","/HistorialPDF"})
public class Controller_Est extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Model_est model;
    public Controller_Est(){
        model= new Model_est();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String ruta = request.getServletPath();
        String respuesta = "";
        switch (ruta) {
            case "/MisCursos":
                  {
                        System.out.println("Process request");
                 HttpSession session = request.getSession(true);
                  
                Usuarios actual = (Usuarios) session.getAttribute("Usuario");
                respuesta = "historial_Est.jsp";
                System.out.println("Despues de service");
                List<Matricula> cursos_est = Service.instance().read_grupos_estudiante(actual.getId());
                List<Grupo> grupos = Service.instance().read_all_grupos();
                List<Curso> cursos = Service.instance().lista_cursos();
                
                model.setEstudiantes_matricula(cursos_est);
                
                model.setTodos_grupos(grupos);
                model.setTodos_cursos(cursos);
                
                System.out.println(cursos_est.size());
                request.setAttribute("Model_est", model);
                respuesta = "historial_Est.jsp";
				break;
				  
            }
            case "/HistorialPDF":
                respuesta = "historial_Est.jsp";
                break;
            default:
                respuesta = "";
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
            Logger.getLogger(Controller_Est.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Controller_Est.class.getName()).log(Level.SEVERE, null, ex);
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
