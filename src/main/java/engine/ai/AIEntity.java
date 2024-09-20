package engine.ai;

import engine.generic.Coordinates;
import engine.generic.MovementStrategy;
import engine.generic.Velocity;

import java.awt.Rectangle;
import java.util.ArrayList;

public class AIEntity {
    private Coordinates coordinates;
    private Velocity velocity;
    private Rectangle bounds;
    private MovementStrategy movement;

    public AIEntity(Coordinates coordinates, Velocity velocity, Rectangle bounds, MovementStrategy move) {
        this.coordinates = coordinates;
        this.velocity = velocity;
        this.bounds = bounds;
        movement = move;
    }

    protected Coordinates nextMove(int ghostX, int ghostY, Velocity ghostVelocity,
                            Coordinates ghostPrevCoordinates, ArrayList<Rectangle> listRectangle, Rectangle ghostBounds) {
        return movement.nextMove(ghostX, ghostY, ghostVelocity,
        ghostPrevCoordinates, listRectangle, ghostBounds);
    }

    protected Coordinates getPreviousCoordinates() {
        return new Coordinates(coordinates.getX()-velocity.getVelocityX(),coordinates.getY()-velocity.getVelocityY());
    }

    protected Coordinates getCoordinates() {
        return coordinates;
    }

    protected Velocity getVelocity() {
        return velocity;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
