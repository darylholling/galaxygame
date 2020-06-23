import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Galaxy {
    private Location[][] location;

    public void configure(Group root, GridPane gridPane) {
        this.initalizePlayfield(gridPane);

        gridPane.setPrefSize(600, 600);
        gridPane.setStyle("-fx-background-image: url('wp1.gif');");
        gridPane.setLayoutY(50);

        root.getChildren().add(gridPane);
    }

    public Location[][] getLocation(){
        return this.location;
    }

    public void initalizePlayfield(GridPane gridPane) {
        int boardSize = 12;
        Location[][] location = new Location[boardSize][boardSize];
        //        initialize playfield
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                location[i][j] = new Location(i, j);
                Rectangle tile = new Rectangle(50, 50);
                tile.setFill(Color.TRANSPARENT);
                gridPane.add(tile, i, j);
            }
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                gridPane.setColumnIndex(location[i][j], i);
                gridPane.setRowIndex(location[i][j], j);
                if (i + 1 < boardSize) {
                    location[i][j].setRight(location[i + 1][j]);
                }

                if (i - 1 < boardSize && i - 1 >= 0) {
                    location[i][j].setLeft(location[i - 1][j]);
                }

                if (j + 1 < boardSize) {
                    location[i][j].setDown(location[i][j + 1]);
                }

                if (j - 1 < boardSize && j - 1 >= 0) {
                    location[i][j].setUp(location[i][j - 1]);
                }
            }
        }

        this.location = location;
    }
}