/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import Grupos.Logica.Grupo;
import Usuarios.logica.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author pg300
 */
public class GruposDAO {
         public void create(Grupo cl) throws SQLException, Exception{
        String sqlcommand =  "inserto into Grupos (num_grupo,id_curso,prof_titular,dias,horario)"
                + "values(?,?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
       
        stm.setInt(1,cl.getNum_grupo());
        stm.setInt(2,cl.getId_curso());
        stm.setString(3,cl.getProf_titular());
        stm.setString(4,cl.getDias());
        stm.setString(5,cl.getHorario());
       

        
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Grupo ya existe");
        }
        
    }
    
    public Grupo read(String id) throws Exception{
      
        
        String sqlcommand = "select * from Grupos where num_grupo = ?";
        System.out.println("Entransdo en DB");
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);//Crashea Glassfish
          System.out.println("Buscando Curso en DB");
        stm.setString(1, id);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            System.out.println("Grupos encontrado base de datos");
            
            return from(rs);
        }
        else{
            System.out.println("Database: Grupo no existe");
            throw new Exception ("Grupo no Existe");
        }
    }
    
    
    public Grupo from (ResultSet rs){
        try {
                Grupo r= new Grupo();
            r.setNum_grupo(rs.getInt("id_grupo"));
            r.setId_curso(rs.getInt("id_est"));
            
            r.setProf_titular(rs.getString("prof_titular"));
             r.setDias(rs.getString("dias"));
            r.setHorario(rs.getString("horario"));
          
                   
            return r;
        } catch (SQLException ex) {
            System.out.println("Database: Error creando Curso");
            return null;
        }
    }
    ///////////////////////////////////////////////////////////////
     public Usuarios readProfesor(String id_grupo) throws Exception{//terminar
         String sqlcommand = "select * from Grupos where num_grupo = ?";
         System.out.println("Entransdo en DB");
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);//Crashea Glassfish
          System.out.println("Buscando Curso en DB con profesor ");
          
        stm.setString(1, id_grupo); 
        ResultSet rs =  Database.instance().executeQuery(stm); 
         if (rs.next()) {
            System.out.println("Grupos encontrado base de datos");
            
            String id_profesor= from(rs).getProf_titular();
            UsuariosDAO dao= new UsuariosDAO();
            return dao.read(id_profesor);
            
        }
        else{
            System.out.println("Database: Grupo no existe, profesor no ecnontrado");
            throw new Exception ("Grupo no Existe");
        }
      
     }
     /*
       public ArrayList<Grupo> read_grupos_profesor(String id_grupo) throws Exception{//terminar
        
     }
            public ArrayList<Grupo> read_grupos_curso(String id_grupo) throws Exception{//terminar
        
     }
*/
     /* Codigo Util para despujes
      public ArrayList<Expenses> getExpenses() {
        ArrayList<Expenses> expenses = new ArrayList<Expenses>();
        try {
            Statement stmt = myConnection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM expenses");
            while(result.next()){

                Expenses expense = new Expenses();
                expense.setNum(result.getInt(1));
                expense.setPayment(result.getString(2));
                expense.setReceiver(result.getInt(3));
                expense.setValue(result.getDouble(4));

                expenses.add(expense);

                }
        }
            catch (SQLException e){
                 System.out.println(e.getMessage());
             }
        return expenses;
    }
     
     */
}
