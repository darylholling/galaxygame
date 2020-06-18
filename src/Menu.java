import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu {
    Scene scene1, scene2;

    public void initalize(Stage stage, GridPane gridPane, Scene gameScene) throws Exception {
        stage.setTitle("Galaxy The Game!");

        Button startGame = new Button("Start the game!");
        Button highScores = new Button("View highscores");
        Button exitGame = new Button("Quit game");
        startGame.setOnAction(e -> stage.setScene(scene2));
//        highScores.setOnAction(e);
        exitGame.setOnAction(e -> stage.close());

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(startGame, highScores, exitGame);

        layout1.setStyle("-fx-background-image: url('Space2.png');");
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 600, 600);

        gridPane.setStyle("-fx-background-image: url('wp1.jpg');");

        Button button3 = new Button("Easy");
        button3.setOnAction(e -> stage.setScene(gameScene));
        Button button4 = new Button("Hard");
        //        button4.setOnAction(e->stage.setScene(scene2));
        Button button5 = new Button("Very Hard");
//    button1.setOnAction(e->stage.setScene(scene2));
        Button button6 = new Button("Go back");
        button6.setOnAction(e -> stage.setScene(scene1));
//
        button3.setMaxWidth(120);
        button4.setMaxWidth(120);
        button5.setMaxWidth(120);
        button6.setMaxWidth(120);

        VBox layout2 = new VBox(20);
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(button3, button4, button5, button6);

        scene2 = new Scene(layout2, 600, 600);
        layout2.setStyle("-fx-background-image: url('galaxy-menu.png');");
        stage.setScene(scene1);
        stage.show();
    }
}
