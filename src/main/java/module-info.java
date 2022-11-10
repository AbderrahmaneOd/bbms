module com.example.bbms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bbms to javafx.fxml;
    exports com.example.bbms;
    exports com.example.bbms.controller;
    opens com.example.bbms.controller to javafx.fxml;
}