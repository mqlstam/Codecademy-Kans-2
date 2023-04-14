package com.codecademy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import com.codecademy.database.DbConnection;
import com.codecademy.domain.Certificate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CertificateDAOimpl implements CertificateDAO{

    private DbConnection dbConnection;
    private Timestamp timestamp;

    public CertificateDAOimpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public ObservableList<Certificate> getCertificates() {
        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("SELECT * FROM Certificate");
            ResultSet result = query.executeQuery();

            ObservableList<Certificate> list = FXCollections.observableArrayList();

            while (result.next()) {
                list.add(new Certificate(result.getInt("CertificateID"), result.getDouble("Grade"), result.getString("Employee")));
            }
            return list;
        } catch (Exception e) {
            System.err.println("Error in getCertificates");
            e.printStackTrace();
        }
        return null;
        
    }

    @Override
    public void addCertificate(Certificate certificate) {

        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("INSERT INTO Certificate VALUES(?, ?)");
            query.setDouble(1, certificate.getGrade());
            query.setString(2, certificate.getEmployee());
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in addCertificate");
            e.printStackTrace();
        }
       
    }

    @Override
    public void updateCertificate(Certificate certificate) {

        try(Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("UPDATE Certificate SET Grade = ?, Employee = ? WHERE CertificateID = ?"); 
            
            query.setDouble(1, certificate.getGrade());
            query.setString(2, certificate.getEmployee());
            query.setInt(3, certificate.getCertificateID());
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in updateCertificate");
            e.printStackTrace(); 
        }

    }

    @Override
    public void deleteCertificate(Certificate certificate) {

        try(Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("DELETE FROM Certificate WHERE CertificateID = ?");
            query.setInt(1, certificate.getCertificateID());
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in deleteCertificate");
            e.printStackTrace();
        }
    }
    
}
