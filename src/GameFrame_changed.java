
import java.io.IOException;

import GUI.PlayerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GameFrame_changed extends Application{

    
    @Override
    public void start(Stage primarysStage) throws IOException{
        Parent startScreen = FXMLLoader.load(getClass().getResource("GUI/StartScreen.fxml"));
        
        Scene startScene = new Scene(startScreen);
        PlayerController.readData();
        //setting stage scene to our scene and displaying
        primarysStage.setScene(startScene);
        primarysStage.show();   
    }


    public static void main(String[] args) {
        launch(args);
    }
}
