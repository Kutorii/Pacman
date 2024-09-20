package engine.physicEngine;

import engine.physicEngine.collision.PhysicCollisionEvent;

import java.util.ArrayList;

public class CorePhysics {
    private final int windowHeight;
    private final int windowWidth;

    private final ArrayList<PhysicEntity> entities;
    private final ArrayList<PhysicCollisionEvent> physicCollisionEvents;

    public CorePhysics(int windowWidth, int windowHeight, ArrayList<PhysicCollisionEvent> l) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        entities = new ArrayList<>();
        physicCollisionEvents = l;
    }

    public void addEntity(PhysicEntity e) {
        entities.add(e);
    }

    public void removeEntity(PhysicEntity e) {
        entities.remove(e);
    }

    public void removeAllEntities() {
        entities.clear();
    }

    public void updateEntities() {
        physicCollisionEvents.clear();
        for (PhysicEntity e : entities) {
            if (e.getVelocityX() != 0 || e.getVelocityY() != 0) {
                move(e);
            }
        }
        checkCollisions();
    }

    private void move(PhysicEntity e) {
        e.setX(e.getX() + e.getVelocityX());
        e.setY(e.getY() + e.getVelocityY());
    }

    private void checkCollisions() {
        for (int i = 0; i < entities.size(); i++) {
            PhysicEntity e1 = entities.get(i);
            boolean hasCollision = false;
            for (int j = i+1; j < entities.size(); j++) {
                PhysicEntity e2 = entities.get(j);
                if (e2 != e1 && e2.getBounds().intersects(e1.getBounds())) {
                    hasCollision = true;
                    if (!e1.isPassable() && !e2.isPassable()) {
                        e2.setX(e2.getX() - e2.getVelocityX());
                        e2.setY(e2.getY() - e2.getVelocityY());
                    }

                    PhysicCollisionEvent ce = new PhysicCollisionEvent();
                    ce.setCollision(e1, e2);
                    physicCollisionEvents.add(ce);
                }
            }

            if (e1.getY() < 0 || e1.getX() < 0
                    || e1.getY() > windowHeight - e1.getBounds().height
                    || e1.getX() > windowWidth - e1.getBounds().width) {
                hasCollision = true;
                PhysicCollisionEvent ce = new PhysicCollisionEvent();
                ce.setCollision(e1, new PhysicEntity(null, null,0,0,"WINDOW_BORDER"));
                physicCollisionEvents.add(ce);
            }

            if (hasCollision) {
                e1.setX(e1.getX() - e1.getVelocityX());
                e1.setY(e1.getY() - e1.getVelocityY());
            }
        }
    }
}
