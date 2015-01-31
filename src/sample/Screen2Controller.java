package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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

//    public static void setPlaersName (){
//
//    }
//    public static void checkForNewgame() {
//
//    }


    @FXML
    private  void goToScreen1(ActionEvent event) {
        myController.setScreen(ScreensFramework.screen1ID);
    }
    @FXML
    private  void goToScreen3(ActionEvent event) {
        myController.setScreen(ScreensFramework.screen3ID);
    }
    @FXML
    private void throwDices(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ThrowDices.fxml"));
        stage.setTitle("Throw Dices");
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }
    @FXML
    private  void goToScreen4(ActionEvent event) {
        myController.setScreen(ScreensFramework.screen4ID);
//        ThrowDicesController clear = new ThrowDicesController();
//        clear.clear();
    }

    public void exitPlatform(ActionEvent event) {
        Platform.exit();
    }
    @FXML
    public void setPlaersName(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SetPlayers.fxml"));
        stage.setTitle("Set Players");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

}
