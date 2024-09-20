package engine.events;

import engine.kernel.Entity;

public class CollisionEvent {
    private Entity e1;
    private Entity e2;

    public void setCollision(Entity e1, Entity e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Entity getEntity1() {
        return e1;
    }

    public Entity getEntity2() {
        return e2;
    }
}
