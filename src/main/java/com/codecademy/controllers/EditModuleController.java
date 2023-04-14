package com.codecademy.controllers;

import com.codecademy.dao.ContactPersonDAO;
import com.codecademy.dao.ContactPersonDAOimpl;
import com.codecademy.dao.ContentDAO;
import com.codecademy.dao.ContentDAOimpl;
import com.codecademy.dao.CourseDAO;
import com.codecademy.dao.CourseDAOImpl;
import com.codecademy.dao.ModuleDAO;
import com.codecademy.dao.ModuleDAOImpl;
import com.codecademy.database.DbConnection;
import com.codecademy.domain.ContactPerson;
import com.codecademy.domain.Content;
import com.codecademy.domain.Course;
import com.codecademy.domain.Module;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditModuleController {
    public static void display(Module module){
        ModuleDAO moduleDAO = new ModuleDAOImpl(new DbConnection());
        Stage stage = new Stage();
        stage.setTitle("Anhtuan Nguyen(2192526), Luuk beks(2192527), Miquel Stam(2192528)");
        stage.setWidth(1000);
        stage.setHeight(800);
        stage.setResizable(false);

        FlowPane root = new FlowPane();

        HBox contentBox = new HBox();
        Label contentLabel = new Label("Choose content");
        ChoiceBox<Integer> contentId = new ChoiceBox<Integer>();
        ContentDAO contentDAO = new ContentDAOimpl(new DbConnection());
        ObservableList<Content> contents = contentDAO.getContents();
        ObservableList<Integer> contentIds = FXCollections.observableArrayList();
        for (Content content : contents) {
            contentIds.add(content.getContentItemId());
        }
        contentId.setItems(contentIds);
        contentId.setValue(module.getContentId());

        contentBox.getChildren().addAll(contentLabel, contentId);
        contentBox.setSpacing(10);

        TextField moduleTitle = new TextField();
        moduleTitle.setText(module.getModuleTitle());

        TextField version = new TextField();
        version.setText(String.valueOf(module.getVersion()));

        HBox contactBox = new HBox();
        Label contactPersonLabel = new Label("Choose contact person: ");
        ChoiceBox<String> contactPersonEmail = new ChoiceBox<>();
        ContactPersonDAO contactPersonDao = new ContactPersonDAOimpl(new DbConnection());
        ObservableList<ContactPerson> contactPersons = contactPersonDao.getContactPersons();
        ObservableList<String> contactPersonEmails = FXCollections.observableArrayList();
        for (ContactPerson contactPerson : contactPersons) {
            contactPersonEmails.add(contactPerson.getContactPersonEmail());
        }
        contactPersonEmail.setItems(contactPersonEmails);
        contactPersonEmail.setValue(module.getContactPersonEmail());
        contactBox.getChildren().addAll(contactPersonLabel, contactPersonEmail);
        contactBox.setSpacing(10);

        HBox courseBox = new HBox();
        Label courseLabel = new Label("Choose course: ");
        ChoiceBox<String> courseName = new ChoiceBox<String>();
        CourseDAO courseDao = new CourseDAOImpl(new DbConnection());
        ObservableList<Course> courses = courseDao.getCourses();
        ObservableList<String> courseNames = FXCollections.observableArrayList();
        for (Course course : courses) {
            courseNames.add(course.getCourseName());
        }
        courseName.setItems(courseNames);
        courseName.setValue(module.getCourseName());
        courseBox.getChildren().addAll(courseLabel, courseName);
        courseBox.setSpacing(10);

        moduleTitle.setPromptText("Module title");
        version.setPromptText("Version");
        
       
        Button back = new Button("Back");
        Button save = new Button("Save");
      
        HBox hBox = new HBox();
        hBox.getChildren().addAll(save, back);
        hBox.setSpacing(70);
        back.setPrefSize(50, 30);
        save.setPrefSize(50, 30);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(contentBox, moduleTitle, version, contactBox, courseBox, hBox);
        vBox.setSpacing(25);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(vBox);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        save.setOnAction(e -> {
            float versionFloatValue = 0.0f;
            try {
                versionFloatValue = Float.parseFloat(version.getText());
            } catch (NumberFormatException ex) {
                // Show an error message and return without submitting the form
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Invalid input");
                alert.setContentText("Please enter a valid floating point number for the version field.");
                alert.showAndWait();
                return;
            }
            module.setContentId(contentId.getValue());
            module.setModuleTitle(moduleTitle.getText());
            module.setVersion(versionFloatValue);
            module.setContactPersonEmail(contactPersonEmail.getValue());
            module.setCourseName(courseName.getValue());
            moduleDAO.updateModule(module);
            stage.close();
            ModuleController.display();
        });

        back.setOnAction(e -> {
            ModuleController.display();
            stage.close();
        });
    }
       
}