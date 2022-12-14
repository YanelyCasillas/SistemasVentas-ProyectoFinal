package Controlador;

import Entidad.ClientesEntidad;
import Entidad.DetalleVentasEntidad;
import Entidad.ProductosEntidad;
import Entidad.VendedorEntidad;
import Entidad.VentasEntidad;
import Modelo.MetodoClientes;
import Modelo.MetodoProductos;
import Modelo.MetodoVendedor;
import Modelo.MetodoVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vistas.Clientes;
import vistas.Login;
import vistas.Principal;

import vistas.Ventas;

public class ControladorVentas implements ActionListener {

    Ventas venta = new Ventas();
    MetodoVentas metVenta = new MetodoVentas();
    VentasEntidad ventaEnti = new VentasEntidad();
    ClientesEntidad clienEnti = new ClientesEntidad();
    MetodoClientes metClien = new MetodoClientes();
    Clientes clien = new Clientes();
    Principal prin = new Principal();
    ProductosEntidad prodEnti = new ProductosEntidad();
    MetodoProductos metProd = new MetodoProductos();
    DetalleVentasEntidad detVentaEnti = new DetalleVentasEntidad();
    VendedorEntidad vendeEnti = new VendedorEntidad();
    Login log = new Login();
    MetodoVendedor metVende = new MetodoVendedor();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorVentas(Ventas venta, MetodoVentas metVenta, VentasEntidad ventaEnti,
            ClientesEntidad clienEnti, MetodoClientes metClien,
            Clientes clien, Principal prin, ProductosEntidad prodEnti,
            MetodoProductos metProd, DetalleVentasEntidad detVentaEnti, VendedorEntidad vendeEnti, Login log,
            MetodoVendedor metVende) {
        this.venta = venta;
        this.metVenta = metVenta;
        this.ventaEnti = ventaEnti;
        this.clienEnti = clienEnti;
        this.metClien = metClien;
        this.clien = clien;
        this.prin = prin;
        this.prodEnti = prodEnti;
        this.metProd = metProd;
        this.detVentaEnti = detVentaEnti;
        this.vendeEnti = vendeEnti;
        this.log = log;
        this.metVende = metVende;

        venta.btnAgregar.addActionListener(this);
        venta.btnBuscarCliente.addActionListener(this);
        venta.btnBuscarProducto.addActionListener(this);
        venta.btnCancelar.addActionListener(this);
        venta.btnGenerarVenta.addActionListener(this);

        venta.setTitle("MODULO DE VENTAS");
        Fecha();
        GenerarSerie();
        venta.txtVendedor.setText("Andyi");


    }

    int idprod;
    double totalPagar;
    int cant;
    double precio;

    public void BuscarCliente() {
        int r;
        String cod = venta.txtCodCliente.getText();
        if (venta.txtCodCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el codigo del cliente");
        } else {
            clienEnti = metClien.listarID(cod);
            if (clienEnti.getDni() != null) {
                venta.txtCliente.setText(clienEnti.getNom());
                venta.txtCodProducto.requestFocus();
            } else {
                r = JOptionPane.showConfirmDialog(null, "Cliente no registrado, ¿Desea registar? ");
                if (r == 0) {
                    prin.jdpPrincipal.add(clien);
                    clien.setVisible(true);
                }
            }
        }

    }

    public void BuscarProducto() {
        int id = Integer.parseInt(venta.txtCodProducto.getText());
        if (venta.txtCodProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el codigo del producto");
        } else {
            prodEnti = metProd.listarID(id);
            if (prodEnti.getId() != 0) {
                venta.txtProducto.setText(prodEnti.getNom());
                venta.txtPrecio.setText("" + prodEnti.getPrecio());
                venta.txtStock.setText("" + prodEnti.getStock());
            } else {
                JOptionPane.showMessageDialog(null, "Producto no registrado");
                venta.txtCodProducto.requestFocus();
            }
        }
    }

    public void AgregarProducto() {
        double total;
        int item = 0;
        modelo = (DefaultTableModel) venta.jtblVentas.getModel();
        item = item + 1;
        idprod = prodEnti.getId();
        String nomprod = venta.txtProducto.getText();
        precio = Double.parseDouble(venta.txtPrecio.getText());
        cant = Integer.parseInt(venta.jspCantidad.getValue().toString());
        int stock = Integer.parseInt(venta.txtStock.getText());
        total = cant * precio;
        ArrayList lista = new ArrayList();
        if (stock > 0) {
            if (cant >= 1) {
                lista.add(item);
                lista.add(idprod);
                lista.add(nomprod);
                lista.add(cant);
                lista.add(precio);
                lista.add(total);
                Object[] ob = new Object[6];
                ob[0] = lista.get(0);
                ob[1] = lista.get(1);
                ob[2] = lista.get(2);
                ob[3] = lista.get(3);
                ob[4] = lista.get(4);
                ob[5] = lista.get(5);
                modelo.addRow(ob);
                venta.jtblVentas.setModel(modelo);
                CalcularTotal();
            } else {

                JOptionPane.showMessageDialog(null, "La cantidad debe mayor a 0");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Producto sin Stock");
        }

    }

    public void CalcularTotal() {
        totalPagar = 0;
        for (int i = 0; i < venta.jtblVentas.getRowCount(); i++) {
            cant = Integer.parseInt(venta.jtblVentas.getValueAt(i, 3).toString());
            precio = Double.parseDouble(venta.jtblVentas.getValueAt(i, 4).toString());
            totalPagar = totalPagar + (cant * precio);

        }
        venta.txtTotalPagar.setText("" + totalPagar + "0");
    }

    public void GuardarVentas() {

        int idv = 5;
        int idc = clienEnti.getId();
        String serie = venta.txtSerie.getText();
        String fecha = venta.txtFecha.getText();
        double monto = totalPagar;
        String estado = "1";

        ventaEnti.setIdCliente(idc);
        ventaEnti.setIdVendedor(idv);
        ventaEnti.setSerie(serie);
        ventaEnti.setFecha(fecha);
        ventaEnti.setMonto(monto);
        ventaEnti.setEstado(estado);

        metVenta.GuardarVentas(ventaEnti);

    }

    public void GuardarDetalleVentas() {
        String idv = metVenta.IdVentas();
        int idve = Integer.parseInt(idv);
        for (int i = 0; i < venta.jtblVentas.getRowCount(); i++) {
            int idp = Integer.parseInt(venta.jtblVentas.getValueAt(i, 1).toString());
            int canti = Integer.parseInt(venta.jtblVentas.getValueAt(i, 3).toString());
            double pre = Double.parseDouble(venta.jtblVentas.getValueAt(i, 4).toString());
            detVentaEnti.setIdVentas(idve);
            detVentaEnti.setIdProducto(idp);
            detVentaEnti.setCantidad(canti);
            detVentaEnti.setPreVenta(pre);
            metVenta.GuardarDetalleVentas(detVentaEnti);
        }
    }

    public void Fecha() {
        Calendar calendar = new GregorianCalendar();
        venta.txtFecha.setText("" + calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void GenerarSerie() {
        String serie = metVenta.NroSerieVentas();
        if (serie == null) {
            venta.txtSerie.setText("0000001");
        } else {
            int increment = Integer.parseInt(serie);
            increment = increment + 1;
            venta.txtSerie.setText("000000" + increment);
        }
    }

    public void ActualizarStock() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            idprod = Integer.parseInt(venta.jtblVentas.getValueAt(i, 1).toString());
            cant = Integer.parseInt(venta.jtblVentas.getValueAt(i, 3).toString());
            prodEnti = metProd.listarID(idprod);
            int sa = prodEnti.getStock() - cant;
            metProd.ActualizarStock(sa, idprod);

        }

    }

    public void Nuevo() {
        LimpiarTabla();
        venta.txtCodCliente.setText("");
        venta.txtCliente.setText("");
        venta.jspCantidad.setValue(0);
        venta.txtCodProducto.setText("");
        venta.txtPrecio.setText("");
        venta.txtProducto.setText("");
        venta.txtStock.setText("");
        venta.txtTotalPagar.setText("");
        venta.txtCodCliente.requestFocus();

    }

    public void LimpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == venta.btnBuscarCliente) {
            BuscarCliente();
        }

        if (e.getSource() == venta.btnBuscarProducto) {
            BuscarProducto();
        }

        if (e.getSource() == venta.btnAgregar) {
            AgregarProducto();
        }

        if (e.getSource() == venta.btnGenerarVenta) {
            if (venta.txtTotalPagar.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe ingresar los datos");
            } else {
                GuardarVentas();
                GuardarDetalleVentas();
                ActualizarStock();
                JOptionPane.showMessageDialog(null, "La venta se realizó con éxito");
                Nuevo();
                GenerarSerie();
            }
        }
        if (e.getSource() == venta.btnCancelar) {
            Nuevo();
        }
    }

}
