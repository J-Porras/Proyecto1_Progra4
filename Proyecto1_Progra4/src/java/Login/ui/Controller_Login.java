/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login.ui;

import Login.Model_Login;
import Service.logic.Service;
import Usuarios.logica.Usuarios;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Porras
 */
@WebServlet(name = "Controller_Login", urlPatterns = {"/IniciarSesion", "/CerrarSesion", "/Perfil", "/Inicio"})
public class Controller_Login extends javax.servlet.http.HttpServlet {

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
    private Model_Login model;

    public Controller_Login() {
        this.service = new Service();
        this.model = new Model_Login();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        this.service = Service.instance();
        this.model = new Model_Login();
        String solicitud = request.getServletPath();
        String respuesta = "";
        switch (solicitud) {
            case ("/IniciarSesion"): {
                respuesta = "Presentation/perfil/perfil.jsp";
                String contrasenna = request.getParameter("contrasenna");
                String id_usuario = request.getParameter("id");
                Usuarios u = Service.instance().login(new Usuarios(id_usuario, "", contrasenna, "", "", 0, ""));
                model.setCurrent_user(u);
                HttpSession session = request.getSession(true);
                request.setAttribute("Model_Login", model);//No se debe mandar el modelo, ya que es una instancia
                //a sesión general debe ser manejada por el objeto httpsession debido a que es el cookie quien va a identificar
                session.setAttribute("Usuario", u);
                request.getRequestDispatcher(respuesta).forward(request, response);
            }
            case ("/CerrarSesion"): {
                respuesta = ".";//Por el momento se devuelve a la pestanna principal
                HttpSession session = request.getSession(true);
                session.removeAttribute("Usuario");
                request.getRequestDispatcher(respuesta).forward(request, response);
            }
            case ("/Inicio"): {
                respuesta = "index.jsp";//Por el momento se devuelve a la pestanna principal
                HttpSession session = request.getSession(true);
                Usuarios u = model.getCurrent_user();
                if(u != null){
                    session.setAttribute("Usuario", u);
                }
                request.getRequestDispatcher(respuesta).forward(request, response);
            }
            default: {
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller_Login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Controller_Login.class.getName()).log(Level.SEVERE, null, ex);
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
