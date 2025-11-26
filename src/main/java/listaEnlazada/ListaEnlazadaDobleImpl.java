package listaEnlazada;

/**
 *
 * @author futfl
 */
public class ListaEnlazadaDobleImpl implements ListaDobleInterface {

    private NodoDoble cabeza;
    private NodoDoble cola;
    private int longitud;

    public ListaEnlazadaDobleImpl() {
        cabeza = null;
        cola = null;
        longitud = 0;
    }

    @Override
    public boolean estaVacia() {
        return cabeza == null;
    }

    @Override
    public void insertarInicio(Object elemento) {
        NodoDoble nuevo = new NodoDoble(elemento);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
        longitud++;
    }

    @Override
    public void insertarFinal(Object elemento) {
        NodoDoble nuevo = new NodoDoble(elemento);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        longitud++;
    }

    @Override
    public void insertarEn(Object elemento, int posicion) {
        if (posicion <= 0) {
            insertarInicio(elemento);
        } else if (posicion >= longitud) {
            insertarFinal(elemento);
        } else {
            NodoDoble actual = cabeza;
            for (int i = 0; i < posicion; i++) {
                actual = actual.siguiente;
            }
            NodoDoble nuevo = new NodoDoble(elemento);
            NodoDoble anterior = actual.anterior;

            nuevo.anterior = anterior;
            nuevo.siguiente = actual;
            anterior.siguiente = nuevo;
            actual.anterior = nuevo;
            longitud++;
        }
    }

    @Override
    public Object retirarInicio() {
        if (estaVacia()) {
            return null;
        }
        Object e = cabeza.elemento;
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        }
        longitud--;
        return e;
    }

    @Override
    public Object retirarFinal() {
        if (estaVacia()) {
            return null;
        }
        Object e = cola.elemento;
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            cola = cola.anterior;
            cola.siguiente = null;
        }
        longitud--;
        return e;
    }

    @Override
    public Object retirarEn(int posicion) {
        if (posicion <= 0) {
            return retirarInicio();
        }
        if (posicion >= longitud - 1) {
            return retirarFinal();
        }

        NodoDoble actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = actual.siguiente;
        }

        NodoDoble ant = actual.anterior;
        NodoDoble sig = actual.siguiente;

        ant.siguiente = sig;
        sig.anterior = ant;
        longitud--;
        return actual.elemento;
    }

    @Override
    public Object buscarPorId(String id) {
        NodoDoble actual = cabeza;
        while (actual != null) {
            try {
                java.lang.reflect.Method m
                        = actual.elemento.getClass().getMethod("getId");
                Object valor = m.invoke(actual.elemento);
                if (valor != null && valor.toString().equals(id)) {
                    return actual.elemento;
                }
            } catch (Exception e) {
                return null;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    @Override
    public String imprimirAdelante() {
        StringBuilder sb = new StringBuilder();
        NodoDoble actual = cabeza;
        while (actual != null) {
            sb.append(actual.elemento).append("\n");
            actual = actual.siguiente;
        }
        return sb.toString();
    }

    @Override
    public String imprimirAtras() {
        StringBuilder sb = new StringBuilder();
        NodoDoble actual = cola;
        while (actual != null) {
            sb.append(actual.elemento).append("\n");
            actual = actual.anterior;
        }
        return sb.toString();
    }

    @Override
    public int getLongitud() {
        return longitud;
    }

    public Object obtenerEn(int posicion) {
        if (posicion < 0 || posicion >= longitud) {
            return null;
        }
        NodoDoble actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = actual.siguiente;
        }
        return actual.elemento;
    }
}
