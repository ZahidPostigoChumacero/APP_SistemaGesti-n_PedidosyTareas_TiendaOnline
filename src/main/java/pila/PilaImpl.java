package pila;

/**
 *
 * @author futfl
 */
public class PilaImpl implements PilaInterface {

    private NodoPila cima;

    @Override
    public boolean estaVacia() {
        return cima == null;
    }

    @Override
    public void push(Object elemento) {
        NodoPila nuevo = new NodoPila(elemento);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    @Override
    public Object pop() {
        if (estaVacia()) {
            return null;
        }
        Object e = cima.elemento;
        cima = cima.siguiente;
        return e;
    }

    @Override
    public Object cima() {
        if (estaVacia()) {
            return null;
        }
        return cima.elemento;
    }
} 
 