
package Modelo;

import java.util.List;

public interface CRUD {
    public List listar();
    public int agregar(Object[] o);
    public int actualizar(Object[] o);
    public void eliminar(int id);
}
