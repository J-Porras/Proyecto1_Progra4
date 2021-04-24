/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_index.ui;

import Cursos.Logica.Curso;
import Data.Service.logic.Service;
import Login.Model_Login;
import Login.ui.Controller_Login;
import Usuarios.logica.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author pgat3000
 */
@WebServlet(name = "Controller_index", urlPatterns = {"/InicioPrincipal"})
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
    
       private Service service;
    private Controller_Login controller_login;

        public Controller_index() {
        this.service = new Service();
        
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        respuesta = this.show(request);//Devuelve a la pestanna principal
                System.out.println("RESPUESTA SHOW");  
                
               
                System.out.println("RESPONDE SEND REDIRECT");  
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
        Curso curso = new Curso(0,"","",false,0.0);
        request.setAttribute("curso", curso);
        try {
            request.setAttribute("cursos", Service.instance().cursos_ofrecidos());
        } catch (Exception ex) {
            Logger.getLogger(Controller_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("RETURN EN SHOW");  
        return "/index.jsp";
    }
}