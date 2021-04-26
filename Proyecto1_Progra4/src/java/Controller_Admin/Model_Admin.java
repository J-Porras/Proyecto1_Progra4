/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Admin;

import Cursos.Logica.Curso;
import Grupos.Logica.Grupo;
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
    private List<Grupo> grupos;
    private List<Grupo> grupostodos;

    public List<Grupo> getGrupostodos() {
        return grupostodos;
    }

    public void setGrupostodos(List<Grupo> grupostodos) {
        this.grupostodos = grupostodos;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
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
