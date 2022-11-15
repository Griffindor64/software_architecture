package edu.utl.dsm.helpdesk.DDD;

import edu.utl.dsm.helpdesk.cqrs.UsuarioCQRS;
import edu.utl.dsm.helpdesk.model.Usuario;

public class UsuarioAppServicies {
 
    public Usuario iniciarSesion(String nombreUsuario, String contrasennia) throws Exception {
        UsuarioCQRS uCQRS = new UsuarioCQRS();
        return uCQRS.iniciarSesion(nombreUsuario, contrasennia);
    }
    public int registrarUsuario(Usuario usuario) throws Exception{
        UsuarioCQRS uCQRS = new UsuarioCQRS();
        return uCQRS.registrarUsuario(usuario);
    }
    
    public Boolean validarNombreUsuario(String nombreUsuario) throws Exception{
        UsuarioCQRS uCQRS = new UsuarioCQRS();
        return uCQRS.validarNombreUsuario(nombreUsuario);
    }
    
}
