
package Modelo;

import Entidad.DetalleVentasEntidad;
import Entidad.VentasEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class MetodoVentas {

    PreparedStatement ps;
    ResultSet rs;
    
    int r=0;
    private static final Conexion con = Conexion.saberEstado();
    public String NroSerieVentas(){
        String serie="";
        String sql="select max(NumeroVentas) from ventas";
        try {
            ps=con.getCn().prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                serie=rs.getString(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        finally {
            con.cerrarConexion();
        }
        return serie;
    }
    
    public String IdVentas(){
        String idv="";
        String sql="select max(IdVentas) from ventas";
        try {
            ps=con.getCn().prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                idv=rs.getString(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        finally {
            con.cerrarConexion();
        }
        return idv;
    }
    
    public int GuardarVentas(VentasEntidad venta){
        String sql="insert into ventas(IdCliente,IdVendedor,NumeroVentas,FechaVentas,Monto,Estado)values(?,?,?,?,?,?)";
        try {
            ps=con.getCn().prepareStatement(sql);
            ps.setInt(1, venta.getIdCliente());
            ps.setInt(2, venta.getIdVendedor());
            ps.setString(3, venta.getSerie());
            ps.setString(4, venta.getFecha());
            ps.setDouble(5, venta.getMonto());
            ps.setString(6, venta.getEstado());
            r=ps.executeUpdate();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        finally {
            con.cerrarConexion();
        }
        return r;
    
    }
    
    public int GuardarDetalleVentas(DetalleVentasEntidad dv){
        String sql="insert into detalle_ventas(IdVentas,IdProducto,Cantidad,PrecioVenta)values(?,?,?,?)";
        try {
            //con=cn.Conectar();
            ps=con.getCn().prepareStatement(sql);
            ps.setInt(1, dv.getIdVentas());
            ps.setInt(2, dv.getIdProducto());
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getPreVenta());
            r=ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        finally {
            con.cerrarConexion();
        }
        return r;
    }    
    
}
