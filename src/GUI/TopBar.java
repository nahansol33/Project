package GUI;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class TopBar extends HBox{

    public BulletsIcon bullets;
    private HBox topHbox;
    private Label levelText;
    private Label currentScore;


    @FXML
    Label playerLabel;
    
    //setting the top part of the borderpane as hbox and creating labels
    public TopBar(){
        topHbox = new HBox();
        // updateTopBar(0);
        topHbox.setStyle("-fx-background-color: #336699;");
        topHbox.setPadding(new Insets(20, 10, 10, 10));
        topHbox.setSpacing(200);

        playerLabel = new Label("");
        playerLabel.setTextFill(Color.WHITE);
        levelText = new Label("Level: 1");
        currentScore = new Label("Current Score: ");
        currentScore.setTextFill(Color.WHITE);
        currentScore.setMaxWidth(333);
        levelText.setTextFill(Color.WHITE);
        levelText.setMaxWidth(333);
        bullets = new BulletsIcon(10);
        

        topHbox.getChildren().addAll(playerLabel, levelText, currentScore, bullets);
        setHgrow(playerLabel, Priority.ALWAYS);
        setHgrow(currentScore, Priority.ALWAYS);
        setHgrow(levelText, Priority.ALWAYS);
        setHgrow(bullets, Priority.ALWAYS);


        topHbox.setPrefWidth(1300);
        // topHbox.relocate(0, 100);
        
        this.getChildren().add(topHbox);
    }

    public void displayPlayerName(String name){
        playerLabel.setText("Current Player: " + name);
    }

    public void updateScore(int points){
        currentScore.setText("Current Score: " + points);
    }

    public void updateLevel(int level){
        levelText.setText("Current Level: " + level);
    }
    
    // public void updateTopBar(int numberOfBullets){
    //     topHbox.getChildren().remove(bullets);
    //     bullets = new BulletsIcon(numberOfBullets);
    //     topHbox.getChildren().add(bullets);
    // }
}
