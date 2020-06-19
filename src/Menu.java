import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Menu {
    Scene scene1, scene2;
    MediaPlayer mediaPlayer;

    public void initalize(Stage stage, GridPane gridPane, Scene gameScene) throws Exception {
        String musicFile = "src\\Star Wars.mp3";

        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();

        stage.setTitle("Galaxy Quest!");
        Label welcome = new Label("Galaxy Quest!");
        welcome.setFont(Font.font("Verdana", 60));
        welcome.setTextFill(Color.WHITE);

        Button startGame = new Button("Start the game!");
        Button highScores = new Button("View high scores");
        Button exitGame = new Button("Quit game");
        startGame.setOnAction(e -> stage.setScene(scene2));
//      highScores.setOnAction(e);
        exitGame.setOnAction(e -> stage.close());

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(welcome, startGame, highScores, exitGame);

        layout1.setStyle("-fx-background-image: url('Space2.png');");
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 600, 600);

        gridPane.setStyle("-fx-background-image: url('wp1.jpg');");

        Button easy = new Button("Easy");
        easy.setOnAction(e -> stage.setScene(gameScene));
        Button medium = new Button("Medium");
        //        medium.setOnAction(e->stage.setScene(scene2));
        Button hard = new Button("Hard");
//      hard.setOnAction(e->stage.setScene(scene2));
        Button goBack = new Button("Go back");
        goBack.setOnAction(e -> stage.setScene(scene1));
//
        easy.setMaxWidth(120);
        medium.setMaxWidth(120);
        hard.setMaxWidth(120);
        goBack.setMaxWidth(120);

        VBox layout2 = new VBox(20);
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(easy, medium, hard, goBack);

        scene2 = new Scene(layout2, 600, 600);
        layout2.setStyle("-fx-background-image: url('galaxy-menu.png');");
        stage.setScene(scene1);
        stage.show();
    }
}
