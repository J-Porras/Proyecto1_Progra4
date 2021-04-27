/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Est;

import Cursos.Logica.Curso;
import Grupos.Logica.Grupo;
import Matriculas.Logic.Matricula;
import java.util.List;

/**
 *
 * @author pg300
 */
public class Model_est {
    private List<Matricula >estudiantes_matricula;
    private List<Grupo> todos_grupos;
    private List<Curso> todos_cursos;

    public List<Curso> getTodos_cursos() {
        return todos_cursos;
    }

    public void setTodos_cursos(List<Curso> todos_cursos) {
        this.todos_cursos = todos_cursos;
    }
    public List<Grupo> getTodos_grupos() {
        return todos_grupos;
    }

    public void setTodos_grupos(List<Grupo> todos_grupos) {
        this.todos_grupos = todos_grupos;
    }
    public List<Matricula> getEstudiantes_matricula() {
        return estudiantes_matricula;
    }

    public void setEstudiantes_matricula(List<Matricula> estudiantes_matricula) {
        this.estudiantes_matricula = estudiantes_matricula;
    }
}
