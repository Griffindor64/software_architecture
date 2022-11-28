package edu.utl.dsm.helpdesk.model;

public class Usuario {

    private int id;
    private String nombres;
    private String apellidos;
    private int estatus;
    private String nombreUsuario;
    private String contrasennia;
    private int rol;

    public Usuario() {
    }

    public Usuario(int id, String nombres, String apellidos, String nombreUsuario, String contrasennia, int rol) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nombreUsuario = nombreUsuario;
        this.contrasennia = contrasennia;
        this.rol = rol;
    }

    public Usuario(String nombres, String apellidos,  String nombreUsuario, String contrasennia, int rol) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nombreUsuario = nombreUsuario;
        this.contrasennia = contrasennia;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasennia() {
        return contrasennia;
    }

    public void setContrasennia(String contrasennia) {
        this.contrasennia = contrasennia;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", estatus=" + estatus + ", nombreUsuario=" + nombreUsuario + ", contrasennia=" + contrasennia + ", rol=" + rol + '}';
    }

    

}
