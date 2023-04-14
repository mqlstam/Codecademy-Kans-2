
package com.codecademy.controllers;

import com.codecademy.dao.CourseDAO;
import com.codecademy.dao.CourseDAOImpl;
import com.codecademy.dao.ModuleDAO;
import com.codecademy.dao.ModuleDAOImpl;
import com.codecademy.database.DbConnection;
import com.codecademy.domain.Course;
import com.codecademy.domain.Difficulty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * 
 * The AddCourseController class is a controller class for adding a new course
 * to the system.
 * 
 * It allows the user to input a course name, topic, introduction text, tag, and
 * difficulty level,
 * 
 * and saves the new course to the database upon clicking the "Save" button.
 * 
 * This class also has a back button that takes the user back to the
 * CourseController display.
 * 
 * 
 */

public class AddCourseController {

    private static String courseDifficulty;
    private static int moduleId;

    public static void display() {

        DbConnection dbConnection = new DbConnection();
        CourseDAO courseDAO = new CourseDAOImpl(dbConnection);
        ModuleDAO moduleDAO = new ModuleDAOImpl(dbConnection);
        Stage stage = new Stage();
        stage.setTitle("Anhtuan Nguyen(2192526), Luuk beks(2192527), Miquel Stam(2192528)");
        stage.setWidth(1000);
        stage.setHeight(800);
        stage.setResizable(false);

        FlowPane root = new FlowPane();

        Scene scene = new Scene(root);
        Label course = new Label("Course");
        course.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        TextField courseName = new TextField();

        TextField courseTopic = new TextField();
        TextField courseIntroText = new TextField();
        TextField courseTag = new TextField();

        ChoiceBox<String> difficulty = new ChoiceBox<>();
        ObservableList<String> difficultyList = FXCollections.observableArrayList("Beginner", "Advanced", "Expert");
        difficulty.getItems().addAll(difficultyList);
        difficulty.setValue("Beginner");
        courseDifficulty = difficulty.getValue();
        difficulty.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
            courseDifficulty = newValue;
        });

        courseName.setPromptText("Course name");
        courseTopic.setPromptText("Course topic");
        courseIntroText.setPromptText("Course Intro Text");
        courseTag.setPromptText("Course tag");
        Button back = new Button("Back");
        Button save = new Button("Save");

        save.setOnAction(e -> {
            courseDAO.addCourse(new Course(courseName.getText(), courseTopic.getText(), courseIntroText.getText(),
                    courseTag.getText(), Difficulty.valueOf(courseDifficulty.toUpperCase())));
            // moduleDAO.
            stage.close();
            CourseController.display();
        });

        HBox hBox = new HBox();
        courseIntroText.setPrefSize(500, 100);
        hBox.getChildren().addAll(save, back);
        hBox.setSpacing(70);
        back.setPrefSize(50, 30);
        save.setPrefSize(50, 30);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(course, courseName, courseTopic, courseIntroText, courseTag, difficulty, hBox);

        vBox.setSpacing(25);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(vBox);

        back.setOnAction(e -> {
            stage.close();
            CourseController.display();
        });

        stage.setScene(scene);
        stage.show();
    }
}
