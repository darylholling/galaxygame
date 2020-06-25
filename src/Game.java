import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Game extends Application {
    GridPane gridPane = new GridPane();
    Galaxy galaxy = new Galaxy();
    Group root = new Group();
    Timer timer = new Timer(root);
    Menu menu = new Menu();
    MoveService moveService = new MoveService();
    SpriteService spriteService = new SpriteService();

    public void start(Stage stage) {
        Scene scene = new Scene(root, 600, 650);
        menu.initalize(stage, scene, this);
    }

    public class Menu {
        Scene scene1, scene2;
        MediaPlayer mediaPlayer;
        Game game;

        public void initalize(Stage stage, Scene gameScene, Game game) {
            this.game = game;

            String musicFile = "src\\Star Wars.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();

            stage.setTitle("Galaxy Quest!");
            Label welcome = new Label("Galaxy Quest!");
            welcome.setFont(Font.font("Verdana", 60));
            welcome.setTextFill(Color.WHITE);

            Button goBack = new Button("Go back");
            VBox layoutChooseLevel = new VBox(20);

            goBack.setOnAction(e -> {
                if (!layoutChooseLevel.getChildren().contains(goBack)) {
                    layoutChooseLevel.getChildren().add(goBack);
                }

                stage.setScene(scene1);
            });

            VBox layoutWelcome = new VBox(20);

            Button startGame = new Button("Start the game!");
            startGame.setOnAction(e -> stage.setScene(scene2));

            Button highScores = new Button("View high scores");
            highScores.setOnAction(e -> {
                this.displayHighScores(goBack, stage);
            });

            Button exitGame = new Button("Quit game");
            exitGame.setOnAction(e -> stage.close());

            layoutWelcome.setAlignment(Pos.CENTER);
            layoutWelcome.getChildren().addAll(welcome, startGame, highScores, exitGame);
            layoutWelcome.setStyle("-fx-background-image: url('Space2.jpg');");
            scene1 = new Scene(layoutWelcome, 600, 600);

            Label chooseLevel = new Label("Choose your level: ");
            chooseLevel.setFont(Font.font("Verdana", 30));
            chooseLevel.setTextFill(Color.WHITE);

            Button easy = new Button("Easy");
            easy.setOnAction(e -> {
                this.startGame(3, 5, stage, gameScene);
            });
            Button medium = new Button("Medium");
            medium.setOnAction(e -> {
                this.startGame(5, 9, stage, gameScene);
            });
            Button hard = new Button("Hard");
            hard.setOnAction(e -> {
                this.startGame(7, 13, stage, gameScene);
            });

            easy.setMaxWidth(120);
            medium.setMaxWidth(120);
            hard.setMaxWidth(120);
            goBack.setMaxWidth(120);

            layoutChooseLevel.setAlignment(Pos.CENTER);
            System.out.println(goBack);
            layoutChooseLevel.getChildren().addAll(chooseLevel, easy, medium, hard, goBack);
            layoutChooseLevel.setStyle("-fx-background-image: url('galaxy-menu.png');");
            scene2 = new Scene(layoutChooseLevel, 600, 600);

            stage.setScene(scene1);
            stage.setResizable(false);
            stage.show();
        }

        private void startGame(Integer planetQuantity, Integer meteoriteQuantity, Stage stage, Scene scene) {
            game.spriteService.setPlanetQuantity(planetQuantity);
            game.spriteService.setMeteoriteQuantity(meteoriteQuantity);

            stage.setScene(scene);
            timer.start();

            galaxy.configure(root, gridPane);
            spriteService.configure(gridPane, galaxy.getLocation());
            moveService.configure(stage, scene, spriteService, gridPane, timer);
        }

        private void displayHighScores(Button goBack, Stage stage) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader("highscore.txt"));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            List<Integer> numbers = new ArrayList<>();
            String line = null;

            while (true) {
                try {
                    if ((line = Objects.requireNonNull(bufferedReader).readLine()) == null) break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                String[] stringNumbers = line != null ? line.split(" ") : new String[0];
                for (String strNumber : stringNumbers) {
                    numbers.add(Integer.parseInt(strNumber));
                }
            }
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Collections.sort(numbers);

            if (numbers.size() > 10) {
                for (int i = numbers.size() - 1; i > 9; i--) {
                    numbers.remove(numbers.get(i));
                }
            }

            Group highScoreGroup = new Group();
            VBox highScoreBox = new VBox();
            highScoreBox.setStyle("-fx-background-image: url('Space2.jpg');");
            highScoreBox.setMinWidth(600);
            highScoreBox.setMinHeight(600);
            highScoreBox.setAlignment(Pos.BASELINE_CENTER);

            Label header = new Label("High scores");
            header.setTextFill(Color.RED);
            header.setStyle("-fx-font: 50 fantasy;");
            highScoreBox.getChildren().add(header);
            goBack.setLayoutX(270);
            goBack.setLayoutY(3);

            for (Integer number : numbers) {
                Label label = new Label();
                label.setText(String.valueOf(number));
                label.setTextFill(Color.RED);
                label.setStyle("-fx-font: 35 fantasy;");
                highScoreBox.getChildren().add(label);
            }

            highScoreGroup.getChildren().addAll(highScoreBox, goBack);
            Scene highScoreScene = new Scene(highScoreGroup, 600, 600);
            stage.setScene(highScoreScene);
        }
    }
}
