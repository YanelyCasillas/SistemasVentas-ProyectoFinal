
package Controlador;

import Entidad.VendedorEntidad;
import Modelo.TextPrompt;
import Modelo.MetodoCuenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vistas.Login;
import Modelo.Hash;
import Modelo.MetodoVendedor;
import vistas.Principal;
import vistas.Ventas;


public class ControladorLogin implements ActionListener{
    Login log = new Login();
    MetodoCuenta metCuen = new MetodoCuenta();
    VendedorEntidad vendeEnti = new VendedorEntidad();
    Principal prin = new Principal();


    public ControladorLogin(Login log,MetodoCuenta metCuen,VendedorEntidad vendeEnti,Principal prin) {
        this.log = log;
        this.metCuen = metCuen;
        this.vendeEnti = vendeEnti;
        this.prin = prin;

        
        this.log.btnSesion.addActionListener(this);
        
        log.setTitle("ALL STAR TECHNOLOGY");
        log.setLocationRelativeTo(null);
        TextPrompt dni = new TextPrompt("Ejm. 12345678", log.txtDNI);
        TextPrompt contra = new TextPrompt("Ejm. C0ntr@s3ñ4#", log.passContraseña);
    }
    
    public void Validar() {

        String pass = new String(log.passContraseña.getPassword());
        String dni = new String(log.txtDNI.getText());
        if (!log.txtDNI.getText().equals("") && !pass.equals("")) {
            String nuevoPass = Hash.sha1(pass);
            
            vendeEnti=metCuen.ValidarVendedor(dni, nuevoPass);
            if (vendeEnti.getDni() != null && vendeEnti.getContraseña() != null) {
                prin.setVisible(true);
                log.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "El DNI o Contraseña son incorrectos");
                log.txtDNI.setText("");
                log.passContraseña.setText("");
                log.txtDNI.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar sus datos");
            log.txtDNI.requestFocus();
        }

    }
    
    public void Privilegios(){
        if (vendeEnti.getTipoUser().equals("Vendedor")) {
            prin.jmiVendedor.setVisible(false);
        } 
    }
    


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == log.btnSesion) {
                Validar();
                Privilegios();
            }

        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, a);
        }
    }
}
