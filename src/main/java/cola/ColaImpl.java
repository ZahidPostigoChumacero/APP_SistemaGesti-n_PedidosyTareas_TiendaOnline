package cola;

/**
 *
 * @author futfl
 */
public class ColaImpl implements ColaInterface {

    private NodoCola frente;
    private NodoCola fin;

    @Override
    public boolean estaVacia() {
        return frente == null;
    }

    @Override
    public void encolar(Object elemento) {
        NodoCola nuevo = new NodoCola(elemento);
        if (estaVacia()) {
            frente = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    @Override
    public Object desencolar() {
        if (estaVacia()) {
            return null;
        }
        Object e = frente.elemento;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        return e;
    }

    @Override
    public Object frente() {
        if (estaVacia()) {
            return null;
        }
        return frente.elemento;
    }
}
