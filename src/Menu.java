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
    Group root = new Group();
    Timer timer = new Timer(root);


    public void initalize(Stage stage, GridPane gridPane, Scene gameScene, Timer timer) throws Exception {
        String musicFile = "src\\Star Wars.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
//      mediaPlayer.play();

        stage.setTitle("Galaxy Quest!");
        Label welcome = new Label("Galaxy Quest!");
        welcome.setFont(Font.font("Verdana", 60));
        welcome.setTextFill(Color.WHITE);

        VBox layout1 = new VBox(20);

        Button startGame = new Button("Start the game!");
        startGame.setOnAction(e -> stage.setScene(scene2));

        Button highScores = new Button("View high scores");
//      highScores.setOnAction(e);
        Button exitGame = new Button("Quit game");
        exitGame.setOnAction(e -> stage.close());

        layout1.getChildren().addAll(welcome, startGame, highScores, exitGame);

        layout1.setStyle("-fx-background-image: url('Space2.png');");
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 600, 600);

        VBox layout2 = new VBox(20);

        Button easy = new Button("Easy");
        easy.setOnAction(e -> {
            stage.setScene(gameScene);
            timer.start();
        });
        Button medium = new Button("Medium");
//      medium.setOnAction(e->stage.setScene(scene2));
        Button hard = new Button("Hard");
//      hard.setOnAction(e->stage.setScene(scene2));
        Button goBack = new Button("Go back");
        goBack.setOnAction(e -> stage.setScene(scene1));

        easy.setMaxWidth(120);
        medium.setMaxWidth(120);
        hard.setMaxWidth(120);
        goBack.setMaxWidth(120);

        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(easy, medium, hard, goBack);
        layout2.setStyle("-fx-background-image: url('galaxy-menu.png');");
        scene2 = new Scene(layout2, 600, 600);

        stage.setScene(scene1);
        stage.show();
    }
}
