package Puntu3;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ModificaFichXml {
    public static void main(String[] args) {
        //Creamos una referencia (fichXML) de tipo File al cargar el fichero XML existente
        File fichXML = new File("src/Puntu3/peliculas.xml");
        try {
            //Parsea el fichero y lo convierte de fichero a documento XML recorrible
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(fichXML);
            //Elimina nodos vacíos y combina adyacentes en caso de que los hubiera
            doc.getDocumentElement().normalize();
            //Prepara el documento XML resultante para mostrarlo por pantalla y grabarlo
            TransformerFactory factoriaTransf = TransformerFactory.newInstance();
            Transformer transformador = factoriaTransf.newTransformer();
            DOMSource source = new DOMSource(doc);
            //Muestra por pantalla el fichero XML tras la modificación
            System.out.println("---Documento XML resultante---");
            StreamResult consoleResult = new StreamResult(System.out);
            transformador.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
