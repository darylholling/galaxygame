import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import org.junit.Assert;
import org.junit.Test;

public class MovableInterfaceTest {
    JFXPanel jfxPanel = new JFXPanel();
    GridPane gridPane = new GridPane();

    @Test
    public void move() {
        Spaceship spaceship = new Spaceship();
        Location location = new Location(5, 5);
        spaceship.setLocation(location);

        Location up = new Location(4, 5);
        Location right = new Location(6, 5);
        Location down = new Location(5, 6);
        Location left = new Location(5, 4);

        location.setUp(up);
        location.setRight(right);
        location.setDown(down);
        location.setLeft(left);

        spaceship.move(spaceship, location.getRight(), gridPane);

        Assert.assertEquals(spaceship.getLocation(), location.getRight());
    }
}