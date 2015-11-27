package eu.theunitry.fabula.UNGameEngine.audio;

import eu.theunitry.fabula.UNGameEngine.objects.UNObject;

import javax.sound.sampled.*;

/**
 * This class is deprecated because we are now using an external
 * library.
 */
public abstract class UNAudioObject extends UNObject
{
    private double volume;
    private String src;
    private boolean playing;
    private Clip sound;

    /**
     * UNAudioObject
     * @param src
     */
    public UNAudioObject(String src)
    {
        this.setSrc(src);
        this.setPlaying(false);
    }

    public Clip getSound()
    {
        return this.sound;
    }

    public String getSrc()
    {
        return this.src;
    }

    public void setSrc(String src)
    {
        this.src = "/audio/" + src;
    }

    public double getVolume()
    {
        return this.volume;
    }

    public void setVolume(double volume)
    {
        this.volume = volume;
    }

    public void play()
    {
        this.getSound().start();
        this.setPlaying(true);
    }

    public void stop()
    {
        this.getSound().stop();
        this.setPlaying(false);
    }

    private void setPlaying(boolean playing)
    {
        this.playing = playing;
    }

    public boolean isPlaying()
    {
        return this.playing;
    }

}