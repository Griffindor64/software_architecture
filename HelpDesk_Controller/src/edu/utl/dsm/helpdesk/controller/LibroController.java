package edu.utl.dsm.helpdesk.controller;

import edu.utl.dsm.helpdesk.DDD.LibroAppServicies;
import edu.utl.dsm.helpdesk.MVVM.LibroViewModel;
import edu.utl.dsm.helpdesk.model.Libro;
import java.util.ArrayList;
import java.util.List;

public class LibroController {

    public List<Libro> mostrarLibros() throws Exception {
        LibroAppServicies objLibAS = new LibroAppServicies();
        List<Libro> libros = new ArrayList<>();
        libros = objLibAS.getAlllibros();
        return libros;
    }

    public List<Libro> consultarLibros(String filtro) throws Exception {
        LibroAppServicies objLibAS = new LibroAppServicies();
        List<Libro> libros = new ArrayList<>();
        libros = objLibAS.consultarLibros(filtro);
        return libros;
    }

    public String recuperarLibro(int id_libro) throws Exception {
        LibroAppServicies objLibAS = new LibroAppServicies();
        String archivo = objLibAS.recuperarLibro(id_libro);
        return archivo;
    }

    public List<LibroViewModel> mostrarLibrosPublic() throws Exception {
        LibroAppServicies objLibAS = new LibroAppServicies();
        List<LibroViewModel> libros = new ArrayList<>();
        libros = objLibAS.getAlllibrosPublic();
        return libros;
    }

    public int registrarLibro(Libro libro) throws Exception {
        LibroAppServicies objLibAS = new LibroAppServicies();
        int id = objLibAS.registrarLibro(libro);
        return id;
    }

    public int registrarLibroPublic(LibroViewModel libro) throws Exception {
        LibroAppServicies objLibAS = new LibroAppServicies();
        int id = objLibAS.registrarLibroPublic(libro);
        return id;
    }

    public Boolean actualizarLibro(Libro libro, int archivo) throws Exception {
        LibroAppServicies objLibAS = new LibroAppServicies();
        return objLibAS.actualizarLibro(libro, archivo);
    }

    public Boolean eliminarLibro(int id) throws Exception {
        LibroAppServicies objLibAS = new LibroAppServicies();
        return objLibAS.eliminarLibro(id);
    }

}
