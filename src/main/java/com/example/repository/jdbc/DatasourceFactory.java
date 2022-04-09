package com.example.repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatasourceFactory {

    private Connection connection;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/developers",
                "devuser",
                "pgpwd4dev");
    }
}
