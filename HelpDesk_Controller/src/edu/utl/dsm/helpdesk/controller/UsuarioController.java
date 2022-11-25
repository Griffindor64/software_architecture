package edu.utl.dsm.helpdesk.controller;

import edu.utl.dsm.helpdesk.DDD.UsuarioAppServicies;
import edu.utl.dsm.helpdesk.model.Usuario;

public class UsuarioController {

    public Usuario iniciarSesion(String nombreUsuario, String contrasennia) throws Exception {
        UsuarioAppServicies objUsuAS = new UsuarioAppServicies();
        return objUsuAS.iniciarSesion(nombreUsuario, contrasennia);
    }

    public int registrarUsuario(Usuario usuario) throws Exception {
        UsuarioAppServicies objUsuAS = new UsuarioAppServicies();
        return objUsuAS.registrarUsuario(usuario);
    }

    public Boolean actualizarUsuario(Usuario usuario) throws Exception {
        UsuarioAppServicies objUsuAS = new UsuarioAppServicies();
        return objUsuAS.actualizarUsuario(usuario);
    }

    public Boolean eliminarUsuario(int id) throws Exception {
        UsuarioAppServicies objUsuAS = new UsuarioAppServicies();
        return objUsuAS.eliminarUsuario(id);
    }

    public Boolean validarNombreUsuario(String nombreUsuario) throws Exception {
        UsuarioAppServicies objUsuAS = new UsuarioAppServicies();
        return objUsuAS.validarNombreUsuario(nombreUsuario);
    }
}
