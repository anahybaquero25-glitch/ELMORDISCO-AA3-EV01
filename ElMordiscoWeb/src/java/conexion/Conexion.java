package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/el_mordisco";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    URL,
                    USUARIO,
                    PASSWORD
            );

        } catch (Exception e) {
            System.out.println("Error de conexion: " + e.getMessage());
            return null;
        }
    }
}
