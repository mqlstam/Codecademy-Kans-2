module com.codecademy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.codecademy to javafx.fxml;
    opens com.codecademy.controllers to javafx.fxml;
    opens com.codecademy.dao to javafx.fxml;
    opens com.codecademy.database to javafx.fxml;
    opens com.codecademy.domain to javafx.fxml;

    exports com.codecademy;
    exports com.codecademy.controllers;
    exports com.codecademy.dao;
    exports com.codecademy.database;
    exports com.codecademy.domain;
    


}


