package game;

import engine.generic.CollisionAction;
import engine.generic.Coordinates;
import engine.generic.Distance;
import engine.generic.KeyInputAction;
import engine.generic.MovementStrategy;
import engine.generic.Velocity;
import engine.events.CollisionEvent;
import engine.graphicEngine.Text;
import engine.kernel.AutonomousEntity;
import engine.kernel.CoreKernel;
import engine.kernel.Entity;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Comparator;

public class Game {
    private CoreKernel kernel;
    private final int WIDTH;
    private final int HEIGHT;

    private int nmbCollectible;

    private int score;

    private Menu menu;

    public Game(int width, int height, String name) {
        WIDTH = width;
        HEIGHT = height;
        kernel = new CoreKernel(WIDTH, HEIGHT, name);
        menu = new Menu(kernel, WIDTH, HEIGHT);
        nmbCollectible = 0;
        score = 0;
    }

    public void initGame() {
        Entity titlePacman = new Entity(new Coordinates(150,10),"titlepacman500.png","title");
        kernel.addEntity(titlePacman);
        menu.display();
        kernel.setInputAction(new KeyInputAction() {
            @Override
            public void onKeyPressedAction(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    menu.deleteAllMenuEntity();
                    initLevelOne();
                }
            }
        });
    }

    public void initLevelOne(){
        kernel.setFrameName("Level 1");

        Entity player = new Entity(new Coordinates(400, 362), "Player/player_pacman_right.png", "player");
        kernel.addEntity(player);

        //InitCorner
        Entity downRightCorner = new Entity(new Coordinates(646, 544), "Wall/Blue/DownRightCorner.png", "downRightCorner");
        kernel.addEntity(downRightCorner);
        Entity upRightCorner =  new Entity(new Coordinates(646, 200), "Wall/Blue/UpRightCorner.png", "upRightCorner");
        kernel.addEntity(upRightCorner);
        Entity downLeftCorner = new Entity(new Coordinates(150, 544), "Wall/Blue/DownLeftCorner.png", "downLeftCorner");
        kernel.addEntity(downLeftCorner);
        Entity upLeftCorner =  new Entity(new Coordinates(150, 200), "Wall/Blue/UpLeftCorner.png", "upLeftCorner");
        kernel.addEntity(upLeftCorner);
        //InitWall
        for (int i = 208; i < 542; i= i +8) {
            Entity leftWall = new Entity(new Coordinates(150, i), "Wall/Blue/Wall.png", "leftWall"+i);
            kernel.addEntity(leftWall);
        }
        for (int i = 208; i < 542; i= i +8) {
            Entity rightWall = new Entity(new Coordinates(646, i), "Wall/Blue/Wall.png", "rightWall"+i);
            kernel.addEntity(rightWall);
        }
        for (int i = 158; i < 642; i= i +8) {
            Entity upWall = new Entity(new Coordinates(i, 200), "Wall/Blue/Wall.png", "upWall"+i);
            kernel.addEntity(upWall);
        }
        for (int i = 158; i < 642; i= i +8) {
            Entity downWall = new Entity(new Coordinates(i, 544), "Wall/Blue/Wall.png", "downWall"+i);
            kernel.addEntity(downWall);
        }
        //Init Blocks
        for (int i = 200; i < 600; i = i+150) {
            Entity square = new Entity(new Coordinates(i, 250), "Block/Blue/square100.png", "squareUp"+i);
            kernel.addEntity(square);
        }
        for (int i = 200; i < 600; i = i+150) {
            Entity square = new Entity(new Coordinates(i, 400), "Block/Blue/square100.png", "squareDown"+i);
            kernel.addEntity(square);
        }
//      Init Collectible
        int x = -1;
        for (int i = 179; i < 642; i+=44) {
            if (x%3==0) {
                i+=7;
            }
            Entity collectible = new Entity(new Coordinates(i, 225), new Ellipse2D.Double(i,255,8,8), "collectibleUpH"+i);
            collectible.setPassable(true);
            nmbCollectible++;
            kernel.addEntity(collectible);

            // No collectible on PacMan
            if (!(x == 3 || x == 4)) {
                collectible = new Entity(new Coordinates(i, 370), new Ellipse2D.Double(i, 255, 8, 8), "collectibleMidH" + i);
                collectible.setPassable(true);
                nmbCollectible++;
                kernel.addEntity(collectible);
            }

            collectible = new Entity(new Coordinates(i, 515), new Ellipse2D.Double(i,255,8,8), "collectibleDownH"+i);
            collectible.setPassable(true);
            nmbCollectible++;
            kernel.addEntity(collectible);
            if (x%3==0) {
                i+=7;
            }
            x++;
        }

        x = 0;
        for (int i = 270; i < 516; i+=50) {
            x++;
            if (!(x == 3 || x == 6)) {
                Entity collectible = new Entity(new Coordinates(179, i), new Ellipse2D.Double(179, i, 8, 8), "collectibleLeftV" + i);
                collectible.setPassable(true);
                nmbCollectible++;
                kernel.addEntity(collectible);

                collectible = new Entity(new Coordinates(325, i), new Ellipse2D.Double(311, i, 8, 8), "collectibleMid1" + i);
                collectible.setPassable(true);
                nmbCollectible++;
                kernel.addEntity(collectible);

                collectible = new Entity(new Coordinates(471, i), new Ellipse2D.Double(311, i, 8, 8), "collectibleMid2" + i);
                collectible.setPassable(true);
                nmbCollectible++;
                kernel.addEntity(collectible);

                collectible = new Entity(new Coordinates(617, i), new Ellipse2D.Double(311, i, 8, 8), "collectibleRight" + i);
                collectible.setPassable(true);
                nmbCollectible++;
                kernel.addEntity(collectible);
            }
        }
        // Init Ghost
        AutonomousEntity stalker = createStalkerGhost(player);
        stalker.setPassable(true);
        stalker.setVelocityX(1);
        stalker.setVelocityY(1);
        stalker.setGlobalVelocity(1);
        kernel.addEntity(stalker);

        // Init Score
        Entity gameScore = new Entity(new Coordinates(50,700),new Text("Score : "+ score),"score");
        kernel.addEntity(gameScore);

        initGameCollisionsAction();
        initGameInputs();
    }

    private void initGameCollisionsAction() {
        kernel.setCollisionAction(new CollisionAction() {
            @Override
            public void onCollision(ArrayList<CollisionEvent> collisions) {
                for (CollisionEvent ev: collisions) {
                    Entity e1 = ev.getEntity1();
                    Entity e2 = ev.getEntity2();

                    if (e2.getId().equals("WINDOW_BORDER")) {
                        e1.setVelocityX(0);
                        e1.setVelocityY(0);
                    }

                    if((e2.getId().contains("ghost") && e1.getId().equals("player"))
                        || (e1.getId().contains("ghost") && e2.getId().equals("player"))) {
                        kernel.removeAllEntities();
                        kernel.playLoseMusic();
                        Entity gameOver = new Entity(new Coordinates(50,200),"game over pacman700.png" ," gameover");
                        score = 0;
                        kernel.addEntity(gameOver);
                        kernel.setInputAction(new KeyInputAction() {
                            @Override
                            public void onKeyPressedAction(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                    kernel.removeAllEntities();
                                    initGame();
                                }
                            }
                        });
                    }
                    if(e2.getId().contains("collectible") && e1.getId().equals("player")
                        || e1.getId().contains("collectible") && e2.getId().equals("player")) {
                        kernel.removeEntity(e2);
                        nmbCollectible = nmbCollectible - 1;
                        score = score + 100;
                        kernel.playCoinMusic();
                        Entity e  = kernel.getEntityById("score");
                        e.setText(new Text("Score : " + score));
                        if(nmbCollectible <= 0){
                            kernel.removeAllEntities();
                            kernel.playWinMusic();
                            Entity gameOver = new Entity(new Coordinates(80,200),"you win600.png" ," youWin");
                            score = 0;
                            kernel.addEntity(gameOver);
                            kernel.setInputAction(new KeyInputAction() {
                                @Override
                                public void onKeyPressedAction(KeyEvent e) {
                                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        kernel.removeAllEntities();
                                        initGame();
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });
    }

    private void initGameInputs() {
        kernel.setInputAction(new KeyInputAction() {
            @Override
            public void onKeyPressedAction(KeyEvent e) {
                int velocity = 2;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> {
                        Entity entity = kernel.getEntityById("player");
                        entity.setImage("Player/player_pacman_up.png");
                        entity.setVelocityY(-velocity);
                        entity.setVelocityX(0);
                    }
                    case KeyEvent.VK_DOWN -> {
                        Entity entity = kernel.getEntityById("player");
                        entity.setImage("Player/player_pacman_down.png");
                        entity.setVelocityY(velocity);
                        entity.setVelocityX(0);
                    }
                    case KeyEvent.VK_LEFT -> {
                        Entity entity = kernel.getEntityById("player");
                        entity.setImage("Player/player_pacman_left.png");
                        entity.setVelocityX(-velocity);
                        entity.setVelocityY(0);
                    }
                    case KeyEvent.VK_RIGHT -> {
                        Entity entity = kernel.getEntityById("player");
                        entity.setImage("Player/player_pacman_right.png");
                        entity.setVelocityX(velocity);
                        entity.setVelocityY(0);
                    }
                    case KeyEvent.VK_ESCAPE -> {
                        kernel.pause();
                        KeyInputAction k = kernel.getInputAction();
                        kernel.setInputAction(e1 -> {
                            if (e1.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                kernel.resume();
                                kernel.setInputAction(k);
                            }
                        });
                    }
                }
            }
        });
    }

    private AutonomousEntity createStalkerGhost(Entity player) {
        return new AutonomousEntity(new Coordinates(550, 362), "RedGhost32.png", "ghost",
                new MovementStrategy() {
                    @Override
                    public Coordinates nextMove(int aiX, int aiY, Velocity aiVelocity, Coordinates aiPreviousCoordinates,
                                                ArrayList<Rectangle> listRectangle, Rectangle aiBounds) {

                        int targetX = player.getX();
                        int targetY = player.getY();
                        ArrayList<Distance> listDist= new ArrayList<>();

                        double distUpPose = distBetweenGhostAndTarget(aiX,aiY-aiVelocity.getGlobalVelocity(),targetX,targetY);
                        double distDownPose = distBetweenGhostAndTarget(aiX,aiY+aiVelocity.getGlobalVelocity(),targetX,targetY);
                        double distLeftPose = distBetweenGhostAndTarget(aiX-aiVelocity.getGlobalVelocity(),aiY,targetX,targetY);
                        double distRightPose = distBetweenGhostAndTarget(aiX+aiVelocity.getGlobalVelocity(),aiY,targetX,targetY);

                        Distance distUp = new Distance(distUpPose);
                        Distance distDown = new Distance(distDownPose);
                        Distance distLeft = new Distance(distLeftPose);
                        Distance distRight = new Distance(distRightPose);

                        listDist.add(distUp);
                        listDist.add(distDown);
                        listDist.add(distLeft);
                        listDist.add(distRight);
                        listDist.sort(Comparator.comparing(Distance::getDistance));
                        Coordinates finalCoordinates;

                        while (true) {
                            if (listDist.get(0).equals(distUp)) {
                                Coordinates coordinates = new Coordinates(aiX, aiY - aiVelocity.getGlobalVelocity());
                                Rectangle rectangle = new Rectangle(coordinates.getX(), coordinates.getY(), aiBounds.width, aiBounds.height);
                                if (coordinates.getY() != aiPreviousCoordinates.getY()) {
                                    if(!hasCollision(rectangle,aiBounds, listRectangle)){
                                        finalCoordinates = coordinates;
                                        break;
                                    }
                                }
                            } else if (listDist.get(0).equals(distDown)) {
                                Coordinates coordinates = new Coordinates(aiX, aiY + aiVelocity.getGlobalVelocity());
                                Rectangle rectangle = new Rectangle(coordinates.getX(), coordinates.getY(), aiBounds.width, aiBounds.height);
                                if (coordinates.getY() != aiPreviousCoordinates.getY()) {
                                    if(!hasCollision(rectangle, aiBounds, listRectangle)){
                                        finalCoordinates = coordinates;
                                        break;
                                    }
                                }

                            } else if (listDist.get(0).equals(distLeft)) {
                                Coordinates coordinates = new Coordinates(aiX - aiVelocity.getGlobalVelocity(), aiY);
                                Rectangle rectangle = new Rectangle(coordinates.getX(), coordinates.getY(), aiBounds.width, aiBounds.height);
                                if (coordinates.getX() != aiPreviousCoordinates.getX()) {
                                    if(!hasCollision(rectangle, aiBounds, listRectangle)){
                                        finalCoordinates = coordinates;
                                        break;
                                    }
                                }

                            } else if (listDist.get(0).equals(distRight)) {
                                Coordinates coordinates = new Coordinates(aiX + aiVelocity.getGlobalVelocity(), aiY);
                                Rectangle rectangle = new Rectangle(coordinates.getX(), coordinates.getY(), aiBounds.width, aiBounds.height);
                                if (coordinates.getX() != aiPreviousCoordinates.getX()) {
                                    if(!hasCollision(rectangle, aiBounds, listRectangle)){
                                        finalCoordinates = coordinates;
                                        break;
                                    }
                                }
                            }
                            listDist.remove(0);
                        }

                        return finalCoordinates;
                    }

                    private boolean hasCollision(Rectangle e1, Rectangle ghostBounds, ArrayList<Rectangle> listRectangle) {
                        for (Rectangle rectangle: listRectangle){
                            if(!ghostBounds.equals(rectangle)) {
                                if (e1.intersects(rectangle)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }

                    private double distBetweenGhostAndTarget(int ghostX, int ghostY, int targetX, int targetY) {
                        return Math.sqrt(Math.pow(targetX - ghostX, 2) + Math.pow(targetY - ghostY, 2));
                    }
                });
    }

    public void start() {
        kernel.launch();
    }
}
