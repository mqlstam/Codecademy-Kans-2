package com.codecademy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private String url;
    private String user;
    private String password;

    public DbConnection() {
        this.url = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=LuukIsTheBest;";
        this.user = "LuukB";
        this.password = "Welkom!123";
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
