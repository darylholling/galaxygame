import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

abstract public class Sprite extends ImageView {
    public Sprite() {
    }

    public Sprite(String url) {
        super(url);
    }

    public Sprite(Image image) {
        super(image);
    }
}
