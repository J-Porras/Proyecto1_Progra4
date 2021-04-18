/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import Usuarios.logica.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author pgat3000
 */
public class UsuariosDAO {
    public Usuarios create(Usuarios cl) throws SQLException, Exception{
        String sqlcommand =  "inserto into Usuario (id,nombre,contrasenna,telefono,email,rol,especialidad) "
                + "values(?,?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
       
        stm.setString(1,cl.getId());
        stm.setString(2,cl.getNombre());
        stm.setString(3,cl.getContrasenna());
        stm.setString(4,cl.getTelefono());
        stm.setString(5,cl.getTelefono());
        stm.setInt(6,cl.getRol());
        stm.setString(7,cl.getEspecialidad());
        //stm.setString(5, "true");//dice que no es case sensitive y que 1 y 0 sirven
        
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
}
