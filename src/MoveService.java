import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class MoveService {
    Spaceship spaceship;
    GridPane gridPane;
    SpriteService spriteService;
    Timer timer;
    Stage stage;

    public void configure(Stage stage, Scene scene, SpriteService spriteService, GridPane gridPane, Timer timer) {
        this.spaceship = spriteService.getSpaceship();
        this.gridPane = gridPane;
        this.spriteService = spriteService;
        this.timer = timer;
        this.stage = stage;

        this.initiateMoveListener(scene);
    }

    public void initiateMoveListener(Scene scene) {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                    case W:
                        Location up = spaceship.getLocation().getUp();

                        if (up != null) {
                            locationLogic(up, spaceship);
                        }
                        moveMeteorites();
                        if (up != null) {
                            check(up);
                        }
                        break;
                    case DOWN:
                    case S:
                        Location down = spaceship.getLocation().getDown();

                        if (down != null) {
                            locationLogic(down, spaceship);
                        }
                        moveMeteorites();
                        if (down != null) {
                            check(down);
                        }
                        break;
                    case LEFT:
                    case A:
                        Location left = spaceship.getLocation().getLeft();

                        if (left != null) {
                            locationLogic(left, spaceship);
                        }
                        moveMeteorites();
                        if (left != null) {
                            check(left);
                        }
                        break;
                    case RIGHT:
                    case D:
                        Location right = spaceship.getLocation().getRight();

                        if (right != null) {
                            locationLogic(right, spaceship);
                        }
                        moveMeteorites();
                        if (right != null) {
                            check(right);
                        }
                        break;
                }
            }
        });
    }

    public Location getRandom(Meteorite meteorite) {
        Random random = new Random();
        int number = random.nextInt(4);
        Location location = null;
        if (number == 0) {
            if (meteorite.getLocation().getUp() != null && meteorite.getLocation().getUp().getRow() >= 0 && !meteorite.getLocation().getUp().hasPlanet() && !meteorite.getLocation().getUp().hasWormhole()) {
                location = meteorite.getLocation().getUp();
            }
        }
        if (number == 1) {
            if (meteorite.getLocation().getRight() != null && meteorite.getLocation().getRight().getColumn() < 12 && !meteorite.getLocation().getRight().hasPlanet() && !meteorite.getLocation().getRight().hasWormhole()) {
                location = meteorite.getLocation().getRight();
            }
        }
        if (number == 2) {
            if (meteorite.getLocation().getDown() != null && meteorite.getLocation().getDown().getRow() < 12 && !meteorite.getLocation().getDown().hasPlanet() && !meteorite.getLocation().getDown().hasWormhole()) {
                location = meteorite.getLocation().getDown();
            }
        }
        if (number == 3) {
            if (meteorite.getLocation().getLeft() != null && meteorite.getLocation().getLeft().getColumn() >= 0 && !meteorite.getLocation().getLeft().hasPlanet() && !meteorite.getLocation().getLeft().hasWormhole()) {
                location = meteorite.getLocation().getLeft();
            }
        }

        return location;
    }

    private void moveMeteorites() {
        for (Meteorite meteorite : spriteService.getMeteorites()) {
            Location randomLocation = getRandom(meteorite);
            while (randomLocation == null || randomLocation.hasMeteorite()) {
                randomLocation = getRandom(meteorite);
            }
            move(meteorite, randomLocation);
        }
    }

    private void locationLogic(Location location, Sprite sprite) {
        if (location.hasPlanet()) {
            visitPlanet((Spaceship) sprite, location);
        } else if (location.hasMeteorite()) {
            this.updateScene(false);
        } else if (location.hasWormhole()) {
            visitWormhole();
        }

        move(sprite, location);
    }

    public void move(Sprite sprite, Location newLocation) {
        sprite.getLocation().removeSprite(sprite);
        sprite.setLocation(newLocation);
        gridPane.setColumnIndex(sprite, newLocation.getColumn());
        gridPane.setRowIndex(sprite, newLocation.getRow());
    }

    //to check if spaceship and meteorite move to the same location after keypress
    public void check(Location location) {
        if (location.hasMeteorite()) {
            this.updateScene(false);
        }
    }

    public void visitPlanet(Spaceship spaceship, Location location) {
        Planet planet = (Planet) location.getSprite();

        if (!planet.isVisited()) {
            planet.setVisited(true);
            spaceship.setPlanetsVisited(spaceship.getPlanetsVisited() + 1);
        }

        if (spaceship.getPlanetsVisited() == spriteService.planetQuantity && !this.spriteService.isWormholeInitialized()) {
            this.spriteService.addSprite(1, "wormhole");
        }
    }

    public void visitWormhole() {
        this.updateScene(true);
    }

    public void updateScene(Boolean winner) {
        VBox vBox = new VBox(20);

//        TODO find out why label position does not work
        timer.timerLabel.setLayoutY(400);
        timer.timerLabel.setLayoutX(250);
        timer.stop();

        if (winner) {
            vBox.setStyle("-fx-background-image: url('wp2.gif');");
        } else {
            vBox.setStyle("-fx-background-image: url('wp3.gif');");
        }

        vBox.getChildren().add(timer.timerLabel);

        Scene scene = new Scene(vBox, 600, 600);

        stage.setScene(scene);
    }
}

