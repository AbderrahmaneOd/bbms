package com.example.bbms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    void signIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("view/Admin-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Admin Dashboard");
        stage.setScene(scene);
        stage.show();









        /*FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("../view/Admin-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();*/
    }


}