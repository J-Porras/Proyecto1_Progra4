/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Est.ui;



import Controller_Est.Model_est;
import Cursos.Logica.Curso;
import Data.Service.logic.Service;
import Grupos.Logica.Grupo;
import Matriculas.Logic.Matricula;
import Usuarios.logica.Usuarios;
import com.itextpdf.io.font.constants.StandardFonts;


import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import com.itextpdf.kernel.geom.PageSize;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell; 
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "Controller_Est", urlPatterns = {"/MisCursos","/HistorialPDF"})
public class Controller_Est extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Model_est model;
    public Controller_Est(){
        model= new Model_est();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String ruta = request.getServletPath();
        String respuesta = "";
        switch (ruta) {
            case "/MisCursos":{
                System.out.println("Process request");
                HttpSession session = request.getSession(true);
                  
                Usuarios actual = (Usuarios) session.getAttribute("Usuario");
                respuesta = "historial_Est.jsp";
                System.out.println("Despues de service");
                List<Matricula> cursos_est = Service.instance().read_grupos_estudiante(actual.getId());
                List<Grupo> grupos = Service.instance().read_all_grupos();
                List<Curso> cursos = Service.instance().lista_cursos();
                
                model.setEstudiantes_matricula(cursos_est);
                
                model.setTodos_grupos(grupos);
                model.setTodos_cursos(cursos);
                
                System.out.println(cursos_est.size());
                request.setAttribute("Model_est", model);
                respuesta = "historial_Est.jsp";
		break;
				  
            }
            case "/HistorialPDF":
                System.out.println("CASE HISTORIAL PDF");
                respuesta = this.createPDF(request, response);
                System.out.println("RESPUESTA: " + respuesta);
                break;
                
                
            default:
                respuesta = "";
                break;
        }
        if(respuesta!=null){
            request.getRequestDispatcher(respuesta).forward( request, response);
        }
    }
    
    //genera el historial en PDF
    private String createPDF(HttpServletRequest request,  HttpServletResponse response){
        try {
            System.out.println("TRY HISTORIAL PDF");
            List<Matricula> cursos_est =  model.getEstudiantes_matricula();
            List<Grupo> grupos = model.getTodos_grupos();
            List<Curso> cursos = model.getTodos_cursos();
            List<Usuarios> profesores =Service.instance().read_all_profesores();
            HttpSession session = request.getSession(true);
            
            System.out.println("LINEA 115");
                 String pdfFilename= "constancia.pdf";   
            Usuarios actual = (Usuarios) session.getAttribute("Usuario");
             System.out.println("LINEA 118");
            //se crea un nuevo pdf y se guarda en response
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfDocument pdf = new PdfDocument(new PdfWriter(baos));  
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            System.out.println("after pdf writer");
           
            Document doc = new Document(pdf);
            
            System.out.println("LINEA 123");
            
            
            
            //codigo estanadar
            doc.add(new Paragraph("HISTORIAL DE ESTUDIANTE: CursoLibres.com " ) );
            doc.add(new Paragraph("=============================================="));
            doc.add(new Paragraph("Nombre del Estudiante: " + actual.getNombre()));
            doc.add(new Paragraph("=============================================="));
            doc.add(new Paragraph("ID: " + actual.getId()));
            doc.add(new Paragraph("=============================================="));
            
            
            //parrafo de com.itextpdf.text.Paragraph
                 System.out.println("ANTES  TABLE  PDF");
            
            //generando tabla de cursos
            //codigo de -> https://www.tutorialspoint.com/itext/itext_adding_table.htm
            //https://www.tutorialspoint.com/itext/itext_adding_image_to_pdf.htm
            
            float [] pointColumnWidths = {150F, 150F, 150F,150F};   
            Table table = new Table(pointColumnWidths);
            
            //table.addCell(new Cell().add(new Paragraph("IMG"))); 
            table.addCell(new Cell().add(new Paragraph("CURSO")));       
            table.addCell(new Cell().add(new Paragraph("ID GRUPO"))); 
            table.addCell(new Cell().add(new Paragraph("PROFESOR"))); 
            table.addCell(new Cell().add(new Paragraph("NOTA"))); 
            
            
            System.out.println("FIN INIT TABLE  PDF");

            ////////////////////////////////////////
            //añadiendo informacion de cada curso
            for(Matricula m : cursos_est){
                String nom_profe = "-";
                String nombre_curso ="-";
                //ImageData data = ImageDataFactory.create("C:/images/"+m.getId_grupo());
                
                for(Grupo g: grupos){//itera por cada grupo de la DB
                    
                    if(g.getNum_grupo()==m.getId_grupo()){
                        //si encuentra el nombre de profe y es el mismo en la 
                        //matricula lo añade
                        nom_profe =g.getProf_titular();

                        for(Curso u: cursos)
                        {
                            //itera por los cursos hasta buscar el curso al que
                            //pertenece el grupo
                            if(u.getId() == g.getId_curso()){
                                nombre_curso =u.getNombre();
                                break;
                            }
                        }
                        break;
                    }
                }
                System.out.println("sacando IMAGEN DE PDF ");
               // ImageData image_data = ImageDataFactory.create("C:/images/"+m.getId_grupo());
                //Image image_curso = new Image(image_data);
                
                //table.addCell(image_curso.setAutoScale(true));
                //deberia reescalar la imagen
                
                table.addCell(nombre_curso);//nombre de grupo
                table.addCell(Integer.toString(m.getId_grupo()));//id grupo
                for(Usuarios c:profesores){
                    if(nom_profe.equals(c.getId()));
                    {
                        nom_profe=c.getNombre();
                        break;
                    }
                }
                table.addCell(nom_profe);//nombre del profe
                table.addCell(Double.toString(m.getCalificacion()));
                
            }//fin ciclo matricula
            
           
           doc.add(table);
            doc.close();
           // setting some response headers
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        // setting the content type
        response.setContentType("application/pdf");
        // the contentlength
        response.setContentLength(baos.size());
        // write ByteArrayOutputStream to the ServletOutputStream
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();
        
            System.out.println("FIN CREATE PDF");

        }//fin try
        
        catch (IOException ex) {
            Logger.getLogger(Controller_Est.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Controller_Est.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Controller_Est.class.getName()).log(Level.SEVERE, null, ex);
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
