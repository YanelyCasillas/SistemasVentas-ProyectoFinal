
package Entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class VendedorEntidad {
    private int IdVendedor;
    private String Dni;
    private String Nombres;
    private String apePaterno;
    private String apeMaterno;
    private String contraseña;
    private int telefono;
    private String estado;
    private String tipoUser;
    


    
    
}
