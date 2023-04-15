package com.codecademy.controllers;

import java.util.List;

import com.codecademy.MainMenu;
import com.codecademy.dao.CourseDAO;
import com.codecademy.dao.CourseDAOImpl;
import com.codecademy.dao.EnrollmentDAO;
import com.codecademy.dao.EnrollmentDAOImpl;
import com.codecademy.dao.StudentDAO;
import com.codecademy.dao.StudentDAOImpl;
import com.codecademy.dao.WebcastDAO;
import com.codecademy.dao.WebcastDAOImpl;
import com.codecademy.database.DbConnection;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StatisticsController {

    public static void display() {
        Stage stage = new Stage();
        stage.setTitle("Anhtuan Nguyen(2192526), Luuk beks(2192527), Miquel Stam(2192528)");
        stage.setWidth(1000);
        stage.setHeight(800);
        stage.setResizable(false);

        TabPane tabPane = new TabPane();

        Tab webcastsTab = new Tab("Top 3 Viewed Webcasts");
        Tab coursesTab = new Tab("Top 3 courses with most certificates");
        Tab coursecompletedGender = new Tab("Gender with most completed courses");

        WebcastDAO webcastDAO = new WebcastDAOImpl(new DbConnection());
        CourseDAO courseDAO = new CourseDAOImpl(new DbConnection());
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl(new DbConnection());

        List<String> top3Webcasts = webcastDAO.getTop3ViewedWebcasts();
        List<String> top3Courses = courseDAO.getTop3CertifiedCourses();
        double genderPercentage = enrollmentDAO.getCompletionPercentageByGender(null);

        

        // Create the ListView and Back button for the Top 3 Viewed Webcasts tab
        FlowPane root = new FlowPane();
        Button back = new Button("Back");
        ListView<String> listView = new ListView<>(FXCollections.observableArrayList(top3Webcasts));
        System.out.println("Top 3 viewed webcasts: " + top3Webcasts);
        Label titleLabel = new Label("Top 3 Viewed Webcasts");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().addAll(titleLabel, listView);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(spacer, back);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(hbox);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(vbox);

        // Create the ListView and Back button for the Top 3 Courses with the Most
        // Certificates tab
        FlowPane coursesRoot = new FlowPane();
        Button coursesBack = new Button("Back");
        ListView<String> coursesListView = new ListView<>(FXCollections.observableArrayList(top3Courses));
        Label coursesTitleLabel = new Label("Top 3 Courses with the Most Certificates");
        coursesTitleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        VBox coursesVbox = new VBox(coursesTitleLabel, coursesListView);
        coursesVbox.setSpacing(10);
        Region coursesSpacer = new Region();
        HBox.setHgrow(coursesSpacer, Priority.ALWAYS);
        HBox coursesHbox = new HBox(coursesSpacer, coursesBack);
        coursesHbox.setAlignment(Pos.BOTTOM_RIGHT);
        coursesHbox.setPadding(new Insets(10));
        coursesVbox.getChildren().addAll(coursesHbox);
        coursesRoot.setAlignment(Pos.CENTER);
        coursesRoot.getChildren().addAll(coursesVbox);

        // Create the ChoiceBox and Label for the Completion Percentage by Gender tab
        ChoiceBox<String> genderChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList("Male", "Female", "Other"));
        Label percentageLabel = new Label(String.format("%.2f%%", genderPercentage));
        percentageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        percentageLabel.setAlignment(Pos.CENTER);

        // Add a change listener to the ChoiceBox to update the percentage label
        genderChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            double newPercentage = enrollmentDAO.getCompletionPercentageByGender(newValue);
            percentageLabel.setText(String.format("%.2f%%", newPercentage));
        });

        // Add the ChoiceBox and Label to the Completion Percentage by Gender tab
        Button genderBack = new Button("Back");
        VBox genderVBox = new VBox(genderChoiceBox, percentageLabel, genderBack);
        genderVBox.setSpacing(50);
        genderVBox.setPadding(new Insets(50));
        percentageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        genderVBox.setAlignment(Pos.CENTER);
        coursecompletedGender.setContent(genderVBox);

        // Add the Completion Percentage by Gender tab to the TabPane

        webcastsTab.setContent(root);
        coursesTab.setContent(coursesRoot);

        tabPane.getTabs().addAll(webcastsTab, coursesTab, coursecompletedGender);
        tabPane.getSelectionModel().selectFirst();

        Scene scene = new Scene(tabPane);

        back.setOnAction(e -> {
            MainMenu.display();
            stage.close();
        });

        coursesBack.setOnAction(e -> {
            MainMenu.display();
            stage.close();
        });

        genderBack.setOnAction(e -> {
            MainMenu.display();
            stage.close();
        });


        stage.setScene(scene);
        stage.show();

    }

}
