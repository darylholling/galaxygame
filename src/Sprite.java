import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

abstract class Sprite extends Pane {
    protected Location location;

    ImageView image;

    public Sprite(String imageName) {
        Image image = new Image(imageName);
        this.setImage(new ImageView(image));
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
        this.location.addSprite(this);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
        getChildren().add(this.image);
    }
}