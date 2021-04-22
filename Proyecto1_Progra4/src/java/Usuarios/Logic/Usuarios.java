/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios.Logic;

/**
 *
 * @author pgat3000
 */
public class Usuarios {
    private String id;
    private String nombre;
    private String contrasenna;
    private String telefono;
    private String email;
    private int rol;
    private String especialidad;
    public Usuarios(){}
    public Usuarios(String id, String nombre, String contrasenna, String telefono, String email, int rol, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenna = contrasenna;
        this.telefono = telefono;
        this.email = email;
        this.rol = rol;
        this.especialidad = especialidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
}
