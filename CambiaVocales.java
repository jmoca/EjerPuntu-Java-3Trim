import java.io.*;
import java.util.Scanner;

public class CambiaVocales {

    // después cuando see lea en pantalla en la lectura poner original -> cambiado
    public static void main(String[] args) {
        CambiaVocales cambi_voca = new CambiaVocales();
        System.out.print("\n\t\t\t\t\033[4mPROGRAMA CAMBIAVOCALES\033[0m\n");
        cambi_voca.intro_Fic();
        cambi_voca.saca_Fic();
    }
    // Método para leer lo escrito por teclado he introducido en un archivo de texto
    public void intro_Fic(){
        System.out.println("Introduzca el texto:");
        Scanner teclado = new Scanner(System.in);
        PrintWriter salida = null;
        try {
            // Abrir el archivo para escritura
            salida = new PrintWriter("src/cambiavocales.txt");
            // Leer seis líneas de texto ingresadas por el usuario
            for (int i = 1; i <= 6; i++) {
                String frase = teclado.nextLine();
                // Si la longitud de la frase es mayor que 80 caracteres, se va a recortar
                if (frase.length() > 80) {
                    frase = frase.substring(0,80);
                }
                // Escribir la frase en el archivo
                salida.println(frase);

            }
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } finally {
            // Cerrar el archivo
            salida.close();
        }
        System.out.println("\noo00 Las líneas se han grabado en el fichero 00oo\n");
}
    // Método para extraer y modificar texto del archivo
    public void saca_Fic(){
        FileReader fr = null;
        try {
            // Abrir el archivo para lectura
            fr = new FileReader("src/cambiavocales.txt");
            BufferedReader entrada = new BufferedReader(fr);
            //Se lee la primera línea del fichero
            String cadena = entrada.readLine();
            //Mientras no se llegue al final del fichero que siga leyendo
            while (cadena != null) {
                // Reemplazar vocales por otras vocales en la cadena y mostrarla
                String frase_Modi = cadena.replace("a", "i");
                frase_Modi = frase_Modi.replace("e","o");
                System.out.println(cadena +" -> "+frase_Modi);
                //Se leen las siguientes líneas que haya en el fichero
                cadena = entrada.readLine();
            }
             System.out.println("\noo00 Se acabó el programa 00oo\n");
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            try {
                // Cerrar el archivo
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }

}
