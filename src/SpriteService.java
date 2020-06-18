import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class SpriteService {
    GridPane gridPane;
    Location[][] playfield;
    int planetQuantity = 4;
    Spaceship spaceship = new Spaceship();
    ArrayList<Meteorite> meteorites = new ArrayList<>();

//    public SpriteService(GridPane gridPane, Location[][] playfield) {
//        this.gridPane = gridPane;
//        this.playfield = playfield;
//    }

    public void setGridPane(GridPane gridPane){
        this.gridPane = gridPane;
    }

    public void setPlayfield(Location[][] playfield){
        this.playfield = playfield;
    }

    public void initializeSprites(){
        this.initializeSpaceShip();
        this.addSprite(playfield, planetQuantity, "planet");
        this.addSprite(playfield, 1, "meteorite");
    }

    public void initializeSpaceShip(){
        spaceship.setLocation(playfield[0][0]);
        this.gridPane.add(spaceship, spaceship.getLocation().getColumn(), spaceship.getLocation().getRow());
    }

    public void addSprite(Location[][] location, int amount, String string) {
        int i = 0;
        while (i < amount) {
            int column = (int) Math.floor(Math.random() * 12);
            int row = (int) Math.floor(Math.random() * 12);

            if (!location[column][row].hasSprite()) {
                Sprite anySprite;
                switch (string) {
                    case "meteorite":
                        anySprite = new Meteorite();
                        meteorites.add((Meteorite)anySprite);
                        break;
                    case "planet":
                        anySprite = new Planet();
                        break;
                    case "wormhole":
                        anySprite = new Wormhole();
                        break;
                    default:
                        //@todo handle exception maybe??
                        System.out.println("invalid input");
                        return;
                }

                anySprite.setLocation(location[column][row]);
                this.gridPane.add(anySprite, anySprite.getLocation().getColumn(), anySprite.getLocation().getRow());

                i++;
            }
        }
    }
}
