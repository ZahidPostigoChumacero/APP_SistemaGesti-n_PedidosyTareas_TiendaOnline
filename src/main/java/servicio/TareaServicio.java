package servicio;

import cola.ColaImpl;
import modelo.Tarea;

/**
 *
 * @author futfl
 */
public class TareaServicio {

    private ColaImpl cola;

    public TareaServicio() {
        this.cola = new ColaImpl();
    }

    public void generarTarea(String idPedido, String descripcion) {
        cola.encolar(new Tarea(idPedido, descripcion));
    }

    public Tarea obtenerTareaActual() {
        Object o = cola.frente();
        if (o instanceof Tarea t) {
            return t;
        }
        return null;
    }

    public Tarea procesarSiguienteTarea() {
        Object o = cola.desencolar();
        if (o instanceof Tarea t) {
            return t;
        }
        return null;
    }

    public boolean hayTareas() {
        return !cola.estaVacia();
    }
}
