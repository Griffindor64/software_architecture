package edu.utl.dsm.helpdesk.model;

public class Libro {
    private int id;
    private String nombre, descripcion, tema;

    public Libro() {
    }

    public Libro(int id, String nombre, String descripcion, String tema) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tema = tema;
    }

    public Libro(String nombre, String descripcion, String tema) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tema = tema;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tema=" + tema + '}';
    }

   
    
}
