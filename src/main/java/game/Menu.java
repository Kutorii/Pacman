package game;

import engine.generic.Coordinates;
import engine.kernel.CoreKernel;
import engine.kernel.Entity;

public class Menu {
    private int WIDTH;
    private int HEIGHT;
    private CoreKernel kernel;
    private Entity levelOne;

    public Menu(CoreKernel kernel, int width, int height) {
        this.kernel = kernel;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    public void display() {
        levelOne = new Entity(new Coordinates(300,400),"press enter pacman200.png","levelOne");
        kernel.addEntity(levelOne);
        kernel.launchGraphic();
    }

    public void deleteAllMenuEntity(){
        kernel.removeEntity(levelOne);
    }
}
