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
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "Controller_Login", urlPatterns = {"/IniciarSesion", "/CerrarSesion", "/Login", "/Inicio", "/Registrarse", "/RegistroCompleto"})
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Model_Login getModel() {
        return model;
    }

    public void setModel(Model_Login model) {
        this.model = model;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        this.service = Service.instance();
        String solicitud = request.getServletPath();
        String respuesta = "";
        Map<String, String> errores = new HashMap<>();
        switch (solicitud) {

            case ("/IniciarSesion"): {
                String contrasenna = request.getParameter("contrasenna");
                String id_usuario = request.getParameter("id");
                try {
                    Usuarios u = Service.instance().login(new Usuarios(id_usuario, "", contrasenna, "", "", 0, ""));
                    model.setCurrent_user(u);
                    HttpSession session = request.getSession(true);
                    request.setAttribute("Model_Login", model);//No se debe mandar el modelo, ya que es una instancia
                    //a sesión general debe ser manejada por el objeto httpsession debido a que es el cookie quien va a identificar
                    session.setAttribute("Usuario", u);
                    respuesta = "/InicioPrincipal";
                } catch (Exception e) {//Excepción de datos erroneos o inválidos
                    errores.put("id", id_usuario);
                    errores.put("contrasenna", contrasenna);
                    request.setAttribute("Error", errores);
                    respuesta = "loggin.jsp";
                }
                request.getRequestDispatcher(respuesta).forward(request, response);
                break;
            }
            case ("/CerrarSesion"): {
                respuesta = "/InicioPrincipal";
                HttpSession session = request.getSession(true);
                session.removeAttribute("Usuario");
                model.setCurrent_user(null);
                System.out.println("REQUEST CERRAR SESION");
                request.getRequestDispatcher(respuesta).forward(request, response);
                break;
            }

            case ("/Registrarse"): {
                respuesta = "registrarse.jsp";
                request.getRequestDispatcher(respuesta).forward(request, response);
                break;
            }

            case ("/RegistroCompleto"): {
                respuesta = "/InicioPrincipal";
                String respuestaError = "registrarse.jsp";
                String nombre = request.getParameter("nombre");
                String id = request.getParameter("id");
                String email = request.getParameter("email");
                String telefono = request.getParameter("telefono");
                String contrasenna = request.getParameter("contrasenna");
                System.out.println("crear");
                System.out.println(id);

                if (nombre.isEmpty() || id.isEmpty() || email.isEmpty() || telefono.isEmpty() || contrasenna.isEmpty()) {
                    System.out.println("vacio");
                    errores.put("nombre", nombre);
                    errores.put("id", id);
                    errores.put("email", email);
                    errores.put("tel", telefono);
                    errores.put("contrasenna", contrasenna);
                    request.setAttribute("Error", errores);
                    respuesta = "Registrarse";
                    request.getRequestDispatcher(respuestaError).forward(request, response);
                    break;
                }

                Usuarios u = Service.instance().crear_usario(new Usuarios(id, nombre, contrasenna, telefono, email, 3, null));

                if (u == null) {
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
                request.getRequestDispatcher("loggin.jsp").forward(request, response);
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
