package modelo;

/**
 *
 * @author futfl
 */
public class Accion {

    private String idPedido;
    private String descripcion;

    public Accion(String idPedido, String descripcion) {
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
        return "[" + idPedido + "] " + descripcion;
    }
}
