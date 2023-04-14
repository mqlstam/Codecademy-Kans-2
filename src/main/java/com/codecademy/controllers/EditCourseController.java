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

public class EditCourseController {
    private static String courseDifficulty;
    private static int moduleId;
    public static void display(Course course) {
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
        Label courseLabel = new Label("Course");
        courseLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        
        TextField courseName = new TextField();
        courseName.setText(course.getCourseName());
        courseName.setEditable(false);
        // ChoiceBox<String> ModuleId = new ChoiceBox<>();
        // ObservableList<String> ModuleIdList = moduleDAO.getModuleIds();
        // ModuleId.getItems().addAll(ModuleIdList);
        // ModuleId.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
        //     moduleId = Integer.parseInt(newValue);
        // });
        // ModuleId.setValue(String.valueOf(course.getModuleId()));
        
        TextField courseTopic = new TextField();
        courseTopic.setText(course.getCourseTopic());
        TextField courseIntroText = new TextField();
        courseIntroText.setText(course.getCourseIntroText());
        TextField courseTag = new TextField();
        courseTag.setText(course.getCourseTag());
        
        ChoiceBox<String> difficulty = new ChoiceBox<>();
        ObservableList<String> difficultyList = FXCollections.observableArrayList("Beginner", "Advanced", "Expert");
        difficulty.getItems().addAll(difficultyList);
        difficulty.setValue(course.getDifficulty().toString());
        courseDifficulty = difficulty.getValue();
        difficulty.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
            courseDifficulty = newValue;
        });

        courseName.setPromptText("Course name");
        courseTopic.setPromptText("Course topic");
        courseIntroText.setPromptText("Course Intro Text");
        Button back = new Button("Back");
        Button save = new Button("Save");
        save.setOnAction(e -> {
            courseDAO.updateCourse(new Course(courseName.getText(), courseTopic.getText(), courseIntroText.getText(), courseTag.getText(), Difficulty.valueOf(courseDifficulty.toUpperCase())));
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
        vBox.getChildren().addAll(courseLabel, courseName, courseTopic, courseIntroText, courseTag, difficulty, hBox);
        // vBox.getChildren().addAll(courseLabel, courseName, ModuleId, courseTopic, courseIntroText, courseTag, difficulty, hBox);
        
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