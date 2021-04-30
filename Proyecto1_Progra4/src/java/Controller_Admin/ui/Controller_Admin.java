/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller_Admin.Model_Admin;
import Cursos.Logica.Curso;
import Data.Service.logic.Service;
import Grupos.Logica.Grupo;
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

/**
 *
 * @author pg300
 */
@WebServlet(name = "Controller_Admin", urlPatterns = {"/Administrador", "/CrearCursos", "/CrearProfesores", "/ListaUsuarios", "/CrearGrupos","/buscarUsuarios","/buscarProfesor","/buscarCursosA"})
public class Controller_Admin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Model_Admin model;

    public Controller_Admin() {
        model = new Model_Admin();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String ruta = request.getServletPath();
        switch (ruta) {
            case "/CrearCursos": {//Por el momento solo va la pestanna
                List<Curso> cursos = Service.instance().lista_cursos();
                model.setCursos(cursos);
                System.out.println("Controller");
                System.out.println(cursos.size());
                request.setAttribute("Model_Admin", model);
                request.getRequestDispatcher("crearcursos.jsp").forward(request, response);
                break;
            }
            case "/CrearProfesores": {
                List<Usuarios> profesores = Service.instance().read_all_profesores();
                model.setProfesores(profesores);
                System.out.println(profesores.size());
                request.setAttribute("Model_Admin", model);
                request.getRequestDispatcher("registrarse.jsp").forward(request, response);
                break;
            }
            case "/ListaUsuarios": {
                List<Usuarios> usuarios = Service.instance().read_all_usuarios();
                model.setUsuarios(usuarios);
                
                System.out.println(usuarios.size());
                request.setAttribute("Model_Admin", model);
                request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                break;
            }
            case "/CrearGrupos": {
                List<Curso> cursos = Service.instance().lista_cursos();
                model.setCursos(cursos);
                List<Usuarios> profesores = Service.instance().read_all_profesores();
                model.setProfesores(profesores);
                int id= Integer.parseInt(request.getParameter("id"));
                List<Grupo> grupos= Service.instance().read_grupos_curso(id);
                List<Grupo> grupostodos=Service.instance().read_all_grupos();
                Curso grupoC = Service.instance().getCurso(id);
                model.setGrupostodos(grupostodos);
                model.setGrupos(grupos);
                model.setCurso(grupoC);
                System.out.println(grupos.size());
                request.setAttribute("Model_Admin", model);
                request.removeAttribute("id");
                request.getRequestDispatcher("creargrupos.jsp").forward(request, response);
                break;
            }
            case "/buscarUsuarios":{
                String texto = request.getParameter("Usuario");
                 List<Usuarios> usuarios = Service.instance().read_filtradosU(texto);
                model.setUsuarios(usuarios);
                
                System.out.println(usuarios.size());
                request.setAttribute("Model_Admin", model);
                request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                break;
            }
            case "/buscarProfesor":
            {
                  String texto = request.getParameter("profesor");
                List<Usuarios> profesores = Service.instance().read_filtradosP(texto);
                model.setProfesores(profesores);
                System.out.println(profesores.size());
                request.setAttribute("Model_Admin", model);
                request.getRequestDispatcher("registrarse.jsp").forward(request, response);
                break;
             
            }
            case "/buscarCursosA" :{
                System.out.print("FILTRO");
                System.out.print(request.getParameter("Filtro"));
                String Filtro = request.getParameter("Filtro");
                String texto = request.getParameter("curso");
                   switch(Filtro){
                    case "1":{
                        List<Curso> cursos = Service.instance().read_filtrados_Nombre(texto);
                        model.setCursos(cursos);
                        System.out.println("Controller");
                        System.out.println(cursos.size());
                        request.setAttribute("Model_Admin", model);
                        request.getRequestDispatcher("crearcursos.jsp").forward(request, response);
                break;
                    }
                    case "2":{
                      List<Curso> cursos = Service.instance().read_filtrados_Teamtica(texto);
                        model.setCursos(cursos);
                        System.out.println("Controller");
                        System.out.println(cursos.size());
                        request.setAttribute("Model_Admin", model);
                        request.getRequestDispatcher("crearcursos.jsp").forward(request, response);
                        break;
                    }
                    case "0":{
                       List<Curso> cursos = Service.instance().read_filtrados(texto);
                        model.setCursos(cursos);
                        System.out.println("Controller");
                        System.out.println(cursos.size());
                        request.setAttribute("Model_Admin", model);
                        request.getRequestDispatcher("crearcursos.jsp").forward(request, response);
                    }
                    default:
                }
                break;
            }
            default:
                break;
        }
        //basura
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
            Logger.getLogger(Controller_Admin.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Controller_Admin.class.getName()).log(Level.SEVERE, null, ex);
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
