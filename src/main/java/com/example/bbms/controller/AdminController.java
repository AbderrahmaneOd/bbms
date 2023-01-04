package com.example.bbms.controller;


import com.example.bbms.MainApplication;
import com.example.bbms.model.BloodBank;
import com.example.bbms.model.Camp;
import com.example.bbms.model.Donor;
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
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AdminController implements Initializable{

    // Variable for Scene Switching
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Variable for statistics
    @FXML
    private PieChart pieChart;
    @FXML
    private Label labelTotalCamps;

    @FXML
    private Label labelTotalDonors;

    @FXML
    private Label labelTotalRequests;
    @FXML
    private Label labelTotalDeliveredRequests;

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

    // FXML Injection for Camp Functionality
    @FXML
    private TextField txtNameC;

    @FXML
    private TextField txtAddressC;

    @FXML
    private TextField txtPhoneC;
    @FXML
    private TextField txtOrganizedByC;

    @FXML
    private TextField txtStartDateC;
    @FXML
    private TextField txtEndDateC;

    @FXML
    private TableView<Camp> tableCamp;

    @FXML
    private TableColumn<Camp, String> IDColmnC;

    @FXML
    private TableColumn<Camp, String> NameColmnC;

    @FXML
    private TableColumn<Camp, String> AddressColmnC;

    @FXML
    private TableColumn<Camp, String> PhoneColmnC;
    @FXML
    private TableColumn<Camp, String> OrganizedByColmnC;
    @FXML
    private TableColumn<Camp, String> StartDateColmnC;
    @FXML
    private TableColumn<Camp, String> EndDateColmnC;

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
        statistics();
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


            alert.setHeaderText("BloodBank Registration");
            alert.setContentText("Record Added");

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
            alert.setTitle("BloodBank Registration");


            alert.setHeaderText("BloodBank Registration");
            alert.setContentText("Deleted!");

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
            alert.setTitle("BloodBank Registration");


            alert.setHeaderText("BloodBank Registration");
            alert.setContentText("Updated!");

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
        tableC();
        tableD();
        statistics();
    }

    // Statistics
    void statistics(){
        Connect();
        int aPQuantity = 0, aMQuantity = 0, bPQuantity = 0, bMQuantity = 0,
                abPQuantity = 0, abMQuantity= 0, oPQuantity= 0, oMQuantity= 0,
                totalDonors = 0, totalCamps = 0, totalRequests = 0, totalDeliveredRequests = 0,
                lowA = 0, lowB = 0, lowAB = 0, lowO = 0,
                mediumA = 0, mediumAB = 0, mediumB = 0, mediumO = 0,
                highA = 0, highAB = 0, highB = 0, highO = 0;
        try{
            Statement stmt = con.createStatement();

            // A+ and A-
            ResultSet resultSet = stmt.executeQuery("SELECT SUM(subTotal) from (select sum(quantity_stock_bk) as subTotal from blood_stock_bloodbank WHERE bloodtype_stock_bk = \'A+\' union all select sum(quantity_stock_h) from blood_stock_hospital WHERE bloodtype_stock_h = \'A+\') as x;");
            if(resultSet.next())
                aPQuantity = resultSet.getInt("SUM(subTotal)");

            resultSet = stmt.executeQuery("SELECT SUM(subTotal) from (select sum(quantity_stock_bk) as subTotal from blood_stock_bloodbank WHERE bloodtype_stock_bk = \'A-\' union all select sum(quantity_stock_h) from blood_stock_hospital WHERE bloodtype_stock_h = \'A-\') as x;");
            if(resultSet.next())
                aMQuantity = resultSet.getInt("SUM(subTotal)");


             // B+ and B-
            resultSet = stmt.executeQuery("SELECT SUM(subTotal) from (select sum(quantity_stock_bk) as subTotal from blood_stock_bloodbank WHERE bloodtype_stock_bk = \'B+\' union all select sum(quantity_stock_h) from blood_stock_hospital WHERE bloodtype_stock_h = \'B+\') as x;");
            if(resultSet.next())
                bPQuantity = resultSet.getInt("SUM(subTotal)");

            resultSet = stmt.executeQuery("SELECT SUM(subTotal) from (select sum(quantity_stock_bk) as subTotal from blood_stock_bloodbank WHERE bloodtype_stock_bk = \'B-\' union all select sum(quantity_stock_h) from blood_stock_hospital WHERE bloodtype_stock_h = \'B-\') as x;");
            if(resultSet.next())
                bMQuantity = resultSet.getInt("SUM(subTotal)");

            // AB+ and AB-
            resultSet = stmt.executeQuery("SELECT SUM(subTotal) from (select sum(quantity_stock_bk) as subTotal from blood_stock_bloodbank WHERE bloodtype_stock_bk = \'AB+\' union all select sum(quantity_stock_h) from blood_stock_hospital WHERE bloodtype_stock_h = \'AB+\') as x;");
            if(resultSet.next())
                abPQuantity = resultSet.getInt("SUM(subTotal)");


            resultSet = stmt.executeQuery("SELECT SUM(subTotal) from (select sum(quantity_stock_bk) as subTotal from blood_stock_bloodbank WHERE bloodtype_stock_bk = \'AB-\' union all select sum(quantity_stock_h) from blood_stock_hospital WHERE bloodtype_stock_h = \'AB-\') as x;");
            if(resultSet.next())
                abMQuantity = resultSet.getInt("SUM(subTotal)");

            // O+ and O-
            resultSet = stmt.executeQuery("SELECT SUM(subTotal) from (select sum(quantity_stock_bk) as subTotal from blood_stock_bloodbank WHERE bloodtype_stock_bk = \'O+\' union all select sum(quantity_stock_h) from blood_stock_hospital WHERE bloodtype_stock_h = \'O+\') as x;");
            if(resultSet.next())
                oPQuantity = resultSet.getInt("SUM(subTotal)");


            resultSet = stmt.executeQuery("SELECT SUM(subTotal) from (select sum(quantity_stock_bk) as subTotal from blood_stock_bloodbank WHERE bloodtype_stock_bk = \'O-\' union all select sum(quantity_stock_h) from blood_stock_hospital WHERE bloodtype_stock_h = \'O-\') as x; ");
            if(resultSet.next())
                oMQuantity = resultSet.getInt("SUM(subTotal)");

            // Total Donors
            resultSet = stmt.executeQuery("SELECT COUNT(id_donor) FROM donor");
            if(resultSet.next())
                totalDonors = resultSet.getInt("COUNT(id_donor)");

            // Total Camps
            resultSet = stmt.executeQuery("SELECT COUNT(id_camp) FROM camp");
            if(resultSet.next())
                totalCamps = resultSet.getInt("COUNT(id_camp)");

            // Total Requests
            resultSet = stmt.executeQuery("SELECT COUNT(id_request) FROM blood_request");
            if(resultSet.next())
                totalRequests = resultSet.getInt("COUNT(id_request)");

            // Total Delivered Requests
            resultSet = stmt.executeQuery("SELECT COUNT(id_request) FROM blood_request WHERE status = \"delivered\" ");
            if(resultSet.next())
                totalDeliveredRequests = resultSet.getInt("COUNT(id_request)");


            // Low, Medium and High for A
            resultSet = stmt.executeQuery("SELECT SUM(quantity_request) FROM blood_request WHERE bloodtype_request LIKE \"A_\" AND priority = \"low\" ");
            if(resultSet.next())
                lowA = resultSet.getInt("SUM(quantity_request)");
            resultSet = stmt.executeQuery("SELECT SUM(quantity_request) FROM blood_request WHERE bloodtype_request LIKE \"A_\" AND priority = \"medium\"");
            if(resultSet.next())
                mediumA = resultSet.getInt("SUM(quantity_request)");
            resultSet = stmt.executeQuery("SELECT SUM(quantity_request) FROM blood_request WHERE bloodtype_request LIKE \"A_\" AND priority = \"high\"");
            if(resultSet.next())
                highA = resultSet.getInt("SUM(quantity_request)");


            // Low, Medium and High for B
            resultSet = stmt.executeQuery("SELECT SUM(quantity_request) FROM blood_request WHERE bloodtype_request LIKE \"B_\" AND priority = \"low\"");
            if(resultSet.next())
                lowB = resultSet.getInt("SUM(quantity_request)");
            resultSet = stmt.executeQuery("SELECT SUM(quantity_request) FROM blood_request WHERE bloodtype_request LIKE \"B_\" AND priority = \"medium\"");
            if(resultSet.next())
                mediumB = resultSet.getInt("SUM(quantity_request)");
            resultSet = stmt.executeQuery("SELECT SUM(quantity_request) FROM blood_request WHERE bloodtype_request LIKE \"B_\" AND priority = \"high\"");
            if(resultSet.next())
                highB = resultSet.getInt("SUM(quantity_request)");

            // Low, Medium and High for AB
            resultSet = stmt.executeQuery("SELECT SUM(quantity_request) FROM blood_request WHERE bloodtype_request LIKE \"AB_\" AND priority = \"low\"");
            if(resultSet.next())
                lowAB = resultSet.getInt("SUM(quantity_request)");
            resultSet = stmt.executeQuery("SELECT SUM(quantity_request) FROM blood_request WHERE bloodtype_request LIKE \"AB_\" AND priority = \"medium\"");
            if(resultSet.next())
                mediumAB = resultSet.getInt("SUM(quantity_request)");
            resultSet = stmt.executeQuery("SELECT SUM(quantity_request) FROM blood_request WHERE bloodtype_request LIKE \"AB_\" AND priority = \"high\"");
            if(resultSet.next())
                highAB = resultSet.getInt("SUM(quantity_request)");

            // Low, Medium and High for O
            resultSet = stmt.executeQuery("SELECT SUM(quantity_request) FROM blood_request WHERE bloodtype_request LIKE \"O_\" AND priority = \"low\" ");
            if(resultSet.next())
                lowO = resultSet.getInt("SUM(quantity_request)");
            resultSet = stmt.executeQuery("SELECT SUM(quantity_request) FROM blood_request WHERE bloodtype_request LIKE \"O_\" AND priority = \"medium\"");
            if(resultSet.next())
                mediumO = resultSet.getInt("SUM(quantity_request)");
            resultSet = stmt.executeQuery("SELECT SUM(quantity_request) FROM blood_request WHERE bloodtype_request LIKE \"O_\" AND priority = \"high\"");
            if(resultSet.next())
                highO = resultSet.getInt("SUM(quantity_request)");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }


        labelTotalDonors.setText(String.valueOf(totalDonors));
        labelTotalCamps.setText(String.valueOf(totalCamps));
        labelTotalRequests.setText(String.valueOf(totalRequests));
        labelTotalDeliveredRequests.setText(String.valueOf(totalDeliveredRequests));

        //Preparing ObservbleList object
        ObservableList<PieChart.Data> bloodQuantity = FXCollections.observableArrayList(
                new PieChart.Data("A+", aPQuantity),
                new PieChart.Data("B+", abPQuantity),
                new PieChart.Data("AB+", bPQuantity),
                new PieChart.Data("O+", oPQuantity),

                new PieChart.Data("A-", aMQuantity),
                new PieChart.Data("B-", bMQuantity),
                new PieChart.Data("AB-", abMQuantity),
                new PieChart.Data("O-", oMQuantity) );

        pieChart.setData(bloodQuantity);
        pieChart.setTitle("Available Blood Packet by Group");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        //Defining the x axis
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "A", "B", "AB", "O")));
        xAxis.setLabel("Request Priority");

        //Defining the y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Blood packet");

        BarChart barChart = new BarChart(xAxis, yAxis);
        barChart.setTitle("Blood Request by Priority");
        barChart.setLayoutX(340);
        barChart.setLayoutY(155);
        barChart.setPrefHeight(343);
        barChart.setPrefWidth(360);

        //Prepare XYChart.Series objects by setting data
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("High");
        series1.getData().add(new XYChart.Data<>("A", highA));
        series1.getData().add(new XYChart.Data<>("B", highB));
        series1.getData().add(new XYChart.Data<>("AB", highAB));
        series1.getData().add(new XYChart.Data<>("O", highO));


        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Medium");
        series2.getData().add(new XYChart.Data<>("A", mediumA));
        series2.getData().add(new XYChart.Data<>("B", mediumB));
        series2.getData().add(new XYChart.Data<>("AB", mediumAB));
        series2.getData().add(new XYChart.Data<>("O", mediumO));


        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Low");
        series3.getData().add(new XYChart.Data<>("A", lowA));
        series3.getData().add(new XYChart.Data<>("B", lowB));
        series3.getData().add(new XYChart.Data<>("AB", lowAB));
        series3.getData().add(new XYChart.Data<>("O", lowO));



        //Setting the data to bar chart
        barChart.getData().addAll(series1, series2, series3);
        StatisticsPane.getChildren().add(barChart);


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

    // Function for Camp functionality
    @FXML
    void addC(ActionEvent event) {

        String stname,address,phone,startDate, endDate,organizedBy;
        stname = txtNameC.getText();
        address = txtAddressC.getText();
        phone = txtPhoneC.getText();
        startDate = txtStartDateC.getText();
        endDate = txtEndDateC.getText();
        organizedBy = txtOrganizedByC.getText();

        try
        {
            pst = con.prepareStatement("insert into camp (name_camp,address_camp,phone_camp, organized_by, start_date, end_date)values(?,?,?,?,?,?)");
            pst.setString(1, stname);
            pst.setString(2, address);
            pst.setString(3, phone);
            pst.setString(4, organizedBy);
            pst.setString(5, startDate);
            pst.setString(6, endDate);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Camp Registration");


            alert.setHeaderText("Camp Registration");
            alert.setContentText("Record Added!");

            alert.showAndWait();

            tableC();

            txtNameC.setText("");
            txtAddressC.setText("");
            txtPhoneC.setText("");
            txtOrganizedByC.setText("");
            txtStartDateC.setText("");
            txtEndDateC.setText("");
            txtOrganizedByC.requestFocus();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    public void tableC()
    {
        Connect();
        ObservableList<Camp> camps = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select id_camp, name_camp,address_camp,phone_camp, organized_by, start_date, end_date from camp");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Camp st = new Camp();
                    st.setId(rs.getString("id_camp"));
                    st.setName(rs.getString("name_camp"));
                    st.setAddress(rs.getString("address_camp"));
                    st.setPhone(rs.getString("phone_camp"));
                    st.setOrganizedBy(rs.getString("organized_by"));
                    st.setStartDate(rs.getString("start_date"));
                    st.setEndDate(rs.getString("end_date"));
                    camps.add(st);
                }
            }
            tableCamp.setItems(camps);
            IDColmnC.setCellValueFactory(f -> f.getValue().idProperty());
            NameColmnC.setCellValueFactory(f -> f.getValue().nameProperty());
            AddressColmnC.setCellValueFactory(f -> f.getValue().addressProperty());
            PhoneColmnC.setCellValueFactory(f -> f.getValue().phoneProperty());
            OrganizedByColmnC.setCellValueFactory(f -> f.getValue().organizedByProperty());
            StartDateColmnC.setCellValueFactory(f -> f.getValue().startDateProperty());
            EndDateColmnC.setCellValueFactory(f -> f.getValue().endDateProperty());



        }

        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        tableCamp.setRowFactory( tv -> {
            TableRow<Camp> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableCamp.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(tableCamp.getItems().get(myIndex).getId()));
                    txtNameC.setText(tableCamp.getItems().get(myIndex).getName());
                    txtAddressC.setText(tableCamp.getItems().get(myIndex).getAddress());
                    txtPhoneC.setText(tableCamp.getItems().get(myIndex).getphone());
                    txtOrganizedByC.setText(tableCamp.getItems().get(myIndex).getphone());
                    txtStartDateC.setText(tableCamp.getItems().get(myIndex).getphone());
                    txtEndDateC.setText(tableCamp.getItems().get(myIndex).getphone());




                }
            });
            return myRow;
        });


    }

    @FXML
    void deleteC(ActionEvent event) {
        myIndex = tableCamp.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableCamp.getItems().get(myIndex).getId()));


        try
        {
            pst = con.prepareStatement("delete from camp where id_camp = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Camp Registration");


            alert.setHeaderText("Camp Registration");
            alert.setContentText("Deleted!");

            alert.showAndWait();
            tableC();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @FXML
    void updateC(ActionEvent event) {

        String stname,address,phone,startDate, endDate,organizedBy;

        myIndex = tableCamp.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableCamp.getItems().get(myIndex).getId()));

        stname = txtNameC.getText();
        address = txtAddressC.getText();
        phone = txtPhoneC.getText();
        organizedBy = txtOrganizedByC.getText();
        startDate = txtStartDateC.getText();
        endDate = txtEndDateC.getText();
        try
        {
            pst = con.prepareStatement("update camp set name_camp = ?,address_camp = ? ,phone_camp = ?, organized_by = ?, start_date = ?, end_date = ? where id_camp = ? ");
            pst.setString(1, stname);
            pst.setString(2, address);
            pst.setString(3, phone);
            pst.setString(4, organizedBy);
            pst.setString(5, startDate);
            pst.setString(6, endDate);
            pst.setInt(7, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Camp Registration");


            alert.setHeaderText("Camp Registration");
            alert.setContentText("Updated!");

            alert.showAndWait();
            tableC();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    // Function for Donors functionality
    @FXML
    void addD(ActionEvent event) {

        String stname,address,phone,email, bloodType;
        stname = txtFullNameD.getText();
        address = txtAddressD.getText();
        phone = txtPhoneD.getText();
        email = txtEmailD.getText();
        bloodType = txtBloodTypeD.getText();

        try
        {
            pst = con.prepareStatement("insert into donor (full_name, address_donor, phone_donor, email_donor, bloodtype_donor) values (?,?,?,?,?)");
            pst.setString(1, stname);
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

            tableD();

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


    public void tableD()
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
    void deleteD(ActionEvent event) {
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
            tableD();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @FXML
    void updateD(ActionEvent event) {

        String stname,address,phone,email, bloodType;

        myIndex = tableDonor.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableDonor.getItems().get(myIndex).getId()));

        stname = txtFullNameD.getText();
        address = txtAddressD.getText();
        phone = txtPhoneD.getText();
        email = txtEmailD.getText();
        bloodType = txtBloodTypeD.getText();
        try
        {
            pst = con.prepareStatement("update donor set full_name = ?,address_donor = ? ,phone_donor = ?, email_donor = ?, bloodtype_donor = ? where id_donor = ? ");
            pst.setString(1, stname);
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
            tableD();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


}