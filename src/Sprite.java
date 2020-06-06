import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Collection;

class Sprite extends Pane {
    public Sprite(String imageName) {
        Image image = new Image(imageName);
        ImageView iv = new ImageView(image);
        getChildren().add(iv);
    }
}