package org.game;

import java.io.InputStream;
import java.io.BufferedInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
 /**
     * Sound Class to manage game sounds
     * 
     * @author Nicole Malku
     */
public class Sound {
    Clip clip;
    private String[] soundFilePath = new String[30];
    FloatControl flt;
    int volSlider = 3;
    float vol;


    /**
     * Sound constructor to grab the .wav files
     */
    public Sound(){
        soundFilePath[0] = "/sound/gamesong.wav";
        soundFilePath[1] = "/sound/rewardsound.wav";
        soundFilePath[2] = "/sound/winningsong.wav";
        soundFilePath[3] = "/sound/hitsound.wav";
        soundFilePath[4] = "/sound/intro.wav";
        soundFilePath[5] = "/sound/selection.wav";
        soundFilePath[6] = "/sound/enter.wav";
        soundFilePath[7] = "/sound/exit.wav";
        soundFilePath[8] = "/sound/failure.wav";
        soundFilePath[9] = "/sound/teleport.wav";
        soundFilePath[10] = "/sound/punishment.wav";
        soundFilePath[11] = "/sound/endloss.wav";
        
    }
    
    /**
     * Sets the audio file to be used by the sound player at the specified index.
     * 
     * 
     * @param i the index of the sound desired from soundFilePath
     * 
     */
    public void setFile(int i) {
        try
        {   
            InputStream is = getClass().getResourceAsStream(soundFilePath[i]);
            InputStream buffered = new BufferedInputStream(is);
            AudioInputStream aud = AudioSystem.getAudioInputStream(buffered);
                clip = AudioSystem.getClip();
                clip.open(aud);
                flt =(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        if(clip != null)    clip.stop();

    }
    public void volume(){
        switch(volSlider){
        case 0: vol = -80f; break;
        case 1: vol = -20f; break;
        case 2: vol = -12f;break;
        case 3: vol = -6f;break;
        case 4: vol = 1f; break;
        case 5: vol = 6f;break;
        }
        flt.setValue(vol);
    }
}
