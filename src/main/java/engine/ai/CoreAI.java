package engine.ai;

import engine.generic.Coordinates;

import java.awt.Rectangle;
import java.util.ArrayList;

public class CoreAI {
    private final ArrayList<AIEntity> entities;
    private final ArrayList<Rectangle> hitBoxes;

    public CoreAI(ArrayList<Rectangle> hitBoxes) {
        entities = new ArrayList<>();
        this.hitBoxes = hitBoxes;
    }

    public void addEntity(AIEntity entity) {
        entities.add(entity);
    }

    public void removeEntity(AIEntity entity) {
        entities.remove(entity);
    }

    public void removeAllEntities() {
        entities.clear();
    }

    public void updateEntities() {
        for (AIEntity entity : entities) {
            Coordinates nextCoordinate =  entity.nextMove(entity.getCoordinates().getX(), entity.getCoordinates().getY(),
                    entity.getVelocity(), entity.getPreviousCoordinates(), hitBoxes, entity.getBounds());

            if (nextCoordinate.getX() > entity.getCoordinates().getX()) {
                entity.getVelocity().setVelocityX(entity.getVelocity().getGlobalVelocity());
                entity.getVelocity().setVelocityY(0);
            }
            else if (nextCoordinate.getX() < entity.getCoordinates().getX()) {
                entity.getVelocity().setVelocityX(-entity.getVelocity().getGlobalVelocity());
                entity.getVelocity().setVelocityY(0);
            }
            else if (nextCoordinate.getY() > entity.getCoordinates().getY()) {
                entity.getVelocity().setVelocityX(0);
                entity.getVelocity().setVelocityY(entity.getVelocity().getGlobalVelocity());
            }
            else if (nextCoordinate.getY() < entity.getCoordinates().getY()) {
                entity.getVelocity().setVelocityX(0);
                entity.getVelocity().setVelocityY(-entity.getVelocity().getGlobalVelocity());
            }
        }
    }
}
