import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.nio.file.attribute.UserPrincipal;
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
        menu.initalize(stage, gp, scene);

        galaxy.configure(root, gp);
        timer.start();
//        System.out.println(Arrays.asList(galaxy.spriteService.playfield));
//        System.out.println(Arrays.deepToString(galaxy.spriteService.playfield));

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        Location up = spaceship.getLocation().getUp();

                        if (up != null) {
                            locationLogic(up, spaceship);
                        }
                        locationMeteoriteLogic();
                        if (up != null) {
                            check(spaceship, up);
                        }
                        break;
                    case DOWN:
                        Location down = spaceship.getLocation().getDown();

                        if (down != null) {
                            locationLogic(down, spaceship);
                        }
                        locationMeteoriteLogic();
                        if (down != null) {
                            check(spaceship, down);
                        }
                        break;
                    case LEFT:
                        Location left = spaceship.getLocation().getLeft();

                        if (left != null) {
                            locationLogic(left, spaceship);
                        }
                        locationMeteoriteLogic();
                        if (left != null) {
                            check(spaceship, left);
                        }
                        break;
                    case RIGHT:
                        Location right = spaceship.getLocation().getRight();

                        if (right != null) {
                            locationLogic(right, spaceship);
                        }
                        locationMeteoriteLogic();
                        if (right != null) {
                            check(spaceship, right);
                        }
                        break;
                }
            }
        });
        stage.setScene(menu.scene1);
        stage.show();
    }

//    private void moveMeteorites(){
//        this.galaxy.spriteService.meteorites.forEach((meteorite) ->
//                for (Meteorite  meteorite : this.galaxy.spriteService.meteorites) {
//                    Location randomLocation = getRandom(meteorite);
//                    if (randomLocation != null) {
//                        move(meteorite, randomLocation);
//                    }
//                }
//        );
//    }

    public Location getRandom(Meteorite meteorite){
        Random random = new Random();
        int number = random.nextInt(4);
                Location loc = null;
        if (number==0) {
            if (meteorite.getLocation().getUp() != null && meteorite.getLocation().getUp().getRow() >= 0 && !meteorite.getLocation().getUp().hasPlanet() && !meteorite.getLocation().getUp().hasWormhole()) {
                loc = meteorite.getLocation().getUp();
            }
        }
        if (number == 1) {
            if (meteorite.getLocation().getRight() != null && meteorite.getLocation().getRight().getColumn() < 12 && !meteorite.getLocation().getRight().hasPlanet() && !meteorite.getLocation().getRight().hasWormhole()) {
                loc = meteorite.getLocation().getRight();
            }
        }
            if (number == 2) {
                if (meteorite.getLocation().getDown() != null && meteorite.getLocation().getDown().getRow() < 12 && !meteorite.getLocation().getDown().hasPlanet() && !meteorite.getLocation().getDown().hasWormhole()) {
                    loc = meteorite.getLocation().getDown();
                }
            }
            if (number == 3) {
                if (meteorite.getLocation().getLeft() != null && meteorite.getLocation().getLeft().getColumn() >= 0 && !meteorite.getLocation().getLeft().hasPlanet() && !meteorite.getLocation().getLeft().hasWormhole()) {
                    loc = meteorite.getLocation().getLeft();
                }
            }

        return loc;
    }

    private void locationMeteoriteLogic() {
        for (Meteorite meteorite : this.galaxy.spriteService.meteorites) {
            Location randomLocation = getRandom(meteorite);
//            System.out.println(randomLocation.hasMeteorite());
//            System.out.println(randomLocation.hasPlanet());
//            System.out.println(randomLocation);
            while (randomLocation == null) {
                randomLocation = getRandom(meteorite);
//                System.out.println(randomLocation);
            }
                move(meteorite, randomLocation);
        }
    }


    private void locationLogic(Location location, Sprite sprite) {
        if (location.hasPlanet()) {
            visitPlanet((Spaceship) sprite, location);
        }
        else if (location.hasMeteorite()) {
            gp.getChildren().remove(sprite);
            gp.setStyle("-fx-background-image: url('wp3.jpg');"); //@todo: proper game over
            timer.stop();
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
        sprite.getLocation().setSprite(null);
        sprite.setLocation(newLocation);
        gp.setColumnIndex(sprite, newLocation.getColumn());
        gp.setRowIndex(sprite, newLocation.getRow());
    }
//to check if spaceship and meteorite move to the same location after keypress
    public void check(Sprite sprite, Location location){
        if(location.hasMeteorite() ){
            gp.getChildren().remove(sprite);
            gp.setStyle("-fx-background-image: url('wp3.jpg');"); //@todo: proper game over
            timer.stop();
        }
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
