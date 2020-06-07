import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Galaxy extends Application {
    int board_size = 12;
    int planet_quantity = 4;
    Node[][] gridPaneNodes = new Node[board_size][board_size];
    GridPane gp;
    Node spaceshipNode;
    Node[][] planetNodes = new Node[planet_quantity][planet_quantity];


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
                tile.setStroke(Color.BLACK);
                gp.add(tile, i, j);
            }
        }
        // add spaceship
        Spaceship space = new Spaceship();
        gp.add(space,0,0);

        //adds 4 planets at random locations ---->> STILL NEED A WAY TO CHECK WHETHER A NODE IS EMPTY BEFORE PLACING A PLANET!!!!

        for (int i = 1; i < 5; i++){
            int posX = (int) Math.floor(Math.random() * 12);
            int posY = (int) Math.floor(Math.random() * 12);
            Planet planet = new Planet();
            gp.add(planet, posX, posY);
        }
        //adds 5 meteorites at random locations -->> STILL NEED A WAY TO CHECK WHETHER A NODE IS EMPTY BEFORE PLACING A METEORITE!!!!
        for (int i = 1; i < 6; i++){
            int posX = (int) Math.floor(Math.random() * 12);
            int posY = (int) Math.floor(Math.random() * 12);
            Meteorite meteorite = new Meteorite();
            gp.add(meteorite, posX, posY);
        }
//        System.out.println(GridPane.getColumnIndex(space));
//        System.out.println(gp.getRowIndex(space));

        gp.setStyle("-fx-background-image: url('wp1.jpg');"); // background is behind the rectangles...

        //scan each node (child) and store the position in array
        for(Node child : gp.getChildren()) {
            Integer column = GridPane.getColumnIndex(child);
            Integer row = GridPane.getRowIndex(child);
            if (column != null && row != null) {
                gridPaneNodes[column][row] = child;
            }
        }
//location of spaceship
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (gridPaneNodes[i][j] instanceof Spaceship){
                    spaceshipNode = gridPaneNodes [i][j];
                }
            }
        }
        //location of planet
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (gridPaneNodes[i][j] instanceof Planet) {
                        Integer column = GridPane.getColumnIndex(gridPaneNodes[i][j]);
                        Integer row = GridPane.getRowIndex(gridPaneNodes[i][j]);
//                    System.out.println(column);
//                    System.out.println(row);
                    for (int x = 0; x < 4; x++) {
                        planetNodes[x][x] = gridPaneNodes[column][row];
                    }
                }
            }
        }
//        to show contents of gridPaneNodes array
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(planetNodes[i][j]);
            }
        }



//        //to show contents of gridPaneNodes array
//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 12; j++) {
//                System.out.println(gridPaneNodes[i][j]);
//            }
//        }

        Scene sc = new Scene(gp, 612, 612);
        stage.setScene(sc);
        stage.show();
    }
    public void move(Sprite sprite, int posX, int posY, GridPane gridPane){
        //some scan/move function here to scan the surrounding grids and move using posX and posY +1 or -1
    }
}
