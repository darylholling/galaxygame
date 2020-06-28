import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

public class GalaxyTest {
    GridPane gridPane = new GridPane();
    Group group = new Group();
    Galaxy galaxy = new Galaxy();

    @Test
    public void initalizePlayfield() {
        galaxy.configure(group, gridPane);

        int locationQuantity = 0;
        int boardSize = 12;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                locationQuantity++;
            }
        }

        Assert.assertEquals(boardSize * boardSize, locationQuantity);
    }
}