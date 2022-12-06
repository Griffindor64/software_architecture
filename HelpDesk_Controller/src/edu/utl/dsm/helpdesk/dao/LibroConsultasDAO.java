package edu.utl.dsm.helpdesk.dao;

import edu.utl.dsm.helpdesk.db.ConexionMySQL;
import edu.utl.dsm.helpdesk.model.Libro;
import edu.utl.dsm.helpdesk.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibroConsultasDAO {

    public List<Libro> mostrarLibros() throws Exception {
        //Se define la consulta a ejecutar
        String query = "select * from libro inner join usuario on libro.idUsuario = usuario.id";
        List<Libro> libros = new ArrayList<>();
        //Generamos el objeto de la conexion
        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Libro libro = new Libro();
            libro.setId(rs.getInt("libro.id"));
            libro.setNombre(rs.getString("nombre"));
            libro.setDescripcion(rs.getString("descripcion"));
            libro.setTema(rs.getString("tema"));
            libro.setArchivo(rs.getString("archivo"));
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("usuario.id"));
            usuario.setNombres(rs.getString("nombres"));
            usuario.setApellidos(rs.getString("apellidos"));
            usuario.setNombreUsuario(rs.getString("nombreUsuario"));
            usuario.setEstatus(rs.getInt("estatus"));
            usuario.setContrasennia(rs.getString("contrasennia"));
            usuario.setRol(rs.getInt("rol"));
            libro.setUsuario(usuario);
            libros.add(libro);
        }
        //Cerramos las conexiones
        rs.close();
        pstmt.close();
        connMySQL.close();

        return libros;

    }

    public List<Libro> consultarLibros(String filtro) throws Exception {
        String query = "SELECT * FROM libro inner join usuario on libro.idUsuario = usuario.id where nombre like '" + filtro + "' or descripcion like '"
                + filtro + "' or tema like '" + filtro + "';";
        List<Libro> libros = new ArrayList<>();
        //Generamos el objeto de la conexion
        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Libro libro = new Libro();
            libro.setId(rs.getInt("libro.id"));
            libro.setNombre(rs.getString("nombre"));
            libro.setDescripcion(rs.getString("descripcion"));
            libro.setTema(rs.getString("tema"));
            libro.setArchivo(rs.getString("archivo"));
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("usuario.id"));
            usuario.setNombres(rs.getString("nombres"));
            usuario.setApellidos(rs.getString("apellidos"));
            usuario.setNombreUsuario(rs.getString("nombreUsuario"));
            usuario.setEstatus(rs.getInt("estatus"));
            usuario.setContrasennia(rs.getString("contrasennia"));
            usuario.setRol(rs.getInt("rol"));
            libro.setUsuario(usuario);
            libros.add(libro);
        }
        //Cerramos las conexiones
        rs.close();
        pstmt.close();
        connMySQL.close();
        return libros;
    }

    public String recuperarLibro(int id_libro) throws Exception {
        String query = "SELECT archivo FROM libro where id like " + id_libro + ";";
        String archivo = null;
        //Generamos el objeto de la conexion
        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            archivo = rs.getString("archivo");
        }
        //Cerramos las conexiones
        rs.close();
        pstmt.close();
        connMySQL.close();
        return archivo;
    }
}
