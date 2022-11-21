package com.example.bbms.controller;

import com.example.bbms.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class BloodBankController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Pane StockManagementPane;

    @FXML
    private Pane addDonorsPane;
    @FXML
    private Button btnAddDonors;

    @FXML
    private Button btnHospitalRequest;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnSearchDonors;

    @FXML
    private Button btnStockManagement;

    @FXML
    private Pane hospitalRequestPane;

    @FXML
    private Pane searchDonorsPane;

    @FXML
    void btnAddDonors(ActionEvent event) {
        addDonorsPane.toFront();
    }

    @FXML
    void btnHospitalRequest(ActionEvent event) {
        hospitalRequestPane.toFront();
    }

    @FXML
    void btnSearchDonors(ActionEvent event) {
        searchDonorsPane.toFront();
    }

    @FXML
    void btnStockManagement(ActionEvent event) {
        StockManagementPane.toFront();
    }

    @FXML
    void btnLogOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(MainApplication.class.getResource("view/Login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Admin Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
