package engine.kernel;

import engine.generic.Coordinates;
import engine.generic.Velocity;
import engine.graphicEngine.GraphicEntity;
import engine.graphicEngine.Text;
import engine.physicEngine.PhysicEntity;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Entity {
    private final PhysicEntity physicEntity;
    private final GraphicEntity graphicEntity;
    private final String id;

    public Entity(Coordinates coordinates, String imagePath, String id) {
        Velocity v = new Velocity(0, 0, 0);
        graphicEntity = new GraphicEntity(coordinates, imagePath);
        physicEntity = new PhysicEntity(coordinates, v, graphicEntity.getWidth(),
                graphicEntity.getHeight(),
                id);
        this.id = id;

    }

    public Entity(Coordinates coordinates, Rectangle bounds, String id) {
        Velocity v = new Velocity(0, 0, 0);
        graphicEntity = new GraphicEntity(coordinates, bounds);
        physicEntity = new PhysicEntity(coordinates, v, bounds.width, bounds.height, id);
        this.id = id;

    }

    public Entity(Coordinates coordinates, Ellipse2D circle, String id) {
        Velocity v = new Velocity(0, 0, 0);
        graphicEntity = new GraphicEntity(coordinates, circle);
        physicEntity = new PhysicEntity(coordinates, v, (int) circle.getWidth(), (int) circle.getHeight(), id);
        this.id = id;
    }

    public Entity(Coordinates coordinates, Text text, String id) {
        Velocity v = new Velocity(0, 0, 0);
        graphicEntity = new GraphicEntity(coordinates, text);
        physicEntity = new PhysicEntity(coordinates, v, (int)text.getWidth(), (int)text.getHeight(), id);
        this.id = id;
    }

    public void setVelocityX(int velocityX) {
        physicEntity.setVelocityX(velocityX);
    }

    public void setVelocityY(int velocityY) {
        physicEntity.setVelocityY(velocityY);
    }

    public void setGlobalVelocity(int globalVelocity) {
        physicEntity.setGlobalVelocity(globalVelocity);
    }

    public void setPassable(boolean passable) {
        physicEntity.setPassable(passable);
    }

    public boolean isPassable() {
        return physicEntity.isPassable();
    }

    public Coordinates getPrevCoordinates() {
        return physicEntity.getPrevCoordinates();
    }

    public Coordinates getCoordinates() {
        return physicEntity.getCoordinates();
    }

    public int getX() {
        return physicEntity.getX();
    }

    public int getY() {
        return physicEntity.getY();
    }

    public int getWidth(){
        return physicEntity.getWidth();
    }

    public int getHeight(){
        return physicEntity.getHeight();
    }

    public void setImage(String imagePath) {
        graphicEntity.setImage(imagePath);
    }

    public void setText(Text Text) {
        graphicEntity.setText(Text);
    }

    public Rectangle getBounds() {
        return physicEntity.getBounds();
    }

    public int getVelocityX() {
        return physicEntity.getVelocityX();
    }

    public int getVelocityY() {
        return physicEntity.getVelocityY();
    }

    public int getGlobalVelocity() {
        return physicEntity.getGlobalVelocity();
    }

    protected Velocity getVelocity() {
        return physicEntity.getVelocity();
    }

    protected PhysicEntity getPhysicEntity() {
        return physicEntity;
    }

    protected GraphicEntity getGraphicEntity() {
        return graphicEntity;
    }

    public String getId() {
        return id;
    }
}
