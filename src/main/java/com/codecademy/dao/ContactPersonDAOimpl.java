package com.codecademy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codecademy.database.DbConnection;
import com.codecademy.domain.ContactPerson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactPersonDAOimpl implements ContactPersonDAO {
    private DbConnection dbConnection;

    public ContactPersonDAOimpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public ObservableList<ContactPerson> getContactPersons() {
        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("SELECT * FROM ContactPerson");
            ResultSet result = query.executeQuery();

            ObservableList<ContactPerson> list = FXCollections.observableArrayList();

            while (result.next()) {
                list.add(new ContactPerson(result.getString("ContactPersonEmail"), result.getString("NameContactPerson")));
            }
            return list;
        } catch (SQLException e) {
            System.err.println("Error in getStudents");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addContactPerson(ContactPerson contactPerson) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addContactPerson'");
    }

    @Override
    public void updateContactPerson(ContactPerson contactPerson) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateContactPerson'");
    }

    @Override
    public void deleteContactPerson(ContactPerson contactPerson) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteContactPerson'");
    }
    
}
