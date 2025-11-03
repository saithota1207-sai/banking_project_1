package org.example.bankingsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import org.slf4j.Logger;

public class DBConnection {
    private static Connection connection;
    private static final Logger logger = LoggerUtil.getLogger(DBConnection.class);

    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Properties props = new Properties();
                try (InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
                    props.load(is);
                }
                String url = props.getProperty("db.url").trim();
                String user = props.getProperty("db.user").trim();
                String pass = props.getProperty("db.password").trim();
                connection = DriverManager.getConnection(url, user, pass);
                logger.info("Connected to {}", url);
            }
        } catch (SQLException se) {
            logger.error("SQL Exception while connecting: {}", se.getMessage());
            throw se;
        } catch (Exception e) {
            logger.error("Error while creating DB connection: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void close(Connection c) {
        if (c==null) return;
        try { if (!c.isClosed()) c.close(); } catch (SQLException e) { logger.warn("Error closing connection: {}", e.getMessage()); }
    }
}
