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

/**
 * UNGameScreen is the frame where the game is displayed.
 */
public class UNGameScreen extends UNObject
{
    private UNWindow window;
    private UNLauncher launcher;
    private UNGraphicsView splash;
    private JPanel currentPanel;
    private UNResourceLoader unResourceLoader;

    private int level, levelMax;

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

//        unResourceLoader.music.get(0).play(true);
//        unResourceLoader.music.get(0).setVolume(0.1);

        this.window.removePanel(splash);
        this.window.addPanel(launcher);
        this.currentPanel = launcher;
        this.window.getFrame().setVisible(true);

        this.level = 1;
        this.levelMax = 5;
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

    public void switchMusic(int index, boolean loop)
    {
        unResourceLoader.music.get(0).stop();
        unResourceLoader.music.get(index).play(loop);
    }

    public ArrayList<Music> getMusic()
    {
        return unResourceLoader.music;
    }

    public ArrayList<Sound> getSounds()
    {
        return unResourceLoader.sounds;
    }

    public ArrayList<Image> getSprites()
    {
        return unResourceLoader.sprites;
    }

    public ArrayList<Image> getBackgrounds()
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


}
