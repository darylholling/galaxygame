import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;

public class Galaxy extends Application {

    public void start(Stage stage) throws Exception {
//        Board board = new Board();

//        Sprite[][] spriteMap = new Sprite[12][12];
//        spriteMap[0][0] = spaceShip;


//        Spaceship spaceShip = new Spaceship("/files/default_profile_icon.png",50,50,false,true, false);

//        Image image = new Image("src/spaceship.jpg");
//        Image image = new Image(getClass().getResource("spaceship.jpg").toExternalForm());
        Image image = new Image(new File("spaceship.jpg").toURI().toString());
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setSmooth(true);
        imageView.setCache(true);
//
//        Group root = new Group();

        Pane outerPane = new Pane();

        outerPane.getChildren().addAll(imageView);

        Scene scene = new Scene(outerPane, 600, 600);
////        scene.setFill(Color.BLACK);
//        root.getChildren().add(imageView);
//        stage.setScene(scene);

        stage.setScene(scene);

        stage.show();
    }
}