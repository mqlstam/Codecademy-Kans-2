package com.codecademy.controllers;

import java.util.List;

import com.codecademy.MainMenu;
import com.codecademy.dao.CourseDAO;
import com.codecademy.dao.CourseDAOImpl;
import com.codecademy.dao.ModuleDAO;
import com.codecademy.dao.ModuleDAOImpl;
import com.codecademy.dao.WebcastDAO;
import com.codecademy.dao.WebcastDAOImpl;
import com.codecademy.database.DbConnection;
import com.codecademy.domain.Course;
import com.codecademy.domain.ModuleProgress;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CourseStatistics {

    /**
     * 
     * This class is responsible for displaying the details of a selected course,
     * 
     * including recommended courses, the number of students who completed the
     * course,
     * 
     * and the average progress per module for all students in the course.
     * 
     * It creates a JavaFX Stage and displays the course details in a TabPane.
     * 
     * The recommended courses and number of completed courses are retrieved from
     * the CourseDAO.
     * 
     * The average progress per module is retrieved from the ModuleDAO.
     * 
     * The class also defines the event handlers for the Back buttons in each tab.
     * 
     */
    private static Course course;

    public CourseStatistics(Course course) {
        this.course = course;
    }

    public static void display() {
        Stage stage = new Stage();
        stage.setTitle("Anhtuan Nguyen(2192526), Luuk beks(2192527), Miquel Stam(2192528)");
        stage.setWidth(1000);
        stage.setHeight(800);
        stage.setResizable(false);

        TabPane tabPane = new TabPane();

        Tab recommendedcourses = new Tab("Recommended courses");
        Tab numCompletedCourses = new Tab("number of completed courses");
        Tab averageProgressPerModule = new Tab("average progress per module");

        CourseDAO courseDAO = new CourseDAOImpl(new DbConnection());
        ModuleDAO moduleDAO = new ModuleDAOImpl(new DbConnection());

        List<String> recommendedCourse = courseDAO.getRecommendedCourses(course.getCourseName());
        int numberCompletedCourses = courseDAO.getNumCompletedCourses(course.getCourseName());

        // Create the ListView and Back button for the Recommended courses tab
        FlowPane root = new FlowPane();
        Button back = new Button("Back");
        Label titleLabel = new Label("Recommended courses");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        ListView<String> listView = new ListView<>(FXCollections.observableArrayList(recommendedCourse));

        VBox vbox = new VBox();
        vbox.setSpacing(15);
        vbox.getChildren().addAll(titleLabel);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(spacer);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(hbox, listView, back);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(vbox);

        // Create the ListView and Back button for the numCompletedCourses tab
        FlowPane coursesRoot = new FlowPane();
        Button coursesBack = new Button("Back");
        Label coursesTitleLabel = new Label(
                "Number of students who completed this course: " + Integer.toString(numberCompletedCourses));
        coursesTitleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        VBox coursesVbox = new VBox(coursesTitleLabel);
        coursesVbox.setSpacing(10);
        Region coursesSpacer = new Region();
        HBox.setHgrow(coursesSpacer, Priority.ALWAYS);
        HBox coursesHbox = new HBox(coursesSpacer, coursesBack);
        coursesHbox.setAlignment(Pos.BOTTOM_RIGHT);
        coursesHbox.setPadding(new Insets(10));
        coursesVbox.getChildren().addAll(coursesHbox);
        coursesRoot.setAlignment(Pos.CENTER);
        coursesRoot.getChildren().addAll(coursesVbox);

        // Create the tableview and Back button for the averageProgressPerModule tab
        TableView<ModuleProgress> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(400);

        Button progressBack = new Button("Back");

        Label progressTitleLabel = new Label("Average progress per module");
        progressTitleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Region progressSpacer = new Region();
        HBox.setHgrow(progressSpacer, Priority.ALWAYS);

        HBox progressHbox = new HBox(progressSpacer, progressBack);
        progressHbox.setAlignment(Pos.BOTTOM_RIGHT);
        progressHbox.setPadding(new Insets(10));

        VBox progressVbox = new VBox(progressTitleLabel, tableView, progressHbox);
        progressVbox.setSpacing(10);

        FlowPane progressRoot = new FlowPane(progressVbox);
        progressRoot.setAlignment(Pos.CENTER);

        // Define the columns for the table
        TableColumn<ModuleProgress, Integer> followNumberCol = new TableColumn<>("FollowNumber");
        followNumberCol.setCellValueFactory(new PropertyValueFactory<>("FollowNumber"));

        TableColumn<ModuleProgress, String> moduleNameCol = new TableColumn<>("ModuleTitle");
        moduleNameCol.setCellValueFactory(new PropertyValueFactory<>("ModuleTitle"));

        TableColumn<ModuleProgress, Double> avgProgressCol = new TableColumn<>("progress");
        avgProgressCol.setCellValueFactory(new PropertyValueFactory<>("progress"));

        // Add the columns to the table
        tableView.getColumns().addAll(followNumberCol, moduleNameCol, avgProgressCol);

        // Populate the table with data
        List<ModuleProgress> moduleProgressList = moduleDAO
                .getAverageProgressPerModuleAllStudents(course.getCourseName());
        tableView.setItems(FXCollections.observableArrayList(moduleProgressList));

        averageProgressPerModule.setContent(progressRoot);
        recommendedcourses.setContent(root);
        numCompletedCourses.setContent(coursesRoot);

        tabPane.getTabs().addAll(recommendedcourses, numCompletedCourses, averageProgressPerModule);
        tabPane.getSelectionModel().selectFirst();

        Scene scene = new Scene(tabPane);

        back.setOnAction(e -> {
            CourseController.display();
            stage.close();
        });

        progressBack.setOnAction(e -> {
            CourseController.display();
            stage.close();
        });

        coursesBack.setOnAction(e -> {
            CourseController.display();
            stage.close();
        });

        stage.setScene(scene);
        stage.show();
    }

}
