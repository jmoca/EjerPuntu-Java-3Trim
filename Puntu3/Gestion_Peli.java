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
                    // Parsea el fichero y lo convierte de fichero a documento XML recorrible
                    DocumentBuilderFactory factoriaDoc = DocumentBuilderFactory.newInstance();
                    DocumentBuilder constructDoc = factoriaDoc.newDocumentBuilder();
                    Document docDOM = constructDoc.parse(fichXML);
                    // Elimina nodos vacíos y combina adyacentes en caso de que los hubiera
                    docDOM.getDocumentElement().normalize();
                    Element nuevaPelicula = docDOM.createElement("pelicula");

                    // Creamos los elementos hijos de la pelicula
                    System.out.println("Nombre de la película:");
                    Element titulo = docDOM.createElement("titulo");
                    titulo.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(titulo);

                    System.out.println("Nombre del guionista:");
                    Element guionista = docDOM.createElement("guionista");
                    guionista.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(guionista);

                    System.out.println("Nombre de la productora:");
                    Element productora = docDOM.createElement("productora");
                    productora.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(productora);

                    System.out.println("Nombre del director:");
                    Element director = docDOM.createElement("director");
                    director.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(director);

                    System.out.println("Nombre del actor:");
                    Element actor = docDOM.createElement("actor");
                    actor.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(actor);

                    System.out.println("Nombre de la sinopsis:");
                    Element sinopsis = docDOM.createElement("sinopsis");
                    sinopsis.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(sinopsis);

                    // Obtenemos el primer hijo del nodo peliculas
                    Node primerNodo = docDOM.getDocumentElement().getFirstChild();
                    // Insertamos el nuevo nodo antes del primer nodo existente
                    docDOM.getDocumentElement().insertBefore(nuevaPelicula, primerNodo);

                    // Escribimos los cambios al archivo XML
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(docDOM);
                    StreamResult result = new StreamResult(new FileOutputStream("src/Puntu3/peliculas.xml"));
                    transformer.transform(source, result);

                    System.out.println("Nuevo nodo agregado correctamente.");
                } catch (FileNotFoundException fnfe) {
                    // Manejar la excepción si el archivo no se encuentra
                    System.out.println("El archivo no se encontró: " + fnfe.getMessage());
                } catch (Exception e) {
                    // Manejar cualquier otra excepción
                    e.printStackTrace();
                }

                break;
            case "2":
                try {
                    // Obtener la posición donde insertar el nuevo nodo
                    System.out.println("Indica la posición dentro del documento:");
                    int posicion = Integer.parseInt(teclado.nextLine());

                    File fichXML = new File("src/Puntu3/peliculas.xml");
                    // Parsea el fichero y lo convierte de fichero a documento XML recorrible
                    DocumentBuilderFactory factoriaDoc = DocumentBuilderFactory.newInstance();
                    DocumentBuilder constructDoc = factoriaDoc.newDocumentBuilder();
                    Document docDOM = constructDoc.parse(fichXML);
                    // Elimina nodos vacíos y combina adyacentes en caso de que los hubiera
                    docDOM.getDocumentElement().normalize();
                    Element nuevaPelicula = docDOM.createElement("pelicula");

                    // Creamos los elementos hijos de la pelicula
                    System.out.println("Nombre de la película:");
                    Element titulo = docDOM.createElement("titulo");
                    titulo.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(titulo);

                    System.out.println("Nombre del guionista:");
                    Element guionista = docDOM.createElement("guionista");
                    guionista.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(guionista);

                    System.out.println("Nombre de la productora:");
                    Element productora = docDOM.createElement("productora");
                    productora.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(productora);

                    System.out.println("Nombre del director:");
                    Element director = docDOM.createElement("director");
                    director.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(director);

                    System.out.println("Nombre del actor:");
                    Element actor = docDOM.createElement("actor");
                    actor.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(actor);

                    System.out.println("Nombre de la sinopsis:");
                    Element sinopsis = docDOM.createElement("sinopsis");
                    sinopsis.appendChild(docDOM.createTextNode(teclado.nextLine()));
                    nuevaPelicula.appendChild(sinopsis);



                    // Obtener la lista de nodos de pelicula
                    NodeList listaPeliculas = docDOM.getElementsByTagName("pelicula");

                    if (posicion >= 0 && posicion <= listaPeliculas.getLength()) {
                        if (posicion == listaPeliculas.getLength()) {
                            // Si la posición es igual a la longitud de la lista, insertar al final
                            docDOM.getDocumentElement().appendChild(nuevaPelicula);
                        } else {
                            // Insertar en la posición indicada
                            Node nodoReferencia = listaPeliculas.item(posicion);
                            docDOM.getDocumentElement().insertBefore(nuevaPelicula, nodoReferencia);
                        }

                        // Escribir los cambios al archivo XML
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(docDOM);
                        StreamResult result = new StreamResult(new FileOutputStream("src/Puntu3/peliculas.xml"));
                        transformer.transform(source, result);

                        System.out.println("Nuevo nodo insertado correctamente en la posición " + posicion);
                    } else {
                        System.out.println("La posición indicada está fuera de rango.");
                    }
                } catch (FileNotFoundException fnfe) {
                    // Manejar la excepción si el archivo no se encuentra
                    System.out.println("El archivo no se encontró: " + fnfe.getMessage());
                } catch (Exception e) {
                    // Manejar cualquier otra excepción
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
                try {
                    File fichXML = new File("src/Puntu3/peliculas.xml");
                    DocumentBuilderFactory factoriaDoc = DocumentBuilderFactory.newInstance();
                    DocumentBuilder constructDoc = factoriaDoc.newDocumentBuilder();
                    Document docDOM = constructDoc.parse(fichXML);
                    docDOM.getDocumentElement().normalize();

                    // Obtener el primer nodo pelicula
                    NodeList listaPeliculas = docDOM.getElementsByTagName("pelicula");
                    Element nodoPelicula = (Element) listaPeliculas.item(0); // Convertir el nodo a Element

                    // Mostrar menú de opciones para modificar los datos del nodo
                    menu_modi();
                    int opcion = Integer.parseInt(teclado.next());
                    teclado.nextLine();

                    // Modificar el dato seleccionado del nodo pelicula

                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese el nuevo título de la película:");
                            String nuevoTitulo = teclado.nextLine();
                            // Actualizar el título de la película
                            NodeList tituloList = ((Element) nodoPelicula).getElementsByTagName("titulo");
                            Node tituloNode = tituloList.item(0);
                            tituloNode.setTextContent(nuevoTitulo);
                            break;
                        case 2:
                            System.out.println("Ingrese el nuevo guionista de la película:");
                            String nuevoGuionista = teclado.nextLine();
                            // Actualizar el guionista de la película
                            NodeList guionistaList = ((Element) nodoPelicula).getElementsByTagName("guionista");
                            Node guionistaNode = guionistaList.item(0);
                            guionistaNode.setTextContent(nuevoGuionista);
                            break;
                        case 3:
                            System.out.println("Ingrese la nueva productora de la película:");
                            String nuevaProductora = teclado.nextLine();
                            // Actualizar la productora de la película
                            NodeList productoraList = ((Element) nodoPelicula).getElementsByTagName("productora");
                            Node productoraNode = productoraList.item(0);
                            productoraNode.setTextContent(nuevaProductora);
                            break;
                        case 4:
                            System.out.println("Ingrese el nuevo director de la película:");
                            String nuevoDirector = teclado.nextLine();
                            // Actualizar el director de la película
                            NodeList directorList = ((Element) nodoPelicula).getElementsByTagName("director");
                            Node directorNode = directorList.item(0);
                            directorNode.setTextContent(nuevoDirector);
                            break;
                        case 5:
                            System.out.println("Ingrese el nuevo actor de la película:");
                            String nuevoActor = teclado.nextLine();
                            // Actualizar el actor de la película
                            NodeList actorList = ((Element) nodoPelicula).getElementsByTagName("actor");
                            Node actorNode = actorList.item(0);
                            actorNode.setTextContent(nuevoActor);
                            break;
                        case 6:
                            System.out.println("Ingrese la nueva sinopsis de la película:");
                            String nuevaSinopsis = teclado.nextLine();
                            // Actualizar la sinopsis de la película
                            NodeList sinopsisList = ((Element) nodoPelicula).getElementsByTagName("sinopsis");
                            Node sinopsisNode = sinopsisList.item(0);
                            sinopsisNode.setTextContent(nuevaSinopsis);
                            break;
                        case 7:
                            System.out.println("Volviendo al menú principal...");
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }


                    // Escribir los cambios al archivo XML
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(docDOM);
                    StreamResult result = new StreamResult(new FileOutputStream("src/Puntu3/peliculas.xml"));
                    transformer.transform(source, result);

                    System.out.println("Nodo modificado correctamente.");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case "2":
                try {
                    File fichXML = new File("src/Puntu3/peliculas.xml");
                    DocumentBuilderFactory factoriaDoc = DocumentBuilderFactory.newInstance();
                    DocumentBuilder constructDoc = factoriaDoc.newDocumentBuilder();
                    Document docDOM = constructDoc.parse(fichXML);
                    docDOM.getDocumentElement().normalize();

                    System.out.println("Indica la posición del nodo que deseas modificar:");
                    int posicion = Integer.parseInt(teclado.nextLine());

                    NodeList listaPeliculas = docDOM.getElementsByTagName("pelicula");
                    if (posicion < 1 || posicion > listaPeliculas.getLength()) {
                        System.out.println("La posición especificada está fuera del rango.");
                        return;
                    }

                    Node nodoPelicula = listaPeliculas.item(posicion - 1);

                    // Mostrar menú de opciones para modificar los datos del nodo
                    menu_modi();
                    int opcion = Integer.parseInt(teclado.next());
                    teclado.nextLine();

                    // Modificar el dato seleccionado del nodo pelicula
                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese el nuevo título de la película:");
                            String nuevoTitulo = teclado.nextLine();
                            // Actualizar el título de la película
                            NodeList tituloList = ((Element) nodoPelicula).getElementsByTagName("titulo");
                            Node tituloNode = tituloList.item(0);
                            tituloNode.setTextContent(nuevoTitulo);
                            break;
                        case 2:
                            System.out.println("Ingrese el nuevo guionista de la película:");
                            String nuevoGuionista = teclado.nextLine();
                            // Actualizar el guionista de la película
                            NodeList guionistaList = ((Element) nodoPelicula).getElementsByTagName("guionista");
                            Node guionistaNode = guionistaList.item(0);
                            guionistaNode.setTextContent(nuevoGuionista);
                            break;
                        case 3:
                            System.out.println("Ingrese la nueva productora de la película:");
                            String nuevaProductora = teclado.nextLine();
                            // Actualizar la productora de la película
                            NodeList productoraList = ((Element) nodoPelicula).getElementsByTagName("productora");
                            Node productoraNode = productoraList.item(0);
                            productoraNode.setTextContent(nuevaProductora);
                            break;
                        case 4:
                            System.out.println("Ingrese el nuevo director de la película:");
                            String nuevoDirector = teclado.nextLine();
                            // Actualizar el director de la película
                            NodeList directorList = ((Element) nodoPelicula).getElementsByTagName("director");
                            Node directorNode = directorList.item(0);
                            directorNode.setTextContent(nuevoDirector);
                            break;
                        case 5:
                            System.out.println("Ingrese el nuevo actor de la película:");
                            String nuevoActor = teclado.nextLine();
                            // Actualizar el actor de la película
                            NodeList actorList = ((Element) nodoPelicula).getElementsByTagName("actor");
                            Node actorNode = actorList.item(0);
                            actorNode.setTextContent(nuevoActor);
                            break;
                        case 6:
                            System.out.println("Ingrese la nueva sinopsis de la película:");
                            String nuevaSinopsis = teclado.nextLine();
                            // Actualizar la sinopsis de la película
                            NodeList sinopsisList = ((Element) nodoPelicula).getElementsByTagName("sinopsis");
                            Node sinopsisNode = sinopsisList.item(0);
                            sinopsisNode.setTextContent(nuevaSinopsis);
                            break;
                        case 7:
                            System.out.println("Volviendo al menú principal...");
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }

                    // Escribir los cambios al archivo XML
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(docDOM);
                    StreamResult result = new StreamResult(new FileOutputStream("src/Puntu3/peliculas.xml"));
                    transformer.transform(source, result);

                    System.out.println("Nodo modificado correctamente.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case  "3":
                try {
                    File fichXML = new File("src/Puntu3/peliculas.xml");
                    DocumentBuilderFactory factoriaDoc = DocumentBuilderFactory.newInstance();
                    DocumentBuilder constructDoc = factoriaDoc.newDocumentBuilder();
                    Document docDOM = constructDoc.parse(fichXML);
                    docDOM.getDocumentElement().normalize();

                    // Obtener el último nodo pelicula
                    NodeList listaPeliculas = docDOM.getElementsByTagName("pelicula");
                    Node nodoPelicula = listaPeliculas.item(listaPeliculas.getLength() - 1);

                    // Mostrar menú de opciones para modificar los datos del nodo
                    menu_modi();
                    int opcion = Integer.parseInt(teclado.next());
                    teclado.nextLine();

                    // Modificar el dato seleccionado del nodo pelicula
                    // Modificar el dato seleccionado del nodo pelicula
                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese el nuevo título de la película:");
                            String nuevoTitulo = teclado.nextLine();
                            // Actualizar el título de la película
                            NodeList tituloList = ((Element) nodoPelicula).getElementsByTagName("titulo");
                            Node tituloNode = tituloList.item(0);
                            tituloNode.setTextContent(nuevoTitulo);
                            break;
                        case 2:
                            System.out.println("Ingrese el nuevo guionista de la película:");
                            String nuevoGuionista = teclado.nextLine();
                            // Actualizar el guionista de la película
                            NodeList guionistaList = ((Element) nodoPelicula).getElementsByTagName("guionista");
                            Node guionistaNode = guionistaList.item(0);
                            guionistaNode.setTextContent(nuevoGuionista);
                            break;
                        case 3:
                            System.out.println("Ingrese la nueva productora de la película:");
                            String nuevaProductora = teclado.nextLine();
                            // Actualizar la productora de la película
                            NodeList productoraList = ((Element) nodoPelicula).getElementsByTagName("productora");
                            Node productoraNode = productoraList.item(0);
                            productoraNode.setTextContent(nuevaProductora);
                            break;
                        case 4:
                            System.out.println("Ingrese el nuevo director de la película:");
                            String nuevoDirector = teclado.nextLine();
                            // Actualizar el director de la película
                            NodeList directorList = ((Element) nodoPelicula).getElementsByTagName("director");
                            Node directorNode = directorList.item(0);
                            directorNode.setTextContent(nuevoDirector);
                            break;
                        case 5:
                            System.out.println("Ingrese el nuevo actor de la película:");
                            String nuevoActor = teclado.nextLine();
                            // Actualizar el actor de la película
                            NodeList actorList = ((Element) nodoPelicula).getElementsByTagName("actor");
                            Node actorNode = actorList.item(0);
                            actorNode.setTextContent(nuevoActor);
                            break;
                        case 6:
                            System.out.println("Ingrese la nueva sinopsis de la película:");
                            String nuevaSinopsis = teclado.nextLine();
                            // Actualizar la sinopsis de la película
                            NodeList sinopsisList = ((Element) nodoPelicula).getElementsByTagName("sinopsis");
                            Node sinopsisNode = sinopsisList.item(0);
                            sinopsisNode.setTextContent(nuevaSinopsis);
                            break;
                        case 7:
                            System.out.println("Volviendo al menú principal...");
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }


                    // Escribir los cambios al archivo XML
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(docDOM);
                    StreamResult result = new StreamResult(new FileOutputStream("src/Puntu3/peliculas.xml"));
                    transformer.transform(source, result);

                    System.out.println("Nodo modificado correctamente.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "4":
                System.out.println("Volviendo al menu principal....");
                break;
        }
    }

    public void eli_nodo(){
        try {
            File fichXML = new File("src/Puntu3/peliculas.xml");
            DocumentBuilderFactory factoriaDoc = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructDoc = factoriaDoc.newDocumentBuilder();
            Document docDOM = constructDoc.parse(fichXML);
            docDOM.getDocumentElement().normalize();

            Scanner teclado = new Scanner(System.in);

            // Mostrar menú de opciones para seleccionar la posición de eliminación
            System.out.println("1) Eliminar nodo al principio del documento");
            System.out.println("2) Eliminar nodo en posiciones intermedias del documento");
            System.out.println("3) Eliminar nodo al final del documento");
            System.out.println("4) Volver al menú principal");
            System.out.print("Opción: ");
            int opcion = Integer.parseInt(teclado.nextLine());

            NodeList listaPeliculas;

            switch (opcion) {
                case 1:
                    // Eliminar nodo al principio del documento
                    listaPeliculas = docDOM.getElementsByTagName("pelicula");
                    Node nodoPelicula = listaPeliculas.item(0);
                    nodoPelicula.getParentNode().removeChild(nodoPelicula);
                    break;
                case 2:
                    // Eliminar nodo en posiciones intermedias del documento
                    System.out.println("Indica el título de la película:");
                    String titulo = teclado.nextLine();
                    NodeList peliculas = docDOM.getElementsByTagName("pelicula");
                    for (int i = 0; i < peliculas.getLength(); i++) {
                        Node pelicula = peliculas.item(i);
                        if (pelicula.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementoPelicula = (Element) pelicula;
                            String tituloPelicula = elementoPelicula.getElementsByTagName("titulo").item(0).getTextContent();
                            if (tituloPelicula.equals(titulo)) {
                                pelicula.getParentNode().removeChild(pelicula);
                                System.out.println("Nodo eliminado correctamente.");
                                break;
                            }
                        }
                    }
                    break;
                case 3:
                    // Eliminar nodo al final del documento
                    listaPeliculas = docDOM.getElementsByTagName("pelicula");
                    Node nodoPeliculaFin = listaPeliculas.item(listaPeliculas.getLength() - 1);
                    nodoPeliculaFin.getParentNode().removeChild(nodoPeliculaFin);
                    break;
                case 4:
                    // Volver al menú principal
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }

            // Escribir los cambios al archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(docDOM);
            StreamResult result = new StreamResult(new FileOutputStream("src/Puntu3/peliculas.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
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