import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class  Gestion_tele {

    Scanner teclado=new Scanner(System.in);

    // Atributo para dar el formato de moneda de euro
    NumberFormat forma_esp = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-ES"));

    // Método para dar de alta nuevas facturas
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
            // Se abre el archivo para escritura
            fos = new FileOutputStream("src/facturas_telf.dat", true);
            dos = new DataOutputStream(fos);
            // Se escriben los datos en el archivo
            dos.writeInt(num_abo);
            dos.writeUTF(nom_abo);
            dos.writeFloat(valo_fac);

        }catch (FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            // Se cierran los flujos
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
    // Método para modificar el valor de una factura
    public void modi() {
        System.out.println("\n\033[4mModificación del valor de factura\033[0m\n");
        System.out.print("Número del abonado: ");
        int num_abo = teclado.nextInt();

        try {
            // Se abre el archivo para lectura y escritura para sobreescribir los datos del .dat con la clase RamdomAccess
            File fich = new File("src/facturas_telf.dat");
            RandomAccessFile fichero = new RandomAccessFile(fich, "rw");
            fichero.seek(0);
            // Se recorre el archivo buscando el número de abonado
            while (fichero.getFilePointer() < fichero.length()) {
                int num_dat = fichero.readInt();
                if (num_dat == num_abo) {
                    // Saltar el campo de nombre del abonado
                    fichero.readUTF();
                    long pos = fichero.getFilePointer();
                    float valor_fac = fichero.readFloat();
                    System.out.println("Valor de la factura: " + forma_esp.format(valor_fac));
                    System.out.print("Nuevo Valor factura: ");
                    float nue_fac = teclado.nextFloat();
                    // Se sobreescribe el nuevo valor de la factura
                    fichero.seek(pos);
                    fichero.writeFloat(nue_fac);
                    // Salir del método después de modificar la factura
                    return;
                } else {
                    // Saltar el campo de nombre del abonado
                    fichero.readUTF();
                    // Saltar el campo de valor de la factura
                    fichero.readFloat();
                }
            }

            // Mensaje de error por si el abonado no se encontró
            throw new NoSuchElementException("El abonado no se encuentra en el archivo.");

        } catch (FileNotFoundException | NoSuchElementException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para consultar la facturación de un abonado
    public void fact_abo() {
        FileInputStream fis = null;
        DataInputStream dis = null;

        System.out.println("\n\033[4mConsulta facturación abonado\033[0m\n");
        System.out.print("Número del abonado: ");
        int num_abo = teclado.nextInt();

        try {
            // Se abre el archivo para lectura
            fis = new FileInputStream("src/facturas_telf.dat");
            dis = new DataInputStream(fis);
            // Se busca el abonado en el archivo
            while (true) {
                try {
                    int num_dat = dis.readInt();
                    if (num_dat == num_abo) {
                        // Saltar el campo de nombre del abonado
                        dis.readUTF();
                        float valor_fac = dis.readFloat();
                        System.out.println("Valor de la factura: " + forma_esp.format(valor_fac));
                        // Salir del método después de imprimir el valor de la factura
                        return;
                    } else {
                        // Saltar el campo de nombre del abonado
                        dis.readUTF();
                        // Saltar el campo de valor de la factura
                        dis.readFloat();
                    }
                } catch (EOFException e) {
                    throw new NoSuchElementException("El abonado no se encuentra en el archivo.");
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                // Se cierran los flujos
                if (dis != null) dis.close();
                if (fis != null) fis.close();
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }
    // Método para mostrar la facturación total de la compañía
    public void fact_total(){
        System.out.println("\n\033[4mConsulta facturación total\033[0m\n");
        FileInputStream fis = null;
        DataInputStream dis = null;
        try{
            // Se abre el archivo para lectura
            fis = new FileInputStream("src/facturas_telf.dat");
            dis = new DataInputStream(fis);
            float total_fac = 0;
            //Verifica si hay datos disponibles para ser leídos en el flujo de entrada.
            while (dis.available() > 0){
                int num_dat = dis.readInt();
                dis.readUTF(); // Saltar el campo de nombre del abonado
                float valor_fac = dis.readFloat();
                total_fac += valor_fac;
            }
            System.out.println("Facturación total: " + forma_esp.format(total_fac));
        }catch (FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            // Se cierran los flujos
            try {
                if (dis != null) dis.close();
                if (fis != null) fis.close();
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }
    // Método para eliminar el fichero de facturas.dat
    public void eli(){
        System.out.println("\n\033[4mEliminar fichero\033[0m\n");
        File archi_dat = new File("src/facturas_telf.dat");
        if (archi_dat.delete()) {
            System.out.println("Eliminado: "+archi_dat.getName());
        }
        else {
            System.out.println("Problema al borrar directorio.");
        }
    }
    // Método para mostrar el menú de opciones
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
