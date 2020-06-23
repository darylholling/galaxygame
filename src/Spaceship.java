public class Spaceship extends Sprite implements MovableInterface {
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

    @Override
    public void move(Sprite sprite, Location location, MoveService moveService) {
        if (location.hasPlanet()) {
            moveService.visitPlanet((Spaceship) sprite, location);
            return;
        } else if (location.hasMeteorite()) {
            moveService.updateScene(false);
            return;
        } else if (location.hasWormhole()) {
            moveService.visitWormhole();
            return;
        }

        sprite.getLocation().removeSprite(sprite);
        sprite.setLocation(location);
        moveService.gridPane.setColumnIndex(sprite, location.getColumn());
        moveService.gridPane.setRowIndex(sprite, location.getRow());
    }
}
