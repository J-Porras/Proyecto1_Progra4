/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matriculas.Logic;

/**
 *
 * @author pg300
 */
public class Matricula {
    private int id_grupo;
    private String id_est;
    private String fec_matricula;
    private Double Calificacion;

    public Matricula() {
    }

    public Matricula(int id_grupo, String id_est, String fec_matricula, Double Calificacion) {
        this.id_grupo = id_grupo;
        this.id_est = id_est;
        this.fec_matricula = fec_matricula;
        this.Calificacion = Calificacion;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getId_est() {
        return id_est;
    }

    public void setId_est(String id_est) {
        this.id_est = id_est;
    }

    public String getFec_matricula() {
        return fec_matricula;
    }

    public void setFec_matricula(String fec_matricula) {
        this.fec_matricula = fec_matricula;
    }

    public Double getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(Double Calificacion) {
        this.Calificacion = Calificacion;
    }
    
}
