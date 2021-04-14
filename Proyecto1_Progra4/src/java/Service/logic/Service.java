/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.logic;

import Database.UsuariosDAO;
import Usuarios.logica.Usuarios;

/**
 *
 * @author pgat3000
 */
public class Service {
    private UsuariosDAO usuarioDao;
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
    
    public Usuarios login(Usuarios u) throws Exception{
        Usuarios result = null;
        
        result = usuarioDao.read(u.getId());
        System.out.println("Service tiene la culpa");
        if(result == null)
            throw new Exception("Usuario no encontrado");
        
        if(result.getContrasenna() != u.getContrasenna()){
            throw new Exception("Usuario no encontrado");
        }
        
        return result;
    }
    
    
    
}
