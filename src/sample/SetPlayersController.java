package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radko on 31.1.2015 г..
 */
public class SetPlayersController {
    public static List<PlayersInfo> listPlayer = new ArrayList<>();
    public TextField player1Name;
    public TextField player2Name;
    public TextField cashDebit;
    public Button setPlayersBtn;
    public Label informationLabel;
    public Label bidInfo;

    @FXML
    public void setListPlayer(ActionEvent event) throws InterruptedException {
        if (player1Name != null  && player2Name != null && cashDebit != null) {
            PlayersInfo player1 = new PlayersInfo();
            player1.playerName = player1Name.getText();
            player1.amountOfMonew = Integer.parseInt(cashDebit.getText());
            player1.numOfThrows = 0;
            player1.bid = (Integer.parseInt(cashDebit.getText())/2);

            PlayersInfo player2 = new PlayersInfo();
            player2.playerName = player2Name.getText();
            player2.amountOfMonew = Integer.parseInt(cashDebit.getText());
            player2.numOfThrows = 0;
            player2.bid = (Integer.parseInt(cashDebit.getText())/2);

            listPlayer.add(player1);
            listPlayer.add(player2);

            Stage stage = (Stage) setPlayersBtn.getScene().getWindow();
            stage.close();

        }else {
            informationLabel.setText("You must fill in all of the fields!!!");
        }
    }
}
