package Controlador;

import Entidad.ClientesEntidad;
import Modelo.MetodoClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vistas.Clientes;

public class ControladorClientes implements ActionListener {

    Clientes clien = new Clientes();
    MetodoClientes metClien = new MetodoClientes();
    ClientesEntidad clienEnti = new ClientesEntidad();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorClientes(Clientes clien, MetodoClientes metClien, ClientesEntidad clienEnti) {
        this.clien = clien;
        this.metClien = metClien;
        this.clienEnti = clienEnti;

        this.clien.btnAgregar.addActionListener(this);
        this.clien.btnActualizar.addActionListener(this);
        this.clien.btnEditar.addActionListener(this);
        this.clien.btnNuevo.addActionListener(this);
        this.clien.btnEliminar.addActionListener(this);

        clien.setTitle("MODULO DE CLIENTES");
        listar();

    }

    int id;

    public void selecTabla() {
        int fila = clien.jtableClientes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            id = Integer.parseInt(clien.jtableClientes.getValueAt(fila, 0).toString());
            String dni = clien.jtableClientes.getValueAt(fila, 1).toString();
            String nom = clien.jtableClientes.getValueAt(fila, 2).toString();
            String dir = clien.jtableClientes.getValueAt(fila, 3).toString();
            String es = clien.jtableClientes.getValueAt(fila, 4).toString();
            clien.txtDni.setText(dni);
            clien.txtNombres.setText(nom);
            clien.txtDireccion.setText(dir);
            clien.cbxEstado.setSelectedItem(es);
        }
    }

    public void listar() {
        List<ClientesEntidad> lista = metClien.listar();
        modelo = (DefaultTableModel) clien.jtableClientes.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getDni();
            ob[2] = lista.get(i).getNom();
            ob[3] = lista.get(i).getDir();
            ob[4] = lista.get(i).getEstado();
            modelo.addRow(ob);

        }
        clien.jtableClientes.setModel(modelo);
    }

    public void agregar() {

        if (clien.txtDni.getText().equals("") || clien.txtNombres.getText().equals("") || clien.txtDireccion.getText().equals("")
                || clien.cbxEstado.getSelectedItem().equals("SELECCIONAR")) {
            JOptionPane.showMessageDialog(null, "Debe Llenar todos los campos");
        } else {

            if (metClien.ExisteCliente(clien.txtDni.getText()).equals("")) {
                if (clien.txtDni.getText().length() == 8) {
                    String dni = clien.txtDni.getText();
                    String nom = clien.txtNombres.getText();
                    String dir = clien.txtDireccion.getText();
                    String es = clien.cbxEstado.getSelectedItem().toString();

                    Object[] ob = new Object[4];
                    ob[0] = dni;
                    ob[1] = nom;
                    ob[2] = dir;
                    ob[3] = es;
                    metClien.agregar(ob);
                    nuevo();
                } else {
                    JOptionPane.showMessageDialog(null, "El DNI debe tener 8 digitos");
                    clien.txtDni.setText("");
                    clien.txtDni.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "El cliente ya existe");
                nuevo();
                clien.txtDni.requestFocus();
            }

        }
    }

    public void actualizar() {
        if (clien.txtDni.getText().equals("") || clien.txtNombres.getText().equals("") || clien.txtDireccion.getText().equals("")
                || clien.cbxEstado.getSelectedItem().equals("SELECCIONAR")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el boton editar");
        } else {
            if (metClien.ExisteCliente(clien.txtDni.getText()).equals("")) {
                if (clien.txtDni.getText().length() == 8) {
                    String dni = clien.txtDni.getText();
                    String nom = clien.txtNombres.getText();
                    String dir = clien.txtDireccion.getText();
                    String es = clien.cbxEstado.getSelectedItem().toString();
                    Object[] ob = new Object[5];
                    ob[0] = dni;
                    ob[1] = nom;
                    ob[2] = dir;
                    ob[3] = es;
                    ob[4] = id;
                    metClien.actualizar(ob);
                    nuevo();
                } else {
                    JOptionPane.showMessageDialog(null, "El DNI debe tener 8 digitos");
                    clien.txtDni.setText("");
                    clien.txtDni.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "El cliente ya existe");
                nuevo();
                clien.txtDni.requestFocus();
            }

        }

    }

    public void eliminar() {
        int fila = clien.jtableClientes.getSelectedRow();
        int ids = Integer.parseInt(clien.jtableClientes.getValueAt(fila, 0).toString());
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            metClien.eliminar(ids);

        }
    }

    public void nuevo() {
        clien.txtDni.setText("");
        clien.txtDireccion.setText("");
        clien.txtNombres.setText("");
        clien.cbxEstado.setSelectedIndex(0);
        clien.txtDni.requestFocus();
    }

    public void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clien.btnAgregar) {
            agregar();
            limpiarTabla();
            listar();
//            nuevo();
        }

        if (e.getSource() == clien.btnActualizar) {
            actualizar();
            limpiarTabla();
            listar();
        }

        if (e.getSource() == clien.btnEditar) {
            selecTabla();

        }

        if (e.getSource() == clien.btnNuevo) {
            nuevo();
            limpiarTabla();
            listar();
        }

        if (e.getSource() == clien.btnEliminar) {
            eliminar();
            limpiarTabla();
            listar();
            nuevo();
        }

    }

}
