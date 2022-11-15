package edu.utl.dsm.helpdesk.cqrs;

import edu.utl.dsm.helpdesk.dao.UsuarioComandosDAO;
import edu.utl.dsm.helpdesk.model.Usuario;

public class UsuarioCQRS {

    
     public Boolean validarNombreUsuario(String nombreUsuario) throws Exception{
        UsuarioComandosDAO objUsuDAO = new UsuarioComandosDAO();
        Usuario usuario = objUsuDAO.validarNombreUsuario(nombreUsuario);
        if (usuario.getId() == 0) {
            return false;
        } else {
            return true;
        }
        
    }
    
    public Usuario iniciarSesion(String nombreUsuario, String contrasennia) throws Exception {
        UsuarioComandosDAO objUsuDAO = new UsuarioComandosDAO();
        Usuario u = new Usuario();
        if (validarNombreUsuario(nombreUsuario)) {
            u = objUsuDAO.iniciarSesion(nombreUsuario, contrasennia);
        } else {
            u = null;
        }
        return u;
    }

    public int registrarUsuario(Usuario usuario) throws Exception {
        UsuarioComandosDAO objUsuDAO = new UsuarioComandosDAO();
       int idU = 0;
        if (!validarNombreUsuario(usuario.getNombreUsuario())) {
            idU = objUsuDAO.registrarUsuario(usuario);
        }
        return idU;
    }

}
