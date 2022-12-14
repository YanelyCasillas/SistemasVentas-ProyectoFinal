
package Modelo;

import Entidad.ProductosEntidad;
import Entidad.VendedorEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

public class MetodoProductos implements CRUD{
    int r;

    PreparedStatement ps;
    ResultSet rs;
    
    private static final Conexion con = Conexion.saberEstado();
    
    public String ExisteProducto(String nom){
        try {
            String sql="select * from producto where Nombres=?";
            ps=con.getCn().prepareStatement(sql);
            ps.setString(1, nom);
            rs=ps.executeQuery();
            while (rs.next()) {                
                return rs.getString("Nombres");
            }
        } catch (Exception e) {
        }
        return "";
    }
    
    public int ActualizarStock(int cant,int idp){
        String sql= "update producto set Stock=? where IdProducto=?";
        try {

            ps=con.getCn().prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, idp);
            r=ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        finally {
            con.cerrarConexion();
        }
        return r;
    }

    public ProductosEntidad listarID(int id){
        ProductosEntidad prodEnti = new ProductosEntidad();
        String sql="select * from producto where IdProducto=?";
        try {

            ps=con.getCn().prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()) {                
                prodEnti.setId(rs.getInt(1));
                prodEnti.setNom(rs.getString(2));
                prodEnti.setPrecio(rs.getDouble(3));
                prodEnti.setStock(rs.getInt(4));
                prodEnti.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        finally {
            con.cerrarConexion();
        }
        return prodEnti;
    }
    
    @Override
    public List listar() {
        List<ProductosEntidad> lista = new ArrayList<>();
        String sql = "select * from producto";
        try {
            //con=cn.Conectar();
            ps=con.getCn().prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                ProductosEntidad prodEnti = new ProductosEntidad();
                prodEnti.setId(rs.getInt(1));
                prodEnti.setNom(rs.getString(2));
                prodEnti.setPrecio(rs.getDouble(3));
                prodEnti.setStock(rs.getInt(4));
                prodEnti.setEstado(rs.getString(5));
                lista.add(prodEnti);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        finally {
            con.cerrarConexion();
        }
        return lista;
    }

    @Override
    public int agregar(Object[] o) {
        int r=0;
        String sql="insert into producto(Nombres,Precio,Stock,Estado)values(?,?,?,?)";
        try {
            //con=cn.Conectar();
            ps=con.getCn().prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            r=ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        finally {
            con.cerrarConexion();
        }
        return r;
    }

    @Override
    public int actualizar(Object[] o) {
        int r=0;
        String sql="update producto set Nombres=?,Precio=?,Stock=?,Estado=? where IdProducto=?";
        try {
            //con=cn.Conectar();
            ps=con.getCn().prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            r=ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        finally {
            con.cerrarConexion();
        }
        return r;
    }

    @Override
    public void eliminar(int id) {
        String sql="delete from producto where IdProducto = ?";
        try {
            //con=cn.Conectar();
            ps=con.getCn().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        finally {
            con.cerrarConexion();
        }
    }
}
