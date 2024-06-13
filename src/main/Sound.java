package main;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {
    private Clip clip;
    private URL[] soundURL = new URL[30];
    private Clip[] clips = new Clip[30];
    private long clipTimePosition = 0;

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/changePosition.wav");
        soundURL[1] = getClass().getResource("/sound/fastDrop.wav");
        soundURL[2] = getClass().getResource("/sound/score1.wav");
        soundURL[3] = getClass().getResource("/sound/score2.wav");
        soundURL[4] = getClass().getResource("/sound/score3.wav");
        soundURL[5] = getClass().getResource("/sound/mainTheme.wav");

        // Initialize clips array
        for (int i = 0; i < soundURL.length; i++) {
            if (soundURL[i] != null) {
                try {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
                    clips[i] = AudioSystem.getClip();
                    clips[i].open(ais);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setFile(int i) {
        if (i >= 0 && i < clips.length) {
            clip = clips[i];
            clipTimePosition = 0;  // Reset the position when a new clip is set
        }
    }

    public void play() {
        if (clip != null) {
            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void stopSpecificClip(int i) {
        if (i >= 0 && i < clips.length && clips[i] != null) {
            clips[i].stop();
        }
    }


    public Clip getCurrentClip() {
        return clip;
    }
}
