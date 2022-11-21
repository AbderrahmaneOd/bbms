package com.example.bbms.controller;

import com.example.bbms.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HospitalController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Pane stockManagementPane;


    @FXML
    private Pane searchBloodGroupsPane;

    @FXML
    private Pane searchDonorsPane;

    @FXML
    private Pane BloodRequestPane;
    @FXML
    void btnBloodRequest(ActionEvent event) {
        BloodRequestPane.toFront();
    }

    @FXML
    void btnSearchBloodGroups(ActionEvent event) {
        searchBloodGroupsPane.toFront();
    }

    @FXML
    void btnSearchDonors(ActionEvent event) {
        searchDonorsPane.toFront();
    }

    @FXML
    void btnStockManagement(ActionEvent event) {
        stockManagementPane.toFront();
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

