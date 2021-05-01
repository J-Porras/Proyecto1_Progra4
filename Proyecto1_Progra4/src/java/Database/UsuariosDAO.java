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

    public Usuarios create(Usuarios cl) throws SQLException, Exception {
        String sqlcommand = "insert into Usuarios (id,nombre,contrasenna,telefono,email,rol,especialidad)"
                + "values(?,?,?,?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setString(1, cl.getId());
        stm.setString(2, cl.getNombre());
        stm.setString(3, cl.getContrasenna());
        stm.setString(4, cl.getTelefono());
        stm.setString(5, cl.getEmail());
        stm.setInt(6, cl.getRol());
        stm.setString(7, cl.getEspecialidad());
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Usuario ya existe");
        }
        return cl;
    }

    public Usuarios read(String id) throws Exception {
        String sqlcommand = "select * from usuarios where id = ?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);//Crashea Glassfish
        stm.setString(1, id);
        ResultSet rs = Database.instance().executeQuery(stm);
        if (rs.next()) {
            return from(rs);
        } else {
            throw new Exception("Usuario no Existe");
        }
    }

    public List<Usuarios> read_all() throws Exception {

        List<Usuarios> usuarios = Collections.synchronizedList(new ArrayList<>());
        String sqlcommand = "select * from usuarios ";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);//Crashea Glassfish
        ResultSet rs = Database.instance().executeQuery(stm);
        while (rs.next()) {
            Usuarios r = new Usuarios();
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

    public List<Usuarios> read_all_profes() throws Exception {
        List<Usuarios> usuarios = Collections.synchronizedList(new ArrayList<Usuarios>());
        String sqlcommand = "select * from usuarios ";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);//Crashea Glassfish
        ResultSet rs = Database.instance().executeQuery(stm);
        while (rs.next()) {
            Usuarios r = new Usuarios();
            r.setId(rs.getString("id"));
            r.setNombre(rs.getString("nombre"));
            r.setContrasenna(rs.getString("contrasenna"));
            r.setTelefono(rs.getString("telefono"));
            r.setEmail(rs.getString("email"));
            r.setRol(rs.getInt("rol"));
            r.setEspecialidad(rs.getString("especialidad"));
            if (r.getRol() == 2) {
                usuarios.add(r);
            }
        }
        return usuarios;
    }

    public Usuarios from(ResultSet rs) {
        try {
            Usuarios r = new Usuarios();
            r.setId(rs.getString("id"));
            r.setNombre(rs.getString("nombre"));
            r.setContrasenna(rs.getString("contrasenna"));
            r.setTelefono(rs.getString("telefono"));
            r.setEmail(rs.getString("email"));
            r.setRol(rs.getInt("rol"));
            r.setEspecialidad(rs.getString("especialidad"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<Usuarios> read_Filtrar_Profes(String filter) throws Exception {
        List<Usuarios> usuarios = Collections.synchronizedList(new ArrayList<>());
        String sqlcommand = "select * from Usuarios where nombre like  ?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        filter = filter + "%";
        stm.setString(1, filter);
        ResultSet rs = Database.instance().executeQuery(stm);
        while (rs.next()) {
            Usuarios r = new Usuarios();
            r.setId(rs.getString("id"));
            r.setNombre(rs.getString("nombre"));
            r.setContrasenna(rs.getString("contrasenna"));
            r.setTelefono(rs.getString("telefono"));
            r.setEmail(rs.getString("email"));
            r.setRol(rs.getInt("rol"));
            r.setEspecialidad(rs.getString("especialidad"));
            if (r.getRol() == 2) {
                usuarios.add(r);
            }
        }
        return usuarios;
    }

    public List<Usuarios> read_Filtrar(String filter) throws Exception {
        List<Usuarios> usuarios = Collections.synchronizedList(new ArrayList<Usuarios>());
        String sqlcommand = "select * from Usuarios where nombre like  ?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        filter = filter + "%";
        stm.setString(1, filter);
        ResultSet rs = Database.instance().executeQuery(stm);
        while (rs.next()) {
            Usuarios r = new Usuarios();
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
}
