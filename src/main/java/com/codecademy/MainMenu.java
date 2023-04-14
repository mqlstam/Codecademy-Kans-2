package com.codecademy;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

import com.codecademy.controllers.CertificateController;
import com.codecademy.controllers.CourseController;
import com.codecademy.controllers.EnrollmentController;
import com.codecademy.controllers.StatisticsController;
import com.codecademy.controllers.StudentController;
import com.codecademy.domain.Certificate;

/**
 * JavaFX App
 */
public class MainMenu extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Anhtuan Nguyen(2192526), Luuk beks(2192527), Miquel Stam(2192528) ");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.setResizable(false);

        Label codecademy = new Label("Codecademy");
        codecademy.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        
        codecademy.setTranslateY(-55);
        codecademy.setTranslateX(-275);
        FlowPane root = new FlowPane();
        Button student = new Button("Student");
        Button course = new Button("Course");
        Button enrollment = new Button("Enrollment");
        Button certificate = new Button("Certificate");
        Button statistics = new Button("Statistics");
        Button exit = new Button("Exit");
        student.setPrefSize(150, 50);
        course.setPrefSize(150, 50);
        enrollment.setPrefSize(150, 50);
        certificate.setPrefSize(150, 50);
        statistics.setPrefSize(150, 50);
        exit.setPrefSize(100, 30);
        exit.setTranslateX(350);
        exit.setTranslateY(60);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(codecademy, student, course, enrollment, certificate, statistics, exit);
        vBox.setSpacing(25);

        root.setAlignment(Pos.CENTER);
        root.getChildren().add(vBox);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Paths.get("src/main/resources/com/codecademy/styles.css").toUri().toString());


        student.setOnAction(e -> {
           StudentController.display(); 
           stage.close();
        });

        course.setOnAction(e -> {
            CourseController.display();
            stage.close();
        });

        enrollment.setOnAction(e -> {
            EnrollmentController.display();
            stage.close();
        });

        certificate.setOnAction(e -> {
            CertificateController.display();
            stage.close();
        });
        
        statistics.setOnAction(e -> {
            StatisticsController.display();
            stage.close();
        });

        exit.setOnAction(e -> {
            stage.close();
        });

        stage.setScene(scene);
        stage.show();
  
    }
    public static void display(){
        Stage stage = new Stage();
        stage.setTitle("Anhtuan Nguyen(2192526), Luuk beks(2192527), Miquel Stam(2192528)");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.setResizable(false);

        Label codecademy = new Label("Codecademy");
        codecademy.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        
        codecademy.setTranslateY(-55);
        codecademy.setTranslateX(-275);
        FlowPane root = new FlowPane();
        Button student = new Button("Student");
        Button course = new Button("Course");
        Button enrollment = new Button("Enrollment");
        Button certificate = new Button("Certificate");
        Button exit = new Button("Exit");
        Button statistics = new Button("Statistics");
        student.setPrefSize(150, 50);
        course.setPrefSize(150, 50);
        enrollment.setPrefSize(150, 50);
        certificate.setPrefSize(150, 50);
        statistics.setPrefSize(150, 50);
        exit.setPrefSize(100, 30);
        exit.setTranslateX(350);
        exit.setTranslateY(60);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(codecademy, student, course, enrollment, certificate, statistics, exit);
        vBox.setSpacing(25);

        root.setAlignment(Pos.CENTER);
        root.getChildren().add(vBox);
        Scene scene = new Scene(root);
        //get the css file
        scene.getStylesheets().add(Paths.get("codecademy/src/main/resources/com/codecademy/Styles.css").toUri().toString());

        
        student.setOnAction(e -> {
           StudentController.display(); 
           stage.close();
        });

        course.setOnAction(e -> {
            CourseController.display();
            stage.close();
        });

        enrollment.setOnAction(e -> {
            EnrollmentController.display();
            stage.close();
        });

        certificate.setOnAction(e -> {
            CertificateController.display();
            stage.close();
        });

        exit.setOnAction(e -> {
            stage.close();
        });

        statistics.setOnAction(e -> {
            StatisticsController.display();
            stage.close();
        });

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}