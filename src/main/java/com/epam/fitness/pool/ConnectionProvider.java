package com.epam.fitness.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionProvider {
    private static final String URI = "db.uri";
    private static final String LOGIN = "db.login";
    private static final String PASSWORD = "db.password";
    private static final String DATABASE_PROPERTIES_FILE = "mysql";

    public Connection createConnection() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_PROPERTIES_FILE);
        String uri = resourceBundle.getString(URI);
        String login = resourceBundle.getString(LOGIN);
        String password = resourceBundle.getString(PASSWORD);
        return DriverManager.getConnection(uri, login, password);
    }

}
