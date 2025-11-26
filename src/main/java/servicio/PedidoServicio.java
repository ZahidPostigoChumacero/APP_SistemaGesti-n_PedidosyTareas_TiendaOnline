package servicio;

import arbol.ArbolBSTImpl;
import java.time.LocalDate;
import pila.PilaImpl;
import modelo.Accion;
import modelo.Pedido;
import modelo.Producto;
import persistencia.PedidosXmlRepository;

/**
 *
 * @author futfl
 */
public class PedidoServicio {

    private ArbolBSTImpl arbolPedidos;
    private PedidosXmlRepository repo;
    private PilaImpl pilaAcciones;

    public PedidoServicio(String rutaXml) {
        this.repo = new PedidosXmlRepository(rutaXml);
        try {
            this.arbolPedidos = repo.cargar();
        } catch (Exception e) {
            this.arbolPedidos = new ArbolBSTImpl();
        }
        this.pilaAcciones = new PilaImpl();
    }

    public Pedido crearPedido(String id, String cliente) {
        Pedido p = new Pedido(id, cliente, LocalDate.now(), "PENDIENTE");
        arbolPedidos.insertar(p);
        pilaAcciones.push(new Accion(id, "Crear pedido"));
        guardar();
        return p;
    }

    public Pedido buscarPorId(String id) {
        return arbolPedidos.buscarPorId(id);
    }

    public void cambiarEstado(String idPedido, String nuevoEstado) {
        Pedido p = buscarPorId(idPedido);
        if (p != null) {
            p.setEstado(nuevoEstado);
            pilaAcciones.push(new Accion(idPedido, "Cambiar estado a " + nuevoEstado));
            guardar();
        }
    }

    public void agregarProductoAPedido(String idPedido, Producto producto) {
        Pedido p = buscarPorId(idPedido);
        if (p != null && p.agregarProducto(producto)) {
            pilaAcciones.push(new Accion(idPedido, "Agregar producto " + producto.getId()));
            guardar();
        }
    }

    public String listarPedidosOrdenados() {
        return arbolPedidos.recorridoInOrden();
    }

    public String deshacerUltimaAccion() {
        Object o = pilaAcciones.pop();
        if (o instanceof Accion a) {
            // Para este ejemplo no revertimos realmente, solo registramos el mensaje.
            return "Deshecha (lógicamente) la acción: " + a.getDescripcion()
                    + " sobre pedido " + a.getIdPedido();
        }
        return "No hay acciones que deshacer.";
    }

    private void guardar() {
        try {
            repo.guardar(arbolPedidos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
