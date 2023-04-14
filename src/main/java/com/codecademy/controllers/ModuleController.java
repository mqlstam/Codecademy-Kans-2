package com.codecademy.controllers;



import com.codecademy.MainMenu;
import com.codecademy.dao.ModuleDAO;
import com.codecademy.dao.ModuleDAOImpl;
import com.codecademy.database.DbConnection;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ModuleController {
    public static void display(){
        DbConnection dbConnection = new DbConnection();
        ModuleDAO moduleDao = new ModuleDAOImpl(dbConnection);
        Stage stage = new Stage();
        stage.setTitle("Anhtuan Nguyen(2192526), Luuk beks(2192527), Miquel Stam(2192528)");
        stage.setWidth(1000);
        stage.setHeight(800);
        stage.setResizable(false);
        
        FlowPane root = new FlowPane();
        
        ObservableList<com.codecademy.domain.Module> list = moduleDao.getAllModules();
        
        TableView<com.codecademy.domain.Module> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Label moduleOverview = new Label("Module overview");
        moduleOverview.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        
        table.setItems(list);
        TableColumn<com.codecademy.domain.Module, Integer> followNumber = new TableColumn<>("Follow number");
        followNumber.setCellValueFactory(new PropertyValueFactory<>("followNumber"));
        TableColumn<com.codecademy.domain.Module, Integer> contentId = new TableColumn<>("Content Id");
        contentId.setCellValueFactory(new PropertyValueFactory<>("contentId"));
        TableColumn<com.codecademy.domain.Module, String> moduleTitle = new TableColumn<>("Module title");
        moduleTitle.setCellValueFactory(new PropertyValueFactory<>("moduleTitle"));
        TableColumn<com.codecademy.domain.Module, Float> version = new TableColumn<>("Version");
        version.setCellValueFactory(new PropertyValueFactory<>("version"));
        TableColumn<com.codecademy.domain.Module, String> contactPersonEmail = new TableColumn<>("Contact Person Email");
        contactPersonEmail.setCellValueFactory(new PropertyValueFactory<>("contactPersonEmail"));
        TableColumn<com.codecademy.domain.Module, String> courseName = new TableColumn<>("Course name");
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        
        table.getColumns().addAll(followNumber, contentId, moduleTitle, version, contactPersonEmail, courseName);
        
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
        vbox.getChildren().addAll(moduleOverview, table, hBox);
        
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(vbox);
        
        Scene scene = new Scene(root);
        
        add.setOnAction(e -> {
            AddModuleController.display();
            stage.close();
        });
        
        edit.setOnAction(e -> {
            com.codecademy.domain.Module module = table.getSelectionModel().getSelectedItem();
            if (module != null) {
                EditModuleController.display(module);
                stage.close();
            } else {
                System.out.println("Please select a module");
            }
        });
        
        back.setOnAction(e -> {
            MainMenu.display();
            stage.close();
        });
        
        delete.setOnAction(e -> {
            com.codecademy.domain.Module module = table.getSelectionModel().getSelectedItem();
            if (module == null) {
                return;
            }
            int selectedFollowNumber = module.getFollowNumber(); // get the followNumber of the selected Module
            moduleDao.deleteModule(selectedFollowNumber); // pass the followNumber to the deleteModule method
            stage.close();
            ModuleController.display();
        });
        
        stage.setScene(scene);
        stage.show();
    }
}
