
package Modelo;

import Entidad.VendedorEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class MetodoCuenta {
    PreparedStatement ps;
    ResultSet rs;
    
//    Conexion con = new Conexion();
//    Connection acceso;
    
    private static final Conexion con = Conexion.saberEstado();
    public VendedorEntidad ValidarVendedor(String dni, String contraseña){
        VendedorEntidad vende = new VendedorEntidad();
        String sql="select * from vendedor where Dni=? and Contraseña=?";
        try {
            //acceso=con.Conectar();
            ps=con.getCn().prepareStatement(sql);
            ps.setString(1, dni);
            ps.setString(2, contraseña);
            rs=ps.executeQuery();
            while (rs.next()) {                
                vende.setIdVendedor(rs.getInt(1));
                vende.setDni(rs.getString(2));
                vende.setNombres(rs.getString(3));
                vende.setApePaterno(rs.getString(4));
                vende.setApeMaterno(rs.getString(5));
                vende.setContraseña(rs.getString(6));
                vende.setTelefono(rs.getInt(7));
                vende.setEstado(rs.getString(8));
                vende.setTipoUser(rs.getString(9));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        finally {
            con.cerrarConexion();
        }
        return vende;
        
    }
    
}
