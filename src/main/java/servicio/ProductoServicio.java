package servicio;

import listaEnlazada.ListaEnlazadaDobleImpl;
import modelo.Producto;
import persistencia.ProductosXmlRepository;

/**
 *
 * @author futfl
 */
public class ProductoServicio {

    private ListaEnlazadaDobleImpl catalogo;
    private ProductosXmlRepository repo;

    public ProductoServicio(String rutaXml) {
        this.repo = new ProductosXmlRepository(rutaXml);
        try {
            this.catalogo = repo.cargar();
        } catch (Exception e) {
            this.catalogo = new ListaEnlazadaDobleImpl();
        }
    }

    public void agregarProducto(Producto p) {
        catalogo.insertarFinal(p);
        guardar();
    }

    public Producto buscarPorId(String id) {
        Object o = catalogo.buscarPorId(id);
        if (o instanceof Producto) {
            return (Producto) o;
        }
        return null;
    }

    public String listarProductos() {
        return catalogo.imprimirAdelante();
    }

    public boolean eliminarPorId(String id) {
        for (int i = 0; i < catalogo.getLongitud(); i++) {
            Object o = catalogo.obtenerEn(i);
            if (o instanceof Producto p && p.getId().equals(id)) {
                catalogo.retirarEn(i);
                guardar();
                return true;
            }
        }
        return false;
    }

    private void guardar() {
        try {
            repo.guardar(catalogo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
