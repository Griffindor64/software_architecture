package edu.utl.dsm.helpdesk.dao;

import edu.utl.dsm.helpdesk.db.ConexionMySQL;
import edu.utl.dsm.helpdesk.model.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LibroComandosDAO {

    public int registrarLibro(Libro b) throws Exception {
        //Se define la consulta a ejecutar
        String query = "insert into libro (nombre, descripcion, tema, idUsuario, archivo) values (?,?,?,?,?);";
        int idGenerado = -1;
        //Generamos el objeto de la conexion
        ConexionMySQL connMySQL = new ConexionMySQL();
        //Abrimos la conexion
        Connection conn = connMySQL.open();
        //Creamos el objeto para ejecutar la consulta
        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        //creamos el objeto para recibir los datos
        ResultSet rs = null;

        //Llenamos los parametros de la consulta
        pstmt.setString(1, b.getNombre());
        pstmt.setString(2, b.getDescripcion());
        pstmt.setString(3, b.getTema());
        pstmt.setInt(4, b.getUsuario().getId());
        pstmt.setString(5, b.getArchivo());
        //Objeto de tipo user
        pstmt.executeUpdate();

        rs = pstmt.getGeneratedKeys();

        if (rs.next()) {
            idGenerado = rs.getInt(1);
            b.setId(idGenerado);
        }
        //Cerramos las conexiones
        rs.close();
        pstmt.close();
        connMySQL.close();

        return idGenerado;
    }

    public void actualizarLibro(Libro libro, int archivo) throws Exception {
        //Se define la consulta a ejecutar
        String query;
        if (archivo == 2) {
            query = "update libro set nombre = '" + libro.getNombre() + "', "
                    + "descripcion = '" + libro.getDescripcion() + "' , tema = '" + libro.getTema() + "'"
                    + " where id = " + libro.getId() + ";";
        } else {
            query = "update libro set nombre = '" + libro.getNombre() + "', "
                    + "descripcion = '" + libro.getDescripcion() + "' , tema = '" + libro.getTema() + "', "
                    + "archivo = '" + libro.getArchivo() + "'"
                    + " where id = " + libro.getId() + ";";
        }

        //Generamos el objeto de la conexion
        ConexionMySQL connMySQL = new ConexionMySQL();
        //Abrimos la conexion
        Connection conn = connMySQL.open();
        //Creamos el objeto para ejecutar la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);

        //Objeto de tipo user
        pstmt.executeUpdate();
        conn.close();
        pstmt.close();
        connMySQL.close();

    }

    public void eliminarLibro(int id) throws SQLException, Exception {
        String query = "update libro set estatus = 0 where id = " + id + ";";

        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(query);

        pstmt.executeUpdate();

        conn.close();
        pstmt.close();
        connMySQL.close();
    }

    public Libro validarNombreLibro(String nombre) throws Exception {
        String query = "SELECT * FROM libro WHERE nombre LIKE'" + nombre + "';";

        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        Libro libro = new Libro();

        while (rs.next()) {

            libro.setId(rs.getInt("id"));
            libro.setNombre(rs.getString("nombre"));
            libro.setTema(rs.getString("tema"));
            libro.setDescripcion(rs.getString("descripcion"));
        }

        rs.close();
        pstmt.close();
        connMySQL.close();

        return libro;

    }

}
