package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("sql.driverName"));
        connection = DriverManager.getConnection(
                properties.getProperty("sql.url"),
                properties.getProperty("sql.login"),
                properties.getProperty("sql.password"));
    }

    private void executeUpdate(String sqlText) throws Exception {
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(sqlText);
        }
    }

    public void createTable(String tableName) throws Exception {
        executeUpdate(String.format(
                    "CREATE TABLE %s()", tableName
            ));
    }

    public void dropTable(String tableName) throws Exception {
        executeUpdate(String.format(
                "DROP TABLE %s", tableName
        ));
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        executeUpdate(String.format(
                "ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type
        ));
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        executeUpdate(String.format(
                "ALTER TABLE %s DROP COLUMN %s", tableName, columnName
        ));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        executeUpdate(String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName
        ));
    }
    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
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
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("sql.properties")) {
            config.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("cars");
            System.out.println(tableEditor.getTableScheme("cars"));

            tableEditor.addColumn("cars", "carName", "text");
            System.out.println(tableEditor.getTableScheme("cars"));

            tableEditor.renameColumn("cars", "carName", "name");
            System.out.println(tableEditor.getTableScheme("cars"));

            tableEditor.dropColumn("cars", "name");
            System.out.println(tableEditor.getTableScheme("cars"));

            tableEditor.dropTable("cars");
        }
    }
}