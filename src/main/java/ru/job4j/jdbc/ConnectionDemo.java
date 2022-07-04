package ru.job4j.jdbc;

import org.junit.Test;
import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertThat;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();

        Class.forName(config.value("sql.driverName"));
        String url = config.value("sql.url");
        String login = config.value("sql.login");
        String password = config.value("sql.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}