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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author pg300
 */
public class GruposDAO {

    public Grupo create(Grupo cl) throws SQLException, Exception {
        String sqlcommand = "insert into Grupos (id_curso,prof_titular,dias,horario)" + "values(?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setInt(1, cl.getId_curso());
        stm.setString(2, cl.getProf_titular());
        stm.setString(3, cl.getDias());
        stm.setString(4, cl.getHorario());
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Grupo ya existe");
        }
        return cl;
    }

    public Grupo read(String id) throws Exception {

        String sqlcommand = "select * from Grupos where num_grupo = ?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);//Crashea Glassfish
        stm.setString(1, id);
        ResultSet rs = Database.instance().executeQuery(stm);
        if (rs.next()) {
            return from(rs);
        } else {
            throw new Exception("Grupo no Existe");
        }
    }

    public Grupo from(ResultSet rs) {
        try {
            Grupo r = new Grupo();
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
    public Usuarios readProfesor(String id_grupo) throws Exception {//terminar
        String sqlcommand = "select * from Grupos where num_grupo = ?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setString(1, id_grupo);
        ResultSet rs = Database.instance().executeQuery(stm);
        if (rs.next()) {
            String id_profesor = from(rs).getProf_titular();
            UsuariosDAO dao = new UsuariosDAO();
            return dao.read(id_profesor);
        } else {
            throw new Exception("Grupo no Existe");
        }

    }

    public List<Grupo> read_grupos_profesor(String id_profesor) throws Exception {
        List<Grupo> grupos_profe = Collections.synchronizedList(new ArrayList<Grupo>());
        String sqlcommand = "select * from Grupos where  prof_titular = ?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setString(1, id_profesor);
        ResultSet rs = Database.instance().executeQuery(stm);
        while (rs.next()) {
            Grupo r = new Grupo();
            r.setNum_grupo(rs.getInt("num_grupo"));
            r.setId_curso(rs.getInt("id_curso"));
            r.setProf_titular(rs.getString("prof_titular"));
            r.setDias(rs.getString("dias"));
            r.setHorario(rs.getString("horario"));
            grupos_profe.add(r);
        }
        return grupos_profe;
    }

    //leer grupos or curso
    public List<Grupo> read_grupos_curso(int id_curso) throws Exception {//terminar
        List<Grupo> grupos_curso = Collections.synchronizedList(new ArrayList<Grupo>());
        String sqlcommand = "select * from Grupos where  id_curso = ?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setInt(1, id_curso);
        ResultSet rs = Database.instance().executeQuery(stm);
        while (rs.next()) {
            Grupo r = new Grupo();
            r.setNum_grupo(rs.getInt("num_grupo"));
            r.setId_curso(rs.getInt("id_curso"));
            r.setProf_titular(rs.getString("prof_titular"));
            r.setDias(rs.getString("dias"));
            r.setHorario(rs.getString("horario"));
            grupos_curso.add(r);
        }
        return grupos_curso;
    }

    public List<Grupo> read_all_grupos() throws Exception {
        List<Grupo> grupos_curso = Collections.synchronizedList(new ArrayList<Grupo>());
        String sqlcommand = "select * from Grupos";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        ResultSet rs = Database.instance().executeQuery(stm);
        while (rs.next()) {
            Grupo r = new Grupo();
            r.setNum_grupo(rs.getInt("num_grupo"));
            r.setId_curso(rs.getInt("id_curso"));
            r.setProf_titular(rs.getString("prof_titular"));
            r.setDias(rs.getString("dias"));
            r.setHorario(rs.getString("horario"));
            grupos_curso.add(r);
        }
        return grupos_curso;
    }
}
