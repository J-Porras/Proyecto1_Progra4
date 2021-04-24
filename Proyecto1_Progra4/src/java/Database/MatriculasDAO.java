/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Grupos.Logica.Grupo;
import Matriculas.Logic.Matricula;
import Usuarios.logica.Usuarios;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author pg300
 */
public class MatriculasDAO {//Trabajar en esto
     public Matricula create( Matricula cl) throws SQLException, Exception{
        String sqlcommand =  "insert into Matriculas (id_grupo,id_est,fec_matricula,calificacion)"
                + "values(?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
       
        stm.setInt(1,cl.getId_grupo());
        stm.setString(2,cl.getId_est());
        String datestr = cl.getFec_matricula();//(formato "2015-03-31";)
        Date date = Date.valueOf(datestr); 
        stm.setDate(3,date);
        stm.setDouble(4,cl.getCalificacion());
        
     
        System.out.println(stm);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            
            throw new Exception("Matricula ya existe");
            
        }
        return cl;
        
    }
    
    public Matricula read(String id) throws Exception{
      
        
        String sqlcommand = "select * from matriculas where id = ?";
        System.out.println("Entransdo en DB");
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);//Crashea Glassfish
          System.out.println("Buscando Matricula en DB");
        stm.setString(1, id);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            System.out.println("Matricula  encontrado base de datos");
            
            return from(rs);
        }
        else{
            System.out.println("Database: Usuario no Matricula");
            throw new Exception ("Matricula no Existe");
        }
    }
    
    
    public Matricula from (ResultSet rs){
        try {
            Matricula r= new Matricula();
            r.setId_grupo(rs.getInt("id_grupo"));
            r.setId_est(rs.getString("id_est"));
            DateFormat df= new SimpleDateFormat("MM,dd,yyyy");
            String dateStr= df.format(rs.getDate("fec_matricula"));
            r.setFec_matricula(dateStr);
            r.setCalificacion(rs.getDouble("calificacion"));
          
            System.out.println("Database:  Matricula creada");
                   
            return r;
        } catch (SQLException ex) {
            System.out.println("Database: Error creando Usuario");
            return null;
        }
    }
    
    //leer estudiante por grupo
    public List<Usuarios> read_estudiante_por_grupo(String id_grupo) throws Exception{//terminar
         List<Usuarios> grupo_est= Collections.synchronizedList(new ArrayList<Usuarios>());
     String sqlcommand = "select * from Grupos where  id_grupo = ?";
     System.out.println("Entransdo en DB");
     PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
     System.out.println("Buscando Curso en DB con profesor ");  
     stm.setString(1, id_grupo);
     ResultSet rs =  Database.instance().executeQuery(stm); 
      try {
         
            while(rs.next()){
             
                UsuariosDAO dao= new UsuariosDAO();
                Usuarios r= dao.read( rs.getString("id_est"));
         
            
                grupo_est.add(r);

            }
      }
      catch (SQLException e){
      System.out.println("Operacion no se logro(leer grupos est)");
                 
    }
     return grupo_est;
    
    }
    void update_calficacion(String id_est, Double nota) throws SQLException{
        String sqlcommand = "update matriculas set calificacion=? where  id_est = ?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setDouble(1, nota);
        stm.setString(2, id_est);
        System.out.println("Nota modificado");
       }
    //grupos de estudiante(Hisotrial)
    public List<Matricula> read_grupos_estudiante(String id_est) throws Exception{//terminar
         List<Matricula> grupo_est= Collections.synchronizedList(new ArrayList<Matricula>());
     String sqlcommand = "select * from Grupos where  id_est = ?";
     System.out.println("Entransdo en DB");
     PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
     System.out.println("Buscando Curso en DB con profesor ");  
     stm.setString(1, id_est);
     ResultSet rs =  Database.instance().executeQuery(stm); 
      try {
         
            while(rs.next()){
             
             Matricula r= new Matricula();
            r.setId_grupo(rs.getInt("id_grupo"));
            r.setId_est(rs.getString("id_est"));
            DateFormat df= new SimpleDateFormat("MM,dd,yyyy");
            String dateStr= df.format(rs.getDate("fec_matricula"));
            r.setFec_matricula(dateStr);
            r.setCalificacion(rs.getDouble("calificacion"));;
         
            
                grupo_est.add(r);

            }
      }
      catch (SQLException e){
      System.out.println("Operacion no se logro(leer grupos est)");
                 
    }
     return grupo_est;
    
    }
}
