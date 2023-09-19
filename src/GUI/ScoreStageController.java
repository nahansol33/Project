package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import myClasses.Player;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ScoreStageController{
    
    // private String name = getNewPlayerName();

    @FXML
    protected VBox vbox;

    @FXML
    protected Label nameLabel;

    @FXML
    protected Label highScore;

    @FXML
    protected Label currentPlayer;

    @FXML
    protected Label currentPlayerScore;
    
    @FXML
    protected Label topPlayer;
    
    @FXML
    protected Label topPlayerScore;
    
    @FXML 
    protected Label secondPlayerName;
    
    @FXML 
    protected Label secondPlayerScore;
    
    @FXML 
    protected Label thirdPlayerName;
    
    @FXML 
    protected Label thirdPlayerScore;

    private static Player player;

    @FXML
    public void initialize() throws IOException{
        vbox.setStyle("-fx-background-color: black;");
        player = Player.getCurrentPlayer();
        updateCurrentPlayerName();
        updateCurrentPlayerScore();
        updateScoreBoard();
    }

    public void updateCurrentPlayerName(){
        currentPlayer.setText(player.getName());
    } 

    public void updateCurrentPlayerScore(){
        currentPlayerScore.setText(String.valueOf(player.getScore()));
    }
    public static void setScoreStagePlayer(Player playerPassed){
        player = playerPassed;
    }

    public void updateScoreBoard() throws IOException{
        PlayerController.readData();
        ArrayList<String[]> data = PlayerController.getDbPlayerList();
        System.out.println("current data length is: " + data.size());

        data.sort(Comparator.comparingInt(array -> Integer.parseInt(array[2])));

        topPlayer.setText(data.get(data.size()-1)[0]);
        topPlayerScore.setText(data.get(data.size()-1)[2]);
        secondPlayerName.setText(data.get(data.size()-2)[0]);
        secondPlayerScore.setText(data.get(data.size()-2)[2]);
        thirdPlayerName.setText(data.get(data.size()-3)[0]);
        thirdPlayerScore.setText(data.get(data.size()-3)[2]);
    }
}
