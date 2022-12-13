package com.example.bbms.controller;

import com.example.bbms.MainApplication;
import com.example.bbms.model.BloodBank;
import com.example.bbms.model.BloodRequest;
import com.example.bbms.model.BloodStock;
import com.example.bbms.model.Donor;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class BloodBankController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Pane variables
    @FXML
    private Pane stockManagementPane;

    @FXML
    private Pane addDonorsPane;

    @FXML
    private Pane hospitalRequestPane;


    // FXML Injection for Donor Functionality
    @FXML
    private TextField txtFullNameD;

    @FXML
    private TextField txtAddressD;

    @FXML
    private TextField txtPhoneD;
    @FXML
    private TextField txtEmailD;
    @FXML
    private TextField txtBloodTypeD;

    @FXML
    private TableView<Donor> tableDonor;

    @FXML
    private TableColumn<Donor, String> IDColmnD;

    @FXML
    private TableColumn<Donor, String> FullNameColmnD;

    @FXML
    private TableColumn<Donor, String> AddressColmnD;

    @FXML
    private TableColumn<Donor, String> PhoneColmnD;
    @FXML
    private TableColumn<Donor, String> EmailColmnD;
    @FXML
    private TableColumn<Donor, String> BloodTypeColmnD;

    // FXML Injection for Stock Management Functionality
    @FXML
    private TextField txtQuantityStock;

    @FXML
    private TextField txtBloodTypeStock;

    @FXML
    private TableView<BloodStock> tableStock;

    @FXML
    private TableColumn<BloodStock, String> IDColmnStock;

    @FXML
    private TableColumn<BloodStock, String> QuantityColmnStock;

    @FXML
    private TableColumn<BloodStock, String> BloodTypeColmnStock;

    // FXML Injection for Hospital Request Functionality
    @FXML
    private TextField txtQuantityH;
    @FXML
    private TextField txtDateH;
    @FXML
    private TextField txtStatusH;
    @FXML
    private TextField txtPriorityH;
    @FXML
    private TextField txtBloodTypeH;
    
    @FXML
    private TextField txtHospitalIdH;

    @FXML
    private TableView<BloodRequest> tableHospitalRequest;

    @FXML
    private TableColumn<BloodRequest, String> IDColmnH;

    @FXML
    private TableColumn<BloodRequest, String> DateColmnH;

    @FXML
    private TableColumn<BloodRequest, String> QuantityColmnH;

    @FXML
    private TableColumn<BloodRequest, String> StatusColmnH;
    @FXML
    private TableColumn<BloodRequest, String> PriorityColmnH;
    @FXML
    private TableColumn<BloodRequest, String> BloodTypeColmnH;
    @FXML
    private TableColumn<BloodRequest, String> HospitalIdColmnH;

    // Blood Bank User ID
    @FXML
    private String bloodBankUserId;
    public void getBloodBankUserId(String userId){
        bloodBankUserId = userId;
    }

    // Function for Menu
    @FXML
    void btnAddDonors(ActionEvent event) {
        addDonorsPane.toFront();
    }

    @FXML
    void btnHospitalRequest(ActionEvent event) {
        hospitalRequestPane.toFront();
    }

    @FXML
    void btnStockManagement(ActionEvent event) {
        stockManagementPane.toFront();
        tableStock();
    }

    @FXML
    void btnLogOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(MainApplication.class.getResource("view/Login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    // Data Base connection
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connect();
        tableDonor();
        tableHospitalRequest();


    }

    // Function for Donors functionality
    @FXML
    void addDonor(ActionEvent event) {

        String fullName,address,phone,email, bloodType;
        fullName = txtFullNameD.getText();
        address = txtAddressD.getText();
        phone = txtPhoneD.getText();
        email = txtEmailD.getText();
        bloodType = txtBloodTypeD.getText();

        try
        {
            pst = con.prepareStatement("insert into donor (full_name, address_donor, phone_donor, email_donor, bloodtype_donor) values (?,?,?,?,?)");
            pst.setString(1, fullName);
            pst.setString(2, address);
            pst.setString(3, phone);
            pst.setString(4, email);
            pst.setString(5, bloodType);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Donor Registration");


            alert.setHeaderText("Donor Registration");
            alert.setContentText("Record Added!");

            alert.showAndWait();

            tableDonor();

            txtFullNameD.setText("");
            txtAddressD.setText("");
            txtPhoneD.setText("");
            txtEmailD.setText("");
            txtBloodTypeD.setText("");
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    public void tableDonor()
    {
        Connect();
        ObservableList<Donor> donors = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select id_donor ,full_name, address_donor, phone_donor, email_donor, bloodtype_donor from donor");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Donor st = new Donor();
                    st.setId(rs.getString("id_donor"));
                    st.setFullName(rs.getString("full_name"));
                    st.setAddress(rs.getString("address_donor"));
                    st.setPhone(rs.getString("phone_donor"));
                    st.setEmail(rs.getString("email_donor"));
                    st.setBloodType(rs.getString("bloodtype_donor"));
                    donors.add(st);
                }
            }
            tableDonor.setItems(donors);
            IDColmnD.setCellValueFactory(f -> f.getValue().idProperty());
            FullNameColmnD.setCellValueFactory(f -> f.getValue().fullNameProperty());
            AddressColmnD.setCellValueFactory(f -> f.getValue().addressProperty());
            PhoneColmnD.setCellValueFactory(f -> f.getValue().phoneProperty());
            EmailColmnD.setCellValueFactory(f -> f.getValue().emailProperty());
            BloodTypeColmnD.setCellValueFactory(f -> f.getValue().bloodTypeProperty());


        }

        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        tableDonor.setRowFactory( tv -> {
            TableRow<Donor> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableDonor.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(tableDonor.getItems().get(myIndex).getId()));
                    txtFullNameD.setText(tableDonor.getItems().get(myIndex).getFullName());
                    txtAddressD.setText(tableDonor.getItems().get(myIndex).getAddress());
                    txtPhoneD.setText(tableDonor.getItems().get(myIndex).getPhone());
                    txtEmailD.setText(tableDonor.getItems().get(myIndex).getEmail());
                    txtBloodTypeD.setText(tableDonor.getItems().get(myIndex).getBloodType());

                }
            });
            return myRow;
        });


    }

    @FXML
    void deleteDonor(ActionEvent event) {
        myIndex = tableDonor.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableDonor.getItems().get(myIndex).getId()));


        try
        {
            pst = con.prepareStatement("delete from donor where id_donor = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Donor Registration");


            alert.setHeaderText("Donor Registration");
            alert.setContentText("Deleted!");

            alert.showAndWait();
            tableDonor();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @FXML
    void updateDonor(ActionEvent event) {

        String fullName,address,phone,email, bloodType;

        myIndex = tableDonor.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableDonor.getItems().get(myIndex).getId()));

        fullName = txtFullNameD.getText();
        address = txtAddressD.getText();
        phone = txtPhoneD.getText();
        email = txtEmailD.getText();
        bloodType = txtBloodTypeD.getText();
        try
        {
            pst = con.prepareStatement("update donor set full_name = ?,address_donor = ? ,phone_donor = ?, email_donor = ?, bloodtype_donor = ? where id_donor = ? ");
            pst.setString(1, fullName);
            pst.setString(2, address);
            pst.setString(3, phone);
            pst.setString(4, email);
            pst.setString(5, bloodType);
            pst.setInt(6, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Donor Registration");


            alert.setHeaderText("Donor Registration");
            alert.setContentText("Updated!");

            alert.showAndWait();
            tableDonor();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    // Function for Stock Management functionality
    @FXML
    void addStock(ActionEvent event) {

        String quantity, bloodType;
        quantity = txtQuantityStock.getText();
        bloodType = txtBloodTypeStock.getText();

        try
        {
            pst = con.prepareStatement("INSERT INTO blood_stock_bloodbank (bloodtype_stock_bk, quantity_stock_bk, id_bk) values (?,?,?)");
            pst.setString(1, bloodType);
            pst.setString(2, quantity);
            pst.setString(3, bloodBankUserId);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stock Registration");


            alert.setHeaderText("Stock Registration");
            alert.setContentText("Record Added!");

            alert.showAndWait();

            tableStock();

            txtQuantityStock.setText("");
            txtBloodTypeStock.setText("");

        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    public void tableStock()
    {
        Connect();
        ObservableList<BloodStock> stocks = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("SELECT id_stock_bk, bloodtype_stock_bk, quantity_stock_bk FROM blood_stock_bloodbank WHERE id_bk = ?");
            pst.setString(1, bloodBankUserId);
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    BloodStock st = new BloodStock();
                    st.setId(rs.getString("id_stock_bk"));
                    st.setBloodType(rs.getString("bloodtype_stock_bk"));
                    st.setQuantity(rs.getString("quantity_stock_bk"));
                    stocks.add(st);
                }
            }
            tableStock.setItems(stocks);
            IDColmnStock.setCellValueFactory(f -> f.getValue().idProperty());
            QuantityColmnStock.setCellValueFactory(f -> f.getValue().quantityProperty());
            BloodTypeColmnStock.setCellValueFactory(f -> f.getValue().bloodTypeProperty());


        }

        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        tableStock.setRowFactory( tv -> {
            TableRow<BloodStock> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableStock.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(tableStock.getItems().get(myIndex).getId()));
                    txtQuantityStock.setText(tableStock.getItems().get(myIndex).getQuantity());
                    txtBloodTypeStock.setText(tableStock.getItems().get(myIndex).getBloodType());

                }
            });
            return myRow;
        });


    }

    @FXML
    void deleteStock(ActionEvent event) {
        myIndex = tableStock.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableStock.getItems().get(myIndex).getId()));


        try
        {
            pst = con.prepareStatement("DELETE FROM blood_stock_bloodbank WHERE id_stock_bk = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Blood Bank Stock Registration");


            alert.setHeaderText("Blood Bank Stock Registration");
            alert.setContentText("Deleted!");

            alert.showAndWait();
            tableStock();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @FXML
    void updateStock(ActionEvent event) {

        String quantity, bloodType;

        myIndex = tableStock.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableStock.getItems().get(myIndex).getId()));

        quantity = txtQuantityStock.getText();
        bloodType = txtBloodTypeStock.getText();
        try
        {
            pst = con.prepareStatement("UPDATE blood_stock_bloodbank set bloodtype_stock_bk = ?, quantity_stock_bk = ? where id_stock_bk = ? ");
            pst.setString(1, bloodType);
            pst.setString(2, quantity);
            pst.setInt(3, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stock Management Registration");


            alert.setHeaderText("Stock Management Registration");
            alert.setContentText("Updated!");

            alert.showAndWait();
            tableStock();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    // Function for Hospital Request functionality
    public void tableHospitalRequest()
    {
        Connect();
        ObservableList<BloodRequest> requests = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("SELECT id_request, date_request, bloodtype_request, quantity_request, status, priority, id_hospital, h.name_h FROM blood_request r, hospital h where r.id_hospital = h.id_h;");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    BloodRequest st = new BloodRequest();
                    st.setId(rs.getString("id_request"));
                    st.setDate(rs.getString("date_request"));
                    st.setBloodType(rs.getString("bloodtype_request"));
                    st.setQuantity(rs.getString("quantity_request"));
                    st.setStatus(rs.getString("status"));
                    st.setPriority(rs.getString("priority"));
                    st.setHospitalId(rs.getString("h.name_h"));
                    requests.add(st);
                }
            }
            tableHospitalRequest.setItems(requests);
            IDColmnH.setCellValueFactory(f -> f.getValue().idProperty());
            DateColmnH.setCellValueFactory(f -> f.getValue().dateProperty());
            BloodTypeColmnH.setCellValueFactory(f -> f.getValue().bloodTypeProperty());
            QuantityColmnH.setCellValueFactory(f -> f.getValue().quantityProperty());
            StatusColmnH.setCellValueFactory(f -> f.getValue().statusProperty());
            PriorityColmnH.setCellValueFactory(f -> f.getValue().priorityProperty());
            HospitalIdColmnH.setCellValueFactory(f -> f.getValue().hospitalIdProperty());


        }

        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        tableHospitalRequest.setRowFactory( tv -> {
            TableRow<BloodRequest> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableHospitalRequest.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(tableHospitalRequest.getItems().get(myIndex).getId()));
                    txtDateH.setText(tableHospitalRequest.getItems().get(myIndex).getDate());
                    txtBloodTypeH.setText(tableHospitalRequest.getItems().get(myIndex).getBloodType());
                    txtQuantityH.setText(tableHospitalRequest.getItems().get(myIndex).getQuantity());
                    txtStatusH.setText(tableHospitalRequest.getItems().get(myIndex).getStatus());
                    txtPriorityH.setText(tableHospitalRequest.getItems().get(myIndex).getPriority());
                    txtHospitalIdH.setText(tableHospitalRequest.getItems().get(myIndex).getHospitalId());

                }
            });
            return myRow;
        });


    }

    @FXML
    void updateRequest(ActionEvent event) {

        String status,priority,date,quantity, bloodType;

        myIndex = tableHospitalRequest.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableHospitalRequest.getItems().get(myIndex).getId()));

        date = txtDateH.getText();
        bloodType = txtBloodTypeH.getText();
        quantity = txtQuantityH.getText();
        status = txtStatusH.getText();
        priority = txtPriorityH.getText();
        try
        {
            pst = con.prepareStatement("UPDATE blood_request SET date_request = ?, bloodtype_request = ?, quantity_request = ?, status = ?, priority = ? WHERE id_request = ? ");
            pst.setString(1, date);
            pst.setString(2, bloodType);
            pst.setString(3, quantity);
            pst.setString(4, status);
            pst.setString(5, priority);
            pst.setInt(6, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Donor Registration");


            alert.setHeaderText("Donor Registration");
            alert.setContentText("Updated!");

            alert.showAndWait();
            tableHospitalRequest();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}