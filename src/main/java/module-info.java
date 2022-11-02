module com.example.bbms {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bbms to javafx.fxml;
    exports com.example.bbms;
    exports com.example.bbms.controller;
    opens com.example.bbms.controller to javafx.fxml;
}