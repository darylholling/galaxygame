import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class SpriteService {
    public int planetQuantity = 4;
    private GridPane gridPane;
    private Location[][] playfield;
    public Spaceship spaceship = new Spaceship();
    private ArrayList<Meteorite> meteorites = new ArrayList<>();
    private Boolean wormholeInitialized = false;

    public void configure(GridPane gridPane, Location[][] playfield) {
        this.playfield = playfield;
        this.gridPane = gridPane;

        this.initializeSprites();
    }

    public Spaceship getSpaceship() {
        return this.spaceship;
    }

    public ArrayList<Meteorite> getMeteorites() {
        return this.meteorites;
    }

    public Boolean isWormholeInitialized() {
        return this.wormholeInitialized;
    }

    public void initializeSprites() {
        this.initializeSpaceShip();
        this.addSprite(planetQuantity, "planet");
        this.addSprite(5, "meteorite");
    }

    private void initializeSpaceShip() {
        spaceship.setLocation(playfield[0][0]);
        this.gridPane.add(spaceship, spaceship.getLocation().getColumn(), spaceship.getLocation().getRow());
    }

    public void addSprite(int amount, String string) {
        int i = 0;
        while (i < amount) {
            int column = (int) Math.floor(Math.random() * 12);
            int row = (int) Math.floor(Math.random() * 12);

            if (!this.playfield[column][row].hasSprite()) {
                Sprite anySprite;
                switch (string) {
                    case "meteorite":
                        anySprite = new Meteorite();
                        meteorites.add((Meteorite) anySprite);
                        break;
                    case "planet":
                        anySprite = new Planet();
                        break;
                    case "wormhole":
                        anySprite = new Wormhole();
                        wormholeInitialized = true;
                        break;
                    default:
                        System.out.println("invalid input");
                        return;
                }

                anySprite.setLocation(this.playfield[column][row]);
                this.gridPane.add(anySprite, anySprite.getLocation().getColumn(), anySprite.getLocation().getRow());

                i++;
            }
        }
    }
}
