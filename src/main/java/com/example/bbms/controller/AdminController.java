package com.example.bbms.controller;


import com.example.bbms.MainApplication;
import com.example.bbms.model.BloodBank;
import com.example.bbms.model.Hospital;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminController implements Initializable{

    // Variable for Scene Switching
    private Stage stage;
    private Scene scene;
    private Parent root;

    // FXML Injection for Blood Bank Functionality
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;

    @FXML
    private TableView<BloodBank> tableBloodBank;

    @FXML
    private TableColumn<BloodBank, String> IDColmn;

    @FXML
    private TableColumn<BloodBank, String> NameColmn;

    @FXML
    private TableColumn<BloodBank, String> AddressColmn;

    @FXML
    private TableColumn<BloodBank, String> EmailColmn;
    @FXML
    private TableColumn<BloodBank, String> PasswordColmn;

    // FXML Injection for Hospital Functionality

    @FXML
    private TableView<Hospital> tableHospital;

    @FXML
    private TableColumn<Hospital, String> IDColmnH;

    @FXML
    private TableColumn<Hospital, String> NameColmnH;

    @FXML
    private TableColumn<Hospital, String> AddressColmnH;

    @FXML
    private TableColumn<Hospital, String> EmailColmnH;
    @FXML
    private TableColumn<Hospital, String> PasswordColmnH;
    @FXML
    private TextField txtNameH;

    @FXML
    private TextField txtAddressH;

    @FXML
    private TextField txtEmailH;
    @FXML
    private TextField txtPasswordH;

    // Pane declaration
    @FXML
    private Pane bloodBanksPane;

    @FXML
    private Pane campsPane;

    @FXML
    private Pane donorsPane;

    @FXML
    private Pane hospitalsPane;

    @FXML
    private Pane StatisticsPane;

    @FXML
    void btnBloodBanks(ActionEvent event) {
        bloodBanksPane.toFront();
    }

    @FXML
    void btnCamps(ActionEvent event) {
        campsPane.toFront();
    }

    @FXML
    void btnDonors(ActionEvent event) {
        donorsPane.toFront();
    }

    @FXML
    void btnHospitals(ActionEvent event) {
        hospitalsPane.toFront();
    }

    @FXML
    void btnStatistics(ActionEvent event) {
        StatisticsPane.toFront();
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


    // Function for Blood Bank functionality
    @FXML
    void addBloodBank(ActionEvent event) {

        String stname,address,email, password;
        stname = txtName.getText();
        address = txtAddress.getText();
        email = txtEmail.getText();
        password = txtPassword.getText();
        try
        {
            pst = con.prepareStatement("insert into blood_bank (name_bk,address_bk,email_bk, password_bk)values(?,?,?,?)");
            pst.setString(1, stname);
            pst.setString(2, address);
            pst.setString(3, email);
            pst.setString(4, password);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BloodBank Insertion");


            alert.setHeaderText("BloodBank Registation");
            alert.setContentText("Record Added!");

            alert.showAndWait();

            table();

            txtName.setText("");
            txtAddress.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            txtName.requestFocus();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    public void table()
    {
        Connect();
        ObservableList<BloodBank> bloodBanks = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select id_bk, name_bk,address_bk,email_bk, password_bk from blood_bank");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    BloodBank st = new BloodBank();
                    st.setId(rs.getString("id_bk"));
                    st.setName(rs.getString("name_bk"));
                    st.setAddress(rs.getString("address_bk"));
                    st.setEmail(rs.getString("email_bk"));
                    st.setPassword(rs.getString("password_bk"));
                    bloodBanks.add(st);
                }
            }
            tableBloodBank.setItems(bloodBanks);
            IDColmn.setCellValueFactory(f -> f.getValue().idProperty());
            NameColmn.setCellValueFactory(f -> f.getValue().nameProperty());
            AddressColmn.setCellValueFactory(f -> f.getValue().addressProperty());
            EmailColmn.setCellValueFactory(f -> f.getValue().emailProperty());
            PasswordColmn.setCellValueFactory(f -> f.getValue().passwordProperty());



        }

        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        tableBloodBank.setRowFactory( tv -> {
            TableRow<BloodBank> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableBloodBank.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(tableBloodBank.getItems().get(myIndex).getId()));
                    txtName.setText(tableBloodBank.getItems().get(myIndex).getName());
                    txtAddress.setText(tableBloodBank.getItems().get(myIndex).getAddress());
                    txtEmail.setText(tableBloodBank.getItems().get(myIndex).getEmail());
                    txtPassword.setText(tableBloodBank.getItems().get(myIndex).getPassword());



                }
            });
            return myRow;
        });


    }

    @FXML
    void DeleteBloodBank(ActionEvent event) {
        myIndex = tableBloodBank.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableBloodBank.getItems().get(myIndex).getId()));


        try
        {
            pst = con.prepareStatement("delete from blood_bank where id_bk = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BloodBank Registationn");


            alert.setHeaderText("BloodBank Registation");
            alert.setContentText("Deletedd!");

            alert.showAndWait();
            table();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @FXML
    void updateBloodBank(ActionEvent event) {

        String stname,address,email, password;

        myIndex = tableBloodBank.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableBloodBank.getItems().get(myIndex).getId()));

        stname = txtName.getText();
        address = txtAddress.getText();
        email = txtEmail.getText();
        password = txtPassword.getText();
        try
        {
            pst = con.prepareStatement("update blood_bank set name_bk = ?,address_bk = ? ,email_bk = ?, password_bk = ? where id_bk = ? ");
            pst.setString(1, stname);
            pst.setString(2, address);
            pst.setString(3, email);
            pst.setString(4, password);
            pst.setInt(5, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BloodBank Registationn");


            alert.setHeaderText("BloodBank Registation");
            alert.setContentText("Updateddd!");

            alert.showAndWait();
            table();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;



    public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");;
            con = DriverManager.getConnection("jdbc:mysql://localhost/bbms","root","abderrahmane");
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        table();
        tableH();
    }

    // Function for Hospital functionality
    @FXML
    void addH(ActionEvent event) {

        String stname,address,email, password;
        stname = txtNameH.getText();
        address = txtAddressH.getText();
        email = txtEmailH.getText();
        password = txtPasswordH.getText();
        try
        {
            pst = con.prepareStatement("insert into hospital (name_h,address_h,email_h, password_h)values(?,?,?,?)");
            pst.setString(1, stname);
            pst.setString(2, address);
            pst.setString(3, email);
            pst.setString(4, password);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hospital Registation");


            alert.setHeaderText("Hospital Registation");
            alert.setContentText("Record Added!");

            alert.showAndWait();

            tableH();

            txtNameH.setText("");
            txtAddressH.setText("");
            txtEmailH.setText("");
            txtPasswordH.setText("");
            txtNameH.requestFocus();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    public void tableH()
    {
        Connect();
        ObservableList<Hospital> hospitals = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select id_h, name_h,address_h,email_h, password_h from hospital");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Hospital st = new Hospital();
                    st.setId(rs.getString("id_h"));
                    st.setName(rs.getString("name_h"));
                    st.setAddress(rs.getString("address_h"));
                    st.setEmail(rs.getString("email_h"));
                    st.setPassword(rs.getString("password_h"));
                    hospitals.add(st);
                }
            }
            tableHospital.setItems(hospitals);
            IDColmnH.setCellValueFactory(f -> f.getValue().idProperty());
            NameColmnH.setCellValueFactory(f -> f.getValue().nameProperty());
            AddressColmnH.setCellValueFactory(f -> f.getValue().addressProperty());
            EmailColmnH.setCellValueFactory(f -> f.getValue().emailProperty());
            PasswordColmnH.setCellValueFactory(f -> f.getValue().passwordProperty());



        }

        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        tableHospital.setRowFactory( tv -> {
            TableRow<Hospital> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableHospital.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(tableHospital.getItems().get(myIndex).getId()));
                    txtNameH.setText(tableHospital.getItems().get(myIndex).getName());
                    txtAddressH.setText(tableHospital.getItems().get(myIndex).getAddress());
                    txtEmailH.setText(tableHospital.getItems().get(myIndex).getEmail());
                    txtPasswordH.setText(tableHospital.getItems().get(myIndex).getPassword());



                }
            });
            return myRow;
        });


    }

    @FXML
    void deleteH(ActionEvent event) {
        myIndex = tableHospital.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableHospital.getItems().get(myIndex).getId()));


        try
        {
            pst = con.prepareStatement("delete from hospital where id_h = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hospital Registration");


            alert.setHeaderText("Hospital Registration");
            alert.setContentText("Deleted!");

            alert.showAndWait();
            tableH();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @FXML
    void updateH(ActionEvent event) {

        String stname,address,email, password;

        myIndex = tableHospital.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableHospital.getItems().get(myIndex).getId()));

        stname = txtNameH.getText();
        address = txtAddressH.getText();
        email = txtEmailH.getText();
        password = txtPasswordH.getText();
        try
        {
            pst = con.prepareStatement("update hospital set name_h = ?,address_h = ? ,email_h = ?, password_h = ? where id_h = ? ");
            pst.setString(1, stname);
            pst.setString(2, address);
            pst.setString(3, email);
            pst.setString(4, password);
            pst.setInt(5, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hospital Registration");


            alert.setHeaderText("Hospital Registration");
            alert.setContentText("Updated!");

            alert.showAndWait();
            tableH();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}