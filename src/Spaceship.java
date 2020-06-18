public class Spaceship extends Sprite {
    Integer planetsVisited = 0;

    public Spaceship() {
        super("spaceship.png");
    }

    public Integer getPlanetsVisited() {
        return planetsVisited;
    }

    public void setPlanetsVisited(Integer planetsVisited) {
        this.planetsVisited = planetsVisited;
    }
}
