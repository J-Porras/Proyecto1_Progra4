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
        String sqlcommand =  "insert into Cursos (nombre,tematica,estado,precio)"
                + "values(?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
       
       // stm.setInt(1,cl.getId());
        stm.setString(1,cl.getNombre());
        stm.setString(2,cl.getTematica());
        stm.setBoolean(3,cl.getEstado());
        stm.setDouble(4,cl.getPrecio());
       System.out.println("Creando curso");

        
        int count = Database.instance().executeUpdate(stm);
         System.out.println(stm);
        if (count == 0) {
            throw new Exception("Curso ya existe");
        }
        return cl;
    }
    
    public Curso read(int id) throws Exception{
      
        
        String sqlcommand = "select * from cursos where id = ?";
        System.out.println("Entransdo en DB");
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);//Crashea Glassfish
          System.out.println("Buscando Curso en DB");
        stm.setInt(1, id);
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
        System.out.println("READ ALL CURSOS------- ");  
        List<Curso> cursos= Collections.synchronizedList(new ArrayList<Curso>());
        String sqlcommand = "select * from Cursos";
        System.out.println("Entransdo en DB");
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        System.out.println("Buscando Curso en DB  ");  

        ResultSet rs =  Database.instance().executeQuery(stm); 
    
            
            while(rs.next()){
            Curso r= new Curso();
            r.setId(rs.getInt("id"));
            r.setNombre(rs.getString("nombre"));
            r.setTematica(rs.getString("tematica"));
            r.setEstado(rs.getBoolean("estado"));
            r.setPrecio(rs.getDouble("precio"));

            cursos.add(r);
System.out.println("Buscando cursos_off ");  
            }
        
        
      
        return cursos;
    }
    
    public List<Curso> read_cursos_oferta() throws Exception{
        List<Curso> cursos= new ArrayList<Curso>();
        String sqlcommand = "select * from Cursos where estado = 1";
        System.out.println("Entransdo en DB");
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        System.out.println("Buscando cursos_off ");  
        ResultSet rs =  Database.instance().executeQuery(stm);
            while(rs.next()){
            Curso r= new Curso();
            r.setId(rs.getInt("id"));
            r.setNombre(rs.getString("nombre"));
            r.setTematica(rs.getString("tematica"));
            r.setEstado(rs.getBoolean("estado"));
            r.setPrecio(rs.getDouble("precio"));
            System.out.println("en while");
            cursos.add(r);

            }
       
  System.out.println("RETURN CURSOS OFF------- ");  
         System.out.println(cursos.size());   
        return cursos;
    }
    
    //==================================
   public List<Curso> read_cursos_filtradosN(String filter) throws Exception{
        List<Curso> cursos= new ArrayList<Curso>();
        String sqlcommand = "select * from Cursos where nombre like = ?%";
        System.out.println("Entransdo en DB");
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setString(1, filter);
        System.out.println("Buscando cursos_off ");  
        ResultSet rs =  Database.instance().executeQuery(stm);
            while(rs.next()){
            Curso r= new Curso();
            r.setId(rs.getInt("id"));
            r.setNombre(rs.getString("nombre"));
            r.setTematica(rs.getString("tematica"));
            r.setEstado(rs.getBoolean("estado"));
            r.setPrecio(rs.getDouble("precio"));
            System.out.println("en while");
            cursos.add(r);

            }
       
  System.out.println("RETURN CURSOS OFF------- ");  
         System.out.println(cursos.size());   
        return cursos;
    }
   //===================================================
    public List<Curso> read_cursos_filtradosT(String filter) throws Exception{
        List<Curso> cursos= new ArrayList<Curso>();
        String sqlcommand = "select * from Cursos where tematica like = ?%";
        System.out.println("Entransdo en DB");
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setString(1, filter);
        System.out.println("Buscando cursos_off ");  
        ResultSet rs =  Database.instance().executeQuery(stm);
            while(rs.next()){
            Curso r= new Curso();
            r.setId(rs.getInt("id"));
            r.setNombre(rs.getString("nombre"));
            r.setTematica(rs.getString("tematica"));
            r.setEstado(rs.getBoolean("estado"));
            r.setPrecio(rs.getDouble("precio"));
            System.out.println("en while");
            cursos.add(r);

            }
       
  System.out.println("RETURN CURSOS OFF------- ");  
         System.out.println(cursos.size());   
        return cursos;
    }
    
    
    //public 
    public Curso from (ResultSet rs){
        try {
                Curso r= new Curso();
            r.setId(rs.getInt("id"));
            r.setNombre(rs.getString("nombre"));
            r.setTematica(rs.getString("tematica"));
            r.setEstado(rs.getBoolean("estado"));
            r.setPrecio(rs.getDouble("precio"));
          
                   
            return r;
        } catch (SQLException ex) {
            System.out.println("Database: Error creando Curso");
            return null;
        }
    }
      public void update_estado(int id) throws SQLException, Exception{
        String sqlcommand = "update cursos set estado=? where id=?";
        Boolean estado;
        estado = !this.read(id).getEstado();
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setBoolean(1, estado);
        stm.setInt(2, id);
        System.out.println(stm);
        Database.instance().executeUpdate(stm);
       
        System.out.println("Estado modificado");
      }
}
