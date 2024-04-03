import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;

public class LeerArchivoDat {

    public static void main(String[] args) {
       LeerArchivoDat leer = new LeerArchivoDat();
       leer.todo();
    }
    public void todo(){
        String fichero= "src/facturas_telf.dat";
        try(DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
            NumberFormat espFormat = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-ES"));
            while (dis.available() > 0){
                System.out.println("Número de abonado: "+dis.readInt());
                System.out.println("Nombre del abonado: "+dis.readUTF());
                System.out.println("Valor de la factura: "+espFormat.format(dis.readFloat()) +"\n");
            }
        } catch (FileNotFoundException fn){
            System.out.println(fn.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}
