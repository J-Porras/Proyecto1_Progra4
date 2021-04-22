/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cursos.Logic;

/**
 *
 * @author pgat3000
 */
public class Curso {
    private int id;
    private String tematica;
    private String Descripcion;
    private Boolean estado;
    private Double precio;

    public Curso() {
    }

    public Curso(int id, String tematica, String Descripcion, Boolean estado, Double precio) {
        this.id = id;
        this.tematica = tematica;
        this.Descripcion = Descripcion;
        this.estado = estado;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
}
