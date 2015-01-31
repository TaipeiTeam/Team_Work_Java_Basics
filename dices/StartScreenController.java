/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dices;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author dell-xps
 */
public class StartScreenController extends ControlledScreen implements Initializable {
    ScreensController myController;
    
    @Override
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private  void goToScreen2(ActionEvent event) {
        myController.setScreen(ScreensFramework.screen2ID);
    }

    public void exitPlatform(ActionEvent event) {

        Platform.exit();
    }
}
