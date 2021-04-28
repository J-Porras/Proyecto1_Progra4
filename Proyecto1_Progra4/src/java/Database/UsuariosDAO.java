/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import Cursos.Logica.Curso;
import Usuarios.logica.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author pgat3000
 */
public class UsuariosDAO {
    public Usuarios create(Usuarios cl) throws SQLException, Exception{
        String sqlcommand =  "insert into Usuarios (id,nombre,contrasenna,telefono,email,rol,especialidad)"
                + "values(?,?,?,?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
       
        stm.setString(1,cl.getId());
        stm.setString(2,cl.getNombre());
        stm.setString(3,cl.getContrasenna());
        stm.setString(4,cl.getTelefono());
        stm.setString(5,cl.getEmail());
        stm.setInt(6,cl.getRol());
        stm.setString(7,cl.getEspecialidad());
     
        System.out.println(stm);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            
            throw new Exception("Usuario ya existe");
            
        }
        return cl;
        
    }
    
    public Usuarios read(String id) throws Exception{
      
        
        String sqlcommand = "select * from usuarios where id = ?";
        System.out.println("Entransdo en DB");
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);//Crashea Glassfish
          System.out.println("Buscando Usuario en DB");
        stm.setString(1, id);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            System.out.println("Usuario encontrado base de datos");
            
            return from(rs);
        }
        else{
            System.out.println("Database: Usuario no existe");
            throw new Exception ("Usuario no Existe");
        }
    }
    
    public List<Usuarios> read_all() throws Exception{
      
         List<Usuarios> usuarios= Collections.synchronizedList(new ArrayList<Usuarios>());
        String sqlcommand = "select * from usuarios ";
        System.out.println("Entransdo en DB");
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);//Crashea Glassfish
          System.out.println("Buscando Usuario en DB");
       
        ResultSet rs =  Database.instance().executeQuery(stm);           
       while(rs.next()) {
            Usuarios r= new Usuarios();
            r.setId(rs.getString("id"));
            r.setNombre(rs.getString("nombre"));
            
            r.setContrasenna(rs.getString("contrasenna"));
            r.setTelefono(rs.getString("telefono"));
            r.setEmail(rs.getString("email"));
            r.setRol(rs.getInt("rol"));
            r.setEspecialidad(rs.getString("especialidad"));
            usuarios.add(r);
            
        }
      return usuarios;
    }
       public List<Usuarios> read_all_profes() throws Exception{
      
         List<Usuarios> usuarios= Collections.synchronizedList(new ArrayList<Usuarios>());
        String sqlcommand = "select * from usuarios ";
        System.out.println("Entransdo en DB");
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);//Crashea Glassfish
          System.out.println("Buscando Usuario en DB");
       
        ResultSet rs =  Database.instance().executeQuery(stm);           
       while(rs.next()) {
            Usuarios r= new Usuarios();
            r.setId(rs.getString("id"));
            r.setNombre(rs.getString("nombre"));
            
            r.setContrasenna(rs.getString("contrasenna"));
            r.setTelefono(rs.getString("telefono"));
            r.setEmail(rs.getString("email"));
            r.setRol(rs.getInt("rol"));
            r.setEspecialidad(rs.getString("especialidad"));
            if(r.getRol()==2){
            usuarios.add(r);
            }
            
        }
      return usuarios;
    }
    
    public Usuarios from (ResultSet rs){
        try {
            Usuarios r= new Usuarios();
            r.setId(rs.getString("id"));
            r.setNombre(rs.getString("nombre"));
            
            r.setContrasenna(rs.getString("contrasenna"));
             r.setTelefono(rs.getString("telefono"));
            r.setEmail(rs.getString("email"));
            r.setRol(rs.getInt("rol"));
            r.setEspecialidad(rs.getString("especialidad"));
                    System.out.println("Database:  Usuario creado");
                    //System.out.println(r.getContrasenna());
            return r;
        } catch (SQLException ex) {
            System.out.println("Database: Error creando Usuario");
            return null;
        }
    }
    
    public List<Usuarios> read_Filtrar_Profes(String filter) throws Exception{
        List<Usuarios> usuarios= Collections.synchronizedList(new ArrayList<Usuarios>());
        String sqlcommand = "select * from Usuarios where nombre like = ?%";
        System.out.println("Entransdo en DB");
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setString(1, filter);
        System.out.println("Buscando cursos_off ");  
        ResultSet rs =  Database.instance().executeQuery(stm);
            while(rs.next()){
             Usuarios r= new Usuarios();
            r.setId(rs.getString("id"));
            r.setNombre(rs.getString("nombre"));
            
            r.setContrasenna(rs.getString("contrasenna"));
            r.setTelefono(rs.getString("telefono"));
            r.setEmail(rs.getString("email"));
            r.setRol(rs.getInt("rol"));
            r.setEspecialidad(rs.getString("especialidad"));
            if(r.getRol()==2){
            usuarios.add(r);
            }

            }
       
  System.out.println("RETURN CURSOS OFF------- ");  
         System.out.println(usuarios.size());   
        return usuarios;
    }

}
