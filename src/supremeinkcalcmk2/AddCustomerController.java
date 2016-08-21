/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supremeinkcalcmk2;

import java.sql.Connection;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

/**
 *
 * @author Archa
 */
public class AddCustomerController {

    @FXML
    private Button SaveButton;

    @FXML
    private TableView<NewCustomerList> NewCustomerTableView;
    @FXML
    private TableColumn<NewCustomerList, String> PriceColumn;
    @FXML
    private TableColumn<NewCustomerList, String> BaseColorColumn;
    @FXML
    private TextField CustomerTextField;

    public void initialize() throws Exception {
    Connection connection;
//        BaseColorColumn.setCellValueFactory(
//                new PropertyValueFactory<NewCustomerList, String>("Yellow")
//        );
        BaseColorColumn.setCellValueFactory(new PropertyValueFactory<NewCustomerList, String>("BaseColor"));
//        PriceColumn.setCellValueFactory(new PropertyValueFactory<NewCustomerList, String>("Price"));
        //makes Price column editable
        PriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        PriceColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<NewCustomerList, String>>() {
                    @Override
                    public void handle(CellEditEvent<NewCustomerList, String> t) {
                        ((NewCustomerList) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setPrice(t.getNewValue());
                    }
                }
        );

        NewCustomerTableView.setItems(data);
        NewCustomerTableView.setEditable(true);

    }
    public ObservableList<NewCustomerList> data = FXCollections.observableArrayList(
            new NewCustomerList("Yellow", "0"),
            new NewCustomerList("012 Yellow", "0"),
            new NewCustomerList("021 Orange", "0"),
            new NewCustomerList("Warm Red", "0"),
            new NewCustomerList("032 Red", "0"),
            new NewCustomerList("Rhodamin Red", "0"),
            new NewCustomerList("Rubine Red", "0"),
            new NewCustomerList("Purple", "0"),
            new NewCustomerList("Violet", "0"),
            new NewCustomerList("072 Blue", "0"),
            new NewCustomerList("Reflex", "0"),
            new NewCustomerList("021 Reflex (AQ)", "0"),
            new NewCustomerList("Blue", "0"),
            new NewCustomerList("Green", "0"),
            new NewCustomerList("Black", "0"),
            new NewCustomerList("Trans.White", "0")
    );

    public void SaveButton() {
        try {
            Stage stage = (Stage) SaveButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        }
    }
}
