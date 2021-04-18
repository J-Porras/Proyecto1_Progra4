/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.logic;

import Database.CursosDao;
import Database.UsuariosDAO;
import Usuarios.logica.Usuarios;

/**
 *
 * @author pgat3000
 */
public class Service {
    private UsuariosDAO usuarioDao;
    private CursosDao cursosdao;
    public static Service theInstance;
    
    public static Service instance(){
        if(theInstance==null){
            theInstance = new Service();
        }
        return theInstance;
    }
    
    public Service(){
        usuarioDao = new UsuariosDAO();
    }
    
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
    
    
    
}
