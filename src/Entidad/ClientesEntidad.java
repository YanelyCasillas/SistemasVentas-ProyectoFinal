
package Entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ClientesEntidad {
    int id;
    String dni;
    String nom;
    String dir;
    String estado;

    
}
