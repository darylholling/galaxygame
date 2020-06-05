import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Pane {
    private double sceneWidth = 600;
    private double sceneHeight = 600;

    private int xFields = 12;
    private int yFields = 12;

    double gridWidth = sceneWidth / xFields;
    double gridHeight = sceneHeight / yFields;

    Tile[][] playfield = new Tile[xFields][yFields];

    Group group = new Group();

    public Board() {
        this.createPlayfield(playfield, group);
    }

    public void createPlayfield(Tile[][] playfield, Group group){
        // initialize playfield
        for( int i=0; i < xFields; i++) {
            for( int j=0; j < yFields; j++) {

                // create node
                Tile tile = new Tile( i * gridWidth, j * gridHeight, gridWidth, gridHeight);

                group.getChildren().add(tile);

                playfield[i][j] = tile;

                System.out.println(i);
                System.out.println(j);
            }
        }
    }

    public class Tile extends Rectangle {

        public Tile(double x, double y, double width, double height) {
            // create rectangle
            Rectangle rectangle = new Rectangle( width, height);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.LIGHTBLUE);

            // set position
            setTranslateX(x);
            setTranslateY(y);

            getChildren().addAll(rectangle);
        }
    }

    public double getSceneWidth() {
        return sceneWidth;
    }

    public double getSceneHeight() {
        return sceneHeight;
    }
}
