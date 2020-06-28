import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import org.junit.Assert;
import org.junit.Test;

public class MeteoriteAmountTest {
    JFXPanel panel = new JFXPanel();
    SpriteService spriteService = new SpriteService();
    Galaxy galaxy = new Galaxy();
    Group root = new Group();
    GridPane gridPane = new GridPane();

    @Test
    public void checkAmountOfMeteorites() {
        galaxy.configure(root, gridPane);
        spriteService.setMeteoriteQuantity(5);
        spriteService.configure(gridPane, galaxy.getLocation());
        Assert.assertEquals(5, spriteService.getMeteorites().size());
    }
}
