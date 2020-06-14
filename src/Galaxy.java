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
//        gp.setGridLinesVisible(true);
        gp.setPrefSize(600, 600);

        LocationService locationService = new LocationService();
        Location[][] gridPaneNodes = locationService.initalize(gp);

        // add spaceship
        Spaceship spaceship = new Spaceship();
        spaceship.setLocation(gridPaneNodes[0][0]);
        gp.add(spaceship,spaceship.getLocation().getColumn(),spaceship.getLocation().getColumn());
//        gp.setColumnIndex(spaceship, spaceship.getLocation().getRight().getColumn());
//        gp.setRowIndex(spaceship,  gp.getRowIndex(spaceship));
        System.out.println(gp.getColumnIndex(spaceship));

//
////        adds 4 planets at random locations
//        addObject(4, "planet");
//
//        //adds 5 meteorites at random locations
//        addObject(5, "meteorite");

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

//    public void addObject(int amount, String string) {
//        int i = 0;
//        while (i < amount) {
//            int posX = (int) Math.floor(Math.random() * 12);
//            int posY = (int) Math.floor(Math.random() * 12);
//
//            if (gridPaneNodes[posX][posY] == null) {
//                Sprite anySprite;
//                switch (string) {
//                    case "meteorite":
//                        anySprite = new Meteorite();
//                        break;
//                    case "planet":
//                        anySprite = new Planet();
//                        break;
//                    case "wormhole":
//                        anySprite = new Wormhole();
//                        break;
//                    default:
//                        //@todo handle exception maybe??
//                        System.out.println("invalid input");
//                        return;
//                }
//                gp.add(anySprite, posX, posY);
//                gridPaneNodes[posX][posY] = anySprite;
//                i++;
//            }
//        }
//    }
}