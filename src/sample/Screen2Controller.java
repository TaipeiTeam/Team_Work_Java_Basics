package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Screen2Controller implements Initializable, ControlledScreen{
    public  LinkedHashMap<String,Integer> throwList = new LinkedHashMap<>();
    public  Label informationLabel;

    public Button startBtn;
    public static String winnerName = "Radoslav";
    public Button throwBtn;
    public Label player1Name;
    public Label player1Money;
    public Label player2Name;
    public Label player2Money;
    public Button setPlayersBtn;
    public Label playersTurn;
    public Label winnerLabel;
    public Button resetBtn;
    ScreensController myController;
    public static int dicesSumPlayer1;
    public static int dicesSumPlayer2;
    public static int sumDices;
    public static  int tempCount = 1;


    @Override
    public void setScreenParent(ScreensController screenParent) {

        myController = screenParent;

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    public void exit(KeyEvent event) {
        Platform.exit();
    }


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

    public  void main(String[] args) {
        if (SetPlayersController.listPlayer.size() >=2) {
            informationLabel.setVisible(false);
        }
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
    public void checkForSetPlayers (){
        List<PlayersInfo> tempList = SetPlayersController.listPlayer;
        if (tempList.size() < 3){
            throwBtn.setDisable(true);
//            informationLabel.setText("Set Players Information First!");
        }
    }
    @FXML
    public void starGame(ActionEvent event) {
        if (SetPlayersController.listPlayer.size() < 2) {
            informationLabel.setVisible(true);
        }else {
            setPlayersBtn.setDisable(true);
            setPlayersBtn.setVisible(false);
            player1Name.setText(SetPlayersController.listPlayer.get(0).getPlayerName());
            player1Name.setVisible(true);
            player1Money.setText(String.valueOf(SetPlayersController.listPlayer.get(0).getAmountOfMonew()));
            player1Money.setVisible(true);
            player2Name.setText(SetPlayersController.listPlayer.get(1).getPlayerName());
            player2Name.setVisible(true);
            player2Money.setText(String.valueOf(SetPlayersController.listPlayer.get(1).getAmountOfMonew()));
            player2Money.setVisible(true);
            if (tempCount % 2 != 0) {
                playersTurn.setText(SetPlayersController.listPlayer.get(0).getPlayerName() + "'s turn");
            }else {
                playersTurn.setText(SetPlayersController.listPlayer.get(1).getPlayerName() + "'s turn");
            }
            informationLabel.setVisible(false);
            startBtn.setDisable(true);
            startBtn.setVisible(false);

            throwBtn.setDisable(false);
            throwBtn.setVisible(true);

        }
    }


    private void claculation1(String player1Name, int dicesSumPlayer1, String player2Name, int dicesSumPlayer2) {
        String winnwer;
        if (dicesSumPlayer1 > dicesSumPlayer2) {
            winnwer =player1Name;
        }else {
            winnwer = player2Name;
        }

        for (PlayersInfo listOfPlayer : SetPlayersController.listPlayer) {
            if (listOfPlayer.getPlayerName().equals(winnwer)) {
                listOfPlayer.amountOfMonew +=  listOfPlayer.getBid();

            }else  {
                listOfPlayer.amountOfMonew -=  listOfPlayer.getBid();
            }
            listOfPlayer.numOfThrows++;

//            if (listOfPlayer.amountOfmoney == 0) {
//                playWinner(listOfPlayer.getPlayername(),listOfPlayer.getNumberOfThrow());
//            }
        }

    }

    public void refreshPlayersInfo(ActionEvent event) {
        player1Name.setText(SetPlayersController.listPlayer.get(0).getPlayerName());
//        player1Name.setVisible(true);
        player1Money.setText(String.valueOf(SetPlayersController.listPlayer.get(0).getAmountOfMonew()));
//        player1Money.setVisible(true);
        player2Name.setText(SetPlayersController.listPlayer.get(1).getPlayerName());
//        player2Name.setVisible(true);
        player2Money.setText(String.valueOf(SetPlayersController.listPlayer.get(1).getAmountOfMonew()));
//        player2Money.setVisible(true);
//        player1Name.setText("test");

//        player1Name.setText(player1Na);
//        player1Money.setText(String.valueOf(player1Mo));
//        player2Name.setText(player2Na);
//        player2Money.setText(String.valueOf(player2Mo));

    }
    
    public Circle circle11;
    public Circle circle12;
    public Circle circle13;
    public Circle circle14;
    public Circle circle15;
    public Circle circle16;
    public Circle circle21;
    public Circle circle22;
    public Circle circle23;
    public Circle circle24;
    public Circle circle25;
    public Circle circle26;
    public GridPane dice1;
    public GridPane dice2;
    public Circle circle1center;
    public Circle circle2center;
    
    public void rollDices(ActionEvent actionEvent) throws InterruptedException {
        throwBtn.setDisable(true);
        clear();
        try {
            playSound("RollDice.wav");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        int numDice1 = random.nextInt(6);
        int numDice2 = random.nextInt(6);
        sumDices = numDice1 + numDice2 + 2;
        double rotateDice1 = random.nextInt((180-(-180)+1))+(-180);
        double rotateDice2 = random.nextInt((180-(-180)+1))+(-180);
        rollDice(numDice1,numDice2,rotateDice1,rotateDice2);
        refreshScene();
        throwBtn.setDisable(false);
        if (throwList.size() < 1) {
            if (tempCount % 2 != 0) {
                throwList.put(SetPlayersController.listPlayer.get(0).getPlayerName(),sumDices);
                tempCount++;
            }else {
                throwList.put(SetPlayersController.listPlayer.get(1).getPlayerName(),sumDices);
                tempCount++;
            }
            refreshPlayersInfo(actionEvent);
        }else {
            if (tempCount % 2 != 0) {
                throwList.put(SetPlayersController.listPlayer.get(0).getPlayerName(),sumDices);
                playersTurn.setText(SetPlayersController.listPlayer.get(0).getPlayerName() + "'s turn");
                tempCount++;
            }else {
                throwList.put(SetPlayersController.listPlayer.get(1).getPlayerName(),sumDices);
                playersTurn.setText(SetPlayersController.listPlayer.get(1).getPlayerName() + "'s turn");

                tempCount++;
            }

            calculation(throwList);
//            Thread.sleep(1000);
            clear();
            throwList.clear();
        }


    }

    private void refreshScene() {
        player1Name.setText(SetPlayersController.listPlayer.get(0).getPlayerName());
        player1Name.setVisible(true);
        player1Money.setText(String.valueOf(SetPlayersController.listPlayer.get(0).getAmountOfMonew()));
        player1Money.setVisible(true);
        player2Name.setText(SetPlayersController.listPlayer.get(1).getPlayerName());
        player2Name.setVisible(true);
        player2Money.setText(String.valueOf(SetPlayersController.listPlayer.get(1).getAmountOfMonew()));
        player2Money.setVisible(true);
//        if (tempCount % 2 != 0) {
//            playersTurn.setText(SetPlayersController.listPlayer.get(0).getPlayerName() + "'s turn");
//        }else {
//            playersTurn.setText(SetPlayersController.listPlayer.get(1).getPlayerName() + "'s turn");
//        }
    }

    private void calculation(LinkedHashMap<String, Integer> throwList) {
        String winner = null;
        int biggestThrow = 0;
        int dice1 = 0;
        int dice2 = 0;
        int temp = 1;
        if (SetPlayersController.listPlayer.get(0).getAmountOfMonew() != 0 && SetPlayersController.listPlayer.get(1).getAmountOfMonew() != 0) {
            for (Map.Entry<String, Integer> stringIntegerEntry : throwList.entrySet()) {
                if (temp % 2 != 0) {
                    dice1 = stringIntegerEntry.getValue();
                }else {
                    dice2 = stringIntegerEntry.getValue();
                }
                if (stringIntegerEntry.getValue() > biggestThrow) {
                    biggestThrow = stringIntegerEntry.getValue();
                }
                temp++;
            }
            for (Map.Entry<String, Integer> stringIntegerEntry : throwList.entrySet()) {
                if (stringIntegerEntry.getValue() == biggestThrow) {
                    winner = stringIntegerEntry.getKey();
                }
            }
            if (dice1 != dice2) {
                for (PlayersInfo listOfPlayer : SetPlayersController.listPlayer) {
                    if (listOfPlayer.getPlayerName().equals(winner)) {
                        listOfPlayer.amountOfMonew +=  listOfPlayer.getBid();

                    }else  {
                        listOfPlayer.amountOfMonew -=  listOfPlayer.getBid();
                    }
                    listOfPlayer.numOfThrows++;

                }
            }
        }

        if (SetPlayersController.listPlayer.get(0).getAmountOfMonew() == 0 || SetPlayersController.listPlayer.get(1).getAmountOfMonew() == 0) {
            throwBtn.setDisable(true);
            resetBtn.setDisable(false);
            resetBtn.setVisible(true);
            checkForWinnerName();
        }

    }

    private void checkForWinnerName() {
        String winnerName = null;
        int numbersOfThrows = 0;
        int winMoney = 0;

        int tempMaxAmountOfMoney = 0;

        for (PlayersInfo playersInfo : SetPlayersController.listPlayer) {
            if (playersInfo.getAmountOfMonew() > tempMaxAmountOfMoney) {
                tempMaxAmountOfMoney = playersInfo.getAmountOfMonew();
            }
        }
        for (PlayersInfo playersInfo : SetPlayersController.listPlayer) {
            if (playersInfo.getAmountOfMonew() == tempMaxAmountOfMoney) {
                winnerName = playersInfo.getPlayerName();
                numbersOfThrows = playersInfo.getNumOfThrows();
                winMoney = playersInfo.getAmountOfMonew();
            }
        }
        winnerLabel.setText("The winner is: " + winnerName + "!!!");
        winnerLabel.setDisable(false);
        winnerLabel.setVisible(true);

        writeInStatistics(winnerName, numbersOfThrows, winMoney);

    }

    @FXML
    private void resetGame(ActionEvent event) {
        SetPlayersController.listPlayer.clear();
        throwList.clear();
        setPlayersBtn.setVisible(true);
        setPlayersBtn.setDisable(false);
        startBtn.setDisable(false);
        startBtn.setVisible(true);

        throwBtn.setDisable(true);
        throwBtn.setVisible(false);
        winnerLabel.setText("");
        winnerLabel.setVisible(false);
        tempCount = 1;
        clear();
        starGame(event);

    }

    private void writeInStatistics(String winnerName, int numbersOfThrows, int winMoney) {

    }


    public static void playSound(String file) throws InterruptedException {
        AudioPlayer loadedPlayer = AudioPlayer.player;
        AudioStream openAudionStream;
        AudioData DataFromAudioStream ;
        ContinuousAudioDataStream loop = null; //we don`t want to play sound more than once but i don`t know how to play just once
        try {
            openAudionStream = new AudioStream(new FileInputStream(file));
            DataFromAudioStream = openAudionStream.getData();
            loop = new ContinuousAudioDataStream(DataFromAudioStream);
        } catch (IOException error) {}

        loadedPlayer.start(loop);
        Thread.sleep(1500);
        loadedPlayer.stop(loop);
    }
    
    private void rollDice(int numDice1, int numDice2, double rottateDice1, double rottateDice2) {
        dice1.setRotate(rottateDice1);
        dice1.setVisible(true);
        switch (numDice1+1) {
            case 1:
                circle1center.setRadius(5.0);
                circle1center.setVisible(true);
                break;
            case 2:
                circle12.setVisible(true);
                circle15.setVisible(true);
                break;
            case 3:
                circle11.setVisible(true);
                circle16.setVisible(true);
                circle1center.setVisible(true);
                break;
            case  4:
                circle11.setVisible(true);
                circle14.setVisible(true);
                circle16.setVisible(true);
                circle13.setVisible(true);
                break;
            case  5:
                circle11.setVisible(true);
                circle14.setVisible(true);
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
                circle2center.setRadius(5.0);
                circle2center.setVisible(true);
                break;
            case 2:
                circle22.setVisible(true);
                circle25.setVisible(true);
                break;
            case 3:
                circle21.setVisible(true);
                circle26.setVisible(true);
                circle2center.setVisible(true);
                break;
            case  4:
                circle21.setVisible(true);
                circle24.setVisible(true);
                circle26.setVisible(true);
                circle23.setVisible(true);
                break;
            case  5:
                circle21.setVisible(true);
                circle24.setVisible(true);
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
        dice2.setVisible(false);
        circle1center.setVisible(false);
        circle2center.setVisible(false);
        circle1center.setRadius(5.0);
        circle2center.setRadius(5.0);
    }


}
