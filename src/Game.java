import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game extends Application {
    GridPane gridPane = new GridPane();
    Galaxy galaxy = new Galaxy();
    Group root = new Group();
    Timer timer = new Timer(root);
    Menu menu = new Menu();
    MoveService moveService = new MoveService();
    SpriteService spriteService = new SpriteService();
    ArrayList<Long> highscores = new ArrayList<>();

    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(root, 600, 650);
        menu.initalize(stage, gridPane, scene, timer);

        galaxy.configure(root, gridPane);
//        timer.start();

        spriteService.configure(gridPane, galaxy.getPlayfield());
        moveService.configure(stage, scene, spriteService, gridPane, timer);

//        stage.setScene(scene);
//        stage.show();
    }
}
