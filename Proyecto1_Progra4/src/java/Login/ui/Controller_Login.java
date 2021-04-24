/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login.ui;

import Cursos.Logica.Curso;
import Data.Service.logic.Service;
import Login.Model_Login;
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
@WebServlet(name = "Controller_Login", urlPatterns = {"/IniciarSesion", "/CerrarSesion", "/Perfil", "/Inicio", "/Registrarse","/RegistroCompleto"})
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
                break;
            }
            case ("/CerrarSesion"): {
                respuesta = this.show(request);
                HttpSession session = request.getSession(true);
                session.removeAttribute("Usuario");
                model.setCurrent_user(null);
                System.out.println("REQUEST CERRAR SESION"); 
                request.getRequestDispatcher(respuesta).forward(request, response);
                break;
            }
            
            case ("/Inicio"): {
                respuesta = this.show(request);//Devuelve a la pestanna principal
                System.out.println("RESPUESTA SHOW");  
                HttpSession session = request.getSession(true);
                Usuarios u = model.getCurrent_user();
                if (u != null) {
                    
                    session.setAttribute("Usuario", u);
                }
                System.out.println("RESPONDE SEND REDIRECT");  
                //response.sendRedirect(respuesta);
                request.getRequestDispatcher(respuesta).forward(request, response);
                
                break;
            }
            case ("/Registrarse"): {
                respuesta = "registrarse.jsp";
                request.getRequestDispatcher(respuesta).forward(request, response);
                break;
            }
            
            case ("/RegistroCompleto"): {
                respuesta = "."; 
                String respuestaError = "registrarse.jsp";
                String nombre = request.getParameter("nombre");
                String id= request.getParameter("id");
                String email = request.getParameter("email");
                String telefono = request.getParameter("telefono");
                String contrasenna = request.getParameter("contrasenna");
                System.out.println("crear");
                System.out.println(id);
                
                if(nombre.isEmpty()||id.isEmpty()||email.isEmpty()||telefono.isEmpty()||contrasenna.isEmpty()){
                    System.out.println("vacio");
                    request.getRequestDispatcher(respuestaError).forward(request, response);
                    break;
                }
                
                Usuarios u= Service.instance().crear_usario(new Usuarios(id,nombre, contrasenna, telefono,email,0, " "));
                  
                if(u==null){
                     //En el caso que el usuario ya exista
                   request.getRequestDispatcher(respuestaError).forward(request, response); 
                }
                 
                model.setCurrent_user(u);
                HttpSession session = request.getSession(true);
                request.setAttribute("Model_Login", model); 
                session.setAttribute("Usuario", u);
                System.out.println("crear2");

             //   request.getRequestDispatcher(respuesta).forward(request, response);
                request.getRequestDispatcher(respuesta).forward(request, response);
                break;
            }
            default: {
                break;
            }

        }
    }
    
    private String show(HttpServletRequest request) {     
        Curso curso = new Curso(0,"","",false,0.0);
        request.setAttribute("curso", curso);
        try {
            request.setAttribute("cursos", Service.instance().cursos_descuento());
        } catch (Exception ex) {
            Logger.getLogger(Controller_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("RETURN EN SHOW");  
        return "/index.jsp";
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
