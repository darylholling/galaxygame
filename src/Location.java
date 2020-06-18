import javafx.scene.layout.Pane;

public class Location extends Pane {
    private Location up;
    private Location right;
    private Location down;
    private Location left;

    private int column;
    private int row;

    private Sprite sprite;

    public Location(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public Location getRight() {
        return right;
    }

    public void setRight(Location right) {
        this.right = right;
    }

    public Location getUp() {
        return up;
    }

    public void setUp(Location up) {
        this.up = up;
    }

    public Location getDown() {
        return down;
    }

    public void setDown(Location down) {
        this.down = down;
    }

    public Location getLeft() {
        return left;
    }

    public void setLeft(Location left) {
        this.left = left;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean hasSprite() {
        return sprite != null;
    }
    public boolean hasPlanet() {
        return sprite instanceof Planet;
    }
    public boolean hasMeteorite() {
        return sprite instanceof Meteorite;
    }
    public boolean hasWormhole() {
        return sprite instanceof Wormhole;
    }
    public boolean hasSpaceship() {
        return sprite instanceof Spaceship;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

//    @Override
//    public String toString() {
//        return this.hasSprite() + "";
//    }
}
