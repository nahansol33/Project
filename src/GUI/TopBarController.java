package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TopBarController {
    @FXML 
    Label playerLabel;

    public void displayPlayerName(String name){
        playerLabel.setText("Current Player: " + name);
    }
    
}

