/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Service.logic;

import Cursos.Logica.Curso;
import Database.CursosDao;
import Database.GruposDAO;
import Database.MatriculasDAO;
import Database.UsuariosDAO;
import Grupos.Logica.Grupo;
import Matriculas.Logic.Matricula;
import Usuarios.logica.Usuarios;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pgat3000
 */
public class Service {
    private final UsuariosDAO usuarioDao;
    private final CursosDao cursosdao;
    private final GruposDAO gruposdao;
    private final MatriculasDAO matriculasdao;
    
    private static Service theInstance;
    
    public static Service instance(){
        if(theInstance==null){
            theInstance = new Service();
        }
        return theInstance;
    }
    
    public Service(){
        usuarioDao = new UsuariosDAO();
        cursosdao= new CursosDao();
        gruposdao = new GruposDAO();
        matriculasdao= new MatriculasDAO();
    }
    //Jalar lista de curso de la base de datos 
    public List<Curso> lista_cursos() throws Exception{
        
        try{    
            return cursosdao.read_all_cursos();
        }
        catch(Exception e){
            return null;
        }    
    }
    
    //devuelve cursos en oferta NO USAR
    public List<Curso> cursos_ofrecidos() throws Exception{
        return cursosdao.read_cursos_oferta();   
    }
    
    
//crear nuevo matricula
   public Matricula  crear_matricula(Matricula u){
         Matricula result = null;
         try{
            result=matriculasdao.create(u);
        //    System.out.println(u.getId());
              return result;
         }
         catch(Exception e){
           return null ;//usuario ya existe 
         }
   
    }    
   
   public void updateMatricula(String id_est,int id_grupo,double nota){
        try {
            matriculasdao.update_calficacion(id_est,id_grupo , nota);
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   
//crear nuevp grupo
    public Grupo crear_grupo(Grupo u){
         Grupo result = null;
         try{
            result=gruposdao.create(u);
            //System.out.println(u.getId());
              return result;
         }
         catch(Exception e){
           return null ;//Grupo ya existe 
         }
   
    }   
//crear nuevo curso
     public Curso crear_curso(Curso u){
         Curso result = null;
         try{
            result=cursosdao.create(u);
            System.out.println(u.getId());
              return result;
         }
         catch(Exception e){
           return null ;//usuario ya existe 
         }
   
    }
    //crear usario nuevo en la base de datos
    public Usuarios crear_usario(Usuarios u){
         Usuarios result = null;
         try{
            result=usuarioDao.create(u);
            System.out.println(u.getId());
              return result;
         }
         catch(Exception e){
           return null ;//usuario ya existe 
         }
   
    }
    
    
    
    
    public Grupo getGrupo(String id_grupo){
        try {
            Grupo result = gruposdao.read(id_grupo);
            return result;
            
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    
    public Usuarios getUsuario(String id){
        Usuarios result = null;
        try {
            result = usuarioDao.read(id);
            if(result == null){
                throw new Exception();
            }
            else{
                return result;
            }
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return null;
    }
    
    
    
    public Usuarios login(Usuarios u) throws Exception{
        Usuarios result = null;
        
        result = usuarioDao.read(u.getId());
        System.out.println("Service tiene la culpa");
        if(result == null)
            throw new Exception("Usuario no encontrado");
               System.out.println("Contrassena de base de datos: "+result.getContrasenna());
        System.out.println("Contrassena de usuario: "+u.getContrasenna());
            if((result.getContrasenna()).equals(u.getContrasenna())){
               System.out.println("Correcto");  
            }
        if(!(result.getContrasenna()).equals(u.getContrasenna())){
            throw new Exception("Usuario con mala contrasenna");
        }
        
        return result;
    }
    //leer todos los profes
    public List<Usuarios> read_all_profesores(){
         try{    
            return usuarioDao.read_all_profes();
        }
        catch(Exception e){
            return null;
        } 
    }
        public List<Usuarios> read_all_usuarios(){
         try{    
            return usuarioDao.read_all();
        }
        catch(Exception e){
            return null;
        } 
    }
        public List<Grupo> read_all_grupos(){
         try{    
            return gruposdao.read_all_grupos();
        }
        catch(Exception e){  
            System.out.println("no sirvio");
            return null;
        } 
           
          
    }
    //falta solo de implementar en service, ya exise el metodo en dao respectivo
    //FALTA leer grupos por curso
    //FALTA LEER profesor por grupo
    //FALTAleer grupos por profe
    //FALTAgrupos por estudiante(historial)
    //FALTAEstudiantes por grupo
}
