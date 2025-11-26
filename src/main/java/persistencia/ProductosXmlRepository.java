package persistencia;

import listaEnlazada.ListaEnlazadaDobleImpl;
import modelo.Producto;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 *
 * @author futfl
 */
public class ProductosXmlRepository {

    private final String rutaArchivo;

    public ProductosXmlRepository(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardar(ListaEnlazadaDobleImpl lista) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        Element root = doc.createElement("productos");
        doc.appendChild(root);

        for (int i = 0; i < lista.getLongitud(); i++) {
            Object obj = lista.obtenerEn(i);
            if (obj instanceof Producto p) {
                Element eProd = doc.createElement("producto");

                Element eId = doc.createElement("id");
                eId.setTextContent(p.getId());
                eProd.appendChild(eId);

                Element eNom = doc.createElement("nombre");
                eNom.setTextContent(p.getNombre());
                eProd.appendChild(eNom);

                Element eDesc = doc.createElement("descripcion");
                eDesc.setTextContent(p.getDescripcion());
                eProd.appendChild(eDesc);

                Element ePrecio = doc.createElement("precio");
                ePrecio.setTextContent(Double.toString(p.getPrecio()));
                eProd.appendChild(ePrecio);

                root.appendChild(eProd);
            }
        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(rutaArchivo));
        transformer.transform(source, result);
    }

    public ListaEnlazadaDobleImpl cargar() throws Exception {
        ListaEnlazadaDobleImpl lista = new ListaEnlazadaDobleImpl();
        File f = new File(rutaArchivo);
        if (!f.exists()) {
            return lista;
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(f);
        NodeList nodos = doc.getElementsByTagName("producto");
        for (int i = 0; i < nodos.getLength(); i++) {
            Element eProd = (Element) nodos.item(i);
            String id = eProd.getElementsByTagName("id").item(0).getTextContent();
            String nombre = eProd.getElementsByTagName("nombre").item(0).getTextContent();
            String desc = eProd.getElementsByTagName("descripcion").item(0).getTextContent();
            double precio = Double.parseDouble(
                    eProd.getElementsByTagName("precio").item(0).getTextContent());
            Producto p = new Producto(id, nombre, desc, precio);
            lista.insertarFinal(p);
        }
        return lista;
    }
}
