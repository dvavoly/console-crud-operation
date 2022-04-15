package com.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcUtils {

    private static Connection connection;
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUtils.class);
    private static final Properties properties = new Properties();
    private static final String PROPERTIES_CONFIGURATION = "src/main/resources/application.properties";

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try (var input = new FileInputStream(PROPERTIES_CONFIGURATION)) {
                properties.load(input);
                connection = DriverManager.getConnection(
                        properties.getProperty("url"),
                        properties.getProperty("username"),
                        properties.getProperty("password")
                );
                return connection;
            } catch (IOException e) {
                LOGGER.error("Unable to find database properties configuration file: ", e);
            }
            return connection;
        }
        return connection;
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        if (sql == null || sql.isEmpty()) {
            throw new IllegalArgumentException("Cannot be null");
        }
        return getConnection().prepareStatement(sql);
    }

    public static int getLastId(String tableName) {
        try (PreparedStatement statement = getPreparedStatement(SqlQuery.getLastId.toString())) {
            statement.setString(1, tableName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
        return -1;
    }
}
