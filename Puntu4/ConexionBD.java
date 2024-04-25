package Puntu4;

import java.sql.*;

public class ConexionBD {
    // Variables para la configuración de la conexión
    private static final String ADD_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String URL_CONEXION = "jdbc:mariadb://localhost:3306/puntu4";

    // Declaración de objetos para la conexión y consulta
    PreparedStatement encapsulaCons = null;
    Connection conexBd = null;

    // Método para establecer la conexión con la base de datos
    public void conexion() {
        // Credenciales de acceso a la base de datos
        final String usuario = "root";
        final String contrasenia = "root";
        try {
            // Cargar el controlador JDBC
            Class.forName(ADD_DRIVER);

            // Establecer conexión a la base de datos
            conexBd = DriverManager.getConnection(URL_CONEXION, usuario, contrasenia);

        } catch (ClassNotFoundException cnfe) {
            // Capturar y mostrar errores relacionados con el controlador JDBC
            System.out.println(cnfe.getMessage());
        } catch (SQLException e) {
            // Lanzar una excepción de tiempo de ejecución para errores de conexión
            throw new RuntimeException(e);
        }
    }

    // Método para insertar un registro en la base de datos
    public void insertar(String cod, String nombre, Float nota1txt, Float nota2txt) {
        conexion();
        try {
            // Preparar la consulta SQL para la inserción
            encapsulaCons = conexBd.prepareStatement("INSERT INTO NOTAS(codigo_matricula, nombre_asignatura, nota1, nota2) VALUES (?, ?, ?, ?)");
            encapsulaCons.setString(1, cod);
            encapsulaCons.setString(2, nombre);
            encapsulaCons.setFloat(3, nota1txt);
            encapsulaCons.setFloat(4, nota2txt);
            encapsulaCons.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            // Capturar e informar sobre intentos de inserción de registros duplicados
            System.out.println("Error: Se intentó insertar un registro duplicado.");

        } catch (SQLException e) {
            // Lanzar una excepción de tiempo de ejecución para otros errores SQL
            throw new RuntimeException(e);
        }
    }

    // Método para modificar un registro en la base de datos
    public void modificar(String cod, String nombre, Float nota1txt, Float nota2txt) {
        conexion();
        try {
            // Preparar la consulta SQL para la actualización
            encapsulaCons = conexBd.prepareStatement("UPDATE NOTAS SET nombre_asignatura = ? , nota1 = ?, nota2 = ? WHERE codigo_matricula = ?");
            encapsulaCons.setString(1, nombre);
            encapsulaCons.setFloat(2, nota1txt);
            encapsulaCons.setFloat(3, nota2txt);
            encapsulaCons.setString(4, cod);
            encapsulaCons.executeUpdate();

        } catch (SQLException e) {
            // Lanzar una excepción de tiempo de ejecución para errores SQL
            throw new RuntimeException(e);
        }
    }

    // Método para eliminar un registro de la base de datos
    public void eli(String cod) {
        conexion();
        try {
            // Preparar la consulta SQL para la eliminación
            encapsulaCons = conexBd.prepareStatement("DELETE FROM NOTAS WHERE codigo_matricula = ?");
            encapsulaCons.setString(1, cod);
            encapsulaCons.executeUpdate();
        } catch (SQLException e) {
            // Lanzar una excepción de tiempo de ejecución para errores SQL
            throw new RuntimeException(e);
        }
    }

    // Método para consultar un registro en la base de datos
    public String[] consu(String cod) {
        conexion();
        try {
            // Preparar la consulta SQL para la consulta
            encapsulaCons = conexBd.prepareStatement("SELECT * FROM NOTAS WHERE codigo_matricula = ?");
            encapsulaCons.setString(1, cod);
            ResultSet resulCons = encapsulaCons.executeQuery();

            // Recorrer registro a registro el resultado obtenido
            String[] informacion = new String[4];
            while (resulCons.next()) {
                informacion[0] = resulCons.getString("codigo_matricula");
                informacion[1] = resulCons.getString("nombre_asignatura");
                informacion[2] = resulCons.getString("nota1");
                informacion[3] = resulCons.getString("nota2");
            }
            return informacion;
        } catch (SQLException e) {
            // Lanzar una excepción de tiempo de ejecución para errores SQL
            throw new RuntimeException(e);
        }
    }
}
