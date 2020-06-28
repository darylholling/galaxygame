import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


class MeteoritesAmountTest {
    JFXPanel panel = new JFXPanel();
    SpriteService spriteService = new SpriteService();
    Galaxy galaxy = new Galaxy();
    Group root = new Group();
    GridPane gridPane = new GridPane();

    @Test
    //checks the amount of meteorites added to playfield at the start of the game.
    void checkAmountOfMeteorites() {
        galaxy.configure(root, gridPane);
        spriteService.setMeteoriteQuantity(5);
        spriteService.configure(gridPane, galaxy.getLocation());
        Assert.assertEquals(5, spriteService.getMeteorites().size());
    }
}
