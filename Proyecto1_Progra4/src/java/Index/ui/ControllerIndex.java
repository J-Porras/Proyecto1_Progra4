/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Cursos.Logica.Curso;
import Data.Service.logic.Service;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import Cursos.Logica;

/**
 *
 * @author Porras
 */
@WebServlet(name = "ControllerIndex", urlPatterns = {"/index"})
@MultipartConfig(location="C:/images")
public class ControllerIndex extends HttpServlet {

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
            throws ServletException, IOException 
    {
        String urlresponse = "";
        
        switch (request.getServletPath()) {
            case "/index":
                urlresponse = this.show(request);
                
                break;
            default:
                break;
        }
        if(urlresponse!=null){
            request.getRequestDispatcher(urlresponse).forward( request, response);
        }

    }
    
    
    //recupera la imagen del curso de la raiz de la pc
    private String getImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
            String codigo = request.getParameter("codigo");
            Path path = FileSystems.getDefault().getPath("C:/Images",codigo);
            try (OutputStream out = response.getOutputStream()) {
                Files.copy(path,out);
                out.flush();
            
        } catch (Exception e) {
        }
        return null;
            
    }
    
    //se llama la primera vez 
    private String show(HttpServletRequest request) {     
        Curso curso = new Curso(0,"","",false,0.0);
        request.setAttribute("curso", curso);
        try {
            request.setAttribute("cursos", Service.instance().cursos_descuento());
        } catch (Exception ex) {
            Logger.getLogger(ControllerIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
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
