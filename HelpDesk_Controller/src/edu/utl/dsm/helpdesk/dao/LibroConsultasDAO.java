package edu.utl.dsm.helpdesk.dao;

import edu.utl.dsm.helpdesk.db.ConexionMySQL;
import edu.utl.dsm.helpdesk.model.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibroConsultasDAO {
     public List<Libro> mostrarLibros() throws Exception {
        //Se define la consulta a ejecutar
        String query = "select * from libro;";
        List<Libro> libros = new ArrayList<>();
        //Generamos el objeto de la conexion
        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Libro libro = new Libro();
            libro.setId(rs.getInt("id"));
            libro.setNombre(rs.getString("nombre"));
            libro.setDescripcion(rs.getString("descripcion"));
            libro.setTema(rs.getString("tema"));
            libros.add(libro);
        }
        //Cerramos las conexiones
        rs.close();
        pstmt.close();
        connMySQL.close();

        return libros;

    }
}
