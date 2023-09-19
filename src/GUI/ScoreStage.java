package GUI;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScoreStage extends Stage{

    private Stage myStage;

    public ScoreStage() throws IOException{
        Parent scoreStageLoader = FXMLLoader.load(getClass().getResource("ScoreStageFXML.fxml"));
        Scene scoreStageScene = new Scene(scoreStageLoader);
        scoreStageScene.getStylesheets().add(getClass().getResource("ScoreStageCSS.css").toExternalForm());
        Stage scoreStage = new Stage();
        scoreStage.setScene(scoreStageScene);
        scoreStage.setTitle("High Scores");
        scoreStage.setMaxWidth(200);
        scoreStage.setMaxHeight(800);
        scoreStage.setX(1380);
        scoreStage.setY(0);
        myStage = scoreStage;
    }

    public Stage getStage(){
        return myStage;
    }
}
