package com.codecademy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.codecademy.database.DbConnection;
import com.codecademy.domain.Content;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * The ContactPersonDAOimpl class implements the ContactPersonDAO interface
 * 
 * and provides methods for accessing contact person data from the database.
 */
public class ContentDAOimpl implements ContentDAO {
    private DbConnection dbConnection;

    /**
     * 
     * Constructs a new ContactPersonDAOimpl object with the given DbConnection.
     * 
     * @param dbConnection the DbConnection to use
     */
    public ContentDAOimpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * 
     * Retrieves a list of all contact persons from the database.
     * 
     * @return an ObservableList of ContactPerson objects representing the contact
     *         persons in the database
     */
    @Override
    public ObservableList<Content> getContents() {
        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("SELECT * FROM Content");
            ResultSet result = query.executeQuery();

            ObservableList<Content> list = FXCollections.observableArrayList();

            while (result.next()) {
                list.add(new Content(result.getInt("ContentID"), result.getString("PublicationDate"),
                        result.getString("Description"), result.getString("Status")));
            }
            return list;
        } catch (Exception e) {
            System.err.println("Error in getContents");
            e.printStackTrace();
        }
        return null;
    }

}
