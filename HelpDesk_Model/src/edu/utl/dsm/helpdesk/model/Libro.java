package edu.utl.dsm.helpdesk.model;

public class Libro {

    private int id;
    private String nombre, descripcion, tema;
    private Usuario usuario;
    private String archivo;

    public Libro() {
    }

    public Libro(int id, String nombre, String descripcion, String tema, Usuario usuario, String archivo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tema = tema;
        this.usuario = usuario;
        this.archivo = archivo;
    }
    
    public Libro(String nombre, String descripcion, String tema, Usuario usuario, String archivo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tema = tema;
        this.usuario = usuario;
        this.archivo = archivo;
    }

    public Libro(int id, String nombre, String descripcion, String tema, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tema = tema;
        this.usuario = usuario;

    }

    public Libro(String nombre, String descripcion, String tema, Usuario usuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.tema = tema;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
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
        return "Libro{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tema=" + tema + ", usuario=" + usuario.toString() + '}';
    }

}
