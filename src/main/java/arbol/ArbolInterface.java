package arbol;

import modelo.Pedido;

/**
 *
 * @author futfl
 */
public interface ArbolInterface {

    void insertar(Pedido pedido);

    Pedido buscarPorId(String id);

    String recorridoInOrden();
}
