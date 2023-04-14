package com.codecademy.controllers;

import java.util.List;

import com.codecademy.dao.CourseDAO;
import com.codecademy.dao.CourseDAOImpl;
import com.codecademy.dao.ModuleDAO;
import com.codecademy.dao.ModuleDAOImpl;
import com.codecademy.dao.StudentDAO;
import com.codecademy.dao.StudentDAOImpl;
import com.codecademy.database.DbConnection;
import com.codecademy.domain.Course;
import com.codecademy.domain.Student;
import com.codecademy.domain.Module;
import com.codecademy.domain.ModuleProgress;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class StudentStatistics {

    private static Student student;

    public StudentStatistics(Student student) {
        this.student = student;
    }

    public static void display() {

        // Create the stage and set its properties
        Stage stage = new Stage();
        stage.setTitle("Anhtuan Nguyen(2192526), Luuk beks(2192527), Miquel Stam(2192528)");
        stage.setWidth(1000);
        stage.setHeight(800);
        stage.setResizable(false);

        // Create the TabPane and its tabs
        TabPane tabPane = new TabPane();
        Tab certificatesAchieved = new Tab("Certificates achieved");
        Tab progressPerModule = new Tab("Progress per module");

        StudentDAO studentDAO = new StudentDAOImpl(new DbConnection());
        CourseDAO courseDAO = new CourseDAOImpl(new DbConnection());
        ModuleDAO moduleDAO = new ModuleDAOImpl(new DbConnection());

        List<String> getCertificatesByEmail = studentDAO.getCertificatesByEmail(student.getEmail());

        // Create the ListView and Back button for the certificatesAchieved tab
        FlowPane root = new FlowPane();
        Button back = new Button("Back");
        ListView<String> listView = new ListView<>(FXCollections.observableArrayList(getCertificatesByEmail));
        Label titleLabel = new Label("Certificates achieved");
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

        // Create the ListView and Back button for the progressPerModule tab
        Button back2 = new Button("Back");
        ComboBox<Course> courseComboBox = new ComboBox<>();
        Label courseLabel = new Label("Select course:");
        courseLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        TableView<ModuleProgress> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<ModuleProgress, Integer> moduleIdCol = new TableColumn<>("FollowNumber");
        moduleIdCol.setCellValueFactory(new PropertyValueFactory<>("FollowNumber"));
        TableColumn<ModuleProgress, String> titleCol = new TableColumn<>("ModuleTitle");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("ModuleTitle"));
        TableColumn<ModuleProgress, Double> progressCol = new TableColumn<>("progress");
        progressCol.setCellValueFactory(new PropertyValueFactory<>("progress"));
        tableView.getColumns().addAll(moduleIdCol, titleCol, progressCol);
        VBox progressBox = new VBox(courseLabel, courseComboBox, tableView, back2);
        progressBox.setSpacing(10);

        moduleIdCol.setText("FollowNumber");
        titleCol.setText("ModuleTitle");
        progressCol.setText("progress");

        // Populate the ComboBox with courses that the student is enrolled in
        List<Course> courses = courseDAO.getCoursesByStudentEmail(student.getEmail());
        courseComboBox.getItems().addAll(courses);

        // Set the content of each tab to its corresponding pane
        certificatesAchieved.setContent(root);
        progressPerModule.setContent(progressBox);

        // Set up the event handler for the back button
        back2.setOnAction(e -> {
            StudentController.display();
            stage.close();
        });

        // Set up the event handler for the courseComboBox
        courseComboBox.setOnAction(e -> {
            Course selectedCourse = courseComboBox.getValue();
            if (selectedCourse != null) {
                List<ModuleProgress> moduleProgressList = moduleDAO.getAverageProgressPerModule(selectedCourse.getCourseName(),
                        student.getEmail());
                tableView.setItems(FXCollections.observableArrayList(moduleProgressList));
            }
        });

        
        // Add the tabs to the TabPane
        tabPane.getTabs().addAll(certificatesAchieved, progressPerModule);

        // Set the selected tab to the first tab
        tabPane.getSelectionModel().selectFirst();

        back.setOnAction(e -> {
            StudentController.display();
            stage.close();
        });

        // Create the scene and add the TabPane to it
        Scene scene = new Scene(tabPane);
        stage.setScene(scene);

        stage.setScene(scene);
        stage.show();
    }
}
