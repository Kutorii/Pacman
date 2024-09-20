import engine.events.KeyPressedEvent;
import engine.inputEngine.CoreInputs;
import org.junit.jupiter.api.Test;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.KeyEvent;


public class TestInput {
    @Test
    public void testInputListener() throws InterruptedException {
        KeyPressedEvent kpe = new KeyPressedEvent();
        CoreInputs coreInputs = new CoreInputs(kpe);

        JFrame frame = new JFrame();
        frame.setSize(new Dimension(100,100));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(coreInputs.getKeyboardInputsListener());
        frame.setVisible(true);

        while (true) {
            coreInputs.update();
            KeyEvent e;
            if ((e = kpe.getAndResetKeyEvent()) != null) {
                //Afficher l'évenement remonté
                System.out.println(e.getKeyChar() + " " + e.getKeyCode());
            }
            Thread.sleep(100);
        }
    }
}
