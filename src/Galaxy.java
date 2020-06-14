import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Galaxy extends Application {
    GridPane gp;
    int board_size = 12;
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
        gp.add(spaceship, spaceship.getLocation().getColumn(), spaceship.getLocation().getColumn());


//        adds 4 planets at random locations
        addObject(location, 4, "planet");

//        //adds 5 meteorites at random locations
        addObject(location,5, "meteorite");

        // if all planets visited , add wormhole
        //addObject(location, 1, "wormhole");

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

    public void move(Sprite sprite, Location[][] location, int column, int row, int columnAfter, int RowAfter){
        sprite.setLocation(location[column][row]);
        gp.add(sprite, sprite.getLocation().getColumn(), sprite.getLocation().getColumn());
    }
    public void moveUp(Sprite sprite, int column , int row) {
        int rowAfter = (row == 0 ? 0 : (row-1));
        int columnAfter = column;
        if (location[columnAfter][rowAfter].hasPlanet() && rowAfter >= 0){
            visitPlanet(sprite, column, row);}
        else if (location[columnAfter][rowAfter].hasMeteorite() || rowAfter < 0) {
            System.out.println("DEAD"); //@todo: gameover
        } else {
            move(sprite, location, column, row, columnAfter, rowAfter);
        }
    }
    public void moveDown(Sprite sprite, int column, int row) {
        int rowAfter = (row == 11 ? 11 : (row+1));
        int columnAfter = column;
        if (location[columnAfter][rowAfter].hasPlanet() && rowAfter < 12){
            visitPlanet(sprite, column, row);}
        else if (location[columnAfter][rowAfter].hasMeteorite()|| (rowAfter > 11)) {
            System.out.println("DEAD"); //@todo: gameover
        } else {
            move(sprite, location, column, row, columnAfter, rowAfter);
        }
    }
    public void moveLeft(Sprite sprite, int column, int row) {
        int columnAfter = (column == 0 ? 0 : (column-1));
        int rowAfter = row;
        if (location[columnAfter][rowAfter].hasPlanet() && columnAfter >= 0){
            visitPlanet(sprite, column, row);}
        else if (location[columnAfter][rowAfter].hasMeteorite() || (columnAfter < 0)) {
            System.out.println("DEAD"); //@todo: gameover
        } else {
            move(sprite, location, column, row, columnAfter, rowAfter);
        }
    }
    public void moveRight(Sprite sprite, int column, int row) {
        int columnAfter = (column == 11 ? 11 : (column+1));
        int rowAfter = row ;
        if (location[columnAfter][rowAfter].hasPlanet() && columnAfter <12 ){
            visitPlanet(sprite, column, row);}
        else if (location[columnAfter][rowAfter].hasMeteorite() || (columnAfter > 11)) {
            System.out.println("DEAD"); //@todo: gameover
        } else {
            move(sprite, location, column, row, columnAfter, rowAfter);
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