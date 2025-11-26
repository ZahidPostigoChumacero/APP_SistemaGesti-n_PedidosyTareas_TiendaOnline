package persistencia;

import arbol.ArbolBSTImpl;
import modelo.Pedido;
import modelo.Producto;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;

/**
 *
 * @author futfl
 */
public class PedidosXmlRepository {

    private final String rutaArchivo;

    public PedidosXmlRepository(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardar(ArbolBSTImpl arbol) throws Exception {
        // Para simplificar, obtenemos un recorrido inorden y lo volvemos a cargar
        // en un DOM manualmente no reversable;

        // Aquí asumimos que el árbol tiene pocos nodos; para guardarlo
        // recorreremos con un método auxiliar que vuelca a XML.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        Element root = doc.createElement("pedidos");
        doc.appendChild(root);

        guardarRec(arbol, doc, root, getRaiz(arbol));

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc),
                new StreamResult(new File(rutaArchivo)));
    }

    // exponemos la raíz usando reflexión solo para este guardado.
    private NodoArbol getRaiz(ArbolBSTImpl arbol) throws Exception {
        java.lang.reflect.Field f = ArbolBSTImpl.class.getDeclaredField("raiz");
        f.setAccessible(true);
        return (NodoArbol) f.get(arbol);
    }

    private void guardarRec(ArbolBSTImpl arbol, Document doc, Element root, NodoArbol nodo) {
        if (nodo == null) {
            return;
        }
        guardarRec(arbol, doc, root, nodo.izquierdo);

        Pedido p = nodo.pedido;
        Element ePed = doc.createElement("pedido");

        Element eId = doc.createElement("id");
        eId.setTextContent(p.getId());
        ePed.appendChild(eId);

        Element eCli = doc.createElement("cliente");
        eCli.setTextContent(p.getCliente());
        ePed.appendChild(eCli);

        Element eFecha = doc.createElement("fecha");
        eFecha.setTextContent(p.getFecha().toString());
        ePed.appendChild(eFecha);

        Element eEstado = doc.createElement("estado");
        eEstado.setTextContent(p.getEstado());
        ePed.appendChild(eEstado);

        root.appendChild(ePed);

        guardarRec(arbol, doc, root, nodo.derecho);
    }

    // Clase interna para acceder a los campos del árbol
    private static class NodoArbol {

        Pedido pedido;
        NodoArbol izquierdo;
        NodoArbol derecho;
    }

    public ArbolBSTImpl cargar() throws Exception {
        ArbolBSTImpl arbol = new ArbolBSTImpl();
        File f = new File(rutaArchivo);
        if (!f.exists()) {
            return arbol;
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(f);
        NodeList nodos = doc.getElementsByTagName("pedido");
        for (int i = 0; i < nodos.getLength(); i++) {
            Element ePed = (Element) nodos.item(i);
            String id = ePed.getElementsByTagName("id").item(0).getTextContent();
            String cliente = ePed.getElementsByTagName("cliente").item(0).getTextContent();
            LocalDate fecha = LocalDate.parse(
                    ePed.getElementsByTagName("fecha").item(0).getTextContent());
            String estado = ePed.getElementsByTagName("estado").item(0).getTextContent();
            Pedido p = new Pedido(id, cliente, fecha, estado);
            arbol.insertar(p);
        }
        return arbol;
    }
}
