import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Galaxy extends Application {
    int board_size = 12;
    Location[][] gridPaneNodes = new Location[board_size][board_size];
    GridPane gp;

    @Override
    public void start(Stage stage) throws Exception {
        gp = new GridPane();
        gp.setGridLinesVisible(true);
        gp.setPrefSize(600, 600);

//        initialize playfield
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                gridPaneNodes[i][j] = new Location();

                gridPaneNodes[i][j].setI(i);
                gridPaneNodes[i][j].setJ(j);

//                Rectangle tile = new Rectangle(50, 50);
//                tile.setFill(Color.TRANSPARENT);
//                tile.setStroke(Color.BLACK);
//                gp.add(tile, i, j);
            }
        }

        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {

                if (i == 0 && j == 0) {
                    Spaceship spaceship = new Spaceship();
                    spaceship.setLocation(gridPaneNodes[i][j]);
                }

                if (i + 1 < board_size){
                    gridPaneNodes[i][j].setRight(gridPaneNodes[i + 1][j]);
                }

                if (i - 1 < board_size && i -1 > 0){
                    gridPaneNodes[i][j].setLeft(gridPaneNodes[i - 1][j]);
                }

                if (j + 1 < board_size){
                    gridPaneNodes[i][j].setDown(gridPaneNodes[i][j + 1]);
                }

                if (j - 1 < board_size && j -1 > 0){
                    gridPaneNodes[i][j].setUp(gridPaneNodes[i][j - 1]);
                }
            }
        }

        // add spaceship

//        Spaceship spaceship = new Spaceship();

//        gp.add(spaceship, 0, 0);
//        gridPaneNodes[0][0] = space;
//
////        adds 4 planets at random locations
//        addObject(4, "planet");
//
//        //adds 5 meteorites at random locations
//        addObject(5, "meteorite");

        // if all planets visited , add wormhole
        //addObject(1, "wormhole");

        gp.setStyle("-fx-background-image: url('wp1.jpg');");

// this function lists the content of our gridPaneNodes[][] for debugging purposes
//        for (int i = 0; i < board_size; i++) {
//            for (int j = 0; j < board_size; j++) {
//                System.out.println(gridPaneNodes[i][j]);
//            }
//        }

        Scene scene = new Scene(gp, 612, 612);
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
