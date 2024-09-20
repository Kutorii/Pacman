package engine.events;

import java.awt.event.KeyEvent;

public class KeyPressedEvent {
    private KeyEvent keyEvent;

    public void setEvent(KeyEvent k) {
        keyEvent = k;
    }

    public KeyEvent getAndResetKeyEvent() {
        KeyEvent k = keyEvent;
        keyEvent = null;
        return k;
    }
}
