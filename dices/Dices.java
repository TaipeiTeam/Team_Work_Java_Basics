/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dices;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author dell-xps
 */
public class Dices extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Image applicationIcon = new Image(getClass().getResourceAsStream("../images/dices_icon.png"));
        stage.getIcons().add(applicationIcon);
        stage.setTitle("Dices");
        Parent root = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
