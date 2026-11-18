
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LeerXML
{
    public static void main(String[] args)
    {
        try
        {
            File archivo = new File("Ranking.xml");

            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();

            fabrica.setFeature(
                "http://apache.org/xml/features/nonvalidating/load-external-dtd",
                false
            );

            DocumentBuilder constructor = fabrica.newDocumentBuilder();

            Document documento = constructor.parse(archivo);

            documento.getDocumentElement().normalize();

            NodeList lista = documento.getElementsByTagName("jugador");

            System.out.println("===== RANKING DE HEROES =====");
            System.out.println("Total de jugadores: " + lista.getLength());

            for (int i = 0; i < lista.getLength(); i++)
            {
                Element jugador = (Element) lista.item(i);

                String nombre = jugador.getElementsByTagName("nombre")
                                .item(0)
                                .getTextContent();

                String puntuacion = jugador.getElementsByTagName("puntuacion")
                                    .item(0)
                                    .getTextContent();

                System.out.println("Posicion: " + (i + 1));
                System.out.println("Nombre: " + nombre);
                System.out.println("Puntuacion: " + puntuacion);
                System.out.println("----------------------");
            }

        }
        catch (Exception e)
        {
            System.out.println("Error al leer el XML: " + e.getMessage());
        }
    }
}
