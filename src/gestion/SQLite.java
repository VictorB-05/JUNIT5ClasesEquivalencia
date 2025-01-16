package gestion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLite implements AutoCloseable{

    private Connection connection;
    private final String bd ="ingresos.db";

    /**
     * Crea la conexión a la base de datos
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public SQLite() throws SQLException, ClassNotFoundException {
        //abra la conexión directaments desde el constructor
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:"+bd);
    }

    public void inicio() throws SQLException{
        Statement statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios ("
                + "DNI TEXT PRIMARY KEY,"
                + "nombre TEXT,"
                + "saldo INTEGER,"
                + ")");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS movimientos ("
                + "DNI TEXT PRIMARY KEY,"
                + "tipo TEXT,"
                + "saldo INTEGER,"
                + ")");
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Cierra la conexión. Este método es llamado automáticamente al usar try-with-resources.
     *
     * @throws SQLException
     */
    @Override
    public void close() throws SQLException {
        connection.close();
        System.out.println("Conexion cerrada...");
    }
}