package main;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound{

    public Clip clip;
    URL[] soundURL = new URL[10];
    FloatControl fc;

    public Sound() {
        soundURL[0] = Sound.class.getResource("res/sounds/MachineGun.wav");
        soundURL[1] = Sound.class.getResource("res/sounds/Pizzle.wav");
        soundURL[2] = Sound.class.getResource("res/sounds/Pop.wav");
        soundURL[3] = Sound.class.getResource("res/sounds/Shotgun.wav");
        soundURL[4] = Sound.class.getResource("res/sounds/Sniper.wav");
        soundURL[5] = Sound.class.getResource("res/sounds/Spawn.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            ais.close();
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
    public void scaleVol(int v) {
        fc.setValue(v);
    }
}
