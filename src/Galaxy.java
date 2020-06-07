import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Galaxy extends Application {
    int board_size = 12;
    Node[][] gridPaneNodes = new Node[board_size][board_size];
    GridPane gp;

    @Override
    public void start(Stage stage) throws Exception {
        gp = new GridPane();
        gp.setGridLinesVisible(true);
        gp.setPrefSize(600, 600);

//        initialize playfield
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {

                Rectangle tile = new Rectangle(50, 50);
                tile.setFill(Color.TRANSPARENT);
                gp.add(tile, i, j);
            }
        }
        // add spaceship
        Spaceship space = new Spaceship();
        gp.add(space, 0, 0);
        gridPaneNodes[0][0] = space;

//        adds 4 planets at random locations
        addObject(4, "planet");

        //adds 5 meteorites at random locations
        addObject(5, "meteorite");

        // if all planets visited , add wormhole
        //addObject(1, "wormhole");

        gp.setStyle("-fx-background-image: url('wp1.jpg');");

// this function lists the content of our gridPaneNodes[][] for debugging purposes
//        for (int i = 0; i < board_size; i++) {
//            for (int j = 0; j < board_size; j++) {
//                System.out.println(gridPaneNodes[i][j]);
//            }
//        }

        Scene scene = new Scene(gp, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void move(Sprite sprite, int posX, int posY, GridPane gridPane) {
        //@todo add some scan/move function here to scan the surrounding grids and move using posX and posY +1 or -1
    }

    public void addObject(int amount, String string) {
        int i = 0;
        while (i < amount) {
            int posX = (int) Math.floor(Math.random() * 12);
            int posY = (int) Math.floor(Math.random() * 12);

            if (gridPaneNodes[posX][posY] == null) {
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
                gp.add(anySprite, posX, posY);
                gridPaneNodes[posX][posY] = anySprite;
                i++;
            }
        }
    }
}
