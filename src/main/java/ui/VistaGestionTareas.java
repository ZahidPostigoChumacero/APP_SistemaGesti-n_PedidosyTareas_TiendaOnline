package ui;

import modelo.Tarea;
import servicio.TareaServicio;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author futfl
 */
public class VistaGestionTareas extends JFrame {

    private final TareaServicio tareaService;
    private JTextField txtIdPedido;
    private JTextField txtDescripcion;
    private JTextArea txtEstado;

    public VistaGestionTareas(TareaServicio tareaService) {
        this.tareaService = tareaService;
        inicializar();
    }

    private void inicializar() {
        setTitle("Gestión de Tareas (Cola)");
        setSize(500, 300);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(2, 2));
        form.add(new JLabel("ID Pedido:"));
        txtIdPedido = new JTextField();
        form.add(txtIdPedido);

        form.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        form.add(txtDescripcion);

        JPanel botones = new JPanel();
        JButton btnGenerar = new JButton("Generar tarea");
        JButton btnVer = new JButton("Ver tarea actual");
        JButton btnProcesar = new JButton("Procesar siguiente");

        botones.add(btnGenerar);
        botones.add(btnVer);
        botones.add(btnProcesar);

        txtEstado = new JTextArea();
        txtEstado.setEditable(false);

        setLayout(new BorderLayout());
        add(form, BorderLayout.NORTH);
        add(botones, BorderLayout.CENTER);
        add(new JScrollPane(txtEstado), BorderLayout.SOUTH);

        btnGenerar.addActionListener(e -> generar());
        btnVer.addActionListener(e -> verActual());
        btnProcesar.addActionListener(e -> procesar());
    }

    private void generar() {
        tareaService.generarTarea(
                txtIdPedido.getText().trim(),
                txtDescripcion.getText().trim()
        );
        txtEstado.setText("Tarea generada.\n");
    }

    private void verActual() {
        Tarea t = tareaService.obtenerTareaActual();
        if (t == null) {
            txtEstado.setText("No hay tareas en cola.\n");
        } else {
            txtEstado.setText("Tarea actual: " + t + "\n");
        }
    }

    private void procesar() {
        Tarea t = tareaService.procesarSiguienteTarea();
        if (t == null) {
            txtEstado.setText("No hay tareas que procesar.\n");
        } else {
            txtEstado.setText("Procesando: " + t + "\n");
        }
    }
}
