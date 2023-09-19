package GUI;

import myClasses.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileNotFoundException;
import java.io.IOException;



public class StartScreenController{
    @FXML
    protected Label ExistingPlayer;

    @FXML
    protected Label authors;

    @FXML
    protected BorderPane borderPane;

    @FXML
    protected PasswordField existingPasswordField;

    @FXML
    protected Label existingPasswordText;

    @FXML
    protected TextField existingPlayerNameField;

    @FXML
    protected Label existingPlayerNameText;

    @FXML
    protected Label gameTitle;

    @FXML
    protected VBox leftBar;

    @FXML
    protected Label message;

    @FXML
    protected TextField newPlayerNameField;

    @FXML
    protected Label newPlayerText;

    @FXML
    protected PasswordField newPlayerPasswordField;

    @FXML
    protected Label passwordText;

    @FXML
    protected Label playerNameText;

    @FXML
    protected VBox rightBar;

    @FXML
    protected Button playButton;

    @FXML
    protected VBox topBar;

    @FXML
    protected static boolean gameStatus = false;

    @FXML
    private static Scene gameScene;

    @FXML
    Window window;

    @FXML
    customMouse mouse = new customMouse();

    @FXML
    private ScoreStageController scoreStageController; 

    @FXML
    private static Stage stage;


    public static boolean returningPlayer = false;
    protected String playerName;
    protected String playerPassword; 


    public void playGame(String name) throws IOException{
           
        gameScene = new GameScreen(name);
        gameScene.setCursor(mouse.customCursor);
        stage = new Stage();
        stage.setScene(gameScene);
        stage.setX(0);
        stage.setY(0);
        stage.show();
    }

    public void initialize(){
        topBar.setStyle("-fx-background-color: #336699;");
        gameTitle.setTextFill(Color.WHITE);
        authors.setTextFill(Color.WHITE);
    }

    public boolean loginCheck(){
        boolean check = true;
        String newPlayerName = newPlayerNameField.getText();
        String exitingPlayerName = existingPlayerNameField.getText();
        String newPlayerPassword = newPlayerPasswordField.getText();
        String existingPlayerPassword = existingPasswordField.getText();
        if ((!newPlayerName.equals("") || !newPlayerPassword.equals("")) && (!exitingPlayerName.equals("")|| !existingPlayerPassword.equals(""))){
            check = false;
            message.setText("Check your login fields and make sure you are inputting fields only for either New Player or Returning Player fields");
        }
        else check = true;
        return check;
    }


    @FXML
    public void onClickPlay(ActionEvent playButtonClicked) throws IOException{
        playButton.isDisabled();
        returningPlayer = false;
        if (loginCheck()==true){
            if (!(newPlayerNameField.getText().isEmpty())){
                if ((newPlayerPasswordField.getText().isEmpty())){
                    message.setText("Password cannot be empty");
                }
                else{
                    playerName = getNewPlayerName();
                    playerPassword = getNewPlayerPassword();
                    Player newPlayer = new Player(playerName, playerPassword, false);
                    newPlayer.setCurrentPlayer();
                    if (newPlayer.successfulCreation == false){
                        message.setText(playerName + " already exists");
                    }
                    else {
                        playGame(playerName);
                        onButtonClicked();
                    }
                }
                
            }
            else if (!(existingPlayerNameField.getText().isEmpty())){
                boolean passwordMatch;
                returningPlayer = true;
                playerName = getExistingPlayerName();
                playerPassword = getExistingPlayerPassword();
                passwordMatch = Player.passwordCheck(playerName, playerPassword);
                if (Player.playerExistCheck(playerName) && passwordMatch){
                    Player player = new Player(playerName, playerPassword, true);
                    player.setCurrentPlayer();
                    playGame(playerName);
                    onButtonClicked();
                }
                else if (Player.playerExistCheck(playerName) == false){
                    message.setText("Entered player does not exist in our system");
                }
                else {
                    message.setText("Your entered password does not match our records");
                }                    
            }
            else if (newPlayerNameField.getText().isEmpty()){
                message.setText("You must properly login");
            }
        }
    }

    @FXML
    public void onButtonClicked(){
        Stage stage = (Stage) playButton.getScene().getWindow();
        stage.close();
    }

    public String getNewPlayerName(){
        return newPlayerNameField.getText();
    }

    public String getNewPlayerPassword(){
        return newPlayerPasswordField.getText();
    }

    public String getExistingPlayerName(){
        return existingPlayerNameField.getText();
    }

    public String getExistingPlayerPassword(){
        return existingPasswordField.getText();
    }

}