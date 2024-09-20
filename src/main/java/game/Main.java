package game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(800, 800, "Game");
        game.initGame();
        game.start();
    }
}
