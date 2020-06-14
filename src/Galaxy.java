import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Galaxy extends Application {
    GridPane gp;
    int planetScore = 0;
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
        addObject(location, 4, "planet");

//        //adds 5 meteorites at random locations
        addObject(location,5, "meteorite");


        gp.setStyle("-fx-background-image: url('wp1.jpg');");

        Scene scene = new Scene(gp, 600, 600);
        stage.setScene(scene);
        stage.show();
        //move spaceship around with arrows
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch(keyEvent.getCode()){
                    case UP: moveUp(spaceship, spaceship.getLocation().getUp().getColumn(),spaceship.getLocation().getUp().getRow()); break;
                    case DOWN: moveDown(spaceship, spaceship.getLocation().getDown().getColumn(),spaceship.getLocation().getDown().getRow()); break;
                    case LEFT: moveLeft(spaceship, spaceship.getLocation().getLeft().getColumn(),spaceship.getLocation().getLeft().getRow()); break;
                    case RIGHT: moveRight(spaceship, spaceship.getLocation().getRight().getColumn(),spaceship.getLocation().getRight().getRow()); break;
                }
            }
        });
    }

    //move spaceship around with arrows

    public void move(Sprite sprite, Location[][] location, int column, int row){
        sprite.setLocation(location[column][row]);
        gp.setColumnIndex(sprite, column);
        gp.setRowIndex(sprite, row);

//        gp.add(sprite, sprite.getLocation().getColumn(), sprite.getLocation().getColumn());
    }
    public void moveUp(Sprite sprite, int column , int row) {
        int rowCheck = (Math.max(row, 0));
        if (location[column][rowCheck].hasPlanet() && row > 0){
            visitPlanet(sprite, column, row);}
        else if (location[column][rowCheck].hasMeteorite()) {
            System.out.println("DEAD"); //@todo: gameover
        } else {
            move(sprite, location, column, row);
        }
    }
    public void moveDown(Sprite sprite, int column, int row) {
        int rowCheck = (Math.min(row, 11));
        if (location[column][rowCheck].hasPlanet() && row < 12){
            visitPlanet(sprite, column, row);}
        else if (location[column][rowCheck].hasMeteorite()) {
            System.out.println("DEAD"); //@todo: gameover
        } else {
            move(sprite, location, column, row);
        }
    }
    public void moveLeft(Sprite sprite, int column, int row) {
        int columnCheck = (Math.max(column, 0));
        if (location[columnCheck][row].hasPlanet() && column > 0){
            visitPlanet(sprite, column, row);}
        else if (location[columnCheck][row].hasMeteorite()) {
            System.out.println("DEAD"); //@todo: gameover
        } else {
            move(sprite, location, column, row);
        }
    }
    public void moveRight(Sprite sprite, int column, int row) {
        int columnCheck = (Math.min(column, 11));
        if (location[columnCheck][row].hasPlanet() && column <12 ){
            visitPlanet(sprite, column, row);}
        else if (location[columnCheck][row].hasMeteorite()) {
            System.out.println("DEAD"); //@todo: gameover
        } else {
            move(sprite, location, column, row);
        }
    }

    public void visitPlanet(Sprite sprite, int column, int row){
        move(sprite, location, column, row);
        gp.getChildren().remove(location[column][row]);
        ImageView image = new ImageView("planetvisited.png");
        gp.add(image, column, row);
        planetScore ++;
        if (planetScore == 4){
            addObject(location, 1, "wormhole");
        }
        System.out.println(planetScore);
    }



    public void addObject(Location[][] location, int amount, String string) {
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