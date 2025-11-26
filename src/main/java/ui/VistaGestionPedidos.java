package ui;

import modelo.Producto;
import servicio.PedidoServicio;
import servicio.ProductoServicio;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author futfl
 */
public class VistaGestionPedidos extends JFrame {

    private final PedidoServicio pedidoService;
    private final ProductoServicio productoService;

    private JTextField txtIdPedido;
    private JTextField txtCliente;
    private JTextField txtIdProducto;
    private JTextArea txtListado;

    public VistaGestionPedidos(PedidoServicio pedidoService,
            ProductoServicio productoService) {
        this.pedidoService = pedidoService;
        this.productoService = productoService;
        inicializar();
    }

    private void inicializar() {
        setTitle("Gestión de Pedidos");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(3, 2));
        form.add(new JLabel("ID Pedido:"));
        txtIdPedido = new JTextField();
        form.add(txtIdPedido);

        form.add(new JLabel("Cliente:"));
        txtCliente = new JTextField();
        form.add(txtCliente);

        form.add(new JLabel("ID Producto a agregar:"));
        txtIdProducto = new JTextField();
        form.add(txtIdProducto);

        JPanel botones = new JPanel();
        JButton btnCrear = new JButton("Crear pedido");
        JButton btnAgregarProd = new JButton("Agregar producto");
        JButton btnEstado = new JButton("Marcar como ENVIADO");
        JButton btnListar = new JButton("Listar pedidos");
        JButton btnDeshacer = new JButton("Deshacer última acción");

        botones.add(btnCrear);
        botones.add(btnAgregarProd);
        botones.add(btnEstado);
        botones.add(btnListar);
        botones.add(btnDeshacer);

        txtListado = new JTextArea();
        txtListado.setEditable(false);

        setLayout(new BorderLayout());
        add(form, BorderLayout.NORTH);
        add(botones, BorderLayout.CENTER);
        add(new JScrollPane(txtListado), BorderLayout.SOUTH);

        btnCrear.addActionListener(e -> crearPedido());
        btnAgregarProd.addActionListener(e -> agregarProducto());
        btnEstado.addActionListener(e -> cambiarEstado());
        btnListar.addActionListener(e -> listar());
        btnDeshacer.addActionListener(e -> deshacer());
    }

    private void crearPedido() {
        String id = txtIdPedido.getText().trim();
        String cli = txtCliente.getText().trim();
        pedidoService.crearPedido(id, cli);
        JOptionPane.showMessageDialog(this, "Pedido creado.");
    }

    private void agregarProducto() {
        String idPed = txtIdPedido.getText().trim();
        String idProd = txtIdProducto.getText().trim();
        Producto p = productoService.buscarPorId(idProd);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            return;
        }
        pedidoService.agregarProductoAPedido(idPed, p);
        JOptionPane.showMessageDialog(this, "Producto agregado al pedido.");
    }

    private void cambiarEstado() {
        String idPed = txtIdPedido.getText().trim();
        pedidoService.cambiarEstado(idPed, "ENVIADO");
        JOptionPane.showMessageDialog(this, "Pedido marcado como ENVIADO.");
    }

    private void listar() {
        txtListado.setText(pedidoService.listarPedidosOrdenados());
    }

    private void deshacer() {
        String msg = pedidoService.deshacerUltimaAccion();
        JOptionPane.showMessageDialog(this, msg);
    }
}
