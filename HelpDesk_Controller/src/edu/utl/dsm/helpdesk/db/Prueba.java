package edu.utl.dsm.helpdesk.db;

import edu.utl.dsm.helpdesk.controller.LibroController;
import edu.utl.dsm.helpdesk.controller.UsuarioController;
import edu.utl.dsm.helpdesk.cqrs.LibroCQRS;
import edu.utl.dsm.helpdesk.cqrs.UsuarioCQRS;
import edu.utl.dsm.helpdesk.dao.LibroComandosDAO;
import edu.utl.dsm.helpdesk.model.Usuario;
import edu.utl.dsm.helpdesk.model.Libro;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Prueba {

    public static void main(String[] args) throws Exception {
        //probarCon();
//        probarGetAll();
        probarActualizar();
//        probarRegister();
    }

    public static void probarLogin() {

        try {
            UsuarioController lg = new UsuarioController();
            Usuario u = lg.iniciarSesion("jessic", "1234");
            System.out.println(u.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    
    public static void probarLoginC() {

        try {
            UsuarioCQRS lg = new UsuarioCQRS();
            Usuario u = lg.iniciarSesion("jessic", "1234");
            System.out.println(lg.iniciarSesion("jessic", "1234"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
//    public static void probarGetAll() {
//        try {
//            LibrosDAO objLD = new LibrosDAO();
//            List<Libro> libros = objLD.getAllLibros();
//            for (int i = 0; i < libros.size(); i++) {
//                System.out.println(libros.get(i).toString());
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//    
//    public static void probarInsertL() {
//
//        Libro l = new Libro("libro1", "descripcion","tema");
//        LibrosDAO ld = new LibrosDAO();
//        try {
//            int id = ld.registrarLibro(l);
//            System.out.println("El registro ha sido completado " + id);
//        } catch (Exception ex) {
//            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
    
    public static void probarActualizar() {

        Libro l = new Libro(8,"li", "des","tema");
        LibroCQRS ld = new LibroCQRS();
        try {
            ld.actualizarLibro(l);
        } catch (Exception ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void probarEliminar() {

        LibroController ld = new LibroController();
        try {
            ld.eliminarLibro(5);
        } catch (Exception ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void probarCon() {
        ConexionMySQL objCon = new ConexionMySQL();
        try {
            objCon.open();
            System.out.println(objCon.toString());
            objCon.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void probarRegister() {

        Usuario usu = new Usuario("cris", "1234");
        UsuarioCQRS uD = new UsuarioCQRS();
        try {
            int id = uD.registrarUsuario(usu);
            System.out.println("El registro ha sido completado " + id);
        } catch (Exception ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void probarN() {

        UsuarioController rC = new UsuarioController();
        try {
            System.out.println(rC.validarNombreUsuario("jessi"));
        } catch (Exception ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
