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
    SpriteService spriteService;
    Game game;

    public void initalize(Stage stage, GridPane gridPane, Scene gameScene, Timer timer, SpriteService spriteService, Game game) throws Exception {
        this.spriteService = spriteService;
        this.game = game;
//        Scanner keyboard = new Scanner(System.in);
//        int answer = Integer.parseInt(keyboard.nextLine());
//        int answer2 = Integer.parseInt(keyboard.nextLine());

        String musicFile = "src\\Star Wars.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
//      mediaPlayer.play();

        stage.setTitle("Galaxy Quest!");
        Label welcome = new Label("Galaxy Quest!");
        welcome.setFont(Font.font("Verdana", 60));
        welcome.setTextFill(Color.WHITE);

        VBox layoutWelcome = new VBox(20);

        Button startGame = new Button("Start the game!");
        startGame.setOnAction(e -> stage.setScene(scene2));

        Button highScores = new Button("View high scores");
//      highScores.setOnAction(e);
        Button exitGame = new Button("Quit game");
        exitGame.setOnAction(e -> stage.close());

        layoutWelcome.setAlignment(Pos.CENTER);
        layoutWelcome.getChildren().addAll(welcome, startGame, highScores, exitGame);
        layoutWelcome.setStyle("-fx-background-image: url('Space2.png');");
        scene1 = new Scene(layoutWelcome, 600, 600);

        VBox layoutChooseLevel = new VBox(20);
        Label chooseLevel = new Label("Choose your level: ");
        chooseLevel.setFont(Font.font("Verdana", 30));
        chooseLevel.setTextFill(Color.WHITE);

        Button easy = new Button("Easy");
        easy.setOnAction(e -> {
            this.startGame(3, 5, stage, gameScene);

            stage.setScene(gameScene);
            timer.start();
        });
        Button medium = new Button("Medium");
        medium.setOnAction(e-> {
        this.startGame(5, 9, stage, gameScene);
            stage.setScene(gameScene);
            timer.start();
        });
        Button hard = new Button("Hard");
        hard.setOnAction(e-> {
        this.startGame(7, 13, stage, gameScene);
            stage.setScene(gameScene);
            timer.start();
        });
//       Button custom = new Button("Custom");
//        custom.setOnAction(e -> stage.setScene(scene3));

//
//        custom.setOnAction(e-> {
//
//            this.startGame(answer, answer2, stage, gameScene);
//            stage.setScene(gameScene);
//            timer.start();
//        });

        Button goBack = new Button("Go back");
        goBack.setOnAction(e -> stage.setScene(scene1));

        easy.setMaxWidth(120);
        medium.setMaxWidth(120);
        hard.setMaxWidth(120);
        goBack.setMaxWidth(120);

        layoutChooseLevel.setAlignment(Pos.CENTER);
        layoutChooseLevel.getChildren().addAll(chooseLevel,easy, medium, hard, goBack);
        layoutChooseLevel.setStyle("-fx-background-image: url('galaxy-menu.png');");
        scene2 = new Scene(layoutChooseLevel, 600, 600);

//        VBox layoutChooseSprites = new VBox(20);
//
//        Label choosePlanets = new Label("Choose the amount of planets: ");
//        Label chooseMeteorites = new Label("Choose the amount of planets: ");
//        TextField amountPlanets = new TextField();
//        amountPlanets.setMaxWidth(50);
//        TextField amountMeteorites = new TextField();
//        amountMeteorites.setMaxWidth(50);
//        Button startCustom = new Button("Start the game");
//        startCustom.setOnAction(e-> {
//            this.startGame(amountMeteorites.getText(), amountPlanets.getText(), stage, gameScene);
//            stage.setScene(gameScene);
//            timer.start();
//        });
//
//
//        choosePlanets.setFont(Font.font("Verdana", 30));
//        choosePlanets.setTextFill(Color.WHITE);
//        chooseMeteorites.setFont(Font.font("Verdana", 30));
//        chooseMeteorites.setTextFill(Color.WHITE);
//
//       layoutChooseSprites.setAlignment(Pos.CENTER);
//       layoutChooseSprites.getChildren().addAll(choosePlanets,amountPlanets, chooseMeteorites, amountMeteorites, startCustom, goBack);
//       layoutChooseSprites.setStyle("-fx-background-image: url('galaxy-menu.png');");
//        scene3 = new Scene(layoutChooseSprites, 600, 600);




                stage.setScene(scene1);
        stage.show();
    }

    public void startGame(Integer planetQuantity, Integer meteoriteQuantity, Stage stage, Scene scene){
        this.spriteService.setPlanetQuantity(planetQuantity);
        this.spriteService.setMeteoriteQuantity(meteoriteQuantity);
        this.game.startgame(stage, scene);
    }

}
