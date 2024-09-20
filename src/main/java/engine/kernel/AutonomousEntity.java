package engine.kernel;

import engine.ai.AIEntity;
import engine.generic.Coordinates;
import engine.generic.MovementStrategy;

import java.awt.Rectangle;

public class AutonomousEntity extends Entity {
    private final AIEntity aiEntity;

    public AutonomousEntity(Coordinates coordinates, String imagePath, String id, MovementStrategy move) {
        super(coordinates, imagePath, id);

        aiEntity = new AIEntity(coordinates, getVelocity(), getBounds(), move);
    }

    public AutonomousEntity(Coordinates coordinates, Rectangle bounds, String id, MovementStrategy move) {
        super(coordinates, bounds, id);

        aiEntity = new AIEntity(coordinates, getVelocity(), bounds, move);
    }

    protected AIEntity getAiEntity() {
        return aiEntity;
    }
}
