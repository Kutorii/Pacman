package graphicEngine;

import engine.generic.Coordinates;
import engine.graphicEngine.CoreGraphics;
import engine.graphicEngine.GraphicEntity;
import org.junit.jupiter.api.Test;

public class TestGraphicEngine {
    @Test
    public void testAddRemoveShowInWindow(){
        CoreGraphics frame = new CoreGraphics(800, 800, "CoreGraphics");

        final GraphicEntity gEntity = new GraphicEntity(new Coordinates(100, 100), "image.png");
        final GraphicEntity gEntity2 = new GraphicEntity(new Coordinates(200, 200), "image.png");

        frame.addEntity(gEntity);
        frame.addEntity(gEntity2);
        frame.removeEntity(gEntity2);
        frame.launchFrame();
        frame.getFrame().repaint();

    }


    public static void main(String[] args) {
        CoreGraphics frame = new CoreGraphics(800, 800, "CoreGraphics");

        final GraphicEntity gEntity = new GraphicEntity(new Coordinates(100, 100), "image.png");
        final GraphicEntity gEntity2 = new GraphicEntity(new Coordinates(200, 200), "image.png");

        frame.addEntity(gEntity);
        frame.addEntity(gEntity2);
        frame.removeEntity(gEntity2);
        frame.launchFrame();
        frame.getFrame().repaint();
    }
}
