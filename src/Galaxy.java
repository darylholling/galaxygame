import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Galaxy extends Application {
    GridPane gp;
    int planetQuantity = 4;
    Location[][] location;

    @Override
    public void start(Stage stage) throws Exception {
        gp = new GridPane();
        gp.setPrefSize(600, 600);
        LocationService locationService = new LocationService();
        location = locationService.initalize(gp);

        // add spaceship
        Spaceship spaceship = new Spaceship();
        spaceship.setLocation(location[0][0]);
        gp.add(spaceship, spaceship.getLocation().getColumn(), spaceship.getLocation().getRow());

//        adds 4 planets at random locations
        addSprite(location, planetQuantity, "planet");

//        //adds 5 meteorites at random locations
        addSprite(location, 5, "meteorite");

        gp.setStyle("-fx-background-image: url('wp1.jpg');");

        Scene scene = new Scene(gp, 600, 600);
        stage.setScene(scene);
        stage.show();
        //move spaceship around with arrows
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        Location up = spaceship.getLocation().getUp();

                        if (up != null) {
                            locationLogic(up, spaceship);
                        }
                        break;
                    case DOWN:
                        Location down = spaceship.getLocation().getDown();

                        if (down != null) {
                            locationLogic(down, spaceship);
                        }
                        break;
                    case LEFT:
                        Location left = spaceship.getLocation().getLeft();

                        if (left != null) {
                            locationLogic(left, spaceship);
                        }
                        break;
                    case RIGHT:
                        Location right = spaceship.getLocation().getRight();

                        if (right != null) {
                            locationLogic(right, spaceship);
                        }
                        break;
                }
            }
        });
    }

    private void locationLogic(Location location, Sprite sprite) {
        if (location.hasPlanet()) {
            visitPlanet((Spaceship) sprite, location);
        } else if (location.hasMeteorite()) {
            System.out.println("DEAD"); //@todo: game over
            return;
        }
        else if (location.hasWormhole()) {
            visitWormhole();
        }
        move(sprite, location);
    }

    public void move(Sprite sprite, Location newLocation) {
        sprite.setLocation(newLocation);
        gp.setColumnIndex(sprite, newLocation.getColumn());
        gp.setRowIndex(sprite, newLocation.getRow());
    }

    public void visitPlanet(Spaceship spaceship, Location pLocation) {
            ImageView image = new ImageView("planetvisited.png");
//        image.toBack();
            gp.add(image, pLocation.getColumn(), pLocation.getRow());
            spaceship.setPlanetsVisited(spaceship.getPlanetsVisited() +1);
            if (spaceship.getPlanetsVisited() == planetQuantity) {
            addSprite( location, 1, "wormhole");
        }
    }
    public void visitWormhole() {
        System.out.println("Winner!"); //@todo : final winner screen (scores maybe?)
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
                gp.add(anySprite, anySprite.getLocation().getColumn(), anySprite.getLocation().getRow());

                i++;
            }
        }
    }
}