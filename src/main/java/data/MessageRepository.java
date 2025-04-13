package data;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

/**
 * Handles database connection and saving messages to PostgreSQL.
 * Supports both environment variables (for Docker) and application.properties (for local).
 */
public class MessageRepository {
    private Connection connection;

    public MessageRepository() throws Exception {
        // Load properties from application.properties
        Properties props = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
        if (input != null) {
            props.load(input);
        }

        // First, try reading from environment variables (Docker)
        String envUrl = System.getenv("DB_URL");
        String envUser = System.getenv("DB_USER");
        String envPass = System.getenv("DB_PASSWORD");

        // Fallback to application.properties (local development)
        String url = (envUrl != null) ? envUrl : props.getProperty("db.url");
        String user = (envUser != null) ? envUser : props.getProperty("db.user");
        String pass = (envPass != null) ? envPass : props.getProperty("db.password");

        // Print where the config came from
        if (envUrl != null) {
            System.out.println("Using DB credentials from environment variables.");
        } else {
            System.out.println("Using DB credentials from application.properties.");
        }

        // Connect to the database
        connection = DriverManager.getConnection(url, user, pass);
    }

    /**
     * Saves a message to the database.
     */
    public void save(String content) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO messages (content) VALUES (?)")) {
            stmt.setString(1, content);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
