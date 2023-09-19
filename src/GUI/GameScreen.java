package GUI;

import javafx.scene.paint.Color;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import myClasses.Player;
import myClasses.Targets; 

public class GameScreen extends Scene{
    
    private static TopBar topbar = new TopBar();
    private static boolean gameOver = false;
    private static Stage gameoverStage;
    private static Stage scoreStage;


    public GameScreen(String name) throws IOException{
        super(setMainPane(name),1300, 950);

        customMouse myMouse = new customMouse();
        this.setCursor(myMouse.customCursor);

        FXMLLoader fxmlLoader = new FXMLLoader(GameScreen.class.getResource("GameOverPage.fxml"));
        Parent gameOver = fxmlLoader.load();
        Scene gameOverScene = new Scene(gameOver);
        gameoverStage = new Stage();
        gameoverStage.setScene(gameOverScene);

    }

    public static Pane setMainPane(String name){
        customMouse myMouse = new customMouse();
        Pane mainPane = new Pane();
        topbar.displayPlayerName(name);
        mainPane.getChildren().add(topbar);
        // mainPane.setStyle("-fx-background-color: white;");
        mainPane.setOnMouseEntered(event ->{mainPane.setCursor(myMouse.customCursor);
        });
        mainPane.setOnMouseExited(event ->{mainPane.setCursor(Cursor.DEFAULT);
        });

        // Targets t1 = new Targets();
        // mainPane.getChildren().add(t1);

        // Targets t2 = new Targets();
        // mainPane.getChildren().add(t2);

        Targets.addTarget(mainPane);    
        // Targets.addTarget(mainPane);    
        // Targets.addTarget(mainPane);    
       
        mainPane.setOnMouseMoved(event ->{
            topbar.updateScore(Targets.getScore());
            topbar.updateLevel(Targets.getLevel());
        });

        mainPane.setOnMouseClicked(event -> {
            topbar.updateScore(Targets.getScore());
            double xClicked = event.getX();
            double yClicked = event.getY();
            Circle circle = new Circle(3, Color.RED);
            circle.setLayoutX(0.0);
            circle.setLayoutY(0.0);
            circle.setCenterX(xClicked);
            circle.setCenterY(yClicked);
            mainPane.getChildren().add(circle);
            if (topbar.bullets.removeBullet() == 0){
                gameOver = true;
                mainPane.setDisable(true);
                
                gameoverStage.show();
                
                Player currentPlayer = Player.getCurrentPlayer();
                try {
                    PlayerController.updatePlayerScore(currentPlayer, currentPlayer.getScore(), Player.getReturningPlayerStatus());
                    System.out.println("Your final score was " + currentPlayer.getScore());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    ScoreStage scoreStage = new ScoreStage();
                    Stage scStage = scoreStage.getStage();
                    scStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //This code was used for testing purposes
            // System.out.println("Main Pane clikx= " + xClicked + "Main Pane clicky= " + yClicked + "\nCircle centerx = " + circle.getCenterX() + "Circle Centery = " + circle.getCenterY() +"\n");
        });
        mainPane.requestFocus();

        return mainPane;
    }

    public static boolean getGameoverStatus(){
        return gameOver;
    }
}
