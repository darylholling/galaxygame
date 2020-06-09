import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Galaxy extends Application {
    int board_size = 12;
    Node[][] gridPaneNodes = new Node[board_size][board_size];
    GridPane gp;
    int planetScore = 0;

    @Override
    public void start(Stage stage) throws Exception {
        gp = new GridPane();
//        gp.setGridLinesVisible(true);
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
        //move spaceship around with arrows
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch(keyEvent.getCode()){
                    case UP: moveUp(space, GridPane.getColumnIndex(space), GridPane.getRowIndex(space)); break;
                    case DOWN: moveDown(space, GridPane.getColumnIndex(space), GridPane.getRowIndex(space)); break;
                    case LEFT: moveLeft(space, GridPane.getColumnIndex(space), GridPane.getRowIndex(space)); break;
                    case RIGHT: moveRight(space, GridPane.getColumnIndex(space), GridPane.getRowIndex(space)); break;
                }
            }
        });
    }

    public void move(Sprite sprite, int posX, int posY, int posAfterX, int posAfterY){
            gridPaneNodes[posX][posY] = null;
            gp.getChildren().remove(sprite);
            gridPaneNodes[posAfterX][posAfterY] = sprite;
            gp.add(sprite, posAfterX, (posAfterY));
        }
        public void visitPlanet(Sprite sprite, int posX, int posY, int posAfterX, int posAfterY){
            move(sprite, posX, posY, posAfterX, posAfterY);
            gp.getChildren().remove(gridPaneNodes[posAfterX][posAfterY] instanceof Planet);
            ImageView image = new ImageView("planetvisited.png");
            gp.add(image, posAfterX, posAfterY);
            planetScore ++;
            if (planetScore == 4){
                addObject(1, "wormhole");
            }
            System.out.println(planetScore);
        }
    public void moveUp(Sprite sprite, int posX, int posY) {
        int posAfterY = (posY == 0 ? 0 : (posY-1));
        int posAfterX = posX;
        if ((gridPaneNodes[posAfterX][posAfterY] instanceof Planet) && posAfterY >= 0){
            visitPlanet(sprite, posX, posY, posAfterX, posAfterY);}
        else if ((gridPaneNodes[posAfterX][posAfterY] instanceof Meteorite) || posAfterY < 0) {
            System.out.println("DEAD"); //@todo: gameover
        } else {
            move(sprite, posX, posY, posAfterX, posAfterY);
        }
    }
    public void moveDown(Sprite sprite, int posX, int posY) {
        int posAfterY = (posY == 11 ? 11 : (posY+1));
        int posAfterX = posX;
        if ((gridPaneNodes[posAfterX][posAfterY] instanceof Planet) && posAfterY < 12){
            visitPlanet(sprite, posX, posY, posAfterX, posAfterY);}
        else if ((gridPaneNodes[posAfterX][posAfterY] instanceof Meteorite) || (posAfterY > 11)) {
            System.out.println("DEAD"); //@todo: gameover
        } else {
            move(sprite, posX, posY, posAfterX, posAfterY);
        }
    }
    public void moveLeft(Sprite sprite, int posX, int posY) {
        int posAfterX = (posX == 0 ? 0 : (posX-1));
        int posAfterY = posY;
        if ((gridPaneNodes[posAfterX][posAfterY] instanceof Planet) && posAfterX >= 0){
            visitPlanet(sprite, posX, posY, posAfterX, posAfterY);}
        else if ((gridPaneNodes[posAfterX][posAfterY] instanceof Meteorite) || (posAfterX < 0)) {
            System.out.println("DEAD"); //@todo: gameover
        } else {
            move(sprite, posX, posY, posAfterX, posAfterY);
        }
    }
    public void moveRight(Sprite sprite, int posX, int posY) {
        int posAfterX = (posX == 11 ? 11 : (posX+1));
        int posAfterY = posY;
        if ((gridPaneNodes[posAfterX][posAfterY] instanceof Planet) && posAfterX <12 ){
            visitPlanet(sprite, posX, posY, posAfterX, posAfterY);}
        else if ((gridPaneNodes[posAfterX][posAfterY] instanceof Meteorite) || (posAfterX > 11)) {
            System.out.println("DEAD"); //@todo: gameover
        } else {
            move(sprite, posX, posY, posAfterX, posAfterY);
        }
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