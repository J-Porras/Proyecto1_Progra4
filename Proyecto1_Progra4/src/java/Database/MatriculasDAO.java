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

    public Matricula create(Matricula cl) throws SQLException, Exception {
        String sqlcommand = "insert into Matriculas (id_grupo,id_est,fec_matricula,calificacion)"
                + "values(?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setInt(1, cl.getId_grupo());
        stm.setString(2, cl.getId_est());
        String datestr = cl.getFec_matricula();//(formato "2015-03-31";)
        Date date = Date.valueOf(datestr);
        stm.setDate(3, date);
        stm.setDouble(4, cl.getCalificacion());
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Matricula ya existe");
        }
        return cl;
    }

    public Matricula read(String id) throws Exception {
        String sqlcommand = "select * from matriculas where id = ?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setString(1, id);
        ResultSet rs = Database.instance().executeQuery(stm);
        if (rs.next()) {
            return from(rs);
        } else {
            throw new Exception("Matricula no Existe");
        }
    }

    public Matricula from(ResultSet rs) {
        try {
            Matricula r = new Matricula();
            r.setId_grupo(rs.getInt("id_grupo"));
            r.setId_est(rs.getString("id_est"));
            DateFormat df = new SimpleDateFormat("MM,dd,yyyy");
            String dateStr = df.format(rs.getDate("fec_matricula"));
            r.setFec_matricula(dateStr);
            r.setCalificacion(rs.getDouble("calificacion"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }

    //leer estudiante por grupo
    public List<Matricula> read_estudiante_por_grupo(int id_grupo) throws Exception {//terminar
        List<Matricula> grupo_est = Collections.synchronizedList(new ArrayList<Matricula>());
        String sqlcommand = "select * from matriculas  where  id_grupo = ?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setInt(1, id_grupo);
        ResultSet rs = Database.instance().executeQuery(stm);
        while (rs.next()) {
            Matricula r = new Matricula();
            r.setId_grupo(rs.getInt("id_grupo"));
            r.setId_est(rs.getString("id_est"));
            DateFormat df = new SimpleDateFormat("MM,dd,yyyy");
            String dateStr = df.format(rs.getDate("fec_matricula"));
            r.setFec_matricula(dateStr);
            r.setCalificacion(rs.getDouble("calificacion"));;
            grupo_est.add(r);
        }
        return grupo_est;
    }

    public void update_calficacion(String id_est, int id_grupo, double nota) throws SQLException {
        String sqlcommand = "update matriculas set calificacion=? where  id_est = ? and id_grupo=?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setDouble(1, nota);
        stm.setString(2, id_est);
        stm.setInt(3, id_grupo);
        Database.instance().executeUpdate(stm);
    }

    public List<Matricula> read_grupos_estudiante(String id_est) throws Exception {//terminar
        List<Matricula> grupo_est = Collections.synchronizedList(new ArrayList<Matricula>());
        String sqlcommand = "select * from matriculas where  id_est = ?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setString(1, id_est);
        ResultSet rs = Database.instance().executeQuery(stm);
        try {
            while (rs.next()) {
                Matricula r = new Matricula();
                r.setId_grupo(rs.getInt("id_grupo"));
                r.setId_est(rs.getString("id_est"));
                DateFormat df = new SimpleDateFormat("MM,dd,yyyy");
                String dateStr = df.format(rs.getDate("fec_matricula"));
                r.setFec_matricula(dateStr);
                r.setCalificacion(rs.getDouble("calificacion"));
                grupo_est.add(r);
            }
        } catch (SQLException e) {
        }
        return grupo_est;
    }
}
