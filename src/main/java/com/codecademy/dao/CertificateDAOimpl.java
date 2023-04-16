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

/**
 * 
 * The implementation of the CertificateDAO interface that handles database
 * operations related to Certificate objects.
 */
public class CertificateDAOimpl implements CertificateDAO {

    private DbConnection dbConnection;
    private Timestamp timestamp;

    /**
     * 
     * Constructs a CertificateDAOimpl object with the given DbConnection.
     * 
     * @param dbConnection the DbConnection object to be used for database
     *                     operations
     */
    public CertificateDAOimpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * 
     * Retrieves a list of all Certificate objects from the database.
     * 
     * @return an ObservableList of Certificate objects
     */
    @Override
    public ObservableList<Certificate> getCertificates() {
        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("SELECT * FROM Certificate");
            ResultSet result = query.executeQuery();

            ObservableList<Certificate> list = FXCollections.observableArrayList();

            while (result.next()) {
                list.add(new Certificate(result.getInt("CertificateID"), result.getDouble("Grade"),
                        result.getString("Employee")));
            }
            return list;
        } catch (Exception e) {
            System.err.println("Error in getCertificates");
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 
     * Adds a new Certificate object to the database.
     * 
     * @param certificate the Certificate object to be added to the database
     */
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

    /**
     * 
     * Updates an existing Certificate object in the database.
     * 
     * @param certificate the Certificate object to be updated in the database
     */
    @Override
    public void updateCertificate(Certificate certificate) {

        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db
                    .prepareStatement("UPDATE Certificate SET Grade = ?, Employee = ? WHERE CertificateID = ?");

            query.setDouble(1, certificate.getGrade());
            query.setString(2, certificate.getEmployee());
            query.setInt(3, certificate.getCertificateID());
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in updateCertificate");
            e.printStackTrace();
        }

    }

    /**
     * 
     * Deletes an existing Certificate object from the database.
     * 
     * @param certificate the Certificate object to be deleted from the database
     */
    @Override
    public void deleteCertificate(Certificate certificate) {

        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("DELETE FROM Certificate WHERE CertificateID = ?");
            query.setInt(1, certificate.getCertificateID());
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in deleteCertificate");
            e.printStackTrace();
        }
    }

}
