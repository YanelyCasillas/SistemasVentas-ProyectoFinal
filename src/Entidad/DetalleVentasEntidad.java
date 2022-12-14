
package Entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class DetalleVentasEntidad {
    int id;
    int idVentas;
    int idProducto;
    int cantidad;
    double preVenta;


    
    
}
