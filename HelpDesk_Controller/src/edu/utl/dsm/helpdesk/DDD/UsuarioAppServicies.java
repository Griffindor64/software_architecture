package edu.utl.dsm.helpdesk.DDD;

import edu.utl.dsm.helpdesk.cqrs.UsuarioCQRS;
import edu.utl.dsm.helpdesk.dao.UsuarioComandosDAO;
import edu.utl.dsm.helpdesk.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioAppServicies {

<<<<<<< HEAD
    public List<Usuario> getAllUsuarios(int rol) throws Exception {
        UsuarioComandosDAO objUsuDAO = new UsuarioComandosDAO();
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = objUsuDAO.mostrarUsuario(rol);
=======
    public List<Usuario> getAllUsuarios(int estatus) throws Exception {
        UsuarioComandosDAO objUsuDAO = new UsuarioComandosDAO();
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = objUsuDAO.mostrarUsuario(estatus);
>>>>>>> 35123ea89056266a7b584197088d2e24aa2649ab
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
