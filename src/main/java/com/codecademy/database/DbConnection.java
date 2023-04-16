package com.codecademy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * A class representing a database connection.
 */
public class DbConnection {
    private String url;
    private String user;
    private String password;

    /**
     * 
     * Constructs a new DbConnection object.
     * Sets the default URL, username and password to connect to the database.
     */
    public DbConnection() {
        this.url = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=LuukIsTheBest;";
        this.user = "LuukB";
        this.password = "Welkom!123";
    }

    /**
     * 
     * Returns a new Connection object to the database.
     * 
     * @return A Connection object to the database.
     * @throws SQLException if there is an error connecting to the database.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
