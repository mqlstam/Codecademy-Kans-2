package com.codecademy.controllers;

import com.codecademy.dao.CertificateDAO;
import com.codecademy.dao.CertificateDAOimpl;
import com.codecademy.database.DbConnection;
import com.codecademy.domain.Certificate;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class EditCertificateController {

    private static Certificate certificate;

    public EditCertificateController(Certificate certificate) {
        this.certificate = certificate;
    }
    
    public static void display(Certificate certificate) {
        DbConnection dbConnection = new DbConnection();
        CertificateDAO certificateDAO = new CertificateDAOimpl(dbConnection);
        Stage stage = new Stage();
        stage.setTitle("Anhtuan Nguyen(2192526), Luuk beks(2192527), Miquel Stam(2192528)");
        stage.setWidth(1000);
        stage.setHeight(800);
        stage.setResizable(false);

        Label updateCertificatLabel = new Label("Update Certificate");
        updateCertificatLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        FlowPane root = new FlowPane();

        TextField grade = new TextField();
        grade.setText(String.valueOf(certificate.getGrade()));

        TextField employee = new TextField();
        employee.setText(certificate.getEmployee());
        certificate.setEmployee(certificate.getEmployee());

        grade.setPromptText("grade");
        employee.setPromptText("Employee");

        Button back = new Button("Back");
        Button update = new Button("update");
      
        HBox hBox = new HBox();
        hBox.getChildren().addAll(update, back);
        hBox.setSpacing(70);
        back.setPrefSize(50, 30);
        update.setPrefSize(50, 30);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(updateCertificatLabel, grade, employee, hBox);
        
        vBox.setSpacing(25);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(vBox);


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        update.setOnAction(e -> {
            double gradeValue = Double.parseDouble(grade.getText());
            certificateDAO.updateCertificate(new Certificate(certificate.getCertificateID(), gradeValue, employee.getText()));
            stage.close();
            CertificateController.display();
        });

        back.setOnAction(e -> {
            stage.close();
            CertificateController.display();
        });
    }
}
