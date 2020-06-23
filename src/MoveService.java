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
                        moveMeteorites();

                        if (up != null) {
                            moveSpaceShip(up, spaceship);
                        }
                        break;
                    case DOWN:
                    case S:
                        Location down = spaceship.getLocation().getDown();
                        moveMeteorites();

                        if (down != null) {
                            moveSpaceShip(down, spaceship);
                        }
                        break;
                    case LEFT:
                    case A:
                        Location left = spaceship.getLocation().getLeft();
                        moveMeteorites();

                        if (left != null) {
                            moveSpaceShip(left, spaceship);
                        }
                        break;
                    case RIGHT:
                    case D:
                        Location right = spaceship.getLocation().getRight();

                        moveMeteorites();

                        if (right != null) {
                            moveSpaceShip(right, spaceship);
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
            while (randomLocation == null || randomLocation.hasMeteorite() || randomLocation.hasPlanet()) {
                randomLocation = getRandom(meteorite);
            }

            if (randomLocation.hasSpaceship()) {
                this.updateScene(false);
            }

            meteorite.move(meteorite, randomLocation, this.gridPane);
        }
    }

    private void moveSpaceShip(Location location, Spaceship spaceship) {
        if (location.hasPlanet()) {
            visitPlanet(spaceship, location);
        } else if (location.hasMeteorite()) {
            this.updateScene(false);
        } else if (location.hasWormhole()) {
            visitWormhole();
        }

        spaceship.move(spaceship, location, this.gridPane);
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

        //TODO fix timerlabel location, perhaps with a new thingy.
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

