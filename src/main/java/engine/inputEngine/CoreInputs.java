package engine.inputEngine;

import engine.events.KeyPressedEvent;

import java.awt.event.KeyEvent;

public class CoreInputs {
    private final KeyboardInputsListener keyboardInputsListener;
    private KeyPressedEvent lastKeyPressed;

    public CoreInputs(KeyPressedEvent e) {
        keyboardInputsListener = new KeyboardInputsListener();
        lastKeyPressed = e;
    }

    public void update() {
        KeyEvent e;
        if ((e = keyboardInputsListener.getLastKeyPressed()) != null) {
            keyboardInputsListener.resetLastKeyPressed();
            lastKeyPressed.setEvent(e);
        } else {
            lastKeyPressed.setEvent(null);
        }
    }

    public KeyboardInputsListener getKeyboardInputsListener() {
        return keyboardInputsListener;
    }
}
