/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cursos.Logica;

/**
 *
 * @author pgat3000
 */
public class Curso {
    private int id;
    private String Nombre;
    private String tematica;
    private Boolean estado;
    private Double precio;

    public Curso() {
    }

    public Curso(int id, String Nombre, String tematica, Boolean estado, Double precio) {
        this.id = id;
        this.Nombre = Nombre;
        this.tematica = tematica;
        this.estado = estado;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
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
