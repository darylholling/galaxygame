import javafx.scene.layout.GridPane;

public class Meteorite extends Sprite implements MovableInterface {
    public Meteorite() {
        super("meteorite.png");
    }

    @Override
    public void move(Sprite sprite, Location location, GridPane gridPane) {
        sprite.getLocation().removeSprite(sprite);
        sprite.setLocation(location);
        gridPane.setColumnIndex(sprite, location.getColumn());
        gridPane.setRowIndex(sprite, location.getRow());
    }
}
