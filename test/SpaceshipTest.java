import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceshipTest {
    JFXPanel jfxPanel = new JFXPanel();
    SpriteService spriteService = new SpriteService();
    Galaxy galaxy = new Galaxy();
    Group group = new Group();
    GridPane gridPane = new GridPane();

    @Test
    public void hasLocation(){
        galaxy.configure(group, gridPane);
        spriteService.configure(gridPane, galaxy.getLocation());

        Assert.assertEquals(spriteService.getSpaceship().getLocation(), galaxy.getLocation()[0][0]);
    }
}