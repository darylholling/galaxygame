import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Location extends Pane {
    private Location up;
    private Location right;
    private Location down;
    private Location left;

    private int column;
    private int row;

    private ArrayList<Sprite> sprites = new ArrayList<>();

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
        return !sprites.isEmpty();
    }

    public boolean hasPlanet() {
        if (this.sprites.isEmpty()) {
            return false;
        }

        if (sprites.size() > 1) {
            for (Sprite sprite : sprites) {
                if (sprite instanceof Planet) {
                    return true;
                }
            }

            return false;
        }

        return sprites.get(0) instanceof Planet;
    }

    public boolean hasMeteorite() {
        if (this.sprites.isEmpty()) {
            return false;
        }

        if (sprites.size() > 1) {
            for (Sprite sprite : sprites) {
                if (sprite instanceof Meteorite) {
                    return true;
                }
            }

            return false;
        }

        return sprites.get(0) instanceof Meteorite;
    }

    public boolean hasWormhole() {
        if (this.sprites.isEmpty()) {
            return false;
        }

        if (sprites.size() > 1) {
            for (Sprite sprite : sprites) {
                if (sprite instanceof Wormhole) {
                    return true;
                }
            }

            return false;
        }

        return sprites.get(0) instanceof Wormhole;
    }

    public boolean hasSpaceship() {
        if (this.sprites.isEmpty()) {
            return false;
        }

        if (sprites.size() > 1) {
            for (Sprite sprite : sprites) {
                if (sprite instanceof Spaceship) {
                    return true;
                }
            }

            return false;
        }

        return sprites.get(0) instanceof Spaceship;
    }

    public Sprite getSprite() {
        return sprites.get(0);
    }

    public ArrayList<Sprite> getSprites() {
        return sprites;
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }
}
