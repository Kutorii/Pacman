package engine.physicEngine;

import engine.generic.Coordinates;
import engine.generic.Velocity;

import java.awt.Rectangle;

public class PhysicEntity {
    private Coordinates coordinates;
    private Velocity velocity;

    private int height;
    private int width;
    private boolean passable;
    private String id;

    public PhysicEntity(Coordinates coordinates, Velocity velocity, int width, int height, String id) {
        this.coordinates = coordinates;
        this.height = height;
        this.width = width;
        this.velocity = velocity;
        this.passable = false;
        this.id = id;
    }

    public Coordinates getPrevCoordinates() {
        return new Coordinates(getX()-getVelocityX(),getY()-getVelocityY());
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getX() {
        return coordinates.getX();
    }

    public int getY() {
        return coordinates.getY();
    }

    public int getVelocityX() {
        return velocity.getVelocityX();
    }

    public int getVelocityY() {
        return velocity.getVelocityY();
    }

    public int getGlobalVelocity() { return velocity.getGlobalVelocity();}

    public Velocity getVelocity() {
        return velocity;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return new Rectangle(coordinates.getX(), coordinates.getY(), width, height);
    }

    public void setX(int x) {
        coordinates.setX(x);
    }

    public void setY(int y) {
        coordinates.setY(y);
    }

    public void setVelocityX(int velocityX) {
        velocity.setVelocityX(velocityX);
    }

    public void setVelocityY(int velocityY) {
        velocity.setVelocityY(velocityY);
    }

    public void setGlobalVelocity(int globalVelocity) {velocity.setGlobalVelocity(globalVelocity);}

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public boolean isPassable() {
        return passable;
    }

    public String getId() {
        return id;
    }
}
