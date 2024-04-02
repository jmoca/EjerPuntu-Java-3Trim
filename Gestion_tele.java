import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class  Gestion_tele {

    Scanner teclado=new Scanner(System.in);

    public void alta(){
        FileOutputStream fos = null;
        DataOutputStream dos = null;


        System.out.println("\n033[4mAlta de factura\033[0m\n");
        System.out.println("Número del abonado:");
        int num_abo = teclado.nextInt();
        System.out.println("Nombre");
        String nom_abo = teclado.nextLine();
        System.out.println("Valor de la factura");
        float valo_fac = teclado.nextFloat();
        System.out.println("Datos incorporados al fichero");


        try {
            fos = new FileOutputStream("src/facturas_telf.dat", true);
            dos = new DataOutputStream(fos);
            dos.writeInt(num_abo);
            dos.writeUTF(nom_abo);
            dos.writeFloat(valo_fac);

        }catch (FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (fos != null)
                    fos.close();
                if (dos != null)
                    dos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void modi() {
        NumberFormat forma_esp = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-ES"));
        System.out.println("\n\033[4mModificación del valor de factura\033[0m\n");
        System.out.print("Número del abonado: ");
        int num_abo = teclado.nextInt();
        try {
            File fich = new File ("src/facturas_telf.dat");
            RandomAccessFile fichero = new RandomAccessFile(fich, "rw");
            fichero.seek(0);
            while (fichero.getFilePointer() < fichero.length()) {
                int num_dat = fichero.readInt();
                if(num_dat == num_abo){
                    fichero.readUTF();
                    long pos = fichero.getFilePointer();
                    float valor_fac = fichero.readFloat();
                    System.out.println("Valor de la factura: "+forma_esp.format(valor_fac));
                    System.out.print("Nuevo Valor factura: ");
                    float nue_fac = teclado.nextFloat();
                    fichero.seek(pos);
                    fichero.writeFloat(nue_fac);
                    break;
                }

            }
        } catch (FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void fact_abo(){
        System.out.println("\n\033[4mConsulta facturación abonado\033[0m\n");
    }
    public void fact_total(){
        System.out.println("\n\033[4mConsulta facturación abonado\033[0m\n");
    }
    public void eli(){
        System.out.println("\n\033[4mEliminar fichero\033[0m\n");
    }

    public void menu(){
        boolean salir = false;
        Scanner teclado = new Scanner(System.in);
        String opcion;
        while (!salir){
            System.out.println("\n\t\t\t\tMenú de Opciones");
            System.out.println("\t\t\t\t================\n");
            System.out.println("1) Alta de nuevas facturas");
            System.out.println("2) Modificación del valor de factura");
            System.out.println("3) Consulta del dato de facturación de un abonado");
            System.out.println("4) Consulta del dato de facturación total de la compañía");
            System.out.println("5) Eliminar el fichero");
            System.out.println("6) Salir");
            System.out.print("\t\tOpcion: ");
            opcion = teclado.next();
            switch (opcion){
                case "1":
                    alta();
                    break;
                case "2":
                    modi();
                    break;
                case "3":
                    fact_abo();
                    break;
                case "4":
                    fact_total();
                    break;
                case "5":
                    eli();
                    break;
                case "6":
                    System.out.println("Saliendo del programa ...");
                    salir = true;
            }


        }
    }
    public static void main(String[] args) {
        System.out.print("\n\t\t\033[4mPROGRAMA GESTIÓN COMPAÑÍA TELEFÓNICA\033[0m\n");
        Gestion_tele G_T = new Gestion_tele();
        G_T.menu();
    }
}
