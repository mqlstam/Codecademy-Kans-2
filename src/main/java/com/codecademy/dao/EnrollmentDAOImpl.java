package com.codecademy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.codecademy.domain.Enrollment;
import com.codecademy.database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EnrollmentDAOImpl implements EnrollmentDAO{
    private DbConnection dbConnection;

    public EnrollmentDAOImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void addEnrollment(String studentEmail, String courseName) {
        try(Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("INSERT INTO Enrollment VALUES(GETDATE() ,?, ?, NULL)");
            query.setString(1, studentEmail);
            query.setString(2, courseName);
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in addEnrollment");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEnrollment(Enrollment enrollment) {
        try(Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("DELETE FROM Enrollment WHERE StudentEmail = ?");
            query.setString(1, enrollment.getStudentEmail());
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in deleteEnrollment");
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Enrollment> getEnrollments() {
        try(Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("SELECT * FROM Enrollment");
            ResultSet result = query.executeQuery();

            ObservableList<Enrollment> list = FXCollections.observableArrayList();

            while (result.next()) {                
                list.add(new Enrollment(result.getString("StudentEmail"), result.getString("CourseName"), result.getTimestamp("EnrollmentDatetime").toLocalDateTime()));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error in getEnrollments");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) {
        try(Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("UPDATE Enrollment SET StudentEmail = ?, CourseName = ? WHERE EnrollmentDatetime = ?");
            query.setString(1, enrollment.getStudentEmail());
            query.setString(2, enrollment.getCourseName());
            query.setString(3, enrollment.getEnrollmentDateTime().toString());
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in updateEnrollment");
            e.printStackTrace();
        }
        
    }
    
    @Override 
    public double getCompletionPercentageByGender(String gender) {
        double completionPercentage = 0;
        try(Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("SELECT COUNT(DISTINCT CourseName) AS TotalCourses, COUNT(DISTINCT C.CertificateID) AS CompletedCourses FROM Enrollment E JOIN Student S ON E.StudentEmail = S.StudentEmail JOIN Certificate C ON E.CertificateID = C.CertificateID WHERE S.Gender = ?");
            query.setString(1, gender);
            ResultSet rs = query.executeQuery();
            if(rs.next()) {
                int totalCourses = rs.getInt("TotalCourses");
                int completedCourses = rs.getInt("CompletedCourses");
                completionPercentage = (completedCourses * 1.0 / totalCourses) * 100;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return completionPercentage;
    }

    @Override
    public void updateRegistrationWithCertificate(String emailAddress, int courseId, int certificateId) {
        try(Connection db = dbConnection.getConnection())
        {
            String updateRegistrationQuery = "UPDATE Enrollment SET CertificateID = ? WHERE StudentEmail = ? AND CourseName = ?";
            PreparedStatement updateRegistrationStmt = db.prepareStatement(updateRegistrationQuery);
            updateRegistrationStmt.setInt(1, certificateId);
            updateRegistrationStmt.setString(2, emailAddress);
            updateRegistrationStmt.setInt(3, courseId);
            updateRegistrationStmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("error in pdateRegistrationWithCertificate");
             e.printStackTrace();
        }

    }

    
}

