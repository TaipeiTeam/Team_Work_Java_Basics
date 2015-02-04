package main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;

public class Screen2Controller implements Initializable, ControlledScreen{
    public  LinkedHashMap<String,Integer> throwList = new LinkedHashMap<>();
    public  Label informationLabel;

    public Button startBtn;
    public static String winnerName = "";
    public Button throwBtn;
    public Label player1Name;
    public Label player1Money;
    public Label player2Name;
    public Label player2Money;
    public Button setPlayersBtn;
    public Label playersTurn;
    public Label winnerLabel;
    public Button resetBtn;
    public Label whoWon;
    public Label player1NameStat;
    public Label player2NameStat;
    public TextArea player1TextArea;
    public TextArea player2TextArea;
    public SplitPane statisticSplitPane;
    
    ScreensController myController;
    public static int dicesSumPlayer1;
    public static int dicesSumPlayer2;
    public static int sumDices;
    public static int tempCount = 1;
    public static String[] playerStatString = new String[50];
    public static int stringIndexCounter = 0;
    public static int insertTextIndex1 = 0;
    public static int insertTextIndex2 = 0;


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
            player1NameStat.setText(SetPlayersController.listPlayer.get(0).getPlayerName() + "'s throws");
            player2NameStat.setText(SetPlayersController.listPlayer.get(1).getPlayerName() + "'s throws");
            informationLabel.setVisible(false);
            startBtn.setDisable(true);
            startBtn.setVisible(false);
            resetBtn.setDisable(true);
            resetBtn.setOpacity(0.7);

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
        }
    }

    public void refreshPlayersInfo(ActionEvent event) {
        player1Name.setText(SetPlayersController.listPlayer.get(0).getPlayerName());
        player1Money.setText(String.valueOf(SetPlayersController.listPlayer.get(0).getAmountOfMonew()));
        player2Name.setText(SetPlayersController.listPlayer.get(1).getPlayerName());
        player2Money.setText(String.valueOf(SetPlayersController.listPlayer.get(1).getAmountOfMonew()));
    }

    public void refreshPlayersInfo1(String playerName, int sumDices) {
        playersTurn.setText(playerName + " thrown: " + sumDices);
        player1Name.setText(SetPlayersController.listPlayer.get(0).getPlayerName());
        player1Money.setText(String.valueOf(SetPlayersController.listPlayer.get(0).getAmountOfMonew()));
        player2Name.setText(SetPlayersController.listPlayer.get(1).getPlayerName());
        player2Money.setText(String.valueOf(SetPlayersController.listPlayer.get(1).getAmountOfMonew()));
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
        throwBtn.setOpacity(0.6);
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
        refreshScene();
        rollDice(numDice1, numDice2,rotateDice1,rotateDice2);
        throwBtn.setDisable(false);
        if (throwList.size() < 1) {
            throwList.put(SetPlayersController.listPlayer.get(0).getPlayerName(),sumDices);
            refreshPlayersInfo1(SetPlayersController.listPlayer.get(0).getPlayerName(),sumDices);
            player1TextArea.appendText((SetPlayersController.listPlayer.get(0).getNumOfThrows() + 1) + " " +
                    SetPlayersController.listPlayer.get(0).getPlayerName() + "`s throw -> " + sumDices + "\n");

        }else {
            throwList.put(SetPlayersController.listPlayer.get(1).getPlayerName(), sumDices);
            refreshPlayersInfo1(SetPlayersController.listPlayer.get(1).getPlayerName(),sumDices);
            player2TextArea.appendText((SetPlayersController.listPlayer.get(1).getNumOfThrows() + 1) + " " +
                    SetPlayersController.listPlayer.get(1).getPlayerName() + "`s throw -> " + sumDices + "\n");

            calculation(throwList);
            throwBtn.setOpacity(1);
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
    }

    private void calculation(LinkedHashMap<String, Integer> throwList) throws InterruptedException {
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
                whoWon.setText(winner + " just won the bid and " + SetPlayersController.listPlayer.get(0).bid + " leva");
                if (winner.equals(SetPlayersController.listPlayer.get(0).getPlayerName())) {
                    player1TextArea.appendText(winner + " just won the bid and " + SetPlayersController.listPlayer.get(0).bid + " leva\n");
                }else {
                    player2TextArea.appendText(SetPlayersController.listPlayer.get(1).getPlayerName() + " just won the bid and " + SetPlayersController.listPlayer.get(1).bid + " leva\n");
                }
                player1Money.setText(String.valueOf(SetPlayersController.listPlayer.get(0).getAmountOfMonew()));
                player2Money.setText(String.valueOf(SetPlayersController.listPlayer.get(1).getAmountOfMonew()));
            } else {
                whoWon.setText("The dices are equal");
                player1TextArea.appendText("The dices are equal\n");
                player2TextArea.appendText("The dices are equal\n");
            }
        }
        if (SetPlayersController.listPlayer.get(0).getAmountOfMonew() == 0 || SetPlayersController.listPlayer.get(1).getAmountOfMonew() == 0) {
            throwBtn.setDisable(true);
            resetBtn.setDisable(false);
            resetBtn.setVisible(true);
            Arrays.fill(playerStatString, null);
            stringIndexCounter = 0;
            insertTextIndex1 = 0;
            insertTextIndex2 = 0;
            checkForWinnerName();
        }
    }

    private void checkForWinnerName() throws InterruptedException {
        String winnName = "21321_1321321321324";
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
                winnName = playersInfo.getPlayerName();
                numbersOfThrows = playersInfo.getNumOfThrows();
                winMoney = playersInfo.getAmountOfMonew();
            }
        }
        winnerLabel.setText("The winner is: " + winnName + "!!!");
        if (winnName.equals(SetPlayersController.listPlayer.get(0).getPlayerName())) {
            player1TextArea.appendText("The winner is: " + winnName + "!!!\n");
        }else {
            player2TextArea.appendText("The winner is: " + SetPlayersController.listPlayer.get(1).getPlayerName() + "!!!\n");
        }
        winnerLabel.setDisable(false);
        winnerLabel.setVisible(true);
        winnerName = winnName;
        writeInStatistics(winnName, numbersOfThrows, winMoney);
        try {
            playSound("Coin.wav");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resetBtn.setDisable(false);
        resetBtn.setOpacity(1.0);
        

    }

    @FXML
    private void resetGame(ActionEvent event) {
        SetPlayersController.listPlayer.clear();
        player1TextArea.clear();
        player2TextArea.clear();
        throwList.clear();
        setPlayersBtn.setVisible(true);
        setPlayersBtn.setDisable(false);
        startBtn.setDisable(false);
        startBtn.setVisible(true);

        throwBtn.setDisable(true);
        throwBtn.setVisible(false);
        winnerLabel.setText("");
        winnerLabel.setVisible(false);
        player1Money.setVisible(false);
        player1Name.setVisible(false);
        player2Name.setVisible(false);
        player2Money.setVisible(false);
        playersTurn.setText("");
        tempCount = 1;
        clear();
        starGame(event);

    }

    private void writeInStatistics(String winnerName, int numberOfThrows, int winMoney) {
        try {
            FileInputStream file = new FileInputStream("dataBase\\myTest.xlsx");

            //create workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //get the last row number to append new data
            int rowNumber = sheet.getLastRowNum()+1;

            //this data needs to be written
            Map<String,Object[]> data = new HashMap<>();
            data.put("new", new Object[]{rowNumber-1, winnerName, numberOfThrows,winMoney});

            //set to iterate and add rows into file
            Set<String> newRows = data.keySet();

            for (String key : newRows) {

                //create a new Row in existing XLSX sheet
                Row row = sheet.createRow(rowNumber++);
                Object[] objArr = data.get(key);
                int celNumber = 0;

                for (Object obj : objArr) {
                    Cell cell = row.createCell(celNumber++);
                    if (obj instanceof String) {
                        cell.setCellValue((String)obj);
                    } else if (obj instanceof Integer) {
                        cell.setCellValue((int)obj);
                    }
                }
            }
            file.close();
            //open an Output Stream to save written data into XLSX file
            try {
                //write the workbook in file system
                FileOutputStream out = new FileOutputStream("dataBase\\myTest.xlsx");
                workbook.write(out);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        whoWon.setText("");
    }
}
