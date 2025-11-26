package arbol;

import modelo.Pedido;

/**
 *
 * @author futfl
 */
public class NodoArbol {

    Pedido pedido;
    NodoArbol izquierdo;
    NodoArbol derecho;

    NodoArbol(Pedido pedido) {
        this.pedido = pedido;
    }
}
