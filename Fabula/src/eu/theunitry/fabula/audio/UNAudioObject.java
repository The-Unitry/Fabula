package eu.theunitry.fabula.audio;

import eu.theunitry.fabula.objects.UNObject;
import kuusisto.tinysound.Music;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public abstract class UNAudioObject extends UNObject
{
    private double volume;
    private String src;
    private boolean playing;
    private Clip sound;

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