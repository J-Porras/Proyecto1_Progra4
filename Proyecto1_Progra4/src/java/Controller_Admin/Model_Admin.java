/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Admin;

import Cursos.Logica.Curso;
import Usuarios.logica.Usuarios;
import java.util.List;

/**
 *
 * @author pg300
 */
public class Model_Admin {
     private List<Curso> cursos;
     private List<Usuarios> profesores;
    private List<Usuarios> usuarios;

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Usuarios> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Usuarios> profesores) {
        this.profesores = profesores;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
