package GUI;

import javafx.scene.layout.HBox;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class BulletsIcon extends HBox{
    HBox hbox;
    static ArrayList<ImageView> bulletArray = new ArrayList<>();
    Image bulletImage = new Image("file:images/bullet1 Background Removed.png", 30, 80, true, true);

    BulletsIcon(int numberOfBullets){
        hbox = new HBox();
        hbox.setSpacing(3);
        Label bulletLabel = new Label("Bullets left: ");
        bulletLabel.setTextFill(Color.WHITE);
        bulletLabel.setMaxWidth(333);

        hbox.getChildren().add(bulletLabel);

        // updateBulletsIcon(numberOfBullets, bulletImage);

        for (int i=0; i<10; i++){
            ImageView img = new ImageView(bulletImage);
            bulletArray.add(img);
        }

        for (int i=0; i < bulletArray.size(); i++){
            hbox.getChildren().addAll(bulletArray.get(i));
        }
        getChildren().add(hbox);
    }

    public int removeBullet(){
        hbox.getChildren().remove(bulletArray.get(0));
        bulletArray.remove(0);
        return bulletArray.size();
    }

    public int getBulletArraySize(){
        return bulletArray.size();
    }



}
