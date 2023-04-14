package com.codecademy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.codecademy.database.DbConnection;
import com.codecademy.domain.Content;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContentDAOimpl implements ContentDAO {
    private DbConnection dbConnection;

    public ContentDAOimpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    @Override
    public ObservableList<Content> getContents() {
        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("SELECT * FROM Content");
            ResultSet result = query.executeQuery();

            ObservableList<Content> list = FXCollections.observableArrayList();

            while (result.next()) {
                list.add(new Content(result.getInt("ContentID"), result.getString("PublicationDate"), result.getString("Description"), result.getString("Status")));
            }
            return list;
        } catch (Exception e) {
            System.err.println("Error in getContents");
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public void addContent(Content content) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addContent'");
    }

    @Override
    public void updateContent(Content content) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateContent'");
    }

    @Override
    public void deleteContent(Content content) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteContent'");
    }


    
}
