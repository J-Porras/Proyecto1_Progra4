/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Matricula.ui;

import Data.Service.logic.Service;
import Matriculas.Logic.Matricula;
import Usuarios.logica.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "Controller_Matricula", urlPatterns = {"/Matricularme"})
public class Controller_Matricula extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ruta = request.getServletPath();
        String respuesta = "";
          switch (ruta) {
            case "/Matricularme": {
                respuesta ="/InicioPrincipal";
                HttpSession session = request.getSession(true);
                  
                Usuarios actual = (Usuarios) session.getAttribute("Usuario");
                String id_est= actual.getId();
                int num_grupo = Integer.parseInt(request.getParameter("codigo"));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
                LocalDateTime now = LocalDateTime.now();  
                String date =dtf.format(now).toString();
                Matricula u =Service.instance().crear_matricula(new Matricula(num_grupo,id_est,date,0.0));
                if(u==null){
                    System.out.println("Controller_Matriculas:No sirvio la matrocula o ya exite");
                    
                }
                break;
            }
            default:{
                
            }
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

}
