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

/**
 * Implementation of the EnrollmentDAO interface using a relational database.
 */
public class EnrollmentDAOImpl implements EnrollmentDAO {
    private DbConnection dbConnection;

    /**
     * Constructor that initializes the DbConnection object used to connect to the
     * database.
     * 
     * @param dbConnection the DbConnection object used to connect to the database
     */
    public EnrollmentDAOImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * Adds a new enrollment for a student in a course to the database.
     * 
     * @param studentEmail the email of the student being enrolled
     * @param courseName   the name of the course being enrolled in
     */
    @Override
    public void addEnrollment(String studentEmail, String courseName) {
        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("INSERT INTO Enrollment VALUES(GETDATE() ,?, ?, NULL)");
            query.setString(1, studentEmail);
            query.setString(2, courseName);
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in addEnrollment");
            e.printStackTrace();
        }
    }

    /**
     * Deletes an enrollment from the database.
     * 
     * @param enrollment the Enrollment object representing the enrollment to be
     *                   deleted
     */
    @Override
    public void deleteEnrollment(Enrollment enrollment) {
        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("DELETE FROM Enrollment WHERE StudentEmail = ?");
            query.setString(1, enrollment.getStudentEmail());
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in deleteEnrollment");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of all enrollments from the database.
     * 
     * @return an ObservableList of Enrollment objects representing all enrollments
     *         in the database
     */
    @Override
    public ObservableList<Enrollment> getEnrollments() {
        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement("SELECT * FROM Enrollment");
            ResultSet result = query.executeQuery();

            ObservableList<Enrollment> list = FXCollections.observableArrayList();

            while (result.next()) {
                list.add(new Enrollment(result.getString("StudentEmail"), result.getString("CourseName"),
                        result.getTimestamp("EnrollmentDatetime").toLocalDateTime()));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error in getEnrollments");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates an existing enrollment in the database.
     * 
     * @param enrollment the Enrollment object representing the enrollment to be
     *                   updated
     */
    @Override
    public void updateEnrollment(Enrollment enrollment) {
        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement(
                    "UPDATE Enrollment SET StudentEmail = ?, CourseName = ? WHERE EnrollmentDatetime = ?");
            query.setString(1, enrollment.getStudentEmail());
            query.setString(2, enrollment.getCourseName());
            query.setString(3, enrollment.getEnrollmentDateTime().toString());
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in updateEnrollment");
            e.printStackTrace();
        }

    }

    /**
     * Gets the completion percentage for all courses completed by students of a
     * given gender.
     * 
     * @param gender the gender of the students whose course completion percentage
     *               is to be calculated
     * @return a double representing the percentage of courses completed by students
     *         of the specified gender
     */
    @Override
    public double getCompletionPercentageByGender(String gender) {
        double completionPercentage = 0;
        try (Connection db = dbConnection.getConnection()) {
            PreparedStatement query = db.prepareStatement(
                    "SELECT COUNT(DISTINCT CourseName) AS TotalCourses, COUNT(DISTINCT C.CertificateID) AS CompletedCourses FROM Enrollment E JOIN Student S ON E.StudentEmail = S.StudentEmail JOIN Certificate C ON E.CertificateID = C.CertificateID WHERE S.Gender = ?");
            query.setString(1, gender);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                int totalCourses = rs.getInt("TotalCourses");
                int completedCourses = rs.getInt("CompletedCourses");
                completionPercentage = (completedCourses * 1.0 / totalCourses) * 100;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return completionPercentage;
    }

    /**
     * Updates an enrollment with the certificate ID earned by a student for
     * completing a course.
     * 
     * @param emailAddress  the email address of the student who earned the
     *                      certificate
     * @param courseId      the ID of the course for which the certificate was
     *                      earned
     * @param certificateId the ID of the certificate earned by the student
     */
    @Override
    public void updateRegistrationWithCertificate(String emailAddress, int courseId, int certificateId) {
        try (Connection db = dbConnection.getConnection()) {
            String updateRegistrationQuery = "UPDATE Enrollment SET CertificateID = ? WHERE StudentEmail = ? AND CourseName = ?";
            PreparedStatement updateRegistrationStmt = db.prepareStatement(updateRegistrationQuery);
            updateRegistrationStmt.setInt(1, certificateId);
            updateRegistrationStmt.setString(2, emailAddress);
            updateRegistrationStmt.setInt(3, courseId);
            updateRegistrationStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error in pdateRegistrationWithCertificate");
            e.printStackTrace();
        }

    }

}
