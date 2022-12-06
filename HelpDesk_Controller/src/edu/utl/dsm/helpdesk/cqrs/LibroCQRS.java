
package edu.utl.dsm.helpdesk.cqrs;

import edu.utl.dsm.helpdesk.dao.LibroComandosDAO;
import edu.utl.dsm.helpdesk.model.Libro;

public class LibroCQRS {
    
    public Boolean validarNombreLibro(String nombre) throws Exception{
        LibroComandosDAO objLibDAO = new LibroComandosDAO();
        Libro libro = objLibDAO.validarNombreLibro(nombre);
        if (libro.getId() == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public Boolean validarVacio(Libro libro) throws Exception{
        if (libro.getNombre().equals("")){
            return false;
        } else {
            return true;            
        }
    }
    
    public int registrarLibro(Libro libro) throws Exception {
        LibroComandosDAO objLibDAO = new LibroComandosDAO();
        int idL = 0;
        if(validarVacio(libro)){
            if (!validarNombreLibro(libro.getNombre())) {
                idL = objLibDAO.registrarLibro(libro);
            }
        }
        return idL;
    }
    
    public Boolean actualizarLibro(Libro libro, int archivo) throws Exception {
        LibroComandosDAO objLibDAO = new LibroComandosDAO();
        if(validarVacio(libro)){
                objLibDAO.actualizarLibro(libro, archivo);
                return true;
        } else {
            return false;
        }
    }
    
    public Boolean eliminarLibro(int id) throws Exception{
        LibroComandosDAO objLibDAO = new LibroComandosDAO();
        if(id == 0){
            return false;
        } else {
            objLibDAO.eliminarLibro(id);
            return true;
        } 
    }
    
    
    
}
