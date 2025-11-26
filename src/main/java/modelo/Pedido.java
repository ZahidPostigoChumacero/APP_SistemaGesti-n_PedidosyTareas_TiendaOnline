package modelo;

import java.time.LocalDate;

/**
 *
 * @author futfl
 */
public class Pedido {

    public static final int MAX_PRODUCTOS = 20;

    private String id;
    private String cliente;
    private LocalDate fecha;
    private Producto[] productos;
    private int cantidadProductos;
    private String estado; // PENDIENTE, PREPARACION, ENVIADO, COMPLETADO

    public Pedido(String id, String cliente, LocalDate fecha, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.estado = estado;
        this.productos = new Producto[MAX_PRODUCTOS];
        this.cantidadProductos = 0;
    }

    public String getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean agregarProducto(Producto p) {
        if (cantidadProductos >= MAX_PRODUCTOS) {
            return false;
        }
        productos[cantidadProductos++] = p;
        return true;
    }

    public Producto[] getProductos() {
        Producto[] copia = new Producto[cantidadProductos];
        for (int i = 0; i < cantidadProductos; i++) {
            copia[i] = productos[i];
        }
        return copia;
    }

    @Override
    public String toString() {
        return "Pedido " + id + " - " + cliente + " - " + fecha + " - " + estado;
    }
}
