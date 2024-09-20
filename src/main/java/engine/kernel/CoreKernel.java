package engine.kernel;

import engine.ai.CoreAI;
import engine.generic.CollisionAction;
import engine.events.CollisionEvent;
import engine.generic.KeyInputAction;
import engine.events.KeyPressedEvent;
import engine.audio.MusicEngine;
import engine.inputEngine.CoreInputs;
import engine.graphicEngine.CoreGraphics;
import engine.physicEngine.CorePhysics;
import engine.physicEngine.PhysicEntity;
import engine.physicEngine.collision.PhysicCollisionEvent;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CoreKernel {
    private final CorePhysics corePhysics;
    private final CoreGraphics coreGraphics;
    private final CoreAI coreAI;
    private final CoreInputs coreInputs;
    private final HashMap<String, Entity> entities;

    private final long FPS_SET = 1000/60;

    private final MusicEngine musicEngine;

    private KeyInputAction inputAction;
    private final KeyPressedEvent lastKeyPressed;
    private final ArrayList<PhysicCollisionEvent> physicCollisionEvents;
    private final ArrayList<Rectangle> wallhitBoxes;
    private CollisionAction collisionAction;
    private boolean isPaused;

    public CoreKernel(int width, int height, String name) {
        coreGraphics = new CoreGraphics(width, height, name);
        physicCollisionEvents = new ArrayList<>();
        corePhysics = new CorePhysics(coreGraphics.getFrame().getContentPane().getSize().width,
                coreGraphics.getFrame().getContentPane().getSize().height,
                physicCollisionEvents);
        lastKeyPressed = new KeyPressedEvent();
        wallhitBoxes = new ArrayList<>();
        coreAI = new CoreAI(wallhitBoxes);
        coreInputs = new CoreInputs(lastKeyPressed);
        entities = new HashMap<>();
        coreGraphics.getFrame().addKeyListener(coreInputs.getKeyboardInputsListener());
        musicEngine = new MusicEngine();
        inputAction = e -> {};
        collisionAction = l -> {};
        isPaused = false;
    }

    public void launchGraphic() {
        coreGraphics.launchFrame();
    }

    public void launch() {
        while (true) {
            updateInputs();

            if (!isPaused) {
                corePhysics.updateEntities();
                coreGraphics.repaint();
                onCollisionEvent();
                coreAI.updateEntities();
            }

            try {
                Thread.sleep(FPS_SET);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
    }

    public void setInputAction(KeyInputAction k) {
        inputAction = k;
    }

    public void setCollisionAction(CollisionAction c) {collisionAction = c;}

    public void addEntity(Entity e) {
        if (entities.put(e.getId(), e) != null) {
            throw new IllegalArgumentException(
                    String.format("Entity with id %s already exists", e.getId()));
        }
        if (e instanceof AutonomousEntity) {
            coreAI.addEntity(((AutonomousEntity) e).getAiEntity());
        }
        corePhysics.addEntity(e.getPhysicEntity());
        coreGraphics.addEntity(e.getGraphicEntity());
        if(e.getId().contains("Wall") || e.getId().contains("square")){
            wallhitBoxes.add(e.getBounds());
        }
    }

    public void removeEntity(Entity e) {
        entities.remove(e.getId());
        if (e instanceof AutonomousEntity) {
            coreAI.removeEntity(((AutonomousEntity) e).getAiEntity());
        }
        corePhysics.removeEntity(e.getPhysicEntity());
        coreGraphics.removeEntity(e.getGraphicEntity());
        if(e.getId().contains("Wall") || e.getId().contains("square")){
            wallhitBoxes.remove(e.getBounds());
        }
    }

    public void removeAllEntities() {
        entities.clear();
        corePhysics.removeAllEntities();
        coreGraphics.removeAllEntities();
        coreAI.removeAllEntities();
        wallhitBoxes.clear();
    }

    public Entity getEntityById(String id) {
        return entities.get(id);
    }

    private void updateInputs() {
        coreInputs.update();

        KeyEvent e;
        if ((e = lastKeyPressed.getAndResetKeyEvent()) != null){
            inputAction.onKeyPressedAction(e);
        }
    }

    private void onCollisionEvent() {
        ArrayList<CollisionEvent> collisions = new ArrayList<>();

        for (PhysicCollisionEvent pe : physicCollisionEvents) {
            PhysicEntity pe1 = pe.getPhysicEntity1();
            PhysicEntity pe2 = pe.getPhysicEntity2();

            Entity e1 = entities.get(pe1.getId());
            Entity e2 = entities.get(pe2.getId());

            if (e2 == null) {
                e2 = new Entity(null, new Rectangle(), "WINDOW_BORDER");
                e2.setPassable(true);
            }

            CollisionEvent ev = new CollisionEvent();
            ev.setCollision(e1, e2);
            collisions.add(ev);
        }

        collisionAction.onCollision(collisions);
    }


    public void playCoinMusic() {
        try {
            musicEngine.getMusic("coin.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        musicEngine.playMusic();
    }

    public void playWinMusic() {
        try {
            musicEngine.getMusic("win.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        musicEngine.playMusic();
    }

    public void playLoseMusic() {
        try {
            musicEngine.getMusic("lose.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        musicEngine.playMusic();
    }

    public void stopMusic() {
        musicEngine.stop();
    }

    public void setBackgroundColor(Color c) {
        coreGraphics.setBackgroundColor(c);
    }

    public void setFrameName(String s) {
        coreGraphics.setTitle(s);
    }

    public KeyInputAction getInputAction() {
        return inputAction;
    }
}
