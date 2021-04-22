/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import Cursos.Logica.Curso;
import Grupos.Logica.Grupo;
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
public class CursosDao {
     public Curso create(Curso cl) throws SQLException, Exception{
        String sqlcommand =  "insert into Cursos (id,tematica,descripcion,estado,precio)"
                + "values(?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
       
       // stm.setInt(1,cl.getId());
        stm.setString(1,cl.getTematica());
        stm.setString(2,cl.getDescripcion());
        stm.setBoolean(3,cl.getEstado());
        stm.setDouble(4,cl.getPrecio());
       

        
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Curso ya existe");
        }
        return cl;
    }
    
    public Curso read(String id) throws Exception{
      
        
        String sqlcommand = "select * from cursos where id = ?";
        System.out.println("Entransdo en DB");
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);//Crashea Glassfish
          System.out.println("Buscando Curso en DB");
        stm.setString(1, id);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            System.out.println("Curso encontrado base de datos");
            
            return from(rs);
        }
        else{
            System.out.println("Database: Curso no existe");
            throw new Exception ("Curso no Existe");
        }
    }
     public List<Curso> read_all_cursos() throws Exception{
     List<Curso> cursos= Collections.synchronizedList(new ArrayList<Curso>());
     String sqlcommand = "select * from Cursos";
     System.out.println("Entransdo en DB");
     PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
     System.out.println("Buscando Curso en DB con profesor ");  
    
     ResultSet rs =  Database.instance().executeQuery(stm); 
      try {
         
            while(rs.next()){
            Curso r= new Curso();
            r.setId(rs.getInt("id"));
            r.setTematica(rs.getString("nombre"));
            r.setDescripcion(rs.getString("descripcion"));
            r.setEstado(rs.getBoolean("estado"));
            r.setPrecio(rs.getDouble("precio"));
            
            cursos.add(r);

             }
    }
     catch (SQLException e){
        System.out.println("Operacion no se logro(leer cursos)");
                 
    }
     return cursos;
     }
    
    //public 
    public Curso from (ResultSet rs){
        try {
                Curso r= new Curso();
            r.setId(rs.getInt("id"));
            r.setTematica(rs.getString("nombre"));
            r.setDescripcion(rs.getString("descripcion"));
            r.setEstado(rs.getBoolean("estado"));
            r.setPrecio(rs.getDouble("precio"));
          
                   
            return r;
        } catch (SQLException ex) {
            System.out.println("Database: Error creando Curso");
            return null;
        }
    }
}
