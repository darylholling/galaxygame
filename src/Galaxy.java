import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Galaxy {
//    Location[][] location;
//    int planetQuantity = 4;

    LocationService locationService = new LocationService();
    SpriteService spriteService = new SpriteService();
    Location[][] playfield;

    public void configure(Group root, GridPane gp){
        spriteService.setGridPane(gp);
        spriteService.setPlayfield(locationService.initalizePlayfield(gp));
        playfield = locationService.initalizePlayfield(gp);

        gp.setPrefSize(600, 600);
        gp.setStyle("-fx-background-image: url('wp1.jpg');");
        gp.setLayoutY(50);
//        locationService.initalizePlayfield(gp);
        spriteService.initializeSprites();

        root.getChildren().add(gp);
    }

//    public void start(Stage stage) throws Exception {
//        gp.setPrefSize(600, 600);
//        location = locationService.initalizePlayfield(gp);
        //timer

        // add spaceship

//        adds 4 planets at random locations
//        spriteService.addSprite(location, planetQuantity, "planet");

//        //adds 5 meteorites at random locations
//        spriteService.addSprite(location, 5, "meteorite");

//        gp.setStyle("-fx-background-image: url('wp1.jpg');");
//        gp.setLayoutY(50);
//        root.getChildren().add(gp);
//        Scene scene = new Scene(root, 600, 650);//move spaceship around with arrows
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent keyEvent) {
//                switch (keyEvent.getCode()) {
//                    case UP:
//                        Location up = spaceship.getLocation().getUp();
//
//                        if (up != null) {
//                            locationLogic(up, spaceship);
//                        }
//                        break;
//                    case DOWN:
//                        Location down = spaceship.getLocation().getDown();
//
//                        if (down != null) {
//                            locationLogic(down, spaceship);
//                        }
//                        break;
//                    case LEFT:
//                        Location left = spaceship.getLocation().getLeft();
//
//                        if (left != null) {
//                            locationLogic(left, spaceship);
//                        }
//                        break;
//                    case RIGHT:
//                        Location right = spaceship.getLocation().getRight();
//
//                        if (right != null) {
//                            locationLogic(right, spaceship);
//                        }
//                        break;
//                }
//            }
//        });

//        stage.setScene(scene);
//        stage.show();
//    }

//    private void locationLogic(Location location, Sprite sprite) {
//        if (location.hasPlanet()) {
//            visitPlanet((Spaceship) sprite, location);
//        } else if (location.hasMeteorite()) {
//            gp.getChildren().remove(sprite);
//            gp.setStyle("-fx-background-image: url('wp3.jpg');"); //@todo: proper game over
//            return;
//        } else if (location.hasWormhole()) {
//            visitWormhole(sprite);
//        }
//        move(sprite, location);
//    }
//
//    public void move(Sprite sprite, Location newLocation) {
//        sprite.setLocation(newLocation);
//        gp.setColumnIndex(sprite, newLocation.getColumn());
//        gp.setRowIndex(sprite, newLocation.getRow());
//    }
//
//
//    public void visitPlanet(Spaceship spaceship, Location location) {
//        Planet planet = (Planet) location.getSprite();
//        planet.setVisited(true);
//
//        spaceship.setPlanetsVisited(spaceship.getPlanetsVisited() + 1);
//
//        if (spaceship.getPlanetsVisited() == planetQuantity) {
//            spriteService.addSprite(this.location, 1, "wormhole");
//        }
//    }
//
//    public void visitWormhole(Sprite sprite) {
//        gp.getChildren().remove(sprite);
//        gp.setStyle("-fx-background-image: url('wp2.jpg');"); //@todo : final winner screen (scores maybe?)
//        timer.stop();
//    }
}