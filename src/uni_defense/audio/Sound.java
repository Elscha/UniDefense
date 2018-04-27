package uni_defense.audio;


import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//source: https://stackoverflow.com/questions/23986793/java-playing-a-sound-on-top-of-background-music

public class Sound {
    private boolean loop = false;
    private AudioInputStream ais = null;
    private Clip clip = null;
    //declaration of variables

    public Sound (String fileName)
    //Constructor for the class which fileName and accepts whether the clip needs to loop or not
    {
        this.loop = false;
        //sets the variable within the class as constructor 

        try {
            clip = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(Music.class.getResource(fileName));
            clip.open(ais);

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        //tries to load file into java's built in audio player, else prints the error to console
    }

    public void soundStart ()
    //starts music
    {
        if (loop)
        {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            //starts music on loop if loop is requested
        }
        else
        {
            clip.start();
            //starts music as not on loop
        }

    }


    public void soundStop ()
    //stops the music
    {
        clip.stop();
    }

}