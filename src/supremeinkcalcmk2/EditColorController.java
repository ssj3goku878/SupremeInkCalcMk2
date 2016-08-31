/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supremeinkcalcmk2;

import java.sql.Connection;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Archa
 */
public class EditColorController {

    @FXML
    private Button SaveButton;
    @FXML
    private Button LoadButton;
    @FXML
    public TextField YellowText;
    @FXML
    private TextField TwoYellowText;
    @FXML
    private TextField OrangeText;
    @FXML
    private TextField WarmRedText;
    @FXML
    private TextField RedText;
    @FXML
    private TextField RhodRedText;
    @FXML
    private TextField RubineText;
    @FXML
    private TextField PurpleText;
    @FXML
    private TextField VioletText;
    @FXML
    private TextField TwoBlueText;
    @FXML
    private TextField BlueText;
    @FXML
    private TextField ReflexText;
    @FXML
    private TextField TwoReflexText;
    @FXML
    private TextField GreenText;
    @FXML
    private TextField BlackText;
    @FXML
    private TextField WhiteText;
    @FXML
    private TextField PantoneTextField;
    Connection connection;
    
    public void LoadButton(){
        connection = SqlConnection.FormulaConnection();
        try {
            String SQL = "Select * FROM '100';";
            ResultSet rs = connection.createStatement().executeQuery(SQL);
            while (rs.next()) {
                String Yellow = rs.getString("BasePt");
                YellowText.setText(Yellow);
                
                String TwoYellow = rs.getString("BasePT");
                TwoYellowText.setText(TwoYellow);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //Dialogs.create().message("Hi!").showInformation();

        }
            

    }
}
