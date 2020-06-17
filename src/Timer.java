import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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

            System.out.println(Long.toString(time));
            timerLabel.setText(Long.toString(time));
            // Create and configure VBox
            // gap between components is 20
            VBox vb = new VBox(20);
            // center the components within VBox
            vb.setAlignment(Pos.CENTER);
            // Make it as wide as the application frame (scene)
//            vb.setPrefWidth(scene.getWidth());
            // Move the VBox down a bit
            vb.setLayoutY(30);
            // Add the button and timerLabel to the VBox
            vb.getChildren().add(timerLabel);
            // Add the VBox to the root component
            root.getChildren().add(vb);
        }
    }
}
