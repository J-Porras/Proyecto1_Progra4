/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_profe.ui;

import Data.Service.logic.Service;
import Grupos.Logica.Grupo;
import Login.Model_Login;
import Usuarios.logica.Usuarios;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Porras
 */
@WebServlet(name = "Controller_Profe", urlPatterns = {"/Controller_Profe", "/VerGrupos", "/NewNota", "/ProfesorRegistrado"})

public class Controller_Profe extends HttpServlet {

    private Service service;
    private Model_Login model;

    public Controller_Profe() {
        this.service = new Service();
        this.model = new Model_Login();
    }

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
        this.service = Service.instance();
        String solicitud = request.getServletPath();
        String respuesta = "";

        switch (solicitud) {
            case "/VerGrupos": {
                break;
            }
            case "/NewNota": {
                String id_grupo = request.getParameter("id_grupo");
                Grupo grupo_actual = service.getGrupo(id_grupo);
                String id_estudiante = request.getParameter("id_est");
                Usuarios est = service.getUsuario(id_estudiante);
                double nota_est = Double.parseDouble(request.getParameter("nota_est"));

                service.updateMatricula(est.getId(), grupo_actual.getId_curso(), nota_est);
                respuesta = "/cursosProfe";
                //no existe el url
                request.getRequestDispatcher(respuesta).forward(request, response);

                break;
            }
            case "/ProfesorRegistrado": {
                Map<String, String> errores = new HashMap<>();
                respuesta = "/CrearProfesores";
                String nombre = request.getParameter("nombre");
                String id = request.getParameter("id");
                String email = request.getParameter("email");
                String telefono = request.getParameter("telefono");
                String contrasenna = request.getParameter("contrasenna");
                String especialidad = request.getParameter("especialidad");
                System.out.println("crear");
                System.out.println(id);
                if (nombre.isEmpty() || id.isEmpty() || email.isEmpty() || telefono.isEmpty() || contrasenna.isEmpty() || especialidad.isEmpty()) {
                    System.out.println("vacio");
                    System.out.println("vacio");
                    errores.put("nombre", nombre);
                    errores.put("id", id);
                    errores.put("email", email);
                    errores.put("tel", telefono);
                    errores.put("contrasenna", contrasenna);
                    errores.put("especialidad", especialidad);
                    request.setAttribute("Error", errores);
                    request.getRequestDispatcher(respuesta).forward(request, response);
                    break;
                }

                Usuarios u = Service.instance().crear_usario(new Usuarios(id, nombre, contrasenna, telefono, email, 2, especialidad));

                //En el caso que el usuario ya exista
                request.getRequestDispatcher(respuesta).forward(request, response);

                break;
            }

            default: {
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

}
