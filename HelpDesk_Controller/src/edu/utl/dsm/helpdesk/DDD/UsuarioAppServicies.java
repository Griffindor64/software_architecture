package edu.utl.dsm.helpdesk.DDD;

import edu.utl.dsm.helpdesk.cqrs.UsuarioCQRS;
import edu.utl.dsm.helpdesk.dao.UsuarioComandosDAO;
import edu.utl.dsm.helpdesk.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioAppServicies {

    public List<Usuario> getAllUsuarios(int estatus) throws Exception {
        UsuarioComandosDAO objUsuDAO = new UsuarioComandosDAO();
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = objUsuDAO.mostrarUsuario(estatus);
        return usuarios;
    }
    
    public Usuario iniciarSesion(String nombreUsuario, String contrasennia) throws Exception {
        UsuarioCQRS uCQRS = new UsuarioCQRS();
        return uCQRS.iniciarSesion(nombreUsuario, contrasennia);
    }

    public int registrarUsuario(Usuario usuario) throws Exception {
        UsuarioCQRS uCQRS = new UsuarioCQRS();
        return uCQRS.registrarUsuario(usuario);
    }

    public Boolean actualizarUsuario(Usuario usuario) throws Exception {
        UsuarioCQRS uCQRS = new UsuarioCQRS();
        return uCQRS.actualizarUsuario(usuario);
    }

    public Boolean eliminarUsuario(int id) throws Exception {
        UsuarioCQRS uCQRS = new UsuarioCQRS();
        return uCQRS.eliminarUsuario(id);
    }

    public Boolean validarNombreUsuario(String nombreUsuario) throws Exception {
        UsuarioCQRS uCQRS = new UsuarioCQRS();
        return uCQRS.validarNombreUsuario(nombreUsuario);
    }

}
