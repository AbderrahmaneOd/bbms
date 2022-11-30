package com.example.bbms.controller;

import com.example.bbms.MainApplication;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HospitalController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Pane variables
    @FXML
    private Pane stockManagementPane;
    @FXML
    private Pane searchDonorsPane;
    @FXML
    private Pane BloodRequestPane;

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

    // FXML Injection for Donor Functionality
    @FXML
    private TextField txtAddressD;
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

    // Hospital User ID
    @FXML
    private String hostpitalUserId;

    public void getHospitalUserId(String userId) {
        hostpitalUserId = userId;
    }

    // Function for Menu
    @FXML
    void btnBloodRequest(ActionEvent event) {
        BloodRequestPane.toFront();
        tableHospitalRequest();
    }

    @FXML
    void btnSearchDonors(ActionEvent event) {
        searchDonorsPane.toFront();
    }

    @FXML
    void btnStockManagement(ActionEvent event) {
        stockManagementPane.toFront();
        tableStock();
    }

    @FXML
    void btnLogOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(MainApplication.class.getResource("view/Login-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ;
            con = DriverManager.getConnection("jdbc:mysql://localhost/bbms", "root", "abderrahmane");
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connect();
    }

    // Function for Hospital Request functionality
    @FXML
    void addRequest(ActionEvent event) {

        String status, priority, date, quantity, bloodType;
        date = txtDateH.getText();
        bloodType = txtBloodTypeH.getText();
        quantity = txtQuantityH.getText();
        status = txtStatusH.getText();
        priority = txtPriorityH.getText();

        try {
            pst = con.prepareStatement("INSERT INTO blood_request (date_request, bloodtype_request, quantity_request, status, priority, id_hospital) values (?,?,?,?,?,?)");
            pst.setString(1, date);
            pst.setString(2, bloodType);
            pst.setString(3, quantity);
            pst.setString(4, status);
            pst.setString(5, priority);
            pst.setString(6, hostpitalUserId);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hospital Request Registration");


            alert.setHeaderText("Hospital Request Registration");
            alert.setContentText("Record Added!");

            alert.showAndWait();

            tableHospitalRequest();

            txtDateH.setText("");
            txtBloodTypeH.setText("");
            txtQuantityH.setText("");
            txtStatusH.setText("");
            txtPriorityH.setText("");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    public void tableHospitalRequest() {
        Connect();
        ObservableList<BloodRequest> requests = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("SELECT id_request, date_request, bloodtype_request, quantity_request, status, priority FROM blood_request WHERE id_hospital = ?");
            pst.setString(1, hostpitalUserId);
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    BloodRequest st = new BloodRequest();
                    st.setId(rs.getString("id_request"));
                    st.setDate(rs.getString("date_request"));
                    st.setBloodType(rs.getString("bloodtype_request"));
                    st.setQuantity(rs.getString("quantity_request"));
                    st.setStatus(rs.getString("status"));
                    st.setPriority(rs.getString("priority"));
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

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        tableHospitalRequest.setRowFactory(tv -> {
            TableRow<BloodRequest> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableHospitalRequest.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(tableHospitalRequest.getItems().get(myIndex).getId()));
                    txtDateH.setText(tableHospitalRequest.getItems().get(myIndex).getDate());
                    txtBloodTypeH.setText(tableHospitalRequest.getItems().get(myIndex).getBloodType());
                    txtQuantityH.setText(tableHospitalRequest.getItems().get(myIndex).getQuantity());
                    txtStatusH.setText(tableHospitalRequest.getItems().get(myIndex).getStatus());
                    txtPriorityH.setText(tableHospitalRequest.getItems().get(myIndex).getPriority());

                }
            });
            return myRow;
        });


    }

    @FXML
    void deleteRequest(ActionEvent event) {
        myIndex = tableHospitalRequest.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableHospitalRequest.getItems().get(myIndex).getId()));


        try {
            pst = con.prepareStatement("DELETE FROM blood_request WHERE id_request = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hospital Request Registration");


            alert.setHeaderText("Hospital Request Registration");
            alert.setContentText("Deleted!");

            alert.showAndWait();
            tableHospitalRequest();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @FXML
    void updateRequest(ActionEvent event) {

        String status, priority, date, quantity, bloodType;

        myIndex = tableHospitalRequest.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableHospitalRequest.getItems().get(myIndex).getId()));

        date = txtDateH.getText();
        bloodType = txtBloodTypeH.getText();
        quantity = txtQuantityH.getText();
        status = txtStatusH.getText();
        priority = txtPriorityH.getText();
        try {
            pst = con.prepareStatement("UPDATE blood_request SET date_request = ?, bloodtype_request = ?, quantity_request = ?, status = ?, priority = ? WHERE id_request = ? ");
            pst.setString(1, date);
            pst.setString(2, bloodType);
            pst.setString(3, quantity);
            pst.setString(4, status);
            pst.setString(5, priority);
            pst.setInt(6, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hospital Request Registration");


            alert.setHeaderText("Hospital Request Registration");
            alert.setContentText("Updated!");

            alert.showAndWait();
            tableHospitalRequest();
        } catch (SQLException ex) {
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

        try {
            pst = con.prepareStatement("INSERT INTO blood_stock_hospital (bloodtype_stock_h, quantity_stock_h, id_hospital) values (?,?,?)");
            pst.setString(1, bloodType);
            pst.setString(2, quantity);
            pst.setString(3, hostpitalUserId);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stock Registration");


            alert.setHeaderText("Stock Registration");
            alert.setContentText("Record Added!");

            alert.showAndWait();

            tableStock();

            txtQuantityStock.setText("");
            txtBloodTypeStock.setText("");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    public void tableStock() {
        Connect();
        ObservableList<BloodStock> stocks = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("SELECT id_stock_h, bloodtype_stock_h, quantity_stock_h FROM blood_stock_hospital WHERE id_hospital = ?");
            pst.setString(1, hostpitalUserId);
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    BloodStock st = new BloodStock();
                    st.setId(rs.getString("id_stock_h"));
                    st.setBloodType(rs.getString("bloodtype_stock_h"));
                    st.setQuantity(rs.getString("quantity_stock_h"));
                    stocks.add(st);
                }
            }
            tableStock.setItems(stocks);
            IDColmnStock.setCellValueFactory(f -> f.getValue().idProperty());
            QuantityColmnStock.setCellValueFactory(f -> f.getValue().quantityProperty());
            BloodTypeColmnStock.setCellValueFactory(f -> f.getValue().bloodTypeProperty());


        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        tableStock.setRowFactory(tv -> {
            TableRow<BloodStock> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableStock.getSelectionModel().getSelectedIndex();

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


        try {
            pst = con.prepareStatement("DELETE FROM blood_stock_hospital WHERE id_stock_h = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Blood Bank Stock Registration");


            alert.setHeaderText("Blood Bank Stock Registration");
            alert.setContentText("Deleted!");

            alert.showAndWait();
            tableStock();
        } catch (SQLException ex) {
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
        try {
            pst = con.prepareStatement("UPDATE blood_stock_hospital set bloodtype_stock_h = ?, quantity_stock_h = ? where id_stock_h = ? ");
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
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }



    // Function for Donors functionality
    public void tableDonor()
    {
        Connect();
        ObservableList<Donor> donors = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("SELECT id_donor ,full_name, address_donor, phone_donor, email_donor, bloodtype_donor FROM donor WHERE address_donor LIKE ? AND bloodtype_donor LIKE ?");
            pst.setString(1, "%" + txtAddressD.getText() + "%");
            pst.setString(2, "%" + txtBloodTypeD.getText() + "%");

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
                    txtAddressD.setText(tableDonor.getItems().get(myIndex).getAddress());
                    txtBloodTypeD.setText(tableDonor.getItems().get(myIndex).getBloodType());

                }
            });
            return myRow;
        });


    }

}