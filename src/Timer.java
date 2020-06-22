import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

//import javax.imageio.plugins.tiff.TIFFImageReadParam;

public class Timer extends AnimationTimer {
    Group root;
    private long timestamp;
    private long time = 0;
    private long fraction = 0;
    Label timerLabel = new Label();

    public Timer(Group root) {
        this.root = root;
    }

    @Override
    public void start() {
        // current time adjusted by remaining time from last run
        timestamp = System.currentTimeMillis() - fraction;
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        // save leftover time not handled with the last update
        fraction = System.currentTimeMillis() - timestamp;
    }

    @Override
    public void handle(long now) {
        long newTime = System.currentTimeMillis();
        if (timestamp + 1000 <= newTime) {
            long deltaT = (newTime - timestamp) / 1000;
            time += deltaT;
            timestamp += 1000 * deltaT;

//            System.out.println(Long.toString(time));
            timerLabel.setText(Long.toString(time));
            timerLabel.setTextFill(Color.RED);
            timerLabel.setStyle("-fx-font: 45 verdana;");


            // gap between components is 20
            HBox hb = new HBox(20);
            // Relocate Hb Box
            hb.relocate(30, 1);
            // Add the label and timerLabel to the VBox
            hb.getChildren().addAll(timerLabel);
            // Add the HBox to the root component
            root.getChildren().add(hb);

        }
    }
}
