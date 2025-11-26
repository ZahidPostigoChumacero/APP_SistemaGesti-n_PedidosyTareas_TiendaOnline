package modelo;

/**
 *
 * @author futfl
 */
public class Tarea {

    private String idPedido;
    private String descripcion;

    public Tarea(String idPedido, String descripcion) {
        this.idPedido = idPedido;
        this.descripcion = descripcion;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Pedido " + idPedido + ": " + descripcion;
    }
}
