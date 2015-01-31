package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by radko on 30.1.2015 г..
 */
public class ThrowDicesController implements Initializable, ControlledScreen{
    public Button rollDicesBtn;
    public TextField throwInfo;
    public Button closeButton;
    ScreensController myController;
    public  Circle circle11;
    public  Circle circle12;
    public  Circle circle13;
    public  Circle circle14;
    public  Circle circle15;
    public  Circle circle16;
    public  Circle circle21;
    public  Circle circle22;
    public  Circle circle23;
    public  Circle circle24;
    public  Circle circle25;
    public  Circle circle26;
    public  GridPane dice1;
    public GridPane dice2;
    public  Circle circle1center;
    public  Circle circle2center;
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

//    @FXML
//    private  void goToScreen2(ActionEvent event) {
//        myController.setScreen(ScreensFramework.screen2ID);
//    }
//    @FXML
//    private  void goToScreen3(ActionEvent event) {
//        myController.setScreen(ScreensFramework.screen3ID);
//    }
//
//    public void exitPlatform(ActionEvent event) {
//
//        Platform.exit();
//    }
    public void rollDices(ActionEvent actionEvent) throws InterruptedException {
        clear();
        try {
            playSound();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        int numDice1 = random.nextInt(6);
        int numDice2 = random.nextInt(6);
        int sumDices = numDice1 + numDice2 + 2;
        double rotateDice1 = random.nextInt((180-(-180)+1))+(-180);
        double rotateDice2 = random.nextInt((180-(-180)+1))+(-180);
        rollDice(numDice1,numDice2,rotateDice1,rotateDice2);
        throwInfo.setText(String.valueOf(sumDices));
    }

    public static void playSound() throws InterruptedException {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD ;
        ContinuousAudioDataStream loop = null; //we don`t want to play sound more than once but i don`t know how to play just once
        try {
            BGM = new AudioStream(new FileInputStream("RollDice.wav"));
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);
        } catch (IOException error) {}
        MGP.start(loop);
        Thread.sleep(1800);
        MGP.stop(loop);
    }

    public void rollDice(int numDice1, int numDice2, double rottateDice1, double rottateDice2) {
        dice1.setRotate(rottateDice1);
        dice1.setVisible(true);
        switch (numDice1+1) {
            case 1:
                circle1center.setRadius(30.0);
                circle1center.setVisible(true);
                break;
            case 2:
                circle11.setVisible(true);
                circle14.setVisible(true);
                break;
            case 3:
                circle13.setVisible(true);
                circle15.setVisible(true);
                circle1center.setVisible(true);
                break;
            case  4:
                circle12.setVisible(true);
                circle15.setVisible(true);
                circle16.setVisible(true);
                circle13.setVisible(true);
                break;
            case  5:
                circle12.setVisible(true);
                circle15.setVisible(true);
                circle16.setVisible(true);
                circle13.setVisible(true);
                circle1center.setVisible(true);
                break;
            case 6:
                circle11.setVisible(true);
                circle12.setVisible(true);
                circle13.setVisible(true);
                circle14.setVisible(true);
                circle15.setVisible(true);
                circle16.setVisible(true);
                break;
        }

        dice2.setRotate(rottateDice2);
        dice2.setVisible(true);
        switch (numDice2+1) {
            case 1:
                circle2center.setRadius(30.0);
                circle2center.setVisible(true);
                break;
            case 2:
                circle22.setVisible(true);
                circle24.setVisible(true);
                break;
            case 3:
                circle23.setVisible(true);
                circle25.setVisible(true);
                circle2center.setVisible(true);
                break;
            case  4:
                circle21.setVisible(true);
                circle25.setVisible(true);
                circle26.setVisible(true);
                circle23.setVisible(true);
                break;
            case  5:
                circle21.setVisible(true);
                circle25.setVisible(true);
                circle26.setVisible(true);
                circle23.setVisible(true);
                circle2center.setVisible(true);
                break;
            case 6:
                circle21.setVisible(true);
                circle22.setVisible(true);
                circle23.setVisible(true);
                circle24.setVisible(true);
                circle25.setVisible(true);
                circle26.setVisible(true);
                break;
        }
    }


    public void clear() {
        circle11.setVisible(false);
        circle12.setVisible(false);
        circle13.setVisible(false);
        circle14.setVisible(false);
        circle15.setVisible(false);
        circle16.setVisible(false);
        circle21.setVisible(false);
        circle22.setVisible(false);
        circle23.setVisible(false);
        circle24.setVisible(false);
        circle25.setVisible(false);
        circle26.setVisible(false);
        dice1.setVisible(false);
        dice1.setVisible(false);
        circle1center.setVisible(false);
        circle2center.setVisible(false);
        circle1center.setRadius(20.0);
        circle2center.setRadius(20.0);
        throwInfo.setText("");
    }

    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
