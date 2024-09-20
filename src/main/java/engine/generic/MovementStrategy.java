package engine.generic;

import java.awt.Rectangle;
import java.util.ArrayList;

public interface MovementStrategy {

    Coordinates nextMove(int aiX, int aiY, Velocity aiVelocity,
                  Coordinates aiPreviousCoordinates, ArrayList<Rectangle> hitboxes, Rectangle aiBounds);
}
