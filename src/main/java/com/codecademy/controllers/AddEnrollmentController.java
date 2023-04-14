package com.codecademy.controllers;

import com.codecademy.dao.CourseDAO;
import com.codecademy.dao.CourseDAOImpl;
import com.codecademy.dao.EnrollmentDAO;
import com.codecademy.dao.EnrollmentDAOImpl;
import com.codecademy.dao.StudentDAO;
import com.codecademy.dao.StudentDAOImpl;
import com.codecademy.database.DbConnection;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * 
 * The AddEnrollmentController class is responsible for handling user input and
 * displaying a GUI form for adding new enrollments
 * 
 * to the system. It uses the EnrollmentDAO, StudentDAO, and CourseDAO classes
 * to add new enrollments to the database.
 */
public class AddEnrollmentController {

    /**
     * 
     * The display method creates a GUI form for adding new enrollments to the
     * system. It allows the user to select a student
     * 
     * and a course for the enrollment. When the user clicks the Save button, the
     * enrollment is added to the database using the
     * 
     * EnrollmentDAO class. When the user clicks the Back button, the form is
     * closed.
     */

    public static void display() {
        DbConnection dbConnection = new DbConnection();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl(dbConnection);
        StudentDAO studentDAO = new StudentDAOImpl(dbConnection);
        CourseDAO courseDAO = new CourseDAOImpl(dbConnection);

        Stage stage = new Stage();
        stage.setTitle("Anhtuan Nguyen(2192526), Luuk beks(2192527), Miquel Stam(2192528)");
        stage.setWidth(1000);
        stage.setHeight(800);
        stage.setResizable(false);

        Label enrollment = new Label("Enrollment");
        enrollment.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        FlowPane root = new FlowPane();
        ChoiceBox<String> studentEmail = new ChoiceBox<>();
        ObservableList<String> studentEmailList = studentDAO.getAllStudentEmails();
        studentEmail.getItems().addAll(studentEmailList);
        studentEmail.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
            studentEmail.setValue(newValue);
        });

        ChoiceBox<String> courseName = new ChoiceBox<>();
        ObservableList<String> courseNameList = courseDAO.getAllCourseNames();
        courseName.getItems().addAll(courseNameList);
        courseName.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
            courseName.setValue(newValue);
        });

        Button back = new Button("Back");
        Button save = new Button("Save");

        HBox hBox = new HBox();
        hBox.getChildren().addAll(save, back);
        hBox.setSpacing(70);
        back.setPrefSize(50, 30);
        save.setPrefSize(50, 30);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(enrollment, courseName, studentEmail, hBox);

        vBox.setSpacing(25);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(vBox);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        save.setOnAction(e -> {
            enrollmentDAO.addEnrollment(studentEmail.getValue().toString(), courseName.getValue().toString());
            stage.close();
            EnrollmentController.display();
        });

        back.setOnAction(e -> {
            stage.close();
            EnrollmentController.display();
        });
    }
}
