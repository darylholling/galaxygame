import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Galaxy {
    int board_size = 12;
    Location[][] location;

    public void configure(Group root, GridPane gp) {
        this.initalizePlayfield(gp);

        gp.setPrefSize(600, 600);
        gp.setStyle("-fx-background-image: url('wp1.gif');");
        gp.setLayoutY(50);

        root.getChildren().add(gp);
    }

    public Location[][] getPlayfield(){
        return this.location;
    }

    public void initalizePlayfield(GridPane gp) {
        Location[][] location = new Location[board_size][board_size];
        //        initialize playfield
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                location[i][j] = new Location(i, j);
                Rectangle tile = new Rectangle(50, 50);
                tile.setFill(Color.TRANSPARENT);
                gp.add(tile, i, j);
            }
        }

        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                gp.setColumnIndex(location[i][j], i);
                gp.setRowIndex(location[i][j], j);
                if (i + 1 < board_size) {
                    location[i][j].setRight(location[i + 1][j]);
                }

                if (i - 1 < board_size && i - 1 >= 0) {
                    location[i][j].setLeft(location[i - 1][j]);
                }

                if (j + 1 < board_size) {
                    location[i][j].setDown(location[i][j + 1]);
                }

                if (j - 1 < board_size && j - 1 >= 0) {
                    location[i][j].setUp(location[i][j - 1]);
                }
            }
        }

        this.location = location;
    }
}