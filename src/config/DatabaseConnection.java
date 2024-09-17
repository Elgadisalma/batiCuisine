package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private static final String URL_DB = "jdbc:postgresql://localhost:5432/BatiCuisine";
    private static final String USERNAME_DB = "postgres";
    private static final String PASSWORD_DB = "123";

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL_DB, USERNAME_DB, PASSWORD_DB);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database Driver not found", e);
        } catch (SQLException e) {
            throw new SQLException("Error while establishing the database connection", e);
        }
    }

    public static synchronized DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
