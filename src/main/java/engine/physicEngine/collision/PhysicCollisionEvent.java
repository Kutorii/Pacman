package engine.physicEngine.collision;

import engine.physicEngine.PhysicEntity;

public class PhysicCollisionEvent {
    private PhysicEntity e1;
    private PhysicEntity e2;

    public void setCollision(PhysicEntity e1, PhysicEntity e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public PhysicEntity getPhysicEntity1() {
        return e1;
    }

    public PhysicEntity getPhysicEntity2() {
        return e2;
    }
}
