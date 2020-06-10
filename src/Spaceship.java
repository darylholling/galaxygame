public class Spaceship extends Sprite {
    Location location;

    public Spaceship() {
        super("spaceship.png");
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
