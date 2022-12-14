package Controlador;

import Entidad.ProductosEntidad;
import Modelo.MetodoProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vistas.Productos;

public class ControladorProductos implements ActionListener {

    Productos prod = new Productos();
    MetodoProductos metProd = new MetodoProductos();
    ProductosEntidad prodEnti = new ProductosEntidad();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorProductos(Productos prod, MetodoProductos metProd, ProductosEntidad prodEnti) {
        this.prod = prod;
        this.metProd = metProd;
        this.prodEnti = prodEnti;

        this.prod.btnAgregar.addActionListener(this);
        this.prod.btnActualizar.addActionListener(this);
        this.prod.btnEditar.addActionListener(this);
        this.prod.btnNuevo.addActionListener(this);
        this.prod.btnEliminar.addActionListener(this);

        prod.setTitle("MODULO DE PRODUCTOS");
        listar();
        
    }

    int id;

    public void selecTabla() {
        int fila = prod.jtlbProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            id = Integer.parseInt(prod.jtlbProductos.getValueAt(fila, 0).toString());
            String nom = prod.jtlbProductos.getValueAt(fila, 1).toString();
            String precio = prod.jtlbProductos.getValueAt(fila, 2).toString();
            String stock = prod.jtlbProductos.getValueAt(fila, 3).toString();
            String es = prod.jtlbProductos.getValueAt(fila, 4).toString();

            prod.txtNombre.setText(nom);
            prod.txtPrecio.setText(precio);
            prod.txtStock.setText(stock);
            prod.cbxEstado.setSelectedItem(es);
        }
    }


    public void listar() {
        List<ProductosEntidad> lista = metProd.listar();
        modelo = (DefaultTableModel) prod.jtlbProductos.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNom();
            ob[2] = lista.get(i).getPrecio();
            ob[3] = lista.get(i).getStock();
            ob[4] = lista.get(i).getEstado();
            modelo.addRow(ob);

        }
        prod.jtlbProductos.setModel(modelo);
        
        
    }




    
    public void agregar() {

        if (prod.txtNombre.getText().equals("") || prod.txtPrecio.getText().equals("") || prod.txtStock.getText().equals("")
                || prod.cbxEstado.getSelectedItem().equals("SELECCIONAR")) {
            JOptionPane.showMessageDialog(null, "Debe Llenar todos los campos");
        } else {
            if (metProd.ExisteProducto(prod.txtNombre.getText()).equals("")) {
                String nom = prod.txtNombre.getText();
                        Double precio = Double.parseDouble(prod.txtPrecio.getText());
                        int stock = Integer.parseInt(prod.txtStock.getText());
                        String es = prod.cbxEstado.getSelectedItem().toString();

                        Object[] ob = new Object[4];
                        ob[0] = nom;
                        ob[1] = precio;
                        ob[2] = stock;
                        ob[3] = es;
                        metProd.agregar(ob);
                        nuevo();
            } else {
                JOptionPane.showMessageDialog(null, "El producto ya existe");
                nuevo();
                prod.txtNombre.requestFocus();
            }

        }
    }

    public void actualizar() {
        if (prod.txtNombre.getText().equals("") || prod.txtPrecio.getText().equals("") || prod.txtStock.getText().equals("")
                || prod.cbxEstado.getSelectedItem().equals("SELECCIONAR")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el boton editar");
        } else {
            String nom = prod.txtNombre.getText();
            Double precio = Double.parseDouble(prod.txtPrecio.getText());
            int stock = Integer.parseInt(prod.txtStock.getText());
            String es = prod.cbxEstado.getSelectedItem().toString();
            Object[] ob = new Object[5];
            ob[0] = nom;
            ob[1] = precio;
            ob[2] = stock;
            ob[3] = es;
            ob[4] = id;
            metProd.actualizar(ob);
        }

    }

    public void eliminar() {
        int fila = prod.jtlbProductos.getSelectedRow();
        int ids = Integer.parseInt(prod.jtlbProductos.getValueAt(fila, 0).toString());
        if (fila <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            metProd.eliminar(ids);
        }
    }

    public void nuevo() {
        prod.txtNombre.setText("");
        prod.txtPrecio.setText("");
        prod.txtStock.setText("");
        prod.cbxEstado.setSelectedIndex(0);
        prod.txtNombre.requestFocus();
    }

    public void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == prod.btnAgregar) {
            agregar();
            limpiarTabla();
            listar();
        }

        if (e.getSource() == prod.btnActualizar) {
            actualizar();
            limpiarTabla();
            listar();
            nuevo();
        }

        if (e.getSource() == prod.btnEditar) {
            selecTabla();

        }

        if (e.getSource() == prod.btnNuevo) {
            nuevo();
            limpiarTabla();
            listar();
        }

        if (e.getSource() == prod.btnEliminar) {
            eliminar();
            limpiarTabla();
            listar();
            nuevo();
        }

    }

}
