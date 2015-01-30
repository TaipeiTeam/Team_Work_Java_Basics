package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Screen2Controller implements Initializable, ControlledScreen{
    ScreensController myController;
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private  void goToScreen1(ActionEvent event) {
        myController.setScreen(ScreensFramework.screen1ID);
    }
    @FXML
    private  void goToScreen3(ActionEvent event) {
        myController.setScreen(ScreensFramework.screen3ID);
    }

    public void exitPlatform(ActionEvent event) {
        Platform.exit();
    }
}
