package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://castaneda\\SQLEXPRESS:1433;databaseName=SistemaCocina";
    private static final String USER = "sa"; // Cambia a tu usuario de SQL Server
    private static final String PASSWORD = "1704"; // Cambia a tu contraseña

   public static Connection getConnection() throws SQLException {
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new SQLException("Error: Driver no encontrado", e);
    } catch (SQLException e) {
        System.out.println("Error en la conexión a la base de datos.");
        e.printStackTrace();
        throw e;
    }
}
}

