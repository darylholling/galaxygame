import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Timer extends AnimationTimer {
    Group root;
    long timestamp;
    long time = 0;
    long fraction = 0;
    Label timerLabel = new Label();

    public Timer(Group root) {
        this.root = root;
    }

    @Override
    public void start() {
        timestamp = System.currentTimeMillis() - fraction;
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        fraction = System.currentTimeMillis() - timestamp;
    }

    @Override
    public void handle(long now) {
        long newTime = System.currentTimeMillis();
        long deltaT = (newTime - timestamp) / 1000;
        time += deltaT;
        timestamp += 1000 * deltaT;

        timerLabel.setMinSize(600, 35);
        timerLabel.setText("Seconds played: " + Long.toString(time));
        timerLabel.setTextFill(Color.ALICEBLUE);
        timerLabel.setStyle("-fx-font: 35 fantasy;" +
                "-fx-background-image:url('wp1.gif') ");

        HBox hb = new HBox();
        hb.getChildren().addAll(timerLabel);
        root.getChildren().add(hb);
    }
}
