package edu.utl.dsm.helpdesk.dao;

import edu.utl.dsm.helpdesk.db.ConexionMySQL;
import edu.utl.dsm.helpdesk.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioComandosDAO {

    public int registrarUsuario(Usuario usuario) throws Exception {
        //Se define la consulta a ejecutar
        String query = "insert into usuario (nombres, apellidos, nombreUsuario, contrasennia, rol)"
                + " values (?,?,?,?,?);";
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
        pstmt.setString(1, usuario.getNombres());
        pstmt.setString(2, usuario.getApellidos());
        pstmt.setString(3, usuario.getNombreUsuario());
        pstmt.setString(4, usuario.getContrasennia());
        pstmt.setInt(5, usuario.getRol());
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

    public void actualizarUsuario(Usuario usuario) throws Exception {
        //Se define la consulta a ejecutar
        String query = "update usuario set nombres = '" + usuario.getNombres()
                + "', apellidos = '" + usuario.getApellidos()
                + "' , nombreUsuario = '" + usuario.getNombreUsuario()
                + "', contrasennia = '" + usuario.getContrasennia()
                + "', rol = '" + usuario.getRol()
                + "'   where id = " + usuario.getId() + ";";
        
        //Generamos el objeto de la conexion
                ConexionMySQL connMySQL = new ConexionMySQL();
        //Abrimos la conexion
        Connection conn = connMySQL.open();
        //Creamos el objeto para ejecutar la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);

        pstmt.executeUpdate();
        conn.close();
        pstmt.close();
        connMySQL.close();

    }

    public void eliminarUsuario(int id) throws SQLException, Exception {
        String query = "update usuario set estatus = 0 where id = " + id + ";";

        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(query);

        pstmt.executeUpdate();

        conn.close();
        pstmt.close();
        connMySQL.close();
    }

    public Usuario iniciarSesion(String nombreUsuario, String contrasennia) throws Exception {

        //Se define la consulta a ejecutar
        String query = "SELECT * FROM usuario WHERE nombreUsuario = ? AND contrasennia = ? AND estatus = 1;";
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
        Usuario usuario = new Usuario();

        if (rs.next()) {
            usuario.setId(rs.getInt("id"));
            usuario.setNombres(rs.getString("nombres"));
            usuario.setApellidos(rs.getString("apellidos"));
            usuario.setNombreUsuario(rs.getString("nombreUsuario"));
            usuario.setEstatus(rs.getInt("estatus"));
            usuario.setContrasennia(rs.getString("contrasennia"));
            usuario.setRol(rs.getInt("rol"));
        }
        //Cerramos las conexiones
        rs.close();
        pstmt.close();
        connMySQL.close();

        return usuario;
    }

    public Usuario validarNombreUsuario(String nombreUsuario) throws Exception {
        String query = "SELECT * FROM usuario WHERE nombreUsuario LIKE'" + nombreUsuario + "';";

        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        Usuario usuario = new Usuario();

        while (rs.next()) {
            usuario.setId(rs.getInt("id"));
            usuario.setNombres(rs.getString("nombres"));
            usuario.setApellidos(rs.getString("apellidos"));
            usuario.setNombreUsuario(rs.getString("nombreUsuario"));
            usuario.setEstatus(rs.getInt("estatus"));
            usuario.setContrasennia(rs.getString("contrasennia"));
            usuario.setRol(rs.getInt("rol"));
        }

        rs.close();
        pstmt.close();
        connMySQL.close();

        return usuario;

    }

    public List<Usuario> mostrarUsuario(int rol) throws Exception {
        //Se define la consulta a ejecutar
        String query = "select * from usuario where rol = " + rol + " AND estatus = 1;";
        List<Usuario> alumnos = new ArrayList<>();
        //Generamos el objeto de la conexion
        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Usuario alumno = new Usuario();
            alumno.setId(rs.getInt("id"));
            alumno.setNombres(rs.getString("nombres"));
            alumno.setApellidos(rs.getString("apellidos"));
            alumno.setNombreUsuario(rs.getString("nombreUsuario"));
            alumno.setEstatus(rs.getInt("estatus"));
            alumno.setContrasennia(rs.getString("contrasennia"));
            alumno.setRol(rs.getInt("rol"));
            alumnos.add(alumno);
        }
        //Cerramos las conexiones
        rs.close();
        pstmt.close();
        connMySQL.close();

        return alumnos;

    }

}
