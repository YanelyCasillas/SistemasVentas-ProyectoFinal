
package Modelo;

import Entidad.ClientesEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class MetodoClientes implements CRUD{

    PreparedStatement ps;
    ResultSet rs;
    private static final Conexion con = Conexion.saberEstado();

    
    public String ExisteCliente(String dni){
        try {
            String sql="select * from cliente where Dni=?";
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
    
    public ClientesEntidad listarID(String dni){
        ClientesEntidad clienEnti = new ClientesEntidad();
        String sql="select * from cliente where Dni=?";
        try {

            ps=con.getCn().prepareStatement(sql);
            ps.setString(1, dni);
            rs=ps.executeQuery();
            while (rs.next()) {                
                clienEnti.setId(rs.getInt(1));
                clienEnti.setDni(rs.getString(2));
                clienEnti.setNom(rs.getString(3));
                clienEnti.setDir(rs.getString(4));
                clienEnti.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        finally {
            con.cerrarConexion();
        }
        return clienEnti;
    }

    @Override
    public List listar() {
        List<ClientesEntidad> lista = new ArrayList<>();
        String sql = "select * from cliente";
        try {

            ps=con.getCn().prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                ClientesEntidad clienEnti = new ClientesEntidad();
                clienEnti.setId(rs.getInt(1));
                clienEnti.setDni(rs.getString(2));
                clienEnti.setNom(rs.getString(3));
                clienEnti.setDir(rs.getString(4));
                clienEnti.setEstado(rs.getString(5));
                lista.add(clienEnti);
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
        String sql="insert into cliente(DNI,Nombres,Direccion,Estado)values(?,?,?,?)";
        try {

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
        String sql="update cliente set Dni=?,Nombres=?,Direccion=?,Estado=? where IdCliente=?";
        try {

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
        String sql="delete from cliente where IdCliente = ?";
        try {

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
