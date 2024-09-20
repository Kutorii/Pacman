import engine.audio.MusicEngine;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class TestAudio {
    @Test
    public void testPlayAudio(){
        MusicEngine musicEngine = new MusicEngine();
        try {
            musicEngine.getMusic("bird.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        //le temps d'écouter le son

        try {
            musicEngine.playMusic();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStopAudio(){
        MusicEngine musicEngine = new MusicEngine();
        try {
            musicEngine.getMusic("bird.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        //le temps d'écouter le son

        try {
            musicEngine.playMusic();
            Thread.sleep(1000);
            musicEngine.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
