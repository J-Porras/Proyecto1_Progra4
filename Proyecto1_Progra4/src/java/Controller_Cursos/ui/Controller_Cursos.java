/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Cursos.ui;

import Cursos.Logica.Curso;
import Data.Service.logic.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "ServletCursos", urlPatterns = {"/cursos","/CursoRegistrado"})
@MultipartConfig(location="C:/images")
public class Controller_Cursos extends HttpServlet {

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
            throws ServletException, IOException, Exception {
    response.setContentType("text/html;charset=UTF-8");
    String ruta = request.getServletPath();
        switch(ruta){
            case "/CursoRegistrado":{//Por el momento solo va la pestanna
                final Part image;
                String respuesta="/CrearCursos";
                
                System.out.println("Registrado");
                String nombre = request.getParameter("nombre");
                String tematica = request.getParameter("tematica");
                System.out.println(request.getParameter("oferta"));
             
                Boolean estado;
                if(request.getParameter("oferta")!=null){
                    estado =true;
                }
                else{
                    estado=false;
                }
                
               
                System.out.println(estado);
                Double precio = Double.parseDouble(request.getParameter("precio"));
                int id_new_curso=Service.instance().lista_cursos().size() + 1 ;
                
                if(nombre != null && tematica != null && precio != null){   
                    System.out.println(" correctoasdadasdasdasdasdasdas");
                    
                    image = request.getPart("archivo");
                    if(image!=null)
                        System.out.println(" Imagen procesada");
                    else{
                        System.out.println(" RIP IMAGE");
                    }
                    image.write(Integer.toString(id_new_curso));
                    
                    Service.instance().crear_curso(new Curso(0,nombre,tematica,estado,precio));
                    request.getRequestDispatcher(respuesta).forward(request, response);
                }
                else{
                     System.out.println(" RIP ------------");
                }
                
                
                break;
            }
            default:
                break;
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
            Logger.getLogger(Controller_Cursos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Controller_Cursos.class.getName()).log(Level.SEVERE, null, ex);
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
