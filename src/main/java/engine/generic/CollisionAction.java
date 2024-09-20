package engine.generic;

import engine.events.CollisionEvent;

import java.util.ArrayList;

public interface CollisionAction {

    void onCollision(ArrayList<CollisionEvent> collisions);
}
