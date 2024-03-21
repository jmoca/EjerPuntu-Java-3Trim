import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CambiaVocales {

    // después cuando see lea en pantalla en la lectura poner original -> cambiado
    public static void main(String[] args) {
        CambiaVocales cv = new CambiaVocales();
        System.out.print("\n\t\t\t\t\033[4mPROGRAMA CAMBIAVOCALES\033[0m\n");
        cv.intro_Fic();
    }
    public void intro_Fic(){
        System.out.println("Introduzca el texto:");
        Scanner teclado = new Scanner(System.in);
        PrintWriter salida = null;
        try {
            salida = new PrintWriter("src/cambiavocales.txt");
            for (int i = 1; i <= 6; i++) {
                String frase = teclado.nextLine();
                if (frase.length() > 80) {
                    frase = frase.substring(0,80);
                }
                salida.println(frase);

            }
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } finally {
            salida.close();
        }
        System.out.println("oo00 Las líneas se han grabado en el fichero 00oo");


}

    public void saca_Fic(){

    }
}
