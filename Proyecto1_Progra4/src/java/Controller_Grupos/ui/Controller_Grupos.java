/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Grupos.ui;

import Data.Service.logic.Service;
import Grupos.Logica.Grupo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pg300
 */
@WebServlet(name = "Controller_Grupos", urlPatterns = {"/GrupoRegistrado"})
public class Controller_Grupos extends HttpServlet {

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
      
        String ruta = request.getServletPath();
        switch (ruta) {
            case "/GrupoRegistrado": {
                String respuesta = "/CrearGrupos";
            String curso = request.getParameter("selectCurso");
            String profe = request.getParameter("selectProfe");
            String Hora = request.getParameter("selectHora");
            String HoraF = request.getParameter("selectHoraF");
            if(curso.equals("Tematica")||profe.equals("Profesor")||Hora.equals("Hora Incial")||HoraF.equals("Hora Final")){
                    System.out.println("vacio");
                    request.getRequestDispatcher(respuesta).forward(request, response);
                    break;
                }
            if(HoraF.equals(Hora)){
                request.getRequestDispatcher(respuesta).forward(request, response);  
                break;
            }
            Hora= Hora+":00";
            HoraF=HoraF+":00";
            String HoraD=Hora+"-"+HoraF;
            String[] dias = request.getParameterValues("dias");
           
            if(dias==null){
                  request.getRequestDispatcher(respuesta).forward(request, response);  
                break;
            } 
            List<String> dias_lista = Arrays.asList(dias);
              System.out.println("Controller_Grupos");
            System.out.println(dias_lista.toString());
            
            String diasListo=" ";
            for(int i =0;i<dias_lista.size();i++){
                if(i!=(dias_lista.size()-1)){
                diasListo=diasListo+dias_lista.get(i)+"-";
                }
                else{
                    diasListo=diasListo+dias_lista.get(i); 
                }
            }
            System.out.println(diasListo);
            
             Grupo u= Service.instance().crear_grupo(new Grupo(0,Integer.parseInt(curso),profe,diasListo,HoraD));
              if(u==null){
                request.getRequestDispatcher(respuesta).forward(request, response);  
                break;
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

}
