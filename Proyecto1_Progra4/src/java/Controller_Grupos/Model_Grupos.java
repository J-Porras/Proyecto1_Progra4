/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Grupos;

import Cursos.Logica.Curso;
import Grupos.Logica.Grupo;
import Usuarios.logica.Usuarios;
import java.util.List;

/**
 *
 * @author pg300
 */
public class Model_Grupos {
    List<Grupo> grupos;
    List<Usuarios> posibleProfesor;
    List<Curso> cursos;

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
    public List<Usuarios> getPosibleProfesor() {
        return posibleProfesor;
    }

    public void setPosibleProfesor(List<Usuarios> posibleProfesor) {
        this.posibleProfesor = posibleProfesor;
    }
    
    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }


    
}
