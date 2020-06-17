import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Game extends Application {
    GridPane gp = new GridPane();
    Galaxy galaxy = new Galaxy();
    Group root = new Group();
    Timer timer = new Timer(root);
    Spaceship spaceship = galaxy.spriteService.spaceship;

    public void start(Stage stage) throws Exception {
        galaxy.configure(root, gp);
        timer.start();

        Scene scene = new Scene(root, 600, 650);//move spaceship around with arrows
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        Location up = spaceship.getLocation().getUp();

                        if (up != null) {
                            locationLogic(up, spaceship);
                        }
                        moveMeteorites();
                        break;
                    case DOWN:
                        Location down = spaceship.getLocation().getDown();

                        if (down != null) {
                            locationLogic(down, spaceship);
                        }
                        moveMeteorites();
                        break;
                    case LEFT:
                        Location left = spaceship.getLocation().getLeft();

                        if (left != null) {
                            locationLogic(left, spaceship);
                        }
                        moveMeteorites();
                        break;
                    case RIGHT:
                        Location right = spaceship.getLocation().getRight();

                        if (right != null) {
                            locationLogic(right, spaceship);
                        }
                        moveMeteorites();
                        break;
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    private void moveMeteorites(){
        this.galaxy.spriteService.meteorites.forEach((meteorite) ->
                move(meteorite, meteorite.getLocation().getRight())
        );
    }

    private void locationLogic(Location location, Sprite sprite) {
        System.out.println("ll");

        if (location.hasPlanet()) {
            visitPlanet((Spaceship) sprite, location);
        } else if (location.hasMeteorite()) {
            gp.getChildren().remove(sprite);
            gp.setStyle("-fx-background-image: url('wp3.jpg');"); //@todo: proper game over
            return;
        } else if (location.hasWormhole()) {
            System.out.println("wormhole");
            visitWormhole(sprite);
        }
        System.out.println("nottinhg");

        move(sprite, location);
    }

    public void move(Sprite sprite, Location newLocation) {
        sprite.setLocation(newLocation);
        gp.setColumnIndex(sprite, newLocation.getColumn());
        gp.setRowIndex(sprite, newLocation.getRow());
    }


    public void visitPlanet(Spaceship spaceship, Location location) {
        Planet planet = (Planet) location.getSprite();
        planet.setVisited(true);

        spaceship.setPlanetsVisited(spaceship.getPlanetsVisited() + 1);

        if (spaceship.getPlanetsVisited() == 4) {
            galaxy.spriteService.addSprite(galaxy.playfield, 1, "wormhole");
        }
    }

    public void visitWormhole(Sprite sprite) {
        gp.getChildren().remove(sprite);
        gp.setStyle("-fx-background-image: url('wp2.jpg');"); //@todo : final winner screen (scores maybe?)
        timer.stop();
    }
}
