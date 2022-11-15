package edu.utl.dsm.helpdesk.dao;

import edu.utl.dsm.helpdesk.db.ConexionMySQL;
import edu.utl.dsm.helpdesk.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioComandosDAO {
     public int registrarUsuario(Usuario usuario) throws Exception {
        //Se define la consulta a ejecutar
        String query = "insert into users (username, password) values (?,?);";
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
        pstmt.setString(1, usuario.getNombreUsuario());
        pstmt.setString(2, usuario.getContrasennia());
        //Objeto de tipo user
        pstmt.executeUpdate();

        rs = pstmt.getGeneratedKeys();

        if (rs.next()) {
            idGenerado = rs.getInt(1);
            usuario.setId(idGenerado);
        }
        //Cerramos las conexiones
        rs.close();
        pstmt.close();
        connMySQL.close();

        return idGenerado;
    }

    public Usuario iniciarSesion(String nombreUsuario, String contrasennia) throws Exception {

        //Se define la consulta a ejecutar
        String query = "SELECT * FROM users WHERE username = ? AND password = ?;";
        //Generamos el objeto de la conexion
        ConexionMySQL connMySQL = new ConexionMySQL();
        //Abrimos la conexion
        Connection conn = connMySQL.open();
        //Creamos el objeto para ejecutar la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);
        //Llenamos los parametros de la consulta
        pstmt.setString(1, nombreUsuario);
        pstmt.setString(2, contrasennia);
        //creamos el objeto para recibir los datos
        ResultSet rs = pstmt.executeQuery();
        //Objeto de tipo user
        Usuario user = new Usuario();

        if (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setNombreUsuario(rs.getString("username"));
            user.setContrasennia(rs.getString("password"));
        }
        //Cerramos las conexiones
        rs.close();
        pstmt.close();
        connMySQL.close();

        return user;
    }

    public Usuario validarNombreUsuario(String nombreUsuario) throws Exception {
        String query = "SELECT * FROM users WHERE username LIKE'" + nombreUsuario + "';";

        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        Usuario usuario = new Usuario();

        while (rs.next()) {

            usuario.setId(rs.getInt("id"));
            usuario.setContrasennia(rs.getString("password"));
            usuario.setNombreUsuario(rs.getString("username"));
        }

        rs.close();
        pstmt.close();
        connMySQL.close();
        
        return usuario;
        
    }
}
