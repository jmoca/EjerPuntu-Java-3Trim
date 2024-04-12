package Puntu3;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class Gestion_Peli {
    Scanner teclado = new Scanner(System.in);
    String opcion;
    public void menu_nodos(String opcion){
        if (opcion.equals("Insertar")){
            System.out.println("1) Insertar nodo al principio del documento");
            System.out.println("2) Insertar nodo en posiciones intermedias del documento");
            System.out.println("3) Insertar nodo al final del documento");
            System.out.println("4) Volver al menú principal");
        }else if (opcion.equals("Modificar")){
            System.out.println("1) Modificar nodo al principio del documento");
            System.out.println("2) Modificar nodo en posiciones intermedias del documento");
            System.out.println("3) Modificar nodo al final del documento");
            System.out.println("4) Volver al menú principal");
        }else {
            System.out.println("1) Eliminar nodo al principio del documento");
            System.out.println("2) Eliminar nodo en posiciones intermedias del documento");
            System.out.println("3) Eliminar nodo al final del documento");
            System.out.println("4) Volver al menú principal");
        }

    }
    public void doc_XML(){

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
    public void nuevo_nodo(){
        menu_nodos("Insertar");
        switch (opcion){
            case "1":

                break;
            case "2":
                System.out.println("Indica la posición dentro del documento:");
                break;
            case "3":

                break;
            case "4":
                break;
    }
    }
    public void modi_nodo(){
        menu_nodos("Modificar");
    }

    public void eli_nodo(){
        menu_nodos("Eliminar");
    }
    public void menu(){
        boolean salir = false;


        System.out.println("==========================");
        System.out.println("PROGRAMA GESTIÓN PELÍCULAS");
        System.out.println("==========================");
        while (!salir){
            System.out.println("1) Presentar el documento XML completo");
            System.out.println("2) Añadir nuevo nodo al documento");
            System.out.println("3) Modificar datos de un nodo del documento");
            System.out.println("4) Eliminar un nodo concreto del documento");
            System.out.println("5) Salir");
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
                
                   

            }
        }
    }

    public static void main(String[] args) {
        Gestion_Peli GP = new Gestion_Peli();
        GP.menu();
    }
}
