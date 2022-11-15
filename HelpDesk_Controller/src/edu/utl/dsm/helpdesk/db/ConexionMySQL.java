package edu.utl.dsm.helpdesk.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author German Vargas
 */
public class ConexionMySQL {
    
    Connection conexion;
    
    public Connection open() throws Exception{
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/escuela";
        String user = "root";
        String password = "root";
        
        Class.forName(driver);
        
        conexion = DriverManager.getConnection(url, user, password);
        return conexion;
    }
    
    public void close(){
        if(conexion != null){
            try {
                conexion.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
