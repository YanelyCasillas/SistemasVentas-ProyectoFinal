
package Controlador;

import Entidad.VendedorEntidad;
import Modelo.MetodoVendedor;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import vistas.Clientes;
import vistas.Login;
import vistas.Principal;
import vistas.Productos;
import vistas.ReporteVentas;
import vistas.Ventas;
import vistas.Vendedor;

public class ControladorPrincipal implements ActionListener{
    
    Principal prin = new Principal();
    Ventas venta = new Ventas();
    Clientes clien = new Clientes();
    Productos prod = new Productos();
    Vendedor vende = new Vendedor();
    ReporteVentas repVenta = new ReporteVentas();
    Login log = new Login();
//    VendedorEntidad vendeEnti= new VendedorEntidad();
//    MetodoVendedor metVende = new MetodoVendedor();

    public ControladorPrincipal(Principal prin,Ventas venta,Clientes clien,Productos prod,Vendedor vende,ReporteVentas repVenta,
                                Login log) {
        this.prin=prin;
        this.venta=venta;
        this.clien=clien;
        this.prod=prod;
        this.vende=vende;
        this.repVenta=repVenta;
        this.log=log;
//        this.metVende=metVende;
//        this.vendeEnti=vendeEnti;
        
        this.prin.jmiSalir.addActionListener(this);
        this.prin.jmiGenerarVentas.addActionListener(this);
        this.prin.jmiCliente.addActionListener(this);
        this.prin.jmiProducto.addActionListener(this);
        this.prin.jmiVendedor.addActionListener(this);
        this.prin.jmiReporteVenta.addActionListener(this);
        this.prin.jmiCerrarSesion.addActionListener(this);
        
        prin.setTitle("ALL STAR TECHNOLOGY");
        prin.setExtendedState(MAXIMIZED_BOTH);
//        VendedorVenta();
    }
    
    
    public void CentrarVentana(JInternalFrame frame){
        prin.jdpPrincipal.add(frame);
        Dimension dimension=prin.jdpPrincipal.getSize();
        Dimension fventas=frame.getSize();
        frame.setLocation((dimension.width -fventas.width)/2, (dimension.height -fventas.height)/2);
        frame.show();
        
    }
    
    public void CerrarSesion(){
        prin.dispose();
        log.setVisible(true);
        log.txtDNI.setText("");
        log.passContraseña.setText("");
    
    }
    
    
    public void Salir(){
        System.exit(0);
    }
    
//    public void VendedorVenta(){
//        String dniVende= log.txtDNI.getText();
//        vendeEnti=metVende.listarID(dniVende);
//        venta.txtVendedor.setText(vendeEnti.getNombres());
//    
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        try {
            if (e.getSource() == prin.jmiSalir) {
                Salir();
            }
            
            if (e.getSource() == prin.jmiGenerarVentas) {
                CentrarVentana(venta);
            }
            
            if (e.getSource() == prin.jmiCliente) {
                CentrarVentana(clien);
            }
            
            if (e.getSource() == prin.jmiProducto) {
                CentrarVentana(prod);
            }
            
            if (e.getSource() == prin.jmiVendedor) {
                CentrarVentana(vende);
            }
            
            if (e.getSource() == prin.jmiCerrarSesion) {
                CerrarSesion();
            }


        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, a);
        }
    }
    
}
