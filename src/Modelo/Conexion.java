
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Conexion {
public static Conexion instancia;// aplicar el singleton
    private Connection con;
    String DRIVER = "com.mysql.jdbc.Driver";
    String USUARIO = "root";
    String PASSWORD = "";
    String URL = "jdbc:mysql://localhost:3306/bd_ventas";

    private Conexion() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized static Conexion saberEstado() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getCn() {
        return con;
    }

    public void cerrarConexion() {
        instancia = null;
    }
}
