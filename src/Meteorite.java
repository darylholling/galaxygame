import javafx.scene.layout.GridPane;

public class Meteorite extends Sprite implements MovableInterface {
    public Meteorite() {
        super("meteorite.png");
    }

    @Override
    public void move(Sprite sprite, Location location, MoveService moveService) {
        sprite.getLocation().removeSprite(sprite);
        sprite.setLocation(location);
        moveService.gridPane.setColumnIndex(sprite, location.getColumn());
        moveService.gridPane.setRowIndex(sprite, location.getRow());
    }
}
