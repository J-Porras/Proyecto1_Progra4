/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Matriculas.Logic.Matricula;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
    //falta agregar leer estudiante por grupo
    //estudiantes de profesor
    //grupos de estudiante
}
