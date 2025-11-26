package ui;

import modelo.Producto;
import servicio.ProductoServicio;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author futfl
 */
public class VistaGestionProductos extends JFrame {

    private final ProductoServicio productoService;

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextArea txtListado;

    public VistaGestionProductos(ProductoServicio productoService) {
        this.productoService = productoService;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Gestión de Productos");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panelForm = new JPanel(new GridLayout(4, 2));
        panelForm.add(new JLabel("ID:"));
        txtId = new JTextField();
        panelForm.add(txtId);

        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panelForm.add(txtDescripcion);

        panelForm.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelForm.add(txtPrecio);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar por ID");
        JButton btnListar = new JButton("Listar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        txtListado = new JTextArea();
        txtListado.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtListado);

        setLayout(new BorderLayout());
        add(panelForm, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Eventos
        btnAgregar.addActionListener(e -> agregarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnListar.addActionListener(e -> listarProductos());
    }

    private void agregarProducto() {
        try {
            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            String desc = txtDescripcion.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());

            Producto p = new Producto(id, nombre, desc, precio);
            productoService.agregarProducto(p);
            JOptionPane.showMessageDialog(this, "Producto agregado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio inválido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProducto() {
        String id = txtId.getText().trim();
        boolean ok = productoService.eliminarPorId(id);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Producto eliminado.");
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.");
        }
    }

    private void listarProductos() {
        txtListado.setText(productoService.listarProductos());
    }
}
