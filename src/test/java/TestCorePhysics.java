import static org.assertj.core.api.Assertions.assertThat;

import engine.generic.Coordinates;
import engine.generic.Velocity;
import engine.physicEngine.CorePhysics;
import engine.physicEngine.PhysicEntity;
import engine.physicEngine.collision.PhysicCollisionEvent;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class TestCorePhysics {
    @Test
    public void testCollision() throws NoSuchFieldException, IllegalAccessException {
        CorePhysics physics = new CorePhysics(800,800, new ArrayList<>());

        Field field = physics.getClass().getDeclaredField("physicCollisionEvents");
        field.setAccessible(true);
        ArrayList<PhysicCollisionEvent> collisionEvents = (ArrayList<PhysicCollisionEvent>) field.get(physics);

        PhysicEntity e1 = new PhysicEntity(new Coordinates(0, 0), new Velocity(0,0,0),20, 20, "1");
        PhysicEntity e2 = new PhysicEntity(new Coordinates(0, 0), new Velocity(0,0,0),10, 10, "2");
        physics.addEntity(e1);
        physics.addEntity(e2);
        // Collision with e2
        physics.updateEntities();
        assertThat(collisionEvents.get(0).getPhysicEntity2() == e2).isTrue();

        PhysicEntity e3 = new PhysicEntity(new Coordinates(50, 50), new Velocity(0,0,0),10, 10, null);
        physics.addEntity(e3);
        // No collision with current rectangles and further rectangles
        physics.updateEntities();
        assertThat(collisionEvents.size() == 1).isTrue();

        PhysicEntity e4 = new PhysicEntity(new Coordinates(100, 100), new Velocity(0,0,0),200 ,200, null);
        PhysicEntity e5 = new PhysicEntity(new Coordinates(200, 200), new Velocity(0,0,0),200, 200, null);
        physics.addEntity(e4);
        physics.addEntity(e5);
        // Collision with e5
        physics.updateEntities();
        assertThat(collisionEvents.get(1).getPhysicEntity2() == e5).isTrue();

        PhysicEntity e6 = new PhysicEntity(new Coordinates(399, 399), new Velocity(0,0,0), 200, 200, null);
        physics.addEntity(e6);
        // Collision with e5
        physics.updateEntities();
        assertThat(collisionEvents.get(2).getPhysicEntity2() == e6).isTrue();

        physics.removeEntity(e6);
        PhysicEntity e7 = new PhysicEntity(new Coordinates(400, 400), new Velocity(0,0,0),200, 200, null);
        physics.addEntity(e7);
        // No collision with none of the rectangles on the panel
        physics.updateEntities();
        assertThat(collisionEvents.size() == 2).isTrue();

        PhysicEntity e8 = new PhysicEntity(new Coordinates(500, 500), new Velocity(0,0,0),10, 10, null);
        e8.setPassable(true);
        physics.addEntity(e8);
        // e8 has no hit box
        // but is still counted has a collision.
        physics.updateEntities();
        assertThat(collisionEvents.get(2).getPhysicEntity2() == e8).isTrue();
    }
}
