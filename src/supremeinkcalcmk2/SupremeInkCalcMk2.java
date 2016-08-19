/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supremeinkcalcmk2;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nader Ahmed
 * 8.9.16
 */
public class SupremeInkCalcMk2 extends Application {
    
    
    
        public static void main(String[] args) {
        launch(args);    
    }
        
    @Override
    public void start(Stage primaryStage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
    primaryStage.setTitle("Supreme Ink Customer Price Calculator");
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
    
    

    }

    /**
     * @param args the command line arguments
     */
    
    
    
}
