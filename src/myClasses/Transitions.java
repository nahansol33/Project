package myClasses;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public interface Transitions {

    public static PathTransition rectangleTransition() {
        PathTransition transition = new PathTransition();
        transition.setPath(new Rectangle(300, 150));
        transition.setCycleCount(PathTransition.INDEFINITE);
        return transition;
    }

    public static PathTransition circleTransition() {
        PathTransition transition = new PathTransition();
        transition.setPath(new Circle(100));
        transition.setCycleCount(PathTransition.INDEFINITE);
        return transition;
    }
    
    public static PathTransition arcsTransition() {
        PathTransition transition = new PathTransition();
        transition.setPath(new Arc(0, 0, 100, 100, 0, 180));
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.setAutoReverse(true);
        return transition;
    }

    public static PathTransition lineTransition() {
        PathTransition transition = new PathTransition();
        transition.setPath(new Line(Targets.randPositionX(), Targets.randPositionY(), Targets.randPositionX(), Targets.randPositionY()));
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.setAutoReverse(true);
        return transition;
    }

    public static PathTransition randomTransition(){
        Path path = new Path();
        path.getElements().add(new MoveTo(Targets.randPositionX(), Targets.randPositionY()));
        path.getElements().add(new CubicCurveTo(Targets.randPositionX(), Targets.randPositionY(), Targets.randPositionX(), Targets.randPositionY(), Targets.randPositionX(), Targets.randPositionY()));
        path.getElements().add(new CubicCurveTo(Targets.randPositionX(), Targets.randPositionY(), Targets.randPositionX(), Targets.randPositionY(), Targets.randPositionX(), Targets.randPositionY()));
        PathTransition transition = new PathTransition();
        transition.setPath(path);
        transition.setCycleCount(PathTransition.INDEFINITE);
        return transition;
    }

    public static ParallelTransition fadeRectangleTransition(Targets t1){
        RotateTransition rotate =  new RotateTransition(Duration.millis(5500), t1);
        rotate.setByAngle(270f);
        rotate.setCycleCount(3);
        rotate.setAutoReverse(true);
        rotate.play();

        TranslateTransition tr = new TranslateTransition(Duration.seconds(5), t1);
        tr.setAutoReverse(true);
        tr.setCycleCount(TranslateTransition.INDEFINITE);
        tr.setFromX(100);
        tr.setToX(400);
        tr.setFromY(100);
        tr.setFromY(400);
        tr.play();

        ParallelTransition pr = new ParallelTransition();
        pr.getChildren().addAll(rotate, tr);
        pr.setAutoReverse(false);
        pr.setCycleCount(ParallelTransition.INDEFINITE);
        pr.play();
        return pr;
    }
    
    public static ParallelTransition lineParallelTransition(Targets t1){
        RotateTransition rotate =  new RotateTransition(Duration.millis(5500), t1);
        rotate.setByAngle(360f);
        rotate.setCycleCount(3);
        rotate.setAutoReverse(true);
        rotate.play();

        ScaleTransition scale = new ScaleTransition(Duration.millis(5000), t1);
        scale.setToX(0.75);
        scale.setToY(2);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);
        scale.play();

        ParallelTransition para = new ParallelTransition();
        para.getChildren().addAll(rotate, scale);
        para.setAutoReverse(true);
        para.setCycleCount(ParallelTransition.INDEFINITE);
        para.play();
        return para;
    }

    public abstract Transition getPathTransition();

}





