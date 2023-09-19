package myClasses;

import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Targets extends Pane{

    private static double speed = 4;
    private static int level = 1;
    private StackPane pane;
    private Image target;
    private ImageView viewTarget;
    private PathTransition path;
    public static ArrayList<Targets> targetList = new ArrayList<>();
    private static double clickThreshold = 20.0;
    private String turl;
    private static int AccumScore;
    private int score;
    private static int targetNumber = 1;


    public Targets(){
        pane = new StackPane();
        // pane.setStyle("-fx-background-color: black;");
        pane.setMinSize(40.0, 40.0);
        pane.setMaxSize(50, 50.0);
        ColorTargets colorOfTarget = ColorTargets.values()[randTarget()];
        score = colorOfTarget.getTargetScore();
        turl = colorOfTarget.getUrl();
        path = (PathTransition) colorOfTarget.getPathTransition();
    
        target = new Image(turl, 50, 50, true, true);
        viewTarget = new ImageView(target);
        Label label = new Label("Target" + targetNumber);
        pane.getChildren().addAll(viewTarget, label);
        targetNumber+=1;

        //Setting the position of the targets to random coordinates.
        pane.relocate(randPositionX(), randPositionY());
        getChildren().add(pane);

        setMouseTransparent(false); 

        targetList.add(this); // Add the instance to targetList

        if (level >=3){
            if (checkYellow()){
                ParallelTransition para = Transitions.fadeRectangleTransition(this);
                para.setNode(pane);
                para.setCycleCount(ParallelTransition.INDEFINITE);
                para.play();
                score += 100;
            }

            else {
                path.setNode(pane);
                path.setDuration(Duration.seconds(speed));
            }
        }

        else if(level >=2){
            if (checkBlue()){
                ParallelTransition para = Transitions.lineParallelTransition(this);
                // para.setNode(pane);
                para.setCycleCount(ParallelTransition.INDEFINITE);
                para.play();
                score += 100;
            }
            else {
                path.setNode(pane);
                path.setDuration(Duration.seconds(speed));
            }
        }

        else{
            path.setNode(pane);
            path.setDuration(Duration.seconds(speed));
        }
       

        this.getPath().play();
    
        checkGreen();
        
        setOnMouseClicked(event -> {
            // targetClicked = false;
            double clickX = event.getX();
            double clickY = event.getY();
            double targetCenterX = pane.localToScene(pane.getWidth() / 2, pane.getHeight() / 2).getX();
            double targetCenterY = pane.localToScene(pane.getWidth() / 2, pane.getHeight() / 2).getY();
            double distance = Math.sqrt(Math.pow(clickX - targetCenterX, 2) + Math.pow(clickY - targetCenterY, 2));
            //this was used for testing purposes
            // System.out.println("clikx= " + clickX + "clicky= " + clickY + "\ntargetcenterx = " + targetCenterX + "targetCentery = " + targetCenterY +"\n");

            if (distance <= clickThreshold) {
                AccumScore += score;
                Pane parentPane = (Pane) getParent();
                parentPane.getChildren().remove(this);
                targetList.remove(this);
                addTarget(parentPane);
                // parentPane.getChildren().add(new Targets());
                event.consume();
            }
        });
       if (AccumScore >= 500 && level == 1){
        levelUp();
       }
       if (AccumScore >= 1500 && level == 2){
        levelUp();
       }
       if (AccumScore >= 2500 && level ==3){
        levelUp();
       }
       if (AccumScore >= 3500 && level==4){
        levelUp();
       }
    }

    public static void addTarget(Pane pane){
        pane.getChildren().add(new Targets());
    }

    public static int randPositionY(){
        return (int) (100 + Math.random()*300);
    }

    public static int randPositionX(){
        return (int) (100 + Math.random()*300);
    }

    public static int randTarget(){
        return (int) (Math.random()*5);
    }

    public PathTransition getPath(){
        return this.path;
    }

    public void checkGreen(){
        if ((this.turl).equals("file:images/green_target.png")){
            PauseTransition delay = new PauseTransition(Duration.seconds(5.0));
            delay.setOnFinished(event -> {
                Pane parentPane = (Pane) getParent();
                parentPane.getChildren().remove(this);
                targetList.remove(this);
                addTarget(parentPane);
            });
            delay.play();
        }
        AccumScore += 50;
    }

    public boolean checkBlue(){
        if ((this.turl).equals("file:images/blue_target.png")){
            return true;
        }
        else return false;
    }

    public String getTurl(){
        return this.turl;
    }

    public boolean checkYellow(){
        if ((this.turl).equals("file:images/yellow_target.png")){
            return true;
        }
        else return false;
    }

    public static int getScore(){
        return AccumScore;
    }

    public Pane getPane(){
        return pane;
    }

    public void levelUp(){
        level += 1;
        speed -=1.5;
    }

    public static int getLevel(){
        return level;
    }

    public static double getThreshold(){
        return clickThreshold;
    }

    public int getTargetPoints(){
        return score;
    }

    public void updateAccumScore(int score){
        AccumScore += score;
    }
    public void removeTargetFromPane() {
        Pane parentPane = (Pane) getParent();
        parentPane.getChildren().remove(this);
        Targets.targetList.remove(this);
    }

}
