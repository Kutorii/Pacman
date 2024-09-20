package engine.inputEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputsListener implements KeyListener {
    private KeyEvent lastKeyPressed;

    public KeyboardInputsListener() {}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        lastKeyPressed = e;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public KeyEvent getLastKeyPressed() {
        return lastKeyPressed;
    }

    public void resetLastKeyPressed() {
        lastKeyPressed = null;
    }

}
