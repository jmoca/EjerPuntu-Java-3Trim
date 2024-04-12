package Puntu3;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class ModificaFichXml {
    public static void main(String[] args) {

        //Creamos una referencia (fichXML) de tipo File al cargar el fichero XML existente
        File fichXML = new File("src/Puntu3/peliculas.xml");
        try {
            //Lee el contenido del archivo XML
            Scanner scanner = new Scanner(fichXML);
            System.out.println("---Contenido del archivo XML---");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
