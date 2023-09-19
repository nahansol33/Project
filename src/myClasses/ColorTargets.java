package myClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.animation.PathTransition;


public enum ColorTargets implements Transitions{
    
    BlueTarget("file:images/blue_target.png", 50) {
        @Override
        public PathTransition getPathTransition() {
            return Transitions.circleTransition();
        }
        
    },
    GreenTarget("file:images/green_target.png", -100) {
        @Override
        public PathTransition getPathTransition() {
            return Transitions.arcsTransition();
        }
    },
    PinkTarget("file:images/pink_target.png", 200) {
        @Override
        public PathTransition getPathTransition() {
            return Transitions.randomTransition();
        }
    },
    RedTarget("file:images/red_target.png", 75) {
        @Override
        public PathTransition getPathTransition() {
            return Transitions.rectangleTransition();
        }
    },
    YellowTarget("file:images/yellow_target.png", 25) {
        @Override
        public PathTransition getPathTransition() {
            return Transitions.lineTransition();
        }
    };

    private final String url;
    private final int score;

    ColorTargets(String url, int score) {
        this.url = url;
        this.score = score;
    }

    public String getUrl() {
        return url;
    }

    public int getTargetScore(){
        return score;
    }

}