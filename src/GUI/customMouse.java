package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class customMouse {
    Image cursorImage;
    public ImageCursor customCursor;
    //creating a custom image cursor.
    public customMouse(){
        cursorImage = new Image("file:images/red_cursor Background Removed.png", 30, 30, true, true);
        double xCor = cursorImage.getWidth() / 2;
        double yCor = cursorImage.getHeight() / 2;
        customCursor = new ImageCursor(cursorImage, xCor, yCor);
    }
    

}
