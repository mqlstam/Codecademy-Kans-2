package com.codecademy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.codecademy.database.DbConnection;
import com.codecademy.domain.Address;
import com.codecademy.domain.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDAOImpl implements StudentDAO{

    private DbConnection dbConnection;

    public StudentDAOImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public ObservableList<Student> getStudents() {
        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("SELECT s.StudentEmail, s.Name, s.BirthDate, s.Gender, a.AddressID, a.Street, a.HouseNumber, a.PostalCode, a.City, a.Country FROM Student s JOIN Address a ON s.AddressID = a.AddressID");
            ResultSet result = query.executeQuery();
    
            ObservableList<Student> list = FXCollections.observableArrayList();
    
            while (result.next()) {
                Address address = new Address(result.getInt("AddressID"), result.getString("Street"), result.getString("HouseNumber"), result.getString("PostalCode"), result.getString("City"), result.getString("Country"));
                list.add(new Student(result.getString("StudentEmail"), result.getString("Name"), result.getDate("BirthDate").toLocalDate(), result.getString("Gender"), address));
            }
            return list;
        } catch (Exception e) {
            System.err.println("Error in getStudents");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<String> getAllStudentEmails() {
        try (Connection db = dbConnection.getConnection()){
            PreparedStatement query = db.prepareStatement("SELECT StudentEmail FROM Student");
            ResultSet result = query.executeQuery();
            ObservableList<String> list = FXCollections.observableArrayList();

            while (result.next()) {
                list.add(result.getString("StudentEmail"));
            }
            return list;
        } catch (Exception e) {
            System.out.println("Error in getAllStudentEmails");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addStudent(Student student) {
        Connection db = null;
        try {
            db = dbConnection.getConnection();
            db.setAutoCommit(false);
    
            PreparedStatement query = db.prepareStatement("INSERT INTO Address (Street, HouseNumber, PostalCode, City, Country) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            query.setString(1, student.getAddress().getStreet());
            query.setString(2, student.getAddress().getHouseNumber());
            query.setString(3, student.getAddress().getPostalCode());
            query.setString(4, student.getAddress().getCity());
            query.setString(5, student.getAddress().getCountry());
            query.executeUpdate();
    
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                int addressId = generatedKeys.getInt(1);
    
                query = db.prepareStatement("INSERT INTO Student (StudentEmail, Name, BirthDate, Gender, AddressID) VALUES(?, ?, ?, ?, ?)");
                query.setString(1, student.getEmail());
                query.setString(2, student.getName());
                query.setDate(3, java.sql.Date.valueOf(student.getBirthDate()));
                query.setString(4, student.getGender());
                query.setInt(5, addressId);
                query.executeUpdate();
    
                db.commit();
            }
        } catch (SQLException e) {
            System.out.println("Error in addStudent");
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    db.setAutoCommit(true);
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    

    @Override
    public void updateStudent(Student student) {
        Connection db = null;
        try {
            db = dbConnection.getConnection();
            db.setAutoCommit(false);
    
            PreparedStatement query = db.prepareStatement("UPDATE Student SET Name = ?, BirthDate = ?, Gender = ? WHERE StudentEmail = ?");
            query.setString(1, student.getName());
            query.setDate(2, java.sql.Date.valueOf(student.getBirthDate()));
            query.setString(3, student.getGender());
            query.setString(4, student.getEmail());
            query.executeUpdate();
            query = db.prepareStatement("UPDATE Address SET Street = ?, HouseNumber = ?, PostalCode = ?, City = ?, Country = ? WHERE AddressID = (SELECT AddressID FROM Student WHERE StudentEmail = ?)");
            query.setString(1, student.getAddress().getStreet());
            query.setString(2, student.getAddress().getHouseNumber());
            query.setString(3, student.getAddress().getPostalCode());
            query.setString(4, student.getAddress().getCity());
            query.setString(5, student.getAddress().getCountry());
            query.setString(6, student.getEmail());
            query.executeUpdate();
    
            db.commit();
        } catch (SQLException e) {
            System.out.println("Error in updateStudent");
            e.printStackTrace(); 
        } finally {
            if (db != null) {
                try {
                    db.setAutoCommit(true);
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    

    @Override
    public void deleteStudent(Student student) {
        try(Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("DELETE FROM Student WHERE StudentEmail = ?");
            query.setString(1, student.getEmail());
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in deleteStudent");
            e.printStackTrace();
        }
    }
    
    @Override
    public List<String> getCertificatesByEmail(String Email) {
        List<String> certificates = new ArrayList<>();
        try(Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("SELECT C.CertificateID FROM Certificate C INNER JOIN Enrollment E ON C.CertificateID = E.CertificateID WHERE E.StudentEmail = ?");
            query.setString(1, Email);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                String certificateName = rs.getString("CertificateID");
                certificates.add(certificateName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return certificates;
    }

    @Override
    public void getProgressPerModuleForAccount(String emailAddress, String courseName) {
    try (Connection db = dbConnection.getConnection()) {
        
        // Get the progress per module for the selected account and course
        String progressQuery = "SELECT M.FollowNumber, M.ModuleTitle, SC.percentage AS progress " +
            "FROM Module M " +
            "JOIN Content C ON M.ContentID = C.ContentID " +
            "JOIN Student_Content SC ON C.ContentID = SC.ContentID " +
            "WHERE C.ContentID IN (SELECT ContentItemID FROM Course WHERE CourseID = ?) " +
            "AND SC.StudentEmail = ? " +
            "ORDER BY M.FollowNumber;";

        PreparedStatement progressStatement = db.prepareStatement(progressQuery);
        progressStatement.setString(1, courseName);
        progressStatement.setString(2, emailAddress);

        ResultSet resultSet = progressStatement.executeQuery();

        System.out.println("Progress per module for account " + emailAddress + " and course name " + courseName + ":");
        while (resultSet.next()) {
            int moduleId = resultSet.getInt("FollowNumber");
            String title = resultSet.getString("ModuleTitle");
            int progress = resultSet.getInt("progress");

            System.out.println("Module ID: " + moduleId + ", Title: " + title + ", Progress: " + progress + "%");
        }
    } catch (SQLException e) {
        System.out.println("Error while getting the progress per module for account: " + e.getMessage());
    }
}

}
