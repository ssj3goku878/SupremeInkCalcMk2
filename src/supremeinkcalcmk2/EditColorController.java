/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supremeinkcalcmk2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    public void SaveButton() {
        Connection CN = null;
        PreparedStatement pstmt1, pstmt2,
                pstmt3, pstmt4, pstmt5, pstmt6,
                pstmt7, pstmt8, pstmt9, pstmt10,
                pstmt11, pstmt12, pstmt13,
                pstmt14, pstmt15, pstmt16,
                pstmt17, pstmt18 = null;
        try {
            //sql connection
            CN = SqlConnection.FormulaConnection();

            String SQL2 = "CREATE TABLE `" + PantoneTextField.getText() + "` "
                    + "(`BaseFormula` "
                    + "TEXT, `BasePT` INTEGER);";

            
            String SQL3
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('Yellow','" + YellowText.getText() + "' );";
            String SQL4
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('012 Yellow','" + TwoYellowText.getText() + "' );";
            String SQL5
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('021 Orange','" + OrangeText.getText() + "' );";
            String SQL6
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('Warm Red','" + WarmRedText.getText() + "' );";
            String SQL7
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('032 Red','" + RedText.getText() + "' );";
            String SQL8
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('Rhodamin Red','" + RhodRedText.getText() + "' );";
            String SQL9
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('Rubine','" + RubineText.getText() + "' );";
            String SQL10
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('Purple','" + PurpleText.getText() + "' );";
            String SQL11
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('Violet','" + VioletText.getText() + "' );";
            String SQL12
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('072 Blue','" + BlueText.getText() + "' );";
            String SQL13
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('Reflex','" + ReflexText.getText() + "' );";
            String SQL14
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('021 Reflex (AQ)','" + TwoReflexText.getText() + "' );";
            String SQL15
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('Blue','" + TwoBlueText.getText() + "' );";
            String SQL16
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('Green','" + GreenText.getText() + "' );";
            String SQL17
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('Black','" + BlackText.getText() + "' );";
            String SQL18
                    = "INSERT INTO `" + PantoneTextField.getText() + "`(`BaseFormula`, `BasePT`) VALUES ('Trans. White','" + WhiteText.getText() + "' );";

            pstmt2 = CN.prepareStatement(SQL2);
//            pstmt1.setString(1, PantoneTextField.getText()); // Insert into

//            pstmt1.execute();
            pstmt2.executeUpdate();
            pstmt3 = CN.prepareStatement(SQL3);
            pstmt4 = CN.prepareStatement(SQL4);
            pstmt5 = CN.prepareStatement(SQL5);
            pstmt6 = CN.prepareStatement(SQL6);
            pstmt7 = CN.prepareStatement(SQL7);
            pstmt8 = CN.prepareStatement(SQL8);
            pstmt9 = CN.prepareStatement(SQL9);
            pstmt10 = CN.prepareStatement(SQL10);
            pstmt11 = CN.prepareStatement(SQL11);
            pstmt12 = CN.prepareStatement(SQL12);
            pstmt13 = CN.prepareStatement(SQL13);
            pstmt14 = CN.prepareStatement(SQL14);
            pstmt15 = CN.prepareStatement(SQL15);
            pstmt16 = CN.prepareStatement(SQL16);
            pstmt17 = CN.prepareStatement(SQL17);
            pstmt18 = CN.prepareStatement(SQL18);

            pstmt3.execute();
            pstmt4.execute();
            pstmt5.execute();
            pstmt6.execute();
            pstmt7.execute();
            pstmt8.execute();
            pstmt9.execute();
            pstmt10.execute();
            pstmt11.execute();
            pstmt12.execute();
            pstmt13.execute();
            pstmt14.execute();
            pstmt15.execute();
            pstmt16.execute();
            pstmt17.execute();
            pstmt18.execute();

            CN.close();
            //close window
            Stage stage = (Stage) SaveButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
