
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ModificarXML {

    public static void main(String[] args) {

        try {
            File archivo = new File("Ranking_prueba.xml");

            DocumentBuilderFactory fabrica =
                    DocumentBuilderFactory.newInstance();

            fabrica.setFeature(
                "http://apache.org/xml/features/nonvalidating/load-external-dtd",
                false
            );

            DocumentBuilder constructor =
                    fabrica.newDocumentBuilder();

            Document documento = constructor.parse(archivo);

            documento.getDocumentElement().normalize();

            NodeList lista =
                    documento.getElementsByTagName("jugador");

            boolean encontrado = false;

            for (int i = 0; i < lista.getLength(); i++) {

                Element jugador = (Element) lista.item(i);

                String nombre = jugador
                        .getElementsByTagName("nombre")
                        .item(0)
                        .getTextContent();

                if (nombre.equals("Pablo")) {

                    Element puntuacion = (Element) jugador
                            .getElementsByTagName("puntuacion")
                            .item(0);

                    puntuacion.setTextContent("390");

                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {

                TransformerFactory transformerFabrica =
                        TransformerFactory.newInstance();

                Transformer transformer =
                        transformerFabrica.newTransformer();

                transformer.setOutputProperty(
                        OutputKeys.INDENT, "yes");

                transformer.setOutputProperty(
                        OutputKeys.ENCODING, "UTF-8");

                transformer.setOutputProperty(
                        OutputKeys.DOCTYPE_SYSTEM, "Ranking.dtd");

                DOMSource origen = new DOMSource(documento);

                StreamResult destino =
                        new StreamResult(archivo);

                transformer.transform(origen, destino);

                System.out.println(
                    "Puntuacion modificada correctamente");

            } else {
                System.out.println("Jugador no encontrado.");
            }

        } catch (Exception e) {

            System.out.println(
                "Error al modificar el XML: " + e.getMessage());
        }
    }
}
