import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class highScoreAddTest {
    JFXPanel panel = new JFXPanel();

    public List<Integer> readFile() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("highscore.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        List<Integer> numbers = new ArrayList<>();
        String line = null;

        while (true) {
            try {
                if ((line = Objects.requireNonNull(bufferedReader).readLine()) == null) break;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String[] stringNumbers = line != null ? line.split(" ") : new String[0];
            for (String strNumber : stringNumbers) {
                numbers.add(Integer.parseInt(strNumber));
            }
        }
        try {
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return numbers;
    }

    @Test
    public void isHighScoreAdded() {

        List<Integer> numbers = this.readFile();

        int expected = numbers.size() + 1;
        MoveService moveService = new MoveService();
        moveService.writeHighScore(5);

        List<Integer> numbersAfterUpdate = this.readFile();

        Assert.assertEquals(expected, numbersAfterUpdate.size());
    }
}
