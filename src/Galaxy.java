import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Galaxy {
//    Location[][] location;
//    int planetQuantity = 4;

    LocationService locationService = new LocationService();
    SpriteService spriteService = new SpriteService();
    Location[][] playfield;

    public void configure(Group root, GridPane gp){
        spriteService.setGridPane(gp);
        playfield = locationService.initalizePlayfield(gp);
        spriteService.setPlayfield(playfield);

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
    Scene scene1, scene2, scene3;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Galaxy The Game!");

        Button startGame = new Button("Start the game!");
        Button highScores = new Button("View highscores");
        Button exitGame = new Button("Quit game");
        startGame.setOnAction(e->stage.setScene(scene2));
//        highScores.setOnAction(e);
        exitGame.setOnAction(e->stage.close());

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(startGame,highScores,exitGame);

        layout1.setStyle("-fx-background-image: url('Space2.png');");
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1,600, 600);

        gp = new GridPane();
        gp.setPrefSize(600, 600);
        LocationService locationService = new LocationService();
        location = locationService.initalize(gp);

        // add spaceship

//        adds 4 planets at random locations
//        spriteService.addSprite(location, planetQuantity, "planet");

//        //adds 5 meteorites at random locations
        addSprite(location, 5, "meteorite");

        gp.setStyle("-fx-background-image: url('wp1.jpg');");

        Button button3 = new Button("Easy");
        button3.setOnAction(e->stage.setScene(scene3));
        Button button4 = new Button("Hard");
//        button4.setOnAction(e->stage.setScene(scene2));
        Button button5 = new Button("Very Hard");
//    button1.setOnAction(e->stage.setScene(scene2));
        Button button6 = new Button("Go back");
        button6.setOnAction(e->stage.setScene(scene1));
//
        button3.setMaxWidth(120);
        button4.setMaxWidth(120);
        button5.setMaxWidth(120);
        button6.setMaxWidth(120);

        VBox layout2 = new VBox(20);
        layout2.setAlignment(Pos.CENTER);




        layout2.getChildren().addAll(button3, button4, button5, button6);

        scene2 = new Scene(layout2, 600, 600);
        layout2.setStyle("-fx-background-image: url('galaxy-menu.png');");
        scene3 = new Scene(gp, 600, 600);
        stage.setScene(scene1);
        stage.show();


        //move spaceship around with arrows
        scene3.setOnKeyPressed(new EventHandler<KeyEvent>() {
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