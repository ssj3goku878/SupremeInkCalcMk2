/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Get whats in the combo box (Customer Name)
//Load the prices from that customer 
//Get whats in pantone box
//Load pantone formula
//do math formula for price
package supremeinkcalcmk2;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Archa
 */
public class MainController implements Initializable {

    @FXML
    public ComboBox<String> ComboBoxSelectCustomer;
//    @FXML
//    private TableView<String> CustomerTableView;
    @FXML
    private TableView PantoneFormulaTableView;
    @FXML
    private TableColumn<FormulaList, String> BaseFormula;
    @FXML
    private TableColumn<FormulaList, String> BasePT;
    @FXML
    private TableView CustomerTableView;
    @FXML
    private TableColumn<BaseColor, String> BaseColor;
    @FXML
    private TableColumn<BaseColor, String> Price;
    @FXML
    private TextField PantoneNumberLabel;
    @FXML
    private Label LogoLabel;
    Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //Customer combo box
        ComboBoxSelectCustomer.setEditable(true);
        PantoneFormulaTableView.setEditable(true);
        CustomerTableView.setEditable(true);
        Price.setEditable(true);
        buildDataComboBox(); //call build buildDataComboBox method
        ComboBoxEvent(); // call ComboBoxEvent method

//        ComboBoxSelectCustomer.getItems().addAll("Register", "O'SULLIVAN", "Graphic Packaging", "Color Graphics", "Sign Masters");
        //CustomerTableView
//        BaseColor.setCellValueFactory(new PropertyValueFactory<BaseColor, String>("BaseColor"));
//        Price.setCellValueFactory(new PropertyValueFactory<BaseColor, Double>("Price"));
//        CustomerTableView.setItems(data);
        //ComboBoxSelectCustomer.setOnAction(e -> System.out.println(ComboBoxSelectCustomer.getValue()));
        //buildDataTableView();
        //load data into CustomerTableView when user interacts with ComboBox list
    }

    //Customer TableView
//    ObservableList<BaseColor> data = FXCollections.observableArrayList(
//            new BaseColor("Yellow", 5.4),
//            new BaseColor("Yellow 012", 5.4),
//            new BaseColor("Orange 021", 4.4),
//            new BaseColor("Warm Red", 8.7),
//            new BaseColor("032", 8.7),
//            new BaseColor("Rubine Red", 8.7),
//            new BaseColor("Rhodamine Red", 8.7),
//            new BaseColor("Purple", 8.7),
//            new BaseColor("Violet", 8.7),
//            new BaseColor("072", 8.7),
//            new BaseColor("L/F Reflex", 8.7),
//            new BaseColor("Reflex", 8.7),
//            new BaseColor("Blue", 8.7),
//            new BaseColor("Green", 8.7),
//            new BaseColor("Black", 8.7),
//            new BaseColor("Trans. White", 8.7)
//    );
    //update price from edit
    public void updatePrice(String newPrice, String RowName) {
        try {
            String updateQuery = "UPDATE " + ComboBoxSelectCustomer.getValue() + " SET Price = ? WHERE BaseColor = ?"; //If you require multiple columns to get a unique row, add them in the where clause as well.
            connection = SqlConnection.CustomerConnection();
            PreparedStatement ps = connection.prepareStatement(updateQuery); //con is the connection object
            ps.setString(1, newPrice); //if a string
            ps.setString(2, RowName); //if a string
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("could not update price");

        }
    }

    //update BaseBT from edit
    public void updateBasePT(String newBasePT, String rowName) {
        try {
            String updateQuery = "UPDATE `" + PantoneNumberLabel.getText() + "` SET BasePT = ? WHERE BaseFormula = ?"; //If you require multiple columns to get a unique row, add them in the where clause as well.
            connection = SqlConnection.FormulaConnection();
            PreparedStatement ps = connection.prepareStatement(updateQuery);
            ps.setString(1, newBasePT);
            ps.setString(2, rowName);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("could not update formula");
        }
    }

    //ComboBox on event
    public void ComboBoxEvent() {
        ComboBoxSelectCustomer.setOnAction((event) -> {
            ObservableList<BaseColor> dataCustomerViewTable = FXCollections.observableArrayList();
            BaseColor.setCellValueFactory(new PropertyValueFactory<BaseColor, String>("BaseColor"));
            Price.setCellValueFactory(new PropertyValueFactory<BaseColor, String>("Price"));

            Price.setCellFactory(TextFieldTableCell.forTableColumn());// change each column to textfield on click
            Price.setOnEditCommit( // make each table editable
                    new EventHandler<TableColumn.CellEditEvent<BaseColor, String>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<BaseColor, String> t) {
                            ((BaseColor) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPrice(t.getNewValue());
                            String newPrice = t.getNewValue(); // get new price
                            String RowName = t.getRowValue().getBaseColor(); //Unique identfier is something that uniquely identify the row. It could be the name of the object that we are pricing here.
                            updatePrice(newPrice, RowName); //Call DAO now
                        }
                    }
            );// end editable code

            //connection = SqlConnection.CustomerConnection();
            try {
                String SQL = "Select BaseColor, Price FROM `" + ComboBoxSelectCustomer.getValue() + "`";
//            String SQL = "Select Name FROM CustomerList WHERE Name = '" + ComboBoxSelectCustomer.getValue() + "'";
                connection = SqlConnection.CustomerConnection();
                ResultSet rs = connection.createStatement().executeQuery(SQL);
                while (rs.next()) {
                    BaseColor BS = new BaseColor();
                    BS.BaseColor.set(rs.getString("BaseColor"));
                    BS.Price.set(rs.getString("Price"));
                    dataCustomerViewTable.add(BS);
//                dataCustomerViewTable.add(rs.getString("Name"));
                }
                CustomerTableView.setItems(dataCustomerViewTable);
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }); // end on action event (load tableview from combo box selection)
    }//end combobox method

    public void ButtonNewPantone() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditColorFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            //stops user from using previous window
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GetFormulaButton() {
        ObservableList<FormulaList> dataFormuViewTable = FXCollections.observableArrayList();
        BaseFormula.setCellValueFactory(new PropertyValueFactory<FormulaList, String>("BaseFormula"));
        BasePT.setCellValueFactory(new PropertyValueFactory<FormulaList, String>("BasePT"));
        BasePT.setCellFactory(TextFieldTableCell.forTableColumn());
        BasePT.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<FormulaList, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<FormulaList, String> t) {
                        ((FormulaList) t.getTableView().getItems().get(t.getTablePosition().getRow())).setBasePT(t.getNewValue());
                        String newBasePT = t.getNewValue();
                        String rowName = t.getRowValue().getBaseFormula();
                        updateBasePT(newBasePT, rowName);
                    }
                }
        );

        //connection = SqlConnection.FormulaConnection();
        try {
            String SQL = "SELECT BaseFormula, BasePT FROM `" + PantoneNumberLabel.getText() + "`";
//            String SQL = "Select Name FROM CustomerList WHERE Name = '" + ComboBoxSelectCustomer.getValue() + "'";
            connection = SqlConnection.FormulaConnection();
            ResultSet rs = connection.createStatement().executeQuery(SQL);
            while (rs.next()) {
                FormulaList FL = new FormulaList();
                FL.BaseFormula.set(rs.getString("BaseFormula"));
                FL.BasePT.set(rs.getString("BasePT"));
                dataFormuViewTable.add(FL);
//                dataCustomerViewTable.add(rs.getString("Name"));
            }
            PantoneFormulaTableView.setItems(dataFormuViewTable);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void CustomerButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCustomerFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            //stops user from using previous window
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void calculatePriceButton() { //button that will calculate the price
        //divide raw material price by 100
        //multiply by raw material amount in fomrula
        //take each number that was multipled and add them up for final price
        try {
            //get formual data
            connection = SqlConnection.FormulaConnection();

            String Yellow = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = 'Yellow';";
            ResultSet Yellowrs = connection.createStatement().executeQuery(Yellow);
            String YellowFormula = Yellowrs.getString(2);
            System.out.println("Fomrula Yellow :" + YellowFormula);

            String TwoYellow = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = '012 Yellow';";
            ResultSet TwoYellowrs = connection.createStatement().executeQuery(TwoYellow);
            String TwoYellowFormula = TwoYellowrs.getString(2);
            System.out.println("Fomrula 012 Yellow :" + TwoYellowFormula);

            String TwoOrange = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = '021 Orange';";
            ResultSet TwoOrangers = connection.createStatement().executeQuery(TwoOrange);
            String TwoOrangeFormula = TwoOrangers.getString(2);
            System.out.println("Fomrula 012 Orange :" + TwoOrangeFormula);

            String WarmRed = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = 'Warm Red';";
            ResultSet WarmRedrs = connection.createStatement().executeQuery(WarmRed);
            String WarmRedFormula = WarmRedrs.getString(2);
            System.out.println("Fomrula Warm Red :" + WarmRedFormula);

            String TwoRed = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = '032 Red';";
            ResultSet TwoRedrs = connection.createStatement().executeQuery(TwoRed);
            String TwoRedFormula = TwoRedrs.getString(2);
            System.out.println("Fomrula 032 Red :" + TwoRedFormula);

            String RhodRed = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = 'Rhodamin Red';";
            ResultSet RhodRedrs = connection.createStatement().executeQuery(RhodRed);
            String RhodRedFormula = RhodRedrs.getString(2);
            System.out.println("Fomrula Rhodamin Red :" + RhodRedFormula);

            String Rubine = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = 'Rubine';";
            ResultSet Rubiners = connection.createStatement().executeQuery(Rubine);
            String RubineFormula = Rubiners.getString(2);
            System.out.println("Fomrula Rubine :" + RubineFormula);

            String Purple = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = 'Purple';";
            ResultSet Purplers = connection.createStatement().executeQuery(Purple);
            String PurpleFormula = Purplers.getString(2);
            System.out.println("Fomrula Purple :" + PurpleFormula);

            String Violet = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = 'Violet';";
            ResultSet Violetrs = connection.createStatement().executeQuery(Violet);
            String VioletFormula = Violetrs.getString(2);
            System.out.println("Fomrula Violet :" + VioletFormula);

            String TwoBlue = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = '072 Blue';";
            ResultSet TwoBluers = connection.createStatement().executeQuery(TwoBlue);
            String TwoBlueFormula = TwoBluers.getString(2);
            System.out.println("Fomrula 072 Blue :" + TwoBlueFormula);

            String Reflex = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = 'Reflex';";
            ResultSet Reflexrs = connection.createStatement().executeQuery(Reflex);
            String ReflexFormula = Reflexrs.getString(2);
            System.out.println("Fomrula Reflex :" + ReflexFormula);

            String TwoReflex = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = '021 Reflex (AQ)';";
            ResultSet TwoReflexrs = connection.createStatement().executeQuery(TwoReflex);
            String TwoReflexrsFormula = TwoReflexrs.getString(2);
            System.out.println("Fomrula 021 Reflex (AQ) :" + TwoReflexrsFormula);

            String Blue = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = 'Blue';";
            ResultSet Bluers = connection.createStatement().executeQuery(Blue);
            String BlueFormula = Bluers.getString(2);
            System.out.println("Fomrula Blue :" + BlueFormula);

            String Green = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = 'Green';";
            ResultSet Greenrs = connection.createStatement().executeQuery(Green);
            String GreenFormula = Greenrs.getString(2);
            System.out.println("Fomrula Green :" + GreenFormula);

            String Black = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = 'Black';";
            ResultSet Blackrs = connection.createStatement().executeQuery(Black);
            String BlackFormula = Blackrs.getString(2);
            System.out.println("Fomrula Black :" + BlackFormula);

            String White = "SELECT * FROM `" + PantoneNumberLabel.getText() + "` WHERE BaseFormula = 'Trans. White';";
            ResultSet Whiters = connection.createStatement().executeQuery(White);
            String WhiteFormula = Whiters.getString(2);
            System.out.println("Fomrula White :" + WhiteFormula);
            connection.close();
        } catch (Exception e) {
            System.out.println("Error getting formulas");
            e.printStackTrace();
        }
        
        //get customer prices
        try {
            connection = SqlConnection.CustomerConnection();
            
            String Yellow = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = 'Yellow';";
            ResultSet Yellowrs = connection.createStatement().executeQuery(Yellow);
            String YellowPrice = Yellowrs.getString(3);
            System.out.println("Price Yellow :" + YellowPrice);
            
            String TwoYellow = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = '012 Yellow';";
            ResultSet TwoYellowrs = connection.createStatement().executeQuery(TwoYellow);
            String TwoYellowPrice = TwoYellowrs.getString(3);
            System.out.println("Price 012 Yellow :" + TwoYellowPrice);

            String TwoOrange = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = '021 Orange';";
            ResultSet TwoOrangers = connection.createStatement().executeQuery(TwoOrange);
            String TwoOrangePrice = TwoOrangers.getString(3);
            System.out.println("Price 012 Orange :" + TwoOrangePrice);

            String WarmRed = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = 'Warm Red';";
            ResultSet WarmRedrs = connection.createStatement().executeQuery(WarmRed);
            String WarmRedPrice = WarmRedrs.getString(3);
            System.out.println("Price Warm Red :" + WarmRedPrice);

            String TwoRed = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = '032 Red';";
            ResultSet TwoRedrs = connection.createStatement().executeQuery(TwoRed);
            String TwoRedPrice = TwoRedrs.getString(3);
            System.out.println("Price 032 Red :" + TwoRedPrice);

            String RhodRed = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = 'Rhodamin Red';";
            ResultSet RhodRedrs = connection.createStatement().executeQuery(RhodRed);
            String RhodRedPrice = RhodRedrs.getString(3);
            System.out.println("Price Rhodamin Red :" + RhodRedPrice);

            String Rubine = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = 'Rubine';";
            ResultSet Rubiners = connection.createStatement().executeQuery(Rubine);
            String RubinePrice = Rubiners.getString(3);
            System.out.println("Price Rubine :" + RubinePrice);

            String Purple = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = 'Purple';";
            ResultSet Purplers = connection.createStatement().executeQuery(Purple);
            String PurplePrice = Purplers.getString(3);
            System.out.println("Price Purple :" + PurplePrice);

            String Violet = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = 'Violet';";
            ResultSet Violetrs = connection.createStatement().executeQuery(Violet);
            String VioletPrice = Violetrs.getString(3);
            System.out.println("Price Violet :" + VioletPrice);

            String TwoBlue = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = '072 Blue';";
            ResultSet TwoBluers = connection.createStatement().executeQuery(TwoBlue);
            String TwoBluePrice = TwoBluers.getString(3);
            System.out.println("Price 072 Blue :" + TwoBluePrice);

            String Reflex = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = 'Reflex';";
            ResultSet Reflexrs = connection.createStatement().executeQuery(Reflex);
            String ReflexPrice = Reflexrs.getString(3);
            System.out.println("Price Reflex :" + ReflexPrice);

            String TwoReflex = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = '021 Reflex (AQ)';";
            ResultSet TwoReflexrs = connection.createStatement().executeQuery(TwoReflex);
            String TwoReflexrsPrice = TwoReflexrs.getString(3);
            System.out.println("Price 021 Reflex (AQ) :" + TwoReflexrsPrice);

            String Blue = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = 'Blue';";
            ResultSet Bluers = connection.createStatement().executeQuery(Blue);
            String BluePrice = Bluers.getString(3);
            System.out.println("Price Blue :" + BluePrice);

            String Green = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = 'Green';";
            ResultSet Greenrs = connection.createStatement().executeQuery(Green);
            String GreenPrice = Greenrs.getString(3);
            System.out.println("Price Green :" + GreenPrice);

            String Black = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = 'Black';";
            ResultSet Blackrs = connection.createStatement().executeQuery(Black);
            String BlackPrice = Blackrs.getString(3);
            System.out.println("Price Black :" + BlackPrice);

            String White = "SELECT * FROM `" + ComboBoxSelectCustomer.getValue() + "` WHERE BaseColor = 'Trans. White';";
            ResultSet Whiters = connection.createStatement().executeQuery(White);
            String WhitePrice = Whiters.getString(3);
            System.out.println("Price White :" + WhitePrice);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error getting price");
        }

    }

//    public void buildDataTableView() {
//        //viewtable db connect
//        ObservableList<BaseColor> dataCustomerViewTable = FXCollections.observableArrayList();
//        BaseColor.setCellValueFactory(new PropertyValueFactory<BaseColor, String>("BaseColor"));
//        Price.setCellValueFactory(new PropertyValueFactory<BaseColor, String>("Price"));
//        connection = SqlConnection.CustomerConnection();
//        try {
//            String SQL = "Select BaseColor, Price FROM " + ComboBoxSelectCustomer.getValue() + "";
////            String SQL = "Select Name FROM CustomerList WHERE Name = '" + ComboBoxSelectCustomer.getValue() + "'";
//            connection = SqlConnection.CustomerConnection();
//            ResultSet rs = connection.createStatement().executeQuery(SQL);
//            while (rs.next()) {
//                BaseColor BS = new BaseColor();
//                BS.BaseColor.set(rs.getString("BaseColor"));
//                BS.Price.set(rs.getString("Price"));
//                dataCustomerViewTable.add(BS);
////                dataCustomerViewTable.add(rs.getString("Name"));
//            }
//            CustomerTableView.setItems(dataCustomerViewTable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //combobox sql connection and fill data
    public void buildDataComboBox() {
        //viewtable db connect
//        ObservableList<String> dataCustomerViewTable = FXCollections.observableArrayList();
//        BaseColor.setCellValueFactory(new PropertyValueFactory<BaseColor, String>("BaseColor"));
//        Price.setCellValueFactory(new PropertyValueFactory<BaseColor, Double>("Price"));
        //combobox db connect
        ObservableList<String> dataComboBox = FXCollections.observableArrayList();
        connection = SqlConnection.CustomerConnection();
        try {
            String SQL = "Select Name From CustomerList";
            ResultSet rs = connection.createStatement().executeQuery(SQL);
            while (rs.next()) {
                dataComboBox.add(rs.getString("Name"));
//                dataCustomerViewTable.add(rs.getString("Yellow"));
            }
            ComboBoxSelectCustomer.setItems(dataComboBox);
//            CustomerTableView.setItems(dataCustomerViewTable);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Building ComboBox Data");
        }
        if (connection == null) {
            System.exit(1);
            System.out.println("Connection failed");
        }
    }

    public boolean isDbConnected() {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
