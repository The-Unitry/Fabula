package eu.theunitry.fabula.UNGameEngine.graphics;

import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;
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
    private UNView splash;
    private JPanel currentPanel;
    private ArrayList<Music> music;
    private ArrayList<Sound> sounds;
    private ArrayList<Image> sprites, backgrounds;
    private int level, levelMax;

    public UNGameScreen()
    {
        music = new ArrayList<Music>();
        sounds = new ArrayList<Sound>();
        sprites = new ArrayList<Image>();
        backgrounds = new ArrayList<Image>();

        this.window = new UNWindow("Fabula", 768, 512);
        this.launcher = new UNLauncher(this);
        this.splash = new UNView(this, false);

        try {
            this.splash.setBackgroundImage(ImageIO.read(new File("res/backgrounds/splash_unitry.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.window.addPanel(splash);
        this.window.getFrame().setVisible(true);

        TinySound.init();
        preload();

        music.get(0).play(true);
        music.get(0).setVolume(0.1);

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
        music.get(0).stop();
        music.get(index).play(loop);
    }

    public ArrayList<Music> getMusic()
    {
        return this.music;
    }

    public ArrayList<Sound> getSounds()
    {
        return this.sounds;
    }

    public ArrayList<Image> getSprites()
    {
        return this.sprites;
    }

    public ArrayList<Image> getBackgrounds()
    {
        return this.backgrounds;
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

    public void preload()
    {
        /**
         * Font Preload
         */
        try
        {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/Minecraftia.ttf")));
        }
        catch (IOException|FontFormatException e)
        {
            e.printStackTrace();
        }

        /**
         * Music Preload
         */
        music.add(0, TinySound.loadMusic("audio/intro.wav"));
        music.add(1, TinySound.loadMusic("audio/song2.wav"));

        /**
         * Sound Effects Preload
         */
        sounds.add(0, TinySound.loadSound("audio/gibberish.wav"));


        /**
         * Sprites Preload
         */
        try
        {
            //BACKGROUND PRELOAD
            backgrounds.add(0, ImageIO.read(new File("res/backgrounds/underwater.png")));
            backgrounds.add(1, ImageIO.read(new File("res/backgrounds/moon.png")));

            //SPRITE PRELOAD
            //TUILTJE IDLE
            sprites.add(0, ImageIO.read(new File("res/animations/tuiltje/idle/idle0.png")));
            sprites.add(1, ImageIO.read(new File("res/animations/tuiltje/idle/idle1.png")));
            sprites.add(2, ImageIO.read(new File("res/animations/tuiltje/idle/idle2.png")));
            sprites.add(3, ImageIO.read(new File("res/animations/tuiltje/idle/idle3.png")));
            sprites.add(4, ImageIO.read(new File("res/animations/tuiltje/idle/idle4.png")));
            sprites.add(5, ImageIO.read(new File("res/animations/tuiltje/idle/idle5.png")));
            //TUILTJE FLAPPING
            sprites.add(6, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping0.png")));
            sprites.add(7, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping1.png")));
            sprites.add(8, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping2.png")));
            sprites.add(9, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping3.png")));
            sprites.add(10, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping4.png")));
            sprites.add(11, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping5.png")));
            //TUILTJE SAD
            sprites.add(12, ImageIO.read(new File("res/animations/tuiltje/sad/sad0.png")));
            sprites.add(13, ImageIO.read(new File("res/animations/tuiltje/sad/sad1.png")));
            sprites.add(14, ImageIO.read(new File("res/animations/tuiltje/sad/sad2.png")));
            sprites.add(15, ImageIO.read(new File("res/animations/tuiltje/sad/sad3.png")));
            sprites.add(16, ImageIO.read(new File("res/animations/tuiltje/sad/sad4.png")));
            sprites.add(17, ImageIO.read(new File("res/animations/tuiltje/sad/sad5.png")));
            //TUILTJE HAPPY
            sprites.add(18, ImageIO.read(new File("res/animations/tuiltje/happy/happy0.png")));
            sprites.add(19, ImageIO.read(new File("res/animations/tuiltje/happy/happy1.png")));
            sprites.add(20, ImageIO.read(new File("res/animations/tuiltje/happy/happy2.png")));
            sprites.add(21, ImageIO.read(new File("res/animations/tuiltje/happy/happy3.png")));
            sprites.add(22, ImageIO.read(new File("res/animations/tuiltje/happy/happy4.png")));
            sprites.add(23, ImageIO.read(new File("res/animations/tuiltje/happy/happy5.png")));
            sprites.add(24, ImageIO.read(new File("res/animations/tuiltje/happy/happy6.png")));
            sprites.add(25, ImageIO.read(new File("res/animations/tuiltje/happy/happy7.png")));
            sprites.add(26, ImageIO.read(new File("res/animations/tuiltje/happy/happy8.png")));
            sprites.add(27, ImageIO.read(new File("res/animations/tuiltje/happy/happy9.png")));
            //TUILTJE QUESTIONING
            sprites.add(28, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning0.png")));
            sprites.add(29, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning1.png")));
            sprites.add(30, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning2.png")));
            sprites.add(31, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning3.png")));
            sprites.add(32, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning4.png")));
            sprites.add(33, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning5.png")));
            sprites.add(34, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning6.png")));
            sprites.add(35, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning7.png")));
            sprites.add(36, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning8.png")));
            sprites.add(37, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning9.png")));
            //LEVEL0
            sprites.add(38, ImageIO.read(new File("res/sprites/apple.png")));
            sprites.add(39, ImageIO.read(new File("res/sprites/basket.png")));
            sprites.add(40, ImageIO.read(new File("res/sprites/snowball.png")));
            //LEVEL7
            sprites.add(41, ImageIO.read(new File("res/sprites/kist_open.png")));
            sprites.add(42, ImageIO.read(new File("res/sprites/muntje.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
