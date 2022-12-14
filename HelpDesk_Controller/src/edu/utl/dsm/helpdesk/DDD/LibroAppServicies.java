package edu.utl.dsm.helpdesk.DDD;

import edu.utl.dsm.helpdesk.MVVM.LibroViewModel;
import edu.utl.dsm.helpdesk.cqrs.LibroCQRS;
import edu.utl.dsm.helpdesk.dao.LibroConsultasDAO;
import edu.utl.dsm.helpdesk.model.Libro;
import edu.utl.dsm.helpdesk.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class LibroAppServicies {

    public List<Libro> getAlllibros() throws Exception {
        LibroConsultasDAO objLibDAO = new LibroConsultasDAO();
        List<Libro> libros = new ArrayList<>();
        libros = objLibDAO.mostrarLibros();
        return libros;
    }

    public List<Libro> consultarLibros(String filtro) throws Exception {
        LibroConsultasDAO objLibDAO = new LibroConsultasDAO();
        List<Libro> libros = new ArrayList<>();
        libros = objLibDAO.consultarLibros(filtro);
        return libros;
    }

    public String recuperarLibro(int id_libro) throws Exception {
        LibroConsultasDAO objLibDAO = new LibroConsultasDAO();
        String archivo = objLibDAO.recuperarLibro(id_libro);
        return archivo;
    }

    public List<LibroViewModel> getAlllibrosPublic() throws Exception {
        LibroConsultasDAO objLibDAO = new LibroConsultasDAO();
        List<Libro> libros = new ArrayList<>();
        libros = objLibDAO.mostrarLibros();
        List<LibroViewModel> librosPublic = new ArrayList<>();
        for (int i = 0; i < libros.size(); i++) {
            librosPublic.add(new LibroViewModel(libros.get(i).getId(), libros.get(i).getNombre(), libros.get(i).getDescripcion(), libros.get(i).getTema()));
        }
        return librosPublic;
    }

    public int registrarLibroPublic(LibroViewModel l) throws Exception {
        LibroCQRS lCQRS = new LibroCQRS();
        Libro libro = new Libro(l.getNombre_lbr(), l.getDescripcion_lbr(), l.getTema_lbr(), new Usuario(0, "", "", "", "", 0));
        int id = lCQRS.registrarLibro(libro);
        return id;
    }

    public int registrarLibro(Libro libro) throws Exception {
        LibroCQRS lCQRS = new LibroCQRS();
        int id = lCQRS.registrarLibro(libro);
        return id;
    }

    public Boolean actualizarLibro(Libro libro, int archivo) throws Exception {
        LibroCQRS lCQRS = new LibroCQRS();
        return lCQRS.actualizarLibro(libro, archivo);
    }

    public Boolean eliminarLibro(int id) throws Exception {
        LibroCQRS lCQRS = new LibroCQRS();
        return lCQRS.eliminarLibro(id);
    }
}
