package arbol;

import modelo.Pedido;

/**
 *
 * @author futfl
 */
public class ArbolBSTImpl implements ArbolInterface {

    private NodoArbol raiz;

    @Override
    public void insertar(Pedido pedido) {
        raiz = insertarRec(raiz, pedido);
    }

    private NodoArbol insertarRec(NodoArbol nodo, Pedido pedido) {
        if (nodo == null) {
            return new NodoArbol(pedido);
        }
        int cmp = pedido.getId().compareTo(nodo.pedido.getId());
        if (cmp < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, pedido);
        } else if (cmp > 0) {
            nodo.derecho = insertarRec(nodo.derecho, pedido);
        } else {
            nodo.pedido = pedido; // reemplaza
        }
        return nodo;
    }

    @Override
    public Pedido buscarPorId(String id) {
        NodoArbol actual = raiz;
        while (actual != null) {
            int cmp = id.compareTo(actual.pedido.getId());
            if (cmp == 0) {
                return actual.pedido;
            }
            if (cmp < 0) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
        }
        return null;
    }

    @Override
    public String recorridoInOrden() {
        StringBuilder sb = new StringBuilder();
        inOrden(raiz, sb);
        return sb.toString();
    }

    private void inOrden(NodoArbol nodo, StringBuilder sb) {
        if (nodo == null) {
            return;
        }
        inOrden(nodo.izquierdo, sb);
        sb.append(nodo.pedido).append("\n");
        inOrden(nodo.derecho, sb);
    }
}
