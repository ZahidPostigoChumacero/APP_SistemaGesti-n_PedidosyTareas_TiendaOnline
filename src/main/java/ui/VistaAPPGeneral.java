package ui;

import servicio.PedidoServicio;
import servicio.ProductoServicio;
import servicio.TareaServicio;

import javax.swing.*;

/**
 *
 * @author futfl
 */
public class VistaAPPGeneral {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            ProductoServicio ps = new ProductoServicio("productos.xml");
            PedidoServicio peds = new PedidoServicio("pedidos.xml");
            TareaServicio ts = new TareaServicio();

            VistaTiendaonline frm = new VistaTiendaonline(ps, peds, ts);
            frm.setVisible(true);
        });

    }
}
