package engine.audio;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.util.Objects;

public class MusicEngine {
    private Clip clip;

    public MusicEngine(){

    }

    public void getMusic(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        InputStream reader = new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(reader);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    public void playMusic() {
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void resume(long clipTimePosition){
        clip.setMicrosecondPosition(clipTimePosition);
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
