package Puntu3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Gestion_Peli {
    Scanner teclado = new Scanner(System.in);
    String opcion;
    public void menu_nodos(String opcion){
        if (opcion.equals("Insertar")){
            System.out.println("\t1) Insertar nodo al principio del documento");
            System.out.println("\t2) Insertar nodo en posiciones intermedias del documento");
            System.out.println("\t3) Insertar nodo al final del documento");
            System.out.println("\t4) Volver al menú principal");
            System.out.print("Opcion: ");
        }else if (opcion.equals("Modificar")){
            System.out.println("\t1) Modificar nodo al principio del documento");
            System.out.println("\t2) Modificar nodo en posiciones intermedias del documento");
            System.out.println("\t3) Modificar nodo al final del documento");
            System.out.println("\t4) Volver al menú principal");
            System.out.print("Opcion: ");
        }else {
            System.out.println("\t1) Eliminar nodo al principio del documento");
            System.out.println("\t2) Eliminar nodo en posiciones intermedias del documento");
            System.out.println("\t3) Eliminar nodo al final del documento");
            System.out.println("\t4) Volver al menú principal");
            System.out.print("Opcion: ");
        }

    }

    public void doc_XML() {
        // Creamos una referencia (fichXML) de tipo File al cargar el fichero XML existente
        File fichXML = new File("src/Puntu3/peliculas.xml");
        try {
            // Parsea el fichero y lo convierte de fichero a documento XML recorrible
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(fichXML);

            // Elimina nodos vacíos y combina adyacentes en caso de que los hubiera
            doc.getDocumentElement().normalize();

            // Prepara el documento XML resultante para mostrarlo por pantalla y grabarlo
            TransformerFactory factoriaTransf = TransformerFactory.newInstance();
            Transformer transformador = factoriaTransf.newTransformer();

            // Establece las propiedades de salida para el transformer
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            // Muestra por pantalla el fichero XML
            System.out.println("---Documento XML resultante---");
            StreamResult consoleResult = new StreamResult(System.out);
            transformador.transform(source, consoleResult);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void nuevo_nodo(){
        menu_nodos("Insertar");
        opcion = teclado.next();
        teclado.nextLine();
        switch (opcion){
            case "1":
                try {
                    File fichXML = new File("src/Puntu3/peliculas.xml");
                    //Parsea el fichero y lo convierte de fichero a documento XML recorrible
                    DocumentBuilderFactory factoriaDoc = DocumentBuilderFactory.newInstance();
                    DocumentBuilder constructDoc = factoriaDoc.newDocumentBuilder();
                    Document docDOM = constructDoc.parse(fichXML);
                    //Elimina nodos vacíos y combina adyacentes en caso de que los hubiera
                    docDOM.getDocumentElement().normalize();
                    Element nuevaPelicula = docDOM.createElement("película");

                    // Creamos los elementos hijos de la pelicula
                    System.out.println("Nombre de la película");
                    Element titulo = docDOM.createElement("titulo");
                    titulo.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(titulo);

                    System.out.println("Nombre del guionista ");
                    Element guionista = docDOM.createElement("guionista");
                    guionista.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(guionista);

                    System.out.println("Nombre de la productora");
                    Element productora = docDOM.createElement("productora");
                    productora.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(productora);

                    System.out.println("Nombre del director");
                    Element director = docDOM.createElement("director");
                    director.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(director);

                    System.out.println("Nombre del actor");
                    Element actor = docDOM.createElement("actor");
                    actor.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(actor);

                    System.out.println("Nombre de la sinopsis");
                    Element sinopsis = docDOM.createElement("sinopsis");
                    sinopsis.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(sinopsis);

                    // Agregamos el nuevo nodo pelicula al nodo peliculas
                    docDOM.getDocumentElement().appendChild(nuevaPelicula);

                    // Escribimos los cambios al archivo XML
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(docDOM);
                    StreamResult result = new StreamResult(new FileOutputStream("src/Puntu3/peliculas.xml"));
                    transformer.transform(source, result);

                    System.out.println("Nuevo nodo agregado correctamente.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                System.out.println("Indica la posición dentro del documento:");
                int posicion = Integer.parseInt(teclado.nextLine()) - 1;


                try {
                    File fichXML = new File("src/Puntu3/peliculas.xml");
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse(fichXML);
                    doc.getDocumentElement().normalize();

                    NodeList lis_peli = doc.getElementsByTagName("pelicula");
                    for (int cont = 0; cont < lis_peli.getLength(); cont++) {
                        Node cada_peli = lis_peli.item(cont);
                        if (cont == posicion) { // Verificar si es la película que queremos modificar
                            Element modiPelicula = (Element) cada_peli;

                            System.out.println("Nombre de la película:");
                            modiPelicula.getElementsByTagName("titulo").item(0).setTextContent(teclado.nextLine());

                            System.out.println("Nombre del guionista:");
                            modiPelicula.getElementsByTagName("guionista").item(0).setTextContent(teclado.nextLine());

                            System.out.println("Nombre de la productora:");
                            modiPelicula.getElementsByTagName("productora").item(0).setTextContent(teclado.nextLine());

                            System.out.println("Nombre del director:");
                            modiPelicula.getElementsByTagName("director").item(0).setTextContent(teclado.nextLine());

                            System.out.println("Nombre del actor:");
                            modiPelicula.getElementsByTagName("actor").item(0).setTextContent(teclado.nextLine());

                            System.out.println("Nombre de la sinopsis:");
                            modiPelicula.getElementsByTagName("sinopsis").item(0).setTextContent(teclado.nextLine());

                            // Escribimos los cambios al archivo XML
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            DOMSource source = new DOMSource(doc);
                            StreamResult result = new StreamResult(new FileOutputStream("src/Puntu3/peliculas.xml"));
                            transformer.transform(source, result);

                            System.out.println("Película modificada correctamente.");
                            break; // Salir del bucle después de modificar la película
                        }
                    }
                } catch (FileNotFoundException fnfe) {
                    System.out.println("El archivo no se encontró: " + fnfe.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case "3":
                try {
                    File fichXML = new File("src/Puntu3/peliculas.xml");
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse(fichXML);
                    doc.getDocumentElement().normalize();

                    Element nuevaPelicula = doc.createElement("pelicula");

                    System.out.println("Nombre de la película:");
                    Element titulo = doc.createElement("titulo");
                    titulo.appendChild(doc.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(titulo);

                    System.out.println("Nombre del guionista:");
                    Element guionista = doc.createElement("guionista");
                    guionista.appendChild(doc.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(guionista);

                    System.out.println("Nombre de la productora:");
                    Element productora = doc.createElement("productora");
                    productora.appendChild(doc.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(productora);

                    System.out.println("Nombre del director:");
                    Element director = doc.createElement("director");
                    director.appendChild(doc.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(director);

                    System.out.println("Nombre del actor:");
                    Element actor = doc.createElement("actor");
                    actor.appendChild(doc.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(actor);

                    System.out.println("Nombre de la sinopsis:");
                    Element sinopsis = doc.createElement("sinopsis");
                    sinopsis.appendChild(doc.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(sinopsis);

                    // Agregamos la nueva película al final del elemento 'peliculas'
                    doc.getDocumentElement().appendChild(nuevaPelicula);

                    // Escribimos los cambios al archivo XML
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new FileOutputStream("src/Puntu3/peliculas.xml"));
                    transformer.transform(source, result);

                    System.out.println("Nueva película agregada correctamente.");
                } catch (FileNotFoundException fnfe) {
                    System.out.println("El archivo no se encontró: " + fnfe.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "4":
                System.out.println("Volviendo al menu principal....");
                break;
    }
    }
    public void menu_modi(){
        System.out.println("1) Titulo \t\t\t 4) Director\n" +
                "2) Guionista \t\t 5) Actor\n" +
                "3) Productora \t\t 6) Sinopsis\n" +
                "7) Volver al menú anterior");
    }
    public void modi_nodo(){
        menu_nodos("Modificar");
        opcion = teclado.next();
        teclado.nextLine();
        switch (opcion){
            case "1":
                menu_modi();
                opcion = teclado.next();
                teclado.nextLine();
                break;
            case "2":
                System.out.println("Indica el título de la película:");
                int posicion = Integer.parseInt(teclado.nextLine()) - 1;
                break;
            case  "3":
                break;
            case "4":
                System.out.println("Volviendo al menu principal....");
                break;
        }
    }

    public void eli_nodo(){
        menu_nodos("Eliminar");
    }
    public void menu(){
        boolean salir = false;
        System.out.println("\t\t==========================");
        System.out.println("\t\tPROGRAMA GESTIÓN PELÍCULAS");
        System.out.println("\t\t==========================");
        while (!salir){
            System.out.println("\t1) Presentar el documento XML completo");
            System.out.println("\t2) Añadir nuevo nodo al documento");
            System.out.println("\t3) Modificar datos de un nodo del documento");
            System.out.println("\t4) Eliminar un nodo concreto del documento");
            System.out.println("\t5) Salir");
            System.out.print("Opcion: ");
            opcion = teclado.next();
            switch (opcion){
                case "1":
                    doc_XML();
                    break;
                case "2":
                    nuevo_nodo();
                    break;
                case "3":
                    modi_nodo();
                    break;
                case "4":
                    eli_nodo();
                    break;
                case "5":
                    System.out.println("Saliendo del programa ...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida, por favor seleccione nuevamente.");
                    break;
                   

            }
        }
    }

    public static void main(String[] args) {
        Gestion_Peli GP = new Gestion_Peli();
        GP.menu();
    }
}
