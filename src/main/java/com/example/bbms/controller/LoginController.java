package com.example.bbms.controller;

import com.example.bbms.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userId;
    @FXML
    private ChoiceBox<String> userType;
    final private String[] userTypes = {"Admin", "Hospital", "Blood Bank"};

    Connection con;
    PreparedStatement pst;

    // Connect to DB
    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost/bbms", "root", "abderrahmane");
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        int userNameVariable = Integer.parseInt(userId.getText());
        String userPasswordVariable = "";

        if (userType.getValue().equals("Hospital")) {
            try {
                pst = con.prepareStatement("SELECT password_h FROM hospital WHERE id_h = ?");
                pst.setInt(1, userNameVariable);
                // Admin
                ResultSet resultSet = pst.executeQuery();
                if (resultSet.next())
                    userPasswordVariable = resultSet.getString("password_h");

                if (userPasswordVariable.equals(password.getText())) {
                    root = FXMLLoader.load(MainApplication.class.getResource("view/Hospital-view.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setTitle("Hospital Dashboard");
                    stage.setScene(scene);
                    stage.show();
                }

            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        } else if (userType.getValue().equals("Blood Bank")) {
            try {
                pst = con.prepareStatement("SELECT password_bk FROM blood_bank WHERE id_bk = ?");
                pst.setInt(1, userNameVariable);
                // Admin
                ResultSet resultSet = pst.executeQuery();
                if (resultSet.next())
                    userPasswordVariable = resultSet.getString("password_bk");

                if (userPasswordVariable.equals(password.getText())) {
                    root = FXMLLoader.load(MainApplication.class.getResource("view/BloodBank-view.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setTitle("Blood Bank Dashboard");
                    stage.setScene(scene);
                    stage.show();
                }

            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }

        }else if(userType.getValue().equals("Admin")){
            if(userId.getText().equals("1") && password.getText().equals("a")){
                root = FXMLLoader.load(MainApplication.class.getResource("view/Admin-view.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Admin Dashboard");
                stage.setScene(scene);
                stage.show();
            }

        }

        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userType.getItems().addAll(userTypes);
        userType.setValue("User Type");
        Connect();
    }
}