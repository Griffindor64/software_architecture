package edu.utl.dsm.helpdesk.model;

public class Usuario {
    
    private int id;
    private String nombreUsuario;
    private String contrasennia;

    public Usuario() {
    }

    public Usuario(int id, String nombreUsuario, String contrasennia) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasennia = contrasennia;
    }

    public Usuario(String nombreUsuario, String contrasennia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasennia = contrasennia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombreUsuario=" + nombreUsuario + ", contrasennia=" + contrasennia + '}';
    }    

}
