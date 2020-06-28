import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import org.junit.Assert;
import org.junit.Test;

public class SpriteServiceTest {

    JFXPanel panel = new JFXPanel();

    @Test
    public void RandomMeteoritesTest() {
        SpriteService spriteService = new SpriteService();
        Galaxy galaxy = new Galaxy();
        GridPane gridPane = new GridPane();
        Group root = new Group();

        galaxy.configure(root, gridPane);
        spriteService.configure(gridPane, galaxy.getLocation());

        spriteService.addSprite(2, "meteorite");


        Assert.assertNotEquals(spriteService.getMeteorites().get(0).getLocation(), spriteService.getMeteorites().get(1).getLocation());
    }
}