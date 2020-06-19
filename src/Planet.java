import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Planet extends Sprite {
    private Boolean isVisited;

    public Planet(){
        super("Planet.png");
    }

    public Boolean isVisited() {
        return isVisited;
    }

    public void setVisited(Boolean visited) {
        isVisited = visited;
        super.setImage(new ImageView(new Image("planetvisited.png")));
    }
}
