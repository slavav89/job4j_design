package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, IOException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws IOException, SQLException, ClassNotFoundException {
        try (InputStream in = TableEditor
                .class
                .getClassLoader()
                .getResourceAsStream("app.properties")) {
            properties.load(in);
            Class.forName(properties.getProperty("driver_class"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
        }
    }

    public void createTable(String tableName) throws SQLException {
        try (var statement = connection.createStatement()) {
            String sql = String.format("CREATE TABLE IF NOT EXISTS %s(ID SERIAL PRIMARY KEY);", tableName);
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (var statement = connection.createStatement()) {
            String sql = "DROP TABLE IF EXISTS " + tableName;
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (var statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE IF EXISTS %s ADD %s %s", tableName, columnName, type);
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (var statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE IF EXISTS %s DROP COLUMN %s", tableName, columnName);
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (var statement = connection.createStatement()) {
            String sql = String
                    .format("ALTER TABLE IF EXISTS %s RENAME %s TO %s", tableName, columnName, newColumnName);
            statement.execute(sql);
        }
    }

    public String getTableScheme(String tableName) throws SQLException {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        try (TableEditor tb = new TableEditor(new Properties())) {
            tb.createTable("countries");
            System.out.println(tb.getTableScheme("countries"));
            tb.addColumn("countries", "SQUARE", "TEXT");
            System.out.println(tb.getTableScheme("countries"));
            tb.renameColumn("countries", "SQUARE", "AREA");
            System.out.println(tb.getTableScheme("countries"));
            tb.dropColumn("countries", "AREA");
            System.out.println(tb.getTableScheme("countries"));
            tb.dropTable("countries");
        }
    }
}