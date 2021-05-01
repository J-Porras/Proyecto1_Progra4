/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Cursos.ui;

import Cursos.Logica.Curso;
import Data.Service.logic.Service;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "ServletCursos", urlPatterns = {"/cursos", "/CursoRegistrado", "/images/image", "/CambioEstado"})
@MultipartConfig(location = "C:/images")
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
        Map<String, String> errores = new HashMap<>();
        switch (ruta) {
            case "/CursoRegistrado": {
                final Part image;
                String respuesta = "/CrearCursos";
                String nombre = request.getParameter("nombre");
                String tematica = request.getParameter("tematica");
                Boolean estado = request.getParameter("oferta") != null;
                String precio = request.getParameter("precio");
                image = request.getPart("archivo");
                int id_new_curso = Service.instance().lista_cursos().size() + 1;
                double Precio = 0;
                try {
                    Precio = Double.parseDouble(precio);
                } catch (NumberFormatException e) {
                    errores.put("precio", precio);
                }
                if (nombre.isEmpty() || tematica.isEmpty() || precio.isEmpty() || image.getSize() == 0) {
                    errores.put("nombre", nombre.isEmpty() ? "" : nombre);
                    errores.put("tematica", tematica.isEmpty() ? "" : tematica);
                    errores.put("precio", precio.isEmpty() ? "" : precio);
                    request.setAttribute("Error", errores);
                } else {
                    image.write(Integer.toString(id_new_curso));
                    Service.instance().crear_curso(new Curso(0, nombre, tematica, estado, Precio));
                }
                break;
            }

            case "/images/image": {
                this.image(request, response);
                break;
            }
            case "/CambioEstado": {
                String respuesta = "/CrearCursos";
                System.out.println(request.getParameter("id"));
                String id_curso = request.getParameter("id");
                Service.instance().updateEnOferta(Integer.parseInt(id_curso));
                request.getRequestDispatcher(respuesta).forward(request, response);
                break;
            }
            default:
                break;
        }

    }

    private String image(HttpServletRequest request, HttpServletResponse response) {
        String codigo = request.getParameter("codigo");
        Path path = FileSystems.getDefault().getPath("C:/images", codigo);
        try (OutputStream out = response.getOutputStream()) {
            Files.copy(path, out);
            out.flush();
        } catch (IOException e) {
            // handle exception
        }
        return null;
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
            System.out.println("Error al llamar al proces request");
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
