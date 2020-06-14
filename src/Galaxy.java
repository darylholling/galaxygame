import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Galaxy extends Application {
    GridPane gp;
    int board_size = 12;

    @Override
    public void start(Stage stage) throws Exception {
        gp = new GridPane();
        gp.setPrefSize(600, 600);

        LocationService locationService = new LocationService();
        Location[][] location = locationService.initalize(gp);

        // add spaceship
        Spaceship spaceship = new Spaceship();
        spaceship.setLocation(location[0][0]);
        gp.add(spaceship, spaceship.getLocation().getColumn(), spaceship.getLocation().getColumn());


//        adds 4 planets at random locations
        addObject(location, 4, "planet");

//        //adds 5 meteorites at random locations
        addObject(location,5, "meteorite");

        // if all planets visited , add wormhole
        //addObject(1, "wormhole");

        gp.setStyle("-fx-background-image: url('wp1.jpg');");

        Scene scene = new Scene(gp, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void move(Sprite sprite, int posX, int posY, GridPane gridPane) {
        //@todo add some scan/move function here to scan the surrounding grids and move using posX and posY +1 or -1
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