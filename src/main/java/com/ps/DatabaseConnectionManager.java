package com.ps;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CarDealershipDB";
    private static final String DB_USERNAME = "your_username"; // Replace with your DB username
    private static final String DB_PASSWORD = "your_password"; // Replace with your DB password

    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
