import javafx.scene.layout.GridPane;

public class Spaceship extends Sprite implements MovableInterface {
    private Integer planetsVisited = 0;

    public Spaceship() {
        super("spaceship.gif");
    }

    public Integer getPlanetsVisited() {
        return planetsVisited;
    }

    public void setPlanetsVisited(Integer planetsVisited) {
        this.planetsVisited = planetsVisited;
    }

    @Override
    public void move(Sprite sprite, Location location, GridPane gridPane) {
        sprite.getLocation().removeSprite(sprite);
        sprite.setLocation(location);
        gridPane.setColumnIndex(sprite, location.getColumn());
        gridPane.setRowIndex(sprite, location.getRow());
    }
}
