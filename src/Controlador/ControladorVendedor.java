package Controlador;

import Entidad.VendedorEntidad;
import Modelo.Hash;
import Modelo.MetodoVendedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vistas.Vendedor;

public class ControladorVendedor implements ActionListener {

    VendedorEntidad vendeEnti = new VendedorEntidad();
    MetodoVendedor metVende = new MetodoVendedor();
    Vendedor vende = new Vendedor();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorVendedor(VendedorEntidad vendeEnti, MetodoVendedor metVende, Vendedor vende) {
        this.vende = vende;
        this.metVende = metVende;
        this.vendeEnti = vendeEnti;

        this.vende.btnAgregar.addActionListener(this);
        this.vende.btnActualizar.addActionListener(this);
        this.vende.btnEditar.addActionListener(this);
        this.vende.btnNuevo.addActionListener(this);
        this.vende.btnEliminar.addActionListener(this);

        vende.setTitle("MODULO DE VENDEDORES");
        listar();

    }

    int id;

    public void selecTabla() {
        int fila = vende.jtblVendedor.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            id = Integer.parseInt(vende.jtblVendedor.getValueAt(fila, 0).toString());
            String dni = vende.jtblVendedor.getValueAt(fila, 1).toString();
            String nom = vende.jtblVendedor.getValueAt(fila, 2).toString();
            String apePaterno = vende.jtblVendedor.getValueAt(fila, 3).toString();
            String apeMaterno = vende.jtblVendedor.getValueAt(fila, 4).toString();
            String telefono = vende.jtblVendedor.getValueAt(fila, 5).toString();
            String es = vende.jtblVendedor.getValueAt(fila, 6).toString();
            String tipoUser = vende.jtblVendedor.getValueAt(fila, 7).toString();
            vende.txtDNI.setText(dni);
            vende.txtNombres.setText(nom);
            vende.txtApePaterno.setText(apePaterno);
            vende.txtApeMaterno.setText(apeMaterno);
            vende.txtTelefono.setText(telefono);
            vende.cbxEstado.setSelectedItem(es);
            vende.cbxTipoUsuario.setSelectedItem(tipoUser);
        }
    }

    public void listar() {
        List<VendedorEntidad> lista = metVende.listar();
        modelo = (DefaultTableModel) vende.jtblVendedor.getModel();
        Object[] ob = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getIdVendedor();
            ob[1] = lista.get(i).getDni();
            ob[2] = lista.get(i).getNombres();
            ob[3] = lista.get(i).getApePaterno();
            ob[4] = lista.get(i).getApeMaterno();
            ob[5] = lista.get(i).getTelefono();
            ob[6] = lista.get(i).getEstado();
            ob[7] = lista.get(i).getTipoUser();
            modelo.addRow(ob);

        }
        vende.jtblVendedor.setModel(modelo);
    }

    public void agregar() {
        String pass = new String(vende.passContraseña.getPassword());
        String passcon = new String(vende.passConfirmar.getPassword());

        if (vende.txtDNI.getText().equals("") || vende.txtNombres.getText().equals("") || vende.txtApePaterno.getText().equals("")
                || vende.txtApeMaterno.equals("") || vende.txtTelefono.equals("") || vende.cbxEstado.getSelectedItem().equals("SELECCIONAR")
                || vende.cbxTipoUsuario.getSelectedItem().equals("SELECCIONAR")) {
            JOptionPane.showMessageDialog(null, "Debe Llenar todos los campos");
        } else {
            if (metVende.ExisteVendedor(vende.txtDNI.getText()).equals("")) {
                if (vende.txtDNI.getText().length() == 8) {
                    if (vende.txtTelefono.getText().length() == 9) {
                        if (pass.equals(passcon)) {
                            String nuevoPass = Hash.sha1(pass);
                            String dni = vende.txtDNI.getText();
                            String nom = vende.txtNombres.getText();
                            String apePaterno = vende.txtApePaterno.getText();
                            String apeMaterno = vende.txtApeMaterno.getText();
                            String contraseña = nuevoPass;
                            int telefono = Integer.parseInt(vende.txtTelefono.getText());
                            String es = vende.cbxEstado.getSelectedItem().toString();
                            String tipoUser = vende.cbxTipoUsuario.getSelectedItem().toString();

                            Object[] ob = new Object[8];
                            ob[0] = dni;
                            ob[1] = nom;
                            ob[2] = apePaterno;
                            ob[3] = apeMaterno;
                            ob[4] = contraseña;
                            ob[5] = telefono;
                            ob[6] = es;
                            ob[7] = tipoUser;
                            metVende.agregar(ob);
                            nuevo();
                        } else {
                            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "El telefono debe tener 9 digitos");
                        vende.txtTelefono.setText("");
                        vende.txtTelefono.requestFocus();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "El DNI debe tener 8 digitos");
                    vende.txtDNI.setText("");
                    vende.txtDNI.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "El vendedor ya existe");
                vende.txtDNI.setText("");
                vende.txtDNI.requestFocus();
            }

        }
    }

    public void actualizar() {
        String pass = new String(vende.passContraseña.getPassword());
        String passcon = new String(vende.passConfirmar.getPassword());
        if (vende.txtDNI.getText().equals("") || vende.txtNombres.getText().equals("") || vende.txtApePaterno.getText().equals("")
                || vende.txtApeMaterno.equals("") || vende.txtTelefono.equals("") || vende.cbxEstado.getSelectedItem().equals("SELECCIONAR")
                || vende.cbxTipoUsuario.getSelectedItem().equals("SELECCIONAR")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el boton editar");
        } else {

            if (pass.equals(passcon)) {
                String dni = vende.txtDNI.getText();
                String nom = vende.txtNombres.getText();
                String apePaterno = vende.txtApePaterno.getText();
                String apeMaterno = vende.txtApeMaterno.getText();
                String contraseña = pass;
                int telefono = Integer.parseInt(vende.txtTelefono.getText());
                String es = vende.cbxEstado.getSelectedItem().toString();
                String tipoUser = vende.cbxTipoUsuario.getSelectedItem().toString();

                Object[] ob = new Object[9];
                ob[0] = dni;
                ob[1] = nom;
                ob[2] = apePaterno;
                ob[3] = apeMaterno;
                ob[4] = contraseña;
                ob[5] = telefono;
                ob[6] = es;
                ob[7] = tipoUser;
                ob[8] = id;
                metVende.actualizar(ob);
                nuevo();
            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            }

        }

    }

    public void eliminar() {
        int fila = vende.jtblVendedor.getSelectedRow();
        int ids = Integer.parseInt(vende.jtblVendedor.getValueAt(fila, 0).toString());
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            metVende.eliminar(ids);
        }
    }

    public void nuevo() {
        vende.txtDNI.setText("");
        vende.txtNombres.setText("");
        vende.txtApePaterno.setText("");
        vende.txtApeMaterno.setText("");
        vende.txtTelefono.setText("");
        vende.passConfirmar.setText("");
        vende.passContraseña.setText("");
        vende.cbxEstado.setSelectedIndex(0);
        vende.cbxTipoUsuario.setSelectedIndex(0);;
    }

    public void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vende.btnAgregar) {
            agregar();
            limpiarTabla();
            listar();

        }

        if (e.getSource() == vende.btnActualizar) {
            actualizar();
            limpiarTabla();
            listar();

        }

        if (e.getSource() == vende.btnEditar) {
            selecTabla();

        }

        if (e.getSource() == vende.btnNuevo) {
            nuevo();
            limpiarTabla();
            listar();
        }

        if (e.getSource() == vende.btnEliminar) {
            eliminar();
            limpiarTabla();
            listar();
            nuevo();
        }

    }
}
