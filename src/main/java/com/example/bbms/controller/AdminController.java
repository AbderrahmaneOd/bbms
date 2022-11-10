package com.example.bbms.controller;


import com.example.bbms.model.BloodBank;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    
    
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TableView<BloodBank> table;

    @FXML
    private TableColumn<BloodBank, String> IDColmn;

    @FXML
    private TableColumn<BloodBank, String> NameColmn;

    @FXML
    private TableColumn<BloodBank, String> AddressColmn;

    @FXML
    private TableColumn<BloodBank, String> EmailColmn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    void Add(ActionEvent event) {

        String stname,address,email;
        stname = txtName.getText();
        address = txtAddress.getText();
        email = txtEmail.getText();
        try
        {
            pst = con.prepareStatement("insert into registation(name,address,email)values(?,?,?)");
            pst.setString(1, stname);
            pst.setString(2, address);
            pst.setString(3, email);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BloodBank Registation");


            alert.setHeaderText("BloodBank Registation");
            alert.setContentText("Record Addedddd!");

            alert.showAndWait();

            table();

            txtName.setText("");
            txtAddress.setText("");
            txtEmail.setText("");
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
        ObservableList<BloodBank> students = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select id,name,address,email from registation");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    BloodBank st = new BloodBank();
                    st.setId(rs.getString("id"));
                    st.setName(rs.getString("name"));
                    st.setAddress(rs.getString("address"));
                    st.setEmail(rs.getString("email"));
                    students.add(st);
                }
            }
            table.setItems(students);
            IDColmn.setCellValueFactory(f -> f.getValue().idProperty());
            NameColmn.setCellValueFactory(f -> f.getValue().nameProperty());
            AddressColmn.setCellValueFactory(f -> f.getValue().addressProperty());
            EmailColmn.setCellValueFactory(f -> f.getValue().emailProperty());



        }

        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        table.setRowFactory( tv -> {
            TableRow<BloodBank> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  table.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtName.setText(table.getItems().get(myIndex).getName());
                    txtAddress.setText(table.getItems().get(myIndex).getAddress());
                    txtEmail.setText(table.getItems().get(myIndex).getEmail());



                }
            });
            return myRow;
        });


    }

    @FXML
    void Delete(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));


        try
        {
            pst = con.prepareStatement("delete from registation where id = ? ");
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
    void Update(ActionEvent event) {

        String stname,address,email;

        myIndex = table.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        stname = txtName.getText();
        address = txtAddress.getText();
        email = txtEmail.getText();
        try
        {
            pst = con.prepareStatement("update registation set name = ?,address = ? ,email = ? where id = ? ");
            pst.setString(1, stname);
            pst.setString(2, address);
            pst.setString(3, email);
            pst.setInt(4, id);
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
            con = DriverManager.getConnection("jdbc:mysql://localhost/studcruds","root","abderrahmane");
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        table();
    }

}