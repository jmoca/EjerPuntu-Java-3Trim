import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class  Gestion_tele {

    Scanner teclado=new Scanner(System.in);

    public void alta(){
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        HashMap <String,String> nue_abo = new HashMap<>();
        ArrayList<HashMap> lis_abo = new ArrayList<>();

        System.out.println("\n\t\t\033[4mAlta de factura\033[0m\n");
        System.out.println("Número del abonado:");
        String num_abo = teclado.nextLine();
        System.out.println("Nombre");
        String nom_abo = teclado.nextLine();
        System.out.println("Valor de la factura");
        String valo_fac = teclado.nextLine();
        System.out.println("Datos incorporados al fichero");

        nue_abo.put("num_abonado", num_abo); nue_abo.put("nom_abonado",nom_abo); nue_abo.put("valo_factura", valo_fac);
        lis_abo.add(nue_abo);
        try {
            fos = new FileOutputStream("src/facturas_telf.dat", true);
            dos = new DataOutputStream(fos);
            dos.write();


        }catch (FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        }
    }
    public void modi(){

    }
    public void fact_abo(){

    }
    public void fact_total(){

    }
    public void eli(){

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
                    System.out.println("Saliendo del programa");
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
