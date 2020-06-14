import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

class Sprite extends Pane {
    protected Location location;

    public Sprite(String imageName) {
        Image image = new Image(imageName);
        ImageView iv = new ImageView(image);
        getChildren().add(iv);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
        this.location.setSprite(this);
    }
}