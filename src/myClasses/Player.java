package myClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import GUI.PlayerController;
import GUI.ScoreStageController;

public class Player{
    protected String userName;
    protected String password;
    public int score;
    protected static ArrayList<Player> playerList = new ArrayList<>();
    protected static Player currentPlayer;
    public boolean successfulCreation = true;
    public static boolean returningPlayer = false;

    public Player(String userName, String password, boolean returning) throws IOException{
        if (playerExistCheck(userName) == false && returning == false){
            this.userName = userName;
            this.password = password;
            playerList.add(this);
            currentPlayer = this;
            PlayerController.savePlayer(currentPlayer, "playerDatabase.txt");
            ScoreStageController.setScoreStagePlayer(currentPlayer);
            System.out.println("NEw player created");
        }
        else if (playerExistCheck(userName) == true && returning == true){
            if (passwordCheck(userName, password)){
                this.userName = userName;
                this.password = password;
                returningPlayer = true;
                playerList.add(this);
                currentPlayer = this;
                ScoreStageController.setScoreStagePlayer(currentPlayer);
            }
            System.out.println("Return player created");
        }

        else {
            System.out.println("Error while creating player");
            successfulCreation = false;
        }
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return userName;
    }

    public int getScore(){
        return Targets.getScore();
    }

    protected void updateScore(int points){
        score += points;
    }

    public void setCurrentPlayer(){
        currentPlayer = this;
    }

    public static Player getCurrentPlayer(){
        return currentPlayer;
    }
    public static boolean getReturningPlayerStatus(){
        return returningPlayer;
    }
    
    public static boolean playerExistCheck(String userName) throws FileNotFoundException{
        boolean exist = false;
        ArrayList<String []> dbPlayerList = PlayerController.getDbPlayerList();
        for (String [] a:dbPlayerList){
            if (a[0].equals(userName)){
                exist = true;
            }
        }
        return exist;
    }
    public static boolean passwordCheck(String playerName, String EnteredPassword){
        boolean check = false;
        ArrayList<String []> dbPlayerList = PlayerController.getDbPlayerList();
        for (String []s: dbPlayerList){
            if (s[0].equals(playerName)){
                if (s[1].equals(EnteredPassword)){
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
}
