import javafx.animation.AnimationTimer;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveServiceTest {
    JFXPanel jfxPanel = new JFXPanel();
    SpriteService spriteService = new SpriteService();
    Galaxy galaxy = new Galaxy();
    Group root = new Group();
    Timer timer = new Timer(root);
    GridPane gridPane = new GridPane();

    @Test
    public void checkPlanetScore() {
        MoveService moveService = new MoveService();

        moveService.spriteService = spriteService;
        Planet planet = new Planet();
        Planet planet1 = new Planet();
        galaxy.configure(root, gridPane);
        spriteService.configure(gridPane, galaxy.getLocation());
        planet.setLocation(galaxy.getLocation()[1][0]);
        planet1.setLocation(galaxy.getLocation()[2][0]);
//        spriteService.getSpaceship().setLocation(planet.getLocation());
//        spriteService.getSpaceship().setLocation(planet1.getLocation());



        int oldQuantity = spriteService.getSpaceship().getPlanetsVisited();
        moveService.visitPlanet(spriteService.getSpaceship(), planet.getLocation());

        moveService.visitPlanet(spriteService.getSpaceship(), planet1.getLocation());

        Assert.assertNotEquals((long)oldQuantity, (long)spriteService.getSpaceship().getPlanetsVisited());

    }
}