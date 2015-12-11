package eu.theunitry.fabula.UNGameEngine.graphics;

import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;
import eu.theunitry.fabula.UNGameEngine.launcher.UNResourceLoader;
import eu.theunitry.fabula.UNGameEngine.objects.UNObject;
import kuusisto.tinysound.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * UNGameScreen is the frame where the game is displayed.
 */
public class UNGameScreen extends UNObject
{
    private UNWindow window;
    private UNLauncher launcher;
    private UNGraphicsView splash;
    private JPanel currentPanel;
    public UNResourceLoader unResourceLoader;
    private boolean adventure;

    private int level, levelMax, subLevel4;

    public UNGameScreen()
    {
        this.window = new UNWindow("Fabula", 768, 512);
        this.launcher = new UNLauncher(this);
        this.splash = new UNGraphicsView(this, false);

        try {
            this.splash.setBackgroundImage(ImageIO.read(new File("res/backgrounds/splash_unitry.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.window.addPanel(splash);
        this.window.getFrame().setVisible(true);

        TinySound.init();

        unResourceLoader = new UNResourceLoader();

        unResourceLoader.music.get("intro").play(true);
        unResourceLoader.music.get("intro").setVolume(0.1);

        this.window.removePanel(splash);
        this.window.addPanel(launcher);
        this.currentPanel = launcher;
        this.window.getFrame().setVisible(true);

        this.level = 1;
        this.subLevel4 = 1;
        this.levelMax = 5;

        this.setAdventure(false);
    }

    public void switchPanel(JPanel panel)
    {
        this.window.removePanel(currentPanel);
        this.window.addPanel(panel);
        this.currentPanel = panel;
        this.window.getFrame().setVisible(true);
    }

    public UNWindow getWindow()
    {
        return this.window;
    }

    public void stopAudio()
    {
        for (Map.Entry<String, Music> music : this.getMusic().entrySet()) {
            music.getValue().stop();
        }
        for (Map.Entry<String, Sound> sound : this.getSounds().entrySet()) {
            sound.getValue().stop();
        }
    }

    public void switchToLauncher()
    {
        this.switchPanel(new UNLauncher(this));
        this.stopAudio();
        this.getMusic().get("intro").play(true);
        this.getMusic().get("intro").setVolume(0.1);
    }

    public void switchToCredits()
    {
        this.switchPanel(new UNCreditsView(this));
        this.stopAudio();
    }

    public void switchMusic(String index, boolean loop)
    {
        unResourceLoader.music.get("intro").stop();
        unResourceLoader.music.get(index).play(loop);
    }

    public Map<String, Music> getMusic()
    {
        return unResourceLoader.music;
    }

    public Map<String, Sound> getSounds()
    {
        return unResourceLoader.sounds;
    }

    public Map<String, Image> getSprites()
    {
        return unResourceLoader.sprites;
    }

    public Map<String, Image> getBackgrounds()
    {
        return unResourceLoader.backgrounds;
    }

    public int getLevel()
    {
        return level;
    }

    public int getLevelMax()
    {
        return levelMax;
    }

    public void addLevel()
    {
        this.level++;
    }

    public void resetProgress()
    {
        this.level = 1;
    }

    public UNLauncher getLauncher() {
        return this.launcher;
    }

    public void setLauncher(UNLauncher launcher) {
        this.launcher = launcher;
    }

    public boolean isAdventure() {
        return this.adventure;
    }

    public void setAdventure(boolean adventure) {
        this.adventure = adventure;
    }

    //For Level 4
    public int getSubLevel4()
    {
        return subLevel4;
    }

    public void setSubLevel4(int subLevel4)
    {
        this.subLevel4 = subLevel4;
    }

    public void resetSubLevel4()
    {
        this.subLevel4 = 1;
    }

    public void addSubLevel4()
    {
        this.subLevel4++;
    }
}
