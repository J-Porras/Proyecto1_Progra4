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
import java.util.List;

/**
 *
 * @author pgat3000
 */
public class Service {
    private UsuariosDAO usuarioDao;
    private CursosDao cursosdao;
    private GruposDAO gruposdao;
    private MatriculasDAO matriculasdao;
    
    public static Service theInstance;
    
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
    
    //falta solo de implementar en service, ya exise el metodo en dao respectivo
    //FALTA leer grupos por curso
    //FALTA LEER profesor por grupo
    //FALTAleer grupos por profe
    //FALTAgrupos por estudiante(historial)
    //FALTAEstudiantes por grupo
}
