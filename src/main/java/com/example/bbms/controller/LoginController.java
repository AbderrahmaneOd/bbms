package com.example.bbms.controller;

import com.example.bbms.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    @FXML
    void signIn(ActionEvent event) throws IOException {

        if(userName.getText().equals("a") && password.getText().equals("a")){
            root = FXMLLoader.load(MainApplication.class.getResource("view/Admin-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Admin Dashboard");
            stage.setScene(scene);
            stage.show();
        }else if(userName.getText().equals("h") && password.getText().equals("h")){
            root = FXMLLoader.load(MainApplication.class.getResource("view/Hospital-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Hospital Dashboard");
            stage.setScene(scene);
            stage.show();

        }else if(userName.getText().equals("b") && password.getText().equals("b")){
            root = FXMLLoader.load(MainApplication.class.getResource("view/BloodBank-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Blood Bank Dashboard");
            stage.setScene(scene);
            stage.show();
        }


    }


}