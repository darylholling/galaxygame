import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;

public class Game extends Application {
    GridPane gp = new GridPane();
    Galaxy galaxy = new Galaxy();
    Group root = new Group();
    Timer timer = new Timer(root);
    Spaceship spaceship = galaxy.spriteService.spaceship;
    Menu menu = new Menu();

    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(root, 600, 650);//move spaceship around with arrows
//        menu.initalize(stage, gp, scene);

        galaxy.configure(root, gp);
        timer.start();
//        System.out.println(Arrays.asList(galaxy.spriteService.playfield));
        System.out.println(Arrays.deepToString(galaxy.spriteService.playfield));

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
                move(meteorite, getRandom(meteorite))
        );
    }

    public Location getRandom(Meteorite meteorite){
        Random random = new Random();
        int number = random.nextInt(4);

        if (number == 0) {
            if (meteorite.getLocation().getUp() != null && !meteorite.getLocation().getUp().hasSprite()) {
                return meteorite.getLocation().getUp();
            }
        }

        if (number == 1) {
            if (meteorite.getLocation().getRight() != null && !meteorite.getLocation().getRight().hasSprite()) {
                return meteorite.getLocation().getRight();
            }
        }

        if (number == 2) {
            if (meteorite.getLocation().getDown() != null && !meteorite.getLocation().getDown().hasSprite()) {
                return meteorite.getLocation().getDown();
            }
        }

        if (number == 3) {
            if (meteorite.getLocation().getLeft() != null && !meteorite.getLocation().getLeft().hasSprite()) {
                return meteorite.getLocation().getLeft();
            }
        }

        return null;
//        return this.getRandom(meteorite);
    }

    private void locationMeteoriteLogic(Location location, Sprite sprite) {
        if (location.hasPlanet()) {
            return;
        } else if (location.hasMeteorite()) {
            return;
        } else if (location.hasWormhole()) {
            return;
        }

        move(sprite, location);
    }

    private void locationLogic(Location location, Sprite sprite) {
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

        move(sprite, location);
    }

    public void move(Sprite sprite, Location newLocation) {
//        System.out.println(newLocation.getColumn());
//        System.out.println(newLocation.getRow());
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
