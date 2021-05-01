/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_index.ui;

import Controller_index.Model_index;
import Cursos.Logica.Curso;
import Data.Service.logic.Service;
import Login.ui.Controller_Login;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pgat3000
 */
@WebServlet(name = "Controller_index", urlPatterns = {"/InicioPrincipal", "/buscarCursos"})
public class Controller_index extends HttpServlet {

    String respuesta;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final Model_index model;

    public Controller_index() {
        this.model = new Model_index();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ruta = request.getServletPath();
        String respuesta = "";
        switch (ruta) {
            case "/InicioPrincipal": {
                response.setContentType("text/html;charset=UTF-8");
                respuesta = this.show(request);//Devuelve a la pestanna principal
                request.getRequestDispatcher(respuesta).forward(request, response);
                break;
            }
            case "/buscarCursos": {
                respuesta = "/index.jsp";
                String Filtro = request.getParameter("Filtro");
                String texto = request.getParameter("curso");
                switch (Filtro) {
                    case "1": {
                        List<Curso> cursos = Service.instance().read_filtrados_Nombre(texto);
                        model.setCursos(cursos);
                        request.setAttribute("Model_index", model);
                        break;
                    }
                    case "2": {
                        List<Curso> cursos = Service.instance().read_filtrados_Teamtica(texto);
                        model.setCursos(cursos);
                        request.setAttribute("Model_index", model);
                        break;
                    }
                    case "0": {
                        List<Curso> cursos = Service.instance().read_filtrados(texto);
                        model.setCursos(cursos);
                        request.setAttribute("Model_index", model);
                        break;
                    }
                    default:
                }
                request.getRequestDispatcher(respuesta).forward(request, response);
                break;
            }

        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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

    private String show(HttpServletRequest request) {
        List<Curso> cursos = null;
        try {
            cursos = Service.instance().cursos_ofrecidos();
        } catch (Exception ex) {
            cursos = new ArrayList<>();
        }
        model.setCursos(cursos);
        request.setAttribute("Model_index", model);
        return "/index.jsp";
    }
}
