package main;

/**
 * Created by radko on 31.1.2015 г..
 */
public class PlayersInfo {

    public String playerName;
    public int amountOfMonew;
    public int numOfThrows;
    public  int bid;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }
//    public PlayersInfo(String playerName, int amountOfMonew, int numOfThrows) {
//        this.playerName = playerName;
//        this.amountOfMonew = amountOfMonew;
//        this.numOfThrows = numOfThrows;
//    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getAmountOfMonew() {
        return amountOfMonew;
    }

    public void setAmountOfMonew(int amountOfMonew) {
        this.amountOfMonew = amountOfMonew;
    }

    public int getNumOfThrows() {
        return numOfThrows;
    }

    public void setNumOfThrows(int numOfThrows) {
        this.numOfThrows = numOfThrows;
    }
}
