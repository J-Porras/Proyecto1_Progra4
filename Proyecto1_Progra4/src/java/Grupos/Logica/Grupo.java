/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grupos.Logica;

/**
 *
 * @author pg300
 */
public class Grupo {
    private int num_grupo;
    private int id_curso;
    private String prof_titular;
    private String dias;
    private String horario;

    public Grupo(int num_grupo, int id_curso, String prof_titular, String dias, String horario) {
        this.num_grupo = num_grupo;
        this.id_curso = id_curso;
        this.prof_titular = prof_titular;
        this.dias = dias;
        this.horario = horario;
    }

    public Grupo() {
    }

    public int getNum_grupo() {
        return num_grupo;
    }

    public void setNum_grupo(int num_grupo) {
        this.num_grupo = num_grupo;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getProf_titular() {
        return prof_titular;
    }

    public void setProf_titular(String prof_titular) {
        this.prof_titular = prof_titular;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    
}
