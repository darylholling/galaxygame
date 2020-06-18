import javafx.scene.Group;
import javafx.scene.layout.GridPane;

public class Galaxy {
//    Location[][] location;
//    int planetQuantity = 4;

    LocationService locationService = new LocationService();
    SpriteService spriteService = new SpriteService();
    Location[][] playfield;

    public void configure(Group root, GridPane gp) {
        spriteService.setGridPane(gp);
        playfield = locationService.initalizePlayfield(gp);
        spriteService.setPlayfield(playfield);

        gp.setPrefSize(600, 600);
        gp.setStyle("-fx-background-image: url('wp1.jpg');");
        gp.setLayoutY(50);
//        locationService.initalizePlayfield(gp);
        spriteService.initializeSprites();

        root.getChildren().add(gp);
    }
}