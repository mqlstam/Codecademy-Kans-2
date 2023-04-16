package com.codecademy.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.codecademy.MainMenu;
import com.codecademy.dao.StudentDAO;
import com.codecademy.dao.StudentDAOImpl;
import com.codecademy.database.DbConnection;
import com.codecademy.domain.Student;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * 
 * The StudentController class is responsible for displaying the student
 * overview screen, which lists all the students in the system in a table.
 * The class also provides functionality to add, edit, and delete students, as
 * well as view statistics for a selected student.
 */
public class StudentController {
    /**
     * 
     * Displays the student overview screen, which lists all the students in the
     * system in a table.
     * 
     * Provides functionality to add, edit, and delete students, as well as view
     * statistics for a selected student.
     */
    public static void display() {

        Stage stage = new Stage();
        stage.setTitle("Anhtuan Nguyen(2192526), Luuk beks(2192527), Miquel Stam(2192528)");
        stage.setWidth(1000);
        stage.setHeight(800);
        stage.setResizable(false);

        FlowPane root = new FlowPane();

        Label studentOverview = new Label("Student overview");
        studentOverview.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        StudentDAO studentDAO = new StudentDAOImpl(new DbConnection());
        ObservableList list = studentDAO.getStudents();
        TableView<Student> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(list);
        TableColumn<Student, String> emailCol = new TableColumn<>("email");
        emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        TableColumn<Student, String> nameCol = new TableColumn<>("name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        TableColumn<Student, LocalDate> birthdayCol = new TableColumn<>("birthday");
        birthdayCol.setCellValueFactory(new PropertyValueFactory<Student, LocalDate>("birthDate"));
        TableColumn<Student, String> genderCol = new TableColumn<>("gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));
        TableColumn<Student, String> streetCol = new TableColumn<>("Street");
        streetCol.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getStreet()));
        TableColumn<Student, String> houseNumberCol = new TableColumn<>("House Number");
        houseNumberCol.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getHouseNumber()));
        TableColumn<Student, String> postalCodeCol = new TableColumn<>("Postal Code");
        postalCodeCol.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getPostalCode()));
        TableColumn<Student, String> cityCol = new TableColumn<>("City");
        cityCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getCity()));
        TableColumn<Student, String> countryCol = new TableColumn<>("Country");
        countryCol.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getCountry()));
        table.getColumns().addAll(emailCol, nameCol, birthdayCol, genderCol, streetCol, houseNumberCol, postalCodeCol,
                cityCol, countryCol);
        table.setPrefWidth(800);

        Button add = new Button("Add");
        Button edit = new Button("Edit");
        Button delete = new Button("Delete");
        Button back = new Button("Back");

        HBox hBox = new HBox();

        hBox.getChildren().addAll(add, edit, delete, back);
        hBox.setSpacing(25);

        add.setPrefSize(50, 30);
        edit.setPrefSize(50, 30);
        delete.setPrefSize(80, 30);
        back.setPrefSize(50, 30);
        table.setEditable(false);

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(studentOverview, table, hBox);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(vbox);

        Scene scene = new Scene(root);

        add.setOnAction(e -> {
            AddStudentController.display();
            stage.close();
        });

        back.setOnAction(e -> {
            MainMenu.display();
            stage.close();
        });

        edit.setOnAction(e -> {
            Student student = table.getSelectionModel().getSelectedItem();
            if (student != null) {
                EditStudentController.display(student);
                stage.close();
            } else {
                System.out.println("No student selected");
            }
        });

        delete.setOnAction(e -> {
            Student student = table.getSelectionModel().getSelectedItem();
            if (student != null) {
                studentDAO.deleteStudent(student);
                stage.close();
                display();
            } else {
                System.out.println("No student selected");
            }
        });

        table.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Student selectedStudent = table.getSelectionModel().getSelectedItem();
                if (selectedStudent != null) {
                    StudentStatistics statistics = new StudentStatistics(selectedStudent);
                    StudentStatistics.display();
                    stage.close();
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }
}
