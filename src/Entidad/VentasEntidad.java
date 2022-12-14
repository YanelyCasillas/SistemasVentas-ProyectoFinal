
package Entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class VentasEntidad {
    int id;
    int idCliente;
    int idVendedor;
    String serie;
    String Fecha;
    double monto;
    String estado;


}
