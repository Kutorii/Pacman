package engine.graphicEngine;

import engine.generic.Coordinates;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GraphicEntity {
    private Coordinates coordinates;
    private BufferedImage image;
    private Rectangle bounds;
    private Ellipse2D circle;
    private Text text;

    public GraphicEntity(Coordinates coordinates, String path) {
        setImage(path);
        this.coordinates = coordinates;
    }

    public GraphicEntity(Coordinates coordinates, Rectangle bounds) {
        this.coordinates = coordinates;
        this.bounds = bounds;
    }

    public GraphicEntity(Coordinates coordinates, Ellipse2D circle) {
        this.coordinates = coordinates;
        this.circle = circle;
        this.bounds = circle.getBounds();
    }

    public GraphicEntity(Coordinates coordinates, Text text) {
        this.text = text;
        this.coordinates = coordinates;
    }

    public void setText(Text text){
        this.text = text;
        this.bounds = new Rectangle(new Dimension((int) this.text.getWidth(),(int) this.text.getHeight()));
    }

    public void setImage(String path) {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
            bounds = new Rectangle(new Dimension(image.getWidth(), image.getHeight()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getX() {
        return coordinates.getX();
    }

    public int getY() {
        return coordinates.getY();
    }

    public int getWidth() {
        return bounds.width;
    }

    public int getHeight() {
        return bounds.height;
    }

    protected BufferedImage getImage() {
        return image;
    }

    protected Ellipse2D getCircle() {
        return circle;
    }

    protected Text getText() {return text;}
}
