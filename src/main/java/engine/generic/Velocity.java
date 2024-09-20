package engine.generic;

public class Velocity {
    private int velocityX;
    private int velocityY;

    private int globalVelocity;

    public Velocity(int velocityX, int velocityY, int globalVelocity) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.globalVelocity = globalVelocity;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void setGlobalVelocity(int globalVelocity){this.globalVelocity = globalVelocity;}

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public int getGlobalVelocity() {return globalVelocity;}
}
