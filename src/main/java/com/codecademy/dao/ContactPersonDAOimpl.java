package com.codecademy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codecademy.database.DbConnection;
import com.codecademy.domain.ContactPerson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * The ContactPersonDAOimpl class implements the ContactPersonDAO interface and
 * provides methods to retrieve ContactPerson data
 * 
 * from the database.
 */
public class ContactPersonDAOimpl implements ContactPersonDAO {
    private DbConnection dbConnection;

    /**
     * 
     * Constructor for ContactPersonDAOimpl class.
     * 
     * @param dbConnection an instance of DbConnection to connect to the database
     */
    public ContactPersonDAOimpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * 
     * Retrieves all contact persons from the database.
     * 
     * @return an ObservableList of ContactPerson objects
     */
    @Override
    public ObservableList<ContactPerson> getContactPersons() {
        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("SELECT * FROM ContactPerson");
            ResultSet result = query.executeQuery();

            ObservableList<ContactPerson> list = FXCollections.observableArrayList();

            while (result.next()) {
                list.add(new ContactPerson(result.getString("ContactPersonEmail"),
                        result.getString("NameContactPerson")));
            }
            return list;
        } catch (SQLException e) {
            System.err.println("Error in getStudents");
            e.printStackTrace();
        }
        return null;
    }

}
