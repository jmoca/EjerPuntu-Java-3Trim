package Puntu4;

import java.sql.*;

public class ConexionBD {
    private static final String ADD_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String URL_CONEXION = "jdbc:mariadb://localhost:3306/puntu4";
    PreparedStatement encapsulaCons = null;
    Connection conexBd = null;
    public void conexion(){
        final String usuario = "root";
        final String contrasenia = "root";


        try{
            Class.forName(ADD_DRIVER);

            // Establecer conexión a la base de datos
            conexBd = DriverManager.getConnection(URL_CONEXION, usuario, contrasenia);
            System.out.println("Tenemos conexión...");

        }  catch (ClassNotFoundException cnfe) {
        System.out.println(cnfe.getMessage());
        } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    }
    public void insertar(String cod, String nombre, Float nota1txt, Float nota2txt) {
        conexion();
        try {
            encapsulaCons = conexBd.prepareStatement("INSERT INTO NOTAS(codigo_matricula, nombre_asignatura, nota1, nota2) VALUES (?, ?, ?, ?)");
            encapsulaCons.setString(1, cod);
            encapsulaCons.setString(2, nombre);
            encapsulaCons.setFloat(3, nota1txt);
            encapsulaCons.setFloat(4, nota2txt);
            int filActualizadas = encapsulaCons.executeUpdate();
            System.out.println(filActualizadas+" registros actualizados");

        } catch (SQLIntegrityConstraintViolationException e) {

            System.out.println("Error: Se intentó insertar un registro duplicado.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificar(){

    }
    public void eli(){

    }
    public void consu(){

    }
}
