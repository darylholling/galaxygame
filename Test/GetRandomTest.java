
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class GetRandomTest {
    JFXPanel panel = new JFXPanel();
    SpriteService spriteService = new SpriteService();
    MoveService moveService = new MoveService();
    Galaxy galaxy = new Galaxy();
    Group root = new Group();
    GridPane gridPane = new GridPane();

    //check if meteorites move randomly
    @Test
    void checkGetRandom() {
        galaxy.configure(root, gridPane);
        spriteService.configure(gridPane, galaxy.getLocation());

        Meteorite meteorite = new Meteorite();
        meteorite.setLocation(galaxy.getLocation()[5][5]);

        moveService.getRandom(meteorite);

        while (true) {

            Location a = moveService.getRandom(meteorite);
            Location b = moveService.getRandom(meteorite);

            if (a != b) {
                Assert.assertNotEquals(a, b);
                break;
            }
        }
    }
}