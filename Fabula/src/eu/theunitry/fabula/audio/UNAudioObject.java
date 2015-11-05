package eu.theunitry.fabula.audio;

import eu.theunitry.fabula.objects.UNObject;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class UNAudioObject extends UNObject
{

    private double volume;
    private String src;
    private boolean playing;
    private MediaPlayer sound;

    public UNAudioObject(String src) {
        this.setSrc("res/audio/" + src);
        this.setVolume(1);
        this.setPlaying(false);
        this.updateSound(this.getSrc());
    }

    public void updateSound(String src) {
        Media media = new Media(src);
        sound = new MediaPlayer(media);
    }

    public MediaPlayer getSound() {
        return this.sound;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(String src) {
        this.src = "res/audio/" + src;
    }

    public double getVolume() {
        return this.volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
        this.getSound().setVolume(this.getVolume());
    }

    public void play() {
        this.getSound().play();
        this.setPlaying(true);
    }

    public void pause() {
        this.getSound().pause();
        this.setPlaying(false);
    }

    public void stop() {
        this.getSound().stop();
        this.setPlaying(false);
    }

    private void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean getPlaying() {
        return this.playing;
    }

}
