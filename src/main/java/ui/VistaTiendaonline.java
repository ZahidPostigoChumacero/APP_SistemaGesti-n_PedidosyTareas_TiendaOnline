package ui;

import servicio.PedidoServicio;
import servicio.ProductoServicio;
import servicio.TareaServicio;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author futfl
 */
public class VistaTiendaonline extends JFrame {

    private final ProductoServicio productoService;
    private final PedidoServicio pedidoService;
    private final TareaServicio tareaService;

    public VistaTiendaonline(ProductoServicio ps, PedidoServicio peds, TareaServicio ts) {
        this.productoService = ps;
        this.pedidoService = peds;
        this.tareaService = ts;
        inicializar();
    }

    private void inicializar() {
        setTitle("Sistema de Gestión de Pedidos y Tareas");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnProductos = new JButton("Gestión de Productos");
        JButton btnPedidos = new JButton("Gestión de Pedidos");
        JButton btnTareas = new JButton("Gestión de Tareas");

        setLayout(new GridLayout(3, 1));
        add(btnProductos);
        add(btnPedidos);
        add(btnTareas);

        btnProductos.addActionListener(e -> {
            VistaGestionProductos f = new VistaGestionProductos(productoService);
            f.setVisible(true);
        });

        btnPedidos.addActionListener(e -> {
            VistaGestionPedidos f = new VistaGestionPedidos(pedidoService, productoService);
            f.setVisible(true);
        });

        btnTareas.addActionListener(e -> {
            VistaGestionTareas f = new VistaGestionTareas(tareaService);
            f.setVisible(true);
        });
    }
}
