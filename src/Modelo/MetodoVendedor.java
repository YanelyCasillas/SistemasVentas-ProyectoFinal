package Modelo;

import Entidad.ClientesEntidad;
import Entidad.VendedorEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

public class MetodoVendedor implements CRUD {

    PreparedStatement ps;
    ResultSet rs;

    private static final Conexion con = Conexion.saberEstado();
    
    public String ExisteVendedor(String dni){
        try {
            String sql="select * from vendedor where Dni=?";
            ps=con.getCn().prepareStatement(sql);
            ps.setString(1, dni);
            rs=ps.executeQuery();
            while (rs.next()) {                
                return rs.getString("Dni");
            }
        } catch (Exception e) {
        }
        return "";
    }

    public VendedorEntidad listarID(String dni) {
        VendedorEntidad vendeEnti = new VendedorEntidad();
        String sql = "select * from vendedor where Dni=?";
        try {
            ps = con.getCn().prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            while (rs.next()) {
                vendeEnti.setIdVendedor(rs.getInt(1));
                vendeEnti.setDni(rs.getString(2));
                vendeEnti.setNombres(rs.getString(3));
                vendeEnti.setApePaterno(rs.getString(4));
                vendeEnti.setApeMaterno(rs.getString(5));
                vendeEnti.setTelefono(rs.getInt(7));
                vendeEnti.setEstado(rs.getString(8));
                vendeEnti.setTipoUser(rs.getString(9));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        } finally {
            con.cerrarConexion();
        }
        return vendeEnti;
    }

    @Override
    public List listar() {
        List<VendedorEntidad> lista = new ArrayList<>();
        String sql = "select * from vendedor";
        try {
            //con=cn.Conectar();
            ps = con.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                VendedorEntidad vendeEnti = new VendedorEntidad();
                vendeEnti.setIdVendedor(rs.getInt(1));
                vendeEnti.setDni(rs.getString(2));
                vendeEnti.setNombres(rs.getString(3));
                vendeEnti.setApePaterno(rs.getString(4));
                vendeEnti.setApeMaterno(rs.getString(5));
                vendeEnti.setTelefono(rs.getInt(7));
                vendeEnti.setEstado(rs.getString(8));
                vendeEnti.setTipoUser(rs.getString(9));

                lista.add(vendeEnti);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        } finally {
            con.cerrarConexion();
        }
        return lista;

    }

    @Override
    public int agregar(Object[] o) {
        int r = 0;
        String sql = "insert into vendedor(Dni,Nombres,ApellidoPaterno,ApellidoMaterno,Contraseña,Telefono,Estado,Tipo_user)values(?,?,?,?,?,?,?,?)";
        try {
            ps = con.getCn().prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            ps.setObject(7, o[6]);
            ps.setObject(8, o[7]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        } finally {
            con.cerrarConexion();
        }
        return r;
    }

    @Override
    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "update vendedor set Dni=?,Nombres=?,ApellidoPaterno=?,ApellidoMaterno=?,Contraseña=?,Telefono=?,Estado=?,Tipo_user=? where IdVendedor=?";
        try {
            ps = con.getCn().prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            ps.setObject(7, o[6]);
            ps.setObject(8, o[7]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        } finally {
            con.cerrarConexion();
        }
        return r;
    }

    @Override
    public void eliminar(int id) {
        String sql = "delete from vendedor where IdVendedor = ?";
        try {
            ps = con.getCn().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        } finally {
            con.cerrarConexion();
        }
    }

}
